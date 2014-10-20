/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import org.ayf.command.ReportCommand;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.views.DonationStatementReportView;

/**
 *
 * @author om
 */
public class DonationStatementReport extends Report{

    public DonationStatementReport() {
        super(ReportCommand.SubCommandType.StatementsOfDonation);
        setView(new DonationStatementReportView(this));
    }

    
    @Override
    public ReportData getData() {
        DonationStatementReportView view = (DonationStatementReportView)getView();
        
        if(view == null)
            return null;
        
        return DatabaseManager.getDonationByGrouping(view.getGroupingOption(), view.getStartYear(), view.getEndYear());
    }
    
}
