/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui.controllers;

import org.ayf.managers.ApplicationManager;
import org.ayf.ui.SettingsView;

/**
 *
 * @author om
 */
public class SettingsViewController {
    
    SettingsView settingsView;

    public SettingsViewController() 
    {
        settingsView = new SettingsView();
    }
    
    void initializeView()
    {
        settingsView.initializeSettingsData();
    }
    
    public void showSettingsView()
    {
        this.settingsView.initializeSettingsData();
        this.settingsView.setVisible(true);
        this.settingsView.toFront();
    }
    
    void databasePathDidChange(String oldPath, String newPath)
    {
        ApplicationManager.getSharedManager().databasePathDidChange(oldPath, newPath);
    }
    
    void imagePathDidChange(String oldPath, String newPath)
    {
        ApplicationManager.getSharedManager().imagePathDidChange(oldPath, newPath);
    }
    
}
