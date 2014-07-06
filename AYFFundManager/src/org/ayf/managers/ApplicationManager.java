/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import org.ayf.command.Command;
import org.ayf.command.ReportCommand;
import org.ayf.command.ToolbarCommand;
import org.ayf.mainmenubar.MainMenuController;
import org.ayf.models.SideBarTableModel.Option;
import org.ayf.toolbar.ToolbarController;
import org.ayf.ui.InformationPanel;
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
    private ToolbarController   toolbarController;
    private MainMenuController  mainMenuBarController;
    
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
        
        this.toolbarController = new ToolbarController();
        this.mainFrame.add(this.toolbarController.getToolbarView(), BorderLayout.NORTH);
        
        mainMenuBarController = new MainMenuController();
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

    public ToolbarController getToolbarController() {
        return toolbarController;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {  
        Command command = (Command)e.getSource();
        if(command != null)
        {
            switch(command.getType())
            {
                case Report:
                {
                    ReportCommand reportCommand = (ReportCommand)command;
                    Option categoryOption = reportCommand.getOption().getParentOption();

                    if(categoryOption == null)
                    {
                        categoryOption = reportCommand.getOption();
                    }

                    getReportController().actionPerformed(e);
                }
                    break;
                case Toolbar:
                {
                    ToolbarCommand toolbarCommand = (ToolbarCommand) command;
                    handleAction(toolbarCommand);
                }
                case Nenubar: 
                    handleAction(command);
                    break;
            }
        }
    }
    
    void handleAction(Command command)
    {
        if(command != null)
        {
            switch(command.getSubType())
            {
                case UserAdd:
                    handleUserAddAction();
                    break;
                case UserDelete:
                    handleUserDeleteAction();
                    break;
                case UserSearch:
                    handleUserSearchAction();
                    break;
                case UserInfo:
                    break;
                case UserEdit:
                    handleUserEditAction();
                    break;
                case Settings:
                    handleSettingsAction();
                    break;
                case Save:
                    break;
                case Donate:
                    handleDonateAction();
                    break;
            }
        }
    }

    private void handleUserAddAction() {
        JFrame addMemberFrame = new JFrame();
        addMemberFrame.setLayout(new BorderLayout());
        
        InformationPanel panel = new InformationPanel();
        addMemberFrame.add(panel, BorderLayout.CENTER);
        
        addMemberFrame.setVisible(true);
    }

    private void handleUserDeleteAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleUserSearchAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleUserEditAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleSettingsAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleDonateAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
