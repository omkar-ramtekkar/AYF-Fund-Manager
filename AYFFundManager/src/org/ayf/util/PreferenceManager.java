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
    
    public static final String DATABASE_PATH = "DatabasePath";
    public static final String IMAGE_PATH = "imagePath";
    public static final String NEXT_REG_ID = "nextRegisterationID";
    public static final String NEXT_DONATION_ID = "nextDonationID";
    
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
    
    public static String getNextRegID()
    {
        return getIntance().getString(NEXT_REG_ID, "1");
    }
    
    public static String getNextDonationID()
    {
        return getIntance().getString(NEXT_DONATION_ID, "1");
    }
    
    public static String getDatabaseDir()
    {
        return getIntance().getString(DATABASE_PATH, "");
    }
    
    public static void setDatabaseDir(String dir)
    {
        getIntance().setString(DATABASE_PATH, dir);
    }
    
    public static String getImageDir()
    {
        return getIntance().getString(IMAGE_PATH, "");
    }
    
    public static void setImageDir(String dir)
    {
        getIntance().setString(IMAGE_PATH, dir);
    }
    
    public static void setNextRegID(String id) 
    {
        getIntance().setString(NEXT_REG_ID, id);
    }

    public static void setNextDonationID(String id) 
    {
        getIntance().setString(NEXT_DONATION_ID, id);
    }
    
    
    public static void updateNextRegID()
    {
        String nextRegID = PreferenceManager.getNextRegID();
        int regNumber = Integer.parseInt(nextRegID);
        PreferenceManager.getIntance().setString(NEXT_REG_ID, Integer.toString(++regNumber));
    }
    
    public static void updateNextDonationID()
    {
        String nextID = PreferenceManager.getNextDonationID();
        int donationNumber = Integer.parseInt(nextID);
        PreferenceManager.getIntance().setString(NEXT_DONATION_ID, Integer.toString(++donationNumber));
    }
}
