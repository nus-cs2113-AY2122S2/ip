package duke;

import duke.task.Deadline;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask;

        switch (this.commandWord) {
        case "deadline":
            String[] userArguments = this.arguments.split(" /by ", 2);
            String description = userArguments[0]; // eg. return book
            String by = userArguments[1]; // eg. Sunday
            newTask = new Deadline(description, by);
            break;
        default:
            newTask = new Todo(this.arguments);
        }

        tasks.addTask(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", newTask));
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
