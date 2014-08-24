/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.managers;

import java.util.ArrayList;
import java.util.EventListener;
import org.ayf.database.entities.BaseEntity;

/**
 *
 * @author om
 */
public interface DatabaseUpdateListener extends EventListener
{
    void entityDidAdded(BaseEntity entity);
    void entityDidUpdated(BaseEntity entity);
    void entityDidRemoved(BaseEntity entity);
    void entityDidRead(BaseEntity entity);
    void entitiesDidRead(ArrayList<BaseEntity> entities);
}
