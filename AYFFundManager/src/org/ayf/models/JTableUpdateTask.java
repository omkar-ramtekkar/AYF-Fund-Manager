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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumn;
import org.ayf.database.entities.Member;

public class JTableUpdateTask extends SwingWorker<JTable, Row> {

    JTable      table       = null;

    ArrayList<Member> members   = null;
    
    String[] columnNames = null;

    public JTableUpdateTask(JTable table, ArrayList<Member> members, String[] columnNames){
        this.table = table;
        this.members = members;
        this.columnNames = columnNames;
    }

    @Override
    protected JTable doInBackground() throws Exception {
        List<Row> rows = new ArrayList<Row>();
        Object[] values = new Object[6];
        
        for (Member member : members)
        {
            rows.add(new Row(member.toArray()));
        }
        
        process(rows); 
        return this.table;
    }

    protected void process(List<Row> chunks) {
        ReportTableModel tableModel = (this.table.getModel() instanceof ReportTableModel ? (ReportTableModel) this.table.getModel() : null);
        if (tableModel == null) {
            tableModel = new ReportTableModel(this.columnNames, chunks);
            
            this.table.setModel(tableModel);
        } else {
            tableModel.getRows().addAll(chunks);
        }
        
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
        
        TableColumn column = null;
        while((column = columns.nextElement()) != null)
        {
            column.setPreferredWidth(25);
        }
        
        tableModel.fireTableDataChanged();
    }
}