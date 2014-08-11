/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.mainmenubar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
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
        
        JMenu fileMenu = new JMenu("File");
        Dimension dimension = new Dimension(30, 30);

        fileMenu.add(new ActionItem("Change Database...", Command.SubCommandType.ChangeDatabase, "change_database", dimension));
        fileMenu.add(new ActionItem("Change Image Folder...", Command.SubCommandType.ChangeDatabase, "change_image_folder", dimension));        
        fileMenu.addSeparator();
        
        fileMenu.add(new ActionItem("Print Report...", Command.SubCommandType.PrintReport, "printer", dimension));
        fileMenu.addSeparator();
        
        JMenu exportMenu = new JMenu(new ActionItem("Export As", Command.SubCommandType.None, "export_as", dimension));
        exportMenu.add(new ActionItem("Excel File...", Command.SubCommandType.ExportAsExcel, "export_excel", dimension));
        exportMenu.add(new ActionItem("CSV File...", Command.SubCommandType.ExportAsCSV, "export_csv", dimension));
        exportMenu.add(new ActionItem("PDF File...", Command.SubCommandType.ExportAsPDF, "export_pdf", dimension));
        
        fileMenu.add(exportMenu);
        
        JMenu memberMenu = new JMenu("Actions");
        memberMenu.add(new ActionItem("Register Member...", Command.SubCommandType.UserAdd, "user_add", dimension));
        memberMenu.add(new ActionItem("Update Member...", Command.SubCommandType.UserEdit, "user_edit", dimension));
        memberMenu.add(new ActionItem("Remove Member...", Command.SubCommandType.UserDelete, "user_remove", dimension));
        
        JMenu tools = new JMenu("Tools");
        tools.add(new ActionItem("Settings...", Command.SubCommandType.Settings, "settings", dimension));
        tools.add(new ActionItem("Change Password...", Command.SubCommandType.AdminPassword, "settings", dimension));
        
        mainMenuBarView.add(fileMenu);
        mainMenuBarView.add(new JLabel());
        mainMenuBarView.add(memberMenu);
        mainMenuBarView.add(tools);
        
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        e.setSource(e);
        ApplicationManager.getSharedManager().actionPerformed(e);
    }
}
