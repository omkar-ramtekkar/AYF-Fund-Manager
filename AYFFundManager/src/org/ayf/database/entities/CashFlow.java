/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;
import java.util.Vector;
import org.ayf.reports.ReportData;

/**
 *
 * @author om
 */
public class CashFlow extends BaseEntity{
    int id;
    Date date;
    Type status;
    String description;

    
    public CashFlow(int id, Date date, Type status, String description) {
        this.id = id;
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

    public Type getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
    
    
    
    public static String getNameForColumnID(CashFlow.ColumnName name)
    {
        switch(name)
        {
            case TransactionID:
                return "ID";
            case Date:
                return "Date";
            case CurrentStatus:
                return "Status";
            case Description:
                return "Description of Transaction";
        }
        
        return null;
    }
    
    public Object getValueForField(CashFlow.ColumnName fieldName)
    {
        switch(fieldName)
        {
            case TransactionID:
                return getId();
            case Date:
                return getDate();
            case CurrentStatus:
                return getStatus();
            case Description:
                return getDescription();
        }
        
        return null;
    }
    
    @Override
    public Vector<ColumnName> getColumnIDsForDetailLevel(CashFlow.DetailsLevel level)
    {
        Vector columnNames = new Vector(4);
        columnNames.add((CashFlow.ColumnName.TransactionID));
        columnNames.add((CashFlow.ColumnName.Date));
        columnNames.add((CashFlow.ColumnName.CurrentStatus));
        columnNames.add((CashFlow.ColumnName.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector<Object> getColumnsForDetailsLevel(CashFlow.DetailsLevel level)
    {
        Vector columnNames = new Vector(4);
        columnNames.add(getNameForColumnID(CashFlow.ColumnName.TransactionID));
        columnNames.add(getNameForColumnID(CashFlow.ColumnName.Date));
        columnNames.add(getNameForColumnID(CashFlow.ColumnName.CurrentStatus));
        columnNames.add(getNameForColumnID(CashFlow.ColumnName.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector<Object> toDataArray(DetailsLevel level)
    {
        Vector transactionDetails = new Vector(10);
        
        transactionDetails.add(getValueForField(CashFlow.ColumnName.TransactionID));
        transactionDetails.add(getValueForField(CashFlow.ColumnName.Date));
        transactionDetails.add(getValueForField(CashFlow.ColumnName.CurrentStatus));
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
            case Description:
                return EditorType.Label;
            case Date:
                return EditorType.Date;
            case CurrentStatus:
                return EditorType.ComboBox;
        }
        
        return null;
    }
}
