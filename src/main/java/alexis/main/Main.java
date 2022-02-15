package alexis.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import alexis.exceptions.EmptyListException;
import alexis.exceptions.IllegalTodoException;
import alexis.task.Task;
import alexis.task.Deadline;
import alexis.task.Event;
import alexis.task.Todo;

public class Main {
    //Constant(s) used in multiple classes
    public static final String BORDER_LINE = "---------------------------------------------------------------";

    //Constants used in this Main class
    public static final String HAPPY_FACE = "\uD83D\uDE04";
    public static final String SAD_FACE = "\uD83D\uDE41";
    public static final String GREETING_MESSAGE = "Hello! I'm Alexis, your trusty helper!\n"
            + "What can I do for you? " + HAPPY_FACE +"\n\n"
            + "Hint: You may use these commands to navigate around:\n"
            + "[list] [todo] [deadline] [event] [mark] [unmark] [delete] [bye]";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String DISPLAY_TASK_MESSAGE = "Here are the tasks in your list:";
    public static final String EMPTY_LIST_MESSAGE = "Your list is empty. You have no tasks now.";
    public static final String INVALID_INPUT_MESSAGE = SAD_FACE
            + " Oops!! I'm sorry, but I don't know what that means :-(";
    //Exception messages
    public static final String TODO_EXCEPTION_MESSAGE_TEXT = " Oops!! The description of a todo cannot be empty.";
    public static final String DEADLINE_EXCEPTION_MESSAGE_TEXT_ONE = " Oops!! The description of a deadline"
            + " cannot be empty.";
    public static final String DEADLINE_EXCEPTION_MESSAGE_TEXT_TWO = " Oops!! The description of a deadline "
            + "has to have the format:\n   (task description) /by (deadline time)";
    public static final String EVENT_EXCEPTION_MESSAGE_TEXT_ONE = " Oops!! The description of an event "
            + "cannot be empty.";
    public static final String EVENT_EXCEPTION_MESSAGE_TEXT_TWO = " Oops!! The description of an event "
            + "has to have the format:\n   (task description) /at (event time)";
    public static final String MARK_EXCEPTION_TEXT_ONE = " Oops!! Please input a task number after 'mark'";
    public static final String MARK_EXCEPTION_TEXT_TWO = " Oops!! Please input a valid task number to mark";
    public static final String UNMARK_EXCEPTION_TEXT_ONE = " Oops!! Please input a task number after 'unmark'";
    public static final String UNMARK_EXCEPTION_TEXT_TWO = " Oops!! Please input a valid task number to unmark";

    //Constants used in the Task class
    public static final String ADD_NEW_TASK_MESSAGE = "Got it! I've added this task:";
    public static final String MARK_AS_DONE_MESSAGE = "Great! I've marked this task as done:";
    public static final String MARK_AS_UNDONE_MESSAGE = "Ok. I've marked this task as not done yet:";

    public static void greet() {
        System.out.println(BORDER_LINE + "\n" + GREETING_MESSAGE + "\n" + BORDER_LINE);
    }

    public static void exit() {
        System.out.println(BORDER_LINE + "\n" + GOODBYE_MESSAGE + "\n" + BORDER_LINE);
    }

    private static void invalid_input() {
        System.out.println(BORDER_LINE + "\n" + INVALID_INPUT_MESSAGE + "\n" + BORDER_LINE);
    }

    private static void todoExceptionMessage() {
        System.out.println(BORDER_LINE + "\n" + SAD_FACE + TODO_EXCEPTION_MESSAGE_TEXT + "\n" + BORDER_LINE);
    }

    private static void deadlineExceptionMessage(String deadlineExceptionText) {
        System.out.println(BORDER_LINE + "\n" + SAD_FACE + deadlineExceptionText + "\n" + BORDER_LINE);
    }

    private static void eventExceptionMessage(String eventExceptionText) {
        System.out.println(BORDER_LINE + "\n" + SAD_FACE + eventExceptionText + "\n" + BORDER_LINE);
    }

    private static void markExceptionMessage(String markExceptionText) {
        System.out.println(BORDER_LINE + "\n" + SAD_FACE + markExceptionText + "\n" + BORDER_LINE);
    }

    private static void unmarkExceptionMessage(String unmarkExceptionText) {
        System.out.println(BORDER_LINE + "\n" + SAD_FACE + unmarkExceptionText + "\n" + BORDER_LINE);
    }

    public static void checkListValidity(int numOfTasks) throws EmptyListException {
        if (numOfTasks == 0) {
            throw new EmptyListException();
        }
    }

    public static void displayList(Task[] tasks, int numOfTasks) {
        System.out.println(BORDER_LINE);
        try {
            checkListValidity(numOfTasks);
            System.out.println(DISPLAY_TASK_MESSAGE);
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println((i + 1) + ".[" + tasks[i].typeOfTask() + "][" + tasks[i].getStatusIcon() + "] "
                        + tasks[i].getFullDescription());
            }
        } catch (EmptyListException e) {
            System.out.println(EMPTY_LIST_MESSAGE);
        }
        System.out.println(BORDER_LINE);
    }

    public static void checkTodoInputValidity(String input) throws IllegalTodoException {
        if (input.substring(5).equals("")) {
            throw new IllegalTodoException();
        }
    }

    public static int todo(Task[] tasks, int numOfTasks, String input) {
        try {
            checkTodoInputValidity(input);
            tasks[numOfTasks] = new Todo(input.substring(5));
            numOfTasks++;
        } catch (StringIndexOutOfBoundsException | IllegalTodoException e) {
            todoExceptionMessage();
        }
        return numOfTasks;
    }
    
    public static int deadline(Task[] tasks, int numOfTasks, String input) {
        try {
            String[] deadlineDescriptionSplitArr = input.substring(9).split(" /by ");
            tasks[numOfTasks] = new Deadline(deadlineDescriptionSplitArr[0], deadlineDescriptionSplitArr[1]);
            numOfTasks++;
        } catch (StringIndexOutOfBoundsException e) {
            deadlineExceptionMessage(DEADLINE_EXCEPTION_MESSAGE_TEXT_ONE);
        } catch (ArrayIndexOutOfBoundsException e) {
            deadlineExceptionMessage(DEADLINE_EXCEPTION_MESSAGE_TEXT_TWO);
        }
        return numOfTasks;
    }    

    public static int event(Task[] tasks, int numOfTasks, String input) {
        try {
            String[] eventDescriptionSplitArr = input.substring(6).split(" /at ");
            tasks[numOfTasks] = new Event(eventDescriptionSplitArr[0], eventDescriptionSplitArr[1]);
            numOfTasks++;
        } catch (StringIndexOutOfBoundsException e) {
            eventExceptionMessage(EVENT_EXCEPTION_MESSAGE_TEXT_ONE);
        } catch (ArrayIndexOutOfBoundsException e) {
            eventExceptionMessage(EVENT_EXCEPTION_MESSAGE_TEXT_TWO);
        }
        return numOfTasks;
    }

    public static void mark(Task[] tasks, String[] arrOfInputStrings) {
        try {
            int inputTaskNumber = Integer.parseInt(arrOfInputStrings[1]) - 1;
            tasks[inputTaskNumber].markAsDone();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            markExceptionMessage(MARK_EXCEPTION_TEXT_ONE);
        } catch (NullPointerException e) {
            markExceptionMessage(MARK_EXCEPTION_TEXT_TWO);
        }
    }

    public static void unmark(Task[] tasks, String[] arrOfInputStrings) {
        try {
            int inputTaskNumber = Integer.parseInt(arrOfInputStrings[1]) - 1;
            tasks[inputTaskNumber].markAsUndone();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            unmarkExceptionMessage(UNMARK_EXCEPTION_TEXT_ONE);
        } catch (NullPointerException e) {
            unmarkExceptionMessage(UNMARK_EXCEPTION_TEXT_TWO);
        }
    }

    private static void writeToFile(String filePath, int numOfTasks, Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < numOfTasks; i++) {
            fw.write(tasks[i].typeOfTask() + " | " + tasks[i].getStatusIcon() + " | "
                    + tasks[i].getFullDescription() + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String filepath = "list.txt";

        try {
            File file = new File(filepath);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred in creating a file.");
            e.printStackTrace();
        }

        int taskCounter = 0;
        Task[] tasks = new Task[100];

        greet();
        String input = in.nextLine();

        while (!input.equals("bye")) {

            String[] arrOfInputStrings = input.split(" ");

            switch(arrOfInputStrings[0]) {
            case "list":
                displayList(tasks, taskCounter);
                break;
            case "todo":
                taskCounter = todo(tasks, taskCounter, input);
                break;
            case "deadline":
                taskCounter = deadline(tasks, taskCounter, input);
                break;
            case "event":
                taskCounter = event(tasks, taskCounter, input);
                break;
            case "mark":
                mark(tasks, arrOfInputStrings);
                break;
            case "unmark":
                unmark(tasks, arrOfInputStrings);
                break;
            default:
                invalid_input();
                break;
            }

            try {
                new FileWriter(filepath, false).close();    //deletes all content in the file
                writeToFile(filepath, taskCounter, tasks);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            input = in.nextLine();
        }

        exit();

    }

}
