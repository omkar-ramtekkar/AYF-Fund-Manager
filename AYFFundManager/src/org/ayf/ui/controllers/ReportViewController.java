/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JPanel;
import org.ayf.command.Command;
import org.ayf.managers.ApplicationManager;
import org.ayf.managers.ReportManager;
import org.ayf.command.ReportCommand;
import org.ayf.models.SideBarTableModel.Option;
import org.ayf.reports.Report;
import org.ayf.ui.ReportView;

/**
 *
 * @author om
 */
public class ReportViewController implements ActionListener, MouseListener
{
    ReportManager reportManager;
    ReportView reportView;

    public ReportViewController() {
        initialize();
    }
    
    void initialize()
    {
        reportView = new ReportView();
        reportManager = new ReportManager();
        
        ApplicationManager.getSharedManager().getMainFrame().setRightView(reportView);
        
        updateReportViewWithReports(reportManager.getReports(ReportCommand.SubCommandType.Dashboard));
    }
    

    public ReportManager getReportManager() {
        return reportManager;
    }

    public JPanel getReportView() {
        return reportView;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        ReportCommand command = (ReportCommand)e.getSource();
        
        Option parentOption = command.getOption().getParentOption();
        Vector<Report> reports = new Vector<Report>();
        if(parentOption != null)
        {
            Report report = reportManager.getReportsForType(parentOption.getOptionType(), command.getOption().getOptionType());
            if(report != null)
            {
                reports.add(report);
            }
        }
        else
        {
            if(!command.getOption().hasSubOptions())
            {
                reports = reportManager.getReports(command.getSubType());
            }
        }
        
        updateReportViewWithReports(reports);
    }
    
    public void openReport(Command.SubCommandType type, Command.SubCommandType subType)
    {
        Vector<Report> reports = null;
        if(type != null && subType != null)
        {
            reports = new Vector<Report>();
            Report report = reportManager.getReportsForType(type, subType);
            if(report != null)
            {
                reports.add(report);
            }
        }
        else if(type != null)
        {
            reports = reportManager.getReports(type);
        }
        
        updateReportViewWithReports(reports);
    }
    
    void updateReportViewWithReports(Vector<Report> reports)
    {
        if(reports != null && !reports.isEmpty())
        {
            cleanReportView();
            
            for (Report report : reports) 
            {
                reportView.addView(report.getView());
                report.updateReport();
            }
        }
    }
    
    void cleanReportView()
    {
        this.reportView.cleanView();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
