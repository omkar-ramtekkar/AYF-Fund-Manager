/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.sql.Date;

/**
 *
 * @author om
 */
public class StartEndYear
{
    java.sql.Date startYear = null;
    java.sql.Date endYear = null;

    public StartEndYear(java.sql.Date s, java.sql.Date e)
    {
        setStartYear(s);
        setEndYear(e);
    }

    public Date getStartYear() {
        return startYear;
    }

    public Date getEndYear() {
        return endYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(10);
        buffer.append(DateTime.getYear(getStartYear()));
        buffer.append("-");
        buffer.append(DateTime.getYear(getEndYear()));
        return buffer.toString();
    }
}
