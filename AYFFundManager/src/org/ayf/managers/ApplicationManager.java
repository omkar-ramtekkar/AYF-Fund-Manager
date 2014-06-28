/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.ayf.models.Command;
import org.ayf.models.SideBarTableModel.Option;
import org.ayf.ui.MainFrame;
import org.ayf.ui.controllers.ReportViewController;
import org.ayf.ui.controllers.SideBarTableController;

/**
 
 * @author om
 */
public class ApplicationManager implements ActionListener
{
    private MainFrame mainFrame = null;
    private SideBarTableController sidebarTableController;
    private ReportViewController reportController;
    
    private static ApplicationManager instance = null;

    public static ApplicationManager getSharedManager()
    {
        if(instance == null)
        {
            instance = new ApplicationManager();
        }
        
        return instance;
    }
    
    
    private ApplicationManager() 
    {
    }
    
    public void initialize()
    {
        this.mainFrame = new MainFrame();

        //Configure sidebar table
        this.sidebarTableController = new SideBarTableController();
        this.reportController = new ReportViewController();
        this.sidebarTableController.addActionListener(this);
    }

    
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public SideBarTableController getSidebarTableController() {
        return sidebarTableController;
    }

    public ReportViewController getReportController() {
        return reportController;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Command command = (Command)e.getSource();
        Option categoryOption = command.getOption().getParentOption();
        
        if(categoryOption == null)
        {
            categoryOption = command.getOption();
        }
        
        getReportController().actionPerformed(e);
    }
}
