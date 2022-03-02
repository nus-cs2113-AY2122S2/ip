package solana.command;

import solana.Storage;
import solana.task.Todo;

import static solana.task.TaskList.tasks;

/**
 * Represents the Todo Command.
 */
public class TodoCommand extends Command {
    protected String parsedInput;
    protected boolean isFromUser;
    protected boolean isMarked;

    public TodoCommand(String parsedInput, boolean isFromUser, boolean isMarked) {
        this.parsedInput = parsedInput;
        this.isFromUser = isFromUser;
        this.isMarked = isMarked;
    }

    /**
     * Adds a Todo task and saves the updated TaskList into the savedTasks.txt file. This command also serves to
     * load Todo tasks from savedTask.txt at startup, if the file exists.
     */
    @Override
    public void executeCommand() {
        Todo newTodo = new Todo(parsedInput);
        tasks.add(newTodo);

        if (isFromUser) {
            super.printAddedPrompt(newTodo);
            Storage storage = new Storage();
            storage.saveTasks();
        }

        if (isMarked) {
            newTodo.markAsDone();
        }
    }
}
