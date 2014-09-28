/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;


import java.awt.IllegalComponentStateException;
import javax.swing.JTable;
import org.ayf.reports.Report;
import org.ayf.reports.ReportData;
import org.ayf.util.ScreenUtil;
import org.ayf.util.Toast;

/**
 *
 * @author om
 */
public class AllSubscriptionAmountsReportView extends BaseReportView {

    /**
     * Creates new form AllSubscriptionAmountsReportView
     */
    public AllSubscriptionAmountsReportView(Report report) {
        super(report);
        initComponents();
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
        reportTable = new ReportTable();
        editButton = new javax.swing.JButton();

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        reportTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(reportTable);

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
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
                        .add(editButton))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {editButton, refreshButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(searchTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(refreshButton)
                    .add(editButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
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

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed

        if(this.editButton.getText().equalsIgnoreCase("Save"))
        {
            //Save details
            String toastMessage = "Failed to save";
            boolean success = false;
            if(getReport() != null)
            {
                this.editButton.setText("Edit subscription details");
                if(saveReportDataToDatabase())
                {
                    toastMessage = "Details saved successfully!";
                    success = true;
                }
            }

            try {
                Toast.showToast(toastMessage, ScreenUtil.getCenterPointOnScreen(getReportTable()), success);
            } catch (IllegalArgumentException ex) {
            } catch (IllegalComponentStateException ex) {
            }
            finishEditingReportTable();
        }
        else
        {
            this.editButton.setText("Save");
            try {
                Toast.showToast("Click 'Save' to save details", ScreenUtil.getCenterPointOnScreen(getReportTable()), true);
            } catch (IllegalArgumentException ex) {
            } catch (IllegalComponentStateException ex) {
            }
            startEditingReportTable();
        }
    }//GEN-LAST:event_editButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable reportTable;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateViewDecoration(ReportData data) {
    }

    @Override
    protected JTable getReportTable() {
        return this.reportTable;
    }
}
