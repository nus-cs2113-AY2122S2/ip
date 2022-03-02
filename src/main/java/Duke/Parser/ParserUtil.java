package Duke.Parser;

import Duke.Commands.*;
import Duke.DukeException;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class ParserUtil {
    public static Command parseMarkCommand(String message, TaskList taskList) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(Ui.DISPLAY_LINE + System.lineSeparator() + "Please add something to the list first:)"
                    + System.lineSeparator() + Ui.DISPLAY_LINE);
        } else {
            String[] splitMessage = message.split(" ");
            if (splitMessage.length != 2) {
                throw new DukeException(Ui.DISPLAY_LINE + System.lineSeparator() + "Please input a number\n" +
                        "For eg. 'mark 2'\n" + Ui.DISPLAY_LINE);
            }
            String getNumber = splitMessage[1];
            int positionToMark = Integer.parseInt(getNumber) - 1;
            if (positionToMark + 1 == 0 | positionToMark + 1 > taskList.size()) {
                throw new DukeException(Ui.displayMarkMessage(taskList));
            } else {
                return new MarkCommand(positionToMark);
            }
        }

    }

    public static Command parseUnmarkCommand(String message, TaskList taskList) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(Ui.DISPLAY_LINE + System.lineSeparator() + "Please add something to the list first:)"
                    + System.lineSeparator() + Ui.DISPLAY_LINE);
        } else {
            String[] splitMessage = message.split(" ");
            if (splitMessage.length != 2) {
                throw new DukeException(Ui.DISPLAY_LINE + System.lineSeparator() + "Please input a number\n" +
                        "For eg. 'unmark 2'\n" + Ui.DISPLAY_LINE);
            }
            String getNumber = splitMessage[1];
            int positionToUnMark = Integer.parseInt(getNumber) - 1;
            if (positionToUnMark + 1 == 0 | positionToUnMark + 1 > taskList.size()) {
                throw new DukeException(Ui.displayUnmarkMessage(taskList));
            } else {
                return new UnmarkCommand(positionToUnMark);
            }
        }
    }

    public static Command parseFindCommand (String message, TaskList taskList) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(Ui.DISPLAY_LINE + System.lineSeparator() + "Please add something to the list first:)"
                    + System.lineSeparator() + Ui.DISPLAY_LINE);
        } else {
            String[] splitMessage = message.split(" ");
            if (splitMessage.length != 2) {
                throw new DukeException(Ui.DISPLAY_LINE + System.lineSeparator() + "Please input a description\n" +
                        "For eg. 'find book'\n" + Ui.DISPLAY_LINE);
            }
            String getDescription = splitMessage[1];
            return new FindCommand(getDescription);
        }
    }

    public static Command parseDeleteCommand (String message, TaskList taskList) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(Ui.DISPLAY_LINE + System.lineSeparator() + "Please add something to the list first:)"
                    + System.lineSeparator() + Ui.DISPLAY_LINE);
        }
        String[] splitMessage = message.split(" ");
        if (splitMessage.length != 2) {
            throw new DukeException(Ui.DISPLAY_LINE + System.lineSeparator() + "Please input a number\n" +
                    "For eg. 'delete 2'\n" + Ui.DISPLAY_LINE);
        }
        String getNumber = splitMessage[1];
        int positionToDelete = Integer.parseInt(getNumber) - 1;
        if (positionToDelete + 1 == 0 | positionToDelete + 1 > taskList.size()) {
            throw new DukeException(Ui.displayDeleteMessage(taskList));
        } else {
            return new DeleteCommand(positionToDelete);
        }
    }

    public static Command parseTodoCommand (String message, boolean isDone) throws DukeException {
        String[] splitMessage = message.split(" ", 2);
        if (splitMessage.length != 2) {
            throw new DukeException(Ui.displayTodoMessage());
        }
        String getDescription = splitMessage[1];
        if (getDescription.equals("")) {
            throw new DukeException(Ui.displayTodoMessage());
        } else {
            return new ToDoCommand(getDescription, isDone);
        }
    }

    public static String[] splitLongMessage (String message, String regex, String type) throws DukeException {
        String[] splitMessage = message.split(" ", 2);
        if (splitMessage.length != 2) {
            if (type.equals("deadline")) {
                throw new DukeException(Ui.displayDeadlineMessage());
            } else {
                throw new DukeException(Ui.displayEventMessage());
            }
        }
        String getSecondPart = splitMessage[1];
        return getSecondPart.split(regex, 2);
    }

    public static Command parseDeadlineCommand (String message, boolean isDone) throws DukeException {
        String[] splitSecondPart = splitLongMessage(message, " /by ", "deadline");
        if (splitSecondPart.length != 2) {
            throw new DukeException(Ui.displayDeadlineMessage());
        }
        String getDescription = splitSecondPart[0];
        String getDate = splitSecondPart[1];
        if (getDescription.equals("") | getDate.equals("")) {
            throw new DukeException(Ui.displayDeadlineMessage());
        } else {
            return new DeadlineCommand(getDescription, isDone, getDate);
        }
    }

    public static Command parseEventCommand (String message, boolean isDone) throws DukeException {
        String[] splitSecondPart = splitLongMessage(message, " /at ","event");
        if (splitSecondPart.length != 2) {
            throw new DukeException(Ui.displayEventMessage());
        }
        String getDescription = splitSecondPart[0];
        String getDate = splitSecondPart[1];
        if (getDescription.equals("") | getDate.equals("")) {
            throw new DukeException(Ui.displayEventMessage());
        } else {
            return new EventCommand(getDescription, isDone,getDate);
        }
    }

}
