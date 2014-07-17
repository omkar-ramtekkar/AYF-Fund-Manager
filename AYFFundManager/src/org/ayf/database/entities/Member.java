/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;
import java.util.Vector;
import org.ayf.reports.ReportData;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class Member extends BaseEntity 
{

    int     memberID;
    String  firstName;
    String  middleName;
    String  lastName;
    Date    dateOfBirth;
    MaritalStatus  maritalStatus;
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
    String  profession;
    
    Date    registerationDate;
    String  position;
    String  imagePath;
    ActiveStatus currentStatus;

    public Member() {
    }
    
    
    public Member(int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, MaritalStatus maritalStatus, String cast, String subCast, String district, String bloodGroup, Gender gender, String permanentAddress, String temporaryAddress, String contactNumber, String emailAddress, String education, String profession, Date registerationDate, String position, String imagePath, ActiveStatus currentStatus) {
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
        this.currentStatus = currentStatus;
    }

    public Member(int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, MaritalStatus maritalStatus, String cast, Gender gender, Date registerationDate, String position, ActiveStatus currentStatus) {
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
        this.currentStatus = currentStatus;
    }

    public MaritalStatus getMaritalStatus() {
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

    public String getProfession() {
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

    public ActiveStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(ActiveStatus currentStatus) {
        this.currentStatus = currentStatus;
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

    public void setProfession(String profession) {
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

    public void setMaritalStatus(MaritalStatus maritalStatus) {
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
    
    public static String getNameForColumnID(ColumnName name)
    {
        switch(name)
        {
            case MemberID:
                return "Member ID";
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
        
        return null;
    }
    
    public Object getValueForField(ColumnName fieldName)
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
    
    @Override
    public  EditorType getColumnEditorTypeForColumnName(ColumnName columnNames) {
        switch(columnNames)
        {
            case MemberID:
            case FirstName:
            case MiddleName:
            case LastName:
            case Cast:
            case SubCast:
            case District:
            case Age:
            case PermanentAddress:
            case TemporaryAddress:
            case ContactNumber:
            case EmailAddress:
            case Education:
            case ImagePath:
                return EditorType.Label;
                
            case DateOfBirth:
            case RegisterationDate:
            case DonationDate:
                return EditorType.Date;
                
            case MaritalStatus:
            case BloodGroup:
            case Gender:
            case Profession:
            case Position:
                return EditorType.ComboBox;
        }
        
        return null;
    }

    
    @Override
    public Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level) {
        Vector<ColumnName> columnNames = new Vector<ColumnName>(20);
        
        columnNames.add((ColumnName.MemberID));
        columnNames.add((ColumnName.FirstName));
        columnNames.add((ColumnName.MiddleName));
        columnNames.add((ColumnName.LastName));
        columnNames.add((ColumnName.Gender));
        columnNames.add((ColumnName.DateOfBirth));
        
        switch(level)
        {
            case Basic:                
                columnNames.add((ColumnName.MaritalStatus));
                columnNames.add((ColumnName.ContactNumber));
                columnNames.add((ColumnName.EmailAddress));
                columnNames.add((ColumnName.Education));
                columnNames.add((ColumnName.Profession));
                columnNames.add((ColumnName.District));
                columnNames.add((ColumnName.RegisterationDate));
                columnNames.add((ColumnName.Position));
                break;
            case AllPersonal:
                columnNames.add((ColumnName.Age));
                columnNames.add((ColumnName.MaritalStatus));
                columnNames.add((ColumnName.BloodGroup));
                columnNames.add((ColumnName.ContactNumber));
                columnNames.add((ColumnName.EmailAddress));
                columnNames.add((ColumnName.Education));
                columnNames.add((ColumnName.Profession));
                columnNames.add((ColumnName.District));
                columnNames.add((ColumnName.PermanentAddress));
                columnNames.add((ColumnName.TemporaryAddress));
                break;
            case AllProfessional:
                columnNames.add((ColumnName.ContactNumber));
                columnNames.add((ColumnName.EmailAddress));
                columnNames.add((ColumnName.Education));
                columnNames.add((ColumnName.Profession));
                break;
            case AllSocial:
                columnNames.add((ColumnName.Education));
                columnNames.add((ColumnName.ContactNumber));
                columnNames.add((ColumnName.EmailAddress));
                columnNames.add((ColumnName.Cast));
                columnNames.add((ColumnName.SubCast));                
                columnNames.add((ColumnName.RegisterationDate));
                columnNames.add((ColumnName.Position));
                break;
        }
        
        columnNames.trimToSize();
        return columnNames;
    }
    
    
    public Vector<Object> getColumnsForDetailsLevel(DetailsLevel level)
    {
        Vector columnNames = new Vector(20);
        
        columnNames.add(getNameForColumnID(ColumnName.MemberID));
        columnNames.add(getNameForColumnID(ColumnName.FirstName));
        columnNames.add(getNameForColumnID(ColumnName.MiddleName));
        columnNames.add(getNameForColumnID(ColumnName.LastName));
        columnNames.add(getNameForColumnID(ColumnName.Gender));
        columnNames.add(getNameForColumnID(ColumnName.DateOfBirth));
        
        switch(level)
        {
            case Basic:                
                columnNames.add(getNameForColumnID(ColumnName.MaritalStatus));
                columnNames.add(getNameForColumnID(ColumnName.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnName.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnName.Education));
                columnNames.add(getNameForColumnID(ColumnName.Profession));
                columnNames.add(getNameForColumnID(ColumnName.District));
                columnNames.add(getNameForColumnID(ColumnName.RegisterationDate));
                columnNames.add(getNameForColumnID(ColumnName.Position));
                break;
            case AllPersonal:
                columnNames.add(getNameForColumnID(ColumnName.Age));
                columnNames.add(getNameForColumnID(ColumnName.MaritalStatus));
                columnNames.add(getNameForColumnID(ColumnName.BloodGroup));
                columnNames.add(getNameForColumnID(ColumnName.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnName.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnName.Education));
                columnNames.add(getNameForColumnID(ColumnName.Profession));
                columnNames.add(getNameForColumnID(ColumnName.District));
                columnNames.add(getNameForColumnID(ColumnName.PermanentAddress));
                columnNames.add(getNameForColumnID(ColumnName.TemporaryAddress));
                break;
            case AllProfessional:
                columnNames.add(getNameForColumnID(ColumnName.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnName.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnName.Education));
                columnNames.add(getNameForColumnID(ColumnName.Profession));
                break;
            case AllSocial:
                columnNames.add(getNameForColumnID(ColumnName.Education));
                columnNames.add(getNameForColumnID(ColumnName.ContactNumber));
                columnNames.add(getNameForColumnID(ColumnName.EmailAddress));
                columnNames.add(getNameForColumnID(ColumnName.Cast));
                columnNames.add(getNameForColumnID(ColumnName.SubCast));                
                columnNames.add(getNameForColumnID(ColumnName.RegisterationDate));
                columnNames.add(getNameForColumnID(ColumnName.Position));
                break;
        }
        columnNames.trimToSize();
        
        return columnNames;
    }
    
    public Vector<Object> toDataArray(DetailsLevel level)
    {
        Vector memberDetails = new Vector(20);
        
        memberDetails.add(getValueForField(ColumnName.MemberID));
        memberDetails.add(getValueForField(ColumnName.FirstName));
        memberDetails.add(getValueForField(ColumnName.MiddleName));
        memberDetails.add(getValueForField(ColumnName.LastName));
        memberDetails.add(getValueForField(ColumnName.Gender));
        memberDetails.add(getValueForField(ColumnName.DateOfBirth));
        
        switch(level)
        {
            case Basic:
                memberDetails.add(getValueForField(ColumnName.MaritalStatus));
                memberDetails.add(getValueForField(ColumnName.ContactNumber));
                memberDetails.add(getValueForField(ColumnName.EmailAddress));
                memberDetails.add(getValueForField(ColumnName.Education));
                memberDetails.add(getValueForField(ColumnName.Profession));
                memberDetails.add(getValueForField(ColumnName.District));
                memberDetails.add(getValueForField(ColumnName.RegisterationDate));
                memberDetails.add(getValueForField(ColumnName.Position));
                break;
            case AllPersonal:
                memberDetails.add(getValueForField(ColumnName.Age));
                memberDetails.add(getValueForField(ColumnName.MaritalStatus));
                memberDetails.add(getValueForField(ColumnName.BloodGroup));
                memberDetails.add(getValueForField(ColumnName.ContactNumber));
                memberDetails.add(getValueForField(ColumnName.EmailAddress));
                memberDetails.add(getValueForField(ColumnName.Education));
                memberDetails.add(getValueForField(ColumnName.Profession));
                memberDetails.add(getValueForField(ColumnName.District));
                memberDetails.add(getValueForField(ColumnName.PermanentAddress));
                memberDetails.add(getValueForField(ColumnName.TemporaryAddress));
                break;
            case AllProfessional:
                memberDetails.add(getValueForField(ColumnName.ContactNumber));
                memberDetails.add(getValueForField(ColumnName.EmailAddress));
                memberDetails.add(getValueForField(ColumnName.Education));
                memberDetails.add(getValueForField(ColumnName.Profession));
                break;
            case AllSocial:
                memberDetails.add(getValueForField(ColumnName.Education));
                memberDetails.add(getValueForField(ColumnName.ContactNumber));
                memberDetails.add(getValueForField(ColumnName.EmailAddress));
                memberDetails.add(getValueForField(ColumnName.Cast));
                memberDetails.add(getValueForField(ColumnName.SubCast));                
                memberDetails.add(getValueForField(ColumnName.RegisterationDate));
                memberDetails.add(getValueForField(ColumnName.Position));
                break;
        }
        
        memberDetails.trimToSize();
        return memberDetails;
    }
    
    
    @Override
    public ReportData getReportDataForDetails(DetailsLevel detailsLevel)
    {
        Vector columnNames = getColumnsForDetailsLevel(detailsLevel);
        Vector rowData = toDataArray(detailsLevel);
        
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
        return "Member{" + "memberID=" + memberID + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", maritalStatus=" + maritalStatus.toString() + ", cast=" + cast + ", subCast=" + subCast + ", district=" + district + ", bloodGroup=" + bloodGroup + ", gender=" + gender + ", age=" + age + ", permanentAddress=" + permanentAddress + ", temporaryAddress=" + temporaryAddress + ", contactNumber=" + contactNumber + ", emailAddress=" + emailAddress + ", education=" + education + ", profession=" + profession + ", registerationDate=" + registerationDate + ", position=" + position + ", imagePath=" + imagePath + '}';
    }

    @Override
    public void setValueForField(ColumnName fieldName, Object value) {
        switch(fieldName)
        {
             case FirstName:
                setFirstName((String) value);
                 break;
            case MiddleName:
                setMiddleName((String) value);
                break;
            case LastName:
                setLastName((String) value);
                break;
            case DateOfBirth:
                setDateOfBirth(DateTime.toSQLDate((String)value));
                break;
            case MaritalStatus:
                setMaritalStatus(MaritalStatus.valueOf(value.toString()));
                break;
            case Cast:
                setCast((String) value);
                break;
            case SubCast:
                setSubCast((String) value);
                break;
            case District:
                setDistrict((String) value);
                break;
            case BloodGroup:
                setBloodGroup((String) value);
                break;
            case Gender:
                setGender(Gender.valueOf(value.toString()));
                break;
            case PermanentAddress:
                setPermanentAddress((String) value);
                break;
            case TemporaryAddress:
                setTemporaryAddress((String) value);
                break;
            case ContactNumber:
                setContactNumber((String) value);
                break;
            case EmailAddress:
                setEmailAddress((String) value);
                break;
            case Education:
                setEducation((String) value);
                break;
            case Profession:
                setProfession((String) value);
                break;
            case RegisterationDate:
                setRegisterationDate(DateTime.toSQLDate((String)value));
                break;
            case Position:
                setPosition((String) value);
                break;
            case ImagePath:
                setImagePath((String) value);
                break;
        }
    }
        
}
