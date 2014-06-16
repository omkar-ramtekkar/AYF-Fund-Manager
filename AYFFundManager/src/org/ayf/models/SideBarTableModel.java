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

    enum OptionTypes
    {
        Dashboard,
        Reports,
        Notifications,
        Miscellaneous
    };
    
    public class Option
    {
        String title;
        Option parentOption;
        boolean isSelected;

        public Option(String title, Option parent) {
            this.title = title;
            this.parentOption = parent;
            
            /*if(parent instanceof HeaderOption)
            {
                HeaderOption header = (HeaderOption) parent;
                header.addSubOption(this);
            }*/
        }
        
        
        public Option(OptionTypes type, Option parent) {
            this.title = type.toString();
            this.parentOption = parent;
            
            /*if(parent instanceof HeaderOption)
            {
                HeaderOption header = (HeaderOption) parent;
                header.addSubOption(this);
            }*/
        }
        
        public String getTitle() {
            return title;
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
        
        public HeaderOption(String title) {
            super(title, null);
            subOptions = new ArrayList();
        }
        
        public HeaderOption(OptionTypes type)
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
        
        HeaderOption dashboardOption = new HeaderOption(OptionTypes.Dashboard);
        this.options.add(dashboardOption);
        
        HeaderOption reportOption = new HeaderOption(OptionTypes.Reports);
        this.options.add(reportOption);
        reportOption.addSubOption(new Option("Statement", reportOption));
        reportOption.addSubOption(new Option("Subscriptions", reportOption));
        reportOption.addSubOption(new Option("Donations", reportOption)); //member wise donation details
        
        HeaderOption notificationOption = new HeaderOption(OptionTypes.Notifications);
        this.options.add(notificationOption);
        notificationOption.addSubOption(new Option("Subscription Expiration", notificationOption));

        HeaderOption miscellaneousOption = new HeaderOption(OptionTypes.Miscellaneous);
        this.options.add(miscellaneousOption);
        miscellaneousOption.addSubOption(new Option("Infrastructure Funds", miscellaneousOption));
        
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

