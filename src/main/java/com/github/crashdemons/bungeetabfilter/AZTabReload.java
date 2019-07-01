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
public class AZTabReload extends Command{
  public AZTabReload() {
      super("aztabreload");
  }
  @Override
  public void execute(CommandSender commandSender, String[] strings) {
      if(commandSender.hasPermission("aztectabcompleter.reload")){ commandSender.sendMessage(new TextComponent(ChatColor.RED + "You don't have permission to run this command.")); return; }
      BTFPlugin.instance.loadConfig();
      commandSender.sendMessage(new TextComponent(ChatColor.GREEN + "[AZTab] Config reloaded."));
  }
}
