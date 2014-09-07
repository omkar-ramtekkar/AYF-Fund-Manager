/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import org.ayf.command.Command;
import org.ayf.command.ToolbarCommand;
import org.ayf.managers.ApplicationManager;
import org.ayf.managers.ResourceManager;
import org.ayf.util.DateTime;

/**
 *
 * @author om
 */
public class ToolbarController implements ActionListener{
    
    JToolBar toolbarView;
    JButton dateTimeButton;
    private BufferedImage backgroundImage = null;
    
    public ToolbarController() {
        setupToolbar();
    }
    
    protected void setupToolbar()
    {
        toolbarView = new JToolBar("MainToolbar")
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                if(backgroundImage == null)
                {
                    backgroundImage = ResourceManager.getImage("background_GrayTexture", getSize());
                }

                if(backgroundImage != null)
                {
                    g.drawImage(backgroundImage, 0, 0, null);
                }

                super.paintComponent(g);
            }
            
            @Override
            protected JButton createActionComponent(Action a)
            {
                JButton button = super.createActionComponent(a);
                button.setSize(128, 128);
                return button;
            }
        };
        
        toolbarView.setBorder(new LineBorder(Color.LIGHT_GRAY));
        toolbarView.setBorderPainted(true);
        
        toolbarView.add(new ActionItem("Home", ToolbarCommand.SubCommandType.Home, "home"));
        toolbarView.addSeparator();
        toolbarView.addSeparator();
        
        toolbarView.add(new ActionItem("Print", ToolbarCommand.SubCommandType.PrintReport, "printer"));
        toolbarView.addSeparator();
        toolbarView.addSeparator();
        
        toolbarView.add(new ActionItem("Add Member", ToolbarCommand.SubCommandType.UserAdd, "user_add"));
        toolbarView.add(new ActionItem("Remove Member", ToolbarCommand.SubCommandType.UserDelete, "user_remove"));
        toolbarView.add(new ActionItem("Edit Member Info", ToolbarCommand.SubCommandType.UserEdit, "user_edit"));
        toolbarView.add(new ActionItem("Search Member", ToolbarCommand.SubCommandType.UserSearch, "user_search"));
        toolbarView.addSeparator();
        toolbarView.add(new ActionItem("Donate", Command.SubCommandType.Donate, "donate"));
        toolbarView.addSeparator();
        toolbarView.add(new ActionItem("Settings", ToolbarCommand.SubCommandType.Settings, "settings"));
        
        toolbarView.add( Box.createHorizontalGlue() );
        
        dateTimeButton = new JButton();
        JPanel dateTimePanel = new JPanel()
        {

            @Override
            protected void paintComponent(Graphics g) {
                
            }
           
        };
        
        dateTimePanel.setLayout(new GridLayout(2, 1));
        dateTimePanel.add(new JLabel(DateTime.getFormattedDate()));
        dateTimePanel.add(dateTimeButton);
        dateTimeButton.setSize(new Dimension(50, 50));
        
        toolbarView.add(dateTimePanel);
        
        initDateAndTime();
        
        toolbarView.setRollover(true);
        
        ApplicationManager.getSharedManager().getMainFrame().add(getToolbarView(), BorderLayout.NORTH);
        
    }
    
    private void initDateAndTime()
    {
        dateTimeButton.setText(DateTime.getFormattedTime());
        Timer t = new Timer(0, null);

        t.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                updateDateAndTime();
            }
        });
        
        t.setRepeats(true);
        t.setDelay(1000);
        t.start();
    }
    
    private void updateDateAndTime()
    {
        dateTimeButton.setText(DateTime.getFormattedTime());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public JToolBar getToolbarView() {
        return toolbarView;
    }
}
