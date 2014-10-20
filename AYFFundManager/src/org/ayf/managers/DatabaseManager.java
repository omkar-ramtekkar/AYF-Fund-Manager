/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.io.File;
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
import org.ayf.reports.ReportSpecificData;
import org.ayf.tpl.java2s.FilenameUtils;
import org.ayf.util.DateTime;
import org.ayf.util.PreferenceManager;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


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


    
    public enum UpdateAction { Add, Remove, Update, Read };
    public enum GroupingOption { Type, Month, Year };
    
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
        try {
            // Load MS accces driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Database Error", ERROR_MESSAGE);
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
        File dbFile = new File(PreferenceManager.getDatabaseDir());
        if(!dbFile.exists()) return null;
        
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
        
        if(connection == null) return false;
        
        boolean bSuccess = true;
        try {
            connection.close();
        } catch (SQLException ex) {
            bSuccess = false;
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bSuccess;
    }
    
    private static boolean closeResultSet(ResultSet rs)
    {
        if(rs == null) return false;
        
        boolean bSuccess = true;
        
        try {
            rs.close();
        } catch (SQLException ex) {
            bSuccess = false;
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bSuccess;
    }
    
    private static boolean closeStatement(Statement s)
    {
        if(s == null) return false;
        
        boolean bSuccess = true;
        
        try {
            s.close();
        } catch (SQLException ex) {
            bSuccess = false;
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bSuccess;
    }
    
    
    private static boolean closeConnectionObjects(ResultSet rs, Statement s, Connection conn)
    {
        boolean bSuccess = true;
        bSuccess &= closeResultSet(rs);
        bSuccess &= closeStatement(s);
        bSuccess &= closeConnection(conn);
        
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            connection = createConnection();
            
            if(connection == null) return types;
            
            ps = connection.prepareStatement("SELECT * FROM " + tableName);
            
            rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getTypesFromTable({0}) : Column Count - " + rs.getMetaData().getColumnCount() , tableName);
            
            while(rs.next())
            {
                int id = rs.getInt("ID");
                String typeValue = rs.getString("Type");
                Type type = new Type(id, typeValue);
                types.add(type);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Database Error" + tableName, ERROR_MESSAGE);
        }
        finally
        {
            closeConnectionObjects(rs, ps, connection);
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
                            }catch(SQLException ex){ ex.printStackTrace(); }
                        }
                        
                        int id = rs.getInt(BaseEntity.ColumnName.ID.toString());
                        
                        entity.setValueForField(BaseEntity.ColumnName.ID, id);
                        entities.add(entity);
                    }
                } catch (SQLException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                }
                finally
                {
                    closeConnectionObjects(rs, statement, conn);
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
            
            if(conn == null) return entities;
            
            PreparedStatement ps = null;
            ResultSet rs = null;
            
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
                    
                    ps = conn.prepareStatement(sqlQuery.toString());;
                    
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
                    
                    rs = ps.executeQuery();
                
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
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally
                {
                    closeConnectionObjects(rs, ps, conn);
                }
            }
            
        }
        
        return entities;
    }
    
    public static int getActiveMembersCount(GroupingOption option, java.sql.Date startDate, java.sql.Date endDate)
    {
        return 0;
    }
    
    public static boolean updateEntities(Vector<BaseEntity> entities, Class<?> entityClass)
    {
        boolean bUpdated = entities.size() > 0; 
        
        if(entities.size() > 0)
        {
            Connection conn = null;
            PreparedStatement ps = null;
            
            try 
            {
                conn = createConnection();
                
                if(conn == null) return false;
                
                StringBuilder sqlQuery = new StringBuilder("UPDATE ");
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
                
                ps = conn.prepareStatement(sqlQuery.toString());
                
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
                               
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to register member ", ERROR_MESSAGE);
                bUpdated = false;
            }
            finally
            {
                closeConnectionObjects(null, ps, conn);
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
        
        if(entities.size() > 0)
        {
            Connection conn = null;
            PreparedStatement ps = null;
            
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
                
                ps = conn.prepareStatement(sqlQuery.toString());
                
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
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to register member ", ERROR_MESSAGE);
                
                bInserted = false;
            }
            finally
            {
                closeConnectionObjects(null, ps, conn);
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
    
    public static ReportData getDonationByCategory()
    {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        
        Vector rows = new Vector();
        Vector columns = new Vector();
        
        columns.add("DonationType");
        columns.add("TotalAmount");
        
        DefaultPieDataset pieDataSet = new DefaultPieDataset();
        
        try 
        {
            conn = createConnection();

            String sql = "SELECT DonationType, sum(Amount) as TotalAmount FROM " + DONATIONS_TABLE_NAME + " GROUP BY DonationType";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            
            while(rs.next())
            {
                Vector rowData = new Vector();
                rowData.add(rs.getString("DonationType"));
                rowData.add(rs.getDouble("TotalAmount"));
                
                rows.add(rowData);
                
                String type = rs.getString("DonationType");
                pieDataSet.setValue(type == null ? "Invalid Type" : type, rs.getDouble("TotalAmount"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Unable to get donations by type ", ERROR_MESSAGE);
        }
        finally
        {
            closeConnectionObjects(rs, statement, conn);
        }
        
        ReportData data = new ReportData(rows, columns);
        data.setPieChartDataSet(pieDataSet);
        
        return data;
    }
    
    public static ReportData getDonationByGrouping(GroupingOption option, java.sql.Date startDate, java.sql.Date endDate)
    {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        Vector rows = new Vector();
        Vector columns = new Vector();
        
        StringBuffer sql = new StringBuffer(100);
        
        String rsColumnName = null;
        
        
        switch(option)
        {
            case Type:
            {
                sql.append("SELECT")
                    .append(" DonationType, SUM(Amount) as TotalAmount FROM ")
                    .append(DONATIONS_TABLE_NAME)
                    .append(" WHERE DonationDate BETWEEN ? AND ? GROUP BY (DonationType)");
                
                rsColumnName = "DonationType";
                columns.add("DonationType"); break;
                
            }
                
            case Month:
            {
                sql.append("SELECT")
                    .append(" MONTH(DonationDate) as Month, SUM(Amount) as TotalAmount FROM ")
                    .append(DONATIONS_TABLE_NAME)
                    .append(" WHERE DonationDate BETWEEN ? AND ? GROUP BY MONTH(DonationDate)")
                    .append(" ORDER BY Month ASC");
                
                rsColumnName = "Month";
                columns.add("Month"); break;
            }
            case Year :
            {
                sql.append("SELECT")
                    .append(" YEAR(DonationDate) as Year, SUM(Amount) as TotalAmount FROM ")
                    .append(DONATIONS_TABLE_NAME)
                    .append(" WHERE DonationDate BETWEEN ? AND ? GROUP BY YEAR(DonationDate)");
                
                rsColumnName = "Year";
                columns.add("Year"); break;
            }
        }
        
        columns.add("TotalAmount");
        
        DefaultPieDataset pieDataSet = new DefaultPieDataset();
        DefaultCategoryDataset barDataSet = new DefaultCategoryDataset();
        
        try 
        {
            conn = createConnection();
            
            statement = conn.prepareStatement(sql.toString());
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            
            rs = statement.executeQuery();
            
            while(rs.next())
            {
                Vector rowData = new Vector();
                if(option == GroupingOption.Month)
                {
                    rowData.add(DateTime.Months[rs.getInt(rsColumnName)]);
                }
                else
                {
                    rowData.add(rs.getString(rsColumnName));
                }
                
                rowData.add(rs.getDouble("TotalAmount"));
                
                rows.add(rowData);
                
                String type = rowData.elementAt(0) != null ? rowData.elementAt(0).toString() : null;
                pieDataSet.setValue(type == null ? "Invalid Type" : type, rs.getDouble("TotalAmount"));
                
                barDataSet.setValue(rs.getDouble("TotalAmount"), "", type == null ? "Invalid Type" : type);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), 
                    "Unable to get donation details by " + 
                            option.toString() + 
                            " for year " + 
                            DateTime.getYear(startDate) + "-" + DateTime.getYear(endDate), 
                    ERROR_MESSAGE);
        }
        finally
        {
            closeConnectionObjects(rs, statement, conn);
        }
        
        ReportData data = new ReportData(rows, columns);
        data.setPieChartDataSet(pieDataSet);
        data.setCategoryDataSet(barDataSet);
        
        return data;
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
    
    public static ArrayList<BaseEntity> getMemberSubscriptionEndingBetween(java.sql.Date date1, java.sql.Date date2, boolean includeInactiveMembers) {
                
        ArrayList<BaseEntity> allEntities = getAllEntities(Member.class);
        ArrayList<BaseEntity> resultEntities = new ArrayList<BaseEntity>(10);
        
        for (BaseEntity baseEntity : allEntities) 
        {
            Member member = (Member) baseEntity;
            
            if(!includeInactiveMembers)
            {
                if(!member.isActive())
                    continue;
            }
            
            java.sql.Date regDate = member.getRegisterationDate();
            
            if(regDate == null)
                continue;
            
            int day = DateTime.getDay(regDate);
            int month = DateTime.getMonth(regDate);
            int currentYear = DateTime.getYear(date1);
            
            java.sql.Date newRegDate = DateTime.getDate(day, month, currentYear);
            
            if(newRegDate.getTime() >= date1.getTime() && newRegDate.getTime() <= date2.getTime())
                resultEntities.add(member);
        }
        
        return resultEntities;
    }
    
    
    public static ArrayList<BaseEntity> getMemberSubscriptionEndingBy(java.sql.Date date1, boolean includeInactiveMembers) {
        return getMemberSubscriptionEndingBetween(DateTime.getTodaySQL(), date1, includeInactiveMembers);
    }
    
    public static float getTotalDonationPaidByMember(String regID, String donationType)
    {
        if(regID == null || regID.isEmpty()) return 0;
        
        if(donationType == null || donationType.isEmpty()) return 0;

        float totalDonation = 0;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            StringBuilder sqlString = new StringBuilder(50);
            sqlString.append("SELECT SUM(Amount) as Total FROM ").append(DONATIONS_TABLE_NAME).
                    append(" WHERE (MemberUniqueID=CAST(? as VARCHAR(20)) AND DonationType=CAST(? as VARCHAR(30)))");
            
            conn = createConnection();
            
            if(conn == null) return totalDonation;
            
            ps = conn.prepareStatement(sqlString.toString());
            
            ps.setString(1, regID);
            ps.setString(2, donationType);
            
            rs = ps.executeQuery();
            
            if(rs.next())
                totalDonation = rs.getFloat("Total");
            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeConnectionObjects(rs, ps, conn);
        }
        
        return totalDonation;
    }
    
    public static ReportData getTotalExpensesInYearByType(java.sql.Date fromDate, java.sql.Date toDate)
    {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        Vector rows = new Vector();
        Vector columns = new Vector();
        
        columns.add(BaseEntity.ColumnName.ExpenseType.toString());
        columns.add("TotalExpense");
        columns.add("Year");
        
        DefaultPieDataset pieDataSet = new DefaultPieDataset();
        
        try 
        {
            conn = createConnection();
            
            
            StringBuffer sql = new StringBuffer(100);
            sql.append("SELECT SUM(Amount) as TotalExpense, ExpenseType  FROM ")
                    .append(EXPENSES_TABLE_NAME)
                    .append(" WHERE ")
                    .append(BaseEntity.ColumnName.ExpenseDate.toString())
                    .append(" BETWEEN ? AND ?")
                    .append(" GROUP BY ")
                    .append(BaseEntity.ColumnName.ExpenseType.toString());
            
            
            statement = conn.prepareStatement(sql.toString());
            
            statement.setDate(1, fromDate);
            statement.setDate(2, toDate);
            
            rs = statement.executeQuery();
            
            while(rs.next())
            {
                Vector rowData = new Vector();
                rowData.add(rs.getString(BaseEntity.ColumnName.ExpenseType.toString()));
                rowData.add(rs.getDouble("TotalExpense"));
                rowData.add(DateTime.getYear(fromDate) + "-" + DateTime.getYear(toDate));
                
                rows.add(rowData);
                
                String type = rs.getString(BaseEntity.ColumnName.ExpenseType.toString());
                pieDataSet.setValue(type == null ? "Invalid Type" : type, rs.getDouble("TotalExpense"));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeConnectionObjects(rs, statement, conn);
        }
        
        ReportData data = new ReportData(rows, columns);
        data.setPieChartDataSet(pieDataSet);
        
        return data;
    }
    
    public static ReportData getTotalExpensesPerYear(java.sql.Date fromDate, java.sql.Date toDate)
    {
        return null;
    }
    
    public static ReportData getMemberRegisterationTrendData(GroupingOption option, Date startDate, Date endDate) 
    {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        Vector rows = new Vector();
        Vector columns = new Vector();
        
        StringBuffer sql = new StringBuffer(100);
        
        String rsColumnName = null;
        String valueColumnName = "NumberOfMembersRegistered";
        
        ReportSpecificData reportSpecificData = new ReportSpecificData();
        
        switch(option)
        {
            case Month:
            {
                sql.append("SELECT").append(" MONTH(RegisterationDate) as Month, COUNT(*) as ").append(valueColumnName)
                    .append(" FROM ")
                    .append(MEMBER_TABLE_NAME)
                    .append(" WHERE RegisterationDate BETWEEN ? AND ? GROUP BY MONTH(RegisterationDate)")
                    .append(" ORDER BY Month ASC");
                
                rsColumnName = "Month";
                columns.add("Month"); break;
            }
            case Year :
            {
                sql.append("SELECT").append(" YEAR(RegisterationDate) as Year, COUNT(*) as ").append(valueColumnName)
                    .append(" FROM ")
                    .append(MEMBER_TABLE_NAME)
                    .append(" WHERE RegisterationDate BETWEEN ? AND ? GROUP BY YEAR(RegisterationDate)")
                        .append(" ORDER BY Year DESC");
                
                rsColumnName = "Year";
                columns.add("Year"); break;
            }
        }
        
        columns.add(valueColumnName);
        
        DefaultPieDataset pieDataSet = new DefaultPieDataset();
        DefaultCategoryDataset barDataSet = new DefaultCategoryDataset();
        
        try 
        {
            conn = createConnection();
            
            statement = conn.prepareStatement(sql.toString());
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            
            rs = statement.executeQuery();
            
            while(rs.next())
            {
                Vector rowData = new Vector();
                if(option == GroupingOption.Month)
                {
                    rowData.add(DateTime.Months[rs.getInt(rsColumnName)]);
                }
                else
                {
                    rowData.add(rs.getString(rsColumnName));
                }
                
                rowData.add(rs.getInt(valueColumnName));
                
                rows.add(rowData);
                
                String type = rowData.elementAt(0) != null ? rowData.elementAt(0).toString() : null;
                pieDataSet.setValue(type == null ? "Invalid Type" : type, rs.getInt(valueColumnName));
                
                barDataSet.setValue(rs.getInt(valueColumnName), "", type == null ? "Invalid Type" : type);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), 
                    "Unable to get members detail by " + 
                            option.toString() + 
                            " for year " + 
                            DateTime.getYear(startDate) + "-" + DateTime.getYear(endDate), 
                    ERROR_MESSAGE);
        }
        finally
        {
            closeConnectionObjects(rs, statement, conn);
        }
        
        
        ReportData data = new ReportData(rows, columns);
        data.setPieChartDataSet(pieDataSet);
        data.setCategoryDataSet(barDataSet);
        data.setReportSpecificData(reportSpecificData);
        return data;
    }

}

