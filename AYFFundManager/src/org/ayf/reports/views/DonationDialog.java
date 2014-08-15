/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Donor;
import org.ayf.util.DateTime;
import org.ayf.util.Toast;

/**
 *
 * @author om
 */
public class DonationDialog extends javax.swing.JDialog {

    /**
     * Creates new form DonationDialog
     */
    
    ReportDataProcessor processor;
    boolean userCancelledDialog = true;
    
    public DonationDialog(java.awt.Frame parent, boolean modal, ReportDataProcessor processor)
    {
        super(parent, modal);
        initComponents();
        
        setTitle("Donation Details");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        Vector<String> donationTypes = new Vector<String>(1);
        donationTypes.addAll(BaseEntity.getAllValuesForColumnName(BaseEntity.ColumnName.DonationType));
        
        Vector<String> paymentModes = new Vector<String>(1);
        paymentModes.addAll(BaseEntity.getAllValuesForColumnName(BaseEntity.ColumnName.PaymentMode));
        
        this.donationTypeCombo.setModel(new DefaultComboBoxModel(donationTypes));
        this.paymentModeCombo.setModel(new DefaultComboBoxModel(paymentModes));
        
        java.sql.Date today = new Date(new java.util.Date().getTime());
        this.dobDate.setText(Integer.toString(DateTime.getDay(today)));
        Vector<String> months = new Vector<String>();
            
        months.addAll(Arrays.asList(DateTime.Months));
        
        this.monthCombo.setModel(new DefaultComboBoxModel(months));
        
        this.monthCombo.setSelectedIndex(DateTime.getMonth(today));
            
        this.yearTxt.setText(Integer.toString(DateTime.getYear(today)));
        
        this.processor = processor;
        
        setLocationRelativeTo(null);
            
    }
    
    @Override
    protected JRootPane createRootPane() 
    {
        ActionListener actionListener = new ActionListener() 
        {
            public void actionPerformed(ActionEvent actionEvent) 
            {
                dispose();
            }
        };
        
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        JRootPane rootPane = new JRootPane();
        rootPane.registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }

    public boolean isUserCancelledDialog() {
        return userCancelledDialog;
    }
    
    
    public void setSubscriptionPayment()
    {
        this.donationTypeCombo.setModel(new DefaultComboBoxModel(new String[]{Donor.SUBSCRIPTION_TYPE}));
    }
    
    
    public boolean isValidFormData()
    {
        boolean isValid = true;
        try
        {
            int day = Integer.parseInt(this.dobDate.getText());
            String month = (String)this.monthCombo.getSelectedItem();
            int year = Integer.parseInt(this.yearTxt.getText());
            DateTime.getDate(day, month, year);
        } catch(NumberFormatException ex)
        { 
            Toast.showToast(this.dobDate, "Invalid date.", false); 
            isValid = false;
        }
        
        try
        {
            Integer.parseInt(this.receiptNumberTxt.getText());
        }catch(NumberFormatException ex)
        {
            Toast.showToast(this.receiptNumberTxt, "Invalid receipt number.", false); 
            isValid = false;
        }
        
        try
        {
            Float.parseFloat(this.donationAmountTxt.getText());
        }catch(NumberFormatException ex)
        {
            Toast.showToast(this.donationAmountTxt, "Invalid amount.", false); 
            isValid = false;
        }
        
        return isValid;
    }
    
    public java.sql.Date getDonationDate()
    {
        try
        {
            int day = Integer.parseInt(this.dobDate.getText());
            String month = (String)this.monthCombo.getSelectedItem();
            int year = Integer.parseInt(this.yearTxt.getText());
            return DateTime.getDate(day, month, year);
        } catch(NumberFormatException ex)
        { 
            return null;
        }
    }
    
    public int getReceiptNumber()
    {
        try
        {
            return Integer.parseInt(this.receiptNumberTxt.getText());
        }catch(NumberFormatException ex)
        {
            return Integer.MAX_VALUE;
        }
    }
    
    public float getDonationAmount()
    {
        try
        {
            return Float.parseFloat(this.donationAmountTxt.getText());
        }catch(NumberFormatException ex)
        {
            return 0;
        }
    }
    
    public Object getDonationType()
    {
        return this.donationTypeCombo.getSelectedItem();
    }
    
    public Object getPaymentMode()
    {
        return this.paymentModeCombo.getSelectedItem();
    }
    
    public String getDescription()
    {
        return this.donationDescriptionTxt.getText();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        donationAmountTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        receiptNumberTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        paymentModeCombo = new javax.swing.JComboBox();
        donationTypeCombo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        donationDescriptionTxt = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dobDate = new javax.swing.JTextField();
        monthCombo = new javax.swing.JComboBox();
        yearTxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        donateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Donation Details"));

        jLabel1.setText("Amount");

        jLabel2.setText("Receipt Number");

        jLabel3.setText("Donation Type");

        jLabel4.setText("Payment Mode");

        donationDescriptionTxt.setColumns(20);
        donationDescriptionTxt.setRows(5);
        jScrollPane1.setViewportView(donationDescriptionTxt);

        jLabel5.setText("Description");

        jLabel6.setText("Date Of Birth");

        dobDate.setMinimumSize(new java.awt.Dimension(6, 23));
        dobDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dobDateFocusGained(evt);
            }
        });

        monthCombo.setMaximumRowCount(12);

        yearTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                yearTxtFocusGained(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel1)
                                .add(jLabel2)
                                .add(jLabel3)
                                .add(jLabel4))
                            .add(32, 32, 32)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(paymentModeCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(dobDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(13, 13, 13)
                                    .add(monthCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                    .add(yearTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(donationAmountTxt)
                                .add(receiptNumberTxt)
                                .add(donationTypeCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(jLabel5)
                        .add(jScrollPane1))
                    .add(jLabel6))
                .add(18, 18, 18))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(donationAmountTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(receiptNumberTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dobDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(monthCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6)
                    .add(yearTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(donationTypeCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(paymentModeCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        donateButton.setText("Donate");
        donateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donateButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(cancelButton)
                .add(18, 18, 18)
                .add(donateButton)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {cancelButton, donateButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(donateButton)
                    .add(cancelButton))
                .add(6, 6, 6))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(18, 18, 18))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dobDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobDateFocusGained
    }//GEN-LAST:event_dobDateFocusGained

    private void yearTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearTxtFocusGained
    }//GEN-LAST:event_yearTxtFocusGained

    private void donateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donateButtonActionPerformed
        // TODO add your handling code here:
        if(isValidFormData())
        {
            this.userCancelledDialog = false;
            if(this.processor != null)
            {
                this.processor.processSelectedData(null, null);
            }
            
            dispose();
        }
    }//GEN-LAST:event_donateButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField dobDate;
    private javax.swing.JButton donateButton;
    private javax.swing.JTextField donationAmountTxt;
    private javax.swing.JTextArea donationDescriptionTxt;
    private javax.swing.JComboBox donationTypeCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox monthCombo;
    private javax.swing.JComboBox paymentModeCombo;
    private javax.swing.JTextField receiptNumberTxt;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}
