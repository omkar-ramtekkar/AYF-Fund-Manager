/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import org.ayf.managers.DatabaseManager;
import org.ayf.command.ReportCommand;
import org.ayf.reports.views.GenericCustomReportView;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class GenericCustomReport extends Report{

    public GenericCustomReport(ReportCommand.SubCommandType type) {
        super(type);
        setView(new GenericCustomReportView(this));
    }
    
    @Override
    public ReportData getData() {
        switch(getReportType())
        {
            case DashboardDonation:
                return DatabaseManager.getDonationByCategory();
            case DashboardExpenses:
                return DatabaseManager.getTotalExpensesInYearByType(DateTime.getCurrentFiscalYearStart(), DateTime.getCurrentFiscalYearEnd());
            default:
                return null;
        }
    }
}
