package by.bsu.zuevvlad.seventhlab.logic.command.sql.query;

import by.bsu.zuevvlad.seventhlab.logic.command.Command;
import by.bsu.zuevvlad.seventhlab.logic.command.sql.query.exception.SQLQueryCommandExecutionException;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class SQLQueryCommand extends Command
{
    public SQLQueryCommand(final String descriptionOfCommand)
    {
        super(descriptionOfCommand);
    }

    public abstract ResultSet execute(final Connection connectionToDataBase) throws SQLQueryCommandExecutionException;
}
