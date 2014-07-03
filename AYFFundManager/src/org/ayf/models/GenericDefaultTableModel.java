/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.models;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author om
 */
public class GenericDefaultTableModel extends DefaultTableModel{

    public GenericDefaultTableModel() {
    }

    public GenericDefaultTableModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public GenericDefaultTableModel(Vector columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public GenericDefaultTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public GenericDefaultTableModel(Vector data, Vector columnNames) {
        super(data, columnNames);
    }

    public GenericDefaultTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Object value = super.getValueAt(row, column);
        if(value == null)
        {
            return "";
        }
        
        return value;
    }
}
