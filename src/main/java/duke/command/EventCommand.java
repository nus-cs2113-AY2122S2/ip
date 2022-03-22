package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";
    public static final String EXCEPTION_WRONG_INPUT = "Incorrect input format. Please enter " +
            "in this format: event opening ceremony /at 2022-12-31";
    public static final String KEYWORD_SPLIT = "/at";
    private final Event event;

    public EventCommand(String userInput) throws DukeException {
        super();
        LocalDate date = extractDate(userInput);
        String description = extractDescription(userInput);
        this.event = new Event(description, date);
    }

    public static LocalDate extractDate(String userInput) throws DukeException {
        try {
            String[] argumentList = userInput.split(KEYWORD_SPLIT, 2);
            LocalDate dateInput = LocalDate.parse(argumentList[1].trim()); // yyyy-mm-dd
            return dateInput;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException(EXCEPTION_WRONG_INPUT);
        }
    }

    public static String extractDescription(String userInput) throws DukeException {
        try {
            String[] argumentList = userInput.split(KEYWORD_SPLIT, 2);
            String description = argumentList[0].trim(); // eg. return book
            return description;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(EXCEPTION_WRONG_INPUT);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.event);
        storage.writeTasksToStorage(tasks);

        ui.showToUser(
                MESSAGE_SUCCESS,
                String.format("  %s", this.event),
                tasks.getNumRemainingTasksUi());
    }
}
