package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class addCommand extends Command {
    private final String commandWord;
    private final String arguments;

    public addCommand(String commandWord, String arguments) {
        super();
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        String[] argumentList;
        String description;

        switch (this.commandWord) {
        case "deadline":
            argumentList = splitArguments();
            description = argumentList[0]; // eg. return book
            String by = argumentList[1]; // eg. Sunday
            newTask = new Deadline(description, by);
            break;
        case "event":
            argumentList = splitArguments();
            description = argumentList[0]; // eg. return book
            String eventTime = argumentList[1]; // eg. Sunday
            newTask = new Event(description, eventTime);
            break;
        default:
            // default is todo
            newTask = new Todo(this.arguments);
        }

        tasks.addTask(newTask);
        storage.writeTasksToStorage(tasks);

        // Print the newTask
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", newTask));
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }

    public String[] splitArguments() throws DukeException {
        if (this.arguments.split(" /by ", 2).length < 2) {
            throw new DukeException("OOPS!!! There are missing arguments. Please state the date/time.");
        }
        return this.arguments.split(" /by ", 2);
    }
}
