/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 *
 * @author om
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     * @throws java.lang.ClassNotFoundException
     */
    public MainFrame() {
        initComponents();
        
        //size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        
        //height of the task bar
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        //available size of the screen 
        setSize(screenSize.width, screenSize.height - taskBarSize);
        
        this.statusBar = new StatusBarPanel();
        //this.statusBar.setSize(getWidth(), 33);
        this.statusBar.setBounds(0, getHeight() - 33, getWidth(), 33);
        add(this.statusBar, BorderLayout.SOUTH);
        
        
        getSplitPane().setLeftComponent(null);
        getSplitPane().setRightComponent(null);
        getSplitPane().setSize(getWidth(), getHeight() - this.statusBar.getHeight());

        getSplitPane().setMinimumSize(new Dimension(200, getHeight() - this.statusBar.getHeight()));
        getSplitPane().setMaximumSize(new Dimension(getWidth()/3, getHeight() - this.statusBar.getHeight()));
        
        setVisible(true);
    }

    public final JSplitPane getSplitPane() {
        return splitPane;
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        splitPane = new javax.swing.JSplitPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private StatusBarPanel statusBar;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables
}
