/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.ArrayList;
import java.util.Vector;
import org.ayf.database.entities.Donor;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.command.ReportCommand;
import org.ayf.database.entities.BaseEntity;
import org.ayf.reports.views.AllDonationsReportView;

/**
 *
 * @author om
 */
public class AllDonationsReport extends Report{

    public AllDonationsReport() {
        super(ReportCommand.SubCommandType.DetailsAllDonors);
        
        setView(new AllDonationsReportView(this));
    }

    @Override
    public ReportData getData() {
        ArrayList<BaseEntity> donors = DatabaseManager.getAllEntities(Donor.class);
        
        Vector<BaseEntity> rows = new Vector<BaseEntity>(donors);
        
        return new ReportData(rows, BaseEntity.DetailsLevel.OnlyIDAndName, Donor.class);
    }
    
}
