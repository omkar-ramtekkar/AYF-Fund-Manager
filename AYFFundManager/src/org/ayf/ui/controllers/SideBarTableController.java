/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import org.ayf.managers.ApplicationManager;
import org.ayf.models.SideBarTableModel;
import org.ayf.ui.ButtonRenderer;
import org.ayf.ui.MainFrame;

/**
 *
 * @author om
 */
public class SideBarTableController implements MouseListener, ActionListener, KeyListener {
    private final JTable table;
    private final SideBarTableModel model;

    public SideBarTableController() {
        
        this.model = new SideBarTableModel();
        this.table = new JTable(this.model);
        this.table.setRowMargin(5);
        this.table.setRowHeight(60);
        this.table.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
        this.table.getColumnModel().getColumn(0).setCellRenderer(new ButtonRenderer(0));
        this.table.setTableHeader(null);
        
        MainFrame mainFrame = ApplicationManager.getSharedManager().getMainFrame();
        JScrollPane scrollPane = new JScrollPane(getTable());
        
        mainFrame.getSplitPane().setLeftComponent(scrollPane);
        mainFrame.getSplitPane().setSize(mainFrame.getSize());
        
        this.table.addMouseListener(this);
        this.table.addKeyListener(this);
        
        mainFrame.getSplitPane().setRightComponent(new JPanel());
        
        setDefaultSplitPaneSize();
    }
    
    public void setDefaultSplitPaneSize()
    {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                ApplicationManager.getSharedManager().getMainFrame().getSplitPane().setDividerLocation(200);
            }
        });
    }
   
    public final JTable getTable() {
        return table;
    }

    public TableModel getModel() {
        return model;
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.model.clickEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    
    
}
