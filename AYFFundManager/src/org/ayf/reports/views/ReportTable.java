/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Member;
import org.ayf.models.GenericDefaultTableModel;

/**
 *
 * @author oramtekkar
 */
public class ReportTable extends JTable{

    private boolean isInEditMode = false;
    
    static final Member.ColumnName[] columnIDS = Member.ColumnName.values();
    
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
                Member.ColumnName columnName = model.getTableData().getColumnIDs().get(column);
                BaseEntity.EditorType cellEditorType = model.getTableData().getDummyEntity().getColumnEditorTypeForColumnName(columnName);
                
                if(cellEditorType == Member.EditorType.Date)
                {
                    return super.getCellEditor(row, column);
                }
                else if(cellEditorType == Member.EditorType.ComboBox)
                {                        
                    BaseEntity entity = model.getTableData().getDummyEntity();
                    if(entity != null)
                    {
                        return new DefaultCellEditor(new JComboBox((Vector) entity.getAllValuesForColumnName(columnName)));
                    }
                    
                    return null;
                }
                else if(cellEditorType == Member.EditorType.Label)
                {
                    return super.getCellEditor(row, column);
                }
                else if(cellEditorType == Member.EditorType.Number)
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
