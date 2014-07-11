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
public class Donor extends Member
{
    private float   donationAmount;
    private final long    receiptNumber;
    private Date    donationDate;
    private Type    donationType;
    private Type    paymentMode;

    public Donor(float donationAmount, long receiptNumber, Date donationDate, Type donationType, Type paymentMode, int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, String maritalStatus, String cast, String subCast, String district, String bloodGroup, Gender gender, String permanentAddress, String temporaryAddress, String contactNumber, String emailAddress, String education, Type profession, Date registerationDate, String position, String imagePath, String currentStatus) {
        super(memberID, firstName, middleName, lastName, dateOfBirth, maritalStatus, cast, subCast, district, bloodGroup, gender, permanentAddress, temporaryAddress, contactNumber, emailAddress, education, profession, registerationDate, position, imagePath, currentStatus);
        this.donationAmount = donationAmount;
        this.receiptNumber = receiptNumber;
        this.donationDate = donationDate;
        this.donationType = donationType;
        this.paymentMode = paymentMode;
    }

    public Donor(float donationAmount, long receiptNumber, Date donationDate, Type donationType, Type paymentMode, int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, String maritalStatus, String cast, Gender gender, Date registerationDate, String position, String currentStatus) {
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

    public Type getDonationType() {
        return donationType;
    }

    public Type getPaymentMode() {
        return paymentMode;
    }

    public void setDonationAmount(float donationAmount) {
        this.donationAmount = donationAmount;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public void setDonationType(Type donationType) {
        this.donationType = donationType;
    }

    public void setPaymentMode(Type paymentMode) {
        this.paymentMode = paymentMode;
    }

    
    public static String getNameForColumnID(ColumnNames name)
    {
        String columnName =  Member.getNameForColumnID(name);
        if(columnName == null)
        {
            switch(name)
            {
                case ReceiptNumber:
                    return "Receipt Number";
                case Amount:
                    return "Amount";
                case DonationDate:
                    return "Donation Date";
                case DonationType:
                    return "Donation Type";
                case PaymentMode:
                    return "Payment Mode";
            }
        }
        
        return null;
    }
    
    public static Vector getColumnsForDetailsLevel(DetailsLevel level)
    {
        
        Vector columnNames;
        if(level != Member.DetailsLevel.MemberStatement)
        {
            columnNames = Member.getColumnsForDetailsLevel(level);
        }
        else
        {
            columnNames = new Vector();
        }
        
        columnNames.add(getNameForColumnID(ColumnNames.ReceiptNumber));
        columnNames.add(getNameForColumnID(ColumnNames.DonationDate));
        columnNames.add(getNameForColumnID(ColumnNames.Amount));
        columnNames.add(getNameForColumnID(ColumnNames.DonationType));
        columnNames.add(getNameForColumnID(ColumnNames.PaymentMode));
        
        return columnNames;
    }
    
    @Override
    public Object getValueForField(ColumnNames fieldName)
    {
        Object value = super.getValueForField(fieldName);
        if(value == null)
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
            }
        }
        
        return value;
    }
    
    @Override
    public Vector getMemberDetailsForLevel(DetailsLevel detailLevel)
    {
        Vector memberDetails;
        if(detailLevel != Member.DetailsLevel.MemberStatement)
        {
            memberDetails = super.getMemberDetailsForLevel(detailLevel);
        }
        else
        {
            memberDetails = new Vector();
        }
        
        memberDetails.add(getValueForField(ColumnNames.ReceiptNumber));
        memberDetails.add(getValueForField(ColumnNames.DonationDate));
        memberDetails.add(getValueForField(ColumnNames.Amount));
        memberDetails.add(getValueForField(ColumnNames.DonationType));
        memberDetails.add(getValueForField(ColumnNames.PaymentMode));

        return memberDetails;
    }
    
    
    @Override
    public ReportData getDataForDetails(DetailsLevel detailsLevel)
    {
        Vector columnNames = Donor.getColumnsForDetailsLevel(detailsLevel);
        Vector rowData = getMemberDetailsForLevel(detailsLevel);
        
        return new ReportData(rowData, columnNames);
    }
}
