/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.Vector;

/**
 *
 * @author om
 */
public class ReportData {
    Vector<?> data;
    Vector<Object> columns;

    public ReportData(Vector<?> data, Vector<Object> columns) {
        this.data = data;
        this.columns = columns;
    }

    public Vector<?> getData() {
        return data;
    }

    public Vector<Object> getColumns() {
        return columns;
    }
}
