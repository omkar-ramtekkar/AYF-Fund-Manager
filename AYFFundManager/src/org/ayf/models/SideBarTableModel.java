/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.models;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author om
 */

public final class SideBarTableModel extends AbstractTableModel{

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
    }
    
    
    public SideBarTableModel() {
        generateSideBarOptions();
    }
    
    public void generateSideBarOptions()
    {
        this.options = new ArrayList();
        
        HeaderOption dashboardOption = new HeaderOption(Command.CommandType.Dashboard);
        this.options.add(dashboardOption);
        
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

    public void clickEvent(MouseEvent event)
    {
        JTable table =(JTable) event.getSource();
        int rowIndex = table.rowAtPoint(event.getPoint());
        Object value = table.getModel().getValueAt(rowIndex, 0);
        
        if(table.getSelectedRowCount() > 0) table.clearSelection();
        if(value.getClass().equals(HeaderOption.class))
        {
            if(this.selectedHelderOption != value)
            {
                if(this.selectedHelderOption != null)
                {
                    this.filteredOption.removeAll(this.selectedHelderOption.getSubOptions());
                }
                this.selectedHelderOption = (HeaderOption)value;
                this.filteredOption.addAll(this.filteredOption.indexOf(this.selectedHelderOption) + 1, this.selectedHelderOption.getSubOptions());
            }
            else if(this.selectedHelderOption != null)
            {
                this.filteredOption.removeAll(this.selectedHelderOption.getSubOptions());
                this.selectedHelderOption = null;
                this.selectedSubOption = null;
            }
            
            this.fireTableDataChanged();
            
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Logger.getLogger(SideBarTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.selectedSubOption != value)
        {
            this.selectedSubOption = (Option)value;
        }
    }
    //Private data members
    private ArrayList<Option> options;
    private ArrayList<Option> filteredOption;
    private HeaderOption selectedHelderOption;
    private Option selectedSubOption;
}

