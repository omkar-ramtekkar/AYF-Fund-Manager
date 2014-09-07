/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.reports.print;

import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import org.ayf.managers.ResourceManager;
import org.ayf.reports.views.BaseReportView;

/**
 *
 * @author om
 */
public class PrintInformation 
{
    PrintableView printableView;
    BaseReportView baseReportView = null;
    PageFormat pageFormat = null;
    int numberOfPages = 0;
    
    public static BufferedImage CLIP_TOP_LEFT;
    public static BufferedImage CLIP_TOP;
    public static BufferedImage CLIP_TOP_CENTER;
    public static BufferedImage CLIP_TOP_RIGHT;
    public static BufferedImage CLIP_BOTTOM_LEFT;
    public static BufferedImage CLIP_BOTTOM;
    public static BufferedImage CLIP_BOTTOM_RIGHT;
    public static BufferedImage CLIP_LEFT;
    public static BufferedImage CLIP_RIGHT;
    
    
    private BufferedImage clipTopLeft;
    private BufferedImage clipTop;
    private BufferedImage clipTopCenter;
    private BufferedImage clipTopRight;
    private BufferedImage clipBottomLeft;
    private BufferedImage clipBottom;
    private BufferedImage clipBottomRight;
    private BufferedImage clipLeft;
    private BufferedImage clipRight;

    static
    {
        intitialize();
    }
    
    private static void intitialize() 
    {
        CLIP_TOP_LEFT = ResourceManager.getImage("/report/clipTopLeft");
        CLIP_TOP = ResourceManager.getImage("/report/clipTop");
        CLIP_TOP_CENTER = ResourceManager.getImage("/report/clipTopCenter");
        CLIP_TOP_RIGHT = ResourceManager.getImage("/report/clipTopRight");
        CLIP_BOTTOM_LEFT = ResourceManager.getImage("/report/clipBottomLeft");
        CLIP_BOTTOM = ResourceManager.getImage("/report/clipBottom");
        CLIP_BOTTOM_RIGHT = ResourceManager.getImage("/report/clipBottomRight");
        CLIP_LEFT = ResourceManager.getImage("/report/clipLeft");
        CLIP_RIGHT = ResourceManager.getImage("/report/clipRight");
    }
    
    public static PrintInformation getStandardPrintInformation()
    {
        return new PrintInformation();
    }

    public PrintInformation() {
        initializeBaseInfo();
    }

    public PrintInformation(PrintableView printableView) {
        this();
        this.printableView = printableView;
    }
    
    
    private void initializeBaseInfo()
    {
        setClipBottom(CLIP_BOTTOM);
        setClipBottomLeft(CLIP_BOTTOM_LEFT);
        setClipBottomRight(CLIP_BOTTOM_RIGHT);
        setClipLeft(CLIP_LEFT);
        setClipRight(CLIP_RIGHT);
        setClipTop(CLIP_TOP);
        setClipTopCenter(CLIP_TOP_CENTER);
        setClipTopLeft(CLIP_TOP_LEFT);
        setClipTopRight(CLIP_TOP_RIGHT);
    }

    public PrintableView getPrintableView() {
        return printableView;
    }

    public PageFormat getPageFormat() {
        return pageFormat;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public BaseReportView getBaseReportView() {
        return baseReportView;
    }

    public BufferedImage getClipTopLeft() {
        return clipTopLeft;
    }

    public BufferedImage getClipTop() {
        return clipTop;
    }

    public BufferedImage getClipTopCenter() {
        return clipTopCenter;
    }

    public BufferedImage getClipTopRight() {
        return clipTopRight;
    }

    public BufferedImage getClipBottomLeft() {
        return clipBottomLeft;
    }

    public BufferedImage getClipBottom() {
        return clipBottom;
    }

    public BufferedImage getClipBottomRight() {
        return clipBottomRight;
    }

    public BufferedImage getClipLeft() {
        return clipLeft;
    }

    public BufferedImage getClipRight() {
        return clipRight;
    }

    public void setPrintableView(PrintableView printableView) {
        this.printableView = printableView;
    }
    
    public void setBaseReportView(BaseReportView printableReportView) {
        this.baseReportView = printableReportView;
    }

    
    public void setPageFormat(PageFormat pageFormat) {
        this.pageFormat = pageFormat;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setClipTopLeft(BufferedImage clipTopLeft) {
        this.clipTopLeft = clipTopLeft;
    }

    public void setClipTop(BufferedImage clipTop) {
        this.clipTop = clipTop;
    }

    public void setClipTopCenter(BufferedImage clipTopCenter) {
        this.clipTopCenter = clipTopCenter;
    }

    public void setClipTopRight(BufferedImage clipTopRight) {
        this.clipTopRight = clipTopRight;
    }

    public void setClipBottomLeft(BufferedImage clipBottomLeft) {
        this.clipBottomLeft = clipBottomLeft;
    }

    public void setClipBottom(BufferedImage clipBottom) {
        this.clipBottom = clipBottom;
    }

    public void setClipBottomRight(BufferedImage clipBottomRight) {
        this.clipBottomRight = clipBottomRight;
    }

    public void setClipLeft(BufferedImage clipLeft) {
        this.clipLeft = clipLeft;
    }

    public void setClipRight(BufferedImage clipRight) {
        this.clipRight = clipRight;
    }
}
