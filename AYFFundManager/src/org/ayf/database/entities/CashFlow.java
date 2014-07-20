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
public class CashFlow extends BaseEntity{
    int id;
    Date date;
    String status;
    String description;

    
    public CashFlow(){}
    
    public CashFlow(int id, Date date, String status, String description) {
        setID(id);
        this.date = date;
        this.status = status;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
    public static String getNameForColumnID(CashFlow.ColumnName name)
    {
        switch(name)
        {
            case TransactionID:
            case ID:
                return "Transaction ID";
            case UniqueID:
                return "Unique ID";
            case Date:
            case TransactionDate:
                return "Transaction Date";
            case Status:
                return "Transaction Status";
            case Description:
                return "Description of Transaction";
        }
        
        return null;
    }
    
    public Object getValueForField(CashFlow.ColumnName fieldName)
    {
        switch(fieldName)
        {
            case ID:
            case TransactionID:
                return getId();
            case Date:
            case TransactionDate:
                return getDate();
            case Status:
                return getStatus();
            case Description:
                return getDescription();
        }
        
        return null;
    }
    
    public void setValueForField(ColumnName fieldName, Object value)
    {
        if(value == null) return ;
        
        switch(fieldName)
        {
            case TransactionID:
            case ID :
                setID(Integer.parseInt(value.toString()));
                break;
            case Date:
            case TransactionDate:
                if(value instanceof Date)
                {
                    setDate((Date) value);
                }
                else if(value instanceof Timestamp)
                {
                    setDate((new Date(((Timestamp)value).getTime())));
                }
                else
                {
                    setDate(DateTime.toSQLDate((String)value));
                }
                break;
            case Status:
                setStatus((String) value);
                break;
            case Description:
                setDescription((String)value);
                break;
            default:
                super.setValueForField(fieldName, value);
        }
    }
    
    @Override
    public Vector<ColumnName> getColumnIDsForDetailLevel(CashFlow.DetailsLevel level)
    {
        Vector columnNames = new Vector(4);
        columnNames.add((CashFlow.ColumnName.ID));
        columnNames.add((CashFlow.ColumnName.TransactionDate));
        columnNames.add((CashFlow.ColumnName.Status));
        columnNames.add((CashFlow.ColumnName.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector<Object> getColumnsForDetailsLevel(CashFlow.DetailsLevel level)
    {
        Vector columnNames = new Vector(4);
        columnNames.add(getNameForColumnID(CashFlow.ColumnName.ID));
        columnNames.add(getNameForColumnID(CashFlow.ColumnName.TransactionDate));
        columnNames.add(getNameForColumnID(CashFlow.ColumnName.Status));
        columnNames.add(getNameForColumnID(CashFlow.ColumnName.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector<Object> toDataArray(DetailsLevel level)
    {
        Vector transactionDetails = new Vector(10);
        
        transactionDetails.add(getValueForField(CashFlow.ColumnName.ID));
        transactionDetails.add(getValueForField(CashFlow.ColumnName.TransactionDate));
        transactionDetails.add(getValueForField(CashFlow.ColumnName.Status));
        transactionDetails.add(getValueForField(CashFlow.ColumnName.Description));
        
        transactionDetails.trimToSize();
        
        return transactionDetails;
    }
    
    
    @Override
    public ReportData getReportDataForDetails(DetailsLevel detailsLevel)
    {
        Vector<BaseEntity> entity = new Vector<BaseEntity>();
        entity.add(this);
        return new ReportData(entity, detailsLevel, this.getClass());
    }

    @Override
    public EditorType getColumnEditorTypeForColumnName(ColumnName columnName) {
        switch(columnName)
        {
            case TransactionID:
            case ID:
            case Description:
                return EditorType.Label;
            case Date:
            case TransactionDate:
                return EditorType.Date;
            case Status:
                return EditorType.ComboBox;
        }
        
        return null;
    }
}
