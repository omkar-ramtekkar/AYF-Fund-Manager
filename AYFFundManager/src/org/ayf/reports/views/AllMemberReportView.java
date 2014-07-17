/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import java.awt.Point;
import javax.swing.JTable;
import org.ayf.models.GenericDefaultTableModel;
import org.ayf.reports.AllMembersReport;
import org.ayf.reports.Report;
import org.ayf.reports.ReportData;
import org.ayf.util.Toast;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author om
 */
public class AllMemberReportView extends BaseReportView {

    /**
     * Creates new form AllMemberReportView
     */
        
    public AllMemberReportView(Report report) {
        super(report);
        initComponents();
        PromptSupport.setPrompt("Type text to search Member", searchTextField);
        setupTextSearchForReportTable(searchTextField);
    }
    
    @Override
    public void updateView(ReportData data) {
        if(data != null)
        {
            GenericDefaultTableModel model = new GenericDefaultTableModel(data);
            this.allMembersTable.setModel(model);

            adjustReportTableColumns();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchTextField = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        allMembersTable = new ReportTable();
        memberEditButton = new javax.swing.JButton();

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        allMembersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        allMembersTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(allMembersTable);
        allMembersTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        memberEditButton.setText("Edit Member Details");
        memberEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberEditButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(searchTextField)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(refreshButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(memberEditButton))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(searchTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(refreshButton)
                    .add(memberEditButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        if(getReport() != null)
        {
            getReport().updateReport();
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void memberEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberEditButtonActionPerformed
        Point point = new Point();
        point.x = (int) this.jScrollPane1.getVisibleRect().getCenterX();
        point.y = (int) this.jScrollPane1.getVisibleRect().getCenterY();
        
        
        if(this.memberEditButton.getText().equalsIgnoreCase("Save"))
        {
            //Save details
            AllMembersReport allMemberReport = (AllMembersReport) getReport();
            String toastMessage = "Failed to save";
            if(allMemberReport != null)
            {
                this.memberEditButton.setText("Edit Member Details");
                if(saveReportDataToDatabase())
                {
                    toastMessage = "Details saved successfully!";
                }
            }
            
            Toast.showToast(this.memberEditButton, toastMessage, point, 2000);
            finishEditingReportTable();
        }
        else
        {
            this.memberEditButton.setText("Save");
            Toast.showToast(this.memberEditButton, "Click 'Save' to save details", point, 3000);
            startEditingReportTable();
        }
    }//GEN-LAST:event_memberEditButtonActionPerformed

    protected JTable getReportTable()
    {
        return allMembersTable;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable allMembersTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton memberEditButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables

}
