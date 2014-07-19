/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.models;

import java.sql.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Member;
import org.ayf.reports.ReportData;
import org.ayf.util.DateTime;
import org.ayf.util.Toast;

/**
 *
 * @author om
 */
public class GenericDefaultTableModel extends DefaultTableModel{

    ReportData tableData;
    
    public GenericDefaultTableModel(ReportData data) {
        super(data.getData(), data.getColumns());
        this.tableData = data; 
    }

    public ReportData getTableData() {
        return tableData;
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
        else if(value instanceof Date)
        {
            return DateTime.getFormattedDateSQL((Date) value);
        }
        
        return value;
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        
        super.setValueAt(aValue, row, column);
        
        if(this.getTableData().getEntities() != null)
        {
            BaseEntity entity = this.getTableData().getEntities().get(row);
            BaseEntity.ColumnName columnName = getTableData().getColumnIDs().get(column);
            entity.setValueForField(columnName, aValue);
            super.setValueAt(entity.getValueForField(columnName), row, column);
        }
    }
    
    public Object getEntityValue(int row, int column)
    {
        if(getTableData().getEntities() != null)
        {
            return getTableData().getEntityValue(row, column);
        }
        
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        
        if(getTableData().getColumnIDs().get(column).equals(Member.ColumnName.ID))
        {
            return false;
        }
        else if(getTableData().getColumnIDs().get(column).equals(Member.ColumnName.ReceiptNumber))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    
}
