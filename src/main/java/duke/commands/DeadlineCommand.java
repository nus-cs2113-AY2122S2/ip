package duke.commands;

import duke.Parser;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import java.time.format.DateTimeParseException;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.Ui;
import duke.Storage;

import java.time.LocalDateTime;

import java.util.HashMap;

public class DeadlineCommand extends Command {
    public static final String TASK_ADDED_MESSAGE_FORMAT = "added: %s";
    private static final String COMMAND_NAME = "deadline";
    private static final String EMPTY_ARGUMENTS = "Deadline must have a description!";
    private static final String EMPTY_BYDATE = "Deadline must have a date for /by!";
    private static final String INCORRECT_BYDATE = "The deadline entered for /by must be a valid date time!";

    private HashMap<String, String> arguments;
    /**
     * Initialises the arguments input by the user for this class.
     * @param parsedArguments arguments from Parser
     */
    public DeadlineCommand(HashMap<String, String> parsedArguments) {
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
        String description = arguments.get("");
        String byDate = arguments.get("/by");
        if (description==null || description.equals("")) {
            errorMsg += EMPTY_ARGUMENTS+"\n";
        }
        if (byDate==null || byDate.equals("")) {
            errorMsg += EMPTY_BYDATE+"\n";
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
        String byDateTimeString = arguments.get("/by");
        try {
            LocalDateTime byDateTimeParsed = Parser.parseByDateTime(byDateTimeString);
            Deadline deadlineTask = new Deadline(description, byDateTimeParsed);
            taskList.add(deadlineTask);
            String output = String.format(TASK_ADDED_MESSAGE_FORMAT, deadlineTask.toString());
            ui.showOutput(output);
            storage.write(taskList);
        } catch (DateTimeParseException e) {
            InvalidArgumentException exception = new InvalidArgumentException(COMMAND_NAME, INCORRECT_BYDATE);
            throw exception;
        }
    }
}