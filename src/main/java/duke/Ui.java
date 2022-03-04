package duke;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the User Interface component of the program.
 * The Ui object handles all interactions with the user such as printing the outputs to the user
 * as well as reading in the user inputs .
 */
public class Ui {
    private Scanner in;
    private final String BOT_NAME = "Big Bob";

    public Ui() {
        in = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("\t  Greetings Human! I'm " + BOT_NAME + ".");
        System.out.println("\t  How may i be of service to you?");
    }

    public void showHorizontalLine() {
        System.out.println("  ------------------------------------------------------------------------------");
    }

    public String readUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }

    public void showLoadingError(DukeException de) {
        if (de.getExceptionCause() == DukeExceptionCause.FolderCreationFail) {
            System.out.println("\t  Error Occurred!! File was not found and the parent folder cannot be created.");
        } else {
            System.out.println("\t  Error Occurred!! File was not found and the file cannot be created.");
        }
    }

    public void showInvalidCommandMessage() {
        System.out.println("\t  Error Occurred!! You have entered an invalid command.");
    }

    public void showMissingTaskNameMessage(String typeOfTask) {
        System.out.println("\t  Error Occurred!! The description of a " + typeOfTask + " cannot be empty.");
    }

    public void showMissingTaskIndexMessage() {
        System.out.println("\t  Error Occurred!! The index of a task cannot be empty.");
    }

    public void showInvalidTaskIndexMessage() {
        System.out.println("\t  Error Occurred!! You have entered an invalid index of a task.");
    }

    public void showTaskIndexOutOfRangeMessage() {
        System.out.println("\t  Error Occurred!! You have entered a task index that is out of range.");
    }

    public void showParsingError(DukeException de) {
        DukeExceptionCause causeOfException = de.getExceptionCause();
        switch (causeOfException) {
        case InvalidCommand:
            showInvalidCommandMessage();
            break;
        case ToDoTaskNameEmpty:
            showMissingTaskNameMessage("todo");
            break;
        case EventTaskNameEmpty:
            showMissingTaskNameMessage("event");
            break;
        case DeadlineTaskNameEmpty:
            showMissingTaskNameMessage("deadline");
            break;
        case EmptyTaskIndex:
            showMissingTaskIndexMessage();
            break;
        case TaskIndexOutOfRange:
            showTaskIndexOutOfRangeMessage();
            break;
        case EmptyKeyword:
            showEmptyKeywordMessage();
            break;
        case InvalidTaskIndex:
            showInvalidTaskIndexMessage();
            break;
        default:
            showUnknownErrorMessage();
            break;
        }
    }

    public void showUnknownErrorMessage() {
        System.out.println("\t An Error Has Occurred!!");
    }

    public void showEmptyKeywordMessage() {
        System.out.println("\t Error Occurred!! The find command cannot have an empty keyword.");
    }

    public void showAcknowledgementMessage(String message) {
        System.out.println(message);
    }

    public void showFileWritingError() {
        System.out.println("\t Error Occurred!! Unable to write to file.");
    }

    public void showIndexOutOfBoundError() {
        System.out.println("\t Error Occurred!! Index provided is invalid. Kindly provide a valid one.");
    }

    public void showFarewellGreeting() {
        System.out.println("\t Good bye.See you soon :)");
    }

    public void showInvalidTaskTypeMessage() {
        System.out.println("\t Error Occurred!! Invalid Task Type found in file. Skipping that particular task.");
    }

    public void printList(TaskList listOfTasks, boolean isFindCommand) {
        Task taskToPrint;
        int taskNumber;
        final int ARRAY_LIST_OFFSET = 1;
        if (listOfTasks.getListSize() == 0 && isFindCommand == true) {
            System.out.println("\t There are no matching tasks found within the list.");
            return;
        }
        if (listOfTasks.getListSize() == 0 && isFindCommand == false) {
            System.out.println("\t There are no tasks within the list.");
            return;
        }
        if (isFindCommand == true) {
            System.out.println("\t Here are the matching tasks in your list:");
        } else {
            System.out.println("\t Here are the tasks in your list:");
        }
        for (int i = 0; i < listOfTasks.getListSize(); i++) {
            taskNumber = i + ARRAY_LIST_OFFSET;
            taskToPrint = listOfTasks.getTask(i);
            System.out.println("\t " + taskNumber + "." + taskToPrint.getTaskInformation());
        }
    }
}
