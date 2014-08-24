/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ayf.database.entities.BaseEntity;

/**
 *
 * @author om
 */
public class CacheManager implements DatabaseUpdateListener{
    
    Map<String, ArrayList<BaseEntity>> entityCacheMap;
    Map<String, ArrayList<Object>> objectCacheMap;
    
    private static CacheManager cacheInstance = null;

    private CacheManager() {
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "CacheManager Initialize");
        entityCacheMap = new HashMap<String, ArrayList<BaseEntity>>();
        objectCacheMap = new HashMap<String, ArrayList<Object>>(20);
        DatabaseManager.addDatabaseUpdateListner(this);
    }
    
    static private CacheManager getInstance()
    {
        if(cacheInstance == null) cacheInstance = new CacheManager();
        
        return cacheInstance;
    }
    
    static public void initialize()
    {
        getInstance();
    }
    
    private void clearObjects(Class<?> type)
    {
        if(type == null) return;
        
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Clearing cache for type - {0}", type.getName());
        
        objectCacheMap.put(type.getName(), null);
    }
    
    private void clearEntities(Class<?> type)
    {
        if(type == null) return;
        
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Clearing cache for type - {0}", type.getName());
        entityCacheMap.put(type.getName(), null);
    }
    
    public ArrayList<Object> getObjects(Class<?> type) throws IllegalArgumentException
    {
        if(type == null) throw new IllegalArgumentException("Class type is null");
        
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Entity read request for type - {0}", type.getName());
        
        if(objectCacheMap.containsKey(type.getName()) == false) return null;
        
        return objectCacheMap.get(type.getName());
    }
    
    public ArrayList<BaseEntity> getEntities(Class<?> type) throws IllegalArgumentException
    {
        if(type == null) throw new IllegalArgumentException("Entity type is null");
        
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Entity read request for type - {0}", type.getName());
        if(entityCacheMap.containsKey(type.getName()) == false) return null;
        
        return entityCacheMap.get(type.getName());
    }
    
    static public ArrayList<Object> getCacheObjects(Class<?> type)
    {
        return getInstance().getObjects(type);
    }
    
    static public ArrayList<BaseEntity> getCacheEntities(Class<?> type)
    {
        return getInstance().getEntities(type);
    }
    
    @Override
    public void entityDidAdded(BaseEntity entity) {
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Event for type - {0}", entity.getClass().getName());
        clearEntities(entity.getClass());
    }

    @Override
    public void entityDidUpdated(BaseEntity entity) {
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Event for type - {0}", entity.getClass().getName());
        clearEntities(entity.getClass());
    }

    @Override
    public void entityDidRemoved(BaseEntity entity) {
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Event for type - {0}", entity.getClass().getName());
    }

    @Override
    public void entityDidRead(BaseEntity entity) {
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Event for type - {0}", entity.getClass().getName());
    }

    @Override
    public void entitiesDidRead(ArrayList<BaseEntity> entities) {
        if(entities == null || entities.isEmpty()) return;
        
        BaseEntity entity = entities.get(0);
        
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Event for type - {0}", entity.getClass().getName());
        Logger.getLogger(CacheManager.class.getName()).log(Level.INFO, "Updating cache for type - {0}. Total entity count - {1}", new Object[]{entity.getClass().getName(), entities.size()});
        this.entityCacheMap.put(entity.getClass().getName(), entities);
    }
    
}
