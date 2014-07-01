/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import org.ayf.models.Command;

/**
 *
 * @author om
 */
public class SubscriptionReport extends Report{

    public SubscriptionReport(Command.CommandType type) {
        super(type);
    }

    @Override
    public ReportData getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
