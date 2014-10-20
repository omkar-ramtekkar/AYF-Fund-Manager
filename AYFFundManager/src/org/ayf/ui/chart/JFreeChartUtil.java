/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui.chart;

import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JPanel;
import org.ayf.reports.ReportData;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.CategoryPlot;

/**
 *
 * @author om
 */
public class JFreeChartUtil {
    
    public static JPanel createBarChart(ReportData data)
    {
        return null;
    }
    
    public static void wrapLongLegends(CategoryPlot plot) 
    {
      LegendItemCollection chartLegend = plot.getLegendItems();
      LegendItemCollection res = new LegendItemCollection();
      for (int i = 0; i < chartLegend.getItemCount(); i++) {
         LegendItem item = chartLegend.get(i);
         String label = item.getLabel();
         String[] parts = wrapText(label, 30);
         int p = 0;
         for (String part : parts) {
            if (p == 0) {
               res.add(new LegendItem(part, item.getDescription(), item.getToolTipText(), item.getURLText(), item.isShapeVisible(), item.getShape(), item.isShapeFilled(), item.getFillPaint(), item.isShapeOutlineVisible(), item.getOutlinePaint(), item.getOutlineStroke(), item.isLineVisible(), item.getLine(), item.getLineStroke(), item.getLinePaint()));
            } else {
               res.add(new LegendItem(part, item.getDescription(), item.getToolTipText(), item.getURLText(), false, item.getShape(), item.isShapeFilled(), item.getFillPaint(), false, item.getOutlinePaint(), item.getOutlineStroke(), false, item.getLine(), item.getLineStroke(), item.getLinePaint()));
            }
            p++;
         }
      }
      plot.setFixedLegendItems(res);
   }
    
    static String [] wrapText (String text, int len)
    {
      // return empty array for null text
      if (text == null)
      return new String [] {};

      // return text if len is zero or less
      if (len <= 0)
      return new String [] {text};

      // return text if less than length
      if (text.length() <= len)
      return new String [] {text};

      char [] chars = text.toCharArray();
      Vector lines = new Vector();
      StringBuffer line = new StringBuffer();
      StringBuffer word = new StringBuffer();

      for (int i = 0; i < chars.length; i++) {
        word.append(chars[i]);

        if (chars[i] == ' ') {
          if ((line.length() + word.length()) > len) {
            lines.add(line.toString());
            line.delete(0, line.length());
          }

          line.append(word);
          word.delete(0, word.length());
        }
      }

      // handle any extra chars in current word
      if (word.length() > 0) {
        if ((line.length() + word.length()) > len) {
          lines.add(line.toString());
          line.delete(0, line.length());
        }
        line.append(word);
      }

      // handle extra line
      if (line.length() > 0) {
        lines.add(line.toString());
      }

      String [] ret = new String[lines.size()];
      int c = 0; // counter
      for (Enumeration e = lines.elements(); e.hasMoreElements(); c++) {
        ret[c] = (String) e.nextElement();
      }

      return ret;
    }
}
