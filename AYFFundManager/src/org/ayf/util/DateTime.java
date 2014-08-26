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
import java.util.Locale;

/**
 *
 * @author oramtekkar
 */
public class DateTime {
   
    private static final DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    private static final DateFormat dateFormat = new SimpleDateFormat(" dd-MMM-yyyy ");
    private static java.util.Date today = new java.util.Date();
    public static final String[] Months = { "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };
    

    public static String getFormattedDate()
    {
        return dateFormat.format(getToday());
    }
    
    public static String getFormattedDateSQL(java.sql.Date date)
    {
        if(date == null) return "";
        
        return dateFormat.format(date);
    }
    
    public static String getFormattedDate(java.util.Date date)
    {
        return getFormattedDateSQL(toSQLDate(date));
    }
    
    public static String getFormattedTime()
    {
        today = new java.util.Date();
        return timeFormat.format(getToday());
    }
    
    public static int getDay(java.sql.Date date) throws IllegalArgumentException
    {
        if(date == null) throw new IllegalArgumentException("Provided date is null/invalid");
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getDay(java.util.Date date) throws IllegalArgumentException
    {
        return getDay(toSQLDate(date));
    }
    
    public static int getMonth(java.sql.Date date) throws IllegalArgumentException
    {
        if(date == null) throw new IllegalArgumentException("Provided date is null/invalid");
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal.get(Calendar.MONTH);
    }
    
    public static int getMonth(java.util.Date date) throws IllegalArgumentException
    {
        if(date == null) throw new IllegalArgumentException("Provided date is null/invalid");
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal.get(Calendar.MONTH);
    }
    
    public static int getYear(java.sql.Date date) throws IllegalArgumentException
    {
        if(date == null) throw new IllegalArgumentException("Provided date is null/invalid");
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal.get(Calendar.YEAR);
    }
    
    public static int getYear(java.util.Date date) throws IllegalArgumentException
    {
        return getYear(toSQLDate(date));
    }
    
    public static java.sql.Date getDate(int day, int month, int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        
        return toSQLDate(cal.getTime());
    }
    
    
    public static java.sql.Date getDate(int day, String month, int year)
    {
        int monthIndex = -1;
        
        for (int i=0; i<Months.length; ++i)
        {
            if(Months[i].equalsIgnoreCase(month))
            {
                monthIndex = i;
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
    
    public static java.util.Date toUtilDate(java.sql.Date date)
    {
        if(date == null) return null;
        
        return new java.util.Date(date.getTime());
    }
    
    public static java.sql.Date toSQLDate(String dateString)
    {
        if(dateString == null || dateString.isEmpty()) return null;
        
        java.sql.Date date = null;
        StringBuilder tempDateString = new StringBuilder(dateString.length() + 5);
        tempDateString.append(" ").append(dateString.trim()).append(" ");
        
        dateString = tempDateString.toString();
        
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
        
        return null;
    }
    
    public static java.util.Date getToday()
    {
        return today;
    }
    
    public static java.sql.Date getTodaySQL()
    {
        return toSQLDate(getToday());
    }
    
    static public java.util.Date getBeforeDate(java.util.Date date1, java.util.Date date2)
    {
        if(date1 != null && date2 != null)
        {
            if(date1.before(date2))
                return date1;
            else
                return date2;
        }
        
        return null;
    }
    
    static public java.util.Date getAfterDate(java.util.Date date1, java.util.Date date2)
    {
        if(date1 != null && date2 != null)
        {
            if(date1.after(date2))
                return date1;
            else
                return date2;
        }
        
        return null;
    }
    
    static public java.sql.Date getBeforeDate(java.sql.Date date1, java.sql.Date date2)
    {
        return date1.getTime() < date2.getTime() ? date1 : date2;
    }
    
    static public java.sql.Date getAfterDate(java.sql.Date date1, java.sql.Date date2)
    {
        return date1.getTime() > date2.getTime() ? date1 : date2;
    }
    
    static public boolean isDateBetween(java.sql.Date date1, java.sql.Date date2, java.sql.Date checkDate)
    {
        if(date1 == null || date2 == null) return false;
        
        if(checkDate == null) return false;
        
        if(date1.getTime() <= checkDate.getTime() && date2.getTime() >= checkDate.getTime())
        {
            return true;
        }
        
        return false;
    }
    
    static public boolean isDateBetween(java.util.Date date1, java.sql.Date date2, java.util.Date checkDate)
    {
        if(date1 == null || date2 == null) return false;
        
        if(checkDate == null) return false;
        
        if(date1.getTime() <= checkDate.getTime() && date2.getTime() >= checkDate.getTime())
        {
            return true;
        }
        
        return false;
    }
    
    
    static public java.sql.Date dateByAdding(java.sql.Date initialDate, int days, int months, int years)
    {
        return toSQLDate(dateByAdding(new java.util.Date(initialDate.getTime()), days, months, years));
    }
    
    static public java.util.Date dateByAdding(java.util.Date initialDate, int days, int months, int years)
    {
        if(initialDate == null) return null;
        
        Calendar c = Calendar.getInstance(); // starts with today's date and time
        
        c.setTime(initialDate);
        
        c.add(Calendar.DATE, days);
        c.add(Calendar.MONTH, months);
        c.add(Calendar.YEAR, years);
        
        return c.getTime();
    }
}
