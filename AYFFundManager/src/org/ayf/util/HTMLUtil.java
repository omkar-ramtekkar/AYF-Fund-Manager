/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.util;

import java.util.Vector;

/**
 *
 * @author om
 */
public class HTMLUtil {
    
    public static final String placeholder = "__element__";
    
    public enum TextAlignment {left, right, center, justify};
    public enum TablePropery {border, cellpadding, cellspacing, summary};
    
    public static String horizontleLine()
    {
        return "<hr />";
    }
    
    public static String lineBreak()
    {
        return "<p>&nbsp;</p>";
    }
    
    public static String heading1(String text)
    {
        String html = "<h1>__element__</strong></h1>";
        return html.replace(placeholder, text);
    }
    
    public static String heading2(String text)
    {
        String html = "<h2>__element__</strong></h2>";
        return html.replace(placeholder, text);
    }
    
    public static String image(String url)
    {
        String html = "<p><img alt=\"\" src=\"__element__\" style=\"width: 80px; height: 80px; border-width: 0px; border-style: solid; margin: 0px 10px; float: left;\" />&nbsp; &nbsp; &nbsp;&nbsp;</p>";
        return html.replace(placeholder, url);
    }
    
    public static String centerText(String text)
    {
        String html ="<p style=\"text-align: center;\">__element__</p>";
        return html.replace(placeholder, text);
    }
    
    public static String newPage()
    {
        return "<p>&nbsp;</p><div style=\"page-break-after: always;\"><span style=\"display: none;\">&nbsp;</span></div><p>__element__</p>";
    }
    
    public static String decorate(String baseHtml, String subHtml)
    {
        return baseHtml.replace(placeholder, subHtml);
    }
    
    public static String orderedListElement(String text)
    {
        String lineItem = "<li>__element__</li>";
        return lineItem.replace(placeholder, text);
    }
    
    public static String orderedList()
    {
        return "<ol>__element__</ol>";
    }
    
    public static String orderList(Vector elements)
    {
        //TODO implement
        return null;
    }
    
    public static String caption(String caption)
    {
        String c = "<caption>__element__</caption>";
        if(caption == null) return c.replace(placeholder, "");
        
        return c.replace(placeholder, caption);
    }
    
    public static String tableData(Object value, TextAlignment alignment)
    {
        if(value == null) value = "";
        
        String td = "<td style=\"text-align: __text__alignment__;\">__element__</td>";
        td = td.replace("__text__alignment__", alignment.toString());
        
        td = td.replace(placeholder, value.toString());
        
        return td;
    }
    
    public static String tableRow(Vector values, TextAlignment alignment)
    {
        StringBuilder buffer = new StringBuilder(values.size() * 20);
        buffer.append("<tr>");
        for (Object object : values) 
        {
            buffer.append(tableData(object, alignment));
        }
        
        buffer.append("</tr>");
        
        return buffer.toString();
    }
    
    public static String tableHeader(Vector columnNames, TextAlignment alignment)
    {
        StringBuilder buffer = new StringBuilder(columnNames.size() * 20);
        buffer.append("<tr>");
        for (Object object : columnNames) 
        {
            buffer.append("<th scope=\"col\">").
                    append(object != null ? object.toString() : "").
                    append("</th>");
        }
        
        buffer.append("</tr>");
                
        String tr = buffer.toString();
        
        StringBuilder header = new StringBuilder(tr.length() + 20);
        
        return header.append("<thead>").append(tr).append("</thead>").toString();
    }
    
    public static String tableBody(String rows)
    {
        if(rows == null) return "";
        
        StringBuilder buffer = new StringBuilder(rows.length() + 20);
        
        return buffer.append("<tbody>").append(rows).append("</tbody>").toString();
    }
    
    public static String table(int border, boolean showVerticalLines, boolean showHorizontalLines, int cellpadding, int cellspacing, int width, String summary,
            String caption, String tableHeader, String tableBody)
    {
        if(tableHeader == null) tableHeader = tableHeader(new Vector(), TextAlignment.left);
        if(caption == null) caption = caption("");
        if(tableBody == null) tableBody = tableBody("");
        
        StringBuilder buffer = new StringBuilder(tableHeader.length() + caption.length() + tableBody.length() + 50);
        
        String grid = showHorizontalLines && showVerticalLines ? "ALL" : 
                (showHorizontalLines ? "ROWS" : "COLS");
        StringBuilder table = new StringBuilder(50);
         table.append("<table align=\"center\" border=\"").
                 append(Integer.toString(border)).
                 append("\" RULES=").append(grid).
                 append("\" cellpadding=\"").
                 append(Integer.toString(cellpadding)).
                 append("\" cellspacing=\"").
                 append(Integer.toString(cellspacing)).
                 append("\" style=\"width: ").
                 append(Integer.toString(width)).
                 append("px;\" summary=\"\">");
        
        return buffer.append(table).append(caption).append(tableHeader).append(tableBody).append("</table>").toString();
    }
}