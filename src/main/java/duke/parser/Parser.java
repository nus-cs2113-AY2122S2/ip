package duke.parser;

import duke.TaskList;
import duke.ui.Ui;
import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;

public class Parser {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_BYE = "bye";

    public static void parseInput(String input) throws MissingDescriptionException, MissingDateException {

        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command.toLowerCase()) {
        case COMMAND_LIST:
            TaskList.printTasks();
            break;
        case COMMAND_MARK:
            TaskList.markTask(extractTaskNumber(words));
            break;
        case COMMAND_UNMARK:
            TaskList.unmarkTask(extractTaskNumber(words));
            break;
        case COMMAND_TODO:
            TaskList.addToDo(extractDescription(words));
            break;
        case COMMAND_DEADLINE:
            TaskList.addDeadline(extractDescription(words), extractDeadlineDate(words));
            break;
        case COMMAND_EVENT:
            TaskList.addEvent(extractDescription(words), extractEventDate(words));
            break;
        case COMMAND_DELETE:
            TaskList.deleteTask(extractTaskNumber(words));
            break;
        case COMMAND_FIND:
            TaskList.findTasksByString(extractKeyword(words));
            break;
        case COMMAND_HELP:
            Ui.printHelp();
            break;
        default:
            if (!command.equals(COMMAND_BYE)) {
                Ui.printDefaultErrorMessage();
            }
            break;
        }
    }

    public static int extractTaskNumber(String[] words) throws MissingDescriptionException {
        int taskNumber = -1;
        try {
            if (words.length < 2) {
                throw new MissingDescriptionException();
            }
            taskNumber = Integer.parseInt(words[1]);
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a number after unmark");
        }
        return taskNumber;
    }

    public static String extractKeyword(String[] words) throws MissingDescriptionException {
        if (words.length < 2) {
            throw new MissingDescriptionException();
        }
        return words[1];
    }

    public static String extractDescription(String[] words) throws MissingDescriptionException {
        if (words.length < 2) {
            throw new MissingDescriptionException();
        }
        String[] parameters = words[1].split(" /", 2);
        if (parameters[0].isBlank()) {
            throw new MissingDescriptionException();
        }
        return parameters[0];
    }

    public static String extractDeadlineDate(String[] words) throws MissingDateException {
        String[] parameters = words[1].split(" /by ", 2);
        if (parameters.length < 2 || parameters[1].isBlank()) {
            throw new MissingDateException();
        }
        return parameters[1];
    }

    public static String extractEventDate(String[] words) throws MissingDateException {
        String[] parameters = words[1].split(" /at ", 2);
        if (parameters.length < 2 || parameters[1].isBlank()) {
            throw new MissingDateException();
        }
        return parameters[1];
    }
}
