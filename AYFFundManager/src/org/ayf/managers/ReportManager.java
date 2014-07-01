/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.ayf.models.Command;
import org.ayf.reports.AllDonationsReport;
import org.ayf.reports.AllMembersReport;
import org.ayf.reports.DonationReport;
import org.ayf.reports.Report;

/**
 *
 * @author om
 */
public class ReportManager
{
    Map<Command.CommandType, Vector<Report>> reports;
    
    public ReportManager(){ initialize(); }
    
    void initialize()
    {
        reports = new HashMap<Command.CommandType, Vector<Report>>();
        initializeDashboardReports();
        initializeDetailsReports();
    }
    
    void initializeDashboardReports()
    {
        Vector<Report> dashboardReports = new Vector();
        dashboardReports.add(new DonationReport());
        reports.put(Command.CommandType.Dashboard, dashboardReports);
    }
    
    void initializeDetailsReports()
    {
        Vector<Report> detailsReports = new Vector();
        detailsReports.add(new AllMembersReport());
        detailsReports.add(new AllDonationsReport());
        reports.put(Command.CommandType.Details, detailsReports);
    }

    public Vector<Report> getReports(Command.CommandType type)
    {
        return reports.get(type);
    }
    
    
    public Report getReportsForType(Command.CommandType type, Command.CommandType subType)
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
