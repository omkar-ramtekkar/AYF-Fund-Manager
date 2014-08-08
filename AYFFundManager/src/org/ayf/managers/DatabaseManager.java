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
import org.ayf.database.entities.BaseEntity;
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
    public static final String STATUS_TYPE_TABLE_NAME       = "StatusType";

    public static final String CASHFLOW_STATUS_TYPE_TABLE_NAME    = "StatusType";
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
    public static  ArrayList<Type> STATUS_TYPES;
    
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
    
    public static ArrayList<Type> getStatusTypes() {
        
        if(STATUS_TYPES == null)
        {
            STATUS_TYPES = getTypesFromTable(STATUS_TYPE_TABLE_NAME);
        }
        
        return STATUS_TYPES;
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
    
    public static ArrayList<BaseEntity> getAllEntities(Class<?> entityClass)
    {
        ArrayList<BaseEntity> entities = new ArrayList<BaseEntity>();
        
        if(entityClass != null)
        {
            Connection conn = null;
            conn = createConnection();
                
            StringBuilder sqlQuery = new StringBuilder("SELECT * FROM ");
            String tableName = null;
                
            if(entityClass.equals(Member.class))
            {
                tableName = MEMBER_TABLE_NAME;
            }
            else if(entityClass.equals(Donor.class))
            {
                tableName = DONATIONS_TABLE_NAME;
            }
            else if(entityClass.equals(Expense.class))
            {
                tableName = EXPENSES_TABLE_NAME;
            }
            else if(entityClass.equals(CashFlow.class))
            {
                tableName = CASHFLOWS_TABLE_NAME;
            }
            
            if(tableName != null)
            {
                sqlQuery.append(tableName);
                sqlQuery.trimToSize();
                
                ResultSet rs = null;
                Statement statement = null;
                try 
                {
                    statement = conn.createStatement();
                    rs = statement.executeQuery(sqlQuery.toString());
                    BaseEntity dummyEntity = (BaseEntity) entityClass.newInstance();
                    Vector<BaseEntity.ColumnName> columns = dummyEntity.getColumnIDsForDetailLevel(BaseEntity.DetailsLevel.Database);

                    while(rs.next())
                    {
                        BaseEntity entity = (BaseEntity) entityClass.newInstance();
                        
                        for (BaseEntity.ColumnName columnName : columns) 
                        {
                            try
                            {
                                Object value = rs.getObject(columnName.toString());
                                entity.setValueForField(columnName, value);
                            }catch(Exception ex){ ex.printStackTrace(); }
                        }
                        
                        int id = rs.getInt(BaseEntity.ColumnName.ID.toString());
                        
                        entity.setValueForField(BaseEntity.ColumnName.ID, id);
                        entities.add(entity);
                    }
                    
                    if(rs != null) rs.close();
                    if(statement != null) statement.close();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally
                {
                    closeConnection(conn);
                }
            }
            
        }
        
        return entities;
    }
    
    public static ArrayList<BaseEntity> getEntitiesWithCondition(Vector<BaseEntity.ColumnName> conditionColumns, Vector<Object> conditionValues, Class<?> entityClass)
    {
        ArrayList<BaseEntity> entities = new ArrayList<BaseEntity>();
        
        if(entityClass != null)
        {
            Connection conn = null;
            conn = createConnection();
                
            StringBuilder sqlQuery = new StringBuilder("SELECT * FROM ");
            String tableName = null;
                
            if(entityClass.equals(Member.class))
            {
                tableName = MEMBER_TABLE_NAME;
            }
            else if(entityClass.equals(Donor.class))
            {
                tableName = DONATIONS_TABLE_NAME;
            }
            else if(entityClass.equals(Expense.class))
            {
                tableName = EXPENSES_TABLE_NAME;
            }
            else if(entityClass.equals(CashFlow.class))
            {
                tableName = CASHFLOWS_TABLE_NAME;
            }
            
            if(tableName != null)
            {
                sqlQuery.append(tableName);
                sqlQuery.append(" WHERE ");
                
                StringBuilder conditionString = new StringBuilder();
                
                for(int i=0; i<conditionValues.size(); ++i)
                {
                    if(conditionColumns.lastElement() == conditionColumns.get(i))
                    {
                        conditionString.append(conditionColumns.get(i)).append("=").append(conditionValues.get(i));
                    }
                    else
                    {
                        conditionString.append(conditionColumns.get(i)).append("=").append(conditionValues.get(i)).append(" AND ");
                    }
                }
                
                sqlQuery.append(conditionString);
                
                sqlQuery.trimToSize();
                
                ResultSet rs = null;
                Statement statement = null;
                try 
                {
                    statement = conn.createStatement();
                    rs = statement.executeQuery(sqlQuery.toString());
                    BaseEntity dummyEntity = (BaseEntity) entityClass.newInstance();
                    Vector<BaseEntity.ColumnName> columns = dummyEntity.getColumnIDsForDetailLevel(BaseEntity.DetailsLevel.Database);

                    while(rs.next())
                    {
                        BaseEntity entity = (BaseEntity) entityClass.newInstance();
                        
                        for (BaseEntity.ColumnName columnName : columns) 
                        {
                            try
                            {
                                Object value = rs.getObject(columnName.toString());
                                entity.setValueForField(columnName, value);
                            }catch(Exception ex){ ex.printStackTrace(); }
                        }
                        
                        int id = rs.getInt(BaseEntity.ColumnName.ID.toString());
                        
                        entity.setValueForField(BaseEntity.ColumnName.ID, id);
                        entities.add(entity);
                    }
                    
                    if(rs != null) rs.close();
                    if(statement != null) statement.close();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally
                {
                    closeConnection(conn);
                }
            }
            
        }
        
        return entities;
    }
    
    
    public static boolean updateEntities(Vector<BaseEntity> entities, Class<?> entityClass)
    {
        boolean bUpdated = true; 
        Connection conn = null;
        if(entities != null && entities.size() > 0)
        {
            try 
            {
                conn = createConnection();
                
                StringBuffer sqlQuery = new StringBuffer("UPDATE ");
                String tableName = null;
                
                if(entityClass.equals(Member.class))
                {
                    tableName = MEMBER_TABLE_NAME;
                }
                else if(entityClass.equals(Donor.class))
                {
                    tableName = DONATIONS_TABLE_NAME;
                }
                else if(entityClass.equals(Expense.class))
                {
                    tableName = EXPENSES_TABLE_NAME;
                }
                else if(entityClass.equals(CashFlow.class))
                {
                    tableName = CASHFLOWS_TABLE_NAME;
                }
                else
                {
                    return false;
                }
                
                sqlQuery.append(tableName);
                sqlQuery.append(" SET ");
                
                Vector<BaseEntity.ColumnName> columns = entities.get(0).getColumnIDsForDetailLevel(BaseEntity.DetailsLevel.Database);
                
                for (BaseEntity.ColumnName columnName : columns) {
                    if(columns.lastElement() == columnName)
                    {
                        sqlQuery.append(columnName.toString() + "=?");
                    }
                    else
                    {
                        sqlQuery.append(columnName.toString() + "=?,");
                    }
                }
                
                sqlQuery.append(" WHERE ").append(BaseEntity.ColumnName.ID.toString()).append("=?");
                
                sqlQuery.trimToSize();
                
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, sqlQuery.toString(), "");
                
                PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
                
                for (BaseEntity entity : entities)
                {
                    int i=1;
                    for (BaseEntity.ColumnName columnName : columns) 
                    {
                        Object value = entity.getValueForField(columnName);
                        
                        if(value instanceof String)
                        {
                            ps.setString(i, (String) value);
                        }
                        else if(value instanceof Double)
                        {
                            ps.setDouble(i, ((Double) value));
                        }
                        else if(value instanceof Integer)
                        {
                            ps.setInt(i, (Integer) value);
                        }
                        else if(value instanceof Float)
                        {
                            ps.setFloat(i, (Float) value);
                        }
                        else if(value instanceof Long)
                        {
                            ps.setFloat(i, (Long) value);
                        }
                        else if(value instanceof Date || columnName.toString().contains("Date"))
                        {
                            ps.setDate(i, (Date) value);
                        }
                        else
                        {
                            if(value != null)
                            {
                                ps.setString(i, value.toString());
                            }
                            else
                            {
                                ps.setString(i, "");
                            }
                        }
                        
                        ++i;
                    }
                    
                    ps.setInt(i, entity.getID());
                    
                    bUpdated &= ps.executeUpdate() > 0;
                }
                
                conn.commit();
                ps.close();
                               
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to register member ", ERROR_MESSAGE);
            }
            finally
            {
                closeConnection(conn);
            }
        }
        
        return bUpdated;
    }
    
    public static boolean insertEntities(Vector<BaseEntity> entities, Class<?> entityClass)
    {
        boolean bInserted = true; 
        Connection conn = null;
        if(entities != null && entities.size() > 0)
        {
            try 
            {
                conn = createConnection();
                
                StringBuffer sqlQuery = new StringBuffer("INSERT INTO ");
                String tableName = null;
                
                if(entityClass.equals(Member.class))
                {
                    tableName = MEMBER_TABLE_NAME;
                }
                else if(entityClass.equals(Donor.class))
                {
                    tableName = DONATIONS_TABLE_NAME;
                }
                else if(entityClass.equals(Expense.class))
                {
                    tableName = EXPENSES_TABLE_NAME;
                }
                else if(entityClass.equals(CashFlow.class))
                {
                    tableName = CASHFLOWS_TABLE_NAME;
                }
                else
                {
                    return false;
                }
                
                sqlQuery.append(tableName);
                
                StringBuilder valuesString = new StringBuilder(100);
                StringBuffer  valuePlacement = new StringBuffer(entities.size());
                
                Vector<BaseEntity.ColumnName> columns = entities.get(0).getColumnIDsForDetailLevel(BaseEntity.DetailsLevel.Database);
                
                for (BaseEntity.ColumnName columnName : columns) {
                    if(columns.lastElement() == columnName)
                    {
                        valuesString.append(columnName.toString());
                        valuePlacement.append("?");
                    }
                    else
                    {
                        valuesString.append(columnName.toString() + ",");
                        valuePlacement.append("?,");
                    }
                }
                
                sqlQuery.append(" (").append(valuesString.toString()).append(")");
                sqlQuery.append("VALUES (").append(valuePlacement.toString()).append(")");
                
                sqlQuery.trimToSize();
                
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, sqlQuery.toString(), "");
                
                PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
                
                for (BaseEntity entity : entities)
                {
                    int i=1;
                    for (BaseEntity.ColumnName columnName : columns) 
                    {
                        Object value = entity.getValueForField(columnName);
                        
                        if(value instanceof String)
                        {
                            ps.setString(i, (String) value);
                        }
                        else if(value instanceof Double)
                        {
                            ps.setDouble(i, ((Double) value));
                        }
                        else if(value instanceof Integer)
                        {
                            ps.setInt(i, (Integer) value);
                        }
                        else if(value instanceof Float)
                        {
                            ps.setFloat(i, (Float) value);
                        }
                        else if(value instanceof Long)
                        {
                            ps.setFloat(i, (Long) value);
                        }
                        else if(value instanceof Date)
                        {
                            ps.setDate(i, (Date) value);
                        }
                        else
                        {
                            if(value != null)
                            {
                                ps.setString(i, value.toString());
                            }
                            else
                            {
                                ps.setString(i, "");
                            }
                        }
                        
                        ++i;
                    }
                    
                    bInserted &= ps.executeUpdate() > 0;
                    
                    ApplicationManager.getSharedManager().entityDidAdded(entity);
                }
                
                conn.commit();
                ps.close();
                               
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to register member ", ERROR_MESSAGE);
            }
            finally
            {
                closeConnection(conn);
            }
        }
        
        return bInserted;
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
        
        return new ReportData(rows, columns);
    }
    
    public static ReportData getMemberStatement(String memberRegNumber)
    {
        Vector<BaseEntity.ColumnName> column = new Vector<BaseEntity.ColumnName>();
        column.add(BaseEntity.ColumnName.UniqueID);
        
        Vector<Object> value = new Vector<Object>();
        value.add(memberRegNumber);
        
        ArrayList<BaseEntity> entities =  getEntitiesWithCondition(column, value, Donor.class);
        
        if(entities != null && entities.isEmpty() == false)
        {
            return new ReportData(new Vector<BaseEntity>(entities), BaseEntity.DetailsLevel.MemberStatement, Donor.class);
        }
        else
        {
            return null;
        }
     }

    public static BaseEntity getEntityWithUniqueID(String uniqueID, Class<?> entityClass)
    {
        if(uniqueID == null || uniqueID.length() == 0) return null;
        
        Vector<BaseEntity.ColumnName> column = new Vector<BaseEntity.ColumnName>();
        column.add(BaseEntity.ColumnName.UniqueID);
        
        Vector<Object> value = new Vector<Object>();
        value.add(uniqueID);
        
        ArrayList<BaseEntity> entities =  getEntitiesWithCondition(column, value, entityClass);
        
        if(entities != null && entities.isEmpty() == false)
        {
            return entities.get(0);
        }
        else
        {
            return null;
        }
     }
    
    public static Member getMemberWithID(int memberID) 
    {
        Vector<BaseEntity.ColumnName> column = new Vector<BaseEntity.ColumnName>();
        column.add(BaseEntity.ColumnName.ID);
        
        Vector<Object> value = new Vector<Object>();
        value.add(memberID);
        
        ArrayList<BaseEntity> entities =  getEntitiesWithCondition(column, value, Member.class);
        
        if(entities != null && entities.isEmpty() == false)
        {
            return (Member)entities.get(0);
        }
        else
        {
            return null;
        }
    }

}
