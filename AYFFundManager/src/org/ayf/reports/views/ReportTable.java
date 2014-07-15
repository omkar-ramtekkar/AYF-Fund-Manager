/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.ayf.database.entities.Member;
import org.ayf.models.GenericDefaultTableModel;

/**
 *
 * @author oramtekkar
 */
public class ReportTable extends JTable{

    private boolean isInEditMode = false;
    
    static final Member.ColumnNames[] columnIDS = Member.ColumnNames.values();
    
    @Override
    public boolean isCellEditable(int row, int column) {
        if(this.isInEditMode())
        {
            return super.isCellEditable(row, column);
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        
        if(getModel() instanceof GenericDefaultTableModel)
        {
            GenericDefaultTableModel model = (GenericDefaultTableModel) getModel();
            if(model != null)
            {
                Member.ColumnEditor cellEditorType = Member.getColumnEditorTypeForColumnName( model.getTableData().getColumnIDs().get(column));
                
                if(cellEditorType == Member.ColumnEditor.Date)
                {
                    return super.getCellEditor(row, column);
                }
                else if(cellEditorType == Member.ColumnEditor.ComboBox)
                {
                    return new DefaultCellEditor(new JComboBox());
                }
                else if(cellEditorType == Member.ColumnEditor.Label)
                {
                    return super.getCellEditor(row, column);
                }
                else if(cellEditorType == Member.ColumnEditor.Number)
                {
                    return super.getCellEditor(row, column);
                }
            }
        }
        
        return super.getCellEditor(row, column);
    }

    public boolean isInEditMode() {
        return isInEditMode;
    }

    public void setInEditMode(boolean isEditMode) {
        this.isInEditMode = isEditMode;
    }
    
    
}
