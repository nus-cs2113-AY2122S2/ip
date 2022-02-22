package duke.taskmanagement;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private int taskUniqueID = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static TaskRecorder taskRecorder = new TaskRecorder();

    public TaskManager() {
        try {
            ArrayList<String[]> fileData = taskRecorder.loadData();
            for (String[] data : fileData) {
                String input = data[1];
                String command = data[1].split(" ")[0];
                String description = getDescription(input);
                switch (command) {
                case "todo":
                    tasks.add(new Todo(description, taskUniqueID));
                    break;
                case "deadline":
                    String by = getTimingDetails(input);
                    tasks.add(new Deadline(description, taskUniqueID, by));
                    break;
                case "event":
                    String at = getTimingDetails(input);
                    tasks.add(new Event(description, taskUniqueID, at));
                    break;
                }
                if (data[0].equals("1")) {
                    tasks.get(tasks.size() - 1).setIsMarked();
                }
                taskUniqueID++;
            }
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong while loading the data!");
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please type in a description!");
        } catch (EmptyTimingDetailsException e) {
            System.out.println("Oops! Please indicate the time for the deadline you want to add!");
        }
    }

    public void listTasks() {
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        } else {
            System.out.println("Wow, such empty");
        }
    }

    public void addTodo(String userInput) {
        try {
            String description = getDescription(userInput);
            tasks.add(new Todo(description, taskUniqueID));
            printMessageForAdding(tasks.get(tasks.size() - 1));
            taskRecorder.addData(userInput);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please type in a description!");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong writing to the file!");
        }
    }

    public void addDeadline(String userInput) {
        try {
            String description = getDescription(userInput);
            String by = getTimingDetails(userInput);
            tasks.add(new Deadline(description, taskUniqueID, by));
            printMessageForAdding(tasks.get(tasks.size() - 1));
            taskRecorder.addData(userInput);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please give a description for the deadline you want to add!");
        } catch (EmptyTimingDetailsException e) {
            System.out.println("Oops! Please indicate the time for the deadline you want to add!");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong writing to the file!");
        }
    }

    public void addEvent(String userInput) {
        try {
            String description = getDescription(userInput);
            String at = getTimingDetails(userInput);
            tasks.add(new Event(description, taskUniqueID, at));
            printMessageForAdding(tasks.get(tasks.size() - 1));
            taskRecorder.addData(userInput);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            System.out.println("Oops! Please give a description for the event you want to add!");
        } catch (EmptyTimingDetailsException e) {
            System.out.println("Oops! Please indicate the time for the event you want to add!");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong writing to the file!");
        }
    }

    public void markOrUnmarkTask(String userInput) {
        try {
            String[] words = userInput.split(" ");
            String command = words[0];
            String taskNumber = words[1];
            int number = Integer.parseInt(taskNumber) - 1;
            if (command.equals("mark")) {
                tasks.get(number).setIsMarked();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks.get(number).unsetIsMarked();
                System.out.println("Nice! I've unmarked this task as done:");
            }
            taskRecorder.markOrUnmarkData(number);
            System.out.println(number + 1 + "." + tasks.get(number).toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Oops! The task number given is not in range. Try again!");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Please give an integer value. Try again!");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong writing to the file to mark or unmark a task!");
        }
    }

    public void deleteTask(String userInput) {
        try {
            String[] words = userInput.split(" ");
            String taskNumber = words[1];
            int number = Integer.parseInt(taskNumber) - 1;
            System.out.println("Noted. I've removed this task:\n" + tasks.get(number).toString());
            tasks.remove(number);
            taskRecorder.deleteData(number);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Oops! The task number given is not in range. Try again!");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Please give an integer value. Try again!");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong with deleting a task from the file");
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

    private void printMessageForAdding(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.size() + "." + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}