/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.models.Command;
import org.ayf.reports.views.AllMemberReportView;

/**
 *
 * @author om
 */
public class AllMembersReport extends Report{

    public AllMembersReport() {
        super(Command.CommandType.DetailsAllMembers);
        setView(new AllMemberReportView(this));
    }

    @Override
    public ReportData getData() {
        ArrayList<Member> members = DatabaseManager.getRegisteredMembers();
        
        Vector rows = new Vector(members.size());
        
        for(Member member : members)
        {
            Vector rowData = member.getMemberDetailsForLevel(Member.DetailsLevel.Basic);
            rows.add(rowData);
        }
        
        return new ReportData(rows, Member.getColumnsForDetailsLevel(Member.DetailsLevel.Basic));
    }    
}
