/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.ayf.reports.Report;
import org.ayf.reports.ReportData;
import org.ayf.ui.BackgroundPanel;
import org.ayf.util.TableAutoFilterAdapter;

/**
 *
 * @author om
 */
public abstract class BaseReportView extends BackgroundPanel{
    
    protected Report report;
    TableAutoFilterAdapter rowFilter;

    public BaseReportView(Report report)
    {
        this.report = report;
        rowFilter = null;
    }
    
    
    public abstract void updateView(ReportData data);
    
    protected abstract JTable getReportTable();
    
    protected boolean shouldResizeReportTableColumnWidth() 
    { 
        return true; 
    }
    
    protected int getMinimumColumnWidth() 
    { 
        return 100; 
    }
    
    private void resizeColumnWidth(JTable table) 
    {
        int totalTableWidth = 0;
        
        final TableColumnModel columnModel = table.getColumnModel();
        
        for (int column = 0; column < table.getColumnCount(); column++)
        {
            int width = getMinimumColumnWidth(); // Min width
            
            for (int row = 0; row < table.getRowCount(); row++) 
            {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
            }
        
            totalTableWidth += width;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        
        //table.setSize(totalTableWidth, table.getHeight());
    }
    
    protected void adjustReportTableColumns()
    {
        if(shouldResizeReportTableColumnWidth())
        {
            JTable reportTable = getReportTable();
            if(reportTable != null)
            {
                reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                resizeColumnWidth(reportTable);
            }
        }
    }
    
    protected void setupTextSearchForReportTable(JTextField searchField)
    {
        
        JTable reportTable = getReportTable();
        if(searchField != null && reportTable != null)
        {
            rowFilter = new TableAutoFilterAdapter(reportTable, searchField);
        }
    }    

    public Report getReport() {
        return report;
    }
    
    public void refresh()
    {
        if(report != null)
        {
            report.updateReport();
        }
    }
}
