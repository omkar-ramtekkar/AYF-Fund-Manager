/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.awt.Color;
import java.awt.Label;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 *
 * @author oramtekkar
 */
public class Toast {
    
    private final JComponent component;
    private Point   location;
    private final String  message;
    private long duration; //in millisecond

    public Toast(JComponent comp, Point toastLocation, String msg, long forDuration) {
        this.component = comp;
        this.location = toastLocation;
        this.message = msg;
        this.duration = forDuration;
        
        if(this.component != null)
        {
            if(this.location == null)
            {
                this.location = component.getLocationOnScreen();
            }
                        
            new Thread(new Runnable()
            {
                @Override
                public void run() 
                {
                    Popup view = null;
                    try 
                    {
                        Label tip = new Label(message);
                        tip.setForeground(Color.red);
                        tip.setBackground(Color.white);
                        view = PopupFactory.getSharedInstance().getPopup(component, tip , location.x + 30, location.y + component.getHeight() + 5);
                        view.show();
                        Logger.getLogger(Toast.class.getName()).log(Level.INFO, "Showing Toast: " + message, "");
                        Thread.sleep(duration);
                    } catch (InterruptedException ex) 
                    {
                        Logger.getLogger(Toast.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        view.hide();
                        Logger.getLogger(Toast.class.getName()).log(Level.INFO, "Hiding Toast: " + message, "");
                    }
                }
            }).start();
        }
    }
    
    public static void showToast(JComponent component, String message)
    {
        Toast toast = new Toast(component, null, message, 2000/*Default 2 Sec*/);
    }
    
    public static void showToast(JComponent component, String message, Point location, long forDuration)
    {
        Toast toast = new Toast(component, location, message, forDuration);
    }
}
