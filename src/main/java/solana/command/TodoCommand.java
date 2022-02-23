package solana.command;

import solana.Storage;
import solana.task.Todo;

import static solana.task.TaskList.tasks;

public class TodoCommand extends Command {
    protected String parsedInput;
    protected boolean isFromUser;
    protected boolean isMarked;

    public TodoCommand(String parsedInput, boolean isFromUser, boolean isMarked) {
        this.parsedInput = parsedInput;
        this.isFromUser = isFromUser;
        this.isMarked = isMarked;
    }

    @Override
    public void executeCommand() {
        Todo newTodo = new Todo(parsedInput);
        tasks.add(newTodo);

        if (isFromUser) {
            Storage storage = new Storage();
            storage.saveTasks();
            super.printAddedPrompt(newTodo);
        }

        if (isMarked) {
            newTodo.markAsDone();
        }
    }
}
