package by.bsu.zuevvlad.seventhlab.logic.command;

import by.bsu.zuevvlad.seventhlab.logic.command.sql.update.SQLUpdateColumnCommand;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class CommandTest
{
    public CommandTest()
    {
        super();
    }

    @Test
    public final void commandShouldBeCreated()
    {
        final String nameOfTable = "employee_for_testing";
        final String nameOfUpdatedColumn = "salary";
        final String newValueOfColumn = "salary * 1.1";
        final Command command = new SQLUpdateColumnCommand(nameOfTable, nameOfUpdatedColumn, newValueOfColumn);
        final String expectedDescriptionOfCommand = "UPDATE " + nameOfTable + " SET " + nameOfUpdatedColumn
                + " = " + newValueOfColumn;
        final String actualDescriptionOfCommand = command.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }

    @Test
    public final void descriptionOfCommandShouldBeGot()
    {
        final String nameOfTable = "employee_for_testing";
        final String nameOfUpdatedColumn = "salary";
        final String newValueOfColumn = "salary * 1.1";
        final Command command = new SQLUpdateColumnCommand(nameOfTable, nameOfUpdatedColumn, newValueOfColumn);
        final String expectedDescriptionOfCommand = "UPDATE " + nameOfTable + " SET " + nameOfUpdatedColumn
                + " = " + newValueOfColumn;
        final String actualDescriptionOfCommand = command.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }
}
