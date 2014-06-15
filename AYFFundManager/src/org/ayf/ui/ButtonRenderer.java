package org.ayf.ui;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import org.ayf.models.SideBarTableModel;
import org.ayf.tpl.color.util.Theme;
import org.ayf.tpl.glossybutton.ButtonType;
import org.ayf.tpl.glossybutton.StandardButton;



/**
 *  The ButtonRenderer class provides a renderer and an editor that looks like a
  JButton. The renderer and editor will then be used for a specified column
 *  in the table. The TableModel will contain the String to be displayed on
 *  the button.
 *
 *  The button can be invoked by a mouse click or by pressing the space bar
 *  when the cell has focus. Optionally a mnemonic can be set to invoke the
 *  button. When the button is invoked the provided Action is invoked. The
 *  source of the Action will be the table. The action command will contain
 *  the model row number of the button that was clicked.
 *
 */
public class ButtonRenderer implements TableCellRenderer
{
    private final StandardButton headerOptionButton;
    private final StandardButton subOptionButton;
    private static final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
    /**
     *  Create the ButtonColumn to be used as a renderer and editor. The
     *  renderer and editor will automatically be installed on the TableColumn
     *  of the specified column.
     *
     *  @param column the column to which the button renderer/editor is added
     */
    public ButtonRenderer(int column)
    {
            headerOptionButton = new StandardButton("");
            subOptionButton = new StandardButton("", ButtonType.BUTTON_ROUNDED_RECTANGLUR, Theme.STANDARD_LIGHTGRAY_THEME, Theme.STANDARD_GREEN_THEME, Theme.STANDARD_GREEN_THEME);
            subOptionButton.setDirection(-1);
    }

    //  Implement TableCellRenderer interface
    //
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        
        StandardButton target = null;  
        if(value.getClass().equals(SideBarTableModel.HeaderOption.class))
        {
            headerOptionButton.setText(value.toString());
            headerOptionButton.setSelected(true);
            table.setRowHeight(row, 60);
            target = headerOptionButton;
            SideBarTableModel model = (SideBarTableModel) table.getModel();
            if(model.getSelectedHelderOption() == value)
            {
                headerOptionButton.setDirection(SwingConstants.SOUTH);
            }
            else
            {
                headerOptionButton.setDirection(SwingConstants.EAST);
            }
        }
        else
        {
            table.setRowHeight(row, 40);
            this.subOptionButton.setText(value.toString());
            target = this.subOptionButton;
        }
        
        target.getModel().setSelected(isSelected);
        target.getModel().setRollover(hasFocus);
        
        //Set Direction option
        

        return target;
    }
}
