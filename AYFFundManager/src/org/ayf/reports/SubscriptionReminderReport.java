/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import org.ayf.command.ReportCommand;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.views.SubscriptionReminderReportView;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class SubscriptionReminderReport extends Report{

    java.sql.Date fromDate, toDate;
    
    public SubscriptionReminderReport() 
    {
        super(ReportCommand.SubCommandType.NotificationsMemberSubscription);
        setView(new SubscriptionReminderReportView(this));
    }

    public java.sql.Date getFromDate() {
        return fromDate;
    }

    public java.sql.Date getToDate() {
        return toDate;
    }

    private void setFromDate(java.sql.Date fromDate) {
        this.fromDate = fromDate;
    }

    private void setToDate(java.sql.Date toDate) {
        this.toDate = toDate;
    }
    
    
    public void setDateRange(java.sql.Date from, java.sql.Date to)
    {
        setFromDate(from);
        setToDate(to);
        
        updateReport();
    }
    
    @Override
    public ReportData getData() {
        
        ArrayList<BaseEntity> subscriptionEndingMembers = DatabaseManager.getMemberSubscriptionEndingBetween(getFromDate(), getToDate());
        
        ReportData reportData = new ReportData(new Vector<BaseEntity>(subscriptionEndingMembers), BaseEntity.DetailsLevel.SubscriptionEndingDetails, Member.class);
        
        return reportData;
    }
    
}
