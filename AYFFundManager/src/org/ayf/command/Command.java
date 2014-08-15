/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.command;

/**
 *
 * @author om
 */
public class Command {
    
    public enum CommandType
    {
        None,
        Report,
        Toolbar,
        Menubar
    }
    
    public enum SubCommandType
    {
        None,
        
        //Report Commands
        Dashboard,
        Details,
        Statements,
        Notifications,
        Miscellaneous,
        DashboardDonation,
        DashboardProfitLoss,
        DashboardStatement,
        DetailsAllMembers,
        DetailsAllDonors,
        DetailsAllExpenses,
        DetailsAllCashFlows,
        StatementsByMember,
        StatementsCashFlows,
        NotificationsMemberSubscription,
        
        //Report Command for all kind of search reports
        GenericSearch,
        
        //Toolbar
        Home,
        UserAdd,
        UserDelete,
        UserEdit,
        UserSearch,
        UserInfo,
        Donate,
        Save,
        Download,
        Settings,
        AdminPassword,
        AdminUserName,
        PrintReport,
        ExportAsText,
        ExportAsExcel,
        ExportAsCSV,
        ExportAsPDF,
        ChangeDatabase
    }

    public Command(CommandType type, SubCommandType subType) {
        this.type = type;
        this.subType = subType;
    }

    public CommandType getType() {
        return type;
    }

    public SubCommandType getSubType() {
        return subType;
    }

    protected void setType(CommandType type) {
        this.type = type;
    }

    protected void setSubType(SubCommandType subType) {
        this.subType = subType;
    }
    
    public static String getDisplayNameForSubCommandType(SubCommandType type)
    {
        switch(type)
        {
            case None:
                return "";
            case Dashboard:
                return "Dashboard";
            case Details:
                return "Database";
            case Statements:
                return "Statements";
            case Notifications:
                return "Notifications";
            case Miscellaneous:
                return "Miscelleaneous";
            case DashboardDonation:
                return "Total Donation By Type";
            case DashboardProfitLoss:
                return "Total Income And Expenses";
            case DashboardStatement:
                return "Statement As Of Today";
            case DetailsAllMembers:
                return "Members";
            case DetailsAllDonors:
                return "Donations";
            case DetailsAllExpenses:
                return "Expenses";
            case DetailsAllCashFlows:
                return "Cash Flows";
            case StatementsByMember:
                return "Statement of Member";
            case StatementsCashFlows: //Pending cash receipts
                return "Statement of Cash Flows";
            case NotificationsMemberSubscription:
                return "Subscription Reminder";
            case ExportAsExcel:
                return "Export As Excel";
                
                
                
            ////////////////// Toolbar ///////////////////
            case UserAdd:
                return "Add Member";
            case UserDelete:
                return "Delete Member";
            case UserEdit:
                return "Edit Member";
            case UserSearch:
                return "Search Member";
            case UserInfo:
                return "Member Information";
            case Donate:
                return "Donate";
            case Save:
                return "Save";
            case Download:
                return "Download";
            case Settings:
                return "Settings";
        }
        
        return null;
    }
    
    
    private Command.CommandType type;
    private Command.SubCommandType subType;
}
