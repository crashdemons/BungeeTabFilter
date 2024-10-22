/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.aztectabcompleter.filters;

import com.github.crashdemons.util.Pair;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Defines a set of arguments passed to filters for testing.
 * Contains the command suggestion to be filtered and the player who receives the suggestion.
 * (Semantic-sugar for Pair syntax)
 * @author crash
 */
public class FilterArgs extends Pair<ProxiedPlayer,String> {
    /**
     * Create a new pair of arguments for filters with null values.
     */
    public FilterArgs(){
        super(null,null);
    }
    /**
     * Create a new pair of arguments for filters
     * @param destination the player who is the destination of filtering outcome
     * @param command the command name (suggestion) being filtered
     */
    public FilterArgs(ProxiedPlayer destination, String command){
        super(destination,command);
    }
    /**
     * Gets the player the filtering is being performed for.
     * @return the player the filtering is being performed for. 
     */
    public ProxiedPlayer getDestination(){ return getKey(); }
    /**
     * Gets the command name (suggestion) being filtered.
     * @return the command name.
     */
    public String getCommand(){ return getValue(); }
}
