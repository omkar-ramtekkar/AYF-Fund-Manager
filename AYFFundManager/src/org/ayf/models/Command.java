/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.models;

/**
 *
 * @author om
 */
public class Command {
    
    public enum CommandType
    {
        None,
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
        NotificationsMemberSubscription
    };
    
    public static String getDisplayNameForType(CommandType type)
    {
        switch(type)
        {
            case None:
                return "";
            case Dashboard:
                return "Dashboard";
            case Details:
                return "Details";
            case Statements:
                return "statements";
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
                return "All Members";
            case DetailsAllDonors:
                return "All Donations";
            case DetailsAllExpenses:
                return "All Expenses";
            case DetailsAllCashFlows:
                return "All Cash Flows";
            case StatementsByMember:
                return "Statement of Member";
            case StatementsCashFlows: //Pending cash receipts
                return "Statement of Cash Flows";
            case NotificationsMemberSubscription:
                return "Subscription Reminder";
        }
        
        return null;
    }
    
    SideBarTableModel.Option option;
    CommandType type;

    public Command(SideBarTableModel.Option option, CommandType type) {
        this.option = option;
        this.type = type;
    }

    public SideBarTableModel.Option getOption() {
        return option;
    }

    public CommandType getType() {
        return type;
    }
}
