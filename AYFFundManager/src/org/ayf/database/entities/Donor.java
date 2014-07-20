/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.ReportData;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class Donor extends Member
{
    private float   donationAmount;
    private  long    receiptNumber;
    private Date    donationDate;
    private String    donationType;
    private String    paymentMode;

    public Donor() {
    }
    
    

    public Donor(float donationAmount, long receiptNumber, Date donationDate, String donationType, String paymentMode, int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, MaritalStatus maritalStatus, String cast, String subCast, String district, String bloodGroup, Gender gender, String permanentAddress, String temporaryAddress, String contactNumber, String emailAddress, String education, String profession, Date registerationDate, String position, String imagePath, ActiveStatus currentStatus) {
        super(memberID, firstName, middleName, lastName, dateOfBirth, maritalStatus, cast, subCast, district, bloodGroup, gender, permanentAddress, temporaryAddress, contactNumber, emailAddress, education, profession, registerationDate, position, imagePath, currentStatus);
        this.donationAmount = donationAmount;
        this.receiptNumber = receiptNumber;
        this.donationDate = donationDate;
        this.donationType = donationType;
        this.paymentMode = paymentMode;
    }

    public Donor(float donationAmount, long receiptNumber, Date donationDate, String donationType, String paymentMode, int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, MaritalStatus maritalStatus, String cast, Gender gender, Date registerationDate, String position, ActiveStatus currentStatus) {
        super(memberID, firstName, middleName, lastName, dateOfBirth, maritalStatus, cast, gender, registerationDate, position, currentStatus);
        this.donationAmount = donationAmount;
        this.receiptNumber = receiptNumber;
        this.donationDate = donationDate;
        this.donationType = donationType;
        this.paymentMode = paymentMode;
    }
    

    public float getDonationAmount() {
        return donationAmount;
    }

    public long getReceiptNumber() {
        return receiptNumber;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public String getDonationType() {
        return donationType;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setDonationAmount(float donationAmount) {
        this.donationAmount = donationAmount;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    
    

    
    public Vector getColumnsForDetailsLevel(DetailsLevel level)
    {
        Vector columnNames = new Vector();
        switch(level)
        {
            case Database:
                columnNames.add(getNameForColumnID(ColumnName.ID));
                columnNames.add(getNameForColumnID(ColumnName.UniqueID));
                columnNames.add(getNameForColumnID(ColumnName.FirstName));
                columnNames.add(getNameForColumnID(ColumnName.MiddleName));
                columnNames.add(getNameForColumnID(ColumnName.LastName));
                columnNames.add(getNameForColumnID(ColumnName.PermanentAddress));
                columnNames.add(getNameForColumnID(ColumnName.TemporaryAddress));
                columnNames.add(getNameForColumnID(ColumnName.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnName.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnName.Profession));
                columnNames.add(getNameForColumnID(ColumnName.DateOfBirth));
                columnNames.add(getNameForColumnID(ColumnName.Gender));
                columnNames.add(getNameForColumnID(ColumnName.ReceiptNumber));
                columnNames.add(getNameForColumnID(ColumnName.DonationDate));
                columnNames.add(getNameForColumnID(ColumnName.Amount));
                columnNames.add(getNameForColumnID(ColumnName.DonationType));
                columnNames.add(getNameForColumnID(ColumnName.PaymentMode));
                break;
            default:
            {
                if(level != Member.DetailsLevel.MemberStatement)
                {
                    columnNames.addAll(super.getColumnsForDetailsLevel(level));
                }
                
                columnNames.remove(getNameForColumnID(ColumnName.ImagePath));
                
                columnNames.add(getNameForColumnID(ColumnName.ReceiptNumber));
                columnNames.add(getNameForColumnID(ColumnName.DonationDate));
                columnNames.add(getNameForColumnID(ColumnName.Amount));
                columnNames.add(getNameForColumnID(ColumnName.DonationType));
                columnNames.add(getNameForColumnID(ColumnName.PaymentMode));

                return columnNames;
            }
        }
        
        return columnNames;
    }
    
    public Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level)
    {
        Vector<ColumnName> columnIDs = new Vector<ColumnName>();
        
        switch(level)
        {
            case Database:
                columnIDs.add((ColumnName.UniqueID));
                columnIDs.add((ColumnName.FirstName));
                columnIDs.add((ColumnName.MiddleName));
                columnIDs.add((ColumnName.LastName));
                columnIDs.add((ColumnName.PermanentAddress));
                columnIDs.add((ColumnName.TemporaryAddress));
                columnIDs.add((ColumnName.ContactNumber));
                columnIDs.add((ColumnName.EmailAddress));
                columnIDs.add((ColumnName.Profession));
                columnIDs.add((ColumnName.DateOfBirth));
                columnIDs.add((ColumnName.Gender));
                columnIDs.add((ColumnName.ReceiptNumber));
                columnIDs.add((ColumnName.DonationDate));
                columnIDs.add((ColumnName.Amount));
                columnIDs.add((ColumnName.DonationType));
                columnIDs.add((ColumnName.PaymentMode));
                columnIDs.add((ColumnName.ImagePath));
                break;
            default:
            {
                if(level != Member.DetailsLevel.MemberStatement)
                {
                    columnIDs.addAll(super.getColumnIDsForDetailLevel(level));
                }
                
                columnIDs.remove(ColumnName.ImagePath);
                
                columnIDs.add((ColumnName.ReceiptNumber));
                columnIDs.add((ColumnName.DonationDate));
                columnIDs.add((ColumnName.Amount));
                columnIDs.add((ColumnName.DonationType));
                columnIDs.add((ColumnName.PaymentMode));
            }
        }

        return columnIDs;
    }
    
    @Override
    public Object getValueForField(ColumnName fieldName)
    {
        switch(fieldName)
        {
            case ReceiptNumber:
                return getReceiptNumber();
            case Amount:
                return getDonationAmount();
            case DonationDate:
                return getDonationDate();
            case DonationType:
                return getDonationType();
            case PaymentMode:
                return getPaymentMode();
            default:
                super.getValueForField(fieldName);
        }
        
        return null;
    }
    
    @Override
    protected void setID(int id)
    {
        super.setID(id);
    }

    public void setUniqueID(String uniqueID) 
    {
        super.setUniqueID(uniqueID);
        
        if(getUniqueID() != null && getUniqueID().length() > 0)
        {
            Vector<Object> value = new Vector<Object>(1);
            value.add(getUniqueID());
            
            Vector<ColumnName> column = new Vector<ColumnName>(1);
            column.add(ColumnName.UniqueID);
            
            
            ArrayList<BaseEntity> entity = DatabaseManager.getEntitiesWithCondition(column, value, Member.class);
            
            if(entity != null && entity.size() > 0)
            {
                BaseEntity baseEntity = entity.get(0);
                
                Vector<ColumnName> columns = baseEntity.getColumnIDsForDetailLevel(DetailsLevel.Database);
                
                for (ColumnName columnName : columns) 
                {
                    this.setValueForField(columnName, baseEntity.getValueForField(columnName));
                }
            }
        }
    }

    @Override
    public void setValueForField(ColumnName fieldName, Object value) {
        
        switch(fieldName)
        {
            case Amount:
                setDonationAmount(Double.valueOf(value.toString()).floatValue());
                break;
            case DonationDate:
                if(value instanceof Date)
                {
                    setDonationDate((Date) value);
                }
                else if(value instanceof Timestamp)
                {
                    setDonationDate((new Date(((Timestamp)value).getTime())));
                }
                else
                {
                    setDonationDate(DateTime.toSQLDate((String)value));
                }
                break;
            case DonationType:
                setDonationType((String) value);
                break;
            case PaymentMode:
                setPaymentMode((String) value);
                break;
            default:
                super.setValueForField(fieldName, value);
        }
    }
    
    
    
    @Override
    public Vector toDataArray(DetailsLevel detailLevel)
    {
        Vector memberDetails;
        if(detailLevel != Member.DetailsLevel.MemberStatement)
        {
            memberDetails = super.toDataArray(detailLevel);
        }
        else
        {
            memberDetails = new Vector();
        }
        
        memberDetails.add(getValueForField(ColumnName.ReceiptNumber));
        memberDetails.add(getValueForField(ColumnName.DonationDate));
        memberDetails.add(getValueForField(ColumnName.Amount));
        memberDetails.add(getValueForField(ColumnName.DonationType));
        memberDetails.add(getValueForField(ColumnName.PaymentMode));

        return memberDetails;
    }
    
    
    @Override
    public ReportData getReportDataForDetails(DetailsLevel detailsLevel)
    {
        Vector<Object> columnNames = getColumnsForDetailsLevel(detailsLevel);
        Vector<Object> rowData = toDataArray(detailsLevel);
        
        return new ReportData(rowData, columnNames);
    }
}
