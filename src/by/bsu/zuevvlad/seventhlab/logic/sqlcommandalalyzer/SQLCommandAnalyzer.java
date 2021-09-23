package by.bsu.zuevvlad.seventhlab.logic.sqlcommandalalyzer;

import by.bsu.zuevvlad.seventhlab.logic.command.sql.query.SQLSelectCommand;
import by.bsu.zuevvlad.seventhlab.logic.command.sql.update.SQLDeleteRowCommand;
import by.bsu.zuevvlad.seventhlab.logic.command.sql.update.SQLInsertRowCommand;
import by.bsu.zuevvlad.seventhlab.logic.command.sql.update.SQLUpdateColumnCommand;

import java.util.ArrayList;
import java.util.List;

public final class SQLCommandAnalyzer
{
    public SQLCommandAnalyzer()
    {
        super();
    }

    public final boolean isQueryCommand(final String researchDescriptionOfCommand)
    {
        return researchDescriptionOfCommand.matches(SQLCommandAnalyzer.REGEX_OF_QUERY_COMMAND);
    }

    private static final String REGEX_OF_QUERY_COMMAND = SQLSelectCommand.REGULAR_EXPRESSION_OF_COMMAND;

    public final boolean isUpdateCommand(final String researchDescriptionOfCommand)
    {
        for(final String currentRegex : SQLCommandAnalyzer.REGEXES_OF_UPDATE_COMMANDS)
        {
            if(researchDescriptionOfCommand.matches(currentRegex))
            {
                return true;
            }
        }
        return false;
    }

    private static final List<String> REGEXES_OF_UPDATE_COMMANDS = new ArrayList<String>()
    {
        {
            this.add(SQLUpdateColumnCommand.REGULAR_EXPRESSION_OF_COMMAND);
            this.add(SQLInsertRowCommand.REGULAR_EXPRESSION_OF_COMMAND);
            this.add(SQLDeleteRowCommand.REGULAR_EXPRESSION_OF_COMMAND);
        }
    };
}
