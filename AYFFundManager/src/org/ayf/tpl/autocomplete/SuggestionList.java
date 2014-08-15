package org.ayf.tpl.autocomplete;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;


public class SuggestionList extends JComponent implements KeyListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7575440844332038057L;
	
    public SuggestionList() {
        super();

        // We must be non-opaque since we won't fill all pixels.
        // This will also stop the UI from filling our background.
        setOpaque(false);

        // Add an empty border around us to compensate for
        // the rounded corners.
        //setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
    }

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int width = getWidth();
        int height = getHeight();
        
        System.out.println(height);
        
        g.setColor(Color.blue);
        g.drawRoundRect(0, 0, width, height, height, height);
        
        g.drawString("boom", 0, 0);
        
//        g.setColor(Color.lightGray);
//        g.fillRoundRect(0, 0, width, height, height/2, height/2);
        
        
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.printf("keyTyped = %c\n", e.getKeyChar());
		
	}
}
