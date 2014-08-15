/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.ayf.database.entities.BaseEntity;
import org.ayf.models.GenericDefaultTableModel;
import org.ayf.reports.Report;
import org.ayf.reports.ReportData;
import org.ayf.ui.BackgroundPanel;
import org.ayf.util.JTableCellTabbing;
import org.ayf.util.TableAutoFilterAdapter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

/**
 *
 * @author om
 */


public abstract class BaseReportView extends BackgroundPanel implements ReportViewDelegate
{
    protected Report report;
    TableAutoFilterAdapter rowFilter;
    
    public static final int DefaultRowHeight = 30; 

    public BaseReportView(Report report)
    {
        this.report = report;
        rowFilter = null;
        
        this.addContainerListener(new ContainerAdapter() {
            
            @Override
            public void componentAdded(ContainerEvent e)
            {
                decorateReportTable();
            }
        });
        
    }
    
    protected void decorateReportTable()
    {
        JTable reportTable = getReportTable();
        if(reportTable != null)
        {
            reportTable.setRowHeight(DefaultRowHeight);
            JTableHeader header = reportTable.getTableHeader();
            header.setFont( header.getFont().deriveFont(Font.BOLD, 13) );
            
            JTableCellTabbing.setTabMapping(reportTable);
            reportTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            reportTable.setColumnSelectionAllowed(false);        
        }                
    }
    
    protected void startEditingReportTable()
    {
        if(getReportTable() instanceof ReportTable)
        {
            ReportTable table = (ReportTable) getReportTable();
            table.setInEditMode(true);
            table.changeSelection(table.getSelectedRow(), 0, false, false);
        }
    }
    
    protected void finishEditingReportTable()
    {
        if(getReportTable() instanceof ReportTable)
        {
            ReportTable table = (ReportTable) getReportTable();
            table.setInEditMode(false);
            if(table.isEditing())
            {
                table.getCellEditor().stopCellEditing();
            }
        }
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
        
            totalTableWidth += width;
            columnModel.getColumn(column).setMinWidth(width);
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
    
    public boolean saveReportDataToDatabase()
    {
        JTable table = getReportTable();
        if(table != null && getReport() != null)
        {
            if(table.getModel() instanceof GenericDefaultTableModel)
            {
                GenericDefaultTableModel model = (GenericDefaultTableModel) table.getModel();
                return getReport().saveReportDataToDatabase(model.getTableData());
            }
        }
        
        return false;
    }
    
    protected void setupAutoComplete(JComboBox combo, ObjectToStringConverter stringConvertor)
    {
        if(combo != null)
        {
            ObjectToStringConverter convertor = stringConvertor;
            if(stringConvertor == null)
            {
                convertor = ObjectToStringConverter.DEFAULT_IMPLEMENTATION;
            }
            
            AutoCompleteDecorator.decorate(combo, convertor);
        }
    }
    
    protected void setupAutoComplete(JTextField textField, List<BaseEntity> list, ObjectToStringConverter stringConvertor)
    {
        if(textField != null)
        {
            ObjectToStringConverter convertor = stringConvertor;
            if(stringConvertor == null)
            {
                convertor = ObjectToStringConverter.DEFAULT_IMPLEMENTATION;
            }
            
            AutoCompleteDecorator.decorate(textField, list, false, stringConvertor);
        }
    }
    
    //ReportDelegate
    @Override
    public void reportWillLoad(){}
    @Override
    public void reportDidLoad(){}
    @Override
    public void reportWillUnLoad(){}
    @Override
    public void reportDidUnLoad(){}
    @Override
    public ReportData getSelectedReportData()
    {
        if(getReportTable() != null)
        {
            int selectedRows[] = getReportTable().getSelectedRows();
            
            Vector<BaseEntity> entities = getReport().getData().getEntities();
            Vector<Object> dataObjects = getReport().getData().getData();
            
            if(entities != null)
            {
                Vector<BaseEntity> selectedEntities = new Vector<BaseEntity>(selectedRows.length+1);
                
                for (int i : selectedRows) 
                {
                    selectedEntities.add(entities.get(i));
                }
                
                return new ReportData(selectedEntities, getReport().getData().getDetailLevel(), getReport().getData().getDummyEntity().getClass());
            }
            else
            {
                Vector<Object> selectedDataObjects = new Vector<Object>(selectedRows.length+1);
                
                for (int i : selectedRows) 
                {
                    selectedDataObjects.add(dataObjects.get(i));
                }
                
                return new ReportData(dataObjects, getReport().getData().getColumnIDs(), getReport().getData().getDetailLevel(), null);
            }
        }
        
        return null;
    }
}
