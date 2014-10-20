/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import org.ayf.command.Command;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.views.MemberRegisterationReportView;

/**
 *
 * @author om
 */
public class MemberRegisterationReport extends Report
{

    public MemberRegisterationReport()
    {
        super(Command.SubCommandType.MemberRegisterationStatement);
        setView(new MemberRegisterationReportView(this));
    }

    @Override
    public ReportData getData() {
        MemberRegisterationReportView view = (MemberRegisterationReportView)getView();
        
        if(view == null) return null;
        
        return DatabaseManager.getMemberRegisterationTrendData(view.getGroupingOption(), view.getStartYear(), view.getEndYear());
        
    }
    
}
