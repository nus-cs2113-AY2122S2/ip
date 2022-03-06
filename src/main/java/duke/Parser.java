package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {

    public static Command parse(String command) throws DukeException {

        String[] initialParse = command.split(" ");
        int taskIndexString = 1;
        String commandType = initialParse[0];
        String description;
        String processedString[];
        int taskIndex;

        switch (commandType) {
            case "list":
                return new ListCommand();
            case "todo":
                if (initialParse.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                description = command.split("todo")[1].trim();
                return new AddCommand(new Todo(description));
            case "deadline":
                processedString = command.split("/by");
                return new AddCommand(processDeadline(processedString));
            case "event":
                processedString = command.split("/at");
                return new AddCommand(processEvent(processedString));
            case "mark":
                taskIndex = Integer.parseInt(initialParse[taskIndexString]) - 1;
                return new MarkCommand(taskIndex, true);
            case "unmark":
                taskIndex = Integer.parseInt(initialParse[taskIndexString]) - 1;
                return new MarkCommand(taskIndex, false);
            case "delete":
                taskIndex = Integer.parseInt(initialParse[taskIndexString]) - 1;
                return new DeleteCommand(taskIndex);
            case "find":
                String searchTerm = initialParse[taskIndexString].trim();
                return new FindCommand(searchTerm);
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("Invalid command given");
        }
    }

    private static String processTime(String[] processedString, String separator) {
        final int TIMEINDEX = 1;
        String time = processedString[TIMEINDEX].trim();
        return time;
    }

    private static String processDescription(String[] processedString, String separator) {
        final int PRETIMEINDEX = 0;
        final int DESCRIPTIONINDEX = 1;
        String description = processedString[PRETIMEINDEX].split(separator)[DESCRIPTIONINDEX].trim();
        return description;
    }

    private static Event processEvent(String[] processedString) {
        String at = processTime(processedString, "/at");
        String task = processDescription(processedString, "event");
        return new Event(task, at);
    }

    private static Deadline processDeadline(String[] processedString) {
        String by = processTime(processedString, "/by");
        String task = processDescription(processedString, "deadline");
        return new Deadline(task, by);
    }
}