/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import org.ayf.database.manager.DatabaseManager;
import org.ayf.database.model.Donor;
import org.ayf.database.model.Member;
import org.ayf.ui.MainFrame;
import org.ayf.ui.controllers.SideBarTableController;

/**
 *
 * @author om
 */
public class ApplicationManager {
    private JFrame mainFrame = null;
    private final SideBarTableController sidebarTableController;
    private ReportManager reportManager;

    public ApplicationManager() 
    {
        this.mainFrame = new MainFrame();
        ArrayList<Donor> donors = DatabaseManager.getDonors();
        Donor donor = donors.get(0);
        
        DatabaseManager.performDonate(donor);

        this.sidebarTableController = new SideBarTableController();
        JScrollPane scrollPane = new JScrollPane(this.sidebarTableController.getTable());
        scrollPane.setBounds(0, 0, 200, this.mainFrame.getHeight());
        this.sidebarTableController.getTable().setBounds(0, 0, 200, this.mainFrame.getHeight());
        this.mainFrame.add(scrollPane);
    }

    
    public JFrame getMainFrame() {
        return mainFrame;
    }

    public SideBarTableController getSidebarTableController() {
        return sidebarTableController;
    }

    public ReportManager getReportManager() {
        return reportManager;
    }
    
    
}
