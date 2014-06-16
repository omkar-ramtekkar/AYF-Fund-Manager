/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;

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
    Date    dateOfBirth;
    String  maritalStatus;
    String  cast;
    String  subCast;
    String  district;
    String  bloodGroup;
    Gender  gender;
    byte    age;
    
    String  permanentAddress;
    String  temporaryAddress;
    String  contactNumber;
    String  emailAddress;
    
    String  education;
    Type    profession;
    
    Date    registerationDate;
    String  position;
    String  imagePath;

    public Member(int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, String maritalStatus, String cast, String subCast, String district, String bloodGroup, Gender gender, String permanentAddress, String temporaryAddress, String contactNumber, String emailAddress, String education, Type profession, Date registerationDate, String position, String imagePath) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
        this.cast = cast;
        this.subCast = subCast;
        this.district = district;
        this.bloodGroup = bloodGroup;
        this.gender = gender;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.education = education;
        this.profession = profession;
        this.registerationDate = registerationDate;
        this.position = position;
        this.imagePath = imagePath;
    }

    public Member(int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, String maritalStatus, String cast, Gender gender, Date registerationDate, String position) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
        this.cast = cast;
        this.gender = gender;
        this.registerationDate = registerationDate;
        this.position = position;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getCast() {
        return cast;
    }

    public String getSubCast() {
        return subCast;
    }

    public String getDistrict() {
        return district;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getEducation() {
        return education;
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

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setSubCast(String subCast) {
        this.subCast = subCast;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setRegisterationDate(Date registerationDate) {
        this.registerationDate = registerationDate;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.memberID;
        hash = 47 * hash + (this.dateOfBirth != null ? this.dateOfBirth.hashCode() : 0);
        hash = 47 * hash + (this.gender != null ? this.gender.hashCode() : 0);
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
        if (this.dateOfBirth.equals(other.dateOfBirth)) {
            return false;
        }
        return this.gender == other.gender;
    }
    
    @Override
    public String toString() {
        return "Member{" + "memberID=" + memberID + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", maritalStatus=" + maritalStatus + ", cast=" + cast + ", subCast=" + subCast + ", district=" + district + ", bloodGroup=" + bloodGroup + ", gender=" + gender + ", age=" + age + ", permanentAddress=" + permanentAddress + ", temporaryAddress=" + temporaryAddress + ", contactNumber=" + contactNumber + ", emailAddress=" + emailAddress + ", education=" + education + ", profession=" + profession + ", registerationDate=" + registerationDate + ", position=" + position + ", imagePath=" + imagePath + '}';
    }
        
}
