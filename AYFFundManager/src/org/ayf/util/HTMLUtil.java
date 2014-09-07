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
    
    String horizontleLine()
    {
        return "<hr />";
    }
    
    String lineBreak()
    {
        return "<p>&nbsp;</p>";
    }
    
    String heading1(String text)
    {
        String html = "<h1>__element__</strong></h1>";
        return html.replace(placeholder, text);
    }
    
    String heading2(String text)
    {
        String html = "<h2>__element__</strong></h2>";
        return html.replace(placeholder, text);
    }
    
    String image(String url)
    {
        String html = "<p><img alt=\"\" src=\"__element__\" style=\"width: 80px; height: 80px; border-width: 0px; border-style: solid; margin: 0px 10px; float: left;\" />&nbsp; &nbsp; &nbsp;&nbsp;</p>";
        return html.replace(placeholder, url);
    }
    
    String centerText(String text)
    {
        String html ="<p style=\"text-align: center;\">__element__</p>";
        return html.replace("__element__", text);
    }
    
    String newPage()
    {
        return "<p>&nbsp;</p><div style=\"page-break-after: always;\"><span style=\"display: none;\">&nbsp;</span></div><p>__element__</p>";
    }
    
    String decorate(String baseHtml, String subHtml)
    {
        return baseHtml.replace("__element__", subHtml);
    }
    
    String orderedListElement(String text)
    {
        String lineItem = "<li>__element__</li>";
        return lineItem.replace(placeholder, text);
    }
    
    String orderedList()
    {
        return "<ol>__element__</ol>";
    }
    
    String orderList(Vector elements)
    {
        return null;
    }
}