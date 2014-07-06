/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.command;

import org.ayf.models.SideBarTableModel;

/**
 *
 * @author om
 */
public class ReportCommand extends Command
{
    SideBarTableModel.Option option;
    
    public ReportCommand(SideBarTableModel.Option option, SubCommandType type) {
        super(CommandType.Report, type);
        this.option = option;
    }

    public SideBarTableModel.Option getOption() {
        return option;
    }
}