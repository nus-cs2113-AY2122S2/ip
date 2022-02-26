import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    private static final String filePath = "data/duke.txt";

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) throws IOException {

        File f = new File(filePath);
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create file to save tasks.");
            }
        }

        System.out.println("full path: " + f.getAbsolutePath());

        System.out.println("Hello from\n" + UI.LOGO);
        System.out.println(UI.MESSAGE_GREET);
        writeToFile(filePath, "start program\n");

        ArrayList<String> instructionsList = new ArrayList<>();

        Task task = new Task("hello");
        task.number = 0;

        Deadline deadline = new Deadline("return book", "holiday");
        Event event = new Event("test", "7pm");


        for (int i = 0; i < 200; i++) { // can have 200 input lines (including wrong command)
            Scanner in = new Scanner(System.in);
            task.instruction = in.nextLine();
            String instructionLine = task.instruction.replaceAll("todo|deadline|event", "");
            String updatedInstructionLine; //updated instruction line is in the form of [][] instructionline

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

                    System.out.println(UI.MESSAGE_BYE);
                    writeToFile(filePath, "exit program\n");
                    break;

                } else if (isList) {

                    executeList(instructionsList, task);

                } else if (isMark) {

                    executeMark(instructionsList, arrayOfStr);

                } else if (isTodo) {

                    executeTodo(instructionsList, task, instructionLine, arrayOfStr);

                } else if (isDelete){
                    executeDelete(instructionsList, task, arrayOfStr);

                } else if (isDeadline) {

                    executeDeadline(instructionsList, task, deadline, arrayOfStr, arrayOfDeadline);

                } else if (isEvent) {

                    executeEvent(instructionsList, task, event, arrayOfStr, arrayOfEvent);

                } else if (isFind) {
                    executeFind(instructionsList, task, arrayOfStr);

                } else {
                    System.out.println(UI.ERROR_GENERAL);
                }

            } catch(DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void executeFind(ArrayList<String> instructionsList, Task task, String[] arrayOfStr) throws DukeException {
        if (arrayOfStr.length == 1){
            throw new DukeException("☹ OOPS! You have not entered what you want to find!");
        }

        String keyword = arrayOfStr[1];
        int numOfMatching = 0;

        System.out.println("Here are the matching task(s) in your list:");
        for (int j = 1; j <= task.number; j++) {
            if (instructionsList.get(j - 1).contains(keyword)){
                numOfMatching++;
                System.out.println(numOfMatching + ". " + instructionsList.get(j - 1));
            }
        }
    }

    private static void executeEvent(ArrayList<String> instructionsList, Task task, Event event, String[] arrayOfStr, String[] arrayOfEvent) throws DukeException {
        String updatedInstructionLine;
        if (arrayOfStr.length == 1){
            throw new DukeException(UI.ERROR_NO_EVENT);
        }
        if (arrayOfEvent.length == 1){
            throw new DukeException(UI.ERROR_NO_EVENT_DATE);
        }

        event.instruction = arrayOfEvent[0];
        event.setAt(arrayOfEvent[1]);
        updatedInstructionLine = event.toString();
        instructionsList.add("0");
        instructionsList.set(task.number, updatedInstructionLine);
        task.number++;

        System.out.println("Got it. I've added this task: ");
        System.out.println(event);
        System.out.println("Now you have " + task.number + " task(s) in the list.");

        try {
            appendToFile(filePath, updatedInstructionLine + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void executeDeadline(ArrayList<String> instructionsList, Task task, Deadline deadline, String[] arrayOfStr, String[] arrayOfDeadline) throws DukeException {
        String updatedInstructionLine;
        if (arrayOfStr.length == 1){
            throw new DukeException(UI.ERROR_NO_TASK);
        }
        if (arrayOfDeadline.length == 1){
            throw new DukeException(UI.ERROR_NO_DUE_DATE);
        }
        deadline.instruction = arrayOfDeadline[0];
        deadline.setBy(arrayOfDeadline[1]);
        updatedInstructionLine = deadline.toString();
        instructionsList.add("0");
        instructionsList.set(task.number, updatedInstructionLine);
        task.number++;

        System.out.println("Got it. I've added this task: ");
        System.out.println(deadline);
        System.out.println("Now you have " + task.number + " task(s) in the list.");

        try {
            appendToFile(filePath, updatedInstructionLine + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void executeDelete(ArrayList<String> instructionsList, Task task, String[] arrayOfStr) throws DukeException {
        String instructionNum;
        if (arrayOfStr.length == 1) {
            throw new DukeException(UI.ERROR_NO_TASK);
        }

        instructionNum = arrayOfStr[1];
        int index = Integer.parseInt(instructionNum) - 1;
        task.number--;

        System.out.println("Noted. I've removed this task: ");
        System.out.println(instructionsList.get(index));
        System.out.println("Now you have " + task.number + " task(s) in the list.");
        instructionsList.remove(index);
    }

    private static void executeList(ArrayList<String> instructionsList, Task task) {
        System.out.println("Here are the task(s) in your list:");
        for (int j = 1; j <= task.number; j++) {
            System.out.println(j + ". " + instructionsList.get(j - 1));
        }
    }

    private static void executeMark(ArrayList<String> instructionsList, String[] arrayOfStr) throws DukeException {
        String instructionNum;
        if (arrayOfStr.length == 1){
            throw new DukeException(UI.ERROR_NO_TASK_NUMBER);
        }

        instructionNum = arrayOfStr[1];
        int index = Integer.parseInt(instructionNum) - 1;
        String temp = instructionsList.get(index);
        String prefix = "  \\[T]\\[ ]";
        temp = temp.replaceAll(prefix, "  [T][X]");
        instructionsList.set(index, temp); //updates the list

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(instructionsList.get(index));

        try {
            appendToFile(filePath, instructionsList.get(index) + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void executeTodo(ArrayList<String> instructionsList, Task task, String instructionLine, String[] arrayOfStr) throws DukeException {
        String updatedInstructionLine;
        if (arrayOfStr.length == 1) {
            throw new DukeException(UI.ERROR_NO_TASK);
        }

        updatedInstructionLine = "  [T][ ]" + instructionLine;
        instructionsList.add("0");
        instructionsList.set(task.number, updatedInstructionLine);
        task.number++;

        System.out.println("Got it. I've added this task: ");
        System.out.println(updatedInstructionLine);
        System.out.println("Now you have " + task.number + " task(s) in the list.");

        try {
            appendToFile(filePath, updatedInstructionLine + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
