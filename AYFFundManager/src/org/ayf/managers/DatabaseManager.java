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
import javax.swing.event.EventListenerList;
import net.ucanaccess.jdbc.UcanaccessDriver;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.CashFlow;
import org.ayf.database.entities.Expense;
import org.ayf.database.entities.SubscriptionAmountDetails;
import org.ayf.reports.ReportData;
import org.ayf.tpl.java2s.FilenameUtils;
import org.ayf.util.PreferenceManager;


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

    public static final String CASHFLOW_STATUS_TYPE_TABLE_NAME    = "StatusType";
    public static final String MEMBER_TYPE_TABLE_NAME       = "MemberType";
    public static final String DONATIONS_TABLE_NAME         = "Donations";
    public static final String EXPENSES_TABLE_NAME          = "Expenses";
    public static final String MEMBER_TABLE_NAME            = "Members";
    public static final String CASHFLOWS_TABLE_NAME         = "CashFlows";
    public static final String SUBSCRIPTION_AMOUNT_DETAILS_TABLE_NAME = "SubscriptionAmount";
        
    private static ArrayList<Type> PROFESSION_TYPES;
    private static ArrayList<Type> DONATION_TYPES;
    private static ArrayList<Type> MEMBER_TYPES;
    private static ArrayList<Type> EXPENSE_TYPES;
    private static ArrayList<Type> CASHFLOW_TYPES;
    public static  ArrayList<Type> POSITION_TYPES;
    public static  ArrayList<Type> PAYMENT_MODE_TYPES;
    public static  ArrayList<Type> STATUS_TYPES;
    
    enum UpdateAction { Add, Remove, Update, Read };
    
    private static EventListenerList listenerList = new EventListenerList();
    
    public static void initializeDatabaseManager()
    {
        try
        {
            CacheManager.initialize();
            
            getProfessionTypes();
            getExpenseTypes();
            getDonationTypes();
            getMemberTypes();
            getCashFlowStatusTypes();
        }
        catch(Exception ex)
        {
        }
        
    }
    
    
    static public void loadDatabaseClass()
    {
        //PreferenceManager.setDatabaseDir("");
        
        try {
            // Load MS accces driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (Exception ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Failed to load database Driver", ERROR_MESSAGE);
        }
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
        String url = UcanaccessDriver.URL_PREFIX + PreferenceManager.getDatabaseDir() + ";newDatabaseVersion=V2007";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Database connection error", ERROR_MESSAGE);
        }
        catch(NullPointerException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Database connection error", ERROR_MESSAGE);
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
    
    
    static public void addDatabaseUpdateListner(DatabaseUpdateListener listner)
    {
        if(listner == null) throw new IllegalArgumentException("listner is null");
        
        listenerList.add(DatabaseUpdateListener.class, listner);
    }
    
    static public void removeActionListener(DatabaseUpdateListener listener) {
        
        if(listener == null) return;
        
        listenerList.remove(DatabaseUpdateListener.class, listener);
    }
    
    static protected void fireActionPerformed(UpdateAction updateAction, BaseEntity entity)
    {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i=listeners.length-2; i>=0; i-=2)
        {
            if (listeners[i]==DatabaseUpdateListener.class) {
                switch(updateAction)
                {
                    case Add:
                        ((DatabaseUpdateListener)listeners[i+1]).entityDidAdded(entity);
                        break;
                    case Update:
                        ((DatabaseUpdateListener)listeners[i+1]).entityDidUpdated(entity);
                        break;
                    case Remove:
                        ((DatabaseUpdateListener)listeners[i+1]).entityDidRemoved(entity);
                        break;
                    case Read:
                        ((DatabaseUpdateListener)listeners[i+1]).entityDidRead(entity);
                }
            }
        }
    }
    
    static protected void fireActionPerformed(UpdateAction updateAction, ArrayList<BaseEntity> entities)
    {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i=listeners.length-2; i>=0; i-=2)
        {
            if (listeners[i]==DatabaseUpdateListener.class) {
                switch(updateAction)
                {
                    case Read:
                        ((DatabaseUpdateListener)listeners[i+1]).entitiesDidRead(entities);
                        break;
                }
            }
        }
    }
    
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
            if(CacheManager.getCacheEntities(entityClass) != null) return  CacheManager.getCacheEntities(entityClass);
            
            Connection conn = null;
            conn = createConnection();
            
            if(conn == null) return entities;
                
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
            else if(entityClass.equals(SubscriptionAmountDetails.class))
            {
                tableName = SUBSCRIPTION_AMOUNT_DETAILS_TABLE_NAME;
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
        
        fireActionPerformed(UpdateAction.Read, entities);
        
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
            else if(entityClass.equals(SubscriptionAmountDetails.class))
            {
                tableName = SUBSCRIPTION_AMOUNT_DETAILS_TABLE_NAME;
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
                        conditionString.append(conditionColumns.get(i)).append("=?");
                    }
                    else
                    {
                        conditionString.append(conditionColumns.get(i)).append("=?").append(" AND ");
                    }
                }
                
                sqlQuery.append(conditionString);
                
                sqlQuery.trimToSize();
                
                
                try 
                {
                    
                    PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());;
                    
                    for (int i = 1; i <= conditionColumns.size(); ++i)
                    {
                        Object value = conditionValues.get(i-1);

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
                        else if(value instanceof Date || conditionColumns.get(i).toString().contains("Date"))
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
                    }
                    
                    ResultSet rs = ps.executeQuery();
                
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
                    if(ps != null) ps.close();
                    
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
        boolean bUpdated = entities.size() > 0; 
        Connection conn = null;
        if(entities.size() > 0)
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
                else if(entityClass.equals(SubscriptionAmountDetails.class))
                {
                    tableName = SUBSCRIPTION_AMOUNT_DETAILS_TABLE_NAME;
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
                        sqlQuery.append(columnName.toString()).append("=?");
                    }
                    else
                    {
                        sqlQuery.append(columnName.toString()).append("=?,");
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
                bUpdated = false;
            }
            finally
            {
                closeConnection(conn);
            }
        }
        
        if(bUpdated)
        {
            for (BaseEntity baseEntity : entities) 
            {
                 fireActionPerformed(UpdateAction.Update, baseEntity);
            }
        }
        
        return bUpdated;
    }
    
    public static boolean insertEntities(Vector<BaseEntity> entities, Class<?> entityClass)
    {
        boolean bInserted = !entities.isEmpty(); 
        
        Connection conn = null;
        if(entities.size() > 0)
        {
            try 
            {
                conn = createConnection();
                
                StringBuilder sqlQuery = new StringBuilder("INSERT INTO ");
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
                else if(entityClass.equals(SubscriptionAmountDetails.class))
                {
                    tableName = SUBSCRIPTION_AMOUNT_DETAILS_TABLE_NAME;
                }
                else
                {
                    return false;
                }
                
                sqlQuery.append(tableName);
                
                StringBuilder valuesString = new StringBuilder(100);
                StringBuilder  valuePlacement = new StringBuilder(entities.size());
                
                Vector<BaseEntity.ColumnName> columns = entities.get(0).getColumnIDsForDetailLevel(BaseEntity.DetailsLevel.Database);
                
                for (BaseEntity.ColumnName columnName : columns) {
                    if(columns.lastElement() == columnName)
                    {
                        valuesString.append(columnName.toString());
                        valuePlacement.append("?");
                    }
                    else
                    {
                        valuesString.append(columnName.toString()).append(",");
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
                                ps.setString(i, null);
                            }
                        }
                        
                        ++i;
                    }
                    
                    bInserted &= ps.executeUpdate() > 0;
                    
                    if(!bInserted) 
                    {
                        conn.rollback();
                        break;
                    }
                }
                
                conn.commit();
                ps.close();               
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to register member ", ERROR_MESSAGE);
                
                bInserted = false;
            }
            finally
            {
                closeConnection(conn);
            }
        }
        
        if(bInserted)
        {
            for (BaseEntity baseEntity : entities) 
            {
                 fireActionPerformed(UpdateAction.Add, baseEntity);
            }
        }
        return bInserted;
    }
    
    public static boolean registerMember(Member member)
    {
        boolean bRegistered = false; 
        if(member != null)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, null, "registerMember :" + member.toString());
            
            String imagePath = member.getImagePath();
            
            if(imagePath != null)
            {
                StringBuilder newImageName = new StringBuilder(member.getUniqueID().replaceAll("/", "_"));
                newImageName.append(".").append(FilenameUtils.getExtension(imagePath));
                
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, null, "registerMember: imagePath="+member.getImagePath());
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, null, "registerMember: newImageName="+newImageName);
                
                if(ResourceManager.copyImageFileToImageDir(imagePath, newImageName.toString()))
                {
                    Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, null, "registerMember: Image copied successfully");
                    member.setImagePath(newImageName.toString());
                }
            }
            
            Vector<BaseEntity> entity = new Vector<BaseEntity>(1);
            entity.add(member);
            bRegistered = insertEntities(entity, Member.class);
        }
        
        return bRegistered;
    }
    
    
    public static boolean performDonate(Donor donor)
    {
        boolean bDonateSuccess = false; 
        
        if(donor != null)
        {
            Vector<BaseEntity> entity = new Vector<BaseEntity>(1);
            entity.add(donor);
            bDonateSuccess = insertEntities(entity, Donor.class);
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
        column.add(BaseEntity.ColumnName.MemberUniqueID);
        
        Vector<Object> value = new Vector<Object>();
        value.add(memberRegNumber);
        
        ArrayList<BaseEntity> entities =  getEntitiesWithCondition(column, value, Donor.class);
        
        return new ReportData(new Vector<BaseEntity>(entities), BaseEntity.DetailsLevel.MemberStatement, Donor.class);
    }
    
    
    public static ReportData getMemberStatement(int memberRowID)
    {
        Vector<BaseEntity.ColumnName> column = new Vector<BaseEntity.ColumnName>();
        column.add(BaseEntity.ColumnName.ID);
        
        Vector<Object> value = new Vector<Object>();
        value.add(memberRowID);
        
        ArrayList<BaseEntity> entities =  getEntitiesWithCondition(column, value, Donor.class);
        
        return new ReportData(new Vector<BaseEntity>(entities), BaseEntity.DetailsLevel.MemberStatement, Donor.class);
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
        
        ArrayList<BaseEntity> entities =  getEntitiesWithCondition(column, value, Donor.class);
        
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
