/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

import com.sun.tools.corba.se.idl.InvalidArgument;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ayf.reports.ReportData;
import org.ayf.util.DateTime;
import org.ayf.util.NumberUtil;
import org.ayf.util.PreferenceManager;

/**
 *
 * @author om
 */
public class Donor extends Member
{
    private float       donationAmount;
    private  long       receiptNumber;
    private Date        donationDate;
    private String      donationType;
    private String      paymentMode;
    private String      memberUniqueID;
    
    public static final String SUBSCRIPTION_TYPE = "Subscription";
    
    static Map<DetailsLevel, Vector<ColumnName> > detailLevelVsColumnsMap = null;
    
    static 
    {
        detailLevelVsColumnsMap = new HashMap<DetailsLevel, Vector<ColumnName>>(4);
        init();
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
            
            detailLevelVsColumnsMap.put(DetailsLevel.Basic, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
        
            columnNames.add((ColumnName.UniqueID));
            columnNames.add((ColumnName.FirstName));
            columnNames.add((ColumnName.MiddleName));
            columnNames.add((ColumnName.LastName));
            columnNames.add((ColumnName.Gender));
            columnNames.add((ColumnName.DateOfBirth));
            
            columnNames.add((ColumnName.Age));
            columnNames.add((ColumnName.MaritalStatus));
            columnNames.add((ColumnName.ContactNumber));
            columnNames.add((ColumnName.EmailAddress));
            columnNames.add((ColumnName.Education));
            columnNames.add((ColumnName.Profession));
            columnNames.add((ColumnName.PermanentAddress));
            columnNames.add((ColumnName.TemporaryAddress));
            
            detailLevelVsColumnsMap.put(DetailsLevel.AllPersonal, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
        
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
            columnNames.add((ColumnName.UniqueID));
            columnNames.add((ColumnName.FirstName));
            columnNames.add((ColumnName.MiddleName));
            columnNames.add((ColumnName.LastName));
            columnNames.add((ColumnName.Gender));
            columnNames.add((ColumnName.DateOfBirth));
            columnNames.add((ColumnName.Education));
            columnNames.add((ColumnName.ContactNumber));
            columnNames.add((ColumnName.EmailAddress));
            columnNames.add((ColumnName.Position));
            
            detailLevelVsColumnsMap.put(DetailsLevel.AllSocial, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
            columnNames.add(ColumnName.UniqueID);
            columnNames.add(ColumnName.MemberUniqueID);
            columnNames.add(ColumnName.FirstName);
            columnNames.add(ColumnName.MiddleName);
            columnNames.add(ColumnName.LastName);
            columnNames.add(ColumnName.DateOfBirth);
            columnNames.add(ColumnName.Gender); 
            columnNames.add(ColumnName.ReceiptNumber);
            columnNames.add(ColumnName.DonationDate);
            columnNames.add(ColumnName.Amount);
            columnNames.add(ColumnName.DonationType);
            columnNames.add(ColumnName.PaymentMode);
            columnNames.add(ColumnName.PermanentAddress);
            columnNames.add(ColumnName.TemporaryAddress);
            columnNames.add(ColumnName.ContactNumber);
            columnNames.add(ColumnName.EmailAddress);
            columnNames.add(ColumnName.Profession);
            
            
            detailLevelVsColumnsMap.put(DetailsLevel.Database, columnNames);
        }
        
        {
            Vector columnNames = new Vector(20);
            columnNames.add((ColumnName.UniqueID));
            columnNames.add((ColumnName.FirstName));
            columnNames.add((ColumnName.MiddleName));
            columnNames.add((ColumnName.LastName));
            columnNames.add((ColumnName.ReceiptNumber));
            columnNames.add((ColumnName.DonationDate));
            columnNames.add((ColumnName.Amount));
            columnNames.add((ColumnName.DonationType));
            columnNames.add((ColumnName.PaymentMode));
            columnNames.add((ColumnName.ContactNumber));
            columnNames.add((ColumnName.EmailAddress));
            
            detailLevelVsColumnsMap.put(DetailsLevel.Search, columnNames);
        }
        {
            Vector columnNames = new Vector(20);
            columnNames.add((ColumnName.ReceiptNumber));
            columnNames.add((ColumnName.DonationDate));
            columnNames.add((ColumnName.Amount));
            columnNames.add((ColumnName.DonationType));
            columnNames.add((ColumnName.PaymentMode));
            
            detailLevelVsColumnsMap.put(DetailsLevel.MemberStatement, columnNames);
        }
        
    }
    
    
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
    
    public Donor(Member member)
    {
        initializeDonorWithMember(member);
    }
    
    static public String getNextUniqueID()
    {
        try {
            String id = PreferenceManager.getIntance().getString(PreferenceManager.NEXT_DONATION_ID, "1");
            return "AYF/don/" +
                    NumberUtil.getFormattedNumber(DateTime.getMonth(DateTime.getToday()) + 1) +
                    "/" +
                    DateTime.getYear(DateTime.getToday()) +
                    "/"+ NumberUtil.getFormattedNumber(Integer.parseInt(id));
        } catch (InvalidArgument ex) {
            return "";
        }
    }

    public String getMemberUniqueID() {
        return memberUniqueID;
    }

    public void setMemberUniqueID(String memberUniqueID) {
        this.memberUniqueID = memberUniqueID;
        
        /*
        if(getMemberUniqueID()!= null && getMemberUniqueID().length() > 0)
        {
            Vector<Object> value = new Vector<Object>(1);
            value.add(getMemberUniqueID());
            
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
        }*/
    }
    
    public boolean isSubscription()
    {
        if(getDonationType() != null)
            return SUBSCRIPTION_TYPE.equalsIgnoreCase(getDonationType());
        else
            return false;
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

    public void setReceiptNumber(long receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
    
    
    public String getNameForColumnID(ColumnName name)
    {
        switch(name)
        {
            case UniqueID:
                return "Donation ID";
            case MemberUniqueID:
                return "Member ID";
            default:
                return super.getNameForColumnID(name);
        }
    }
    
    
    public Vector getColumnsForDetailsLevel(DetailsLevel level)
    {
        return super.getColumnsForDetailsLevel(level, detailLevelVsColumnsMap);
    }
    
    public Vector<ColumnName> getColumnIDsForDetailLevel(DetailsLevel level)
    {
        return super.getColumnIDsForDetailLevel(level, detailLevelVsColumnsMap);
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
            case MemberUniqueID:
                return getMemberUniqueID();
            default:
                return super.getValueForField(fieldName);
        }
    }
    

    @Override
    public void setValueForField(ColumnName fieldName, Object value) 
    {
        if(value == null) value = "";
        
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
            case ReceiptNumber:
                try {
                    setReceiptNumber(Integer.parseInt(value.toString()));
                } catch (NumberFormatException numberFormatException) {
                    setReceiptNumber(-1);
                }
                break;
            case DonationType:
                setDonationType((String) value);
                break;
            case PaymentMode:
                setPaymentMode((String) value);
                break;
            case MemberUniqueID:
                setMemberUniqueID(value.toString());
                break;
            default:
                super.setValueForField(fieldName, value);
        }
    }
    
    
    
    @Override
    public Vector toDataArray(DetailsLevel detailLevel)
    {
        return super.toDataArray(detailLevel, detailLevelVsColumnsMap);
    }
    
    
    @Override
    public ReportData getReportDataForDetails(DetailsLevel detailsLevel)
    {
        Vector<Object> columnNames = getColumnsForDetailsLevel(detailsLevel);
        Vector<Object> rowData = toDataArray(detailsLevel);
        
        return new ReportData(rowData, columnNames);
    }

    public void initializeDonorWithMember(Member member) {
        if(member != null)
        {
            Vector<ColumnName> columnIDs = member.getColumnIDsForDetailLevel(BaseEntity.DetailsLevel.Database);
            
            for (ColumnName columnName : columnIDs) 
            {
                setValueForField(columnName, member.getValueForField(columnName));
            }
            
            setMemberUniqueID(member.getUniqueID());
        }
    }
}
