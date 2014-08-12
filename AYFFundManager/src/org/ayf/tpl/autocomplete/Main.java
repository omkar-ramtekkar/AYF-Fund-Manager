package org.ayf.tpl.autocomplete;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextPane;


public class Main extends JFrame {

	private JTextPane textPane;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().init();
	}
	
	private void init() {
		//textPane = new SuggestionTextPane();
		textPane = new JTextPane();
		//textPane.setContentType("text/html");
		//textPane.setLayout(null);
		textPane.setSize(640, 320);
		getContentPane().add(textPane);
		
		
		List<String> list = new ArrayList<String>();
		list.add("apple");
		list.add("almond");
		list.add("banana");
		Java2sAutoComboBox autoComboBox = new Java2sAutoComboBox();
                autoComboBox.setList(list);
		autoComboBox.setSize(180, 24);
		autoComboBox.setStrict(false);
		
		textPane.insertComponent(autoComboBox);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		setSize(640, 320);
		setVisible(true);
	}
	
	

}
