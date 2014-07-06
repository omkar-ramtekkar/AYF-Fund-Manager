/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableModel;
import org.ayf.command.Command;
import org.ayf.managers.ApplicationManager;
import org.ayf.command.ReportCommand;
import org.ayf.models.SideBarTableModel;
import org.ayf.ui.SideBarTableCellRenderer;
import org.ayf.ui.MainFrame;

/**
 *
 * @author om
 */
public class SideBarTableController implements ActionListener, MouseListener, MouseMotionListener {
    private final JTable table;
    private final SideBarTableModel model;
    protected EventListenerList listenerList = new EventListenerList();

    public SideBarTableController() {
        
        this.model = new SideBarTableModel();
        this.table = new JTable(this.model);
        this.table.setRowMargin(5);
        this.table.setRowHeight(45);
        
        this.table.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
        this.table.getColumnModel().getColumn(0).setCellRenderer(new SideBarTableCellRenderer(0));
        this.table.setTableHeader(null);
        
        MainFrame mainFrame = ApplicationManager.getSharedManager().getMainFrame();
        JScrollPane scrollPane = new JScrollPane(getTable());
        
        mainFrame.setLeftView(scrollPane);
        mainFrame.getSplitPane().setSize(mainFrame.getSize());
        
        this.table.addMouseListener(this);
        this.table.addMouseMotionListener(this);
       
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

    
    public void addActionListener(ActionListener listener) {
        listenerList.add(ActionListener.class, listener);
    }


    public void removeActionListener(ActionListener listener) {
        listenerList.remove(ActionListener.class, listener);
    }
    
    
    protected void fireActionPerformed(ActionEvent e) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i=listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.class) {
                ((ActionListener)listeners[i+1]).actionPerformed(e);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Retain old selection
        SideBarTableModel.Option oldOption = this.model.getSelectedSubOption();
        
        //Update model with new selection
        this.model.mouseClicked(e);
        
        SideBarTableModel.Option newOption = this.model.getSelectedSubOption();
        
        if(newOption == null)
        {
            newOption = this.model.getSelectedHelderOption();
        }
        
        if(newOption != null && oldOption != newOption)
        {
            long uniqueId = System.currentTimeMillis() ;
            fireActionPerformed(new ActionEvent(new ReportCommand(newOption, newOption.getOptionType()), (int)uniqueId, Command.CommandType.Report.toString()));
        }
        
        this.table.repaint();
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
        this.model.mouseExited(e);
        this.table.repaint();
    }    

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.model.mouseMoved(e);
        this.table.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.table.repaint();
    }
}
