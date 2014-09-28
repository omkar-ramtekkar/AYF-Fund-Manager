/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.awt.Component;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import static org.ayf.reports.views.BaseReportView.DefaultRowHeight;

/**
 *
 * @author om
 */
public class TableUtil {
    
    public static void adjustReportTableColumns(JTable table)
    {
        if(table == null) return;
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resizeColumnWidth(table);
    }
    
    static void resizeColumnWidth(JTable table) 
    {
        final TableColumnModel columnModel = table.getColumnModel();
        
        JLabel testLabel = new JLabel();
        testLabel.setFont(table.getTableHeader().getFont());
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        Font font = table.getTableHeader().getFont();
                    
        for (int column = 0; column < table.getColumnCount(); column++)
        {
            String columnName = table.getColumnName(column);
            testLabel.setText(columnName);
            testLabel.setSize(testLabel.getPreferredSize());
            
            int width = Math.max((int) font.getStringBounds(columnName, frc).getWidth() + 30, table.getColumnModel().getColumn(column).getWidth());
 
            //disabled row width calculation for now
            for (int row = 0; row < table.getRowCount(); row++) 
            {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
            }
        
            columnModel.getColumn(column).setMinWidth(width);
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public static void decorateTable(JTable table) 
    {
        if(table != null)
        {
            table.setRowHeight(DefaultRowHeight);
            JTableHeader header = table.getTableHeader();
            header.setFont( header.getFont().deriveFont(Font.BOLD, 13) );
            
            JTableCellTabbing.setTabMapping(table);
            table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            table.setColumnSelectionAllowed(false);        
        }
    }
    
    public static String toHTML(JTable table, int width)
    {
        if(table == null) return HTMLUtil.table(0, false, false, 0, 0, 0, null, null, null, null);
        
        int rows = table.getRowCount();
        int cols = table.getColumnCount();

        Vector header = new Vector(cols);
        for (int i = 0; i < cols; ++i)
        {
            header.add(table.getColumnName(i));
        }
        
        String th = HTMLUtil.tableHeader(header, HTMLUtil.TextAlignment.center);
        
        StringBuilder trs = new StringBuilder(cols * 20 * rows);
        
        Vector tr = new Vector(rows);
        
        for (int row = 0; row < rows; ++row) 
        {
            tr.removeAllElements();
            
            for (int col = 0; col < cols; ++col)
            {
                Object value = table.getValueAt(row, col);
                tr.add(value);
            }
            
            trs.append(HTMLUtil.tableRow(tr, HTMLUtil.TextAlignment.left));
        }
        
        String tb = HTMLUtil.tableBody(trs.toString());
        
        return HTMLUtil.table(1, table.getShowVerticalLines(), table.getShowHorizontalLines(), 5, 0, width, "", "", th, tb);
    }
}
