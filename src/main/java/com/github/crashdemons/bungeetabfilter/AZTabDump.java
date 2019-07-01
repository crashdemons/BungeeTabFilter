/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.bungeetabfilter;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class AZTabDump extends Command {

    public AZTabDump() {
        super("aztabdump");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission("aztectabcompleter.dump")) {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "You don't have permission to run this command."));
            return;
        }

        BTFPlugin.instance.dumpFiltering = !BTFPlugin.instance.dumpFiltering;
        String dumpResult = BTFPlugin.instance.dumpFiltering ? "Enabled" : "Disabled";
        commandSender.sendMessage(new TextComponent(ChatColor.GOLD + "[AZTab] Console Filter Logging: " + dumpResult));
    }
}
