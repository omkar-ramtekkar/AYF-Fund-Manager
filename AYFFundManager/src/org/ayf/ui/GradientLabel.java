/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui;

/**
 *
 * @author om
 */
/*
 *  soapUI, copyright (C) 2004-2009 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */


import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Paint;

public class GradientLabel extends Label
{
  // ------------------------------ FIELDS ------------------------------

  private Color start;
  private Color end;

  // --------------------------- CONSTRUCTORS ---------------------------

  public GradientLabel( String text )
  {
    super( text );

    start = Color.LIGHT_GRAY;
    end = start.darker();
  }

  public GradientLabel( String text, Color start, Color end )
  {
    super( text );
    this.start = start;
    this.end = end;
  }

  // -------------------------- OTHER METHODS --------------------------

  public void paint( Graphics g )
  {
    int width = getWidth();
    int height = getHeight();

    // Create the gradient paint
    GradientPaint paint = new GradientPaint( 0, 0, start, width, height, end, true );
    
    // we need to cast to Graphics2D for this operation
    Graphics2D g2d = ( Graphics2D )g;

    // save the old paint
    Paint oldPaint = g2d.getPaint();

    // set the paint to use for this operation
    g2d.setPaint( paint );

    // fill the background using the paint
    g2d.fillRoundRect(LEFT, LEFT, width, height, 2, 2);
        
    // restore the original paint
    g2d.setPaint( oldPaint );

    super.paint( g );
  }
}

