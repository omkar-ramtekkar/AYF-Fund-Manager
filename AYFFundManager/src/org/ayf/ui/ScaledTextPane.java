/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui;

/**
 * @author Stanislav Lapitsky
 * @version 1.0
 */

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.text.*;

public class ScaledTextPane extends JEditorPane 
{
    final SimpleAttributeSet attrs=new SimpleAttributeSet();

    public ScaledTextPane() {
        super();
        
        StyleConstants.setFontSize(attrs,16);
        setEditorKit(new ScaledEditorKit());
        StyledDocument doc=(StyledDocument)ScaledTextPane.this.getDocument();
        doc.setCharacterAttributes(0,1,attrs,true);
        try {
            StyleConstants.setFontFamily(attrs,"Lucida Sans");
            doc.insertString(0, "Lusida Sans font test\n", attrs);

            StyleConstants.setFontFamily(attrs,"Lucida Bright");
            doc.insertString(0, "Lucida Bright font test\n", attrs);

            StyleConstants.setFontFamily(attrs,"Lucida Sans Typewriter");
            doc.insertString(0, "Lucida Sans Typewriter font test\n", attrs);
        }
        catch (BadLocationException ex) {
        }
    }
    
    public void scale(double scale)
    {
        ScaledTextPane.this.getDocument().putProperty("ZOOM_FACTOR",new Double(scale));

        try {
            StyledDocument doc=(StyledDocument)ScaledTextPane.this.getDocument();
            doc.setCharacterAttributes(0,1,attrs,true);
            doc.insertString(0, "", null); //refresh
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void repaint(int x, int y, int width, int height) {
        super.repaint(0, 0, getWidth(), getHeight());
    }

}

class ScaledEditorKit extends StyledEditorKit {
    public ViewFactory getViewFactory() {
        return new StyledViewFactory();
    }

    class StyledViewFactory implements ViewFactory {

        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new LabelView(elem);
                }
                else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                }
                else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new ScaledView(elem, View.Y_AXIS);
                }
                else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                }
                else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }

            // default to text display
            return new LabelView(elem);
        }

    }
}

//-----------------------------------------------------------------
class ScaledView extends BoxView {
    public ScaledView(Element elem, int axis) {
        super(elem, axis);
    }

    public double getZoomFactor() {
        Double scale = (Double) getDocument().getProperty("ZOOM_FACTOR");
        if (scale != null) {
            return scale.doubleValue();
        }

        return 1;
    }

    public void paint(Graphics g, Shape allocation) {
        Graphics2D g2d = (Graphics2D) g;
        double zoomFactor = getZoomFactor();
        AffineTransform old = g2d.getTransform();
        g2d.scale(zoomFactor, zoomFactor);
        super.paint(g2d, allocation);
        g2d.setTransform(old);
    }

    public float getMinimumSpan(int axis) {
        float f = super.getMinimumSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    public float getMaximumSpan(int axis) {
        float f = super.getMaximumSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    public float getPreferredSpan(int axis) {
        float f = super.getPreferredSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    protected void layout(int width, int height) {
        super.layout(new Double(width / getZoomFactor()).intValue(),
                     new Double(height *
                                getZoomFactor()).intValue());
    }

    public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException {
        double zoomFactor = getZoomFactor();
        Rectangle alloc;
        alloc = a.getBounds();
        Shape s = super.modelToView(pos, alloc, b);
        alloc = s.getBounds();
        alloc.x *= zoomFactor;
        alloc.y *= zoomFactor;
        alloc.width *= zoomFactor;
        alloc.height *= zoomFactor;

        return alloc;
    }

    public int viewToModel(float x, float y, Shape a,
                           Position.Bias[] bias) {
        double zoomFactor = getZoomFactor();
        Rectangle alloc = a.getBounds();
        x /= zoomFactor;
        y /= zoomFactor;
        alloc.x /= zoomFactor;
        alloc.y /= zoomFactor;
        alloc.width /= zoomFactor;
        alloc.height /= zoomFactor;

        return super.viewToModel(x, y, alloc, bias);
    }

}
