package duke.application;

import duke.entity.Deadline;
import duke.entity.Event;
import duke.entity.Todo;
import duke.entity.Task;
import duke.exception.DukeException;
import duke.exception.IllegalDeadlineException;
import duke.exception.IllegalDeleteException;
import duke.exception.IllegalEventException;
import duke.exception.IllegalTodoException;

import duke.database.TaskDatabase;



import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;



public class Duke {
    public static final String taskAddedSuccessfully = "Got it, Olivia has added this task:";

    public static void main(String[] args) throws IOException {
        printWelcomeMessage();
        ArrayList<Task> taskList;
        taskList = TaskDatabase.getInstance().read("src/main/java/taskdata.txt");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            try {
                System.out.println("-----------------------------");
                if (userInput.equals("list")) {
                    displayTaskListMenu();
                    displayAllTasks(taskList);
                } else if (userInput.startsWith("unmark")) {
                    try {
                        Task task = getTask(userInput, taskList);
                        task.markAsNotDone();
                        System.out.println(task);
                    } catch (NullPointerException e) {
                        System.out.println("you have failed to unmark the task");
                    }
                } else if (userInput.startsWith("mark")) {
                    try {
                        Task task = getTask(userInput, taskList);
                        task.markAsDone();
                        System.out.println(task);
                    } catch (NullPointerException e) {
                        System.out.println("you have failed to mark the task");
                    }
                } else if (userInput.startsWith("deadline")) {
                    try {
                        Deadline deadline = getDeadline(userInput);
                        taskList.add(deadline);
                        System.out.println(taskAddedSuccessfully);
                        System.out.println(deadline);
                        printTotalNumberOfTasks(taskList);
                    } catch (IllegalDeadlineException e) {
                        System.out.println("OOPS!!! Deadline must have a description and due date.");
                    }
                } else if (userInput.startsWith("event")) {
                    try {
                        Event event = getEvent(userInput);
                        taskList.add(event);
                        System.out.println(taskAddedSuccessfully);
                        System.out.println(event);
                        printTotalNumberOfTasks(taskList);
                    } catch (IllegalEventException e) {
                        System.out.println("OOPS!!! Event must have a description and date");
                    }
                } else if (userInput.startsWith("todo")) {
                    try {
                        Todo todo = getTodo(userInput);
                        taskList.add(todo);
                        System.out.println(taskAddedSuccessfully);
                        System.out.println(todo);
                        printTotalNumberOfTasks(taskList);
                    } catch (IllegalTodoException e) {
                        System.out.println("OOPS!!! The description of todo cannot be empty");
                    }
                } else if (userInput.startsWith("delete")) {
                    try {
                        int indexToDelete = getIndexToDelete(userInput);
                        Task task = taskList.get(indexToDelete);
                        taskList.remove(indexToDelete);
                        System.out.println("Noted. Olivia has removed this task:");
                        System.out.println(task);
                        printTotalNumberOfTasks(taskList);
                    } catch (IllegalDeleteException e) {
                        System.out.println("To delete a task, please enter in the following format: delete <Task Number>");
                    } catch (NumberFormatException e) {
                        System.out.println("You must enter a task number to delete it");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("The task number you entered does not exist");
                    }
                }
                userInput = sc.nextLine();
                checkUserInputValidity(userInput);
            } catch (DukeException e) {
                printIllegalCommandErrorMessage();
            }
        }
        TaskDatabase.getInstance().save("src/main/java/taskdata.txt",taskList);
        printGoodbyeMessage();
    }

    private static int getIndexToDelete(String userInput) throws IllegalDeleteException {
        String[] tokenArray = stringToToken(userInput," ");
        if (tokenArray.length != 2) {
            throw new IllegalDeleteException();
        }
        return Integer.parseInt(tokenArray[1].trim()) - 1;
    }


    private static void printIllegalCommandErrorMessage() {
        System.out.println("OOPS you have confused Olivia, please enter a valid command");
        System.out.println("Please make sure your command starts with todo, event, deadline, mark,"
                + " unmark or equals list or bye");
    }

    private static void checkUserInputValidity(String userInput) throws DukeException {
        //checking validity of commands which uses "start with"
        List<String> validCommandsForStartsWith = Arrays.asList("todo", "event","deadline","mark","unmark","delete");
        boolean isStartWithCommandValid = validCommandsForStartsWith.stream().anyMatch(userInput::startsWith);
        //checking validity of commands which uses "equals"
        List<String> validCommandsForEquals = Arrays.asList("list","bye");
        boolean isEqualsToCommandValid = validCommandsForEquals.stream().anyMatch(userInput::equals);
        //if either 1 is true -> don't throw exception, else throw an exception
        if (!(isStartWithCommandValid ^ isEqualsToCommandValid)) {
            throw new DukeException();
        }
    }

    private static void printTotalNumberOfTasks(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static Todo getTodo(String userInput) throws IllegalTodoException {
        String[] tokenArray = stringToToken(userInput,"todo");
        if (tokenArray.length < 2) {
            throw new IllegalTodoException();
        }
        String description = tokenArray[1];
        //Todo todo = new Todo(description);
        return new Todo(description);
    }

    private static Event getEvent(String userInput) throws IllegalEventException {
        if (!userInput.contains("/at")) {
            throw new IllegalEventException();
        }
        String[] tokenArray = stringToToken(userInput,"/at");
        if (tokenArray.length < 2) {
            throw new IllegalEventException();
        }
        String duration = tokenArray[1].trim();
        String description = tokenArray[0].split("event")[1].trim();
        if (description.isBlank()) {
            throw new IllegalEventException();
        }
        //Event event = new Event(description,duration);
        return new Event(description,duration);
    }

    private static Deadline getDeadline(String userInput) throws IllegalDeadlineException {
        if (!userInput.contains("/by")) {
            throw new IllegalDeadlineException();
        }
        String[] tokenArray = stringToToken(userInput,"/by");
        if (tokenArray.length < 2) {
            throw new IllegalDeadlineException();
        }
        String by = tokenArray[1].trim();
        String description = tokenArray[0].split("deadline")[1].trim();
        if (description.isBlank()) {
            throw new IllegalDeadlineException();
        }
        //Deadline deadline = new Deadline(description,by);
        return new Deadline(description,by);
    }

    private static Task getTask(String userInput,ArrayList<Task> taskList) {
        try {
            int taskIndex = getTaskIndex(userInput);
            return taskList.get(taskIndex);
        } catch (NumberFormatException e) {
            System.out.print("OOPS!!!! Invalid task number,");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("OOPS!!!! The task number you entered doesn't exist, ");
        }
        return null;
    }

    private static int getTaskIndex(String userInput) {
        String[] tokenArray = stringToToken(userInput," ");
        return Integer.parseInt(tokenArray[1]) - 1; //index is raw value - 1
    }

    private static String[] stringToToken(String userInput,String delimiter) {
        return userInput.split(delimiter);
    }

    private static void displayAllTasks(ArrayList<Task> taskList) {
        int i = 1;
        for (Task task : taskList) {
            System.out.print(i + ".");
            System.out.println(task);
            ++i;
        }
        System.out.println("-----------------------------");
    }

    private static void displayTaskListMenu() {
        System.out.println("Olivia presents you a list of tasks for you to do:");
    }

    private static void printGoodbyeMessage() {
        System.out.println("-----------------------------");
        System.out.println("Bye. Hope to see you again soon! With Love, Olivia");
        System.out.println("-----------------------------");
    }

    private static void printWelcomeMessage() {
        String botLogo = "  ___                             __\n"
                       + "|  _  |  |   |  \\        / |     /  \\\n"
                       + "| | | |  |   |   \\      /  |    / _  \\\n"
                       + "| | | |  |   |    \\    /   |   / /_\\  \\\n"
                       + "| |_| |  |   |     \\  /    |  /   _    \\\n"
                       + "| ___ |  |__ |      \\/     | /___/ \\____\\\n";
        System.out.println("Hello from\n" + botLogo);
        System.out.println("-----------------------------");
        System.out.println("Greetings! I'm Olivia, your lovely personal assistant.");
        System.out.println("What can Olivia do for you my love?");
        System.out.println("-----------------------------");
    }
}

