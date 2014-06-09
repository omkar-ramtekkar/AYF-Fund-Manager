/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import org.ayf.ui.MainFrame;
import org.ayf.ui.controllers.SideBarTableController;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author om
 */
public class ApplicationManager {
    private JFrame mainFrame;
    private SideBarTableController sidebarTableController;
    private ReportManager reportManager;

    public ApplicationManager() 
    {
        this.mainFrame = new MainFrame();
        
        this.sidebarTableController = new SideBarTableController();
        JScrollPane scrollPane = new JScrollPane(this.sidebarTableController.getTable());
        scrollPane.setBounds(0, 0, 200, mainFrame.getHeight());
        this.sidebarTableController.getTable().setBounds(0, 0, 200, mainFrame.getHeight());
        mainFrame.add(scrollPane);
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
