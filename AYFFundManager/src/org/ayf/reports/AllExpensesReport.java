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
import org.ayf.command.ReportCommand;
import org.ayf.database.entities.BaseEntity;
import org.ayf.reports.views.AllExpensesReportView;

/**
 *
 * @author om
 */
public class AllExpensesReport extends Report{

    public AllExpensesReport() {
        super(ReportCommand.SubCommandType.DetailsAllExpenses);
        setView(new AllExpensesReportView(this));
    }

    
    @Override
    public ReportData getData() {
        
        ArrayList<BaseEntity> expenses = DatabaseManager.getAllEntities(Expense.class);
        
        Vector<BaseEntity> rows = new Vector<BaseEntity>(expenses);
        
        return new ReportData(rows, BaseEntity.DetailsLevel.Database, Expense.class);
    }
}
