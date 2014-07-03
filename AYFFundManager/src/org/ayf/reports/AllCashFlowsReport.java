/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.ArrayList;
import java.util.Vector;
import org.ayf.database.entities.CashFlow;
import org.ayf.managers.DatabaseManager;
import org.ayf.models.Command;
import org.ayf.reports.views.AllCashFlowsReportView;

/**
 *
 * @author om
 */
public class AllCashFlowsReport extends Report{

    public AllCashFlowsReport()
    {
        super(Command.CommandType.DetailsAllCashFlows);
        setView(new AllCashFlowsReportView(this));
    }

    @Override
    public ReportData getData() {
        ArrayList<CashFlow> transactions = DatabaseManager.getBankTransactions();
        
        Vector rows = new Vector(transactions.size());
        
        for(CashFlow transaction : transactions)
        {
            Vector rowData = transaction.getTransactionDetailsForLevel(CashFlow.DetailsLevel.Complete);
            rows.add(rowData);
        }
        
        return new ReportData(rows, CashFlow.getColumnsForDetailsLevel(CashFlow.DetailsLevel.Complete));
    }
    
}
