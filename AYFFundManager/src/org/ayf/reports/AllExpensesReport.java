/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.ArrayList;
import java.util.Vector;
import org.ayf.database.entities.Expense;
import org.ayf.managers.DatabaseManager;
import org.ayf.models.Command;
import org.ayf.reports.views.AllExpensesReportView;

/**
 *
 * @author om
 */
public class AllExpensesReport extends Report{

    public AllExpensesReport() {
        super(Command.CommandType.DetailsAllExpenses);
        setView(new AllExpensesReportView(this));
    }

    
    @Override
    public ReportData getData() {
        
        ArrayList<Expense> expenses = DatabaseManager.getExpenses();
        
        Vector rows = new Vector(expenses.size());
        
        for(Expense expense : expenses)
        {
            Vector rowData = expense.getExpenseDetailsForLevel(Expense.DetailsLevel.Complete);
            rows.add(rowData);
        }
        
        return new ReportData(rows, Expense.getColumnsForDetailsLevel(Expense.DetailsLevel.Complete));
        
    }
    
}
