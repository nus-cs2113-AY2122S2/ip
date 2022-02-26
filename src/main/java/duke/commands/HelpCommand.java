package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class HelpCommand extends Command {
    private static final String HELP_MESSAGE =
            "List of commands for Duke: "
            + "\ntodo <description>"
            + "\ndeadline <description> /by <date>"
            + "\nevent <description> /at <date>"
            + "\nmark <task number>"
            + "\nunmark <task number>"
            + "\nlist"
            + "\nfind <description>"
            + "\ndelete <task number>"
            + "\nhelp"
            + "\nbye";

    public HelpCommand() {
        this.commandType = CommandType.HELP;
    }

    /**
     * Show help
     *
     * @param taskList the taskList to act on
     * @param ui the provided Ui to output on
     * @param storage the provided filename to update data to
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showOutput(HELP_MESSAGE);
    }
}
