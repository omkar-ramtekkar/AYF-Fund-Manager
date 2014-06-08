/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf;

import backend.model.Donor;
import backend.model.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;


/**
 *
 * @author oramtekkar
 */
public class DatabaseManager {
    
    public static final String DONATION_TYPE_TABLE_NAME     = "DonationType";
    public static final String EXPENSE_TYPE_TABLE_NAME      = "ExpenseType";
    public static final String PROFESSION_TYPE_TABLE_NAME   = "ProfessionType";
    public static final String MEMBER_TYPE_TABLE_NAME       = "MemberType";
    public static final String DONATIONS_TABLE_NAME         = "Donations";
    public static final String EXPENSES_TABLE_NAME          = "Expenses";
    public static final String MEMBER_TABLE_NAME            = "Members";
    public static final String DAILYCASH_TABLE_NAME         = "DailyCash";
    
    private static ArrayList<Type> PROFESSION_TYPES;
    private static ArrayList<Type> DONATION_TYPES;
    private static ArrayList<Type> MEMBER_TYPES;
    private static ArrayList<Type> EXPENSE_TYPES;
    
    private static void intializeDatabaseManager()
    {
        try {
            // Load MS accces driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Failed to load database Driver", ERROR_MESSAGE);
            System.exit(0);
        }
        
        getProfessionTypes();
        getExpenseTypes();
        getDonationTypes();
        getMemberTypes();
    }
    
    static 
    {
        DatabaseManager.intializeDatabaseManager();
    }

    private static Connection createConnection(){
        String url = null;
        if(System.getProperty("os.name").toLowerCase().contains("win"))
        {
            url = "jdbc:ucanaccess://c:/database.accdb;lockmdb=true;ignorecase=true";
        }
        else
        {
            url = "jdbc:ucanaccess:///Volumes/MACINTOSH 2/Projects/AadimYouthFoundation/AYF-Fund-Manager/AYFFundManager/database.accdb;lockmdb=true;ignorecase=true";
        }
        // specify url, username, pasword - make sure these are valid 
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Database connection error", ERROR_MESSAGE);
            System.exit(0);
        }

        System.out.println("Connection Succesfull");
        
        return conn;
    }
    
    private static boolean closeConnection(Connection connection){
        boolean bSuccess = true;
        try {
            connection.close();
        } catch (SQLException ex) {
            bSuccess = false;
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bSuccess;
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Type> getDonationTypes()
    {
        if(DONATION_TYPES == null)
        {
            DONATION_TYPES = getTypesFromTable(DONATION_TYPE_TABLE_NAME);
        }
        
        return DONATION_TYPES;
    }
    
    public static Type getProfessionTypeForID(int id)
    {
        return getTypeFromID(id, PROFESSION_TYPES);
    }
    
    public static Type getProfessionTypeForName(String profession)
    {
        return getTypeFromValue(profession, PROFESSION_TYPES);
    }
    
    public static Type getMemberTypeForID(int id)
    {
        return getTypeFromID(id, MEMBER_TYPES);
    }
    
    public static Type getMemberTypeForName(String profession)
    {
        return getTypeFromValue(profession, MEMBER_TYPES);
    }
    
    public static Type getDonationTypeForID(int id)
    {
        return getTypeFromID(id, DONATION_TYPES);
    }
    
    public static Type getDonationTypeForName(String profession)
    {
        return getTypeFromValue(profession, DONATION_TYPES);
    }
    
    public static Type getExpenseTypeForID(int id)
    {
        return getTypeFromID(id, EXPENSE_TYPES);
    }
    
    public static Type getExpenseTypeForName(String profession)
    {
        return getTypeFromValue(profession, EXPENSE_TYPES);
    }
    
    private static Type getTypeFromID(int id, ArrayList<Type> types)
    {
        Type returnType = null;
        for (Type type : types) {
            if(type.getId() == id)
            {
                returnType = type;
                break;
            }
        }
        
        if(returnType == null)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, "getTypeFromID: type not found for id={0}. TypesValues="+types, id);
            
        }
        return returnType;
    }
    
    private static Type getTypeFromValue(String typeValue, ArrayList<Type> types)
    {
        Type returnType = null;
        for (Type type : types) {
            if(type.getStringValue().equals(typeValue))
            {
                returnType = type;
                break;
            }
        }
        
        if(returnType == null)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, "getTypeFromValue: type not found for stringValue={0}. TypesValues="+types, typeValue);   
        }
        return returnType;
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Type> getExpenseTypes()
    {
        if(EXPENSE_TYPES == null)
        {
            EXPENSE_TYPES = getTypesFromTable(EXPENSE_TYPE_TABLE_NAME);
        }
        
        return EXPENSE_TYPES;
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Type> getMemberTypes()
    {
        if(MEMBER_TYPES == null)
        {
            MEMBER_TYPES = getTypesFromTable(MEMBER_TYPE_TABLE_NAME);
        }
        
        return MEMBER_TYPES;        
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Type> getProfessionTypes()
    {
        if(PROFESSION_TYPES == null)
        {
            PROFESSION_TYPES = getTypesFromTable(PROFESSION_TYPE_TABLE_NAME);
        }
        
        return PROFESSION_TYPES;  
    }
    
    public static ArrayList<Type> getTypesFromTable(String tableName)
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getTypesFromTable({0})", tableName);
        
        ArrayList<Type> types = new ArrayList<>();
        
        try {
            Connection connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getTypesFromTable({0}) : Column Count - " + rs.getMetaData().getColumnCount() , tableName);
            
            while(rs.next())
            {
                int id = Integer.parseInt(rs.getObject("ID").toString());
                String typeValue = rs.getObject("Type").toString();
                Type type = new Type(id, typeValue);
                types.add(type);
            }
            
            ps.close();
            rs.close();
            closeConnection(connection);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table" + tableName, ERROR_MESSAGE);
        }
        
        return types;
    }
    
    public static ArrayList<Member> getRegisteredMembers()
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getRegisteredMembers");
        
        ArrayList<Member> members = new ArrayList<>();
        
        try {
            Connection connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + MEMBER_TABLE_NAME);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getRegisteredMembers : Column Count - {0}", rs.getMetaData().getColumnCount());
            
            while(rs.next())
            {
                int     memberID        = Integer.parseInt(rs.getObject("ID").toString());
                String  firstName       = rs.getObject("FirstName").toString();
                String  middleName      = rs.getObject("MiddleName").toString();
                String  lastName        = rs.getObject("LastName").toString();
                String  permanentAddress= rs.getObject("PermanenetAddress").toString();
                String  temporaryAddress= rs.getObject("TemporaryAddress").toString();
                String  contactNumber    = rs.getObject("ContactNumber").toString();
                String  emailAddress    = rs.getObject("EmailAddress").toString();
                Date    registerationDate = (Date) rs.getObject("RegisterationDate");
                String  position        = rs.getObject("Position").toString();
                String  profession      = rs.getObject("Profession").toString();
                Date    dateOfBirth     = (Date) rs.getObject("DateOfBirth");
                Member.Gender gender    = rs.getObject("Gender").equals("Male") ? Member.Gender.MALE : Member.Gender.FEMALE;
                String imagePath        = rs.getObject("Image").toString();
                
                members.add(new Member(memberID, 
                        firstName, 
                        middleName, 
                        lastName, 
                        permanentAddress, 
                        temporaryAddress, 
                        contactNumber, 
                        dateOfBirth, 
                        registerationDate, 
                        position, 
                        getProfessionTypeForName(profession), 
                        emailAddress, 
                        gender, 
                        imagePath));
            }
            
            ps.close();
            rs.close();
            closeConnection(connection);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        }
        
        return members;
    }
    
    public static Member getMemberWithID(int id)
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getMemberWithID{0}", id);
        
        Member member = null;
        
        try {
            Connection connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ? WHERE ID=?");
            ps.setObject(0, MEMBER_TYPE_TABLE_NAME);
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getRegisteredMembers : Column Count - {0}", rs.getMetaData().getColumnCount());
            
            while(rs.next())
            {
                int     memberID        = Integer.parseInt(rs.getObject("ID").toString());
                String  firstName       = rs.getObject("FirstName").toString();
                String  middleName      = rs.getObject("MiddleName").toString();
                String  lastName        = rs.getObject("LastName").toString();
                String  permanentAddress= rs.getObject("PermanenetAddress").toString();
                String  temporaryAddress= rs.getObject("TemporaryAddress").toString();
                String  contactNumber    = rs.getObject("ContactNumber").toString();
                String  emailAddress    = rs.getObject("EmailAddress").toString();
                Date    registerationDate = (Date) rs.getObject("RegisterationDate");
                String  position        = rs.getObject("Position").toString();
                String  profession      = rs.getObject("Profession").toString();
                Date    dateOfBirth     = (Date) rs.getObject("DateOfBirth");
                Member.Gender gender    = rs.getObject("Gender").equals("Male") ? Member.Gender.MALE : Member.Gender.FEMALE;
                String imagePath        = rs.getObject("Image").toString();

                if(member != null)
                {
                    Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getMemberWithID : Multiple members found with same memberID={0}", id);
                    break;
                }
                
                member = new Member(memberID, 
                        firstName, 
                        middleName, 
                        lastName, 
                        permanentAddress, 
                        temporaryAddress, 
                        contactNumber, 
                        dateOfBirth, 
                        registerationDate, 
                        position, 
                        getProfessionTypeForName(profession), 
                        emailAddress, 
                        gender, 
                        imagePath);
            }
            
            ps.close();
            rs.close();
            closeConnection(connection);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        }
        
        return member;
    }
    
    
    public static ArrayList<Donor> getDonors()
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getDonors");
        
        ArrayList<Donor> donors = new ArrayList<>();
        
        try {
            Connection connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + DONATIONS_TABLE_NAME);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getDonors : Column Count - {0}", rs.getMetaData().getColumnCount());
            
            while(rs.next())
            {
                int     memberID        = rs.getObject("MemberID").toString().length() > 0 ? Integer.parseInt(rs.getObject("MemberID").toString()) : Integer.MAX_VALUE;
                String  firstName       = rs.getObject("FirstName").toString();
                String  middleName      = rs.getObject("MiddleName").toString();
                String  lastName        = rs.getObject("LastName").toString();
                String  permanentAddress= rs.getObject("PermanenetAddress").toString();
                String  temporaryAddress= rs.getObject("TemporaryAddress").toString();
                String  contactNumber   = rs.getObject("ContactNumber").toString();
                String  emailAddress    = rs.getObject("EmailAddress").toString();                
                String  profession      = rs.getObject("Profession").toString();
                Date    dateOfBirth     = (Date) rs.getObject("DateOfBirth");
                Member.Gender gender    = rs.getObject("Gender").equals("Male") ? Member.Gender.MALE : Member.Gender.FEMALE;
                float   donationAmount  = Float.parseFloat(rs.getObject("Amount").toString());
                long    receiptNumber   = Long.parseLong(rs.getObject("ID").toString());
                Date    donationDate    = (Date) rs.getObject("DonationDate");
                String donationType     = rs.getObject("Type").toString();
                String paymentMode      = rs.getObject("PaymentMode").toString();
                
                //Member properties
                String imagePath = null;
                Date registerationDate = null;
                String position = null;
                if(memberID != Integer.MAX_VALUE)
                {
                    Member member = getMemberWithID(memberID);
                    if(member != null)
                    {
                        registerationDate = member.getRegisterationDate();
                        imagePath = member.getImagePath();
                        position = member.getPosition();
                    }
                }
                
                donors.add(new Donor(donationAmount, 
                        receiptNumber, 
                        donationDate, 
                        getTypeFromValue(donationType, DONATION_TYPES), 
                        null, //TODO: - getTypeFromValue(paymentMode, PAYMENT_MODES), 
                        memberID, 
                        firstName, 
                        middleName, 
                        lastName, 
                        permanentAddress, 
                        temporaryAddress, 
                        contactNumber, 
                        dateOfBirth, 
                        registerationDate,
                        position, 
                        getProfessionTypeForName(profession), 
                        emailAddress, 
                        gender, 
                        imagePath));
            }
            
            ps.close();
            rs.close();
            closeConnection(connection);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        }
        
        return donors;
    }
    
    public static boolean registerMember(Member member)
    {
        boolean bRegistered = false; 
        if(member != null)
        {
            try 
            {
                Connection conn = createConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO ? (FirstName, MiddleName, LastName, PermanenetAddress, TemporaryAddress, ContactNumber, EmailAddress, RegisterationDate, Position, Profession, DateOfBirth, Gender, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
                
                ps.setObject(0, MEMBER_TABLE_NAME);
                ps.setString(1, member.getFirstName());
                ps.setString(2, member.getMiddleName());
                ps.setString(3, member.getLastName());
                ps.setString(4, member.getPermanentAddress());
                ps.setString(5, member.getTemporaryAddress());
                ps.setString(6, member.getContactNumber());
                ps.setString(7, member.getEmailAddress());
                ps.setDate(8, new java.sql.Date(member.getRegisterationDate().getTime()));
                ps.setString(9, member.getPosition());
                ps.setString(10, member.getProfession().getStringValue());
                ps.setDate(11, new java.sql.Date(member.getDateOfBirth().getTime()));
                ps.setString(12, member.getGender() == Member.Gender.MALE ? "Male" : "Female" );
                ps.setString(13, member.getImagePath());
                
                ps.executeUpdate();
                
                bRegistered = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to register member ", ERROR_MESSAGE);
            }
        }
        
        return bRegistered;
    }
    
    public static boolean performDonate(Donor donor)
    {
        boolean bDonateSuccess = false; 
        if(donor != null)
        {
            try 
            {
                Connection conn = createConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO ? (FirstName, MiddleName, LastName, PermanenetAddress, TemporaryAddress, ContactNumber, EmailAddress, Profession, DateOfBirth, Gender, Amount, DonationDate, Type, PaymentMode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
                ps.setObject(0, DONATIONS_TABLE_NAME);
                ps.setString(1, donor.getFirstName());
                ps.setString(2, donor.getMiddleName());
                ps.setString(3, donor.getLastName());
                ps.setString(4, donor.getPermanentAddress());
                ps.setString(5, donor.getTemporaryAddress());
                ps.setString(6, donor.getContactNumber());
                ps.setString(7, donor.getEmailAddress());
                ps.setString(8, donor.getProfession().getStringValue());
                ps.setDate(9, new java.sql.Date(donor.getDateOfBirth().getTime()));
                ps.setString(10, donor.getGender() == Member.Gender.MALE ? "Male" : "Female" );
                
                
                //Donor attributes
                ps.setFloat(11, donor.getDonationAmount());
                ps.setDate(12, new java.sql.Date(donor.getDonationDate().getTime()));
                ps.setString(13, donor.getDonationType().getStringValue());
                ps.setString(14, donor.getPaymentMode().getStringValue());
                
                ps.executeUpdate();
                
                bDonateSuccess = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to save donation", ERROR_MESSAGE);
            }
        }
        
        return bDonateSuccess;
    }
}
