/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class Member {
    
    public enum Gender { Male, Female }
    
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
    
    public static String[] getColumnNames()
    {
        String[] columnNames = {
            "ID",
            "FirstName",
            "MiddleName",
            "LastName",
            "Gender",
            "DateOfBirth",
            "MaritalStatus",
            "ContactNumber",
            "EmailAddress",
            "RegisterationDate",
            "Position",
            "Profession",
            "Cast",
            "SubCast",
            "PermanentAddress",
            "TemporaryAddress",
            "District",
            "BloodGroup",
            "Education",
            "Image"
        };
        
         return columnNames;
    }
    
    public Object[] toArray()
    {
        Object[] objects = new Object[20];
        objects[0] = this.memberID;
        objects[1] = this.firstName != null ? this.firstName : "";
        objects[2] = this.middleName != null ? this.middleName : "";
        objects[3] = this.lastName != null ? this.lastName : "";
        objects[4] = this.gender.toString();
        objects[5] = this.dateOfBirth != null ? DateTime.getFormattedDateSQL(this.dateOfBirth) : "";
        objects[6] = this.maritalStatus != null ? this.maritalStatus : "";
        objects[7] = this.contactNumber != null ? this.contactNumber : "";
        objects[8] = this.emailAddress != null ? this.emailAddress : "";
        objects[9] = this.registerationDate != null ? DateTime.getFormattedDateSQL(this.registerationDate) : "";
        objects[10] = this.position != null ? this.profession : "";
        objects[11] = this.profession != null ? this.profession : "";
        objects[12] = this.cast != null ? this.cast : "";
        objects[13] = this.subCast != null ? this.subCast : "";
        objects[14] = this.permanentAddress != null ? this.permanentAddress : "";
        objects[15] = this.temporaryAddress != null ? this.temporaryAddress : "";
        objects[16] = this.district != null ? this.district : "";
        objects[17] = this.bloodGroup != null ? this.bloodGroup : "";
        objects[18] = this.education != null ? this.education : "";
        objects[19] = this.imagePath != null ? this.imagePath : "";
        
        return objects;
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
