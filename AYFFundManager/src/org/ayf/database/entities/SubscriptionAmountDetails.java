/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;
import org.ayf.reports.ReportData;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class SubscriptionAmountDetails extends BaseEntity
{ 
    java.sql.Date fromDate;
    java.sql.Date toDate;
    float amount;

    public SubscriptionAmountDetails() {
    }

    public SubscriptionAmountDetails(Date fromDate, Date toDate, float amount) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.amount = amount;
    }

    public SubscriptionAmountDetails(int id, String uniqueID, String description, Date fromDate, Date toDate, float amount) {
        super(id, uniqueID, description);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.fromDate != null ? this.fromDate.hashCode() : 0);
        hash = 43 * hash + (this.toDate != null ? this.toDate.hashCode() : 0);
        hash = 43 * hash + Float.floatToIntBits(this.amount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final SubscriptionAmountDetails other = (SubscriptionAmountDetails) obj;
        
        return getID() == other.getID();
    }
    
    

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
    @Override
    public Object getValueForField(ColumnName fieldName)
    {
        switch(fieldName)
        {
            case FromDate:
                return getFromDate();
            case ToDate:
                return getToDate();
            case Amount:
                return getAmount();
            default:
                return super.getValueForField(fieldName);
        }
    }
    
    
    @Override
    public void setValueForField(ColumnName fieldName, Object value)
    {
        if(value == null) value = "";
        
        switch(fieldName)
        {
            case FromDate:
                if(value instanceof Date)
                {
                    setFromDate((Date) value);
                }
                else if(value instanceof Timestamp)
                {
                    setFromDate((new Date(((Timestamp)value).getTime())));
                }
                else
                {
                    setFromDate(DateTime.toSQLDate((String)value));
                }
            break;
            case ToDate:
                if(value instanceof Date)
                {
                    setToDate((Date) value);
                }
                else if(value instanceof Timestamp)
                {
                    setToDate((new Date(((Timestamp)value).getTime())));
                }
                else
                {
                    setToDate(DateTime.toSQLDate((String)value));
                }
            break;
            case Amount:
                setAmount(Float.parseFloat(value.toString()));
                break;
            default:
                super.setValueForField(fieldName, value);
        }
    }
    
    @Override
    public EditorType getColumnEditorTypeForColumnName(ColumnName columnNames) {
        switch(columnNames)
        {
            case FromDate:
            case ToDate:
                return EditorType.Date;
            case Amount:
            case ID:
            case UniqueID:
                return EditorType.Label;
        }
        
        return null;
    }

    @Override
    public Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level) {
        Vector<ColumnName> columns = new Vector<ColumnName>(5);
        
        columns.add(ColumnName.UniqueID);
        columns.add(ColumnName.FromDate);
        if(level != DetailsLevel.Database)
        {
            columns.add(ColumnName.ToDate);
        }
        
        columns.add(ColumnName.Amount);
        columns.add(ColumnName.Description);
        
        return columns;
    }

    @Override
    public Vector<Object> getColumnsForDetailsLevel(DetailsLevel level) {
        Vector<Object> columns = new Vector<Object>(5);
        
        columns.add(getNameForColumnID(ColumnName.UniqueID));
        columns.add(getNameForColumnID(ColumnName.FromDate));
        if(level != DetailsLevel.Database)
        {
            columns.add(getNameForColumnID(ColumnName.ToDate));
        }
        
        columns.add(getNameForColumnID(ColumnName.Amount));
        columns.add(getNameForColumnID(ColumnName.Description));
        
        return columns;
    }

    @Override
    public Vector<Object> toDataArray(DetailsLevel level) {
        Vector<Object> values = new Vector<Object>(5);
        
        values.add(getValueForField(ColumnName.UniqueID));
        values.add(getValueForField(ColumnName.FromDate));
        if(level != DetailsLevel.Database)
        {
            values.add(getValueForField(ColumnName.ToDate));
        }
        
        values.add(getValueForField(ColumnName.Amount));
        values.add(getValueForField(ColumnName.Description));
        
        return values;
    }

    @Override
    public ReportData getReportDataForDetails(DetailsLevel detailsLevel) {
        Vector columnNames = getColumnsForDetailsLevel(detailsLevel);
        Vector rowData = toDataArray(detailsLevel);
        
        return new ReportData(rowData, columnNames);
    }
    
    @Override
    public String getNameForColumnID(ColumnName name)
    {
        switch(name)
        {
            case ID:
                return "ID";
            case UniqueID:
                return "Subscription ID";
            case FromDate:
                return "From Date";
            case ToDate:
                return "To Date";
            case Amount:
                return "Amount";
            case Description:
                return "Description";
            default:
                return null;
        }
    }
    

    public int compareTo(SubscriptionAmountDetails o) {
        if(o == null) return 1;
        
        if(this.getFromDate() == o.getFromDate() || this.getFromDate().equals(o.getFromDate())) return 0;
        else if(this.getFromDate().after(o.getFromDate())) return 1;
        else return -1;
    }

    @Override
    public int compareTo(BaseEntity o) {
        return compareTo((SubscriptionAmountDetails) o);
    }
}
