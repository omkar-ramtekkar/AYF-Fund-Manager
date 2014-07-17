/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.ArrayList;
import java.util.Vector;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.command.ReportCommand;
import org.ayf.database.entities.BaseEntity;
import org.ayf.reports.views.AllMemberReportView;

/**
 *
 * @author om
 */
public class AllMembersReport extends Report{

    public AllMembersReport() {
        super(ReportCommand.SubCommandType.DetailsAllMembers);
        setView(new AllMemberReportView(this));
    }

    @Override
    public ReportData getData() {
        ArrayList<BaseEntity> members = DatabaseManager.getAllEntities(Member.class);
        
        Vector<BaseEntity> rows = new Vector<BaseEntity>(members);
        
        return new ReportData(rows, BaseEntity.DetailsLevel.Basic, Member.class);
    }
}
