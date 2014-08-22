/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import com.sun.tools.corba.se.idl.InvalidArgument;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ayf.command.ReportCommand;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.SubscriptionAmountDetails;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.views.AllSubscriptionAmountsReportView;
import org.ayf.util.SubscriptionUtil;

/**
 *
 * @author om
 */
public class AllsubscriptionAmountReport extends GenericSearchReport {

    public AllsubscriptionAmountReport() {
        super(SubscriptionAmountDetails.class, 
                BaseEntity.DetailsLevel.Complete, 
                new AllSubscriptionAmountsReportView(null), 
                ReportCommand.SubCommandType.DetailsAllSubscriptionAmountDetails);
        
    }
    
    @Override
    public ReportData getData() {
        ArrayList<BaseEntity> entities = DatabaseManager.getAllEntities(getEnitityType());
        Vector<BaseEntity> rows = new Vector<BaseEntity>(entities);
        
        try {
            SubscriptionUtil util = new SubscriptionUtil(rows, true);
            rows = util.getAllSubscriptionAmounts();
        } catch (InvalidArgument ex) {
            Logger.getLogger(AllsubscriptionAmountReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ReportData(rows, getDetailLevel(), getEnitityType());
    }
    
}
