package duke;

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
        switch (this.commandWord) {
        case "todo":
            tasks.addTask(new Todo(this.arguments));
            break;
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", this.arguments));
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
