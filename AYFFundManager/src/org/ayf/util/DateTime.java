/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author oramtekkar
 */
public class DateTime {
   
    private static final DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    private static final DateFormat dateFormat = new SimpleDateFormat(" dd-MMM-yyyy ");

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
        if(month.equalsIgnoreCase("jan"))
        {
            monthIndex = 1;
        }
        else if(month.equalsIgnoreCase("feb"))
        {
            monthIndex = 2;
        }
        else if(month.equalsIgnoreCase("mar"))
        {
            monthIndex = 3;
        }
        else if(month.equalsIgnoreCase("april"))
        {
            monthIndex = 4;
        }
        else if(month.equalsIgnoreCase("may"))
        {
            monthIndex = 5;
        }
        else if(month.equalsIgnoreCase("june"))
        {
            monthIndex = 6;
        }
        else if(month.equalsIgnoreCase("july"))
        {
            monthIndex = 7;
        }
        else if(month.equalsIgnoreCase("aug"))
        {
            monthIndex = 8;
        }
        else if(month.equalsIgnoreCase("sep"))
        {
            monthIndex = 9;
        }
        else if(month.equalsIgnoreCase("oct"))
        {
            monthIndex = 10;
        }
        else if(month.equalsIgnoreCase("nov"))
        {
            monthIndex = 11;
        }
        else if(month.equalsIgnoreCase("dec"))
        {
            monthIndex = 12;
        }
        
        return getDate(day, monthIndex, year);
    }
    
    public static java.sql.Date toSQLDate(java.util.Date date)
    {
        if(date == null) return null;
        
        return new java.sql.Date(date.getTime());
    }
}
