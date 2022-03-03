import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the task list and execution of different commands
 */
public class TaskList {

    /**
     * main method to take in user input and execute different commands
     */
    public static void executeCommands() {

        ArrayList<String> instructionsList = new ArrayList<>();
        Task task = new Task("homework");
        Task.number = 0;
        Deadline deadline = new Deadline("return book", "holiday");
        Event event = new Event("test", "7pm");

        Storage.loadFromFile(instructionsList);

        for (int i = 0; i < 200; i++) {
            // take in up to 200 input lines (including invalid commands)

            Duke.scanInput(task);
            String instructionLine = task.instruction.replaceAll("todo|deadline|event", "");

            String[] arrayOfStr = task.instruction.split(" ", 50);
            String[] arrayOfDeadline = instructionLine.split("/by ", 2);
            String[] arrayOfEvent = instructionLine.split("/at ", 2);

            boolean isBye = arrayOfStr[0].equals(UI.COMMAND_BYE);
            boolean isList = arrayOfStr[0].equals(UI.COMMAND_LIST);
            boolean isMark = arrayOfStr[0].equals(UI.COMMAND_MARK);
            boolean isTodo = arrayOfStr[0].equals(UI.COMMAND_TODO);
            boolean isDeadline = arrayOfStr[0].equals(UI.COMMAND_DEADLINE);
            boolean isEvent = arrayOfStr[0].equals(UI.COMMAND_EVENT);
            boolean isDelete = arrayOfStr[0].equals(UI.COMMAND_DELETE);
            boolean isFind = arrayOfStr[0].equals(UI.COMMAND_FIND);

            try {
                if (isBye) {
                    executeBye();
                    break;
                } else if (isList) {
                    executeList(instructionsList);
                } else if (isMark) {
                    executeMark(instructionsList, arrayOfStr);
                } else if (isTodo) {
                    executeTodo(instructionsList, instructionLine, arrayOfStr);
                } else if (isDelete){
                    executeDelete(instructionsList, arrayOfStr);
                } else if (isDeadline) {
                    executeDeadline(instructionsList, deadline, arrayOfStr, arrayOfDeadline);
                } else if (isEvent) {
                    executeEvent(instructionsList, event, arrayOfStr, arrayOfEvent);
                } else if (isFind) {
                    executeFind(instructionsList, task, arrayOfStr);
                } else {
                    System.out.println(UI.ERROR_GENERAL);
                }
            } catch(DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
            Storage.saveToFile(instructionsList);
        }
    }

    private static void executeBye() throws IOException {
        System.out.println(UI.MESSAGE_BYE);
    }

    private static void executeList(ArrayList<String> instructionsList) throws IOException {
        printListMessage(instructionsList);
    }

    private static void executeMark(ArrayList<String> instructionsList, String[] arrayOfStr) throws DukeException {

        Parser.checkExceptionsMark(arrayOfStr);

        String instructionNum;
        instructionNum = arrayOfStr[1];
        int index = Integer.parseInt(instructionNum) - 1;
        String temp = instructionsList.get(index);
        temp = temp.replace("  (T)( )", "  (T)(X)");
        temp = temp.replace("  (E)( )", "  (E)(X)");
        temp = temp.replace("  (D)( )", "  (D)(X)");
        instructionsList.set(index, temp);

        printMarkMessage(instructionsList, index);
    }

    private static void executeTodo(ArrayList<String> instructionsList, String instructionLine, String[] arrayOfStr) throws DukeException, IOException {
        Parser.checkExceptionsTodo(arrayOfStr);

        String updatedInstructionLine;
        updatedInstructionLine = "  (T)( )" + instructionLine;
        instructionsList.add("");
        instructionsList.set(Task.number, updatedInstructionLine);
        Task.number++;

        printTodoMessage(updatedInstructionLine);
    }

    private static void executeFind(ArrayList<String> instructionsList, Task task, String[] arrayOfStr) throws DukeException {
        Parser.checkExceptionsFind(arrayOfStr);

        String keyword = arrayOfStr[1];
        int numOfMatching = 0;

        printFindMessage(instructionsList, keyword, numOfMatching);
    }

    private static void executeEvent(ArrayList<String> instructionsList, Event event, String[] arrayOfStr, String[] arrayOfEvent) throws DukeException {
        Parser.checkExceptionsEvent(arrayOfStr, arrayOfEvent);

        String updatedInstructionLine;
        event.instruction = arrayOfEvent[0];
        event.setAt(arrayOfEvent[1]);
        updatedInstructionLine = event.toString();
        instructionsList.add("");
        instructionsList.set(Task.number, updatedInstructionLine);
        Task.number++;

        printEventMessage(event);
    }

    private static void executeDeadline(ArrayList<String> instructionsList, Deadline deadline, String[] arrayOfStr, String[] arrayOfDeadline) throws DukeException {
        Parser.checkExceptionsDeadline(arrayOfStr, arrayOfDeadline);

        String updatedInstructionLine;
        deadline.instruction = arrayOfDeadline[0];
        deadline.setBy(arrayOfDeadline[1]);
        updatedInstructionLine = deadline.toString();
        instructionsList.add("");
        instructionsList.set(Task.number, updatedInstructionLine);
        Task.number++;

        printDeadlineMessage(deadline);
    }

    private static void executeDelete(ArrayList<String> instructionsList, String[] arrayOfStr) throws DukeException {
        Parser.checkExceptionsDelete(arrayOfStr);

        String instructionNum;
        instructionNum = arrayOfStr[1];
        int index = Integer.parseInt(instructionNum) - 1;
        Task.number--;

        printDeleteMessage(instructionsList, index);
        instructionsList.remove(index);
    }

    private static void printTodoMessage(String updatedInstructionLine) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(updatedInstructionLine);
        System.out.println("Now you have " + Task.number + " task(s) in the list.");
    }

    private static void printMarkMessage(ArrayList<String> instructionsList, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(instructionsList.get(index));
    }

    private static void printDeleteMessage(ArrayList<String> instructionsList, int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(instructionsList.get(index));
        System.out.println("Now you have " + Task.number + " task(s) in the list.");
    }

    private static void printDeadlineMessage(Deadline deadline) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(deadline);
        System.out.println("Now you have " + Task.number + " task(s) in the list.");
    }

    private static void printEventMessage(Event event) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(event);
        System.out.println("Now you have " + Task.number + " task(s) in the list.");
    }

    private static void printListMessage(ArrayList<String> instructionsList) {
        System.out.println("Here are the task(s) in your list:");
        for (int j = 1; j <= Task.number; j++) {
            System.out.println(j + "." + instructionsList.get(j - 1));
        }
    }

    private static void printFindMessage(ArrayList<String> instructionsList, String keyword, int numOfMatching) {
        System.out.println("Here are the matching task(s) in your list:");
        for (int j = 1; j <= Task.number; j++) {
            if (instructionsList.get(j - 1).contains(keyword)){
                numOfMatching++;
                System.out.println(numOfMatching + "." + instructionsList.get(j - 1));
            }
        }
    }
}
