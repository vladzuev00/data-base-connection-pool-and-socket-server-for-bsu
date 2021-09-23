package by.bsu.zuevvlad.seventhlab.logic.command;

public abstract class Command
{
    private final String descriptionOfCommand;

    public Command(final String descriptionOfCommand)
    {
        super();
        this.descriptionOfCommand = descriptionOfCommand;
    }

    public final String getDescriptionOfCommand()
    {
        return this.descriptionOfCommand;
    }
}
