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
public class Expense {
    long id;
    Type expenseType;
    Date date;
    double amount;
    String description;
    Member reponsibleMember; 
    
    public enum ColumnNames
    {
        ExpenseID, ExpenseType, Date, Amount, Description, ResponsibleMemberID, ResponsibleMemberName, ResponsibleMemberPosition
    }
    
    public enum DetailsLevel
    {
        Complete
    }
    

    public Expense(long id, Type expenseType, Date date, double amount, String description, Member reponsibleMember) {
        this.id = id;
        this.expenseType = expenseType;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.reponsibleMember = reponsibleMember;
    }
    
    

    public long getId() {
        return id;
    }

    public Type getExpenseType() {
        return expenseType;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Member getReponsibleMember() {
        return reponsibleMember;
    }
    
    
    public static String getNameForColumnID(Expense.ColumnNames name)
    {
        switch(name)
        {
            case ExpenseID:
                return "ID";
            case ExpenseType:
                return "Category";
            case Date:
                return "Date";
            case Amount:
                return "Amount";
            case Description:
                return "Description of Expense";
            case ResponsibleMemberID:
                return "Responsible Member ID";
            case ResponsibleMemberName:
                return "Responsible Member Name";
            case ResponsibleMemberPosition:
                return "Responsible Member Position";
        }
        
        return null;
    }
    
    public Object getValueForField(Expense.ColumnNames fieldName)
    {
        switch(fieldName)
        {
            case ExpenseID:
                return getId();
            case ExpenseType:
                return getExpenseType();
            case Date:
                return getDate();
            case Amount:
                return getAmount();
            case Description:
                return getDescription();
            case ResponsibleMemberID:
                if(getReponsibleMember() != null)
                {
                    return getReponsibleMember().getMemberID();
                }
            case ResponsibleMemberName:
                if(getReponsibleMember() != null)
                {
                    return getReponsibleMember().getFirstName() + " " + getReponsibleMember().getMiddleName() + " " + getReponsibleMember().getLastName();
                }
            case ResponsibleMemberPosition:
                if(getReponsibleMember() != null)
                {
                    return getReponsibleMember().getPosition();
                }
        }
        
        return null;
    }
    
    public static Vector getColumnsForDetailsLevel(Expense.DetailsLevel level)
    {
        Vector columnNames = new Vector(10);
        columnNames.add(getNameForColumnID(ColumnNames.ExpenseID));
        columnNames.add(getNameForColumnID(ColumnNames.ExpenseType));
        columnNames.add(getNameForColumnID(ColumnNames.Amount));
        columnNames.add(getNameForColumnID(ColumnNames.Date));
        columnNames.add(getNameForColumnID(ColumnNames.ResponsibleMemberID));
        columnNames.add(getNameForColumnID(ColumnNames.ResponsibleMemberName));
        columnNames.add(getNameForColumnID(ColumnNames.ResponsibleMemberPosition));
        columnNames.add(getNameForColumnID(ColumnNames.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector getExpenseDetailsForLevel(Expense.DetailsLevel detailLevel)
    {
        Vector expenseDetails = new Vector(10);
        
        expenseDetails.add(getValueForField(ColumnNames.ExpenseID));
        expenseDetails.add(getValueForField(ColumnNames.ExpenseType));
        expenseDetails.add(getValueForField(ColumnNames.Amount));
        expenseDetails.add(getValueForField(ColumnNames.Date));
        expenseDetails.add(getValueForField(ColumnNames.ResponsibleMemberID));
        expenseDetails.add(getValueForField(ColumnNames.ResponsibleMemberName));
        expenseDetails.add(getValueForField(ColumnNames.ResponsibleMemberPosition));
        expenseDetails.add(getValueForField(ColumnNames.Description));
        
        expenseDetails.trimToSize();
        
        return expenseDetails;
    }
    
    
    public ReportData getDataForDetails(Expense.DetailsLevel detailsLevel)
    {
        Vector columnNames = Expense.getColumnsForDetailsLevel(detailsLevel);
        Vector rowData = getExpenseDetailsForLevel(detailsLevel);
        
        return new ReportData(rowData, columnNames);
    }
}
