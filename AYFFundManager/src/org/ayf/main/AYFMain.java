package org.ayf.main;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.ayf.database.entities.Member;
import org.ayf.managers.ApplicationManager;
import org.ayf.managers.DatabaseManager;
import org.ayf.models.JTableUpdateTask;
import org.ayf.ui.TableDialogFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oramtekkar
 */
public class AYFMain {

    public static void main(String[] args) {
        try 
        {
            DatabaseManager.getRegisteredMembers();
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AYFMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AYFMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AYFMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AYFMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        ApplicationManager.getSharedManager().initialize();

        /*
        TableDialogFrame dialogFrame = new TableDialogFrame();
        dialogFrame.setVisible(true);
        dialogFrame.getReportTable().setRowHeight(30);
        dialogFrame.getReportTable().setShowVerticalLines(true);
        //dialogFrame.getReportTable().setShowHorizontalLines(true);
        JTableUpdateTask jTableUpdateTask = new JTableUpdateTask(dialogFrame.getReportTable(), DatabaseManager.getRegisteredMembers(), Member.getColumnNames());
        jTableUpdateTask.execute();
        */
    }
}
