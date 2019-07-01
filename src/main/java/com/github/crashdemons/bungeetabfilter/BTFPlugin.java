/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.bungeetabfilter;

import com.github.crashdemons.aztectabcompleter.filters.FilterArgs;
import com.github.crashdemons.aztectabcompleter.filters.FilterSet;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteResponseEvent;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class BTFPlugin extends BasicPlugin {
    public static BTFPlugin instance = null;
    
    //internal variables 
    private final FilterSet filters;

    //runtime behavior variables
    public volatile boolean loaded = false;
    private volatile boolean ready = false;

    
    public boolean dumpFiltering = false;
    
    public BTFPlugin() {
        instance=this;
        filters = new FilterSet(this);
    }
    

    private void log(String s) {
        getLogger().info(s);
    }

/*
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!loaded) {
            return true;
        }
        String command = cmd.getName();
        if (command.equalsIgnoreCase("aztabreload")) {
            if (!sender.hasPermission("aztectabcompleter.reload")) {
                sender.sendMessage("You don't have permission to do this.");
                return true;
            }
            loadConfig();
            sender.sendMessage("[AZTab] Config reloaded.");
            return true;
        }else if (command.equalsIgnoreCase("aztabdump")) {
            if (!sender.hasPermission("aztectabcompleter.dump")) {
                sender.sendMessage("You don't have permission to do this.");
                return true;
            }
            dumpFiltering = !dumpFiltering;
            String dumpResult = dumpFiltering? "Enabled" : "Disabled";
            sender.sendMessage("[AZTab] Console Filter Logging: "+dumpResult);
            return true;
        }
        return false;
    }
*/

    public void loadConfig(){
        saveDefaultConfig();//fails silently if config exists
        reloadConfig();
        filters.load(getConfig());
    }
    
    
    
    
    @Override
    public void onEnable() {
        super.onEnable();
        getProxy().getPluginManager().registerCommand(this, new AZTabReload());
        getProxy().getPluginManager().registerCommand(this, new AZTabDump());
        getLogger().info("Registered commands");
        getProxy().getPluginManager().registerListener(this, this);
        getLogger().info("Registered tabcompleteresponse event handler");
        filters.load(getConfig());
        getLogger().info("Loaded filters");
        loaded = true;
        ready = true;
    }
    
    private ProxiedPlayer getPlayerFromConnection(Connection conn){
        for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()){
            if(player.getAddress().equals(conn.getAddress())){
                return player;
            }
        } 
        return null;
    }
 
    
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onTabCompleteResponse(TabCompleteResponseEvent event) {
        if(dumpFiltering) getLogger().info("Filtering tabcomplete response");
        Connection recv=event.getReceiver();
        ProxiedPlayer player = getPlayerFromConnection(recv);
        if(player==null){//if we don't know who this is, we don't show them anything.
            event.setCancelled(true);
            return;
        }
        if (player.hasPermission("aztectabcompleter.bypass")) {
            if(dumpFiltering) getLogger().info(player.getName()+" bypassed filtering by permission.");
            return;
        }
        if(!ready){
            if(dumpFiltering) getLogger().info(player.getName()+" denied suggestions - plugin not ready.");
            event.getSuggestions().clear();
            return;
        }
        if (!player.hasPermission("aztectabcompleter.suggest")) {
            if(dumpFiltering) getLogger().info(player.getName()+" denied suggestions by permission.");
            event.getSuggestions().clear();
        } else {
            if(dumpFiltering) getLogger().info(player.getName()+" commands,  pre-filter: "+event.getSuggestions());
            event.getSuggestions().removeIf(entry -> !filters.filter(new FilterArgs(player, entry)).isAllowed);
            if(dumpFiltering) getLogger().info(player.getName()+" commands, post-filter: "+event.getSuggestions());
        }

    }
}
