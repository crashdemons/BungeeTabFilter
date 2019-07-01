/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.util;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ConnectedPlayer;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class BungeeInfo {
    private BungeeInfo(){}
    
    public static boolean hasPermission(CommandSender sender, String perm){
        if(isConsole(sender) || sender.hasPermission(perm)){
            return true;
        }else return false;
    }
    
    public static boolean isConsole(CommandSender sender){
        if(sender instanceof ProxiedPlayer) return false;
        if(sender instanceof ConnectedPlayer) return false;
        if(sender instanceof Connection) return false;
        return true;
    }
}
