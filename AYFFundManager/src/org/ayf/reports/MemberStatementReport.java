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
    
    String memberRegisterationNumber;

    public MemberStatementReport() {
        super(ReportCommand.SubCommandType.StatementsByMember);
        setView(new MemberStatementReportView(this));
        setMemberRegisterationNumber(null);
    }

    @Override
    public ReportData getData() {
        if(getMemberRegisterationNumber() == null || getMemberRegisterationNumber().length() == 0)
        {
            return null;
        }
        
        return DatabaseManager.getMemberStatement(getMemberRegisterationNumber());
    }

    public void setMemberRegisterationNumber(String memberRegisterationNumber) {
        this.memberRegisterationNumber = memberRegisterationNumber;
        updateReport();
    }

    public String getMemberRegisterationNumber() {
        return memberRegisterationNumber;
    }
    
    
    
}
