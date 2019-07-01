/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.bungeetabfilter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
abstract public class BasicPlugin extends Plugin implements Listener {

    Configuration config = null;
    Configuration defaultConfig = null;

    public Configuration getDefaultConfig(){
        return defaultConfig;
    }
    public Configuration getConfig() {
        return config;
    }
    public Configuration reloadConfig(){
        try{
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        }catch(Exception e){
            config = null;
        }
        return config;
    }
    

    public void saveDefaultConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
                defaultConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    @Override
    public void onLoad(){
        saveDefaultConfig();
    }
    
    @Override
    public void onEnable(){
        reloadConfig();
    }
}
