/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.print;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import org.apache.commons.io.FileUtils;
import org.ayf.managers.ResourceManager;
import org.ayf.models.GenericDefaultTableModel;
import org.ayf.reports.views.BaseReportView;
import org.ayf.reports.views.ReportTable;
import org.ayf.util.TableUtil;

/**
 *
 * @author om
 */
public class PrintableView extends  JPanel implements Printable, HTMLSerializable {

    BaseReportView baseReportView;
    JScrollPane tableScrollPane;
    JTable reportTable;
    
    MessageFormat header;
    MessageFormat footer;
    boolean printPageWidth;
    
    Vector<TableColumnItem> tableColumns = new Vector<TableColumnItem>();

    public PrintableView() {
        setLayout(new BorderLayout());
    }

    public PrintableView(BaseReportView reportView) {
        this();
        this.baseReportView = reportView;
    }

    public BaseReportView getReportView() {
        return baseReportView;
    }

    public void setReportView(BaseReportView reportView) {
        this.baseReportView = reportView;
    }    

    public JTable getReportTable() {
        return reportTable;
    }

    public void setReportTable(JTable reportTableUnused) 
    {
        ReportTable table = new ReportTable();
        table.setModel(new GenericDefaultTableModel(baseReportView.getReport().getData()));
        
        if(this.reportTable != null)
        {
            this.tableScrollPane.remove(this.reportTable);
        }
        
        this.reportTable = table;
        this.reportTable.setShowGrid(true);
        
        if(tableScrollPane == null)
        {
            tableScrollPane = new JScrollPane();
            this.add(this.tableScrollPane, BorderLayout.CENTER);
        }
        
        
        TableUtil.decorateTable(this.reportTable);
        TableUtil.adjustReportTableColumns(this.reportTable);
        
        this.tableScrollPane.setViewportView(this.reportTable);
        
        Vector<TableColumnItem> columnList = new Vector<TableColumnItem>(reportTable.getColumnCount());
        
        Enumeration<TableColumn> tableColEnum = reportTable.getColumnModel().getColumns();
        int i=0;
        while (tableColEnum.hasMoreElements()) {
            TableColumn column = tableColEnum.nextElement();
            int index = i;
            String name = reportTable.getColumnName(index);
            
            columnList.add(new TableColumnItem(column, name, index));
            ++i;
        }
        
        setTableColumns(columnList);
    }

    @Override
    public String getHtml()
    {
        File file = ResourceManager.getResource("/org/ayf/resources/misc/", "HTMLLayoutBaseReport.html");
        if(file.exists())
        {
            try {
                String html = FileUtils.readFileToString(file);
                return prepareHtml(html);
            } catch (IOException ex) {
                Logger.getLogger(org.ayf.reports.views.HTMLPrintable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    public Vector<TableColumnItem> getTableColumns() {
        return tableColumns;
    }

    private void setTableColumns(Vector<TableColumnItem> tableColumns) {
        this.tableColumns = tableColumns;
    }

    
    public void configureView(Vector<Object> selectedColumns)
    {
        JTable table = getReportTable();
        
        if(table != null)
        {
            int colCount = reportTable.getColumnCount();
            
            int i=0;
            while(i<colCount)
            {
                reportTable.removeColumn(reportTable.getColumnModel().getColumn(0));
                ++i;
            }
            
            for (Object item : selectedColumns) 
            {
                TableColumnItem tableColumn = (TableColumnItem)item;
                table.addColumn(tableColumn.getColumn());
            }
        }
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException 
    {
        return getReportTable().getPrintable(this.printPageWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL,
                this.header,
                this.footer).
                print(graphics, pageFormat, pageIndex);
        
    }

    String getDisplayName() {
        return baseReportView.getDisplayName();
    }

    public void setHeader(MessageFormat header) {
        this.header = header;
    }

    public void setFooter(MessageFormat footer) {
        this.footer = footer;
    }

    void setPrintPageWidth(boolean printPageWidth) {
        this.printPageWidth = printPageWidth;
    }

    public MessageFormat getHeader() {
        return header;
    }

    public MessageFormat getFooter() {
        return footer;
    }

    public boolean isPrintPageWidth() {
        return printPageWidth;
    }

    private String prepareHtml(String html) {
        
        String tableHTML = TableUtil.toHTML(getReportTable());
        
        return html.replace("__report__", tableHTML);
    }

    class TableColumnItem
    {
        TableColumn column;
        String name;
        int index;

        public TableColumnItem(TableColumn column, String name, int index) {
            this.column = column;
            this.name = name;
            this.index = index;
        }

        public TableColumn getColumn() {
            return column;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }

        public void setColumn(TableColumn column) {
            this.column = column;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
