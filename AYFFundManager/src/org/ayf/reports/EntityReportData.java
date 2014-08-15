/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Donor;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class EntityReportData
{

    Date lastDonationDate;
    Date lastSubscriptionDate;
    
    float subscriptionTillDate;
    float balanceSubscriptionDues;
    float donationTillDate;
    float expectedSubscription;
    
    Vector<BaseEntity> allDonations;
    
    Member member;

    public EntityReportData() {
        initializeNULL();
    }

    public EntityReportData(Vector<BaseEntity> allDonations) 
    {
        initialize(allDonations);
    }

    public EntityReportData(Member member) {
        this.member = member;
        
        ReportData allDonationsData = DatabaseManager.getMemberStatement(member.getUniqueID());
        initialize(allDonationsData.getEntities());
    }
    
    
    
    void initializeNULL()
    {
        this.lastDonationDate = null;
        this.lastSubscriptionDate = null;
        this.subscriptionTillDate = 0;
        this.balanceSubscriptionDues = 0;
        this.donationTillDate = 0;
        
    }
    
    public void initialize(Vector<BaseEntity> allDonations)
    {
        initializeNULL();
        
        this.allDonations = allDonations;
        
        if(this.allDonations != null)
        {
            for (BaseEntity baseEntity : getAllDonations())
            {
                Donor donor = (Donor) baseEntity; 
                if(this.lastDonationDate == null)
                    this.lastDonationDate = donor.getDonationDate();
                else
                {
                    lastDonationDate = DateTime.getAfterDate(lastDonationDate, donor.getDonationDate());
                }
                
                this.donationTillDate += donor.getDonationAmount();
                
                if(donor.isSubscription())
                {
                    if(this.lastSubscriptionDate == null)
                        this.lastSubscriptionDate = donor.getDonationDate();
                    else
                    {
                        lastSubscriptionDate = DateTime.getAfterDate(lastSubscriptionDate, donor.getDonationDate());
                    }
                    
                    this.subscriptionTillDate += donor.getDonationAmount();
                }
            }
        }
    }

    public float getExpectedSubscription() {
        return expectedSubscription;
    }

    public Date getLastDonationDate() {
        return lastDonationDate;
    }

    public Date getLastSubscriptionDate() {
        return lastSubscriptionDate;
    }

    public float getSubscriptionTillDate() {
        return subscriptionTillDate;
    }

    public float getBalanceSubscriptionDues() {
        return balanceSubscriptionDues;
    }

    public float getDonationTillDate() {
        return donationTillDate;
    }

    public Vector<BaseEntity> getAllDonations() {
        return allDonations;
    }
    
    
    
}
