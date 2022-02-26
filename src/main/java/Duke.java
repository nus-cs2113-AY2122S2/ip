import java.io.*;
import java.nio.file.Path;
import java.util.*;

import em.exception.*;
import task.Task;

import java.util.ArrayList;

import static em.exception.InvalidUserInputException.INVALID_INPUT;

public class Duke {
    public static ArrayList<Task> taskArrayList = new ArrayList<>();
    public static final Path DATABASE_FILEPATH = Path.of("database/database.txt");
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private Storage storage;


    /**
     * Initiate and create all the required data needed before accepting user input.
     * Populate tasks in file into the task list.
     *
     * @param databasePath file path of the pre-defined tasks to be populated to the task list.
     * @throws FileNotFoundException If the file to be read was not found.
     * @throws IOException If the input and output is invalid.
     * @throws InvalidUserInputException If the command in the file is unable to be processed.
     * @throws StorageException  If
     */
    public void initiateData(Path databasePath) {
        ui = new Ui();
        storage = new Storage(databasePath);
        parser = new Parser();
        ui.displayLogo();
        try {
            Storage.checkFileExists();
            tasks = new TaskList(Storage.populateFileContents());
        } catch (FileNotFoundException e) {
            System.out.println(StorageException.INVALID_FILE);
        } catch (IOException e) {
            System.out.println(StorageException.IO_EXCEPTION);
        } catch (InvalidUserInputException e){
            System.out.println(e.getMessage());
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Process user input and get the corresponding output.
     * Keep running until user input "Bye" command.
     * All user command type are case-insensitive. This method is able to process
     * command types such as list, mark , unmark, find, event, deadline and todo.
     * Other command types other than stated above will be deemed as invalid command.
     *
     * @throws IndexOutOfBoundsException If the user input did not include time or date description.
     * @throws NumberFormatException If the task number stated is not a number.
     * @throws InvalidUserInputException If the command in the file is unable to be processed.
     */
    public void processAction() {
        initiateData(DATABASE_FILEPATH);
        String userInput;
        Scanner in = new Scanner(System.in);
        System.out.print("> ");
        userInput = in.nextLine();
        while (!userInput.equalsIgnoreCase("Bye")) {
            try {
                parser.checkValidityOfInput(userInput);
                String action = userInput.split(" ")[0];
                switch (action.toLowerCase()) {
                case "list":
                    ui.displayTaskList(taskArrayList);
                    break;
                case "mark":
                    int markTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (parser.isTaskValid(markTaskNumber, taskArrayList)) {
                        taskArrayList.get(markTaskNumber - 1).markAsDone(markTaskNumber, taskArrayList, true);
                        Storage.modifyDatabase(markTaskNumber, true, false);
                    }
                    break;
                case "unmark":
                    int unmarkTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (parser.isTaskValid(unmarkTaskNumber, taskArrayList)) {
                        taskArrayList.get(unmarkTaskNumber - 1).markAsUndone(unmarkTaskNumber, taskArrayList, true);
                        Storage.modifyDatabase(unmarkTaskNumber, false, false);
                    }
                    break;
                case "todo":
                    tasks.addTask(userInput, taskArrayList, true);
                    break;
                case "event":
                case "deadline":
                    tasks.addTaskAndTime(userInput, taskArrayList, true);
                    break;
                case "delete":
                    int deleteTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (parser.isTaskValid(deleteTaskNumber, taskArrayList)) {
                        taskArrayList.get(deleteTaskNumber - 1).deleteTask(deleteTaskNumber, taskArrayList);
                        Storage.modifyDatabase(deleteTaskNumber, false, true);
                    }
                    break;
                case "find":
                    String keyword = userInput.split(" ")[1];
                    TaskList.findContent(keyword);
                    break;
                default:
                    throw new InvalidUserInputException(INVALID_INPUT);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(InvalidUserInputException.NO_TIME_OR_DESCRIPTION);
            } catch (NumberFormatException e) {
                System.out.println(InvalidUserInputException.CORRUPTED_TASK_NUM);
            } catch (InvalidUserInputException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("> ");
            userInput = in.nextLine();
        }
        ui.displayFarewell();
    }

    public static void main(String[] args) {
        new Duke().processAction();
    }
}