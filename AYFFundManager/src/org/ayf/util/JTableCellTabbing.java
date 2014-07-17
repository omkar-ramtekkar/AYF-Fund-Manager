/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import org.ayf.reports.views.ReportTable;

/**
 *
 * @author oramtekkar
 */
public class JTableCellTabbing {
    /**
     * 
     * Creates a new {@code JTableCellTabbing} object.
     *
     *
     */
    private JTableCellTabbing() {        
    }

    /**
     * 
     * Set Action Map for tabbing and shift-tabbing for the JTable
     *
     *
     */
    @SuppressWarnings("serial")
    static public void setTabMapping(final JTable theTable) {
        if (theTable == null) {
            throw new IllegalArgumentException("theTable is null");
        }

        // Get Input and Action Map to set tabbing order on the JTable
        InputMap im = theTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap am = theTable.getActionMap();

        // Get Tab Keystroke
        KeyStroke tabKey = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);                    
        am.put(im.get(tabKey), new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                if(table == null) return;
                
                int startCol = 0;
                int endCol = table.getColumnCount() - 1;
                int startRow = 0;
                int endRow = table.getModel().getRowCount() - 1;
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();

                col++;

                // Move to next row and left column
                if (col > endCol) {
                    col = startCol;
                    row++;
                }

                // Move to top row
                if (row > endRow ) {
                    row = startRow;
                }

                // Move cell selection
                table.changeSelection(row, col, false, false);
                if(table instanceof ReportTable)
                {
                    ReportTable reportTable = (ReportTable) table;
                    if(reportTable.isInEditMode())
                    {
                        reportTable.editCellAt(row, col);
                    }
                }
            }            
        });

        // Get Shift tab Keystroke
        KeyStroke shiftTab = 
            KeyStroke.getKeyStroke(KeyEvent.VK_TAB, java.awt.event.InputEvent.SHIFT_DOWN_MASK);                    
        am.put(im.get(shiftTab), new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                JTable table = (JTable)e.getSource();
                if(table == null) return;
                
                int startCol = 0;
                int endCol = table.getColumnCount() - 1;
                int startRow = 0;
                int endRow = table.getModel().getRowCount() - 1;
                
                
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();

                col--;

                // Move to top right cell
                if (col < startCol) {
                    col = endCol;
                    row--;
                }

                // Move to bottom row
                if (row < startRow ) {
                    row = endRow;
                }

                // Move cell selection
                table.changeSelection(row, col, false, false);
                if(table instanceof ReportTable)
                {
                    ReportTable reportTable = (ReportTable) table;
                    if(reportTable.isInEditMode())
                    {
                        reportTable.editCellAt(row, col);
                    }
                }
            }            
        }); 
    }
}
