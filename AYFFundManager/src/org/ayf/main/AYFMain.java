package org.ayf.main;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.ayf.managers.ApplicationManager;
import org.ayf.util.DateTime;

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
        
        
        DateTime.toSQLDate(DateTime.getFormattedDate());
        
        try 
        {
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
        JTableUpdateTask jTableUpdateTask = new JTableUpdateTask(dialogFrame.getReportTable(), DatabaseManager.getRegisteredMembers(), Member.getColumnsForDetailsLevel(Member.DetailsLevel.Complete).toArray());
        jTableUpdateTask.execute();
                */
        
        
    }
}
