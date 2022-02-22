package duke.taskmanagement;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.userinterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private int taskUniqueID = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static TaskRecorder taskRecorder = new TaskRecorder();
    private static UserInterface ui = new UserInterface();

    public TaskManager() {
        try {
            ArrayList<String[]> fileData = taskRecorder.loadData();
            for (String[] data : fileData) {
                String input = data[1];
                String command = ui.getCommand(input);
                String description = getDescription(input);
                switch (command) {
                case "todo":
                    Todo newTodo = new Todo(description, taskUniqueID);
                    tasks.add(newTodo);
                    break;
                case "deadline":
                    String by = getTimingDetails(input);
                    Deadline newDeadline = new Deadline(description, taskUniqueID, by);
                    tasks.add(newDeadline);
                    break;
                case "event":
                    String at = getTimingDetails(input);
                    Event newEvent = new Event(description, taskUniqueID, at);
                    tasks.add(newEvent);
                    break;
                }
                if (data[0].equals("1")) {
                    tasks.get(tasks.size() - 1).setIsMarked();
                }
                taskUniqueID++;
            }
        } catch (IOException e) {
            ui.printIOExceptionMessageRead();
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        } catch (EmptyTimingDetailsException e) {
            ui.printEmptyTimingDetailsMessage();
        }
    }

    public void listTasks() {
        ui.printTasks(tasks);
    }

    public void addTodo(String userInput) {
        try {
            String description = getDescription(userInput);
            Todo newTodo = new Todo(description, taskUniqueID);
            tasks.add(newTodo);
            ui.printMessageForAdding(tasks, newTodo);
            taskRecorder.addData(userInput);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    public void addDeadline(String userInput) {
        try {
            String description = getDescription(userInput);
            String by = getTimingDetails(userInput);
            Deadline newDeadline = new Deadline(description, taskUniqueID, by);
            tasks.add(newDeadline);
            ui.printMessageForAdding(tasks, newDeadline);
            taskRecorder.addData(userInput);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        } catch (EmptyTimingDetailsException e) {
            ui.printEmptyTimingDetailsMessage();
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    public void addEvent(String userInput) {
        try {
            String description = getDescription(userInput);
            String at = getTimingDetails(userInput);
            Event newEvent = new Event(description, taskUniqueID, at);
            tasks.add(newEvent);
            ui.printMessageForAdding(tasks, newEvent);
            taskRecorder.addData(userInput);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        } catch (EmptyTimingDetailsException e) {
            ui.printEmptyTimingDetailsMessage();
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    public void markOrUnmarkTask(String userInput) {
        try {
            String command = ui.getCommand(userInput);
            int number = getTaskNumber(userInput);
            ui.printMarkOrUnmarkMessage(tasks, command, number);
            taskRecorder.markOrUnmarkData(number);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printOutOfBoundsExceptionMessage();
        } catch (NumberFormatException e) {
            ui.printNumberFormatExceptionMessage();
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    public void deleteTask(String userInput) {
        try {
            int number = getTaskNumber(userInput);
            ui.printTask(tasks, number);
            tasks.remove(number);
            taskRecorder.deleteData(number);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printOutOfBoundsExceptionMessage();
        } catch (NumberFormatException e) {
            ui.printNumberFormatExceptionMessage();
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    private String getDescription(String userInput) throws EmptyDescriptionException {
        try {
            int indexOfDescription = userInput.indexOf(" ");
            int indexOfSlash = userInput.indexOf("/");
            String description = "";
            if (indexOfSlash != -1) {
                description = userInput.substring(indexOfDescription, indexOfSlash);
            } else {
                description = userInput.substring(indexOfDescription);
            }
            if (description.equals(" ")) {
                throw new EmptyDescriptionException();
            }
            return description;
        } catch (StringIndexOutOfBoundsException | EmptyDescriptionException e) {
            throw new EmptyDescriptionException();
        }
    }

    private String getTimingDetails(String userInput) throws EmptyTimingDetailsException {
        int indexOfSlash = userInput.indexOf("/");
        int indexOfTimingDetails = indexOfSlash + 4;
        if (indexOfSlash != -1) {
            return userInput.substring(indexOfTimingDetails);
        } else {
            throw new EmptyTimingDetailsException();
        }
    }

    private int getTaskNumber(String userInput) {
        String[] words = userInput.split(" ");
        String taskNumber = words[1];
        int number = Integer.parseInt(taskNumber) - 1;
        return number;
    }
}