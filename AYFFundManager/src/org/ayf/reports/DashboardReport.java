/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import org.ayf.command.Command;
import org.ayf.ui.DashboardReportView;

/**
 *
 * @author om
 */
public class DashboardReport extends Report{

    public DashboardReport() {
        super(Command.SubCommandType.DashboardReport);
        setView(new DashboardReportView(this));
    }

    
    
    @Override
    public ReportData getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
