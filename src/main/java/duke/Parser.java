package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String BYE = "bye";
    private static boolean isContinueInput = true;

    private Scanner in;
    private File duke;

    public Parser(File file) {
        duke = file;
    }
    public Parser(Scanner in) {
        this.in = in;
    }

    /**
     * Continuous read user input until user exit the application.
     */
    public void readUntilEndOfInput() {
        String line;

        while (isContinueInput && in.hasNextLine()) {
            line = in.nextLine();
            String[] input = line.split(" ", 2);
            String command = input[0].toLowerCase();
            String commandArg;
            if (input.length > 1) {
                commandArg = input[1];
            } else {
                commandArg = "";
            }
            try {
                taskDecoder(command, commandArg);
            } catch (DukeException e) {
                Ui.printInvalidCommand();
            }
        }
    }

    /**
     * Read the saved content in the file.
     */
    public void readFileContent() {
        try {
            String line;
            readUntilEndOfFile();
            TaskManager.printTaskRemaining();
            Ui.setEndOfSection();
        } catch (IOException e) {
            System.out.println("File corrupted");
        }
    }

    private void readUntilEndOfFile() throws FileNotFoundException {
        String line;
        Scanner readFile = new Scanner(duke);
        for (int i = 0; readFile.hasNextLine(); ++i) {
            line = readFile.nextLine();
            String[] input = line.split(" ", 2);
            if (input[0].charAt(1) == 'T') {
                String todoDescription = input[1].replaceAll("\\W", " ").strip();
                TaskManager.addToDo(todoDescription, todoDescription);
            } else if (input[0].charAt(1) == 'D') {
                String deadline = input[1].replaceAll("\\W", " ").strip();
                String[] deadlineDescription = deadline.split("by", 2);
                TaskManager.addDeadline(deadlineDescription[0].strip(), deadlineDescription[1].strip());
            } else if (input[0].charAt(1) == 'E') {
                String event = input[1].replaceAll("\\W", " ").strip();
                String[] eventDescription = event.split("at", 2);
                TaskManager.addEvent(eventDescription[0].strip(), eventDescription[1].strip());
            }
            if (input[0].length() > 4) {
                TaskManager.getTask(Integer.toString(i + 1)).markAsDone();
            }
        }
        readFile.close();
    }

    /**
     * A method call to interpret the user command
     * @param command the representation of the command type
     * @param commandArg the command description
     * @throws DukeException if the user input the command line incorrectly.
     */
    private void taskDecoder(String command, String commandArg) throws DukeException {
        switch(command) {
        case LIST:
            TaskManager.printAllTasks();
            break;
        case TODO:
            TaskManager.addToDo(commandArg);
            break;
        case EVENT:
            TaskManager.addEvent(commandArg);
            break;
        case DEADLINE:
            TaskManager.addDeadline(commandArg);
            break;
        case MARK:
            TaskManager.markTask(commandArg);
            break;
        case UNMARK:
            TaskManager.unmarkTask(commandArg);
            break;
        case BYE:
            isContinueInput = false;
            TaskManager.saveData();
            break;
        case DELETE:
            TaskManager.deleteTask(commandArg);
            break;
        case FIND:
            TaskManager.findTask(commandArg);
            break;
        default:
            throw new DukeException();
        }
    }
}
