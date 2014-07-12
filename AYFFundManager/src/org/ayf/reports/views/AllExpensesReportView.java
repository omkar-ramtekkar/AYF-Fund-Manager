/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import javax.swing.JTable;
import org.ayf.models.GenericDefaultTableModel;
import org.ayf.reports.Report;
import org.ayf.reports.ReportData;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author om
 */
public class AllExpensesReportView extends BaseReportView {

    /**
     * Creates new form AllExpensesReportView
     */
    public AllExpensesReportView(Report report) {
        super(report);
        initComponents();
        
        PromptSupport.setPrompt("Type text to search Expense", searchTextField);
        setupTextSearchForReportTable(searchTextField);
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
        allExpensesTable = new javax.swing.JTable();

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        allExpensesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        allExpensesTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(allExpensesTable);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(searchTextField)
                        .add(18, 18, 18)
                        .add(refreshButton))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(searchTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(refreshButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        if(report != null)
        {
            report.updateReport();
        }
    }//GEN-LAST:event_refreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable allExpensesTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateView(ReportData data) {
        if(data != null)
        {
            this.allExpensesTable.setModel(new GenericDefaultTableModel(data.getData(), data.getColumns()));
            adjustReportTableColumns();
        }
    }

    @Override
    protected JTable getReportTable() {
        return this.allExpensesTable;
    }
}
