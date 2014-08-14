/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
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
    
    Vector<Vector<JPanel>> panels;
    int currentIndex = -1;
    
    public MemberFrame(Member member, InformationPanel.PanelType context) {
        initComponents();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panel = new InformationPanel(member, context);
        
        panels = panel.getAllPanels();
        
        
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                updatePanel(true);
            }
        });
        
        setSize(new Dimension((int) panel.getPreferredSize().getWidth() + 25, 800));
        setLocationRelativeTo(null);
        
        updateTitle(context);
    }
    
    void updatePanel(boolean next)
    {
        if(next)
        {
            if(this.allPanels.getComponentCount() > 0)
            {
                boolean bValidData = true;
                JPanel dummy = (JPanel)this.allPanels.getComponent(0);
                
                for(int i=0; i< dummy.getComponentCount(); ++i)
                {
                    JPanel testPanel = (JPanel)dummy.getComponent(i);
                    
                    if(testPanel != null)
                    {
                        bValidData &= this.panel.isValidPanelData(testPanel, true);
                    }
                }
                
                if(!bValidData)
                {
                    return;
                }
            }
        }
        
        this.allPanels.removeAll();
        
        if(next) ++currentIndex;
        else --currentIndex;
                 
        if(currentIndex < panels.size() && currentIndex  >= 0)
        {

            Vector<JPanel> p = panels.get(currentIndex);

            JPanel dummy = new JPanel(new GridLayout(p.size(), 1));
            Dimension dimension = new Dimension();
            for (JPanel panel : p)
            {
                dimension.width = Math.max(panel.getPreferredSize().width, dimension.width);
                dimension.height += panel.getPreferredSize().height;
                panel.doLayout();
                panel.validate();
                dummy.add(panel);
            }
            
            dummy.doLayout();
            
            
            
            this.allPanels.add(dummy, BorderLayout.CENTER);
        }
        
        
        if(currentIndex == panels.size() - 1)
        {
            if(this.panel.getPanelType() == InformationPanel.PanelType.View)
            {
                this.actionButton.setEnabled(false);
            }
            else
            {
                if(this.panel.getPanelType() == InformationPanel.PanelType.Donate)
                {
                    this.actionButton.setText("Donate");
                }
                else
                {
                    this.actionButton.setText("Register");
                }
            }
        }
        else
        {
            this.actionButton.setEnabled(true);
            this.actionButton.setText("Next");
        }
        
        this.previousButton.setEnabled(currentIndex != 0);

        this.validate();
        this.repaint();
    }

    void updateTitle(InformationPanel.PanelType context)
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
            case Donate:
                setTitle("Donation Form");
        }
    }
    public void setMember(Member member) {
        panel.setMember(member);
    }

    public Member getMember() {
        return panel.getMember(true, true);
    }
    
    public void setContext(InformationPanel.PanelType context)
    {
        panel.setPanelType(context);
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

        actionPanel = new BackgroundPanel(BackgroundStyle.GradientBlueGray);
        actionButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        mainScrollView = new javax.swing.JScrollPane();
        mainContainerPanel = new javax.swing.JPanel();
        allPanels = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        actionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        actionButton.setText("Register");
        actionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionButtonActionPerformed(evt);
            }
        });

        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout actionPanelLayout = new org.jdesktop.layout.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, actionPanelLayout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .add(cancelButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(previousButton)
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
                    .add(previousButton)
                    .add(cancelButton))
                .add(6, 6, 6))
        );

        getContentPane().add(actionPanel, java.awt.BorderLayout.SOUTH);

        mainContainerPanel.setLayout(new java.awt.BorderLayout());

        allPanels.setLayout(new java.awt.BorderLayout());
        mainContainerPanel.add(allPanels, java.awt.BorderLayout.CENTER);

        mainScrollView.setViewportView(mainContainerPanel);

        getContentPane().add(mainScrollView, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionButtonActionPerformed
        // TODO add your handling code here:
        if(currentIndex < (panels.size() - 1))
        {
            updatePanel(true);
        }
        else
        {
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
                    if(panel.getPanelType() == InformationPanel.PanelType.Donate)
                    {
                        Toast.showToast("Donation performed successfully!", centerPoint, true);
                    }
                    else
                    {
                        Toast.showToast("Member registered successfully!", centerPoint, true);
                    }

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
        }
    }//GEN-LAST:event_actionButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        updatePanel(false);
    }//GEN-LAST:event_previousButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private InformationPanel panel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actionButton;
    private javax.swing.JPanel actionPanel;
    private javax.swing.JPanel allPanels;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel mainContainerPanel;
    private javax.swing.JScrollPane mainScrollView;
    private javax.swing.JButton previousButton;
    // End of variables declaration//GEN-END:variables

    private void updateInformationPanel() {
    }
}
