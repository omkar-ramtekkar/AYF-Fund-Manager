/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui;

import javax.swing.JComponent;

/**
 *
 * @author om
 */
public class ReportView extends javax.swing.JPanel {

    /**
     * Creates new form ReportView
     */
    public ReportView() {
        initComponents();
        
        {
            rootPanel = new BackgroundPanel(BackgroundPanel.BackgroundStyle.Default);
            setLayout(new java.awt.BorderLayout());

            rootPanel.setLayout(new java.awt.GridLayout(2, 2));
            jScrollPane1.setViewportView(rootPanel);

            add(jScrollPane1, java.awt.BorderLayout.CENTER);
        }
    }
    
    public void addView(JComponent view)
    {
        if(view != null)
        {
            rootPanel.add(view);
            rootPanel.repaint(view.getBounds());
        }
    }

    
    public void cleanView() 
    {
        rootPanel.removeAll();
        rootPanel.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        setLayout(new java.awt.BorderLayout());
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    private BackgroundPanel rootPanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    
}
