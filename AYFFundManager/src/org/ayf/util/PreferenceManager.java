/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.util.prefs.Preferences;

/**
 *
 * @author oramtekkar
 */
public class PreferenceManager {
    
    private static PreferenceManager manager = null;
    private Preferences pref;
    
    public static PreferenceManager getIntance()
    {
        
        if(manager == null)
        {
            manager = new PreferenceManager();
            manager.setPref(Preferences.userNodeForPackage(manager.getClass()));
        }
        
        return manager;
    }
    
    private void setPref(Preferences preferences)
    {
        this.pref = preferences;
    }
    
    private Preferences getPref()
    {
        return this.pref;
    }
    
    public String getString(String key, String defaultValue)
    {
        return getPref().get(key, defaultValue);
    }
    
    public void setString(String key, String value)
    {
        getPref().put(key, value);
    }
}
