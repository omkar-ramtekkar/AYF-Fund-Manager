/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
}
