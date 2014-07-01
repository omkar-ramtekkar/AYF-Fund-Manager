/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import org.ayf.managers.DatabaseManager;
import org.ayf.models.Command;
import org.ayf.reports.views.DonationReportView;

/**
 *
 * @author om
 */
public class DonationReport extends Report{

    public DonationReport() {
        super(Command.CommandType.DashboardDonation);
        
        setView(new DonationReportView(this));
    }
    
    @Override
    public ReportData getData() {
        return DatabaseManager.getDonationBySubscription();
    }
}