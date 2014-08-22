/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import com.sun.tools.corba.se.idl.InvalidArgument;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.SubscriptionAmountDetails;

/**
 *
 * @author om
 */
public class SubscriptionUtil {
  
    Vector<BaseEntity> allSubscriptionAmounts;

    public SubscriptionUtil(Vector<BaseEntity> allSubscriptionAmounts, boolean bPopulateToDate) throws InvalidArgument {
        
        if(allSubscriptionAmounts.isEmpty()) throw new InvalidArgument("Subscription Amount Details are empty. Check Database 'SubscriptionAmount' Table");
        
        initialize(allSubscriptionAmounts, bPopulateToDate);
    }
    
    void initialize(Vector<BaseEntity> allSubscriptionAmounts, boolean bPopulateToDate)
    {
        Collections.sort(allSubscriptionAmounts);
        
        this.allSubscriptionAmounts = allSubscriptionAmounts;
        
        if(bPopulateToDate)
        {
            populatedToDate();
        }
    }
    
    public void populatedToDate()
    {
        java.sql.Date toDate = null;
        SubscriptionAmountDetails lastSubscriptionEntity = null;
        for (BaseEntity subscriptionAmountDetails : allSubscriptionAmounts) {
            if(lastSubscriptionEntity == null)
            {
                lastSubscriptionEntity = (SubscriptionAmountDetails)subscriptionAmountDetails;
                continue;
            }
            
            if(lastSubscriptionEntity.getFromDate().equals(subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.FromDate)))
            {
                lastSubscriptionEntity.setToDate(lastSubscriptionEntity.getFromDate());
            }
            else
            {
                lastSubscriptionEntity.setToDate((java.sql.Date) DateTime.dateByAdding((java.sql.Date)subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.FromDate), -1, 0, 0));
            }
            
            lastSubscriptionEntity = (SubscriptionAmountDetails)subscriptionAmountDetails;
        }
        
        lastSubscriptionEntity.setToDate(DateTime.dateByAdding(lastSubscriptionEntity.getFromDate(), -1, 0, 1));        
    }

    public Vector<BaseEntity> getAllSubscriptionAmounts() {
        return allSubscriptionAmounts;
    }
    
    
    
    java.sql.Date getLowestDate()
    {
        return (java.sql.Date)allSubscriptionAmounts.firstElement().getValueForField(BaseEntity.ColumnName.FromDate);
    }
    
    java.sql.Date getHighestDate()
    {
        return (java.sql.Date) allSubscriptionAmounts.lastElement().getValueForField(BaseEntity.ColumnName.FromDate);
    }
    
    java.util.Date getLowestUtilDate()
    {
        return new Date(getLowestDate().getTime());
    }
    
    java.util.Date getHighestUtilDate()
    {
        return new Date(getHighestDate().getTime());
    }
    
    float getLowestSubScriptionAmount()
    {
        return 0;
    }
    
    float getHighestSubScriptionAmount()
    {
        return 0;
    }
    
    float getInitialSubscriptionAmount()
    {
        return (Float)this.allSubscriptionAmounts.firstElement().getValueForField(BaseEntity.ColumnName.Amount);
    }
    
    float getLastSubscriptionAmount()
    {
        return (Float)this.allSubscriptionAmounts.lastElement().getValueForField(BaseEntity.ColumnName.Amount);
    }
    
    float getScriptionAmountForDate(java.sql.Date date) throws InvalidArgument
    {
        if(date == null) throw new InvalidArgument("Date provided for getting subscription amount can't be null");
        
        for (BaseEntity subscriptionAmountDetails : allSubscriptionAmounts) {
            java.sql.Date fromDate = (java.sql.Date) subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.FromDate);
            java.sql.Date toDate = (java.sql.Date) subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.ToDate);
            
            if(DateTime.isDateBetween(fromDate, toDate, date))
            {
                return (Float)subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.Amount);
            }
        }
        
        if(date.before(getLowestDate()))
            return getInitialSubscriptionAmount();
        else
            return getLastSubscriptionAmount();
    }
    
    float getScriptionAmountForDate(java.util.Date date) throws InvalidArgument
    {
        return getScriptionAmountForDate(DateTime.toSQLDate(date));
    }
}
