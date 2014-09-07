/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.ayf.command.ReportCommand;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Donor;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.views.SubscriptionAmountPendingReportView;
import org.ayf.util.DateTime;
import org.ayf.util.SubscriptionUtil;

/**
 *
 * @author om
 */
public class SubscriptionAmountPendingReport extends Report {

    boolean showInactiveMembers = false;
    
    public SubscriptionAmountPendingReport() {
        super(ReportCommand.SubCommandType.NotificationsMemberSubscriptionPending, null);
        setView(new SubscriptionAmountPendingReportView(this));
    }

    public boolean isShowInactiveMembers() {
        return showInactiveMembers;
    }

    public void setShowInactiveMembers(boolean showInactiveMembers) {
        this.showInactiveMembers = showInactiveMembers;
        updateReport();
    }

    
    
    @Override
    public ReportData getData() {
        SubscriptionUtil util = new SubscriptionUtil(true);
        
        ArrayList<BaseEntity> entities = DatabaseManager.getAllEntities(Member.class);
        Map<BaseEntity, Float> pendingSubscription = new HashMap<BaseEntity, Float>();
        
        for (BaseEntity baseEntity : entities)
        {
            if(isShowInactiveMembers())
            {
                if(((Member)baseEntity).isActive()) continue;
            }
            else
            {
                if(!((Member)baseEntity).isActive()) continue;
            }
            
            String regID = baseEntity.getValueForField(BaseEntity.ColumnName.UniqueID).toString();
            java.sql.Date regDate = (java.sql.Date)baseEntity.getValueForField(BaseEntity.ColumnName.RegisterationDate);
            
            float totalSubscriptionToBePaid = 0;
            try
            {
                totalSubscriptionToBePaid = util.getSubscriptionAmountToBePaidBetweenDates(regDate, DateTime.getTodaySQL());
                
            }catch(IllegalArgumentException ex){}
            
            float totalsubscriptionPaid = DatabaseManager.getTotalDonationPaidByMember(regID, Donor.SUBSCRIPTION_TYPE);
            
            float pendingSubscriptionAmount = totalSubscriptionToBePaid - totalsubscriptionPaid;
            if(pendingSubscriptionAmount > 0)
            {
                pendingSubscription.put(baseEntity, pendingSubscriptionAmount);
            }
        }
        
        Vector<Object> columnNames = new Vector<Object>(10);
        
        columnNames.add(entities.get(0).getNameForColumnID(BaseEntity.ColumnName.UniqueID));
        columnNames.add("Pending Amount");
        columnNames.add(entities.get(0).getNameForColumnID(BaseEntity.ColumnName.RegisterationDate));
        columnNames.add(entities.get(0).getNameForColumnID(BaseEntity.ColumnName.FirstName));
        columnNames.add(entities.get(0).getNameForColumnID(BaseEntity.ColumnName.MiddleName));
        columnNames.add(entities.get(0).getNameForColumnID(BaseEntity.ColumnName.LastName));
        columnNames.add(entities.get(0).getNameForColumnID(BaseEntity.ColumnName.Gender));
        columnNames.add(entities.get(0).getNameForColumnID(BaseEntity.ColumnName.ContactNumber));
        columnNames.add(entities.get(0).getNameForColumnID(BaseEntity.ColumnName.Status));
        
        Vector rows = new Vector(pendingSubscription.size());
        
        for (BaseEntity baseEntity : pendingSubscription.keySet()) 
        {
            Vector rowValues = new Vector(10);
            rowValues.add(baseEntity.getValueForField(BaseEntity.ColumnName.UniqueID));
            rowValues.add(pendingSubscription.get(baseEntity));
            rowValues.add(baseEntity.getValueForField(BaseEntity.ColumnName.RegisterationDate));
            rowValues.add(baseEntity.getValueForField(BaseEntity.ColumnName.FirstName));
            rowValues.add(baseEntity.getValueForField(BaseEntity.ColumnName.MiddleName));
            rowValues.add(baseEntity.getValueForField(BaseEntity.ColumnName.LastName));
            rowValues.add(baseEntity.getValueForField(BaseEntity.ColumnName.Gender));
            rowValues.add(baseEntity.getValueForField(BaseEntity.ColumnName.ContactNumber));
            rowValues.add(baseEntity.getValueForField(BaseEntity.ColumnName.Status));
            
            rows.add(rowValues);
        }
        
        return new ReportData(rows, columnNames);
    }
    
}
