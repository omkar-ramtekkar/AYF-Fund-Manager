/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.ayf.reports.ReportData;
import org.ayf.util.DateTime;
import org.ayf.util.PreferenceManager;

/**
 *
 * @author om
 */
public class Member extends BaseEntity 
{
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
    
    static Map<DetailsLevel, Vector<ColumnName> > detailLevelVsColumnsMap = null;
    
    static 
    {
         detailLevelVsColumnsMap = new HashMap<DetailsLevel, Vector<ColumnName>>(4);
        init();
    }

    public Member() {
    }
    
    public Member(int memberID, String firstName, String middleName, String lastName, Date dateOfBirth, MaritalStatus maritalStatus, String cast, String subCast, String district, String bloodGroup, Gender gender, String permanentAddress, String temporaryAddress, String contactNumber, String emailAddress, String education, String profession, Date registerationDate, String position, String imagePath, ActiveStatus currentStatus) {
        this.setID(memberID);
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
        this.setID(memberID);
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
    
    static public String getNextRegID()
    {
        String id = PreferenceManager.getIntance().getString("nextRegisterationID", "1");
        return "AUF/" + 
                DateTime.getMonth(DateTime.toSQLDate(new java.util.Date())) + 
                "/" + 
                DateTime.getYear(DateTime.toSQLDate(new java.util.Date())) +
                "/"+id;
    }

    static void init()
    {
        {
            Vector columnNames = new Vector(20);
        
            columnNames.add(ColumnName.UniqueID);
            columnNames.add(ColumnName.FirstName);
            columnNames.add(ColumnName.MiddleName);
            columnNames.add(ColumnName.LastName);
            columnNames.add(ColumnName.Gender);
            columnNames.add(ColumnName.DateOfBirth);
            
            detailLevelVsColumnsMap.put(DetailsLevel.OnlyIDAndName, columnNames);
        }
        {
            Vector columnNames = new Vector(20);
        
            columnNames.add(ColumnName.UniqueID);
            columnNames.add(ColumnName.FirstName);
            columnNames.add(ColumnName.MiddleName);
            columnNames.add(ColumnName.LastName);
            columnNames.add(ColumnName.Gender);
            columnNames.add(ColumnName.DateOfBirth);
            columnNames.add(ColumnName.MaritalStatus);
            columnNames.add(ColumnName.ContactNumber);
            columnNames.add(ColumnName.EmailAddress);
            columnNames.add(ColumnName.Education);
            columnNames.add(ColumnName.Profession);
            columnNames.add(ColumnName.District);
            columnNames.add(ColumnName.RegisterationDate);
            columnNames.add(ColumnName.Position);
            
            detailLevelVsColumnsMap.put(DetailsLevel.Basic, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
        
            columnNames.add((ColumnName.ID));
            columnNames.add((ColumnName.UniqueID));
            columnNames.add((ColumnName.FirstName));
            columnNames.add((ColumnName.MiddleName));
            columnNames.add((ColumnName.LastName));
            columnNames.add((ColumnName.Gender));
            columnNames.add((ColumnName.DateOfBirth));
            
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
            
            detailLevelVsColumnsMap.put(DetailsLevel.AllPersonal, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
        
            columnNames.add((ColumnName.ID));
            columnNames.add((ColumnName.UniqueID));
            columnNames.add((ColumnName.FirstName));
            columnNames.add((ColumnName.MiddleName));
            columnNames.add((ColumnName.LastName));
            columnNames.add((ColumnName.Gender));
            columnNames.add((ColumnName.DateOfBirth));
            columnNames.add((ColumnName.ContactNumber));
            columnNames.add((ColumnName.EmailAddress));
            columnNames.add((ColumnName.Education));
            columnNames.add((ColumnName.Profession));
            
            detailLevelVsColumnsMap.put(DetailsLevel.AllProfessional, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
            columnNames.add((ColumnName.ID));
            columnNames.add((ColumnName.UniqueID));
            columnNames.add((ColumnName.FirstName));
            columnNames.add((ColumnName.MiddleName));
            columnNames.add((ColumnName.LastName));
            columnNames.add((ColumnName.Gender));
            columnNames.add((ColumnName.DateOfBirth));
            columnNames.add((ColumnName.Education));
            columnNames.add((ColumnName.ContactNumber));
            columnNames.add((ColumnName.EmailAddress));
            columnNames.add((ColumnName.Cast));
            columnNames.add((ColumnName.SubCast));                
            columnNames.add((ColumnName.RegisterationDate));
            columnNames.add((ColumnName.Position));
            
            detailLevelVsColumnsMap.put(DetailsLevel.AllSocial, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
            columnNames.add((ColumnName.ID));
            columnNames.add((ColumnName.UniqueID));
            columnNames.add((ColumnName.FirstName));
            columnNames.add((ColumnName.MiddleName));
            columnNames.add((ColumnName.LastName));
            columnNames.add((ColumnName.Gender));
            columnNames.add((ColumnName.DateOfBirth));
            columnNames.add((ColumnName.MaritalStatus));
            columnNames.add((ColumnName.ContactNumber));
            columnNames.add((ColumnName.EmailAddress));
            columnNames.add((ColumnName.Education));
            columnNames.add((ColumnName.Profession));
            columnNames.add((ColumnName.District));
            columnNames.add((ColumnName.RegisterationDate));
            columnNames.add((ColumnName.Position));
            columnNames.add((ColumnName.Cast));
            columnNames.add((ColumnName.SubCast)); 
            columnNames.add((ColumnName.ImagePath)); 
            columnNames.add((ColumnName.BloodGroup));
            columnNames.add((ColumnName.Status));
            
            detailLevelVsColumnsMap.put(DetailsLevel.Database, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
            columnNames.add((ColumnName.UniqueID));
            columnNames.add((ColumnName.FirstName));
            columnNames.add((ColumnName.MiddleName));
            columnNames.add((ColumnName.LastName));
            columnNames.add((ColumnName.ContactNumber));
            columnNames.add((ColumnName.EmailAddress));
            columnNames.add((ColumnName.RegisterationDate));
            columnNames.add((ColumnName.Status));
            
            detailLevelVsColumnsMap.put(DetailsLevel.Search, columnNames);
        }
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
            case ID:
                return "Member ID";
            case UniqueID:
                return "Registeration Number";
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
            default:
                return super.getValueForField(fieldName);
        }
    }
    
    @Override
    public  EditorType getColumnEditorTypeForColumnName(ColumnName columnNames) {
        switch(columnNames)
        {
            case ID:
            case UniqueID:
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
    public Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level) 
    {
        return detailLevelVsColumnsMap.get(level);
    }
    
    @Override
    public Vector<Object> getColumnsForDetailsLevel(DetailsLevel level)
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
    
    public Vector<Object> toDataArray(DetailsLevel level)
    {
        Vector<ColumnName> columnIDs = detailLevelVsColumnsMap.get(level);
        Vector memberDetails = new Vector(columnIDs.size());
        
        for (ColumnName columnID : columnIDs) {
            memberDetails.add(getValueForField(columnID));
        }
        
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
        hash = 47 * hash + this.getID();
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
        if (this.getID() != other.getID()) {
            return false;
        }
        if (this.dateOfBirth.equals(other.dateOfBirth)) {
            return false;
        }
        return this.gender == other.gender;
    }
    
    @Override
    public String toString() {
        StringBuilder memberString = new StringBuilder(30);
        memberString.append(getUniqueID()).append(" ").append(getFirstName()).append(" ").append(getMiddleName()).append(" ").append(getLastName());
        return  memberString.toString();
    }

    @Override
    public void setValueForField(ColumnName fieldName, Object value) {
        if(value == null) return;
        
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
                if(value instanceof Date)
                {
                    setDateOfBirth((Date) value);
                }
                else if(value instanceof Timestamp)
                {
                    setDateOfBirth((new Date(((Timestamp)value).getTime())));
                }
                else
                {
                    setDateOfBirth(DateTime.toSQLDate((String)value));
                }
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
                if(value instanceof Date)
                {
                    setRegisterationDate((Date) value);
                }
                else if(value instanceof Timestamp)
                {
                    setRegisterationDate((new Date(((Timestamp)value).getTime())));
                }
                else
                {
                    setRegisterationDate(DateTime.toSQLDate((String)value));
                }
                break;
            case Position:
                setPosition((String) value);
                break;
            case ImagePath:
                setImagePath((String) value);
                break;
            default:
                super.setValueForField(fieldName, value);
        }
    }
        
}
