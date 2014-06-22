/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;
import java.util.Arrays;
import java.util.Vector;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class Donor extends Member
{
    private float   donationAmount;
    private long    receiptNumber;
    private Date    donationDate;
    private Type    donationType;
    private Type    paymentMode;

    public Donor(float donationAmount, long receiptNumber, Date donationDate, Type donationType, Type paymentMode, int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, String maritalStatus, String cast, String subCast, String district, String bloodGroup, Gender gender, String permanentAddress, String temporaryAddress, String contactNumber, String emailAddress, String education, Type profession, Date registerationDate, String position, String imagePath) {
        super(memberID, firstName, middleName, lastName, dateOfBirth, maritalStatus, cast, subCast, district, bloodGroup, gender, permanentAddress, temporaryAddress, contactNumber, emailAddress, education, profession, registerationDate, position, imagePath);
        this.donationAmount = donationAmount;
        this.receiptNumber = receiptNumber;
        this.donationDate = donationDate;
        this.donationType = donationType;
        this.paymentMode = paymentMode;
    }

    public Donor(float donationAmount, long receiptNumber, Date donationDate, Type donationType, Type paymentMode, int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, String maritalStatus, String cast, Gender gender, Date registerationDate, String position) {
        super(memberID, firstName, middleName, lastName, dateOfBirth, maritalStatus, cast, gender, registerationDate, position);
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

    @Override
    public Object[] toArray()
    {
        Vector v = new Vector(Arrays.asList(super.toArray()));
        
        v.add(5, getReceiptNumber());
        v.add(6, getDonationAmount());
        v.add(7, getDonationDate() != null ? DateTime.getFormattedDateSQL(getDonationDate()) : "");
        v.add(8, getDonationType() != null ? getDonationType() : "");
        v.add(9, getPaymentMode() != null ? getPaymentMode() : "");
        return v.toArray();
    }
    
    public static String[] getColumnNames()
    {
        Vector v = new Vector(Arrays.asList(Member.getColumnNames()));            
        v.add(5, "ReceiptNumber");
        v.add(6, "Amount");
        v.add(7, "DonationDate");
        v.add(8,"DonationType");
        v.add(9, "PaymentMode");
        
        return(String[]) v.toArray();
    }
}
