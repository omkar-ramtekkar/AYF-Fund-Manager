/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.util.Arrays;
import java.util.Map;
import java.util.Vector;
import static org.ayf.database.entities.Member.detailLevelVsColumnsMap;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.ReportData;
import org.ayf.ui.InformationPanel;

/**
 *
 * @author om
 */
public abstract class BaseEntity implements Comparable<BaseEntity>
{
    public enum Gender { Unknown, Male, Female }
    
    public enum EditorType
    {
        Date,
        ComboBox,
        Label,
        Number
    }
    
    public enum DetailsLevel
    {
        None, OnlyIDAndName, Basic, AllPersonal, AllProfessional, AllSocial,  Complete, MemberStatement, Database, Search  
    }
    
    public enum ColumnName
    {
        ID, FirstName, MiddleName, LastName, DateOfBirth, MaritalStatus, 
        Cast, SubCast, District, BloodGroup, Gender, Age, PermanentAddress, TemporaryAddress,
        ContactNumber, EmailAddress, Education, Profession, RegisterationDate, Position,
        ImagePath, ReceiptNumber, DonationDate, DonationType, PaymentMode, Status, ExpenseType, Date, ExpenseDate, Amount, Description, ResponsibleMember, ResponsibleMemberName, ResponsibleMemberPosition,
        MemberID, TransactionDate, UniqueID, MemberUniqueID, FromDate, ToDate
        
    }
    
    public enum MaritalStatus
    {
        Unknown, Single, Married
    }
    
    public enum ActiveStatus
    {
        Unknown, Active, Inactive
    }

    public BaseEntity() {
    }
    
    

    public BaseEntity(int id, String uniqueID, String description) 
    {
        setID(id);
        setUniqueID(uniqueID);
        setDescription(description);
    }
    
    public Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level, Map<DetailsLevel, Vector<ColumnName>> detailLevelVsColumnsMap) 
    {
        return detailLevelVsColumnsMap.get(level);
    }
    
    public Vector<Object> getColumnsForDetailsLevel(DetailsLevel level, Map<DetailsLevel, Vector<ColumnName>> detailLevelVsColumnsMap)
    {
        Vector<ColumnName> columnIDs = detailLevelVsColumnsMap.get(level);
        
        if(columnIDs == null)
        {
            columnIDs = detailLevelVsColumnsMap.get(DetailsLevel.OnlyIDAndName);
        }
        
        if(columnIDs != null)
        {
            Vector<Object> columnNames = new Vector<Object>(columnIDs.size());
            
            for (ColumnName columnID : columnIDs) {
                columnNames.add(getNameForColumnID(columnID));
            }
            
            return columnNames;
        }
        
        return null;
    }
    
    public Vector<Object> toDataArray(DetailsLevel level, Map<DetailsLevel, Vector<ColumnName>> detailLevelVsColumnsMap)
    {
        Vector<ColumnName> columnIDs = detailLevelVsColumnsMap.get(level);
        Vector details = new Vector(columnIDs.size());
        
        for (ColumnName columnID : columnIDs) {
            details.add(getValueForField(columnID));
        }
        
        return details;
    }
    
    
    
    public static Vector<String> getAllValuesForColumnName(ColumnName columnName)
    {
        switch(columnName)
        {
            case MaritalStatus:
            {
                Vector<String> values = new Vector<String>(2);
                values.add(MaritalStatus.Married.toString());
                values.add(MaritalStatus.Single.toString());
                return values;
            }
            case BloodGroup:
            {
                Vector<String> values = new Vector<String>(Arrays.asList(InformationPanel.BloodGroups));
                return values;
            }
            case Gender:
            {
                Vector<String> values = new Vector<String>(2);
                values.add(Member.Gender.Male.toString());
                values.add(Member.Gender.Female.toString());
                return values;
            }
            case Profession:
            {
                Vector<String> values = new Vector<String>(DatabaseManager.typesToStrings(DatabaseManager.getProfessionTypes()));
                return values;
            }
            case Position:
            {
                Vector<String> values = new Vector<String>(DatabaseManager.typesToStrings(DatabaseManager.getPositionTypes()));
                return values;
            }
            case DonationType:
            {
                Vector<String> values = new Vector<String>(DatabaseManager.typesToStrings(DatabaseManager.getDonationTypes()));
                return values;
            }
            case PaymentMode:
            {
                Vector<String> values = new Vector<String>(DatabaseManager.typesToStrings(DatabaseManager.getPaymentModeTypes()));
                return values;
            }
            case ExpenseType:
            {
                Vector<String> values = new Vector<String>(DatabaseManager.typesToStrings(DatabaseManager.getExpenseTypes()));
                return values;
            }
            case Status:
            {
                return null;
            }
        }
        
        return null;
    }
    
    public abstract EditorType getColumnEditorTypeForColumnName(ColumnName columnNames);
    public abstract Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level);
    public abstract Vector<Object> getColumnsForDetailsLevel(DetailsLevel level);
    public abstract Vector<Object> toDataArray(DetailsLevel level);
    public abstract ReportData getReportDataForDetails(DetailsLevel detailsLevel);
    
    public abstract String getNameForColumnID(ColumnName name);
    
    
    public Object getValueForField(ColumnName fieldName)
    {
        switch(fieldName)
        {
            case ID:
                return getID();
            case UniqueID:
                return getUniqueID();
            case Description:
                return getDescription();
        }
        
        return null;
    }

    public void setValueForField(ColumnName fieldName, Object value)
    {
        if(value == null) value = "";
        
        switch(fieldName)
        {
            case ID:
                setID(Integer.valueOf(value.toString()));
                break;
            case UniqueID:
                setUniqueID(value.toString());
                break;
            case Description:
                setDescription(value.toString());
                break;
        }
    }
    
    public int getID() 
    {
        return id;
    }
    
    protected void setID(int id)
    {
        this.id = id;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(BaseEntity o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private int id = Integer.MAX_VALUE;
    private String uniqueID = null;
    private String description = null;
}
