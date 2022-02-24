import java.io.*;
import java.nio.file.Path;
import java.util.*;

import em.exception.*;
import task.Task;

import java.util.ArrayList;

import static em.exception.InvalidUserInputException.INVALID_INPUT;

public class Duke {
    public static final String LINE_SEPARATOR = "____________________________________________________________\n";
    public static ArrayList<Task> taskArrayList = new ArrayList<>();
    public static final Path DATABASE_FILEPATH = Path.of("database/database.txt");
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private Storage storage;

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


    public void processAction() {
        initiateData(DATABASE_FILEPATH);
        String userInput;
        Scanner in = new Scanner(System.in);
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
            userInput = in.nextLine();
        }
        ui.displayFarewell();
    }

    public static void main(String[] args) {
        new Duke().processAction();
    }
}