/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.ayf.command.Command;
import org.ayf.database.entities.BaseEntity;
import org.ayf.models.GenericDefaultTableModel;
import org.ayf.reports.Report;
import org.ayf.reports.ReportData;
import org.ayf.reports.print.PrintableView;
import org.ayf.ui.BackgroundPanel;
import org.ayf.util.TableAutoFilterAdapter;
import org.ayf.util.TableUtil;
import org.ayf.util.Toast;
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
    
    public PrintableView getPrintableView() throws PrintException 
    {
        if(getReport().getData() == null) throw new PrintException("Report data not available");
        
        PrintableView view = new PrintableView(this);
        view.setReportTable(getReportTable());
        return view;
    }
    
    protected void decorateReportTable()
    {
        TableUtil.decorateTable(getReportTable());
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
    
    public JTable getReportViewTable()
    {
        return getReportTable();
    }
    
    public void updateReportView(final ReportData data)
    {
        if(data != null)
        {
            this.getReportViewTable().setModel(new GenericDefaultTableModel(data));
            adjustReportTableColumns();
        }
        
        updateViewDecoration(data);
        
        Runnable showToastThread = new Runnable() 
        {
            @Override
            public void run() 
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BaseReportView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(data != null)
                {
                    Toast.showToastOnComponentCenter(getReportViewTable(), data.getData().size() + " Records Found", true);
                }
                else
                {
                    Toast.showToastOnComponentCenter(getReportViewTable(), "Report data not available", true);
                }
            }
        };
        
        Thread t = new Thread(showToastThread);
        t.start();
    }
    
    public abstract void updateViewDecoration(ReportData data);
    
    protected abstract JTable getReportTable();
    
    public void configure(Command.SubCommandType type){}
    
    protected boolean shouldResizeReportTableColumnWidth() 
    { 
        return true; 
    }
    
    protected int getMinimumColumnWidth() 
    { 
        return 100; 
    }
    
    protected void adjustReportTableColumns()
    {
        if(shouldResizeReportTableColumnWidth())
        {
            TableUtil.adjustReportTableColumns(getReportTable());
        }
    }
    
    protected void setupTextSearchForReportTable(JTextField searchField)
    {
        JTable reportTable = getReportTable();
        if(reportTable != null)
        {
            rowFilter = new TableAutoFilterAdapter(reportTable, searchField);
        }
    }    

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
        if(getReport() != null)
        {
            getReport().updateReport();
        }
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

    public String getDisplayName() {
        return getReport().getName();
    }
}
