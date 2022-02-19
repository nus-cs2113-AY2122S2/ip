package duke.taskmanagement;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskManager {
    private final static int MAXTASKCOUNT = 100;
    private int taskCount = 0;
    private Task[] taskList = new Task[MAXTASKCOUNT];
    private TaskRecorder taskRecorder = new TaskRecorder();

    public void addTodo(String userInput) {
        try {
            String description = getDescription(userInput);
            taskList[taskCount] = new Todo(description, taskCount);
            printMessageForAdding(taskList[taskCount], taskCount);
            taskCount++;
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please type in a description!");
        }
    }

    public void addDeadline(String userInput) {
        try {
            String description = getDescription(userInput);
            String by = getTimingDetails(userInput);
            taskList[taskCount] = new Deadline(description, taskCount, by);
            printMessageForAdding(taskList[taskCount], taskCount);
            taskCount++;
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
            taskList[taskCount] = new Event(description, taskCount, at);
            printMessageForAdding(taskList[taskCount], taskCount);
            taskCount++;
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please give a description for the event you want to add!");
        } catch (EmptyTimingDetailsException e) {
            System.out.println("Oops! Please indicate the time for the event you want to add!");
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
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException();
        } catch (EmptyDescriptionException e) {
            throw new EmptyDescriptionException();
        }
    }

    private String getTimingDetails(String userInput) throws EmptyTimingDetailsException{
        int indexOfSlash = userInput.indexOf("/");
        if (indexOfSlash != -1) {
            String timingDetails = userInput.substring(indexOfSlash + 4);
            return timingDetails;
        } else {
            throw new EmptyTimingDetailsException();
        }
    }

    private void printMessageForAdding(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.print((taskCount + 1) + ".");
        System.out.println(task.toString());
        System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
    }

    public void listTasks() {
        if (taskCount > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(i + 1 + taskList[i].toString());
            }
        } else if (taskCount == 0) {
            System.out.println("Wow, such empty");
        }
    }

    public void markOrUnmarkTask(String userInput) {
        try {
            String[] words = userInput.split(" ");
            String command = words[0];
            String taskNumber = words[1];
            int number = Integer.parseInt(taskNumber) - 1;
            if (command.equals("mark")) {
                taskList[number].setIsMarked();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                taskList[number].unsetIsMarked();
                System.out.println("Nice! I've unmarked this task as done:");
            }
            System.out.println((taskList[number].getTaskNumber() + 1) + "." + taskList[number].toString());
        } catch (NullPointerException e) {
            System.out.println("Oops! The task number given is not in range. Try again!");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Please give an integer value. Try again!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops! Please give an integer value. Try again!");
        }
    }
}

