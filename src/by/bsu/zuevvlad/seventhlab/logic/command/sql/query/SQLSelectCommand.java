package by.bsu.zuevvlad.seventhlab.logic.command.sql.query;

import by.bsu.zuevvlad.seventhlab.logic.command.sql.query.exception.SQLQueryCommandExecutionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class SQLSelectCommand extends SQLQueryCommand
{
    public static final String REGULAR_EXPRESSION_OF_COMMAND
            = "SELECT ((\\*)|([a-zA-Z_]+(, ?[a-zA-Z_]+)*)) FROM [a-zA-Z]+.*( WHERE .*)?";

    public SQLSelectCommand(final String namesOfSelectedColumns, final String nameOfTable)
    {
        super(SQLSelectCommand.createDescriptionOfCommand(namesOfSelectedColumns, nameOfTable));
    }

    private static String createDescriptionOfCommand(final String namesOfSelectedColumns, final String nameOfTable)
    {
        return String.format(SQLSelectCommand.TEMPLATE_OF_COMMAND, namesOfSelectedColumns, nameOfTable);
    }

    private static final String TEMPLATE_OF_NAMES_OF_SELECTED_COLUMNS = "%s";
    private static final String TEMPLATE_OF_NAME_OF_TABLE = "%s";
    private static final String TEMPLATE_OF_COMMAND = "SELECT " + SQLSelectCommand.TEMPLATE_OF_NAMES_OF_SELECTED_COLUMNS
            + " FROM " + SQLSelectCommand.TEMPLATE_OF_NAME_OF_TABLE;

    public SQLSelectCommand(final String namesOfSelectedColumns, final String nameOfTable,
                            final String conditionOfSelection)
    {
        super(SQLSelectCommand.createDescriptionOfCommand(namesOfSelectedColumns, nameOfTable, conditionOfSelection));
    }

    private static String createDescriptionOfCommand(final String namesOfSelectedColumns, final String nameOfTable,
                                                     final String conditionOfSelection)
    {
        final String templateOfCommand = SQLSelectCommand.TEMPLATE_OF_COMMAND + " "
                + SQLSelectCommand.TEMPLATE_OF_CONDITION_OF_SELECTION;
        return String.format(templateOfCommand, namesOfSelectedColumns, nameOfTable, conditionOfSelection);
    }

    private static final String TEMPLATE_OF_CONDITION_OF_SELECTION = "WHERE %s";

    @Override
    public final ResultSet execute(final Connection connectionToDataBase)
            throws SQLQueryCommandExecutionException
    {
        Statement statement = null;
        SQLQueryCommandExecutionException mainException = null;
        try
        {
            statement = connectionToDataBase.createStatement();
            final String descriptionOfCommand = this.getDescriptionOfCommand();
            return statement.executeQuery(descriptionOfCommand);
        }
        catch(final SQLException cause)
        {
            mainException = new SQLQueryCommandExecutionException(cause);
            throw mainException;
        }
        finally
        {
            try
            {
                if(statement != null)
                {
                    statement.closeOnCompletion();
                }
            }
            catch(final SQLException sqlException)
            {
                if(mainException != null)
                {
                    mainException.addSuppressed(sqlException);
                }
                else
                {
                    mainException = new SQLQueryCommandExecutionException(sqlException);
                }
            }
        }
    }
}
