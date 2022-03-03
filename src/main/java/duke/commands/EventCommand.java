package duke.commands;

import duke.Parser;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import java.time.format.DateTimeParseException;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.Ui;
import duke.Storage;

import java.time.LocalDateTime;

import java.util.HashMap;

public class EventCommand extends Command {
    public static final String TASK_ADDED_MESSAGE_FORMAT = "added: %s";
    private static final String COMMAND_NAME = "deadline";
    private static final String EMPTY_ARGUMENTS = "Event must have a description!";
    private static final String EMPTY_ATDATE = "Event must have a date for /at!";
    private static final String INCORRECT_ATDATE = "The date and time entered for /at must be a valid date time!";

    private HashMap<String, String> arguments;
    /**
     * Initialises the arguments input by the user for this class.
     * @param parsedArguments arguments from Parser
     */
    public EventCommand(HashMap<String, String> parsedArguments) {
        this.arguments = parsedArguments;
    }

    /**
     * Asserts user arguments are legal for Deadline by checking whether description and byDate exists. (Key "", "/by")
     *
     * @throws InvalidArgumentException when argument entered is not an integer
     */
    @Override
    protected void assertArguments() throws InvalidArgumentException {
        String errorMsg = "";
        if (arguments.get("")==null || arguments.get("").equals("")) {
            errorMsg += EMPTY_ARGUMENTS+"\n";
        }
        if (arguments.get("/at")==null || arguments.get("").equals("")) {
            errorMsg += EMPTY_ATDATE +"\n";
        }
        if (!errorMsg.equals("")) {
            throw new InvalidArgumentException(COMMAND_NAME,errorMsg.trim());
        }
    }

    /**
     * Creates an Event task and adds it to taskList
     * @param taskList the taskList to act on
     * @param ui the provided Ui to output on
     * @param storage the provided filename to update data to
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assertArguments();
        String description = arguments.get("");
        String atDateTime = arguments.get("/at");
        try {
            LocalDateTime[] atDateTimeParsed = Parser.parseAtDateTime(atDateTime);
            LocalDateTime atDateTimeStartParsed = atDateTimeParsed[0];
            LocalDateTime atDateTimeEndParsed = atDateTimeParsed[1];
            if (atDateTimeStartParsed.isAfter(atDateTimeEndParsed)) {
                InvalidArgumentException exception = new InvalidArgumentException(COMMAND_NAME, INCORRECT_ATDATE);
                throw exception;
            }
            Event eventTask = new Event(description, atDateTimeStartParsed, atDateTimeEndParsed);
            taskList.add(eventTask);
            String output = String.format(TASK_ADDED_MESSAGE_FORMAT, eventTask.toString());
            ui.showOutput(output);
            storage.write(taskList);
        } catch (IllegalStateException e) {
            // Date given did not match regex format
            InvalidArgumentException exception = new InvalidArgumentException(COMMAND_NAME, INCORRECT_ATDATE);
            throw exception;
        } catch (DateTimeParseException e) {
            // Values given cannot be parsed into date (e.g. value >12 for month)
            InvalidArgumentException exception = new InvalidArgumentException(COMMAND_NAME, INCORRECT_ATDATE);
            throw exception;
        }
    }
}
