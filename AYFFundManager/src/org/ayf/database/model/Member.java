/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author om
 */
public class Member {
    
    public enum Gender { MALE, FEMALE }
    
    int     memberID;
    String  firstName;
    String  middleName;
    String  lastName;
    String  permanentAddress;
    String  temporaryAddress;
    String  contactNumber;
    byte    age;
    Date    dateOfBirth;
    Date    registerationDate;
    String  position;
    Type    profession;
    String  emailAddress;
    Gender  gender;
    String  imagePath;

    public Member(int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, Date registerationDate, Gender gender) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.registerationDate = registerationDate;
        this.gender = gender;
    }

    public Member(int memberID, String firstName, String middleName, String lastName, String permanentAddress, String temporaryAddress, String contactNumber, Date dateOfBirth, Date registerationDate, String position, Type profession, String emailAddress, Gender gender, String imagePath) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
        this.registerationDate = registerationDate;
        this.position = position;
        this.profession = profession;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.imagePath = imagePath;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public byte getAge() {
        return age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getRegisterationDate() {
        return registerationDate;
    }

    public String getPosition() {
        return position;
    }

    public Type getProfession() {
        return profession;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Gender getGender() {
        return gender;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setProfession(Type profession) {
        this.profession = profession;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.memberID;
        hash = 17 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 17 * hash + Objects.hashCode(this.gender);
        hash = 17 * hash + Objects.hashCode(this.firstName);
        hash = 17 * hash + Objects.hashCode(this.middleName);
        hash = 17 * hash + Objects.hashCode(this.lastName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        if (this.memberID != other.memberID) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Member{" + "memberID=" + 
                memberID + ", firstName=" + 
                firstName + ", middleName=" + 
                middleName + ", lastName=" + 
                lastName + ", permanentAddress=" + 
                permanentAddress + ", temporaryAddress=" + 
                temporaryAddress + ", contactNumber=" + 
                contactNumber + ", age=" + 
                age + ", dateOfBirth=" + 
                dateOfBirth + ", registerationDate=" + 
                registerationDate + ", position=" + 
                position + ", profession=" + 
                profession.getStringValue() + ", emailAddress=" + 
                emailAddress + ", gender=" + 
                gender + ", imagePath=" + 
                imagePath + '}';
    }    
}
