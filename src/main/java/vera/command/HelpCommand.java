package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;

import static vera.constant.Messages.HELP_MESSAGE_QUICK_START_COMMAND;
import static vera.constant.Messages.HELP_MESSAGE_SPECIFIC_COMMAND;

public class HelpCommand extends Command {
    private String helpInput;
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_WORD_VARIANT = "quick start";

    public HelpCommand(String filteredHelpInput) {
        helpInput = filteredHelpInput.trim().toLowerCase();
    }

    public void showHelpList(Ui ui) {
        System.out.println("Here is a list of commands available:");
        String[] helpCommands = {ListCommand.COMMAND_WORD, MarkCommand.COMMAND_WORD
                , UnmarkCommand.COMMAND_WORD, TodoCommand.COMMAND_WORD, DeadlineCommand.COMMAND_WORD
                , EventCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD, FindCommand.COMMAND_WORD
                , ClearCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD,};
        for (String helpCommand : helpCommands) {
            ui.showLine();
            showSpecificHelpCommand(ui, helpCommand);
        }
        ui.showLine();
        System.out.println("For a quick summary of what commands to execute, \n"
                + "enter 'help quick start'.");
    }

    public void showSpecificHelpCommand(Ui ui, String helpCommand) {
        switch (helpCommand) {
        case ListCommand.COMMAND_WORD:
            ui.showToUser(ListCommand.MESSAGE_USAGE);
            break;
        case MarkCommand.COMMAND_WORD:
            ui.showToUser(MarkCommand.MESSAGE_USAGE);
            break;
        case UnmarkCommand.COMMAND_WORD:
            ui.showToUser(UnmarkCommand.MESSAGE_USAGE);
            break;
        case TodoCommand.COMMAND_WORD:
            ui.showToUser(TodoCommand.MESSAGE_USAGE);
            break;
        case DeadlineCommand.COMMAND_WORD:
            ui.showToUser(DeadlineCommand.MESSAGE_USAGE);
            break;
        case EventCommand.COMMAND_WORD:
            ui.showToUser(EventCommand.MESSAGE_USAGE);
            break;
        case DeleteCommand.COMMAND_WORD:
            ui.showToUser(DeleteCommand.MESSAGE_USAGE);
            break;
        case FindCommand.COMMAND_WORD:
            ui.showToUser(FindCommand.MESSAGE_USAGE);
            break;
        case ClearCommand.COMMAND_WORD:
            ui.showToUser(ClearCommand.MESSAGE_USAGE);
            break;
        case ExitCommand.COMMAND_WORD:
            ui.showToUser(ExitCommand.MESSAGE_USAGE);
            break;
        case COMMAND_WORD_VARIANT:
            ui.showToUser(HELP_MESSAGE_QUICK_START_COMMAND + HELP_MESSAGE_SPECIFIC_COMMAND);
            break;
        default:
            showHelpList(ui);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        showSpecificHelpCommand(ui, helpInput);
    }
}


