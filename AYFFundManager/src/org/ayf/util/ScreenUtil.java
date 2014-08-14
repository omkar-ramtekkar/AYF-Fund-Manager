/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.awt.Component;
import java.awt.Point;

/**
 *
 * @author om
 */
public class ScreenUtil {
    
    static public Point getCenterPointOnScreen(Component component)
    {
        if(component == null) return null;
        
        Point point = component.getLocationOnScreen();
        point.x += component.getWidth() / 2.0;
        point.y += component.getHeight()/ 2.0;
        
        return point;
    }
}
