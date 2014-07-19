/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.ui.BackgroundPanel.BackgroundStyle;
import org.ayf.util.Toast;

/**
 *
 * @author om
 */
public class MemberFrame extends javax.swing.JFrame {

    /**
     * Creates new form MemberFrame
     * @param member
     * @param context
     */    
    public MemberFrame(Member member, InformationPanel.Context context) {
        initComponents();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panel = new InformationPanel(member, context);
        
        this.mainContainerPanel.add(panel, BorderLayout.CENTER);

        setSize(new Dimension((int) panel.getPreferredSize().getWidth() + 25, 600));
        setLocation(200, 100);
        
        updateTitle(context);
    }

    void updateTitle(InformationPanel.Context context)
    {
        switch(context)
        {
            case View:
                setTitle("Member Information Form");
                return;
            case Registeration:
                setTitle("Member Registeration Form");
                return;
            case Update:
                setTitle("Update Member Information Form");
                return;
        }
    }
    public void setMember(Member member) {
        panel.setMember(member);
    }

    public Member getMember() {
        return panel.getMember(true, true);
    }
    
    public void setContext(InformationPanel.Context context)
    {
        panel.setCurrentContext(context);
        updateTitle(context);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainScrollView = new javax.swing.JScrollPane();
        mainContainerPanel = new javax.swing.JPanel();
        actionPanel = new BackgroundPanel(BackgroundStyle.GradientBlueGray);
        actionButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mainContainerPanel.setLayout(new java.awt.BorderLayout());

        actionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        actionButton.setText("Register");
        actionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");

        org.jdesktop.layout.GroupLayout actionPanelLayout = new org.jdesktop.layout.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, actionPanelLayout.createSequentialGroup()
                .addContainerGap(353, Short.MAX_VALUE)
                .add(cancelButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(actionButton)
                .addContainerGap())
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(actionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(actionButton)
                    .add(cancelButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainContainerPanel.add(actionPanel, java.awt.BorderLayout.SOUTH);

        mainScrollView.setViewportView(mainContainerPanel);

        getContentPane().add(mainScrollView, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionButtonActionPerformed
        // TODO add your handling code here:
        Member member = getMember();
        if(member != null)
        {
            Vector<BaseEntity> entity = new Vector<BaseEntity>();
            entity.add(member);
            boolean bRegistered = DatabaseManager.insertEntities(entity, Member.class);
            if(bRegistered)
            {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Point centerPoint = new Point(screenSize.width / 2, screenSize.height/3);
                this.setVisible(false);
                Toast.showToast("Member registered successfully!", centerPoint, true);
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MemberFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        finally
                        {
                            dispose();
                        }
                    }
                });
            }
        }
    }//GEN-LAST:event_actionButtonActionPerformed

    private InformationPanel panel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actionButton;
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel mainContainerPanel;
    private javax.swing.JScrollPane mainScrollView;
    // End of variables declaration//GEN-END:variables

    private void updateInformationPanel() {
    }
}
