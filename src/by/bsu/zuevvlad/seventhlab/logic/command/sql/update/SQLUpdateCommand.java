package by.bsu.zuevvlad.seventhlab.logic.command.sql.update;

import by.bsu.zuevvlad.seventhlab.logic.command.Command;
import by.bsu.zuevvlad.seventhlab.logic.command.sql.exception.SQLCommandExecutionException;
import by.bsu.zuevvlad.seventhlab.logic.command.sql.update.exception.SQLUpdateCommandExecutionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQLUpdateCommand extends Command
{
    public SQLUpdateCommand(final String descriptionOfCommand)
    {
        super(descriptionOfCommand);
    }

    public final void execute(final Connection connectionToDataBase)
            throws SQLCommandExecutionException
    {
        try(final Statement statement = connectionToDataBase.createStatement())
        {
            final String descriptionOfCommand = this.getDescriptionOfCommand();
            statement.executeUpdate(descriptionOfCommand);
        }
        catch(final SQLException cause)
        {
            throw new SQLUpdateCommandExecutionException(cause);
        }
    }
}
