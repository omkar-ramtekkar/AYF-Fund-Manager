/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import javax.swing.SwingUtilities;
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
    
    private void initializeReport()
    {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                ReportData data = DatabaseManager.getDonationBySubscription();
                DonationReportView view = getView() instanceof DonationReportView ? (DonationReportView)getView() : null;
                
                if(view != null)
                {
                    view.setData(data);
                }
            }
        });
    }
    
    
    @Override
    public void refresh() {
        initializeReport();
    }

    @Override
    public void generate() {
        initializeReport();
    }
}
