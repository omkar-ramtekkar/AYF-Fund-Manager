/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

/**
 *
 * @author om
 */
public interface ReportDelegate 
{
    public void reportWillLoad();
    public void reportDidLoad();
    public void reportWillUnLoad();
    public void reportDidUnLoad();
}
