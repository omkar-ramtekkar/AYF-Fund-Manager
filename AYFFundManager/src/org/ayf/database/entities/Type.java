/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.database.entities;

/**
 *
 * @author oramtekkar
 */
public class Type {
    protected int id;
    protected String stringValue;

    public Type(int id, String stringValue) {
        this.id = id;
        this.stringValue = stringValue;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getId() {
        return id;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public String toString() {
        //return "Type{" + "id=" + id + ", stringValue=" + stringValue + '}';
        return getStringValue();
    }
}
