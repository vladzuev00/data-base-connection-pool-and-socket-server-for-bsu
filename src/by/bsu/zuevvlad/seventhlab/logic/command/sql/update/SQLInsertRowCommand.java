package by.bsu.zuevvlad.seventhlab.logic.command.sql.update;

public final class SQLInsertRowCommand extends SQLUpdateCommand
{
    public static final String REGULAR_EXPRESSION_OF_COMMAND
            = "INSERT INTO [a-zA-Z_]+( \\(([a-zA-Z_]+(, [a-zA-Z_]+)?)\\))? VALUES \\(.*\\)";

    public SQLInsertRowCommand(final String nameOfTable, final String namesOfColumns, final String valuesOfColumns)
    {
        super(SQLInsertRowCommand.createDescriptionOfCommand(nameOfTable, namesOfColumns, valuesOfColumns));
    }

    private static String createDescriptionOfCommand(final String nameOfTable, final String namesOfColumns,
                                                     final String valuesOfColumns)
    {
        return String.format(SQLInsertRowCommand.TEMPLATE_OF_COMMAND, nameOfTable, namesOfColumns, valuesOfColumns);
    }

    private static final String TEMPLATE_OF_NAME_OF_TABLE = "%s";
    private static final String TEMPLATE_OF_NAMES_OF_COLUMNS = "%s";
    private static final String TEMPLATE_OF_VALUES_OF_COLUMNS = "%s";
    private static final String TEMPLATE_OF_COMMAND = "INSERT INTO " + SQLInsertRowCommand.TEMPLATE_OF_NAME_OF_TABLE
            + " (" + SQLInsertRowCommand.TEMPLATE_OF_NAMES_OF_COLUMNS + ") VALUES ("
            + SQLInsertRowCommand.TEMPLATE_OF_VALUES_OF_COLUMNS + ")";
}
