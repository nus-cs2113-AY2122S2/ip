package Eliz;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Eliz {
    public static void printTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int numToPrint = i + 1;
            System.out.println(numToPrint + ".[" + tasks.get(i).getTaskType() + "]" + "[" + tasks.get(i).getStatusIcon() + "] "
                    + tasks.get(i).description);
        }
    }

    public static void markATask(String line, ArrayList<Task> tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        tasks.get(taskNumInt - 1).setAsDone();
        System.out.println("[" + tasks.get(taskNumInt - 1).getTaskType() + "]" + "[X] "
                + tasks.get(taskNumInt - 1).description);
    }

    public static void unmarkATask(String line, ArrayList<Task> tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        tasks.get(taskNumInt - 1).setAsNotDone();
        System.out.println("[" + tasks.get(taskNumInt - 1).getTaskType() + "]" + "[ ] "
                + tasks.get(taskNumInt - 1).description);
    }


    public static Task createTask(String line) {
        Task t;
        String[] splitTwoSections = line.split(" ", 2); //0: task type, 1: rest of the words
        String taskType = splitTwoSections[0];
        switch (taskType) {
        case "todo":
            t = createTodo(taskType, splitTwoSections[1]);
            break;
        case "deadline":
            t = createDeadline(taskType, splitTwoSections[1]);
            break;
        case "event":
            t = createEvent(taskType, splitTwoSections[1]);
            break;
        default:
            return null;
        }
        return t;
    }

    public static Task createTodo(String taskType, String line) {
        Todo newTodo = new Todo(line);
        return newTodo;
    }

    public static Task createDeadline(String taskType, String line) {
        String newDescription = line;
        if (line.contains("/")) {
            String[] splitTwoSections = line.split("/", 2);
            newDescription = splitTwoSections[0] + "(" + splitTwoSections[1] + ")";
        }
        Deadline newDeadline = new Deadline(newDescription);
        return newDeadline;
    }

    public static Task createEvent(String taskType, String line) {
        String newDescription = line;
        if (line.contains("/")) {
            String[] splitTwoSections = line.split("/", 2);
            newDescription = splitTwoSections[0] + "(" + splitTwoSections[1] + ")";
        }
        Event newEvent = new Event(newDescription);
        return newEvent;
    }

    public static void botIntroduction() {
        String logo = " ____    __       __     ______ \n"
                + "|  __|  |  |     |  |   |___  /\n"
                + "| |__   |  |     |  |      / /  \n"
                + "| |__|  |  |     |  |     / /  \n"
                + "| |__   |  |__   |  |    / /___\n"
                + "|____|  |_____|  |__|   |______|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eliz");
        System.out.println("I am your personal task list recorder! If you need help, type [/help]");
        System.out.println("What task would you like me to note down for you?");
    }

    public static void markOrUnmark(String line, ArrayList<Task> tasks) {
        if (line.contains("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");
            unmarkATask(line, tasks);
        } else {
            System.out.println("Nice! I've marked this task as done:");
            markATask(line, tasks);
        }
    }

    public static void displayCommands() {
        System.out.println("Commands to add tasks: ");
        System.out.println("todo [TASK_NAME]");
        System.out.println("event [TASK_NAME]");
        System.out.println("deadline [TASK_NAME]/[DATE]");
        System.out.println(" ");
        System.out.println("Command to display list: ");
        System.out.println("list");
        System.out.println(" ");
        System.out.println("Commands for todo tasks: ");
        System.out.println("mark [TASK_LIST_NUMBER]");
        System.out.println("unmark [TASK_LIST_NUMBER]");
    }

    public static void delete(String line, ArrayList<Task> tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        String deletedTask = tasks.get(taskNumInt - 1).getTaskType();
        String deletedTaskDescription = tasks.get(taskNumInt - 1).description;
        tasks.remove(taskNumInt - 1);
        System.out.println("Noted I have deleted this task");
        System.out.println("[" + deletedTask + "]" + "[ ] " + deletedTaskDescription);
        System.out.println("You have " + tasks.size() + " tasks left in the list.");
    }

    public static void getInput(String line, ArrayList<Task> tasks, int taskCounter) throws ElizException {
        String[] breakTaskNames = breakTaskNames = line.split(" ", 2);
//        if (line.substring(line.length() - 1) == " ") { //in the case that the last character is a space
//            breakTaskNames[0] = line;
//            breakTaskNames[1] = null;
//        } else {
//            breakTaskNames = line.split(" ", 2);
//        }

        if ((breakTaskNames.length < 2)){
            String firstWord = breakTaskNames[0];
            switch (firstWord) {
            case "list" : printTasks(tasks);
                break;
            case "todo":
            case "deadline":
            case "event":
                System.out.println("OOPS!!! The description of a " + firstWord + " cannot be empty.");
                throw new ElizException();

            case "/help":
                displayCommands();
                break;
            default: //for mark, default, other weird spelling words
                System.out.println("OOPS!!! I'm sorry but I do not understand what you mean");
                throw new ElizException();
            }
        } else { //length of breakTaskNames is 2
            //correct inputs for todo, event, deadline and delete
            String firstWord = breakTaskNames[0];
            switch (firstWord) {
            case "mark":
            case "unmark":
                Eliz.markOrUnmark(line, tasks);
                break;
            case "delete":
                Eliz.delete(line, tasks);
                break;
            case "todo":
            case "event":
            case "deadline":
//            System.out.println("Index 0: " + breakTaskNames[0]);
//            System.out.println("Index 1: " + breakTaskNames[1]);
//            boolean isEmpty = false;
//            if (breakTaskNames[1] == null) {
//                isEmpty = true;
//            }
//            System.out.println(isEmpty);
                if (breakTaskNames[1] == null) {
                    System.out.println("OOPS!!! The description of a " + breakTaskNames[0] + " cannot be empty.");
                    throw new ElizException();
                } else {
                    Task t = createTask(line);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task ");
                    System.out.println(t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }
            }
        }
//        if (breakTaskNames[0].equalsIgnoreCase("todo") || breakTaskNames[0].equalsIgnoreCase("deadline")
//        || breakTaskNames[0].equalsIgnoreCase("event")) {
//                /** add line to todo, deadline, or event by creating the respective object */
//                Task t = createTask(line);
//                tasks.add(taskCounter, t);
//                taskCounter++;
//                System.out.println("Got it. I've added this task ");
//                System.out.println(t);
//                System.out.println("Now you have " + taskCounter + " tasks in the list.");
//        } else {
//            if (line.equalsIgnoreCase("list")) { //check if action is t
//                //echo or print tasks
//                printTasks(tasks);
//            } else if (line.contains("mark")) { //to check if todos are marked
//                Eliz.markOrUnmark(line, tasks);
//            } else if (line.contains("delete")) {
//                Eliz.delete(line, tasks);
//            } else {
//                System.out.println("OOPS!!! I'm sorry but I do not understand what you mean");
//                throw new ElizException();
//            }
//        }
//        if (!line.equalsIgnoreCase("list") && !line.contains("mark") && breakTaskNames.length < 2) {
//            System.out.println("OOPS!!! The description of a " + breakTaskNames[0] + " cannot be empty.");
//            throw new ElizException();
//        }
    }

    public static void main(String[] args) {
        /** Key Definitions */
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCounter = 0;
        Scanner in = new Scanner(System.in);
        botIntroduction(); //calls the introduction of the bot
        line = in.nextLine();
        try {
            while (!line.equalsIgnoreCase("bye")) { //while command to end is not entered
                getInput(line, tasks, taskCounter);
                taskCounter++;
                line = in.nextLine();
            }
            System.out.println("Bye. Hope to see you again soon!");
        } catch (ElizException e) {
        }
    }
}
