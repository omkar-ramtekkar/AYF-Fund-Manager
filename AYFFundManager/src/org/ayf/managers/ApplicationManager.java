/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import javax.swing.JOptionPane;
import org.ayf.command.Command;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Donor;
import org.ayf.database.entities.Member;
import org.ayf.mainmenubar.MainMenuController;
import org.ayf.reports.GenericSearchReport;
import org.ayf.reports.ReportData;
import org.ayf.reports.print.BasicPrintable;
import org.ayf.reports.print.PrintingDialog;
import org.ayf.reports.print.ReportPrintable;
import org.ayf.reports.views.BaseReportView;
import org.ayf.reports.views.GenericSearchDialog;
import org.ayf.reports.views.ReportDataProcessor;
import org.ayf.toolbar.ToolbarController;
import org.ayf.ui.InformationPanel;
import org.ayf.ui.MainFrame;
import org.ayf.ui.MemberFrame;
import org.ayf.ui.controllers.ReportViewController;
import org.ayf.ui.controllers.SettingsViewController;
import org.ayf.ui.controllers.SideBarTableController;
import org.ayf.util.PreferenceManager;
import org.ayf.util.Toast;

/**
 
 * @author om
 */
public class ApplicationManager implements ActionListener, DatabaseUpdateListener
{
    private MainFrame mainFrame = null;
    private SideBarTableController sidebarTableController;
    private ReportViewController reportController;
    private ToolbarController   toolbarController;
    private MainMenuController  mainMenuBarController;
    private SettingsViewController settingViewController;
    
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
        DatabaseManager.loadDatabaseClass();
        DatabaseManager.addDatabaseUpdateListner(this);
        
        this.mainFrame = new MainFrame();
        this.settingViewController = new SettingsViewController();
        this.toolbarController = new ToolbarController();        
        this.mainMenuBarController = new MainMenuController();

        //Configure sidebar table
        this.sidebarTableController = new SideBarTableController();

        File databaseFile = new File(PreferenceManager.getDatabaseDir());
        
        checkAndUpdateDatabaseLocation();
        
        if(!databaseFile.isDirectory() && databaseFile.exists())
        {
            DatabaseManager.initializeDatabaseManager();
        }

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
                    getReportController().actionPerformed(e);
                    break;
                case Toolbar:
                    handleAction(command);
                    break;
                case Menubar: 
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
                case Home:
                    sidebarTableController.selectOption(Command.SubCommandType.Dashboard, null);
                    break;
                case AdminPassword:
                    handleAdminSettings();
                    break;
                case PrintReport:
                    handlePrint();
            }
        }
    }

    private void handleUserAddAction() 
    {
        new MemberFrame(null, InformationPanel.PanelType.Registeration).setVisible(true);
    }

    private void handleUserDeleteAction() 
    {
        ReportDataProcessor processor = new ReportDataProcessor() {

            @Override
            public void processSelectedData(ReportData data, BaseReportView reportView) {
                if(data.getEntities() != null && !data.getEntities().isEmpty())
                {
                    Member member = (Member) data.getEntities().get(0);

                    BaseEntity.ActiveStatus currentActuveStatus = member.getCurrentStatus();

                    if(currentActuveStatus != BaseEntity.ActiveStatus.Active)
                    {
                        JOptionPane.showMessageDialog(getMainFrame(), "Member is not active. Select other active member.");
                        return;
                    }
                    
                    String message = "Are you sure, you want to Deactivate member?";
                    
                    int userChoice = JOptionPane.showConfirmDialog(getMainFrame(), message, "Deactivate Member", JOptionPane.YES_NO_OPTION);
                    
                    if(userChoice == JOptionPane.YES_OPTION)
                    {
                        member.setCurrentStatus(BaseEntity.ActiveStatus.Inactive);
                        
                        Vector<BaseEntity> entity = new Vector<BaseEntity>(1);
                        entity.add(member);

                        boolean entityUpdated = DatabaseManager.updateEntities(entity, Member.class);
                        
                        if(entityUpdated)
                            Toast.showToastOnComponentCenter(reportView, "Member deactivated successfully.", true);
                        else
                            Toast.showToastOnComponentCenter(reportView, "Failed to deactivated member.", false);
                    }
                    
                }
            }
        };
                
        GenericSearchDialog dialog = new GenericSearchDialog(new GenericSearchReport(Member.class, BaseEntity.DetailsLevel.Database), processor, getMainFrame(), true);
        dialog.setTitle("Deactivate Member Search Dialog");
        dialog.setVisible(true);
    }

    private void handleUserSearchAction() {
        ReportDataProcessor processor = new ReportDataProcessor() {

            @Override
            public void processSelectedData(ReportData data, BaseReportView reportView) {
                if(data.getEntities() != null && !data.getEntities().isEmpty())
                {
                    MemberFrame editMember = new MemberFrame((Member)data.getEntities().get(0), InformationPanel.PanelType.View);
                    editMember.setVisible(true);
                }
            }
        };
                
        GenericSearchDialog dialog = new GenericSearchDialog(new GenericSearchReport(Member.class, BaseEntity.DetailsLevel.Database), processor, getMainFrame(), true);
        dialog.setTitle("Edit Member Search Dialog");
        dialog.setVisible(true);
    }

    private void handleUserEditAction() {
        ReportDataProcessor processor = new ReportDataProcessor() {

            @Override
            public void processSelectedData(ReportData data, BaseReportView reportView) {
                if(data.getEntities() != null && !data.getEntities().isEmpty())
                {
                    MemberFrame editMember = new MemberFrame((Member)data.getEntities().get(0), InformationPanel.PanelType.Update);
                    editMember.setVisible(true);
                }
            }
        };
                
        GenericSearchDialog dialog = new GenericSearchDialog(new GenericSearchReport(Member.class, BaseEntity.DetailsLevel.Database), processor, getMainFrame(), true);
        dialog.setTitle("Edit Member Search Dialog");
        dialog.setVisible(true);
    }

    private void handleSettingsAction() {
        this.settingViewController.showSettingsView();
    }

    private void handleDonateAction() {
        new MemberFrame(null, InformationPanel.PanelType.Donate).setVisible(true);
    }

    private void checkAndUpdateDatabaseLocation()
    {
        try {
            File databaseFile = new File(PreferenceManager.getDatabaseDir());
            if (databaseFile.isDirectory() || !databaseFile.exists()) 
            {
                throw new FileNotFoundException("Database file not found");
            }
        } catch (FileNotFoundException exception) 
        {
            Point centerPoint = new Point((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.0), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.0));
            Toast.showToast("Invalid Database directory path", centerPoint, false);
            this.settingViewController.showSettingsView();
        }
    }
        
    public void databasePathDidChange(String oldPath, String newPath)
    {
        DatabaseManager.initializeDatabaseManager();
        this.reportController.refresh();
    }

    void showSettingsPanel() {
        this.settingViewController.showSettingsView();
    }

    public void imagePathDidChange(String oldPath, String newPath) {
        
    }

    private void handleAdminSettings() {
        
    }
    
    private void memberDidRegister(Member member)
    {
        PreferenceManager.updateNextRegID();
    }
    
    private void donationDidPerform(Donor donor)
    {
        PreferenceManager.updateNextDonationID();
    }
    
    private void handlePrint() 
    {
        try {
            Vector<BasicPrintable> printableReports = reportController.getPrintableReports();
            PrintingDialog printdialog = new PrintingDialog(printableReports, mainFrame, true);
            printdialog.setLocationRelativeTo(null);
            printdialog.setVisible(true);
        } catch (PrintException ex) {
            Logger.getLogger(ApplicationManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void entityDidAdded(BaseEntity entity) 
    {
        if(entity != null)
        {
            if(entity.getClass().equals(Donor.class))
            {
                donationDidPerform((Donor) entity);
            }
            else if(entity.getClass().equals(Member.class))
            {
                memberDidRegister((Member) entity);
            }
        }
    }
    
    @Override
    public void entityDidUpdated(BaseEntity entity) {
    }

    @Override
    public void entityDidRemoved(BaseEntity entity) {
    }

    @Override
    public void entityDidRead(BaseEntity entity) {
    }

    @Override
    public void entitiesDidRead(ArrayList<BaseEntity> entities) {
    }

}
