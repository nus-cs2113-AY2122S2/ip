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
        System.out.println("\t Greetings Human! I'm " + BOT_NAME + ".");
        System.out.println("\t How may i be of service to you?");
    }

    public void showHorizontalLine() {
        System.out.println("    -----------------------------------------------------------------");
    }

    public String readUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }

    public void showLoadingError(DukeException DE) {
        if (DE.getExceptionCause() == DukeExceptionCause.FOLDERCREATIONFAIL) {
            System.out.println("File was not found and the parent folder of the file is unable to be created.");
        } else if (DE.getExceptionCause() == DukeExceptionCause.FILECREATIONFAIL) {
            System.out.println("File was not found and the file is unable to be created.");
        } else {
            System.out.println("Failure to load data from file.");
        }
        return;
    }

    public void showInvalidCommandMessage() {
        System.out.println("\t OOPS!!! You have entered an invalid command.");
    }

    public void showMissingTaskNameMessage(String typeOfTask) {
        System.out.println("\t OOPS!!! The description of a " + typeOfTask + " cannot be empty.");
    }

    public void showMissingTaskIndexMessage() {
        System.out.println("\t OOPS!!! The index of a task cannot be empty.");
    }

    public void showInvalidTaskIndexMessage() {
        System.out.println("\t  OOPS!!! You have entered an invalid index of a task.");
    }

    public void showTaskIndexOutOfRangeMessage() {
        System.out.println("\t OOPS!!! You have entered a task index that is out of range.");
    }

    public void showParsingError(DukeException de) {
        DukeExceptionCause causeOfException = de.getExceptionCause();
        switch (causeOfException) {
        case INVALIDCOMMAND:
            showInvalidCommandMessage();
            break;
        case TODOTASKNAMEEMPTY:
            showMissingTaskNameMessage("todo");
            break;
        case EVENTTASKNAMEEMPTY:
            showMissingTaskNameMessage("event");
            break;
        case DEADLINETASKNAMEEMPTY:
            showMissingTaskNameMessage("deadline");
            break;
        case EMPTYTASKINDEX:
            showMissingTaskIndexMessage();
            break;
        case TASKINDEXOUTOFRANGE:
            showTaskIndexOutOfRangeMessage();
            break;
        case EMPTYKEYWORD:
            showEmptyKeywordMessage();
            break;
        case INVALIDTASKINDEX:
            showInvalidTaskIndexMessage();
            break;
        default:
            break;
        }
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

    public void printTaskAndIndex(int taskNumber, String taskDescription) {
        System.out.println("\t " + taskNumber + "." + taskDescription);
    }

    public void showFarewellGreeting() {
        System.out.println("    Good bye.See you soon :)");
    }

    public void showInvalidTaskTypeMessage() {
        System.out.println("\t Error Occurred!! Invalid Task Type found in file. Skipping that particular task.");
    }

    public void printList(TaskList listOfTasks, boolean isMatching) {
        if (isMatching) {
            System.out.println("\t Here are the matching tasks in your list:");
        } else {
            System.out.println("\t Here are the tasks in your list:");
        }
        Task taskToPrint;
        for (int i = 0; i < listOfTasks.getListSize(); i++) {
            int taskNumber = i + 1;
            taskToPrint = listOfTasks.getTask(i);
            printTaskAndIndex(taskNumber, taskToPrint.getTaskInformation());
        }
    }
}
