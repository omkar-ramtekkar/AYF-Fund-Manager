/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import org.ayf.command.ReportCommand;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.views.MemberStatementReportView;

/**
 *
 * @author om
 */
public class MemberStatementReport extends Report{
    
    int memberID;

    public MemberStatementReport() {
        super(ReportCommand.SubCommandType.StatementsByMember);
        setView(new MemberStatementReportView(this));
        this.memberID = 1;
    }

    @Override
    public ReportData getData() {
        return DatabaseManager.getMemberStatement(memberID);
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
        updateReport();
    }

    public int getMemberID() {
        return memberID;
    }
    
    
    
}
