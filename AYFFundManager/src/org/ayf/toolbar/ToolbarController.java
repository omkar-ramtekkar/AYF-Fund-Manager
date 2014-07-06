/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToolBar;
import org.ayf.command.Command;
import org.ayf.command.ToolbarCommand;

/**
 *
 * @author om
 */
public class ToolbarController implements ActionListener{
    
    JToolBar toolbarView;
    
    public ToolbarController() {
        setupToolbar();
    }
    
    protected void setupToolbar()
    {
        toolbarView = new JToolBar("MainToolbar");
        
        toolbarView.add(new ActionItem("Add Member", ToolbarCommand.SubCommandType.UserAdd, "user_add"));
        toolbarView.add(new ActionItem("Remove Member", ToolbarCommand.SubCommandType.UserDelete, "user_remove"));
        toolbarView.add(new ActionItem("Edit Member Info", ToolbarCommand.SubCommandType.UserEdit, "user_edit"));
        toolbarView.add(new ActionItem("Search Member", ToolbarCommand.SubCommandType.UserSearch, "user_search"));
        toolbarView.addSeparator();
        toolbarView.add(new ActionItem("Donate", Command.SubCommandType.Donate, "donate"));
        toolbarView.addSeparator();
        toolbarView.add(new ActionItem("Settings", ToolbarCommand.SubCommandType.Settings, "settings"));
        
        toolbarView.setRollover(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public JToolBar getToolbarView() {
        return toolbarView;
    }
}
