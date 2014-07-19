/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.awt.Color;
import java.awt.Label;
import java.awt.Point;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.Popup;
import javax.swing.PopupFactory;


class ToastManager
{
    Vector<Toast> toasts;

    private static ToastManager manager = new ToastManager();
    
    private ToastManager() {
        toasts = new Vector<Toast>(5);
    }
    
    public static ToastManager getShared()
    {
        return manager;
    }
    
    public void add(Toast toast)
    {
        toasts.add(toast);
    }
    
    public boolean remove(Toast toast)
    {
        if(toasts.remove(toast))
        {
            toast.dismiss();
            return true;
        }
        
        return false;
    }
    
    public void removeAll()
    {
        for (Toast toast : toasts) 
        {
            if(remove(toast))
            {
                toast.dismiss();
            }
        }
        
        toasts.removeAllElements();
    }
}


/**
 *
 * @author oramtekkar
 */
public class Toast {
    
    private Point   location;
    private final String  message;
    private long duration; //in millisecond
    boolean isPositive;
    private Thread toastThread;

    public Toast(String msg, Point toastLocation, final boolean isPositive, long forDuration) 
    {
        this.location = toastLocation;
        this.message = msg;
        this.duration = forDuration;
        this.isPositive = isPositive;
        
        toastThread = new Thread(new Runnable()
        {
            @Override
            public void run() 
            {
                Popup view = null;
                try 
                {
                    Label tip = new Label(message);
                    tip.setBackground(Color.WHITE);
                    if(isPositive)
                    {
                        tip.setForeground(Color.BLACK);
                    }
                    else
                    {
                        tip.setForeground(Color.RED);
                    }

                    view = PopupFactory.getSharedInstance().getPopup(null, tip , location.x, location.y);
                    view.show();
                    Logger.getLogger(Toast.class.getName()).log(Level.INFO, "Showing Toast: " + message, "");
                    Thread.sleep(duration);
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(Toast.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    clean();
                    view.hide();
                    Logger.getLogger(Toast.class.getName()).log(Level.INFO, "Hiding Toast: " + message, "");
                }
            }
        });

        toastThread.start();
    }
    
    
    
    private void clean()
    {
        toastThread = null;
        ToastManager.getShared().remove(this);
    }
    
    void dismiss()
    {
        if(toastThread != null)
        {
            toastThread.interrupt();
        }
    }
    
    public static void showToast(JComponent component, String message, boolean isPositive)
    {
        showToast(component, message, isPositive, 2000);
    }
    
    public static void showToast(JComponent component, String message, boolean isPositive, long forDuration)
    {
        Point point = component.getLocationOnScreen();
        showToast(message, point, isPositive, forDuration);
    }
    
    public static void showToast(String message, Point locationOnScreen, boolean isPositive)
    {
        showToast(message, locationOnScreen, isPositive, 2000);
    }
    
    public static void showToast(String message, Point location, boolean isPositive, long forDuration)
    {
        Toast toast = new Toast(message, location, isPositive, forDuration);
    }
}
