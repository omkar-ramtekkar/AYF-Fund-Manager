/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.models;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author om
 */

public final class SideBarTableModel extends AbstractTableModel implements MouseListener, MouseMotionListener
{
    public class Option
    {
        Option parentOption;
        boolean isSelected;
        Command.CommandType optionType;

        public Option(Command.CommandType type, Option parent) {
            this.optionType = type;
            this.parentOption = parent;
            
            /*if(parent instanceof HeaderOption)
            {
                HeaderOption header = (HeaderOption) parent;
                header.addSubOption(this);
            }*/
        }
        
        public boolean isNoneOption()
        {
            return getOptionType() == Command.CommandType.None;
        }
        
        public boolean isHeaderOption()
        {
            return false;
        }

        public Command.CommandType getOptionType() {
            return optionType;
        }
        
        
        public String getTitle() {
            return Command.getDisplayNameForType(getOptionType());
        }
        
        public Option getParentOption()
        {
            return this.parentOption;
        }
        
        public boolean isSelected()
        {
            return this.isSelected;
        }
        
        public void setSelected(boolean bSelected)
        {
            this.isSelected = bSelected;
        }
        
        public ArrayList<Option> getSubOptions() {
            return null;
        }
        
        public boolean hasSubOptions()
        {
            return (getSubOptions() != null && (getSubOptions().size() - 1) > 0);
        }
        
        @Override
        public String toString()
        {
            return getTitle();
        }
    }
    
    public class HeaderOption extends Option
    {
        ArrayList<Option> subOptions;
               
        public HeaderOption(Command.CommandType type)
        {
            super(type, null);
            subOptions = new ArrayList();
        }
        
        public void addSubOption(Option option)
        {
            subOptions.add(option);
        }

        public ArrayList<Option> getSubOptions() {
            return subOptions;
        }
        
        public boolean isHeaderOption()
        {
            return true;
        }
    }
    
    
    public SideBarTableModel() {
        generateSideBarOptions();
    }
    
    public void generateSideBarOptions()
    {
        this.options = new ArrayList();
        
        HeaderOption dashboardOption = new HeaderOption(Command.CommandType.Dashboard);
        this.options.add(dashboardOption);
        this.selectedHelderOption = dashboardOption;
        
        HeaderOption detailsOption = new HeaderOption(Command.CommandType.Details);
        this.options.add(detailsOption);
        detailsOption.addSubOption(new Option(Command.CommandType.DetailsAllMembers, detailsOption));
        detailsOption.addSubOption(new Option(Command.CommandType.DetailsAllDonors, detailsOption));
        detailsOption.addSubOption(new Option(Command.CommandType.DetailsAllExpenses, detailsOption));
        detailsOption.addSubOption(new Option(Command.CommandType.DetailsAllCashFlows, detailsOption));
        detailsOption.addSubOption(new Option(Command.CommandType.None, detailsOption));
        
        
        HeaderOption statementsOption = new HeaderOption(Command.CommandType.Statements);
        this.options.add(statementsOption);
        statementsOption.addSubOption(new Option(Command.CommandType.StatementsByMember, statementsOption));
        statementsOption.addSubOption(new Option(Command.CommandType.StatementsCashFlows, statementsOption));
        statementsOption.addSubOption(new Option(Command.CommandType.None, statementsOption));
        
        HeaderOption notificationOption = new HeaderOption(Command.CommandType.Notifications);
        this.options.add(notificationOption);
        notificationOption.addSubOption(new Option(Command.CommandType.NotificationsMemberSubscription, notificationOption));
        notificationOption.addSubOption(new Option(Command.CommandType.None, notificationOption));
        
        
        HeaderOption miscellaneousOption = new HeaderOption(Command.CommandType.Miscellaneous);
        this.options.add(miscellaneousOption);
        miscellaneousOption.addSubOption(new Option(Command.CommandType.None, miscellaneousOption));
        
        this.filteredOption = new ArrayList(this.options);
    }
    
    @Override
    public int getRowCount() {
        return this.filteredOption.size();
    }

    @Override
    public int getColumnCount() {
         return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.filteredOption.get(rowIndex);
    }

    //SideBarTableModel
    public HeaderOption getSelectedHelderOption() {
        return selectedHelderOption;
    }

    public Option getSelectedSubOption() {
        return selectedSubOption;
    }

    @Override
    public void mouseClicked(MouseEvent event)
    {
        JTable table =(JTable) event.getSource();
        int rowIndex = table.rowAtPoint(event.getPoint());
        Object value = table.getModel().getValueAt(rowIndex, 0);
        
        //Check if user clicks on Header Option
        if(value.getClass().equals(HeaderOption.class))
        {
            boolean isTableDataChanged = false;
            //Check if user clicked on already selected option
            if(this.selectedHelderOption != value)
            {
                //Remove suboptions of old selected option
                if(this.selectedHelderOption != null)
                {
                    this.filteredOption.removeAll(this.selectedHelderOption.getSubOptions());
                }
                
                //Add sub options of newly selected header option
                this.selectedHelderOption = (HeaderOption)value;
                this.selectedSubOption = null;
                this.filteredOption.addAll(this.filteredOption.indexOf(this.selectedHelderOption) + 1, this.selectedHelderOption.getSubOptions());
                isTableDataChanged = true;
            }
            //User clicked on already selected option, remove all it's sub options
            //ie unfold the header option
            else if(this.selectedHelderOption != null)
            {
                this.filteredOption.removeAll(this.selectedHelderOption.getSubOptions());
                this.selectedHelderOption = null;
                this.selectedSubOption = null;
                isTableDataChanged = true;
            }
            
            if(isTableDataChanged)
            {
                this.fireTableDataChanged();
            }
        }
        else if(this.selectedSubOption != value)
        {
            //User changed the suboption
            this.selectedSubOption = (Option)value;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.highlitedOption = null;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        JTable table =(JTable) e.getSource();
        int rowIndex = table.rowAtPoint(e.getPoint());
        Object value = table.getModel().getValueAt(rowIndex, 0);
        
        if(value instanceof Option)
        {
            Option option = (Option) value;
            highlitedOption = option;
        }
    }

    public Option getHighlitedOption() {
        return highlitedOption;
    }

    
    //Private data members
    private ArrayList<Option> options;
    private ArrayList<Option> filteredOption;
    private HeaderOption selectedHelderOption;
    private Option selectedSubOption;
    private Option highlitedOption;
}

