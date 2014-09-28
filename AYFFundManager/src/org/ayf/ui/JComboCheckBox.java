/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ayf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

/**
 *
 * @author om
 */
public class JComboCheckBox extends JComboBox {

    public JComboCheckBox() {
        addStuff();
    }

    public JComboCheckBox(JCheckBox[] items) {
        super(items);
        addStuff();
    }

    public JComboCheckBox(Vector items) {
        super(items);
        convertItemsToCheckBox(items);
        addStuff();
    }
    
    void convertItemsToCheckBox(Vector items)
    {
        Vector<CheckBoxItem> checkBoxes = new Vector<CheckBoxItem>(items.size());
        for (Object object : items)
        {
            CheckBoxItem box = new CheckBoxItem(object);
            box.setSelected(true);
            checkBoxes.add(box);
        }
        
        setModel(new DefaultComboBoxModel(checkBoxes));
    }

    public JComboCheckBox(ComboBoxModel aModel) {
        super(aModel);
        addStuff();
    }

    @Override
    public void setModel(ComboBoxModel aModel) {
        super.setModel(aModel); //To change body of generated methods, choose Tools | Templates.
    }

    public void setItems(Vector items)
    {
        convertItemsToCheckBox(items);
    }
    

    private void addStuff() {
        setRenderer(new ComboBoxRenderer());
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                itemSelected();
            }
        });
    }
    
    public Object[] getSelectedItems()
    {
        Vector<Object> selectedItems = new Vector<Object>(getItemCount());

        for(int i=0; i<getItemCount(); ++i)
        {
            Object item = getItemAt(i);
            if(item instanceof CheckBoxItem)
            {
                CheckBoxItem ckItem = (CheckBoxItem)item;
                if(ckItem.isSelected())
                {
                    selectedItems.add(ckItem.getItem());
                }
            }
        }
        
        return selectedItems.toArray();
        
    }

    private void itemSelected() {
        if (getSelectedItem() instanceof JCheckBox) {
            JCheckBox jcb = (JCheckBox) getSelectedItem();
            jcb.setSelected(!jcb.isSelected());
            
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() 
                {
                    setPopupVisible(true);
                }
            });
        }
    }

    
    
    public class CheckBoxItem extends JCheckBox
    {
        Object item;

        public CheckBoxItem(Object item) {
            super();
            this.item = item;
        }
        
        @Override
        public String toString() {
            return item.toString();
        }

        public void setItem(Object item) {
            this.item = item;
        }

        public Object getItem() {
            return item;
        }

        @Override
        public String getText() {
            if(getItem() != null)
                return getItem().toString();
            else return "";
        }
    }
}
