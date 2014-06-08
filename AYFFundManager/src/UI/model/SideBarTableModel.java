/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI.model;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author om
 */

public final class SideBarTableModel extends AbstractTableModel{

    public class Option
    {
        String title;
        Option parentOption;
        boolean isSelected;

        public Option(String title, Option parent) {
            this.title = title;
            this.parentOption = parent;
            
            if(parent instanceof HeaderOption)
            {
                HeaderOption header = (HeaderOption) parent;
                header.addSubOption(this);
            }
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
            subOptions = new ArrayList<>();
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
        this.options = new ArrayList<>(15);
        
        HeaderOption dashboardOption = new HeaderOption("Dashboard");
        this.options.add(dashboardOption);
        dashboardOption.addSubOption(new Option("DashboardSubOption1", dashboardOption));
        dashboardOption.addSubOption(new Option("DashboardSubOption2", dashboardOption));
        dashboardOption.addSubOption(new Option("DashboardSubOption3", dashboardOption));
        
        HeaderOption reportOption = new HeaderOption("Reports");
        this.options.add(reportOption);
        reportOption.addSubOption(new Option("ReportOption1", reportOption));
        reportOption.addSubOption(new Option("ReportOption2", reportOption));
        reportOption.addSubOption(new Option("ReportOption3", reportOption));
        reportOption.addSubOption(new Option("ReportOption4", reportOption));
        reportOption.addSubOption(new Option("ReportOption5", reportOption));
        
        HeaderOption notificationOption = new HeaderOption("Notifications");
        this.options.add(notificationOption);
        notificationOption.addSubOption(new Option("NotificationOption1", notificationOption));
        notificationOption.addSubOption(new Option("NotificationOption2", notificationOption));
        notificationOption.addSubOption(new Option("NotificationOption3", notificationOption));
        notificationOption.addSubOption(new Option("NotificationOption4", notificationOption));
        
        HeaderOption miscellaneousOption = new HeaderOption("Miscellaneous");
        this.options.add(miscellaneousOption);
        miscellaneousOption.addSubOption(new Option("MiscellaneousOption1", miscellaneousOption));
        miscellaneousOption.addSubOption(new Option("MiscellaneousOption2", miscellaneousOption));
        miscellaneousOption.addSubOption(new Option("MiscellaneousOption3", miscellaneousOption));
        miscellaneousOption.addSubOption(new Option("MiscellaneousOption4", miscellaneousOption));
        
        this.filteredOption = new ArrayList<>(this.options);
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

