/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;




import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.SubscriptionAmountDetails;
import org.ayf.managers.DatabaseManager;

/**
 *
 * @author om
 */
public class SubscriptionUtil {
  
    Vector<BaseEntity> allSubscriptionAmounts;
    
    public SubscriptionUtil(boolean bPopulateToDate)
    {
        ArrayList<BaseEntity> entities = DatabaseManager.getAllEntities(SubscriptionAmountDetails.class);
        Vector<BaseEntity> allSubscriptionAmounts = new Vector<BaseEntity>(entities);
        
        initialize(allSubscriptionAmounts, bPopulateToDate);
    }

    public SubscriptionUtil(Vector<BaseEntity> allSubscriptionAmounts, boolean bPopulateToDate) throws IllegalArgumentException {
        
        if(allSubscriptionAmounts.isEmpty()) throw new IllegalArgumentException("Subscription Amount Details are empty. Check Database 'SubscriptionAmount' Table");
        
        initialize(allSubscriptionAmounts, bPopulateToDate);
    }
    
    public void initialize(Vector<BaseEntity> allSubscriptionAmounts, boolean bPopulateToDate)
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
    
    
    
    public java.sql.Date getLowestDate()
    {
        return (java.sql.Date)allSubscriptionAmounts.firstElement().getValueForField(BaseEntity.ColumnName.FromDate);
    }
    
    public java.sql.Date getHighestDate()
    {
        return (java.sql.Date) allSubscriptionAmounts.lastElement().getValueForField(BaseEntity.ColumnName.FromDate);
    }
    
    public java.util.Date getLowestUtilDate()
    {
        return new Date(getLowestDate().getTime());
    }
    
    java.util.Date getHighestUtilDate()
    {
        return new Date(getHighestDate().getTime());
    }
    
    public float getLowestSubScriptionAmount()
    {
        return getInitialSubscriptionAmount();
    }
    
    public float getHighestSubScriptionAmount()
    {
        return getLastSubscriptionAmount();
    }
    
    public float getInitialSubscriptionAmount()
    {
        return (Float)this.allSubscriptionAmounts.firstElement().getValueForField(BaseEntity.ColumnName.Amount);
    }
    
    public float getLastSubscriptionAmount()
    {
        return (Float)this.allSubscriptionAmounts.lastElement().getValueForField(BaseEntity.ColumnName.Amount);
    }
    
    public float getScriptionAmountForDate(java.sql.Date date) throws IllegalArgumentException
    {
        if(date == null) throw new IllegalArgumentException("Date provided for getting subscription amount can't be null");
        
        if(allSubscriptionAmounts.firstElement().getValueForField(BaseEntity.ColumnName.ToDate) == null)
            populatedToDate();
        
        if(date.before(getLowestDate()))
            return getInitialSubscriptionAmount();
        
        if(date.after(getHighestDate()))
            return getLastSubscriptionAmount();
        
        for (BaseEntity subscriptionAmountDetails : allSubscriptionAmounts) {
            java.sql.Date fromDate = (java.sql.Date) subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.FromDate);
            java.sql.Date toDate = (java.sql.Date) subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.ToDate);
            
            if(DateTime.isDateBetween(fromDate, toDate, date))
            {
                return (Float)subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.Amount);
            }
        }
        
        assert (false);
        return 0;
    }
    
    public float getScriptionAmountForDate(java.util.Date date) throws IllegalArgumentException
    {
        return getScriptionAmountForDate(DateTime.toSQLDate(date));
    }
    
    public float getSubscriptionAmountToBePaidBetweenDates(java.sql.Date startDate, java.sql.Date endDate) throws IllegalArgumentException
    {
        if(startDate == null) throw new IllegalArgumentException("Start date can't be null");
        if(endDate == null) throw new IllegalArgumentException("End date can't be null");
        
        if(startDate.after(endDate)) throw new IllegalArgumentException("Start date should be less than end date");
        
        if(startDate.after(getHighestDate()))
            return getHighestSubScriptionAmount();
        
        if(endDate.before(getLowestDate()))
            return getLowestSubScriptionAmount();
        
        float totalSubscriptionToBePaid = 0;
        
        java.sql.Date tempDate = startDate;
        
        while(tempDate.before(getLowestDate()))
        {
            totalSubscriptionToBePaid += getLowestSubScriptionAmount();
            tempDate = DateTime.dateByAdding(tempDate, 0, 0, 1);
        }
        
        for (BaseEntity baseEntity : allSubscriptionAmounts) 
        {
            SubscriptionAmountDetails subscriptionAmountDetails = (SubscriptionAmountDetails) baseEntity;
            
            if(subscriptionAmountDetails == null) throw new IllegalArgumentException("Invalid entity in allSubscriptionAmounts array : " + baseEntity.toString());
            
            if(subscriptionAmountDetails.getToDate().before(tempDate))
                continue;
            
            
            java.sql.Date fromDate = (java.sql.Date) subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.FromDate);
            java.sql.Date toDate = (java.sql.Date) subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.ToDate);
            
            while(DateTime.isDateBetween(fromDate, toDate, tempDate) && tempDate.before(endDate))
            {
                totalSubscriptionToBePaid += (Float)subscriptionAmountDetails.getValueForField(BaseEntity.ColumnName.Amount);
                tempDate = DateTime.dateByAdding(tempDate, 0, 0, 1);
            }
            
            if(tempDate.after(getHighestDate()))
                break;
        }
        
        while(tempDate.before(endDate))
        {
            totalSubscriptionToBePaid += getHighestSubScriptionAmount();
            tempDate = DateTime.dateByAdding(tempDate, 0, 0, 1);
        }
        
        return totalSubscriptionToBePaid;
    }
    
    public float getSubscriptionAmountToBePaidByToday(java.sql.Date startDate) throws IllegalArgumentException
    {
        return getSubscriptionAmountToBePaidBetweenDates(startDate, DateTime.getTodaySQL());
    }
}
