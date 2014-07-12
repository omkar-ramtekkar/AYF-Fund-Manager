/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author om
 */
public class ResourceManager 
{
    
    public final static String DomainPath = "/org/ayf/resources/images/";
    
    protected static BufferedImage backgroundImage = null;
    
    public static ImageIcon getIcon(String imageName)
    {
        URL url = ResourceManager.class.getResource(DomainPath + imageName + ".png");
        if(url != null)
        {
            return new ImageIcon(url);
        }
        
        return null;
    }
    
    public static ImageIcon getIcon(String imageName, Dimension size)
    {
        BufferedImage resizedImage = getImage(imageName, size);
        if(resizedImage != null)
        {
            return new ImageIcon(resizedImage);
        }
        
        return null;
    }
    
    public static ImageIcon getExternalIcon(String imagePath, Dimension size)
    {
        BufferedImage image = getExternalImage(imagePath, size);
        if(image != null)
        {
            return new ImageIcon(image);
        }
        
        return null;
    }
    
    public static BufferedImage getExternalImage(String imagePath, Dimension size)
    {
        BufferedImage image = getExternalImage(imagePath);
        if(image != null)
        {
            return resizeImage(image, size);
        }
        
        return image;
    }
    
    public static BufferedImage getExternalImage(String imagePath)
    {
        if(imagePath != null)
        {
            try {
                return ImageIO.read(new File(imagePath).toURI().toURL());
            } catch (IOException ex) {
                Logger.getLogger(ResourceManager.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
        return null;
    }
    
    public static BufferedImage getImage(String imageName)
    {
        URL url = ResourceManager.class.getResource(DomainPath + imageName + ".png");
        if(url != null)
        {
            try {
                return ImageIO.read(url);
            } catch (IOException ex) {
                Logger.getLogger(ResourceManager.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }
    
    public static BufferedImage getImage(String imageName, Dimension size)
    {
        BufferedImage resizedImge = resizeImage(getImage(imageName), size);
        return resizedImge;
    }
    
    public static BufferedImage resizeImage(BufferedImage image, Dimension size)
    {
        BufferedImage resizedImage = null;
        if(image != null && (size.height > 0 && size.width > 0))
        {
            resizedImage = new BufferedImage(size.width, size.height, BufferedImage.TRANSLUCENT);
            Graphics2D g2d = (Graphics2D) resizedImage.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(image, 0, 0, size.width, size.height, null);
            g2d.dispose();
        }
        
        return resizedImage;
    }
    
    public static BufferedImage getPanelBackgroundImage()
    {
        if(backgroundImage == null)
        {
            backgroundImage = getImage("background");
        }
        
        return backgroundImage;
    }
}
