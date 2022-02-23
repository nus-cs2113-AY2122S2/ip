package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {

    //Flags
    public static final String FLAG_DEADLINE = " /by ";
    public static final String FLAG_EVENT = " /at ";

    //Returns a String array with the first element as the description and the second element as the deadline/time period
    public static String[] parseDeadlineOrEvent(String input) {
        String command = getCommand(input);
        String[] inputArray;
        if (command.equals(Commands.COMMAND_DEADLINE)) {
            inputArray = input.split(FLAG_DEADLINE);
        } else {
            inputArray = input.split(FLAG_EVENT);
        }
        inputArray[0] = inputArray[0].substring(inputArray[0].indexOf(" "));
        return inputArray;
    }

    //Returns a String representing the integer of the chosen task
    public static String parseMarkOrUnmarkOrDelete(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        if (inputArray.length != 2) {
            throw new DukeException(Ui.ERROR_INVALID_SYNTAX);
        }
        return inputArray[1];
    }

    public static String getCommand(String input) {
        String[] arrayOfInput = input.split(" ", 0);
        return arrayOfInput[0];
    }

    public static String getDescription(String input) {
        return input.substring(input.indexOf(" "));
    }

    public static void listTasks() {
        if (Ui.taskList.size() == 0) {
            System.out.println(Ui.MESSAGE_NO_TASKS);
        } else {
            for (int i = 0; i < Ui.taskList.size(); i++) {
                System.out.println((i + 1) + ". " + Ui.taskList.get(i));
            }
        }
    }

    public static String getFormattedString(Task task) {
        if (task instanceof Todo) {
            return (Storage.FILE_TODO + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription());
        } else if (task instanceof Deadline) {
            return (Storage.FILE_DEADLINE + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription() + " | " + ((Deadline) task).getDeadline());
        } else {
            return (Storage.FILE_EVENT + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription() + " | " + ((Event) task).getAt());
        }
    }

    public static String convertStatusToString(boolean value) {
        if (value) {
            return Storage.FILE_DONE;
        }
        return Storage.FILE_NOT_DONE;
    }

    public static boolean isBye(String command) {
        return command.equals(Commands.COMMAND_BYE);
    }
}
