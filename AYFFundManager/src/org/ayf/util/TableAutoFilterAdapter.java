/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.Timer;

/**
 *
 * @author om
 */
public class TableAutoFilterAdapter implements DocumentListener, ActionListener, PropertyChangeListener{
 
    JTable table;
    JTextField searchField;
    TableRowSorter<TableModel> sorter;
    Timer updateTableTimer;

    public TableAutoFilterAdapter(JTable table, JTextField searchField) {
        this.table = table;
        this.searchField = searchField;
        installerListners(true, true);
        setupAutoFilter();
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setTable(JTable table) {
        if(this.table != null)
        {
            this.table.removePropertyChangeListener(this);
        }
        this.table = table;
        installerListners(true, false);
        setupAutoFilter();
    }

    public void setSearchField(JTextField searchField) {
        if(this.searchField != null)
        {
            this.searchField.getDocument().removeDocumentListener(this);
        }
        this.searchField = searchField;
        installerListners(false, true);
    }
    
    void installerListners(boolean forTable, boolean forSearchField)
    {
        if(forTable && this.table != null)
        {
            this.table.addPropertyChangeListener(this);
        }
        
        if(forSearchField && this.searchField != null)
        {
            this.searchField.getDocument().addDocumentListener(this);
        }
    }
    
    void setupAutoFilter()
    {
        if(table != null)
        {
            sorter = new TableRowSorter<TableModel>(table.getModel());
            table.setRowSorter(sorter);
        }
    }
    
    void updateTable()
    {
        if(this.searchField == null) return ;
        
        if (searchField.getText().length() == 0) 
        {
            sorter.setRowFilter(null);
        } 
        else
        {
            sorter.setRowFilter(RowFilter.regexFilter(searchField.getText()));
        }
    }
    
    void fireTableUpdate()
    {
        updateTable();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        fireTableUpdate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        fireTableUpdate();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        fireTableUpdate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTable();
        updateTableTimer = null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equalsIgnoreCase("model"))
        {
            setupAutoFilter();
        }
    }
}
