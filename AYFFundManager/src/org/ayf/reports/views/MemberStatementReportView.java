/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.managers.ResourceManager;
import org.ayf.models.GenericDefaultTableModel;
import org.ayf.reports.MemberStatementReport;
import org.ayf.reports.Report;
import org.ayf.reports.ReportData;
import org.ayf.ui.InformationPanel;
import org.ayf.ui.MemberFrame;
import org.ayf.util.DateTime;
import org.ayf.util.Toast;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author om
 */
public class MemberStatementReportView extends BaseReportView {

    /**
     * Creates new form MemberStatement
     */
    public MemberStatementReportView(Report report) {
        super(report);
        initComponents();
        PromptSupport.setPrompt("Type text to filter member statement rows", searchTextField);
        PromptSupport.setPrompt("Enter Member ID", memberIDTextField);
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

        searchDetailsPanel = new javax.swing.JPanel();
        searchMemberButton = new javax.swing.JButton();
        memberIDTextField = new javax.swing.JTextField();
        showStatementButton = new javax.swing.JButton();
        reportPanel = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        memberStatementTable = new ReportTable();
        memberInformationPanel = new javax.swing.JPanel();
        memberImageLabel = new javax.swing.JLabel();
        memberFullName = new javax.swing.JLabel();
        dateOfBirthLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        registerationDateLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        districtLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        contactNumberLabel = new javax.swing.JLabel();
        showFullDetailsButton = new javax.swing.JButton();

        searchDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        searchMemberButton.setText("Search Member");
        searchMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMemberButtonActionPerformed(evt);
            }
        });

        memberIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberIDTextFieldActionPerformed(evt);
            }
        });
        memberIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                memberIDTextFieldKeyTyped(evt);
            }
        });

        showStatementButton.setText("Show Statement");
        showStatementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStatementButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout searchDetailsPanelLayout = new org.jdesktop.layout.GroupLayout(searchDetailsPanel);
        searchDetailsPanel.setLayout(searchDetailsPanelLayout);
        searchDetailsPanelLayout.setHorizontalGroup(
            searchDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(searchDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(memberIDTextField)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(showStatementButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(searchMemberButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        searchDetailsPanelLayout.setVerticalGroup(
            searchDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(searchDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(searchDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(searchDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(showStatementButton)
                        .add(searchMemberButton))
                    .add(memberIDTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 8, Short.MAX_VALUE))
        );

        reportPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transactions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        memberStatementTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        memberStatementTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(memberStatementTable);

        org.jdesktop.layout.GroupLayout reportPanelLayout = new org.jdesktop.layout.GroupLayout(reportPanel);
        reportPanel.setLayout(reportPanelLayout);
        reportPanelLayout.setHorizontalGroup(
            reportPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(reportPanelLayout.createSequentialGroup()
                .add(6, 6, 6)
                .add(reportPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1)
                    .add(reportPanelLayout.createSequentialGroup()
                        .add(searchTextField)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(refreshButton)))
                .addContainerGap())
        );
        reportPanelLayout.setVerticalGroup(
            reportPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(reportPanelLayout.createSequentialGroup()
                .add(6, 6, 6)
                .add(reportPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(searchTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(refreshButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        memberInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Basic Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        memberImageLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 255), 1, true));
        memberImageLabel.setMaximumSize(new java.awt.Dimension(88, 88));
        memberImageLabel.setMinimumSize(new java.awt.Dimension(88, 88));
        memberImageLabel.setPreferredSize(new java.awt.Dimension(88, 88));

        memberFullName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Registeration Date      :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Date of Birth                  :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("District                          :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Contact Number         :");

        showFullDetailsButton.setText("Full Details");
        showFullDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showFullDetailsButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout memberInformationPanelLayout = new org.jdesktop.layout.GroupLayout(memberInformationPanel);
        memberInformationPanel.setLayout(memberInformationPanelLayout);
        memberInformationPanelLayout.setHorizontalGroup(
            memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(memberInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(memberImageLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(memberInformationPanelLayout.createSequentialGroup()
                        .add(memberFullName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(showFullDetailsButton))
                    .add(memberInformationPanelLayout.createSequentialGroup()
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(30, 30, 30)
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(dateOfBirthLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .add(registerationDateLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(66, 66, 66)
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(30, 30, 30)
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(districtLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(contactNumberLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        memberInformationPanelLayout.setVerticalGroup(
            memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(memberInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(memberInformationPanelLayout.createSequentialGroup()
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(showFullDetailsButton)
                            .add(memberInformationPanelLayout.createSequentialGroup()
                                .add(3, 3, 3)
                                .add(memberFullName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(memberInformationPanelLayout.createSequentialGroup()
                                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(districtLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(contactNumberLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(memberInformationPanelLayout.createSequentialGroup()
                                .add(dateOfBirthLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(registerationDateLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(memberImageLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(searchDetailsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(reportPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(memberInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(searchDetailsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(memberInformationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(reportPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        if(report != null)
        {
            report.updateReport();
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void showStatementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showStatementButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            if(report != null)
            {
                if(report instanceof MemberStatementReport)
                {
                    MemberStatementReport memberReport = (MemberStatementReport) report;
                    int memberID = Integer.parseInt(memberIDTextField.getText().trim());
                    memberReport.setMemberID(memberID);
                }
            }
        }
        catch(NumberFormatException ex)
        {            
        }
        catch(NullPointerException ex)
        {            
        }
    }//GEN-LAST:event_showStatementButtonActionPerformed

    private void searchMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMemberButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchMemberButtonActionPerformed

    private void showFullDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFullDetailsButtonActionPerformed
        if(report != null)
        {
            if(report instanceof MemberStatementReport)
            {
                MemberStatementReport memberReport = (MemberStatementReport) report;
                int memberID = memberReport.getMemberID();

                if(memberID != Integer.MAX_VALUE)
                {
                    Member member = DatabaseManager.getMemberWithID(memberID);
                    if(member != null)
                    {
                        MemberFrame memberFullInformation = new MemberFrame(member, InformationPanel.Context.View);
                        memberFullInformation.setVisible(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_showFullDetailsButtonActionPerformed

    private void memberIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberIDTextFieldActionPerformed
        // TODO add your handling code here:
        showStatementButtonActionPerformed(evt);
    }//GEN-LAST:event_memberIDTextFieldActionPerformed

    private void memberIDTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memberIDTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_memberIDTextFieldKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contactNumberLabel;
    private javax.swing.JLabel dateOfBirthLabel;
    private javax.swing.JLabel districtLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel memberFullName;
    private javax.swing.JTextField memberIDTextField;
    private javax.swing.JLabel memberImageLabel;
    private javax.swing.JPanel memberInformationPanel;
    private javax.swing.JTable memberStatementTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel registerationDateLabel;
    private javax.swing.JPanel reportPanel;
    private javax.swing.JPanel searchDetailsPanel;
    private javax.swing.JButton searchMemberButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton showFullDetailsButton;
    private javax.swing.JButton showStatementButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateView(ReportData data) {
        if(data != null)
        {
            getReportTable().setModel(new GenericDefaultTableModel(data));
            adjustReportTableColumns();
            
            if(report != null)
            {
                if(report instanceof MemberStatementReport)
                {
                    MemberStatementReport memberReport = (MemberStatementReport) report;
                    int memberID = memberReport.getMemberID();
                    
                    Member member = DatabaseManager.getMemberWithID(memberID);
                    
                    if(member != null)
                    {
                        String fn = member.getFirstName();
                        String mn = member.getMiddleName();
                        String ln = member.getLastName();
                        String fullName =  (fn != null ? fn : "") + " " + (mn != null ? mn : "") + " " +(ln != null ? ln : "");
                        this.memberFullName.setText(fullName);
                        this.registerationDateLabel.setText(DateTime.getFormattedDateSQL(member.getRegisterationDate()));
                        this.contactNumberLabel.setText(member.getContactNumber());
                        this.dateOfBirthLabel.setText(DateTime.getFormattedDateSQL(member.getDateOfBirth()));
                        this.districtLabel.setText(member.getDistrict());
                        this.memberImageLabel.setIcon(ResourceManager.getIcon("no_photo_men", this.memberImageLabel.getPreferredSize()));
                    }
                    else
                    {
                        this.memberFullName.setText("");
                        this.registerationDateLabel.setText("");
                        this.contactNumberLabel.setText("");
                        this.dateOfBirthLabel.setText("");
                        this.districtLabel.setText("");
                        this.memberImageLabel.setIcon(null);
                        
                        Toast.showToast(this.memberIDTextField, "Member not found with this ID", false);
                    }
                }
            }
        }
    }

    
    protected boolean shouldResizeReportTableColumnWidth() 
    { 
        return false; 
    }
    @Override
    protected JTable getReportTable() {
        return memberStatementTable;
    }
}
