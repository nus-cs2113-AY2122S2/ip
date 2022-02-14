package duke;

import duke.Command.*;
import duke.task.Deadline;
import duke.task.ToDo;

/**
 * The parsing object used to parse user input into Commands
 */

public class Parser {
    /**
     * Parses user input into Duke command
     *
     * @param command
     *            String representation of  user input
     * @return Command object
     * @throws DukeException
     *             If task description is empty or invalid commands
     */

    public static Command parse(String command) throws DukeException {
        String[] initialParse = Command.split(" ");
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
                description = Command.split("todo")[1].trim();
                return new AddCommand(new ToDo(description));
            case "deadline":
                processedString = Command.split("/by");
                String by = processedString[1].trim();
                description = processedString[0].split("deadline")[1].trim();
                return new AddCommand(new Deadline(description, by));
            case "event":
                processedString = Command.split("/at");
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
