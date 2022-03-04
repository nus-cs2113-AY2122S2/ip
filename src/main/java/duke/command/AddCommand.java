package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a Command to add a Task to the TaskList.
 * Different types of Task can be added based on the user input.
 */
public class AddCommand extends Command {
    private final String commandWord;
    private final String arguments;

    public AddCommand(String commandWord, String arguments) {
        super();
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        String[] argumentList;
        String description;
        String dateInput;

        switch (this.commandWord) {
        case "deadline":
            argumentList = splitArguments("/by");
            description = argumentList[0].trim(); // eg. return book
            dateInput = argumentList[1].trim(); // yyyy-mm-dd
            newTask = new Deadline(description, dateInput);
            break;
        case "event":
            argumentList = splitArguments("/at");
            description = argumentList[0].trim(); // eg. social event
            dateInput = argumentList[1].trim(); // yyyy-mm-dd
            newTask = new Event(description, dateInput);
            break;
        default:
            // default is todo
            newTask = new Todo(this.arguments);
        }

        tasks.addTask(newTask);
        storage.writeTasksToStorage(tasks);

        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", newTask));
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }

    public String[] splitArguments(String sep) throws DukeException {
        if (this.arguments.split(sep, 2).length < 2) {
            throw new DukeException("OOPS!!! There are missing arguments. Please state the date/time.");
        }
        return this.arguments.split(sep, 2);
    }
}
