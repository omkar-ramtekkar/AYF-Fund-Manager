/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.ayf.command.ReportCommand;
import org.ayf.reports.AllCashFlowsReport;
import org.ayf.reports.AllDonationsReport;
import org.ayf.reports.AllExpensesReport;
import org.ayf.reports.AllMembersReport;
import org.ayf.reports.AllsubscriptionAmountReport;
import org.ayf.reports.DonationReport;
import org.ayf.reports.MemberStatementReport;
import org.ayf.reports.Report;

/**
 *
 * @author om
 */
public class ReportManager
{
    Map<ReportCommand.SubCommandType, Vector<Report>> reports;
    
    public ReportManager(){ initialize(); }
    
    void initialize()
    {
        reports = new HashMap<ReportCommand.SubCommandType, Vector<Report>>();
        initializeDashboardReports();
        initializeDetailsReports();
        initializeStatementsReports();
    }
    
    void initializeDashboardReports()
    {
        Vector<Report> dashboardReports = new Vector();
        dashboardReports.add(new DonationReport());
        
        reports.put(ReportCommand.SubCommandType.Dashboard, dashboardReports);
        
    }
    
    void initializeDetailsReports()
    {
        Vector<Report> detailsReports = new Vector();
        detailsReports.add(new AllMembersReport());
        detailsReports.add(new AllDonationsReport());
        detailsReports.add(new AllExpensesReport());
        detailsReports.add(new AllCashFlowsReport());
        detailsReports.add(new AllCashFlowsReport());
        detailsReports.add(new AllsubscriptionAmountReport());
        
        reports.put(ReportCommand.SubCommandType.Details, detailsReports);
    }
    
    void initializeStatementsReports()
    {
        Vector<Report> statementReports = new Vector();
        statementReports.add(new MemberStatementReport());
        
        reports.put(ReportCommand.SubCommandType.Statements, statementReports);
    }

    public Vector<Report> getReports(ReportCommand.SubCommandType type)
    {
        return reports.get(type);
    }
    
    
    public Report getReportsForType(ReportCommand.SubCommandType type, ReportCommand.SubCommandType subType)
    {
        Vector<Report> typeReports = getReports(type);
        Report requestedReport = null;

        if(typeReports != null)
        {
            for (Report report : typeReports) 
            {
                if(report != null)
                {
                    if(report.getReportType() == subType)
                    {
                        requestedReport = report;
                        break;
                    }

                }
            }
        }
        
        return requestedReport;
    }
   
}
