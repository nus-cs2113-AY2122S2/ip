package duke.taskmanagement;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskManager {
    private int taskUniqueID = 0;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public void listTasks() {
        if (taskList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i).toString());
            }
        } else {
            System.out.println("Wow, such empty");
        }
    }


    public void addTodo(String userInput) {
        try {
            String description = getDescription(userInput);
            taskList.add(new Todo(description, taskUniqueID));
            //taskList[taskUniqueID] = new Todo(description, taskUniqueID);
            printMessageForAdding(taskList.get(taskList.size() - 1));
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please type in a description!");
        }
    }

    public void addDeadline(String userInput) {
        try {
            String description = getDescription(userInput);
            String by = getTimingDetails(userInput);
            taskList.add(new Deadline(description, taskUniqueID, by));
            printMessageForAdding(taskList.get(taskList.size() - 1));
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please give a description for the deadline you want to add!");
        } catch (EmptyTimingDetailsException e) {
            System.out.println("Oops! Please indicate the time for the deadline you want to add!");
        }
    }

    public void addEvent(String userInput) {
        try {
            String description = getDescription(userInput);
            String at = getTimingDetails(userInput);
            taskList.add(new Event(description, taskUniqueID, at));
            printMessageForAdding(taskList.get(taskList.size() - 1));
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please give a description for the event you want to add!");
        } catch (EmptyTimingDetailsException e) {
            System.out.println("Oops! Please indicate the time for the event you want to add!");
        }
    }

    public void markOrUnmarkTask(String userInput) {
        try {
            String[] words = userInput.split(" ");
            String command = words[0];
            String taskNumber = words[1];
            int number = Integer.parseInt(taskNumber) - 1;
            if (command.equals("mark")) {
                taskList.get(number).setIsMarked();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                taskList.get(number).unsetIsMarked();
                System.out.println("Nice! I've unmarked this task as done:");
            }
            System.out.println(number + 1 + "." + taskList.get(number).toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Oops! The task number given is not in range. Try again!");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Please give an integer value. Try again!");
        }
    }

    public void deleteTask(String userInput) {
        try {
            String[] words = userInput.split(" ");
            String taskNumber = words[1];
            int number = Integer.parseInt(taskNumber) - 1;
            System.out.println("Noted. I've removed this task:\n" + taskList.get(number).toString());
            taskList.remove(number);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Oops! The task number given is not in range. Try again!");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Please give an integer value. Try again!");
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
        if (indexOfSlash != -1) {
            return userInput.substring(indexOfSlash + 4);
        } else {
            throw new EmptyTimingDetailsException();
        }
    }

    private void printMessageForAdding(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.size() + "." + task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}