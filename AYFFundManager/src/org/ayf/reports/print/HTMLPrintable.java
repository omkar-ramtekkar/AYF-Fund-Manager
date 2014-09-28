/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.print;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.*;
import java.awt.print.PrinterException;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.RepaintManager;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author om
 */
public class HTMLPrintable extends javax.swing.JFrame implements Printable{

    /**
     * Creates new form HTMLPrintable
     */
    String html;
    
    public HTMLPrintable(String html, Paper paper, Insets margins)
    {
        initComponents();
        initUI();
        
        //setHtml(html);
        
        if(paper == null)
            paper = new Paper();
        
        setSize((int)paper.getImageableWidth(), (int)paper.getImageableHeight());
        validate();

        setHtml(html);
    }
    

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
        this.editorView.setContentType("text/html");
        this.editorView.setText(html);
    }

    public JEditorPane getEditorView() {
        return editorView;
    }

    public JScrollPane getEditorViewScrollPane() {
        return editorViewScrollPane;
    }
    
    
    
    public int print(Graphics g, PageFormat pf, int pageIndex)
            throws PrinterException {
        Graphics2D g2 = (Graphics2D) g;

        RepaintManager.currentManager(this.editorView).setDoubleBufferingEnabled(false);
        Dimension d = this.editorView.getSize();    //get size of document
        double panelWidth = d.width;    //width in pixels
        double panelHeight = d.height;   //height in pixels
        double pageHeight = pf.getImageableHeight();   //height of printer page
        double pageWidth = pf.getImageableWidth();    //width of printer page
        double scale = pageWidth / panelWidth;
        int totalNumPages = (int) Math.ceil(scale * panelHeight / pageHeight);

        System.out.println("HTMLPrintable:print - PageFormat width = " + pf.getImageableWidth());
        System.out.println("HTMLPrintable:print - PageFormat height = " + pf.getImageableHeight());
        System.out.println("HTMLPrintable:print - EditorView = " + this.editorView.getSize());
        System.out.println("HTMLPrintable:print - scale = " + scale);
        System.out.println("HTMLPrintable:print - totalPages = " + totalNumPages);
        System.out.println("HTMLPrintable:print - pageIndex = " + pageIndex);
        
        // Make sure not print empty pages
        if (pageIndex >= totalNumPages) {
            return Printable.NO_SUCH_PAGE;
        }

        // Shift Graphic to line up with beginning of print-imageable region
        g2.translate(pf.getImageableX(), pf.getImageableY());
        // Shift Graphic to line up with beginning of next page to print
        g2.translate(0f, -pageIndex * pageHeight);
        // Scale the page so the width fits...
        g2.scale(scale, scale);
        
        this.editorView.print(g2);
        return PAGE_EXISTS;        
    }

    private void initUI() {
//        Font font = UIManager.getFont("Label.font");
//        String bodyRule = "body { font-family: " + font.getFamily() + "; " +
//                "font-size: " + font.getSize() + "pt; }";
//        ((HTMLDocument)editorView.getDocument()).getStyleSheet().addRule(bodyRule);
//        
        editorView.setEditorKit(new HTMLEditorKit());
        editorView.setOpaque(false);
        editorView.setBorder(null);
        editorView.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorViewScrollPane = new javax.swing.JScrollPane();
        editorView = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        editorViewScrollPane.setViewportView(editorView);

        getContentPane().add(editorViewScrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane editorView;
    private javax.swing.JScrollPane editorViewScrollPane;
    // End of variables declaration//GEN-END:variables

    void setShowTableVerticalLines(boolean selected) {
        HTMLEditorKit kit = (HTMLEditorKit)this.editorView.getEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        
        String showLines = "td {\n" +
                            "  border-left: 1px solid black;\n" +
                            "  border-right:1px solid black;\n" +
                            "}";
        
        String hideLines = "td {\n" +
                            "  border-left: none;\n" +
                            "  border-right: none;\n" +
                            "}";
        
        if(selected)
        {
            styleSheet.removeStyle(hideLines);
            styleSheet.addRule(showLines);
        }
        else
        {
            styleSheet.removeStyle(showLines);
            styleSheet.addRule(hideLines);
        }
        
        Document doc = kit.createDefaultDocument();
        this.editorView.setDocument(doc);
        this.editorView.setText(this.editorView.getText());
    }

    void setShowTableHorizontalLines(boolean selected) {
        HTMLEditorKit kit = (HTMLEditorKit)this.editorView.getEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        
        String showLines = "tr {\n" +
                            "  border-left: 1px solid black;\n" +
                            "  border-right:1px solid black;\n" +
                            "}";
        
        String hideLines = "tr {\n" +
                            "  border-left: none;\n" +
                            "  border-right: none;\n" +
                            "}";
        
        if(selected)
        {
            styleSheet.removeStyle(hideLines);
            styleSheet.addRule(showLines);
        }
        else
        {
            styleSheet.removeStyle(showLines);
            styleSheet.addRule(hideLines);
        }
        
        
        Document doc = kit.createDefaultDocument();
        this.editorView.setDocument(doc);
        this.editorView.setText(this.editorView.getText());
    }
}
