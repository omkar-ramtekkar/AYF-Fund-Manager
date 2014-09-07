/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ayf.ui;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import org.ayf.managers.ResourceManager;

/**
 *
 * @author om
 */
public class BackgroundPanel extends javax.swing.JPanel {

    protected BufferedImage backgroundImage = null;
    protected BackgroundStyle style = BackgroundStyle.Default;

    public enum BackgroundStyle {

        Default,
        GradientBlueGray,
        TextureMac,
        NatureGreen,
        TextureLightGray,
        GradientGray,
        NewStyle
    }

    public BackgroundPanel() {
        addComponentListener(new ComponentAdapter() 
        {
            public void componentResized(ComponentEvent e) 
            {
                setStyle(getStyle());
            }
        });
    }

    public BackgroundPanel(BackgroundStyle style) {
        this();
        this.style = style;
    }

    public BackgroundStyle getStyle() {
        return style;
    }

    public void setStyle(BackgroundStyle style) {
        this.style = style;
        this.backgroundImage = ResourceManager.getImage("background_" + this.style.toString(), getSize());
        repaint();
    }

    protected void paintComponent(Graphics g) {
        if (backgroundImage == null) {
            backgroundImage = ResourceManager.getImage("background_" + this.style.toString(), getSize());
        }

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }

        super.paintComponent(g);
    }

}
