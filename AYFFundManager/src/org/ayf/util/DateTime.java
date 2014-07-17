/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oramtekkar
 */
public class DateTime {
   
    private static final DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    private static final DateFormat dateFormat = new SimpleDateFormat(" dd-MMM-yyyy ");
    
        public static final String[] Months = { "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };

    public static String getFormattedDate()
    {
        return dateFormat.format(new Date());
    }
    
    public static String getFormattedDateSQL(java.sql.Date date)
    {
        return dateFormat.format(date);
    }
    
    public static String getFormattedTime()
    {
        return timeFormat.format(new Date());
    }
    
    public static int getDay(java.sql.Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getMonth(java.sql.Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal.get(Calendar.MONTH);
    }
    
    public static int getYear(java.sql.Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal.get(Calendar.YEAR);
    }
    
    public static Date getDate(int day, int month, int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        
        return cal.getTime();
    }
    
    public static Date getDate(int day, String month, int year)
    {
        int monthIndex = -1;
        
        for (int i=0; i<Months.length; ++i)
        {
            if(Months[i].equalsIgnoreCase(month))
            {
                monthIndex = i + 1;
                break;
            }
        }
        
        return getDate(day, monthIndex, year);
    }
    
    public static java.sql.Date toSQLDate(java.util.Date date)
    {
        if(date == null) return null;
        
        return new java.sql.Date(date.getTime());
    }
    
    public static java.sql.Date toSQLDate(String dateString)
    {
        java.sql.Date date = null;
        if(dateString != null && dateString.length() > 0)
        {
            try {
                date = toSQLDate(dateFormat.parse(dateString));
                return date;
            } catch (ParseException ex) {}
            
            try {
                date = toSQLDate(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(dateString));
                return date;
            } catch (ParseException ex) {}
            
            try {
                date = toSQLDate(new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH).parse(dateString));
                return date;
            } catch (ParseException ex) {}
            
            try {
                date = toSQLDate(new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(dateString));
                return date;
            } catch (ParseException ex) {}
            
            try {
                date = toSQLDate(new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH).parse(dateString));
                return date;
            } catch (ParseException ex) {}
        }
        
        return null;
    }
}
