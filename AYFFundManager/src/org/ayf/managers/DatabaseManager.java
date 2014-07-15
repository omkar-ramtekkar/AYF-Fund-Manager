/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import org.ayf.database.entities.Donor;
import org.ayf.database.entities.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.ayf.database.entities.Type;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import net.ucanaccess.jdbc.UcanaccessDriver;
import org.ayf.database.entities.CashFlow;
import org.ayf.database.entities.Expense;
import org.ayf.reports.ReportData;


/**
 *
 * @author oramtekkar
 */
public class DatabaseManager {
    
    public static final String DONATION_TYPE_TABLE_NAME     = "DonationType";
    public static final String EXPENSE_TYPE_TABLE_NAME      = "ExpenseType";
    public static final String PROFESSION_TYPE_TABLE_NAME   = "ProfessionType";
    public static final String POSITION_TYPE_TABLE_NAME     = "PositionType";
    public static final String PAYMENT_MODE_TYPE_TABLE_NAME = "PaymentModeTypes";

    public static final String CASHFLOW_STATUS_TYPE_TABLE_NAME    = "CashFlowStatusType";
    public static final String MEMBER_TYPE_TABLE_NAME       = "MemberType";
    public static final String DONATIONS_TABLE_NAME         = "Donations";
    public static final String EXPENSES_TABLE_NAME          = "Expenses";
    public static final String MEMBER_TABLE_NAME            = "Members";
    public static final String CASHFLOWS_TABLE_NAME         = "CashFlows";
        
    private static ArrayList<Type> PROFESSION_TYPES;
    private static ArrayList<Type> DONATION_TYPES;
    private static ArrayList<Type> MEMBER_TYPES;
    private static ArrayList<Type> EXPENSE_TYPES;
    private static ArrayList<Type> CASHFLOW_TYPES;
    public static  ArrayList<Type> POSITION_TYPES;
    public static  ArrayList<Type> PAYMENT_MODE_TYPES;
    
    
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
        getCashFlowStatusTypes();
        
    }
    
    static 
    {
        DatabaseManager.intializeDatabaseManager();
    }
    
    private static void dump(ResultSet rs,String exName)
                    throws SQLException {
        System.out.println("-------------------------------------------------");
        System.out.println();
        System.out.println(exName+" result:");
        System.out.println();
        while (rs.next()) {
                System.out.print("| ");
                int j=rs.getMetaData().getColumnCount();
                for (int i = 1; i <=j ; ++i) {
                        Object o = rs.getObject(i);
                        System.out.print(o + " | ");
                }
                System.out.println();
                System.out.println();
        }
    }
    
    private static Connection createConnection()
    {
        String url = null;
        if(System.getProperty("os.name").toLowerCase().contains("win"))
        {
            url = UcanaccessDriver.URL_PREFIX + "D:/database.accdb" + ";newDatabaseVersion=V2007";
        }
        else
        {
            url = UcanaccessDriver.URL_PREFIX + "/Volumes/MACINTOSH 2/Projects/AadimYouthFoundation/AYF-Fund-Manager/AYFFundManager/database.accdb"+ ";newDatabaseVersion=V2007";
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
        catch(NullPointerException ex)
        {
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
    
    public static Type getCashFlowTypeForName(String status)
    {
        return getTypeFromValue(status, CASHFLOW_TYPES);
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
    
    
    public static ArrayList<String> typesToStrings(ArrayList<Type> types)
    {
        ArrayList<String> strings = new ArrayList<String>(types.size());
        
        for (Type type : types)
        {
            strings.add(type.getStringValue());
        }
        
        return strings;
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
        try
        {
        for (Type type : types) {
            if(type.getStringValue().equals(typeValue))
            {
                returnType = type;
                break;
            }
        }
        }
        catch(NullPointerException ex){}
        catch(Exception ex) {}
        
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
    
    public static ArrayList<Type> getPositionTypes()
    {
        if(POSITION_TYPES == null)
        {
            POSITION_TYPES = getTypesFromTable(POSITION_TYPE_TABLE_NAME);
        }
        
        return POSITION_TYPES;
    }
    
    
    public static ArrayList<Type> getPaymentModeTypes()
    {
        if(PAYMENT_MODE_TYPES == null)
        {
            PAYMENT_MODE_TYPES = getTypesFromTable(PAYMENT_MODE_TYPE_TABLE_NAME);
        }
        
        return PAYMENT_MODE_TYPES;
    }
    
    public static ArrayList<Type> getCashFlowStatusTypes()
    {
        if(CASHFLOW_TYPES == null)
        {
            CASHFLOW_TYPES = getTypesFromTable(CASHFLOW_STATUS_TYPE_TABLE_NAME);
        }
        
        return CASHFLOW_TYPES;  
    }
    
    
    public static ArrayList<Type> getTypesFromTable(String tableName)
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getTypesFromTable({0})", tableName);
        
        ArrayList<Type> types = new ArrayList();
        Connection connection = null;
        try {
            connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getTypesFromTable({0}) : Column Count - " + rs.getMetaData().getColumnCount() , tableName);
            
            while(rs.next())
            {
                int id = rs.getInt("ID");
                String typeValue = rs.getString("Type");
                Type type = new Type(id, typeValue);
                types.add(type);
            }
            
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table" + tableName, ERROR_MESSAGE);
        }
        finally
        {
            if(connection != null) closeConnection(connection);
        }
        return types;
    }
    
    public static ArrayList<Member> getRegisteredMembers()
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getRegisteredMembers");
        
        ArrayList<Member> members = new ArrayList();
        Connection connection = null;
        try {
            connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + MEMBER_TABLE_NAME);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getRegisteredMembers : Column Count - {0}", rs.getMetaData().getColumnCount());
            
            while(rs.next())
            {
                int     memberID        = rs.getInt("ID");
                String  firstName       = rs.getString("FirstName");
                String  middleName      = rs.getString("MiddleName");
                String  lastName        = rs.getString("LastName");
                String  permanentAddress= rs.getString("PermanentAddress");
                String  temporaryAddress= rs.getString("TemporaryAddress");
                String  contactNumber   = rs.getString("ContactNumber");
                String  emailAddress    = rs.getString("EmailAddress");
                Date    registerationDate = rs.getDate("RegisterationDate");
                String  position        = rs.getString("Position");
                String  profession      = rs.getString("Profession");
                Date    dateOfBirth     = rs.getDate("DateOfBirth");
                String genderString     = rs.getString("Gender");
                Member.MaritalStatus  maritalStatus   = rs.getString("MaritalStatus").equalsIgnoreCase(Member.MaritalStatus.Married.toString()) ? Member.MaritalStatus.Married : Member.MaritalStatus.Single;
                String  cast            = rs.getString("Cast");
                String  subCast         = rs.getString("SubCast");
                String  district        = rs.getString("District");
                String  bloodGroup      = rs.getString("BloodGroup");
                String  education       = rs.getString("Education");
                String status           = rs.getString("Status");
                Member.ActiveStatus currentStatus = Member.ActiveStatus.Unknown;
                if(status != null)
                {
                    currentStatus = rs.getString("Status").equalsIgnoreCase(Member.ActiveStatus.Active.toString()) ? Member.ActiveStatus.Active : Member.ActiveStatus.Inactive;
                }
                
                Member.Gender gender     = genderString != null ? (genderString.equalsIgnoreCase(Member.Gender.Male.toString())? Member.Gender.Male : Member.Gender.Female) : Member.Gender.Male;
                String imagePath        = rs.getString("Image");

                members.add(new Member(memberID, firstName, middleName, lastName, dateOfBirth, maritalStatus, cast, subCast, district, bloodGroup, gender, permanentAddress, temporaryAddress, contactNumber, emailAddress, education, profession, registerationDate, position, imagePath, currentStatus));
            }
            
            ps.close();
            rs.close();            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        }
        finally
        {
            if(connection != null) closeConnection(connection);
        }
        return members;
    }
    
    public static Member getMemberWithID(int id)
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getMemberWithID{0}", id);
        
        Member member = null;
        
        Connection connection = null;
        try {
            connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+ MEMBER_TABLE_NAME +" WHERE ID=?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getRegisteredMembers : Column Count - {0}", rs.getMetaData().getColumnCount());
            
            while(rs.next())
            {
                int     memberID        = rs.getInt("ID");
                String  firstName       = rs.getString("FirstName");
                String  middleName      = rs.getString("MiddleName");
                String  lastName        = rs.getString("LastName");
                String  permanentAddress= rs.getString("PermanentAddress");
                String  temporaryAddress= rs.getString("TemporaryAddress");
                String  contactNumber   = rs.getString("ContactNumber");
                String  emailAddress    = rs.getString("EmailAddress");
                Date    registerationDate = rs.getDate("RegisterationDate");
                String  position        = rs.getString("Position");
                String  profession      = rs.getString("Profession");
                Date    dateOfBirth     = rs.getDate("DateOfBirth");
                String genderString     = rs.getString("Gender");
                Member.Gender gender    = genderString != null ? (genderString.equals("Male") ? Member.Gender.Male : Member.Gender.Female) : Member.Gender.Male;
                String imagePath        = rs.getString("Image");

                Member.MaritalStatus  maritalStatus   = rs.getString("MaritalStatus").equalsIgnoreCase(Member.MaritalStatus.Married.toString()) ? Member.MaritalStatus.Married : Member.MaritalStatus.Single;
                String  cast            = rs.getString("Cast");
                String  subCast         = rs.getString("SubCast");
                String  district        = rs.getString("District");
                String  bloodGroup      = rs.getString("BloodGroup");
                String  education       = rs.getString("Education");
                Member.ActiveStatus currentStatus    = rs.getString("Status").equalsIgnoreCase(Member.ActiveStatus.Active.toString()) ? Member.ActiveStatus.Active : Member.ActiveStatus.Inactive;
                
                if(member != null)
                {
                    Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getMemberWithID : Multiple members found with same memberID={0}", id);
                    break;
                }
                
                member = new Member(memberID, firstName, middleName, lastName, dateOfBirth, maritalStatus, cast, subCast, district, bloodGroup, gender, permanentAddress, temporaryAddress, contactNumber, emailAddress, education, profession, registerationDate, position, imagePath, currentStatus);
            }
            
            ps.close();
            rs.close();
            
        } catch (NullPointerException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        }
        finally
        {
            if(connection != null) closeConnection(connection);
        }
        
        return member;
    }
    
    
    public static ArrayList<Donor> getDonors()
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getDonors");
        
        ArrayList<Donor> donors = new ArrayList();
        Connection connection = null;
        try {
            connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + DONATIONS_TABLE_NAME);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getDonors : Column Count - {0}", rs.getMetaData().getColumnCount());
            
            while(rs.next())
            {
                int     memberID        = rs.getInt("MemberID");
                String  firstName       = rs.getString("FirstName");
                String  middleName      = rs.getString("MiddleName");
                String  lastName        = rs.getString("LastName");
                String  permanentAddress= rs.getString("PermanentAddress");
                String  temporaryAddress= rs.getString("TemporaryAddress");
                String  contactNumber   = rs.getString("ContactNumber");
                String  emailAddress    = rs.getString("EmailAddress");                
                String  profession      = rs.getString("Profession");
                Date    dateOfBirth     = rs.getDate("DateOfBirth");
                String genderString     = rs.getString("Gender");
                Member.Gender gender    = genderString != null ? (genderString.equals("Male") ? Member.Gender.Male : Member.Gender.Female) : Member.Gender.Male;
                float   donationAmount  = rs.getFloat("Amount");
                long    receiptNumber   = rs.getLong("ReceiptNumber");
                Date    donationDate    = rs.getDate("DonationDate");
                String donationType     = rs.getString("DonationType");
                String paymentMode      = rs.getString("PaymentMode");
                
                //Member properties
                String imagePath        = null;
                Date registerationDate  = null;
                String position         = null;
                Member.MaritalStatus  maritalStatus   = Member.MaritalStatus.Married;
                String  cast            = null;
                String  subCast         = null;
                String  district        = null;
                String  bloodGroup      = null;
                String  education       = null;
                Member.ActiveStatus currentStatus    = Member.ActiveStatus.Unknown;
                if(memberID != Integer.MAX_VALUE)
                {
                    Member member = getMemberWithID(memberID);
                    if(member != null)
                    {
                        registerationDate = member.getRegisterationDate();
                        imagePath = member.getImagePath();
                        position = member.getPosition();
                        maritalStatus = member.getMaritalStatus();
                        cast = member.getCast();
                        subCast = member.getSubCast();
                        district = member.getDistrict();
                        bloodGroup = member.getBloodGroup();
                        education = member.getEducation();
                        currentStatus = member.getCurrentStatus();
                    }
                }
                
                donors.add(new Donor(donationAmount, receiptNumber, donationDate, donationType, paymentMode, memberID, firstName, middleName, lastName, dateOfBirth, maritalStatus, cast, subCast, district, bloodGroup, gender, permanentAddress, temporaryAddress, contactNumber, emailAddress, education, profession, registerationDate, position, imagePath, currentStatus));
            }
            
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        }
        finally
        {
            if(connection != null) closeConnection(connection);
        }
        return donors;
    }
    
    public static boolean registerMember(Member member)
    {
        boolean bRegistered = false; 
        Connection conn = null;
        if(member != null)
        {
            try 
            {
                conn = createConnection();
                
                String sql = "INSERT INTO "+ MEMBER_TABLE_NAME + " (FirstName, MiddleName, LastName, PermanentAddress, TemporaryAddress, ContactNumber, EmailAddress, RegisterationDate, Position, Profession, DateOfBirth, Gender, Image, MaritalStatus, Cast, SubCast, District, BloodGroup, Education, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, member.getFirstName());
                ps.setString(2, member.getMiddleName());
                ps.setString(3, member.getLastName());
                ps.setString(4, member.getPermanentAddress());
                ps.setString(5, member.getTemporaryAddress());
                ps.setString(6, member.getContactNumber());
                ps.setString(7, member.getEmailAddress());
                ps.setDate(8, member.getRegisterationDate());
                ps.setString(9, member.getPosition());
                ps.setString(10, member.getProfession() != null ? member.getProfession() : null);
                ps.setDate(11, member.getDateOfBirth());
                ps.setString(12, member.getGender() == Member.Gender.Male ? "Male" : "Female" );
                ps.setString(13, member.getImagePath());
                ps.setString(14, member.getMaritalStatus().toString());
                ps.setString(15, member.getCast());
                ps.setString(16, member.getSubCast());
                ps.setString(17, member.getDistrict());
                ps.setString(18, member.getBloodGroup());
                ps.setString(19, member.getEducation());
                ps.setString(20, Member.ActiveStatus.Active.toString());
                
                bRegistered = ps.executeUpdate() > 0;
                conn.commit();
                               
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to register member ", ERROR_MESSAGE);
            }
            finally
            {
                closeConnection(conn);
            }
        }
        
        return bRegistered;
    }
    
    public static boolean performDonate(Donor donor)
    {
        boolean bDonateSuccess = false; 
        Connection conn = null;
        if(donor != null)
        {
            try 
            {
                conn = createConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO "+DONATIONS_TABLE_NAME+" (FirstName, MiddleName, LastName, PermanentAddress, TemporaryAddress, ContactNumber, EmailAddress, Profession, DateOfBirth, Gender, Amount, DonationDate, DonationType, PaymentMode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1, donor.getFirstName());
                ps.setString(2, donor.getMiddleName());
                ps.setString(3, donor.getLastName());
                ps.setString(4, donor.getPermanentAddress());
                ps.setString(5, donor.getTemporaryAddress());
                ps.setString(6, donor.getContactNumber());
                ps.setString(7, donor.getEmailAddress());
                ps.setString(8, donor.getProfession() != null ? donor.getProfession() : null);
                ps.setDate(9, donor.getDateOfBirth());
                ps.setString(10, donor.getGender() == Member.Gender.Male ? "Male" : "Female" );
                
                
                //Donor attributes
                ps.setFloat(11, donor.getDonationAmount());
                ps.setDate(12, donor.getDonationDate());
                ps.setString(13, donor.getDonationType() != null ? donor.getDonationType() : null);
                ps.setString(14, donor.getPaymentMode()!= null ? donor.getPaymentMode() : null);
                
                bDonateSuccess = ps.executeUpdate() > 0;
                
                ps.close();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to save donation", ERROR_MESSAGE);
            }
            finally
            {
                if(conn != null) closeConnection(conn);
            }
        }
        
        return bDonateSuccess;
    }
    
    public static ReportData getDonationBySubscription()
    {
        boolean bRegistered = false; 
        Connection conn = null;
        
        Vector rows = new Vector();
        Vector columns = new Vector();
        
        columns.add("DonationType");
        columns.add("TotalAmount");
        
        try 
        {
            conn = createConnection();

            String sql = "SELECT DonationType, sum(Amount) as TotalAmount FROM " + DONATIONS_TABLE_NAME + " GROUP BY DonationType";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            
            while(rs.next())
            {
                Vector rowData = new Vector();
                rowData.add(rs.getString("DonationType"));
                rowData.add(rs.getDouble("TotalAmount"));
                
                rows.add(rowData);
            }
            
            rs.close();
            statement.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to get donations by type ", ERROR_MESSAGE);
        }
        finally
        {
            if(conn != null) { closeConnection(conn); }
        }
        
        return new ReportData(rows, columns, null);
    }

    
    public static ArrayList<Expense> getExpenses() {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getExpenses");
        
        ArrayList<Expense> expenses = new ArrayList();
        Connection connection = null;
        try 
        {
            connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + EXPENSES_TABLE_NAME);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getExpenses : Column Count - {0}", rs.getMetaData().getColumnCount());
            
            while(rs.next())
            {
                int     expenseID       = rs.getInt("ID");
                double  amount          = rs.getDouble("Amount");
                Date    date            = rs.getDate("ExpenseDate");
                String  expenseType     = rs.getString("Type");
                String  description     = rs.getString("Description");
                int     memberID        = rs.getInt("ResponsibleMemberID");
                Member  member          = null;
                
                if(memberID != Integer.MAX_VALUE)
                {
                    member = getMemberWithID(memberID);
                }
                
                expenses.add(new Expense(expenseID, getExpenseTypeForName(expenseType), date, amount, description, member));
            }
            
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        }
        finally
        {
            if(connection != null) closeConnection(connection);
        }
        return expenses;
    }
    
    
     public static ArrayList<CashFlow> getCashFlows() 
     {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getCashFlows");
        
        ArrayList<CashFlow> cashFlows = new ArrayList();
        Connection connection = null;
        try 
        {
            connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + CASHFLOWS_TABLE_NAME);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getCashFlows : Column Count - {0}", rs.getMetaData().getColumnCount());
            
            while(rs.next())
            {
                int     expenseID       = rs.getInt("ID");
                Date    date            = rs.getDate("TransactionDate");
                String  status          = rs.getString("Status");
                String  description     = rs.getString("Description");
                
                cashFlows.add(new CashFlow(expenseID, date, getCashFlowTypeForName(status), description));
            }
            
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to fetch data from table", ERROR_MESSAGE);
        }
        finally
        {
            if(connection != null) closeConnection(connection);
        }
        return cashFlows;
     }
     
     public static ReportData getMemberStatement(int memberID)
     {
        Connection conn = null;
    
        Vector columns = Donor.getColumnsForDetailsLevel(Member.DetailsLevel.MemberStatement);    
        Vector rowData = new Vector();
        
        try 
        {
            conn = createConnection();

            String sql = "SELECT * FROM " + DONATIONS_TABLE_NAME + " WHERE MemberID=? ORDER BY DonationDate";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, memberID);
            
            ResultSet rs = statement.executeQuery();
            
            while(rs.next())
            {                
                int     memberIDTest    = rs.getInt("MemberID");
                float   donationAmount  = rs.getFloat("Amount");
                long    receiptNumber   = rs.getLong("ReceiptNumber");
                Date    donationDate    = rs.getDate("DonationDate");
                String donationType     = rs.getString("DonationType");
                String paymentMode      = rs.getString("PaymentMode");
               
                Donor donation = new Donor(donationAmount, receiptNumber, donationDate, donationType, paymentMode, memberID, null, null, null, null, null, null, null, null, null, null);
                rowData.add(donation.getMemberDetailsForLevel(Member.DetailsLevel.MemberStatement));
            }
            
            rs.close();
            statement.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to get donations by type ", ERROR_MESSAGE);
        }
        finally
        {
            if(conn != null) { closeConnection(conn); }
        }
        
        return new ReportData(rowData, columns, Member.getColumnIDsForDetailLevel(Member.DetailsLevel.MemberStatement));
     }
}
