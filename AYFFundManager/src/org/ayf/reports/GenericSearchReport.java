/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.ArrayList;
import java.util.Vector;
import org.ayf.command.Command;
import org.ayf.command.ReportCommand;
import org.ayf.database.entities.BaseEntity;
import org.ayf.managers.DatabaseManager;
import org.ayf.reports.views.BaseReportView;
import org.ayf.reports.views.GenericSearchReportView;

/**
 *
 * @author om
 */
public class GenericSearchReport extends Report{

    protected  Class<?> enitityType;
    protected  BaseEntity.DetailsLevel detailLevel;
    
    public GenericSearchReport(Class<?> type, BaseEntity.DetailsLevel level)
    {
        super(ReportCommand.SubCommandType.GenericSearch);
        
        setEnitityType(type);
        setDetailLevel(level);
        setView(new GenericSearchReportView(this));
    }
    
    public GenericSearchReport(Class<?> type, BaseEntity.DetailsLevel level, BaseReportView view, ReportCommand.SubCommandType command)
    {
        super(command);
        
        setEnitityType(type);
        setDetailLevel(level);
        setView(view);
        view.setReport(this);
    }

    public GenericSearchReport(Command.SubCommandType reportType) {
        super(reportType);
    }
    

    public Class<?> getEnitityType() {
        return enitityType;
    }

    public void setEnitityType(Class<?> enitityType) {
        this.enitityType = enitityType;
    }

    public BaseEntity.DetailsLevel getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(BaseEntity.DetailsLevel detailLevel) {
        this.detailLevel = detailLevel;
    }
    
    
    @Override
    public ReportData getData() {
        ArrayList<BaseEntity> entities = DatabaseManager.getAllEntities(getEnitityType());
        
        Vector<BaseEntity> rows = new Vector<BaseEntity>(entities);
        
        return new ReportData(rows, getDetailLevel(), getEnitityType());
    }
    
}
