/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui.controllers;

import org.ayf.ui.ButtonRenderer;
import org.ayf.models.SideBarTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author om
 */
public class SideBarTableController implements MouseListener, ActionListener, KeyListener {
    private JTable table;
    private SideBarTableModel model;

    public SideBarTableController() {
        
        this.model = new SideBarTableModel();
        this.table = new JTable(this.model);
        this.table.setRowMargin(5);
        this.table.setRowHeight(60);
        this.table.getColumnModel().getColumn(0).setCellRenderer(new ButtonRenderer(0));
        this.table.setTableHeader(null);
        
        this.table.addMouseListener(this);
        this.table.addKeyListener(this);
    }
   
    public JTable getTable() {
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
