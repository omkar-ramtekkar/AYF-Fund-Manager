/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ayf.ui.InformationPanel;

/**
 *
 * @author om
 */
public class NumberUtil {
    public static String getUnformattedNumber(String formattedString)
    {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        try {
            String unformattedNumber = format.parse(formattedString.trim()).toString();
            return unformattedNumber;
        } catch (ParseException ex) {
            Logger.getLogger(NumberUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static String getFormattedNumber(String unformattedString)
    {
        try
        {
            Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
            String formattedNumber = format.format(Double.parseDouble(unformattedString.trim()));
            formattedNumber = formattedNumber.replace("Rs.", "");
            return formattedNumber;
        }
        catch(NumberFormatException ex)
        {
            Logger.getLogger(NumberUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
