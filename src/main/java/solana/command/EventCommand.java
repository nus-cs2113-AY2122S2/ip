package solana.command;

import solana.Storage;
import solana.task.Event;

import static solana.task.TaskList.tasks;

public class EventCommand extends Command {
    public static final int EVENT_INDEX = 0;
    public static final int AT_DATE_INDEX = 1;

    public static final int SPLIT_LIMIT = 2;

    protected String parsedInput;
    protected boolean isFromUser;
    protected boolean isMarked;

    public EventCommand(String parsedInput, boolean isFromUser, boolean isMarked) {
        this.parsedInput = parsedInput;
        this.isFromUser = isFromUser;
        this.isMarked = isMarked;
    }

    @Override
    public void executeCommand() {
        try {
            String[] parsedInputAsArray;
            Event newEvent;

            if (isFromUser) {
                parsedInputAsArray = parsedInput.split(" /at ", SPLIT_LIMIT);
                newEvent = new Event(parsedInputAsArray[EVENT_INDEX], parsedInputAsArray[AT_DATE_INDEX]);
            } else {
                parsedInputAsArray = parsedInput.split(" \\(At: ", SPLIT_LIMIT);
                newEvent = new Event(parsedInputAsArray[EVENT_INDEX],
                        parsedInputAsArray[AT_DATE_INDEX].replace(")", ""));
            }

            tasks.add(newEvent);

            if (isFromUser) {
                super.printAddedPrompt(newEvent);
                Storage storage = new Storage();
                storage.saveTasks();
            }

            if (isMarked) {
                newEvent.markAsDone();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Include the date or time using keyword \"/at\"!" + System.lineSeparator());
        }
    }
}
