package aeon.controller;

import aeon.exception.AeonException;
import aeon.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /**
     * Inserts a deadline from the saved list of tasks
     * @param list list of tasks to store the tasks read from the text file
     * @param taskType the type of task, whether its a Todo, Deadline or Event task
     * @param isDone boolean to represent if the task was previously marked as done
     */
    public static void readFromFileDeadline(ArrayList<Task> list, String[] taskType, String isDone) {
        try {
            Command.addDeadlineTask(list, taskType);
        } catch (AeonException e) {
            UI.printOut(UI.TASK_DETAILS_MISSING_MSG);
        } catch (IndexOutOfBoundsException e) {
            UI.printOut(UI.DEADLINE_FORMAT_ERR);
        }

        fileMarkTask(list, isDone);
    }

    /**
     * Saves the current list of tasks into a text file on disk
     * @param list the list of tasks to be saved
     * @param rawDesc the actual user input itself, to be saved into the text file
     * @throws IOException if writing to file fails
     */
    public static void writeToFile(ArrayList<Task> list, ArrayList<String> rawDesc) throws IOException {
        FileWriter fw = new FileWriter(UI.FILE_PATH);
        Integer noOfItems = Task.getNoOfItems();
        for (int index = 0; index < noOfItems; index++) {
            String taskDone = list.get(index).getStatusIcon();
            if (taskDone.equals(UI.TASK_MARKED)) {
                taskDone = "1";
            } else {
                taskDone = "0";
            }
            String[] taskToAdd = rawDesc.get(index).split(" ", 2);
            String textToAdd = taskDone + " " + taskToAdd[0] + " " + taskToAdd[1] + "\n";
            fw.write(textToAdd);
        }
        fw.close();
    }

    /**
     * Loads an existing list of tasks from a previously saved text file
     * and stores it in the list of tasks
     * @param list list of tasks to store the tasks read from the text file
     */
    public static void readSavedTaskList(ArrayList<Task> list) throws AeonException {
        File FILE = new File(UI.FILE_PATH);
        File DIRECTORY = new File(UI.DIR_PATH);
        checkDirExists(DIRECTORY);
        checkFileExists(FILE);
        setReadAndWritePermissions(FILE, DIRECTORY);
        Scanner fileScanner = null;
        fileScanner = openTaskFile(FILE, fileScanner);
        while (fileScanner.hasNext()) {
            String taskInFile = fileScanner.nextLine();
            String[] taskInFileArray = taskInFile.split(" ", 2);
            if (Command.checkDetails(taskInFileArray)) {
                throw new AeonException();
            }
            try {
                Parser.parseSavedTaskList(list, taskInFileArray);
            } catch (IndexOutOfBoundsException e) {
                UI.printOut(UI.TEXT_FILE_INCORRECT_CONTENTS);
            }
        }
    }


    /**
     * Ensures the tasklist file and directory are both readable and writeable, in the event the user changes the file
     * permissions directly
     * @param FILE the file object to store the list of tasks
     * @param DIRECTORY the directory object which stores the text file
     */
    public static void setReadAndWritePermissions(File FILE, File DIRECTORY) {
        FILE.setWritable(true);
        FILE.setReadable(true);
        DIRECTORY.setWritable(true);
        DIRECTORY.setReadable(true);
    }

    /**
     * Opens the target file in order to read its contents
     * @param FILE the file to be read
     * @param fileScanner a pointer to the file
     * @return the pointer to the file if said file exists
     */
    private static Scanner openTaskFile(File FILE, Scanner fileScanner) {
        try {
            fileScanner = getScanner(FILE, fileScanner);
        } catch (FileNotFoundException e) {
            UI.printOut(e.getMessage());
        }
        return fileScanner;
    }

    /**
     * Checks if target file exists, and creates a new one if it does not exist yet
     * @param FILE the target file
     */
    private static void checkFileExists(File FILE) {
        if (!FILE.exists()) {
            try {
                createTaskFile(FILE);
            } catch (IOException e) {
                UI.printOut(UI.CREATE_FILE_FAILED);
            }
        }
    }

    /**
     * Checks if target directory exists, and creates a new one if it does not exist yet
     * @param DIRECTORY the target directory
     */
    private static void checkDirExists(File DIRECTORY) {
        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdir();
        }
    }

    /**
     * Creates a new text file to save the list of tasks to
     * @param fileobj the file to be created
     * @throws IOException if file creation fails
     */
    private static void createTaskFile(File fileobj) throws IOException {
        fileobj.createNewFile();
    }

    /**
     * Places a pointer at the beginning of the file to begin reading of its contents
     * @param fileobj the file itself
     * @param fileScanner the name of the pointer
     * @return the pointer to the beginning of the file
     * @throws FileNotFoundException if file currently does not exist
     */
    private static Scanner getScanner(File fileobj, Scanner fileScanner) throws FileNotFoundException {
        fileScanner = new Scanner(fileobj);
        return fileScanner;
    }

    /**
     * Inserts an event from the saved list of tasks
     * @param list list of tasks to store the tasks read from the text file
     * @param taskType the type of task, whether its a Todo, Deadline or Event task
     * @param isDone boolean to represent if the task was previously marked as done
     */
    public static void readFromFileEvent(ArrayList<Task> list, String[] taskType, String isDone) {
        try {
            Command.addEventTask(list, taskType);
        } catch (AeonException e) {
            UI.printOut(UI.TASK_DETAILS_MISSING_MSG);
        } catch (IndexOutOfBoundsException e) {
            UI.printOut(UI.EVENT_FORMAT_ERR);
        }
        fileMarkTask(list, isDone);
    }



    /**
     * Inserts a ToDo task from the saved list of tasks
     * @param list list of tasks to store the tasks read from the text file
     * @param taskType the type of task, whether its a Todo, Deadline or Event task
     * @param isDone boolean to represent if the task was previously marked as done
     */
    public static void readFromFileTodo(ArrayList<Task> list, String[] taskType, String isDone) {
        try {
            Command.addTodoTask(list, taskType);
        } catch (AeonException e) {
            UI.printOut(UI.TASK_DETAILS_MISSING_MSG);
        }
        fileMarkTask(list, isDone);
    }

    /**
     * Checks if task from text file was marked as done before, and mark it if true
     * @param list list of tasks to store the tasks read from the text file
     * @param isDone boolean to represent if the task was previously marked as done
     */
    public static void fileMarkTask(ArrayList<Task> list, String isDone) {
        if (isDone.equals("1")) {
            Integer index = Task.getNoOfItems();
            list.get(index - 1).setDoneStatus(true);
        }
    }

}
