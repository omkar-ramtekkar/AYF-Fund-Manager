/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 *
 * @author om
 */
public class GradientButton extends JButton {
    
    @Override
    public void paintComponent(Graphics g)  
    {  
        Graphics2D g2 = (Graphics2D)g;  
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                            RenderingHints.VALUE_ANTIALIAS_ON);  
        int width = getWidth();  
        int height = getHeight();  
        Paint gradient = new GradientPaint(0, 0, Color.GREEN, width, height, Color.GRAY);  
        g2.setPaint(gradient);  
        g2.fillRect(0, 0, width, height);  
        super.paintComponent(g);  
    }
}
