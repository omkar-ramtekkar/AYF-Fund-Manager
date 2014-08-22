/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import com.sun.tools.corba.se.idl.InvalidArgument;
import java.awt.GraphicsEnvironment;
import java.awt.IllegalComponentStateException;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author om
 */
public class ScreenUtil {
    
    static public Point getCenterPointOnScreen(JComponent component) throws InvalidArgument, IllegalComponentStateException
    {
        if(component == null) throw new InvalidArgument("Provided component is null");
        
        Point point = component.getLocationOnScreen();
        point.x += component.getVisibleRect().getWidth()/ 2.0;
        point.y += component.getVisibleRect().getHeight()/ 2.0;
        
        return point;
    }
    
    static public Point getScreenCenterPoint()
    {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    }
}
