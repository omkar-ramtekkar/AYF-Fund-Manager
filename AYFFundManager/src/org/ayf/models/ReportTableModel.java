/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.models;

/**
 *
 * @author oramtekkar
 */

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

import javax.swing.table.AbstractTableModel;

/**
 * Simple wrapper around Object[] representing a row from the ResultSet.
 */
class Row {
    private final Object[]  values;

    public Row(Object[] values) {
        this.values = values;
    }

    public int getSize() {
        return values.length;
    }

    public Object getValue(int i) {
        return values[i];
    }
}

// TableModel implementation that will be populated by SwingWorker.
public class ReportTableModel extends AbstractTableModel {
    private final String[] rsmd;

    private List<Row>               rows;

    public ReportTableModel(String[] rsmd, List<Row> rows) {
        this.rsmd = rsmd;
        if (rows != null) {
            this.rows = rows;
        } else {
            this.rows = new ArrayList<Row>();
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() 
    {
        return rsmd.length;
    }

    public Object getValue(int row, int column) {
        return rows.get(row).getValue(column);
    }

    public String getColumnName(int col) {
        try {
            return rsmd[col];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Class<?> getColumnClass(int col) {
        return JLabel.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex > rows.size()){
            return null;
        }
        return rows.get(rowIndex).getValue(columnIndex);
    }

    public List<Row> getRows() {
        return this.rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}