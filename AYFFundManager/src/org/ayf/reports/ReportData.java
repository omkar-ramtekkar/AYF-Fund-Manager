/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.Vector;
import org.ayf.database.entities.Member;

/**
 *
 * @author om
 */
public class ReportData {
    Vector<?> data;
    Vector<Object> columns;
    Vector<Member.ColumnNames> columnIDs;

    public ReportData(Vector<?> data, Vector<Object> columns, Vector<Member.ColumnNames> columnIDs) {
        this.data = data;
        this.columns = columns;
        this.columnIDs = columnIDs;
    }

    public Vector<?> getData() {
        return data;
    }

    public Vector<Object> getColumns() {
        return columns;
    }
    
    public Vector<Member.ColumnNames> getColumnIDs()
    {
        return this.columnIDs;
    }
}
