/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.print;

/**
 *
 * @author om
 */
public interface ProgressTracker {
    
    public void updateProgress(int progress);
    public void updateMessage(String message);
}
