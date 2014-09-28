/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.print.PrintException;
import org.ayf.command.Command;
import org.ayf.managers.ApplicationManager;
import org.ayf.managers.ReportManager;
import org.ayf.command.ReportCommand;
import org.ayf.models.SideBarTableModel.Option;
import org.ayf.reports.Report;
import org.ayf.reports.print.PrintableView;
import org.ayf.reports.views.BaseReportView;
import org.ayf.reports.views.ReportViewDelegate;
import org.ayf.ui.ReportView;

/**
 *
 * @author om
 */
public class ReportViewController implements ActionListener
{
    ReportManager reportManager;
    ReportView reportView;
    
    Vector<Report> currentReports;

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

    public ReportView getReportView() {
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
        updateReportViewWithReports(getReport(type, subType));
    }
    
    private Vector<Report> getReport(Command.SubCommandType type, Command.SubCommandType subType)
    {
        Vector<Report> reports = null;
        if(type != null && (subType != null && subType != Command.SubCommandType.None))
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
        
        return reports;
    }
    
    void updateReportViewWithReports(Vector<Report> reports)
    {
        if(reports != null && !reports.isEmpty())
        {
            currentReports = new Vector<Report>(reports);
            
            cleanReportView();
            
            reportView.prepareForReports(reports.size());
            
            for (Report report : reports) 
            {
                ReportViewDelegate delegate = (ReportViewDelegate) report.getView();
                delegate.reportWillLoad();
                reportView.addView(report.getView());
                report.updateReport();
                delegate.reportDidLoad();
            }
        }
    }
    
    void cleanReportView()
    {
        Vector<BaseReportView> reportViews = this.reportView.getReportViews();
        
        for (BaseReportView baseReportView : reportViews) {
            ReportViewDelegate delegate = (ReportViewDelegate) baseReportView;
            if(delegate != null)
            {
                delegate.reportWillUnLoad();
            }
        }
        
        this.reportView.cleanView();
        
        for (BaseReportView baseReportView : reportViews) {
            ReportViewDelegate delegate = (ReportViewDelegate) baseReportView;
            if(delegate != null)
            {
                delegate.reportDidUnLoad();
            }
        }
    }
    
    public void refresh() {
        updateReportViewWithReports(currentReports);
    }
    
    public Vector<PrintableView> getPrintableReports() throws PrintException {
        if(this.currentReports == null || this.currentReports.isEmpty()) throw new PrintException("No reports to print.");
        
        Vector<PrintableView> printables = new Vector<PrintableView>(this.currentReports.size());
        
        for (Report report : this.currentReports) {
            printables.add(report.getPrintableView());
        }
        
        return printables;
    }
}
