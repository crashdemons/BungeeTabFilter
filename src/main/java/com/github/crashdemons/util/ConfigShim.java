/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.util;

import net.md_5.bungee.config.Configuration;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class ConfigShim {
    public Configuration config=null;
    public ConfigShim(Configuration config){this.config=config;}
    
    
    public boolean contains(String key, boolean unused){
        return config.getKeys().contains(key);
    }
}
