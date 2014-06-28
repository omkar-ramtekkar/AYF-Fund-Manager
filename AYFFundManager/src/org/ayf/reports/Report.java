/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import javax.swing.JPanel;
import org.ayf.models.Command;

/**
 *
 * @author om
 */
public abstract class Report {
    JPanel view;
    Command.CommandType reportType;

    public Report(Command.CommandType type) {
        this.reportType = type;
    }

    public JPanel getView() {
        return view;
    }

    public String getName() {
        return reportType.toString();
    }

    protected void setView(JPanel view) {
        this.view = view;
    }

    public Command.CommandType getReportType() {
        return reportType;
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

    abstract public void refresh();
    abstract public void generate();    
}
