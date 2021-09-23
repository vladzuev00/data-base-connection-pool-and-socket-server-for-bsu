package by.bsu.zuevvlad.seventhlab.logic.command.sql.update;

public final class SQLDeleteRowCommand extends SQLUpdateCommand
{
    public static final String REGULAR_EXPRESSION_OF_COMMAND = "DELETE FROM [a-zA-Z_]+( WHERE .+)?";

    public SQLDeleteRowCommand(final String nameOfTable)
    {
        super(SQLDeleteRowCommand.createDescriptionOfCommand(nameOfTable));
    }

    private static String createDescriptionOfCommand(final String nameOfTable)
    {
        return String.format(SQLDeleteRowCommand.TEMPLATE_OF_COMMAND, nameOfTable);
    }

    private static final String TEMPLATE_OF_NAME_OF_TABLE = "%s";
    private static final String TEMPLATE_OF_COMMAND = "DELETE FROM " + SQLDeleteRowCommand.TEMPLATE_OF_NAME_OF_TABLE;

    public SQLDeleteRowCommand(final String nameOfTable, final String conditionOfDeleting)
    {
        super(SQLDeleteRowCommand.createDescriptionOfCommand(nameOfTable, conditionOfDeleting));
    }

    private static String createDescriptionOfCommand(final String nameOfTable, final String conditionOfDeleting)
    {
        final String templateOfCommandWithCondition = SQLDeleteRowCommand.TEMPLATE_OF_COMMAND + " "
                + SQLDeleteRowCommand.TEMPLATE_OF_WHERE_CLAUSE;
        return String.format(templateOfCommandWithCondition, nameOfTable, conditionOfDeleting);
    }

    private static final String TEMPLATE_OF_CONDITION_OF_DELETING = "%s";
    private static final String TEMPLATE_OF_WHERE_CLAUSE = "WHERE "
            + SQLDeleteRowCommand.TEMPLATE_OF_CONDITION_OF_DELETING;
}
