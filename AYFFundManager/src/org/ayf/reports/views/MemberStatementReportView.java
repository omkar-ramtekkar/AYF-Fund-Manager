/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import com.sun.tools.corba.se.idl.InvalidArgument;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Donor;
import org.ayf.database.entities.Member;
import org.ayf.managers.ApplicationManager;
import org.ayf.managers.DatabaseManager;
import org.ayf.managers.ResourceManager;
import org.ayf.models.GenericDefaultTableModel;
import org.ayf.reports.GenericSearchReport;
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
public class MemberStatementReportView extends BaseReportView implements ReportDataProcessor{

    /**
     * Creates new form MemberStatement
     */
    
    private Member currentMember = null;
            
            
    public MemberStatementReportView(Report report) {
        super(report);
        initComponents();
        PromptSupport.setPrompt("Type text to filter member statement rows", searchTextField);
        PromptSupport.setPrompt("Type Member registeration number or click on Search Member", searchMemberTxt);
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
        showStatementButton = new javax.swing.JButton();
        searchMemberTxt = new javax.swing.JTextField();
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
        actionPanel = new javax.swing.JPanel();
        donateButton = new javax.swing.JButton();
        paySubscriptionButton = new javax.swing.JButton();
        duesButton = new javax.swing.JButton();
        deactivateButton = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        searchDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        searchMemberButton.setText("Search Member");
        searchMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMemberButtonActionPerformed(evt);
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
                .add(searchMemberTxt)
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
                .add(searchDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(showStatementButton)
                    .add(searchMemberButton)
                    .add(searchMemberTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 8, Short.MAX_VALUE))
        );

        reportPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transactions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

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
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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

        actionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Member Actions"));

        donateButton.setText("Donate");
        donateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donateButtonActionPerformed(evt);
            }
        });

        paySubscriptionButton.setText("Pay Subscription");
        paySubscriptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paySubscriptionButtonActionPerformed(evt);
            }
        });

        duesButton.setText("Dues");
        duesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duesButtonActionPerformed(evt);
            }
        });

        deactivateButton.setText("Deactivate");
        deactivateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivateButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout actionPanelLayout = new org.jdesktop.layout.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(donateButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(paySubscriptionButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(duesButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(deactivateButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        actionPanelLayout.linkSize(new java.awt.Component[] {deactivateButton, donateButton, duesButton, paySubscriptionButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(actionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(donateButton)
                    .add(paySubscriptionButton)
                    .add(duesButton)
                    .add(deactivateButton))
                .add(6, 6, 6))
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
                    .add(memberInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(actionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(searchDetailsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(memberInformationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(actionPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(12, 12, 12)
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
        updateReportView(this.searchMemberTxt.getText().trim());
    }//GEN-LAST:event_showStatementButtonActionPerformed

    void updateReportView(String memberRegID)
    {
        if(report != null)
        {
            if(report instanceof MemberStatementReport)
            {
                MemberStatementReport memberReport = (MemberStatementReport) report;
                memberReport.setMemberRegisterationNumber(memberRegID);
            }
        }
    }
    
    
    private void searchMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMemberButtonActionPerformed
        // TODO add your handling code here:
        GenericSearchDialog genericSearchDialog = null;
        try {
            genericSearchDialog = new GenericSearchDialog(new GenericSearchReport(Member.class, BaseEntity.DetailsLevel.Search), this, ApplicationManager.getSharedManager().getMainFrame(), true);
            genericSearchDialog.setVisible(true);
        } catch (InvalidArgument ex) {
            Logger.getLogger(MemberStatementReportView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_searchMemberButtonActionPerformed

    private void showFullDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFullDetailsButtonActionPerformed
        if(report != null)
        {
            if(report instanceof MemberStatementReport)
            {
                MemberStatementReport memberReport = (MemberStatementReport) report;
                String memberRegNumber = memberReport.getMemberRegisterationNumber();

                if(memberRegNumber != null)
                {
                    if(this.currentMember != null)
                    {
                        MemberFrame memberFullInformation = new MemberFrame(this.currentMember, InformationPanel.PanelType.View);
                        memberFullInformation.setVisible(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_showFullDetailsButtonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown

    private void donateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donateButtonActionPerformed
        try
        {
            String regNumber = ((MemberStatementReport)getReport()).getMemberRegisterationNumber();
            
            if(regNumber == null || regNumber.length() == 0)
                throw new Exception("Member registeration number is invalid. Select valid member.");
                
            BaseEntity entity = this.currentMember;
            if(entity == null)
                throw new Exception("Member not found with registeration number - " + regNumber + ".");

            if(this.currentMember.getCurrentStatus() != BaseEntity.ActiveStatus.Active)
            {
                JOptionPane.showMessageDialog(this, "Member is not active. Activate member before donation.", "Donation Error", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Activate member before donation.");
            }
            
            DonationDialog donationDialog = new DonationDialog(ApplicationManager.getSharedManager().getMainFrame(), true, this);
            donationDialog.setVisible(true);
            
            if(donationDialog.isUserCancelledDialog() == false)
            {
                Donor newDonation = new Donor((Member) entity);
                newDonation.setDonationAmount(donationDialog.getDonationAmount());
                newDonation.setDonationType(donationDialog.getDonationType().toString());
                newDonation.setDonationDate((new java.sql.Date(DateTime.getToday().getTime())));
                newDonation.setUniqueID(Donor.getNextUniqueID());
                newDonation.setPaymentMode(donationDialog.getPaymentMode().toString());
                newDonation.setReceiptNumber(donationDialog.getReceiptNumber());

                if(!DatabaseManager.performDonate(newDonation))
                    throw new Exception("Donation failed. Unable to make database entry for the danation.");

                Toast.showToastOnComponentCenter(this, "Donation Successful", true);

                getReport().updateReport();
            }
        }
        catch(Exception ex)
        {
            Toast.showToastOnComponentCenter(this, ex.getMessage(), false);
        }
    }//GEN-LAST:event_donateButtonActionPerformed

    private void paySubscriptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paySubscriptionButtonActionPerformed
        try
        {
            String regNumber = ((MemberStatementReport)getReport()).getMemberRegisterationNumber();
            
            if(regNumber == null || regNumber.length() == 0)
                throw new Exception("Member registeration number is invalid. Select valid member.");
                
            BaseEntity entity = this.currentMember;
            if(entity == null)
                throw new Exception("Member not found with registeration number - " + regNumber + ".");

            if(this.currentMember.getCurrentStatus() != BaseEntity.ActiveStatus.Active)
            {
                JOptionPane.showMessageDialog(this, "Member is not active. Activate member before paying subscription.", "Subscription Error", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Activate member before paying subscription.");
            }
            
            DonationDialog donationDialog = new DonationDialog(ApplicationManager.getSharedManager().getMainFrame(), true, this);
            donationDialog.setSubscriptionPayment();
            donationDialog.setTitle("Subscription Payment Form");
            donationDialog.setVisible(true);
            
            if(donationDialog.isUserCancelledDialog() == false)
            {
                Donor newDonation = new Donor((Member) entity);
                newDonation.setDonationAmount(donationDialog.getDonationAmount());
                newDonation.setDonationType(donationDialog.getDonationType().toString());
                newDonation.setDonationDate((new java.sql.Date(DateTime.getToday().getTime())));
                newDonation.setUniqueID(Donor.getNextUniqueID());
                newDonation.setPaymentMode(donationDialog.getPaymentMode().toString());
                newDonation.setReceiptNumber(donationDialog.getReceiptNumber());

                if(!DatabaseManager.performDonate(newDonation))
                    throw new Exception("Donation failed. Unable to make database entry for the danation.");

                Toast.showToastOnComponentCenter(this, "Donation Successful", true);

                getReport().updateReport();
            }
        }
        catch(Exception ex)
        {
            Toast.showToastOnComponentCenter(this, ex.getMessage(), false);
        }
    }//GEN-LAST:event_paySubscriptionButtonActionPerformed

    private void duesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_duesButtonActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void deactivateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivateButtonActionPerformed
        try
        {
            if(this.currentMember == null)
                throw new Exception("Member registeration number is invalid. Select valid member.");
            
            String message = "Are you sure, you want to Deactivate member?";
            
            if(this.currentMember.getCurrentStatus() != BaseEntity.ActiveStatus.Active)
            {
                message = "Are you sure, you want to Activate member?";
            }
            
            int userChoice = JOptionPane.showConfirmDialog(this, message, "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
            
            if(userChoice == JOptionPane.YES_OPTION)
            {
                BaseEntity.ActiveStatus currentActuveStatus = this.currentMember.getCurrentStatus();
                
                if(this.currentMember.getCurrentStatus() == BaseEntity.ActiveStatus.Active)
                {
                    this.currentMember.setCurrentStatus(BaseEntity.ActiveStatus.Inactive);
                }
                else
                {
                    this.currentMember.setCurrentStatus(BaseEntity.ActiveStatus.Active);
                }
                
                Vector<BaseEntity> entity = new Vector<BaseEntity>(1);
                entity.add(this.currentMember);
                
                boolean entityUpdated = DatabaseManager.updateEntities(entity, Member.class);
                
                if(!entityUpdated)
                {
                    this.currentMember.setCurrentStatus(currentActuveStatus);
                    if(currentActuveStatus == BaseEntity.ActiveStatus.Active)
                    {
                        throw new Exception("Failed to deactive member. Try again.");
                    }
                    else
                    {
                        throw new Exception("Failed to activate member. Try again.");
                    }
                }
                
                if(currentMember.getCurrentStatus() == BaseEntity.ActiveStatus.Active)
                {
                    Toast.showToastOnComponentCenter(this, "Member activated  successfully", true);
                }
                else
                {
                    Toast.showToastOnComponentCenter(this, "Member deactivated  successfully", true);
                }
                
                updateViewInternal(this.currentMember);
            }
                
        }catch(Exception ex)
        {
            Toast.showToastOnComponentCenter(this, ex.getMessage(), false);
        }
    }//GEN-LAST:event_deactivateButtonActionPerformed

    @Override
    public void updateView(ReportData data) 
    {
        if(data != null)
        {
            getReportTable().setModel(new GenericDefaultTableModel(data));
            adjustReportTableColumns();
            
            Toast.showToastOnComponentCenter(getReportTable(), data.getData().size() + " Records Found", true);
            
            if(report != null)
            {
                if(report instanceof MemberStatementReport)
                {
                    MemberStatementReport memberReport = (MemberStatementReport) report;
                    
                    Member member = (Member) DatabaseManager.getEntityWithUniqueID(memberReport.getMemberRegisterationNumber(), Member.class);
                    
                    updateViewInternal(member);
                }
            }
        }
    }
    
    void updateViewInternal(Member member)
    {
        this.currentMember = member;
        
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
            this.deactivateButton.setText(member.getCurrentStatus() == BaseEntity.ActiveStatus.Active ? "Deactivate" : "Activate");
            this.memberImageLabel.setIcon(ResourceManager.getImageFromImageFolder(this.currentMember.getImagePath(), this.memberImageLabel.getPreferredSize()));
        }
        else
        {
            this.memberFullName.setText("");
            this.registerationDateLabel.setText("");
            this.contactNumberLabel.setText("");
            this.dateOfBirthLabel.setText("");
            this.districtLabel.setText("");
            this.memberImageLabel.setIcon(null);

            Toast.showToast(this.searchMemberTxt, "Member not found", false);
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
    
    @Override
    public void processSelectedData(ReportData data, BaseReportView reportView) 
    {
        if(data != null)
        {
            BaseEntity entity = data.getEntities().firstElement();
            
            if(entity != null)
            {
                this.searchMemberTxt.setText(entity.getUniqueID());
                updateViewInternal((Member)entity);
                updateReportView(entity.getUniqueID());
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JLabel contactNumberLabel;
    private javax.swing.JLabel dateOfBirthLabel;
    private javax.swing.JButton deactivateButton;
    private javax.swing.JLabel districtLabel;
    private javax.swing.JButton donateButton;
    private javax.swing.JButton duesButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel memberFullName;
    private javax.swing.JLabel memberImageLabel;
    private javax.swing.JPanel memberInformationPanel;
    private javax.swing.JTable memberStatementTable;
    private javax.swing.JButton paySubscriptionButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel registerationDateLabel;
    private javax.swing.JPanel reportPanel;
    private javax.swing.JPanel searchDetailsPanel;
    private javax.swing.JButton searchMemberButton;
    private javax.swing.JTextField searchMemberTxt;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton showFullDetailsButton;
    private javax.swing.JButton showStatementButton;
    // End of variables declaration//GEN-END:variables

}
