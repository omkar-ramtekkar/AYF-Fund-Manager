/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import org.ayf.reports.ReportData;

/**
 *
 * @author om
 */
public interface ReportDataProcessor {
    public void processSelectedData(ReportData data, BaseReportView reportView);
}
