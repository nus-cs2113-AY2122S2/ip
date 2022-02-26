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
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String COMMAND_BYE = "bye";
        String COMMAND_LIST = "list";
        String COMMAND_MARK = "mark";
        String COMMAND_TODO = "todo";
        String COMMAND_DEADLINE = "deadline";
        String COMMAND_EVENT = "event";
        String COMMAND_DELETE = "delete";
        String COMMAND_FIND = "find";

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        writeToFile(filePath, "start program\n");

        ArrayList<String> instructionsList = new ArrayList<>();

        Task task = new Task("hello");
        Task[] tasks = new Task[100];
        task.number = 0;

        Deadline deadline = new Deadline("return book", "holiday");
        Event event = new Event("test", "7pm");

        for (int i = 0; i < 200; i++) { // can have 200 input lines (including wrong command)
            Scanner in = new Scanner(System.in);
            task.instruction = in.nextLine();
            String instructionLine = task.instruction.replaceAll("todo|deadline|event", "");
            String updatedInstructionLine; //updated instruction line is in the form of [][] instructionline

            Task t = new Task("read book");
            String[] arrOfStr = task.instruction.split(" ", 50);
            String[] arrOfDeadline = instructionLine.split("/by ", 2);
            String[] arrOfEvent = instructionLine.split("/at ", 2);

            boolean isBye = arrOfStr[0].equals(COMMAND_BYE);
            boolean isList = arrOfStr[0].equals(COMMAND_LIST);
            boolean isMark = arrOfStr[0].equals(COMMAND_MARK);
            boolean isTodo = arrOfStr[0].equals(COMMAND_TODO);
            boolean isDeadline = arrOfStr[0].equals(COMMAND_DEADLINE);
            boolean isEvent = arrOfStr[0].equals(COMMAND_EVENT);
            boolean isDelete = arrOfStr[0].equals(COMMAND_DELETE);
            boolean isFind = arrOfStr[0].equals(COMMAND_FIND);
            
            String instructionNum;

            try {

                if (isBye) {

                    System.out.println("Bye. Hope to see you again soon! :)");
                    writeToFile(filePath, "exit program\n");
                    break;

                } else if (isList) {

                    System.out.println("Here are the task(s) in your list:");
                    for (int j = 1; j <= task.number; j++) {
                        System.out.println(j + ". " + instructionsList.get(j - 1));
                    }

                } else if (isMark) {

                    if (arrOfStr.length == 1){
                        throw new DukeException("☹ OOPS! You have not entered the task number!");
                    }

                    instructionNum = arrOfStr[1];
                    int index = Integer.parseInt(instructionNum) - 1;
                    t.setStatusIcon(true);
                    String temp = instructionsList.get(index);
                    String prefix = "  \\[T]\\[ ]";
                    temp = temp.replaceAll(prefix, "  [T][X]");
                    instructionsList.set(index, temp); //updates the list
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(instructionsList.get(index));

                } else if (isTodo) {

                    if (arrOfStr.length == 1) {
                        throw new DukeException("☹ OOPS! You have not entered your task!");
                    }

                    updatedInstructionLine = "  [T][ ]" + instructionLine;
                    tasks[task.number] = new Task(updatedInstructionLine);
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

                } else if (isDelete){
                    if (arrOfStr.length == 1) {
                        throw new DukeException("☹ OOPS! You have not entered your task!");
                    }

                    instructionNum = arrOfStr[1];
                    int index = Integer.parseInt(instructionNum) - 1;
                    task.number--;

                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(instructionsList.get(index));
                    System.out.println("Now you have " + task.number + " task(s) in the list.");
                    instructionsList.remove(index);

                } else if (isDeadline) {

                    if (arrOfStr.length == 1){
                        throw new DukeException("☹ OOPS! You have not entered your task!");
                    }
                    if (arrOfDeadline.length == 1){
                        throw new DukeException("Hey! You have not entered the due date! hint: use '/by'");
                    }
                    deadline.instruction = arrOfDeadline[0];
                    deadline.setBy(arrOfDeadline[1]);
                    updatedInstructionLine = deadline.toString();
                    tasks[task.number] = new Task(updatedInstructionLine);
                    instructionsList.add("0");
                    instructionsList.set(task.number, updatedInstructionLine);
                    task.number++;

                    System.out.println("Got it. I've added this task: ");
                    //deadline.getBy();
                    System.out.println(deadline);
                    System.out.println("Now you have " + task.number + " task(s) in the list.");

                    try {
                        appendToFile(filePath, updatedInstructionLine + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (isEvent) {

                    if (arrOfStr.length == 1){
                        throw new DukeException("☹ OOPS! You have not entered your event!");
                    }
                    if (arrOfEvent.length == 1){
                        throw new DukeException("Hey! You have not entered the event date! hint: use '/at'");
                    }

                    event.instruction = arrOfEvent[0];
                    event.setAt(arrOfEvent[1]);
                    updatedInstructionLine = event.toString();
                    tasks[task.number] = new Task(updatedInstructionLine);

                    instructionsList.add("0");
                    instructionsList.set(task.number, updatedInstructionLine);
                    task.number++;

                    System.out.println("Got it. I've added this task: ");
                    //event.getAt();
                    System.out.println(event);
                    System.out.println("Now you have " + task.number + " task(s) in the list.");

                    try {
                        appendToFile(filePath, updatedInstructionLine + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (isFind) {
                    if (arrOfStr.length == 1){
                        throw new DukeException("☹ OOPS! You have not entered what you want to find!");
                    }

                    String keyword = arrOfStr[1];
                    int numOfMatching = 0;

                    System.out.println("Here are the matching task(s) in your list:");
                    for (int j = 1; j <= task.number; j++) {
                        if (instructionsList.get(j - 1).contains(keyword)){
                            numOfMatching++;
                            System.out.println(numOfMatching + ". " + instructionsList.get(j - 1));
                        }
                    }
                } else {
                    System.out.println("☹ OOPS! I'm sorry, but I don't know what that means.");
                }

            } catch(DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
