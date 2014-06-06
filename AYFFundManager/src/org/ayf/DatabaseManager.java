/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    
    // Load MS accces driver class
    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection createConnection(){
        // C:\\databaseFileName.accdb" - location of your database 
        //String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + "C:\\AYF\\database.accdb";
        String url = "jdbc:ucanaccess://c:/AYF/database.accdb;lockmdb=true;ignorecase=true";
        // specify url, username, pasword - make sure these are valid 
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Connection Succesfull");
        
        return conn;
    }
    
    public static boolean closeConnection(Connection connection){
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
        return getTypesFromTable(DONATION_TYPE_TABLE_NAME);
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Type> getExpenseTypes()
    {
        return getTypesFromTable(EXPENSE_TYPE_TABLE_NAME);
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Type> getMemberTypes()
    {
        return getTypesFromTable(MEMBER_TYPE_TABLE_NAME);
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Type> getProfessionTypes()
    {
        return getTypesFromTable(PROFESSION_TYPE_TABLE_NAME);
    }
    
    public static ArrayList<Type> getTypesFromTable(String tableNam)
    {
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getTypesFromTable({0})", tableNam);
        
        ArrayList<Type> types = new ArrayList<>();
        
        try {
            Connection connection = createConnection();
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableNam);
            
            ResultSet rs = ps.executeQuery();
            
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "getTypesFromTable({0}) : Column Count - " + rs.getMetaData().getColumnCount() , tableNam);
            
            while(rs.next())
            {
                int id = Integer.parseInt(rs.getObject("ID").toString());
                String typeValue = rs.getObject("Type").toString();
                Type type = new Type(id, typeValue);
                types.add(type);
            }
            
            closeConnection(connection);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return types;
    }
    
}
