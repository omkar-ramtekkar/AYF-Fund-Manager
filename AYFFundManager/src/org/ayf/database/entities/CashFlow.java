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
public class CashFlow {
    int id;
    Date date;
    Type status;
    String description;

    public enum ColumnNames
    {
        TransactionID, Date, CurrentStatus, Description
    }
    
    public enum DetailsLevel
    {
        Complete
    }

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
    
    
    
    public static String getNameForColumnID(CashFlow.ColumnNames name)
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
    
    public Object getValueForField(CashFlow.ColumnNames fieldName)
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
    
    public static Vector getColumnIDsForDetailLevel(CashFlow.DetailsLevel level)
    {
        Vector columnNames = new Vector(4);
        columnNames.add((CashFlow.ColumnNames.TransactionID));
        columnNames.add((CashFlow.ColumnNames.Date));
        columnNames.add((CashFlow.ColumnNames.CurrentStatus));
        columnNames.add((CashFlow.ColumnNames.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public static Vector getColumnsForDetailsLevel(CashFlow.DetailsLevel level)
    {
        Vector columnNames = new Vector(4);
        columnNames.add(getNameForColumnID(CashFlow.ColumnNames.TransactionID));
        columnNames.add(getNameForColumnID(CashFlow.ColumnNames.Date));
        columnNames.add(getNameForColumnID(CashFlow.ColumnNames.CurrentStatus));
        columnNames.add(getNameForColumnID(CashFlow.ColumnNames.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector getTransactionDetailsForLevel(CashFlow.DetailsLevel detailLevel)
    {
        Vector transactionDetails = new Vector(10);
        
        transactionDetails.add(getValueForField(CashFlow.ColumnNames.TransactionID));
        transactionDetails.add(getValueForField(CashFlow.ColumnNames.Date));
        transactionDetails.add(getValueForField(CashFlow.ColumnNames.CurrentStatus));
        transactionDetails.add(getValueForField(CashFlow.ColumnNames.Description));
        
        transactionDetails.trimToSize();
        
        return transactionDetails;
    }
    
    
    public ReportData getDataForDetails(CashFlow.DetailsLevel detailsLevel)
    {
        Vector columnNames = CashFlow.getColumnsForDetailsLevel(detailsLevel);
        Vector rowData = getTransactionDetailsForLevel(detailsLevel);
        
        return new ReportData(rowData, columnNames, getColumnIDsForDetailLevel(detailsLevel));
    }
}
