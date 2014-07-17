/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;
import java.util.Vector;
import org.ayf.reports.ReportData;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class Expense extends BaseEntity{
    long id;
    String expenseType;
    Date date;
    double amount;
    String description;
    Member reponsibleMember; 

    @Override
    public EditorType getColumnEditorTypeForColumnName(ColumnName columnNames) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Expense() {
    }

    
    
    public Expense(long id, String expenseType, Date date, double amount, String description, Member reponsibleMember) {
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

    public String getExpenseType() {
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

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReponsibleMember(Member reponsibleMember) {
        this.reponsibleMember = reponsibleMember;
    }
    
    
    
    public static String getNameForColumnID(Expense.ColumnName name)
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
    
    @Override
    public Object getValueForField(ColumnName fieldName)
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
                    return getReponsibleMember().getID();
                }
                return Integer.MAX_VALUE;
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
    
    public void setValueForField(ColumnName fieldName, Object value)
    {
        switch(fieldName)
        {
            case ExpenseType:
                setExpenseType((String) value);
            case Date:
                setDate(DateTime.toSQLDate((String)value));
            case Amount:
                setAmount((Double.valueOf(value.toString())));
            case Description:
                setDescription((String) value);
            case ResponsibleMemberID:
                break;
        }
    }
    
    public Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level)
    {
        Vector<ColumnName> columnNames = new Vector<ColumnName>(10);
        columnNames.add((ColumnName.ExpenseID));
        columnNames.add((ColumnName.ExpenseType));
        columnNames.add((ColumnName.Amount));
        columnNames.add((ColumnName.Date));
        columnNames.add((ColumnName.ResponsibleMemberID));
        columnNames.add((ColumnName.ResponsibleMemberName));
        columnNames.add((ColumnName.ResponsibleMemberPosition));
        columnNames.add((ColumnName.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector getColumnsForDetailsLevel(DetailsLevel level)
    {
        Vector columnNames = new Vector(10);
        columnNames.add(getNameForColumnID(ColumnName.ExpenseID));
        columnNames.add(getNameForColumnID(ColumnName.ExpenseType));
        columnNames.add(getNameForColumnID(ColumnName.Amount));
        columnNames.add(getNameForColumnID(ColumnName.Date));
        columnNames.add(getNameForColumnID(ColumnName.ResponsibleMemberID));
        columnNames.add(getNameForColumnID(ColumnName.ResponsibleMemberName));
        columnNames.add(getNameForColumnID(ColumnName.ResponsibleMemberPosition));
        columnNames.add(getNameForColumnID(ColumnName.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector<Object> toDataArray(DetailsLevel level)
    {
        Vector expenseDetails = new Vector(10);
        
        expenseDetails.add(getValueForField(ColumnName.ExpenseID));
        expenseDetails.add(getValueForField(ColumnName.ExpenseType));
        expenseDetails.add(getValueForField(ColumnName.Amount));
        expenseDetails.add(getValueForField(ColumnName.Date));
        expenseDetails.add(getValueForField(ColumnName.ResponsibleMemberID));
        expenseDetails.add(getValueForField(ColumnName.ResponsibleMemberName));
        expenseDetails.add(getValueForField(ColumnName.ResponsibleMemberPosition));
        expenseDetails.add(getValueForField(ColumnName.Description));
        
        expenseDetails.trimToSize();
        
        return expenseDetails;
    }
    
    
    @Override
    public ReportData getReportDataForDetails(DetailsLevel detailsLevel)
    {
        Vector columnNames = getColumnsForDetailsLevel(detailsLevel);
        Vector rowData = toDataArray(detailsLevel);
        
        return new ReportData(rowData, columnNames);
    }
}
