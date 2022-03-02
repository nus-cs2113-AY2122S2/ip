package Duke.Parser;

import Duke.Commands.ByeCommand;
import Duke.Commands.Command;
import Duke.Commands.ListItemCommand;
import Duke.DukeException;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class Parser {

    public static Command parse(String message, TaskList taskList) throws DukeException {
        boolean isDone = false;
        if (message.equals("bye")) {
            return new ByeCommand();
        } else if (message.equals("list")) {
            return new ListItemCommand();
        } else {
            if (message.contains("unmark")) {
                return ParserUtil.parseUnmarkCommand(message, taskList);
            } else if (message.contains("mark")) {
                return ParserUtil.parseMarkCommand(message, taskList);
            } else if (message.contains("delete")) {
                return ParserUtil.parseDeleteCommand(message, taskList);
            } else if (message.contains("todo")) {
                return ParserUtil.parseTodoCommand(message,isDone);
            } else if (message.contains("deadline")) {
                return ParserUtil.parseDeadlineCommand(message, isDone);
            } else if (message.contains("event")) {
                return ParserUtil.parseEventCommand(message,isDone);
            } else {
                throw new DukeException(Ui.displayInvalidInputMessage());
            }
        }
    }
}
