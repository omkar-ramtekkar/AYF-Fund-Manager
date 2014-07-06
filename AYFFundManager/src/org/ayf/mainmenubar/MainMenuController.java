/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.mainmenubar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import org.ayf.command.Command;
import org.ayf.managers.ApplicationManager;
import org.ayf.toolbar.ActionItem;

/**
 *
 * @author om
 */
public class MainMenuController implements ActionListener{
    JMenuBar mainMenuBarView;

    public MainMenuController() {
        setupMenuBar();
        
        ApplicationManager.getSharedManager().getMainFrame().setJMenuBar(mainMenuBarView);
    }

    public JMenuBar getMainMenuBarView() {
        return mainMenuBarView;
    }
    
    void setupMenuBar()
    {
        mainMenuBarView = new JMenuBar();
        JMenu memberMenu = new JMenu("Actions");
        Dimension dimension = new Dimension(25, 25);
        memberMenu.add(new ActionItem("Register Member...", Command.SubCommandType.UserAdd, "user_add", dimension));
        memberMenu.add(new ActionItem("Update Member...", Command.SubCommandType.UserEdit, "user_edit", dimension));
        memberMenu.add(new ActionItem("Remove Member...", Command.SubCommandType.UserDelete, "user_remove", dimension));
        
        mainMenuBarView.add(memberMenu);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        e.setSource(e);
        ApplicationManager.getSharedManager().actionPerformed(e);
    }
}
