/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author om
 */
public class ComboBoxRenderer implements ListCellRenderer {

        private JLabel defaultLabel;

        public ComboBoxRenderer() {
        }

        public Component getListCellRendererComponent(JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Component) {
                Component c = (Component) value;
                if (isSelected) {
                    c.setBackground(list.getSelectionBackground());
                    c.setForeground(list.getSelectionForeground());
                } else {
                    c.setBackground(list.getBackground());
                    c.setForeground(list.getForeground());
                }
                return c;
            } else {
                String newValue = "";
                if(value != null) newValue = value.toString();
                if (defaultLabel == null) {
                    defaultLabel = new JLabel(newValue.toString());
                } else {
                    defaultLabel.setText(newValue.toString());
                }
                return defaultLabel;
            }
        }
    }
