/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.model;

import java.util.Date;

/**
 *
 * @author om
 */
public class Donor extends Member
{
    private float   donationAmount;
    private long    receiptNumber;
    private Date    donationDate;
    private Type donationType;
    private Type paymentMode;

    public Donor(float donationAmount, long receiptNumber, Date donationDate, Type donationType, Type paymentMode, int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, Date registerationDate, Gender gender) {
        super(memberID, firstName, middleName, lastName, dateOfBirth, registerationDate, gender);
        this.donationAmount = donationAmount;
        this.receiptNumber = receiptNumber;
        this.donationDate = donationDate;
        this.donationType = donationType;
        this.paymentMode = paymentMode;
    }

    public Donor(float donationAmount, long receiptNumber, Date donationDate, Type donationType, Type paymentMode, int memberID, String firstName, String middleName, String lastName, String permanentAddress, String temporaryAddress, String contactNumber, Date dateOfBirth, Date registerationDate, String position, Type profession, String emailAddress, Gender gender, String imagePath) {
        super(memberID, firstName, middleName, lastName, permanentAddress, temporaryAddress, contactNumber, dateOfBirth, registerationDate, position, profession, emailAddress, gender, imagePath);
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
    
    
}
