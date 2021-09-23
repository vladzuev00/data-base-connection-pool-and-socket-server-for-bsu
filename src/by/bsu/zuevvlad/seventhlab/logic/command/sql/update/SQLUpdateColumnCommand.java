package by.bsu.zuevvlad.seventhlab.logic.command.sql.update;

public final class SQLUpdateColumnCommand extends SQLUpdateCommand
{
    public static final String REGULAR_EXPRESSION_OF_COMMAND = "UPDATE [a-zA-Z_]+ SET [a-zA-Z_]+ = .+( WHERE .*)?";

    public SQLUpdateColumnCommand(final String nameOfTable, final String nameOfUpdatedColumn,
                                  final String newValueOfColumn)
    {
        super(SQLUpdateColumnCommand.createDescriptionOfCommand(nameOfTable, nameOfUpdatedColumn, newValueOfColumn));
    }

    private static String createDescriptionOfCommand(final String nameOfTable, final String nameOfUpdatedColumn,
                                                     final String newValueOfColumn)
    {
        return String.format(SQLUpdateColumnCommand.TEMPLATE_OF_COMMAND, nameOfTable, nameOfUpdatedColumn,
                newValueOfColumn);
    }

    private static final String TEMPLATE_OF_NAME_OF_TABLE = "%s";
    private static final String TEMPLATE_OF_NAME_OF_UPDATED_COLUMN = "%s";
    private static final String TEMPLATE_OF_NEW_VALUE_OF_COLUMN = "%s";
    private static final String TEMPLATE_OF_COMMAND = "UPDATE " + SQLUpdateColumnCommand.TEMPLATE_OF_NAME_OF_TABLE
            + " SET " + SQLUpdateColumnCommand.TEMPLATE_OF_NAME_OF_UPDATED_COLUMN + " = "
            + SQLUpdateColumnCommand.TEMPLATE_OF_NEW_VALUE_OF_COLUMN;

    public SQLUpdateColumnCommand(final String nameOfTable, final String nameOfUpdatedColumn,
                                  final String newValueOfColumn, final String conditionOfUpdating)
    {
        super(SQLUpdateColumnCommand.createDescriptionOfCommand(nameOfTable, nameOfUpdatedColumn,
                newValueOfColumn, conditionOfUpdating));
    }

    private static String createDescriptionOfCommand(final String nameOfTable, final String nameOfUpdatedColumn,
                                                     final String newValueOfColumn, final String conditionOfUpdating)
    {
        final String templateOfCommandWithCondition = SQLUpdateColumnCommand.TEMPLATE_OF_COMMAND + " "
                + SQLUpdateColumnCommand.TEMPLATE_OF_WHERE_CLAUSE;
        return String.format(templateOfCommandWithCondition, nameOfTable, nameOfUpdatedColumn,
                newValueOfColumn, conditionOfUpdating);
    }

    private static final String TEMPLATE_OF_CONDITION_OF_UPDATING = "%s";
    private static final String TEMPLATE_OF_WHERE_CLAUSE = "WHERE "
            + SQLUpdateColumnCommand.TEMPLATE_OF_CONDITION_OF_UPDATING;
}
