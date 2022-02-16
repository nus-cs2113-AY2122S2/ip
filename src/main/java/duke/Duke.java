package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;

import java.io.*;
import java.util.Scanner;

public class Duke {
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";

    private static final String TASKLIST_FILE_PATH = "data.txt";

    private static TaskList taskList = new TaskList();

    public static void printLine() {
        System.out.println("\t" + "-----------------------------------------");
    }

    public static void greeting() {
        String logo = "\t" + "  ____        _        \n"
                + "\t" + " |  _ \\ _   _| | _____ \n"
                + "\t" + " | | | | | | | |/ / _ \\\n"
                + "\t" + " | |_| | |_| |   <  __/\n"
                + "\t" + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + " Hello from\n" + logo);
        printLine();

        System.out.println("\t" + " Hi! This is Duke!");
        System.out.println("\t" + " I'm glad to be at your service.");
        System.out.println("\t" + " What can I help you with?");
        printLine();
    }

    public static void bye() {
        System.out.println("\t" + " Bye. Hope to see you again soon!");
        printLine();
    }

    public static void ableToAddTask(Task newtask, TaskList taskList) {
        System.out.println("\t" + " Got it. I've added this task:");
        System.out.println("\t" + "\t" + newtask);
        System.out.println("\t" + " Now you have " + taskList.getSize() + " tasks in the list.");
//        try{
//            writeToFile(TASKLIST_FILE_PATH, );
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
        printLine();
    }

    public static String formatTask(Task task) {
        String taskType = task.getType();
        int status = task.getStatus() ? 1 : 0;
        String taskDescription = task.getDescription();
        String taskDate = task.getDate();
        String textToWrite = null;
        switch (taskType) {
        case "T":
            textToWrite = "T," + status + "," + taskDescription;
            break;
        case "D":
            textToWrite = "D," + status + "," + taskDescription + "," + taskDate;
            break;
        case "E":
            textToWrite = "E," + status + "," + taskDescription + "," + taskDate;
            break;
        }
        return textToWrite;
    }

    public static void addTodo(String taskDetail) throws DukeException {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, TODO_COMMAND);
            Task newTask = new Todo(taskDetail);
            taskList.addTask(newTask);
            ableToAddTask(newTask, taskList);
        } catch (DukeException e) {
            System.out.println(e);
            guide();
        }
    }

    public static void addDeadline(String taskDetail) throws DukeException {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, DEADLINE_COMMAND);
            int separatorIndex = taskDetail.indexOf("/by");
            checkKeyword(separatorIndex);
            Task newTask = new Deadline(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4));
            taskList.addTask(newTask);
            ableToAddTask(newTask, taskList);
        } catch (DukeException e) {
            System.out.println(e);
            guide();
        }
    }

    public static void addEvent(String taskDetail) throws DukeException {
        printLine();
        try {
            checkTaskDetailEmpty(taskDetail, EVENT_COMMAND);
            int separatorIndex = taskDetail.indexOf("/at");
            checkKeyword(separatorIndex);
            Task newTask = new Event(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4));
            taskList.addTask(newTask);
            ableToAddTask(newTask, taskList);
        } catch (DukeException e) {
            System.out.println(e);
            guide();
        }
    }

    public static void checkKeyword(int indexOfKeyword) throws DukeException {
        if (indexOfKeyword == -1) {
            throw new DukeException("You should give a time. Please refer to the command guide below.");
        }
    }

    public static void checkTaskDetailEmpty(String taskDetail, String command) throws DukeException {
        if (taskDetail.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }
    }

    public static void checkCommand() throws DukeException {

    }

    public static void guide() {
        System.out.println("\t" + " use \"list\" to show the task list");
        System.out.println("\t" + " use \"todo task\" to add a task without any date/time attached to it\"");
        System.out.println("\t" + " use \"deadline task /by time\" to add a task that need to be done before a specific time/date");
        System.out.println("\t" + " use \"event task /at time\" to add a task that tasks that start at a specific time and ends at a specific time");
        System.out.println("\t" + " use \"mark taskIndex\" to mark that task as done");
        System.out.println("\t" + " use \"unmark taskIndex\" to mark that task as not done");
        System.out.println("\t" + " use \"delete taskIndex\" to ..........");
        System.out.println("\t" + " use \"bye\" to exit the chatbot");
        printLine();
    }

    public static void checkFileExists(String filePath) throws IOException, DukeException {
        File f = new File(filePath);
        if (!f.exists()) {
            FileOutputStream newFile = new FileOutputStream(filePath);
        }
        readFile(filePath);
    }


    public static void readFile(String filePath) throws DukeException, FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int separatorIndex;
        while (s.hasNextLine()) {
            String[] taskInfo = s.nextLine().split(",");
            String taskDetail = null;
            switch (taskInfo[0]) {
            case "T":
                taskDetail = taskInfo[2];
                taskList.addTask(new Todo(taskDetail));
                break;
            case "D":
                taskDetail = taskInfo[2] + " /by " + taskInfo[3];
                separatorIndex = taskDetail.indexOf("/by");
                taskList.addTask(new Deadline(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4)));
                break;
            case "E":
                taskDetail = taskInfo[2] + " /at " + taskInfo[3];
                separatorIndex = taskDetail.indexOf("/at");
                taskList.addTask(new Event(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4)));
                break;
            }
            if (taskInfo[1].equals("1")) {
                taskList.markDone(taskList.getSize() - 1);
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        greeting();
        checkFileExists(TASKLIST_FILE_PATH);
        processInput();
        try {
            FileWriter fw = new FileWriter(TASKLIST_FILE_PATH);

            for (int i = 0; i < taskList.getSize(); i++) {
                fw.write(formatTask(taskList.getTask(i)) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void processInput() throws DukeException {
        Scanner in = new Scanner(System.in);
        String textIn = in.nextLine();
        String taskDetail;
        String command;
        while (!textIn.toLowerCase().equals(BYE_COMMAND)) {
            if (textIn.equals(LIST_COMMAND)) {
                taskList.printTaskList();
            } else if (textIn.startsWith(MARK_COMMAND)) {
                taskList.markDone(Integer.parseInt(textIn.substring(4).trim()));
            } else if (textIn.startsWith(UNMARK_COMMAND)) {
                taskList.unmark(Integer.parseInt(textIn.substring(6).trim()));
            } else if (textIn.startsWith(TODO_COMMAND)) {
                taskDetail = textIn.substring(4);
                addTodo(taskDetail);
            } else if (textIn.startsWith(DEADLINE_COMMAND)) {
                taskDetail = textIn.substring(8);
                addDeadline(taskDetail);
            } else if (textIn.startsWith(EVENT_COMMAND)) {
                taskDetail = textIn.substring(5);
                addEvent(taskDetail);
            } else if (textIn.startsWith(DELETE_COMMAND)) {
                taskList.deleteTask(Integer.parseInt(textIn.substring(6).trim()));
            } else {
                printLine();
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t Please refer to the command guide below.\n");
                guide();
            }
            in = new Scanner(System.in);
            textIn = in.nextLine();


        }
        bye();
    }

}

