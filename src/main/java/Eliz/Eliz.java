package Eliz;

import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;


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
        System.out.println("What can I do for you?");
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

    /*To append to file whenever there is a change in tasks*/
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd);
        fw.close();
    }

    /*Deals with the input and calling methods to configure inputs*/
    public static void getInput(String line, ArrayList<Task> tasks, String filePath) throws ElizException, FileSystemNotFoundException, IOException {
        String[] breakTaskNames = line.split(" ", 2);
        if (breakTaskNames[0].equalsIgnoreCase("todo") || breakTaskNames[0].equalsIgnoreCase("deadline")
                || breakTaskNames[0].equalsIgnoreCase("event")) {
            /** add line to todo, deadline, or event by creating the respective object */
            Task t = createTask(line);
            tasks.add(t);
            System.out.println("Got it. I've added this task ");
            System.out.println(t);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            /*every time an object is added or edited, append to file*/
            try {
                writeToFile(filePath, tasks.get(tasks.size()-1).getTaskType() + "|" + tasks.get(tasks.size()-1).getStatusIcon() + "|"
                        + tasks.get(tasks.size()-1).getDescription() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } else {
            if (line.equalsIgnoreCase("list")) { //check if action is t
                //echo or print tasks
                printTasks(tasks);
            } else if (line.contains("mark")) { //to check if todos are marked
                Eliz.markOrUnmark(line, tasks);
            } else {
                System.out.println("OOPS!!! I'm sorry but I do not understand what you mean");
                throw new ElizException();
            }
        }
        if (!line.equalsIgnoreCase("list") && !line.contains("mark") && breakTaskNames.length < 2) {
            System.out.println("OOPS!!! The description of a " + breakTaskNames[0] + " cannot be empty.");
            throw new ElizException();
        }

    }

    public static void deleteFileBeforeWriting(String filePath) throws IOException{
        Files.delete(Paths.get(filePath));
    }

    public static void createTasksFromFileContents(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath); //helps to create a file for the give file path
        Scanner s = new Scanner(f); //create scanner using file as a source
        int taskCounter = 0;
        Task input;
        while (s.hasNext()) { //while the file is not empty
            String line = s.nextLine();
            String[] arrayOfTaskComponents = line.split("|", 3);
            arrayOfTaskComponents[2] = arrayOfTaskComponents[2].substring(1);
            if (arrayOfTaskComponents[0].contains("T")) {
                input = new Todo(arrayOfTaskComponents[2]);
                if (arrayOfTaskComponents[1].contains("X")) {
                    input.setAsDone();
                }
            } else if (arrayOfTaskComponents[0].contains("D")) {
                input = new Deadline(arrayOfTaskComponents[2]);
            } else if (arrayOfTaskComponents[0].contains("E")) {
                input = new Event(arrayOfTaskComponents[2]);
            } else {
                return; //error
            }
            tasks.add(input);
            taskCounter++;
        }
    }

    public static void main(String[] args) {
        /** Key Definitions */
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCounter = 0;
        Scanner in = new Scanner(System.in);
        botIntroduction(); //calls the introduction of the bot
        line = in.nextLine();
        String filePath = "./task_list.txt";
        /* to retrieve content from file to fill up tasks array*/
        try {
            createTasksFromFileContents(filePath, tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        try {
            while (!line.equalsIgnoreCase("bye")) { //while command to end is not entered
                getInput(line, tasks, filePath);
                taskCounter = tasks.size();
                line = in.nextLine();
            }
            System.out.println("Bye. Hope to see you again soon!");
        } catch (ElizException e) {
        } catch (IOException e) {
        }
    }
}
