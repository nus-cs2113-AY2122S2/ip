import java.util.ArrayList;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.Scanner;

public class Duke {

    public static final String WELCOME_MESSAGE = "##    ##  #######  ##    ##  ######      ##     ## #### ##    ##  ######\n"
                                               + " ##  ##  ##     ## ###   ## ##    ##     ###   ###  ##  ###   ## ##    ##\n"
                                               + "  ####   ##     ## ####  ## ##           #### ####  ##  ####  ## ##\n"
                                               + "   ##    ##     ## ## ## ## ##   ####    ## ### ##  ##  ## ## ## ##   ####\n"
                                               + "   ##    ##     ## ##  #### ##    ##     ##     ##  ##  ##  #### ##    ##\n"
                                               + "   ##    ##     ## ##   ### ##    ##     ##     ##  ##  ##   ### ##    ##\n"
                                               + "   ##     #######  ##    ##  ######      ##     ## #### ##    ##  ######\n"
                                               + "Hello! I'm Yong Ming\n"
                                               + "What can I do for you?";
    public static final String EXIT_MESSAGE = "bye";
    public static final String PRINT_MESSAGE = "list";
    public static final String MARK_MESSAGE = "mark";
    public static final String UNMARK_MESSAGE = "unmark";
    public static final String DELETE_MESSAGE = "delete";
    public static final String TODO_MESSAGE = "todo";
    public static final String DEADLINE_MESSAGE = "deadline";
    public static final String DEADLINE_INDICATOR = " /by ";
    public static final String EVENT_MESSAGE = "event";
    public static final String EVENT_INDICATOR = " /at ";
    public static final String WRONG_FORMAT_MESSAGE = "OOPS!!! One or more parameters are missing or invalid. The correct format is:\n"
                                                    + "todo [description]\n"
                                                    + "deadline [description] /by [deadline]\n"
                                                    + "event [description] /at [time]\n"
                                                    + "mark [Task#]\n"
                                                    + "unmark [Task#]\n"
                                                    + "delete [Task#]\n";
    public static final String WRONG_INPUT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                                   + "The accepted inputs are:\n"
                                                   + "> todo [description]\n"
                                                   + "> deadline [description] /by [deadline]\n"
                                                   + "> event [description] /at [time]\n"
                                                   + "> list\n"
                                                   + "> mark [Task#]\n"
                                                   + "> unmark [Task#]\n"
                                                   + "> delete [Task#]\n"
                                                   + "> bye\n";
    public static final String FILE_PATH = "data/duke.txt";
    public static final String FOLDER_NAME = "data/";

    public static void printList(ArrayList<Task> list, int listCounter) {
        if (listCounter == 0) {
            System.out.println("There are no tasks yet!\n");
        } else {
            for (int i = 0; i < listCounter; i++) {
                int listIndex = i + 1;
                System.out.println(listIndex + "." + list.get(i));
            }
            System.out.println();
        }
    }

    public static int getTaskIndex(String userInput) {
        String taskNumber = userInput.split(" ")[1];
        return Integer.parseInt(taskNumber) - 1;
    }

    private static void markTask(ArrayList<Task> list, String userInput) {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(userInput);
            list.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(WRONG_FORMAT_MESSAGE);
        }
    }

    private static void unmarkTask(ArrayList<Task> list, String userInput) {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(userInput);
            list.get(taskIndex).markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(WRONG_FORMAT_MESSAGE);
        }
    }

    private static void printListCounter(int listCounter) {
        if (listCounter == 0) {
            System.out.println("Now you have no tasks in the list\n");
        } else if (listCounter == 1) {
            System.out.println("Now you have 1 task in the list\n");
        } else {
            System.out.println("Now you have " + listCounter + " tasks in the list.\n");
        }
    }

    public static void deleteTask(ArrayList<Task> list, String userInput, int listCounter) {
        int taskIndex = getTaskIndex(userInput);
        Task deleteTarget = list.get(taskIndex);
        list.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleteTarget);
        listCounter--;
        printListCounter(listCounter);
    }

    public static String[] parseAdditionalParameters (String parsedUserInput, String indicator) {
        String[] additionalParameters = parsedUserInput.split(indicator, 2);
        additionalParameters[0] = additionalParameters[0].trim();
        additionalParameters[1] = additionalParameters[1].trim();
        return additionalParameters;
    }

    private static void printAddToList(ArrayList<Task> list, int listCounter) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + list.get(listCounter));
        listCounter++;
        printListCounter(listCounter);
    }

    //Check what kind of task the user intends to add and process accordingly
    public static void parseInput(ArrayList<Task> list, int listCounter, String userInput) throws DukeException {
        String[] parsedUserInputs = userInput.split(" ", 2);
        parsedUserInputs[0] = parsedUserInputs[0].toLowerCase();
        switch (parsedUserInputs[0]) {
        case TODO_MESSAGE:
            parsedUserInputs[1] = parsedUserInputs[1].trim();
            if (parsedUserInputs[1].length() == 0) {
                throw new IndexOutOfBoundsException();
            }
            list.add(new ToDo(parsedUserInputs[1]));
            break;
        case DEADLINE_MESSAGE:
            String[] deadlineInput = parseAdditionalParameters(parsedUserInputs[1], DEADLINE_INDICATOR);
            if (deadlineInput[0].length() == 0 || deadlineInput[1].length() == 0){
                throw new IndexOutOfBoundsException();
            }
            list.add(new Deadline(deadlineInput[0], deadlineInput[1]));
            break;
        case EVENT_MESSAGE:
            String[] eventInput = parseAdditionalParameters(parsedUserInputs[1], EVENT_INDICATOR);
            if (eventInput[0].length() == 0 || eventInput[1].length() == 0){
                throw new IndexOutOfBoundsException();
            }
            list.add(new Event(eventInput[0], eventInput[1]));
            break;
        default:
            throw new DukeException();
        }
        printAddToList(list, listCounter);
    }

    private static void writeToFile(ArrayList<Task> list) {
        File dir = new File(FOLDER_NAME);
        dir.mkdirs();
        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.close();
        } catch (IOException e) {
            System.out.println("IO Error");
        }
        System.out.println("Task File Updated");
    }

    private static void processInput(ArrayList<Task> list, String userInput, Scanner in, int listCounter) {
        while(!userInput.equalsIgnoreCase(EXIT_MESSAGE)){
            if (userInput.startsWith(PRINT_MESSAGE)) {
                printList(list, listCounter);
            } else if (userInput.startsWith(MARK_MESSAGE)) {
                markTask(list, userInput);
            } else if (userInput.startsWith(UNMARK_MESSAGE)) {
                unmarkTask(list, userInput);
            } else if (userInput.startsWith(DELETE_MESSAGE)) {
                try {
                    deleteTask(list, userInput, listCounter);
                    listCounter--;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(WRONG_FORMAT_MESSAGE);
                }
            } else {
                try {
                    parseInput(list, listCounter, userInput);
                    listCounter++;
                } catch (DukeException e) {
                    System.out.println(WRONG_INPUT_MESSAGE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(WRONG_FORMAT_MESSAGE);
                }
            }
            userInput = in.nextLine();
        }
        writeToFile(list);
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void acceptInput() {
        Object obj;
        ArrayList<Task> list = new ArrayList<>();
        int listCounter = 0;
        try {
            FileInputStream fileIn = new FileInputStream(FILE_PATH);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            objectIn.close();
            System.out.println("Task File Uploaded\n");
            list = (ArrayList<Task>) obj;
            listCounter = list.size();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No input file located\n");
        }
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        processInput(list, userInput, in, listCounter);
    }

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        acceptInput();
    }

}
