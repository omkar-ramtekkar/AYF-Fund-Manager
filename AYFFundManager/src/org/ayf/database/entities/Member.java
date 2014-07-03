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
    
    public enum DetailsLevel
    {
        OnlyIDAndName, Basic, AllPersonal, AllProfessional, AllSocial,  Complete  
    }
    
    public enum ColumnNames
    {
        MemberID, FirstName, MiddleName, LastName, DateOfBirth, MaritalStatus, 
        Cast, SubCast, District, BloodGroup, Gender, Age, PermanentAddress, TemporaryAddress,
        ContactNumber, EmailAddress, Education, Profession, RegisterationDate, Position,
        ImagePath, ReceiptNumber, Amount, DonationDate, DonationType, PaymentMode
    }

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
    
    public static String getNameForColumnID(ColumnNames name)
    {
        switch(name)
        {
            case MemberID:
                return "ID";
            case FirstName:
                return "First Name";
            case MiddleName:
                return "Middle Name";
            case LastName:
                return "Last Name";
            case DateOfBirth:
                return "Date of Birth";
            case MaritalStatus:
                return "Marital Status";
            case Cast:
                return "Cast";
            case SubCast:
                return "Sub Cast";
            case District:
                return "City";
            case BloodGroup:
                return "Blood Group";
            case Gender:
                return "Gender";
            case Age:
                return "Age";
            case PermanentAddress:
                return "Permanent Address";
            case TemporaryAddress:
                return "Temporary Address";
            case ContactNumber:
                return "Contact Number";
            case EmailAddress:
                return "Email Address";
            case Education:
                return "Education";
            case Profession:
                return "Profession";
            case RegisterationDate:
                return "Registeration Date";
            case Position:
                return "Position in Foundation";
            case ImagePath:
                return "Image Location";
        }
        
        return null;
    }
    
    public Object getValueForField(ColumnNames fieldName)
    {
        switch(fieldName)
        {
            case MemberID:
                return getMemberID();
            case FirstName:
                return getFirstName();
            case MiddleName:
                return getMiddleName();
            case LastName:
                return getLastName();
            case DateOfBirth:
                return getDateOfBirth();
            case MaritalStatus:
                return getMaritalStatus();
            case Cast:
                return getCast();
            case SubCast:
                return getSubCast();
            case District:
                return getDistrict();
            case BloodGroup:
                return getBloodGroup();
            case Gender:
                return getGender();
            case Age:
                return getAge();
            case PermanentAddress:
                return getPermanentAddress();
            case TemporaryAddress:
                return getTemporaryAddress();
            case ContactNumber:
                return getContactNumber();
            case EmailAddress:
                return getEmailAddress();
            case Education:
                return getEducation();
            case Profession:
                return getProfession();
            case RegisterationDate:
                return getRegisterationDate();
            case Position:
                return getPosition();
            case ImagePath:
                return getImagePath();
        }
        
        return null;
    }
    
    public static Vector getColumnsForDetailsLevel(DetailsLevel level)
    {
        Vector columnNames = new Vector();
        
        columnNames.add(getNameForColumnID(ColumnNames.MemberID));
        columnNames.add(getNameForColumnID(ColumnNames.FirstName));
        columnNames.add(getNameForColumnID(ColumnNames.MiddleName));
        columnNames.add(getNameForColumnID(ColumnNames.LastName));
        columnNames.add(getNameForColumnID(ColumnNames.Gender));
        columnNames.add(getNameForColumnID(ColumnNames.DateOfBirth));
        
        switch(level)
        {
            case Basic:
                columnNames.add(getNameForColumnID(ColumnNames.District));
                columnNames.add(getNameForColumnID(ColumnNames.MaritalStatus));
                columnNames.add(getNameForColumnID(ColumnNames.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnNames.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnNames.Education));
                columnNames.add(getNameForColumnID(ColumnNames.Profession));
                columnNames.add(getNameForColumnID(ColumnNames.RegisterationDate));
                columnNames.add(getNameForColumnID(ColumnNames.Position));
                break;
            case AllPersonal:
                columnNames.add(getNameForColumnID(ColumnNames.Age));
                columnNames.add(getNameForColumnID(ColumnNames.MaritalStatus));
                columnNames.add(getNameForColumnID(ColumnNames.BloodGroup));
                columnNames.add(getNameForColumnID(ColumnNames.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnNames.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnNames.Education));
                columnNames.add(getNameForColumnID(ColumnNames.Profession));
                columnNames.add(getNameForColumnID(ColumnNames.District));
                columnNames.add(getNameForColumnID(ColumnNames.PermanentAddress));
                columnNames.add(getNameForColumnID(ColumnNames.TemporaryAddress));
                break;
            case AllProfessional:
                columnNames.add(getNameForColumnID(ColumnNames.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnNames.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnNames.Education));
                columnNames.add(getNameForColumnID(ColumnNames.Profession));
                break;
            case AllSocial:
                columnNames.add(getNameForColumnID(ColumnNames.Education));
                columnNames.add(getNameForColumnID(ColumnNames.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnNames.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnNames.Cast));
                columnNames.add(getNameForColumnID(ColumnNames.SubCast));                
                columnNames.add(getNameForColumnID(ColumnNames.RegisterationDate));
                columnNames.add(getNameForColumnID(ColumnNames.Position));
                break;
        }
        
        return columnNames;
    }
    
    public Vector getMemberDetailsForLevel(DetailsLevel detailLevel)
    {
        Vector memberDetails = new Vector();
        
        memberDetails.add(getValueForField(ColumnNames.MemberID));
        memberDetails.add(getValueForField(ColumnNames.FirstName));
        memberDetails.add(getValueForField(ColumnNames.MiddleName));
        memberDetails.add(getValueForField(ColumnNames.LastName));
        memberDetails.add(getValueForField(ColumnNames.Gender));
        memberDetails.add(getValueForField(ColumnNames.DateOfBirth));
        
        switch(detailLevel)
        {
            case Basic:
                memberDetails.add(getValueForField(ColumnNames.MaritalStatus));
                memberDetails.add(getValueForField(ColumnNames.ContactNumber));
                memberDetails.add(getValueForField(ColumnNames.EmailAddress));
                memberDetails.add(getValueForField(ColumnNames.Education));
                memberDetails.add(getValueForField(ColumnNames.Profession));
                memberDetails.add(getValueForField(ColumnNames.District));
                memberDetails.add(getValueForField(ColumnNames.RegisterationDate));
                memberDetails.add(getValueForField(ColumnNames.Position));
                break;
            case AllPersonal:
                memberDetails.add(getValueForField(ColumnNames.Age));
                memberDetails.add(getValueForField(ColumnNames.MaritalStatus));
                memberDetails.add(getValueForField(ColumnNames.BloodGroup));
                memberDetails.add(getValueForField(ColumnNames.ContactNumber));
                memberDetails.add(getValueForField(ColumnNames.EmailAddress));
                memberDetails.add(getValueForField(ColumnNames.Education));
                memberDetails.add(getValueForField(ColumnNames.Profession));
                memberDetails.add(getValueForField(ColumnNames.District));
                memberDetails.add(getValueForField(ColumnNames.PermanentAddress));
                memberDetails.add(getValueForField(ColumnNames.TemporaryAddress));
                break;
            case AllProfessional:
                memberDetails.add(getValueForField(ColumnNames.ContactNumber));
                memberDetails.add(getValueForField(ColumnNames.EmailAddress));
                memberDetails.add(getValueForField(ColumnNames.Education));
                memberDetails.add(getValueForField(ColumnNames.Profession));
                break;
            case AllSocial:
                memberDetails.add(getValueForField(ColumnNames.Education));
                memberDetails.add(getValueForField(ColumnNames.ContactNumber));
                memberDetails.add(getValueForField(ColumnNames.EmailAddress));
                memberDetails.add(getValueForField(ColumnNames.Cast));
                memberDetails.add(getValueForField(ColumnNames.SubCast));                
                memberDetails.add(getValueForField(ColumnNames.RegisterationDate));
                memberDetails.add(getValueForField(ColumnNames.Position));
                break;
        }
        
        return memberDetails;
    }
    
    
    public ReportData getDataForDetails(DetailsLevel detailsLevel)
    {
        Vector columnNames = Member.getColumnsForDetailsLevel(detailsLevel);
        Vector rowData = getMemberDetailsForLevel(detailsLevel);
        
        return new ReportData(rowData, columnNames);
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
