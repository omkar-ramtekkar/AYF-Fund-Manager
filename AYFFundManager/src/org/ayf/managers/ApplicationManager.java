/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import org.ayf.ui.MainFrame;
import org.ayf.ui.controllers.SideBarTableController;

/**
 
 * @author om
 */
public class ApplicationManager implements MouseInputListener
{
    private MainFrame mainFrame = null;
    private SideBarTableController sidebarTableController;
    private ReportManager reportManager;
    
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
        this.sidebarTableController.getTable().addMouseListener(this);
        this.reportManager = new ReportManager();
    }

    
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public SideBarTableController getSidebarTableController() {
        return sidebarTableController;
    }

    public ReportManager getReportManager() {
        return reportManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    
}
