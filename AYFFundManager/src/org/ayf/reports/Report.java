/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import javax.swing.SwingUtilities;
import org.ayf.command.ReportCommand;
import org.ayf.reports.views.BaseReportView;

/**
 *
 * @author om
 */
public abstract class Report {
    BaseReportView view;
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
    
    public abstract ReportData getData();
    
    public void updateReport()
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                ReportData data = getData();
                BaseReportView view = getView();
                
                if(view != null)
                {
                    view.updateView(data);
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
    
    @Override
    public String toString() {
        return "Report{" + "reportType=" + reportType + '}';
    }
}
