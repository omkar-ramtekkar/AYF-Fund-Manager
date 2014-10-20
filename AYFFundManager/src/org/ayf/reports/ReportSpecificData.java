/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports;

/**
 *
 * @author om
 */
public class ReportSpecificData {
    int totalMemberCount;
    int totalActiveMembers;
    int totalDeactiveMembers;

    public int getTotalMemberCount() {
        return totalMemberCount;
    }

    public int getTotalActiveMembers() {
        return totalActiveMembers;
    }

    public int getTotalDeactiveMembers() {
        return totalDeactiveMembers;
    }

    public void setTotalMemberCount(int totalMemberCount) {
        this.totalMemberCount = totalMemberCount;
    }

    public void setTotalActiveMembers(int totalActiveMembers) {
        this.totalActiveMembers = totalActiveMembers;
    }

    public void setTotalDeactiveMembers(int totalDeactiveMembers) {
        this.totalDeactiveMembers = totalDeactiveMembers;
    }
    
    
}
