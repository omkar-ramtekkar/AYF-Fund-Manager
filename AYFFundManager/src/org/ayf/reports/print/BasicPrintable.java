/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.print;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import org.ayf.reports.views.BaseReportView;

/**
 *
 * @author om
 */
public class BasicPrintable implements Printable, HTMLSerializable {
    
    PrintInformation printInfo;
    MessageFormat header;
    MessageFormat footer;
    
    public BasicPrintable(PrintInformation printInfo) {
        this.printInfo = printInfo;
    }

    public PrintInformation getPrintInfo() {
        return printInfo;
    }
    
    public PageFormat getPageFormat()
    {
        PageFormat pf = new PageFormat();
        
        int ix = (int)pf.getImageableX();
        int iy = (int)pf.getImageableY();

        /* width and height of the imageable area */
        int iw = (int)pf.getImageableWidth();
        int ih = (int)pf.getImageableHeight();

        /* width of the clipboard image pieces to be painted on the left */
        int leftWidth = getPrintInfo().getClipLeft().getWidth();

        /* width of the clipboard image pieces to be painted on the right */
        int rightWidth = getPrintInfo().getClipRight().getWidth();

        /* height of the clipboard image pieces to be painted on the top */
        int topHeight = getPrintInfo().getClipTop().getHeight();

        /* height of the clipboard image pieces to be painted on the bottom */
        int bottomHeight = getPrintInfo().getClipBottom().getHeight();

        /* height of the final grades label */
        int finalGradesHeight = 0;//finalGrades.getHeight();


        /*
         * First, calculate the shrunken area that we want the table to print
         * into.
         */


        /* inset the table from the left and right images by 10 */
        final int tableX = ix + leftWidth + 10;
        final int tableW = iw - (leftWidth + 10) - (rightWidth + 10);

        /*
         * inset the table top to leave space for the top image +
         * 10 pixels + the final grades label + 10 pixels.
         */
        final int tableY = iy + topHeight + 10 + finalGradesHeight + 10;

        /* inset the table bottom by the height of the bottom image */
        final int tableH = ih - (topHeight + 10) - (finalGradesHeight + 10) - bottomHeight;


        /*
         * Now print the table into this smaller area.
         */


        /* create a new page format representing the shrunken area to print the table into */
        PageFormat format = new PageFormat() {
            @Override
            public double getImageableX() {return tableX;}
            @Override
            public double getImageableY() {return tableY;}
            @Override
            public double getImageableWidth() {return tableW;}
            @Override
            public double getImageableHeight() {return tableH;}
        };
        
        return format;
    }
    
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException 
    {
        
        int ix = (int)pf.getImageableX();
        int iy = (int)pf.getImageableY();

        /* width and height of the imageable area */
        int iw = (int)pf.getImageableWidth();
        int ih = (int)pf.getImageableHeight();

        /* width of the clipboard image pieces to be painted on the left */
        int leftWidth = getPrintInfo().getClipLeft().getWidth();

        /* width of the clipboard image pieces to be painted on the right */
        int rightWidth = getPrintInfo().getClipRight().getWidth();

        /* height of the clipboard image pieces to be painted on the top */
        int topHeight = getPrintInfo().getClipTop().getHeight();

        /* height of the clipboard image pieces to be painted on the bottom */
        int bottomHeight = getPrintInfo().getClipBottom().getHeight();

        /* height of the final grades label */
        int finalGradesHeight = 0;//finalGrades.getHeight();


        /*
         * First, calculate the shrunken area that we want the table to print
         * into.
         */


        /* inset the table from the left and right images by 10 */
        final int tableX = ix + leftWidth + 10;
        final int tableW = iw - (leftWidth + 10) - (rightWidth + 10);

        /*
         * inset the table top to leave space for the top image +
         * 10 pixels + the final grades label + 10 pixels.
         */
        final int tableY = iy + topHeight + 10 + finalGradesHeight + 10;

        /* inset the table bottom by the height of the bottom image */
        final int tableH = ih - (topHeight + 10) - (finalGradesHeight + 10) - bottomHeight;


        /*
         * Now print the table into this smaller area.
         */


        /* create a new page format representing the shrunken area to print the table into */
        PageFormat format = new PageFormat() {
            @Override
            public double getImageableX() {return tableX;}
            @Override
            public double getImageableY() {return tableY;}
            @Override
            public double getImageableWidth() {return tableW;}
            @Override
            public double getImageableHeight() {return tableH;}
        };

        /*
         * We'll use a copy of the graphics to print the table to. This protects
         * us against changes that the delegate printable could make to the graphics
         * object.
         */
        Graphics gCopy = g.create();
        
        getPrintableView().setHeader(this.header);
        getPrintableView().setHeader(this.footer);

        /* print the table into the shrunken area */
        int retVal = getPrintableView().print(gCopy, format, pageIndex);

        /* if there's no pages left, return */
        if (retVal == NO_SUCH_PAGE) {
            return retVal;
        }

        /* dispose of the graphics copy */
        gCopy.dispose();


        /*
         * Now that we've printed the table, assemble the clipboard image around
         * the outside.
         */


        int leftEnd = ix + leftWidth;
        int clipTopCenterStart = ix + (int)((iw - getPrintInfo().getClipTopCenter().getWidth()) / 2.0f);
        int clipTopCenterEnd = clipTopCenterStart + getPrintInfo().getClipTopCenter().getWidth();
        int rightStart = ix + iw - rightWidth;

        /* draw top left corner */
        g.drawImage(getPrintInfo().getClipTopLeft(), ix, iy, null);

        /* stretch top from top left corner to center image */
        g.drawImage(getPrintInfo().getClipTop(), leftEnd, iy, clipTopCenterStart - leftEnd, topHeight, null);

        /* stretch top from center image to top right corner */
        g.drawImage(getPrintInfo().getClipTop(), clipTopCenterEnd, iy, rightStart - clipTopCenterEnd, topHeight, null);

        /* draw top right corner */
        g.drawImage(getPrintInfo().getClipTopRight(), rightStart, iy, null);

        /* draw top center */
        g.drawImage(getPrintInfo().getClipTopCenter(), clipTopCenterStart, iy, null);

        //int finalGradesStart = ix + (int)((iw - finalGrades.getWidth()) / 2.0f);

        /* draw label */
        //g.drawImage(finalGrades, finalGradesStart, iy + topHeight + 10, null);

        int bottomY = iy + ih - bottomHeight;

        /* draw bottom left corner */
        g.drawImage(getPrintInfo().getClipBottomLeft(), ix, bottomY, null);

        /* draw bottom right corner */
        g.drawImage(getPrintInfo().getClipBottomRight(), rightStart, bottomY, null);

        /* stretch the bottom image from left to right */
        g.drawImage(getPrintInfo().getClipBottom(), leftEnd, bottomY, rightStart - leftEnd, bottomHeight, null);

        /* stretch left side  from top to bottom */
        g.drawImage(getPrintInfo().getClipLeft(), ix, iy + topHeight, leftWidth, bottomY - iy - topHeight, null);

        /* stretch right side from top to bottom */
        g.drawImage(getPrintInfo().getClipRight(), rightStart, iy + topHeight, rightWidth, bottomY - iy - topHeight, null);

        return PAGE_EXISTS;
            
    }

    String getDisplayName() {
        return this.getPrintInfo().getBaseReportView().getDisplayName();
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    public PrintableView getPrintableView() 
    {
        return this.printInfo.getPrintableView();
    }
    
    public BaseReportView getBaseReportView()
    {
        return this.printInfo.getBaseReportView();
    }

    public void setHeader(MessageFormat header) {
        this.header = header;
    }

    public void setFooter(MessageFormat footer) {
        this.footer = footer;
    }

    void setPrintPageWidth(boolean printPageWidth) {
        this.getPrintableView().setPrintPageWidth(printPageWidth);
    }

    @Override
    public String getHtml() {
        return getPrintableView().getHtml();
    }
}
