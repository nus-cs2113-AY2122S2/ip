package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

public class Duke {

    public static final int MAX_TASK = 100;
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_TODO = "todo";
    public static final File filePath = new File("src/main/java/duke/task/Hage.txt");


    private static int itemNum;
    private static String[] dateArray;
    private static String[] eventArray;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static ArrayList<Task> taskArrayList = new ArrayList<>();

    public static void main(String[] args) throws DukeException, IOException {

        printWelcomeMessage();
        initTaskTable();

        while (true) {
            getNewInput();
            saveTaskList();
        }
    }

    private static void getNewInput() throws DukeException, IOException {
        String inputCommand = SCANNER.nextLine();
        executeCommand(inputCommand);
    }

    private static void executeCommand(String inputCommand) throws DukeException, IOException {
        String[] commandArr = inputCommand.split(" ");
        int listNum;
        switch (commandArr[0]) {
        case COMMAND_LIST:
            listNum = 1;
            if (itemNum == 0) {
                System.out.println("List is empty");
            }
            else {
                for (Task a : taskArrayList) {
                    System.out.println(listNum++ + ". " + a);
                }
            }
            break;
        case COMMAND_MARK:
            int markNum = Integer.parseInt(commandArr[1]) - 1;
            try {
                taskArrayList.get(markNum).setDone(true);
                System.out.println("Nice! I've marked this task as done:\n" + taskArrayList.get(markNum));
            } catch(IndexOutOfBoundsException markOutOfBounds) {
                System.out.println("Invalid mark index!!");
            }
            break;
        case COMMAND_UNMARK:
            int unMarkNum = Integer.parseInt(commandArr[1]) - 1;
            try {
                taskArrayList.get(unMarkNum).setDone(false);
                System.out.println("Nice! I've marked this task as not done yet:\n" + taskArrayList.get(unMarkNum));
            } catch(IndexOutOfBoundsException unMarkOutOfBounds) {
                System.out.println("Unmark index is out of bounds!!");
            }
            break;
        case COMMAND_DELETE:
            int deleteIndex = Integer.parseInt(commandArr[1]) - 1;
            try {
                itemNum--;
                System.out.println("Noted. I've removed this task:\n" + taskArrayList.get(deleteIndex) +
                        "\nNow you have " + itemNum + " tasks in the list.");
                taskArrayList.remove(deleteIndex);
            } catch(IndexOutOfBoundsException deleteIndexOutOFBounds) {
                System.out.println("The item you want to delete is not in the list!");
            }
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        default:
            addTask(inputCommand);
            break;
        }
    }

    private static void saveTaskList() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task a : taskArrayList) {
            fw.write(String.valueOf(a.getTaskType()) + "-" + String.valueOf(a.isDone()) + "-"
                    + a.saveDescription() + a.saveAdditionalInfo() + System.lineSeparator());
        }
        fw.close();
    }

    private static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    private static void addTask(String inCommand) throws DukeException {
        String[] sentenceArr = inCommand.split(" ", 2);
        switch (sentenceArr[0]) {
        case COMMAND_TODO:
            try {
                taskArrayList.add(new ToDo(sentenceArr[1]));
                printAcknowledgeAddMessage();
                itemNum++;
            } catch (IndexOutOfBoundsException todoEmpty) {
                System.out.println("OOPS!!! The description of todo cannot be empty.");
            }
            break;
        case COMMAND_DEADLINE:
            try {
                dateArray = sentenceArr[1].split("/by", 2);
                if (dateArray[1].length() == 0 || dateArray[0].length() == 0) {
                    throw new DukeException();
                }
                taskArrayList.add(new Deadline(dateArray[0], dateArray[1]));
                printAcknowledgeAddMessage();
                itemNum++;
            } catch (ArrayIndexOutOfBoundsException deadlineEmpty) {
                System.out.println("OOPS!!! The description and date of deadline cannot be empty.");
            } catch (DukeException invalidDateInput) {
                System.out.println("Invalid deadline input! Please try again.");
            }
            break;
        case COMMAND_EVENT:
            try {
                eventArray = sentenceArr[1].split("/at", 2);
                if (eventArray[1].length() == 0 || eventArray[0].length() == 0) {
                    throw new DukeException();
                }
                taskArrayList.add(new Event(eventArray[0], eventArray[1]));
                printAcknowledgeAddMessage();
                itemNum++;
            } catch (ArrayIndexOutOfBoundsException deadlineEmpty) {
                System.out.println("OOPS!!! The description and time of event cannot be empty.");
            } catch (DukeException invalidEventInput) {
                System.out.println("Invalid event input! Please try again.");
            }
            break;
        default:
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    private static void printAcknowledgeAddMessage() {
        System.out.println("Got it. I've added this task:\n" + taskArrayList.get(itemNum));
        System.out.println("Now you have " + (itemNum + 1) +  " tasks in the list.");
    }


    private static void initTaskTable() throws IOException, DukeException {
        itemNum = 0;
        if (filePath.createNewFile()) {
            System.out.println("new file Hage.txt created");
        } else {
            System.out.println("Hage.txt exists");
        }
        Scanner s = new Scanner(filePath);
        String fileLine;
        String[] fileLineArray;
        char inputTask;
        while (s.hasNext()) {
            fileLine = s.nextLine();
            fileLineArray = fileLine.split("-", 3);
            inputTask = fileLineArray[0].charAt(0);
            switch (inputTask) {
            case 'T':
                loadTask("todo " + fileLineArray[2]);
                break;
            case 'D':
                loadTask("deadline " + fileLineArray[2]);
                break;
            case 'E':
                loadTask("event " + fileLineArray[2]);
            }
            taskArrayList.get(itemNum - 1).setDone(checkIfDone(fileLineArray[1]));
        }
    }

    private static boolean checkIfDone(String checkDone) {

        if (checkDone.equals("true")) {
            return true;
        }
        else {
            return false;
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Hage");
        System.out.println("What can I do for you?");
    }

    private static void loadTask(String inCommand) throws DukeException {
        String[] sentenceArr = inCommand.split(" ", 2);
        switch (sentenceArr[0]) {
        case COMMAND_TODO:
            try {
                taskArrayList.add(new ToDo(sentenceArr[1]));
                itemNum++;
            } catch (IndexOutOfBoundsException todoEmpty) {
                System.out.println("OOPS!!! The description of todo cannot be empty.");
            }
            break;
        case COMMAND_DEADLINE:
            try {
                dateArray = sentenceArr[1].split("/by", 2);
                if (dateArray[1].length() == 0 || dateArray[0].length() == 0) {
                    throw new DukeException();
                }
                taskArrayList.add(new Deadline(dateArray[0], dateArray[1]));
                itemNum++;
            } catch (ArrayIndexOutOfBoundsException deadlineEmpty) {
                System.out.println("OOPS!!! The description and date of deadline cannot be empty.");
            } catch (DukeException invalidDateInput) {
                System.out.println("Invalid deadline input! Please try again.");
            }
            break;
        case COMMAND_EVENT:
            try {
                eventArray = sentenceArr[1].split("/at", 2);
                if (eventArray[1].length() == 0 || eventArray[0].length() == 0) {
                    throw new DukeException();
                }
                taskArrayList.add(new Event(eventArray[0], eventArray[1]));
                itemNum++;
            } catch (ArrayIndexOutOfBoundsException deadlineEmpty) {
                System.out.println("OOPS!!! The description and time of event cannot be empty.");
            } catch (DukeException invalidEventInput) {
                System.out.println("Invalid event input! Please try again.");
            }
            break;
        default:
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }
}
