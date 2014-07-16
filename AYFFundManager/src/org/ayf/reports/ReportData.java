/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.BaseEntity.ColumnName;
import org.ayf.database.entities.BaseEntity.DetailsLevel;

/**
 *
 * @author om
 */
public class ReportData 
{
    Vector<Object> data;
    Vector<Object> columns;
    //TDS cert.
    
    DetailsLevel detailLevel;
    Vector<BaseEntity> entities;
    
    Vector<ColumnName> columnIDs;
    BaseEntity dummyEntity;
    

    public ReportData(Vector<BaseEntity> data, DetailsLevel detailLevel, Class<?> entityClass) {
        try {
            this.dummyEntity = (BaseEntity) entityClass.newInstance();
            this.columns = dummyEntity.getColumnsForDetailsLevel(detailLevel);
            this.columnIDs = getDummyEntity().getColumnIDsForDetailLevel(detailLevel);
        } catch (InstantiationException ex) {
            this.dummyEntity = null;
            Logger.getLogger(ReportData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            this.dummyEntity = null;
            Logger.getLogger(ReportData.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.entities = data;
        this.detailLevel = detailLevel;
        this.data = this.entityToDataArrat(entities);
        
    }
  
    public ReportData(Vector<Object> data, Vector<ColumnName> columnIDs, DetailsLevel detailLevel, Class<?> entityClass) {
        this.data = data;
        this.columnIDs = columnIDs;
        this.entities = null;
        this.detailLevel = DetailsLevel.None;
        
        try {
            this.dummyEntity = (BaseEntity) entityClass.newInstance();
            this.columns = getDummyEntity().getColumnsForDetailsLevel(detailLevel);
        } catch (InstantiationException ex) {
            this.dummyEntity = null;
            Logger.getLogger(ReportData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            this.dummyEntity = null;
            Logger.getLogger(ReportData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ReportData(Vector<Object> data, Vector<Object> columns)
    {
        this.data = data;
        this.columns = columns;
        this.columnIDs = null;
        this.entities = null;
        this.detailLevel = DetailsLevel.None;
        this.dummyEntity = null;
    }
    
      
    private Vector<Object> entityToDataArrat(Vector<BaseEntity> entities)
    {
        Vector<Object> dataArray = new Vector<Object>();
        for (BaseEntity baseEntity : entities)
        {
            dataArray.add(baseEntity.toDataArray(this.detailLevel));
        }
        
        return dataArray;
    }


    public Vector<Object> getData() {
        return data;
    }

    public Vector<Object> getColumns() {
        return columns;
    }
    
    public Vector<ColumnName> getColumnIDs()
    {
        return this.columnIDs;
    }

    public DetailsLevel getDetailLevel() {
        return detailLevel;
    }

    public Vector<BaseEntity> getEntities() {
        return entities;
    }

    public BaseEntity getDummyEntity() {
        return this.dummyEntity;
    }
}
