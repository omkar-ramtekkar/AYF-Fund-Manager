/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.print;

/**
 *
 * @author om
 */
public class ReportPrintable extends BasicPrintable {

    public ReportPrintable() {
        super(PrintInformation.getStandardPrintInformation());
    }

    
    public ReportPrintable(PrintInformation printInfo) {
        super(printInfo);
    }
    
}
