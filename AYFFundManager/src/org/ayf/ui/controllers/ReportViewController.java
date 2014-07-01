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
import org.ayf.managers.ApplicationManager;
import org.ayf.managers.ReportManager;
import org.ayf.models.Command;
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
        
        updateReportViewWithReports(reportManager.getReports(Command.CommandType.Dashboard));
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
        Command command = (Command)e.getSource();
        
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
            reports = reportManager.getReports(command.getType());
        }
        
        updateReportViewWithReports(reports);
    }
    
    void updateReportViewWithReports(Vector<Report> reports)
    {
        cleanReportView();
        if(reports != null)
        {
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
