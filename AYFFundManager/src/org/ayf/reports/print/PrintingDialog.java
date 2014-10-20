/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.print;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.ayf.ui.BackgroundPanel;
import org.ayf.ui.JComboCheckBox;
import org.ayf.util.Toast;

/**
 *
 * @author om
 */
public class PrintingDialog extends javax.swing.JDialog {

    /**
     * Creates new form PrintingDialog
     */
    
    PrinterJob printingJob = PrinterJob.getPrinterJob();
    PageFormat pageFormat = printingJob.defaultPage();
    
    String headerString = "Aadim Youth Report";
    String footerString = "Page - {0}";
    
    boolean showPrintingDialog = true;
    boolean interactivePrinting = true;
    boolean printVerticalLines = true;
    boolean printHorizontalLines = true;
    boolean printPageWidth = true;
    
    PrintableView currentPrintable;
    Vector<PrintableView> printables;
    
    JComboCheckBox reportColumnsCombo;
    HTMLPrintable htmlPrintable;

    public PrintingDialog(Vector<PrintableView> printables, Frame owner, boolean modal) {
        super(owner, modal);
        
        initComponents();
        
        this.reportColumnsCombo = (JComboCheckBox) this.reportColumnsCombo1;
        
        this.printables = printables;
        
        initUI();
    }
    
    void closeDialog()
    {
        dispose();
    }
    
    void initUI()
    {
        this.headerText.setText(headerString);
        this.footerText.setText(footerString);
        this.showPrintDialogCk.setSelected(this.showPrintingDialog);
        this.interactiveCk.setSelected(this.interactivePrinting);
        this.fitWidthCk.setSelected(printPageWidth);
        this.printVerticalLinesCk.setSelected(this.printVerticalLines);
        this.printHorizontalLinesCk.setSelected(this.printHorizontalLines);
        
        this.selectReportCombo.setModel(new DefaultComboBoxModel(printables));
        this.htmlPrintable = new HTMLPrintable("", this.pageFormat.getPaper(), null);
        setPrintable(this.printables.firstElement());
    }
    
    void setPrintable(PrintableView printable)
    {
        if(this.currentPrintable != null)
        {
            this.rootPrintableView.remove(this.currentPrintable);
        }
        
        this.currentPrintable = printable;
        
        this.currentPrintable.setPageFormat(this.pageFormat);
        this.reportColumnsCombo.setItems(this.currentPrintable.getTableColumns());
        
        this.htmlPrintable.setHtml(this.currentPrintable.getHtml());
        this.rootPrintableView.add(this.htmlPrintable.getEditorViewScrollPane(), BorderLayout.CENTER);
        //this.rootPrintableView.add(this.currentPrintable, BorderLayout.CENTER);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new BackgroundPanel();
        jPanel1 = new javax.swing.JPanel();
        pageSetupButton = new javax.swing.JButton();
        previewButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        headerText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        footerText = new javax.swing.JTextField();
        showPrintDialogCk = new javax.swing.JCheckBox();
        interactiveCk = new javax.swing.JCheckBox();
        fitWidthCk = new javax.swing.JCheckBox();
        printVerticalLinesCk = new javax.swing.JCheckBox();
        printHorizontalLinesCk = new javax.swing.JCheckBox();
        printPageNumberCk = new javax.swing.JCheckBox();
        rootPrintableView = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        selectReportCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        reportColumnsCombo1 = new JComboCheckBox();
        jPanel3 = new javax.swing.JPanel();
        printButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        saveToFileButton = new javax.swing.JButton();
        saveAsImageButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Print Dialog");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Printing"));

        pageSetupButton.setText("Page Setup");
        pageSetupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageSetupButtonActionPerformed(evt);
            }
        });

        previewButton.setText("Preview");
        previewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Header");

        jLabel2.setText("Footer");

        showPrintDialogCk.setText("Show print dialog");
        showPrintDialogCk.setToolTipText("If enable, will display a final printing dialog after clicking on Print button");
        showPrintDialogCk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPrintDialogCkActionPerformed(evt);
            }
        });

        interactiveCk.setText("Interactive (Show status dialog)");
        interactiveCk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interactiveCkActionPerformed(evt);
            }
        });

        fitWidthCk.setText("Fit width to printed page");
        fitWidthCk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fitWidthCkActionPerformed(evt);
            }
        });

        printVerticalLinesCk.setText("Verticle Lines");
        printVerticalLinesCk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printVerticalLinesCkActionPerformed(evt);
            }
        });

        printHorizontalLinesCk.setText("Horizontal Lines");
        printHorizontalLinesCk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printHorizontalLinesCkActionPerformed(evt);
            }
        });

        printPageNumberCk.setText("Page Number");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(pageSetupButton)
                        .add(12, 12, 12)
                        .add(previewButton))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1)
                                    .add(jLabel2))
                                .add(18, 18, 18)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(headerText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .add(footerText)))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(showPrintDialogCk)
                                    .add(printVerticalLinesCk))
                                .add(24, 24, 24)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(interactiveCk)
                                    .add(printHorizontalLinesCk))
                                .add(18, 18, 18)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(printPageNumberCk)
                                    .add(fitWidthCk))))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {pageSetupButton, previewButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(pageSetupButton)
                    .add(previewButton))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(headerText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(footerText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(showPrintDialogCk)
                    .add(interactiveCk)
                    .add(fitWidthCk))
                .add(12, 12, 12)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(printHorizontalLinesCk)
                    .add(printVerticalLinesCk)
                    .add(printPageNumberCk))
                .add(18, 18, 18))
        );

        rootPrintableView.setBorder(javax.swing.BorderFactory.createTitledBorder("ReportView"));
        rootPrintableView.setLayout(new java.awt.BorderLayout());

        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Note: If you are printing all columns in report, then before printing, preview the final printing.");

        jLabel5.setText("Select Report");

        selectReportCombo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                selectReportComboPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel3.setText("Select Report Columns");

        reportColumnsCombo1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                reportColumnsCombo1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel4)
                        .add(0, 75, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(selectReportCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 206, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabel3)
                        .add(18, 18, 18)
                        .add(reportColumnsCombo1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(13, 13, 13)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(selectReportCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(reportColumnsCombo1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel4)
                .add(18, 18, 18))
        );

        rootPrintableView.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveToFileButton.setText("Save to file");
        saveToFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToFileButtonActionPerformed(evt);
            }
        });

        saveAsImageButton.setText("Save as Image");
        saveAsImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsImageButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(saveToFileButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(saveAsImageButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(cancelButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(printButton)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(new java.awt.Component[] {cancelButton, printButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(printButton)
                    .add(cancelButton)
                    .add(saveToFileButton)
                    .add(saveAsImageButton))
                .add(12, 12, 12))
        );

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(rootPrintableView, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rootPrintableView, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pageSetupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageSetupButtonActionPerformed
        
        try {
            this.pageFormat = this.printingJob.pageDialog(this.printingJob.defaultPage());
            this.pageFormat = this.printingJob.validatePage(this.pageFormat);
            this.currentPrintable.setPageFormat(this.pageFormat);
        } catch (HeadlessException headlessException) 
        {
            JOptionPane.showMessageDialog(this, headlessException.getMessage(), "Page Setup Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_pageSetupButtonActionPerformed


    
    private void previewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewButtonActionPerformed

        PrintPreview preview = new PrintPreview(this, this.htmlPrintable, this.currentPrintable.getPageFormat());
        preview.setVisible(true);
    }//GEN-LAST:event_previewButtonActionPerformed

    private void saveToFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToFileButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveToFileButtonActionPerformed

    private void saveAsImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsImageButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveAsImageButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        closeDialog();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        
        if(this.currentPrintable == null)
        {
            JOptionPane.showMessageDialog(this, "Nothing to print.", "Print Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.currentPrintable.setHeader(new MessageFormat(this.headerText.getText().trim()));
        this.currentPrintable.setFooter(new MessageFormat(this.footerText.getText().trim()));
        this.currentPrintable.setPrintPageWidth(this.fitWidthCk.isSelected());
        
        try
        {
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            this.printingJob.setPrintService(service);
        } catch (PrinterException ex) {
            Logger.getLogger(PrintingDialog.class.getName()).log(Level.SEVERE, null, ex);
            Toast.showToastOnScreenCenter("Printer is not configured.", false);
            //JOptionPane.showMessageDialog(this, "Print service not found.", "Print Error", JOptionPane.ERROR_MESSAGE);
        }

        PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
        attr_set.add(MediaSizeName.ISO_A4);
                    
        this.printingJob.setPrintable(this.htmlPrintable, this.pageFormat);

        boolean doPrinting = true;
        
        if(this.showPrintingDialog)
        {
            try {
                doPrinting = this.printingJob.printDialog();
            } catch (HeadlessException headlessException) 
            {
                doPrinting = false;
                Logger.getLogger(PrintingDialog.class.getName()).log(Level.SEVERE, null, headlessException);
                JOptionPane.showMessageDialog(this, headlessException.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(doPrinting)
        {
            try 
            {
                this.printingJob.print(attr_set);
                //this.htmlPrintable.pri
            } catch (PrinterException ex) {
                Logger.getLogger(PrintingDialog.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void showPrintDialogCkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPrintDialogCkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showPrintDialogCkActionPerformed

    private void interactiveCkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interactiveCkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interactiveCkActionPerformed

    private void fitWidthCkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fitWidthCkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fitWidthCkActionPerformed

    private void selectReportComboPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_selectReportComboPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        System.out.print("");
    }//GEN-LAST:event_selectReportComboPopupMenuWillBecomeInvisible

    private void reportColumnsCombo1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_reportColumnsCombo1PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        this.currentPrintable.configureView(new Vector<Object>(Arrays.asList(this.reportColumnsCombo.getSelectedItems())));
        this.htmlPrintable.setHtml(this.currentPrintable.getHtml());
    }//GEN-LAST:event_reportColumnsCombo1PopupMenuWillBecomeInvisible

    private void printVerticalLinesCkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printVerticalLinesCkActionPerformed
        this.currentPrintable.setShowVerticalLines(this.printVerticalLinesCk.isSelected());
        this.htmlPrintable.setShowTableVerticalLines(this.printVerticalLinesCk.isSelected());
        this.htmlPrintable.setHtml(this.currentPrintable.getHtml());
    }//GEN-LAST:event_printVerticalLinesCkActionPerformed

    private void printHorizontalLinesCkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printHorizontalLinesCkActionPerformed
        this.currentPrintable.setShowHorizontalLines(this.printHorizontalLinesCk.isSelected());
        this.htmlPrintable.setShowTableHorizontalLines(this.printHorizontalLinesCk.isSelected());
        this.htmlPrintable.setHtml(this.currentPrintable.getHtml());
    }//GEN-LAST:event_printHorizontalLinesCkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox fitWidthCk;
    private javax.swing.JTextField footerText;
    private javax.swing.JTextField headerText;
    private javax.swing.JCheckBox interactiveCk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton pageSetupButton;
    private javax.swing.JButton previewButton;
    private javax.swing.JButton printButton;
    private javax.swing.JCheckBox printHorizontalLinesCk;
    private javax.swing.JCheckBox printPageNumberCk;
    private javax.swing.JCheckBox printVerticalLinesCk;
    private javax.swing.JComboBox reportColumnsCombo1;
    private javax.swing.JPanel rootPrintableView;
    private javax.swing.JButton saveAsImageButton;
    private javax.swing.JButton saveToFileButton;
    private javax.swing.JComboBox selectReportCombo;
    private javax.swing.JCheckBox showPrintDialogCk;
    // End of variables declaration//GEN-END:variables
}
