/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author om
 */
public class ImageUtil {
    
    public static BufferedImage panelToImage(Component panel)
    {
        if(panel == null) return null;
        
        Dimension size = panel.getPreferredSize();
        BufferedImage image = new BufferedImage(
            size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        return image;
    }
    
    public static BufferedImage panelToImage(Component panel, double scale)
    {
        BufferedImage image = panelToImage(panel);
        
        if(image == null) return null;
        
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp =
            new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(image, null);
    }
    
    public static BufferedImage panelToImage(Component panel, Dimension size)
    {
        if(size == null || size.getHeight() < 1 || size.getWidth() < 1) return null;
        
        Dimension panelSize = panel.getSize();
        
        double xRatio = (double)panel.getWidth() / (double)panelSize.getWidth();
        double yRatio = (double)panel.getHeight()/ (double)panelSize.getHeight();
        
        double scale = Math.min(xRatio, yRatio);
        
        return panelToImage(panel, scale);
    }
}
