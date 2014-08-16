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
import org.ayf.util.NumberUtil;
import org.ayf.util.PreferenceManager;

/**
 *
 * @author om
 */
public class Expense extends BaseEntity{
    String expenseType;
    Date date;
    double amount;
    String reponsibleMemberRegID; 

    @Override
    
    public EditorType getColumnEditorTypeForColumnName(ColumnName columnNames) {
        switch(columnNames)
        {
            case ExpenseType:
                return EditorType.ComboBox;
            case Date:
            case ExpenseDate:
                return EditorType.Date;
            case Amount:
            case Description:
            case ResponsibleMember:
            case ResponsibleMemberName:
            case ResponsibleMemberPosition:
            case UniqueID:
            case ID:
                return EditorType.Label;
                
        }
        
        return null;
    }

    public Expense() {
    }

    
    
    public Expense(int id, String uniqueID, String description, String expenseType, Date date, double amount, String reponsibleMemberRegID) {
        super(id, uniqueID, description);
        this.expenseType = expenseType;
        this.date = date;
        this.amount = amount;
        this.reponsibleMemberRegID = reponsibleMemberRegID;
    }
    
    
    static public String getNextUniqueID()
    {
        String id = PreferenceManager.getIntance().getString(PreferenceManager.NEXT_EXPENSE_ID, "1");
        return "AYF/expense/" + 
                NumberUtil.getFormattedNumber(DateTime.getMonth(DateTime.getToday()) + 1) + 
                "/" + 
                DateTime.getYear(DateTime.getToday()) +
                "/"+ NumberUtil.getFormattedNumber(Integer.parseInt(id));
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

    public String getReponsibleMemberRegID() {
        return reponsibleMemberRegID;
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

    public void setReponsibleMember(String reponsibleMemberRegID) {
        this.reponsibleMemberRegID = reponsibleMemberRegID;
    }
    
    
    
    public static String getNameForColumnID(Expense.ColumnName name)
    {
        switch(name)
        {
            case UniqueID:
                return "Expense ID";
            case ExpenseType:
                return "Category";
            case Date:
            case ExpenseDate:
                return "Expense Date";
            case Amount:
                return "Amount";
            case Description:
                return "Description of Expense";
            case ResponsibleMember:
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
            case ExpenseType:
                return getExpenseType();
            case Date:
            case ExpenseDate:
                return getDate();
            case Amount:
                return getAmount();
            case ResponsibleMember:
                return getReponsibleMemberRegID();
            case ResponsibleMemberName:
                return null;
            case ResponsibleMemberPosition:
                return null;
            default:
                return super.getValueForField(fieldName);
        }
    }
    
    public void setValueForField(ColumnName fieldName, Object value)
    {
        if(value == null) value = "";
        
        switch(fieldName)
        {
            case ExpenseType:
                setExpenseType((String) value);
                break;
            case Date:
            case ExpenseDate:
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
            case Amount:
                setAmount((Double.valueOf(value.toString())));
                break;
            case ResponsibleMember:
                setReponsibleMember(value.toString());
                break;
            default:
                super.setValueForField(fieldName, value);
        }
    }
    
    public Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level)
    {
        Vector<ColumnName> columnNames = new Vector<ColumnName>(10);
        columnNames.add((ColumnName.ID));
        columnNames.add((ColumnName.UniqueID));
        columnNames.add((ColumnName.ExpenseType));
        columnNames.add((ColumnName.Amount));
        columnNames.add((ColumnName.ExpenseDate));
        columnNames.add((ColumnName.ResponsibleMember));
        columnNames.add((ColumnName.Description));
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector getColumnsForDetailsLevel(DetailsLevel level)
    {
        Vector columnNames = new Vector(10);
        columnNames.add(getNameForColumnID(ColumnName.ID));
        columnNames.add(getNameForColumnID(ColumnName.UniqueID));
        columnNames.add(getNameForColumnID(ColumnName.ExpenseType));
        columnNames.add(getNameForColumnID(ColumnName.Amount));
        columnNames.add(getNameForColumnID(ColumnName.ExpenseDate));
        columnNames.add(getNameForColumnID(ColumnName.ResponsibleMember));        
        columnNames.add(getNameForColumnID(ColumnName.Description));
        
        
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector<Object> toDataArray(DetailsLevel level)
    {
        Vector expenseDetails = new Vector(10);
        expenseDetails.add(getValueForField(ColumnName.ID));
        expenseDetails.add(getValueForField(ColumnName.UniqueID));
        expenseDetails.add(getValueForField(ColumnName.ExpenseType));
        expenseDetails.add(getValueForField(ColumnName.Amount));
        expenseDetails.add(getValueForField(ColumnName.ExpenseDate));
        expenseDetails.add(getValueForField(ColumnName.ResponsibleMember));
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
