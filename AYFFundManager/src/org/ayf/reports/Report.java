/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import javax.print.PrintException;
import javax.swing.SwingUtilities;
import org.ayf.command.Command;
import org.ayf.command.ReportCommand;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.print.PrintableView;
import org.ayf.reports.views.BaseReportView;

/**
 *
 * @author om
 */
public abstract class Report {
    BaseReportView view;
    ReportCommand.SubCommandType configurationType = ReportCommand.SubCommandType.None;
    
    ReportCommand.SubCommandType reportType;

    public Report(ReportCommand.SubCommandType type, BaseReportView view) {
        this.reportType = type;
        setView(view);
    }

    public Report(ReportCommand.SubCommandType reportType) {
        this.reportType = reportType;
    }
    

    public BaseReportView getView() {
        return view;
    }

    public String getName() {
        return reportType.toString();
    }

    protected void setView(BaseReportView view) {
        this.view = view;
    }

    public ReportCommand.SubCommandType getReportType() {
        return reportType;
    }

    public Command.SubCommandType getConfigurationType() {
        return configurationType;
    }

    protected void setConfigurationType(Command.SubCommandType configurationType) {
        this.configurationType = configurationType;
    }
    
    public void configure(ReportCommand.SubCommandType type)
    { 
        setConfigurationType(configurationType); 
        getView().configure(type);
    }
    
    
    public abstract ReportData getData();
    
    
    public void updateReport()
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                final ReportData data = getData();
                final BaseReportView view = getView();
                
                if(view != null)
                {
                    view.updateReportView(data);
                }
            }
        });
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.view != null ? this.view.hashCode() : 0);
        hash = 31 * hash + (this.reportType != null ? this.reportType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        boolean isReportObject = obj instanceof Report;
        if (!isReportObject)
        {
            return false;
        }
        
        final Report other = (Report) obj;
        if (this.reportType != other.reportType) {
            return false;
        }
        
        return true;
    }
    
    public boolean saveReportDataToDatabase(ReportData data)
    {
        if(data != null && data.getEntities() != null)
        {
            if(DatabaseManager.updateEntities(data.getEntities(), data.getDummyEntity().getClass()))
            {
                updateReport();
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return "Report{" + "reportType=" + reportType + '}';
    }
    
    public PrintableView getPrintableView() throws PrintException 
    {
       return getView().getPrintableView();
    }
}
