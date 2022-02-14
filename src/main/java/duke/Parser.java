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
import duke.task.ToDo;

public class Parser {

    public static Command parse(String command) throws DukeException {

        String[] initialParse = command.split(" ");

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
            return new AddCommand(new ToDo(description));
        case "deadline":
            processedString = command.split("/by");
            String by = processedString[1].trim();
            description = processedString[0].split("deadline")[1].trim();
            return new AddCommand(new Deadline(description, by));
        case "event":
            processedString = command.split("/at");
            String at = processedString[1].trim();
            String task = processedString[0].split("event")[1].trim();
            return new AddCommand(new Event(task, at));
        case "mark":
            taskIndex = Integer.parseInt(initialParse[1]) - 1;
            return new MarkCommand(taskIndex, true);
        case "unmark":
            taskIndex = Integer.parseInt(initialParse[1]) - 1;
            return new MarkCommand(taskIndex, false);
        case "delete":
            taskIndex = Integer.parseInt(initialParse[1]) - 1;
            return new DeleteCommand(taskIndex);
        case "find":
            String searchTerm = initialParse[1].trim();
            return new FindCommand(searchTerm);
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException("Invalid command given");
        }
    }
}
