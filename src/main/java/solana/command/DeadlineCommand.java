package solana.command;

import solana.Storage;
import solana.task.Deadline;

import static solana.task.TaskList.tasks;

public class DeadlineCommand extends Command {
    public static final int DEADLINE_INDEX = 0;
    public static final int BY_DATE_INDEX = 1;

    public static final int SPLIT_LIMIT = 2;

    protected String parsedInput;
    protected boolean isFromUser;
    protected boolean isMarked;

    public DeadlineCommand(String parsedInput, boolean isFromUser, boolean isMarked) {
        this.parsedInput = parsedInput;
        this.isFromUser = isFromUser;
        this.isMarked = isMarked;
    }

    @Override
    public void executeCommand() {
        try {
            String[] parsedInputAsArray;
            Deadline newDeadline;

            if (isFromUser) {
                parsedInputAsArray = parsedInput.split(" /by ", SPLIT_LIMIT);
                newDeadline = new Deadline(parsedInputAsArray[DEADLINE_INDEX], parsedInputAsArray[BY_DATE_INDEX]);
            } else {
                parsedInputAsArray = parsedInput.split(" \\(By: ", SPLIT_LIMIT);
                newDeadline = new Deadline(parsedInputAsArray[DEADLINE_INDEX],
                        parsedInputAsArray[BY_DATE_INDEX].replace(")", ""));
            }

            tasks.add(newDeadline);

            if (isFromUser) {
                super.printAddedPrompt(newDeadline);
                Storage storage = new Storage();
                storage.saveTasks();
            }

            if (isMarked) {
                newDeadline.markAsDone();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using the keyword \"/by\"!" + System.lineSeparator());
        }
    }
}
