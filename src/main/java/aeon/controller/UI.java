package aeon.controller;

import java.util.ArrayList;

public class UI {

    public static final String TASK_NOT_FOUND =
            "Task not found! Perhaps try listing out the available tasks first...";
    public static final String INVALID_INTEGER_MSG = "Please type in a valid integer!";
    public static final String TODO_DESC_ERROR = "Description of TODO cannot be empty!!!";
    public static final String DEADLINE_FORMAT_ERR =
            "Please try again in this format: deadline <description> /by <date>";
    public static final String EVENT_FORMAT_ERR =
            "Please try again in this format: event <description> /at <date>";
    public static final String INVALID_COMMAND = "Not sure what you were trying to do...";
    public static final String TASK_ADDED = "Task added!";
    public static final String CONGRATULATIONS_MSG = "Congrats on completing this task!";
    public static final String MARK_UNDONE = "Alright, marked as undone!";
    public static final String NO_TASKS = "No tasks!";
    public static final String FILE_PATH = "./data/tasklist.txt";
    public static final String DIR_PATH = "./data/";
    public static final String CREATE_FILE_FAILED = "Failed to create file to store task!";
    public static final int COMMAND_WORD = 0;
    public static final String TASK_LIST = "list";
    public static final String TASK_UNMARK = "unmark";
    public static final String TASK_MARK = "mark";
    public static final String TASK_TODO = "todo";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_EVENT = "event";
    public static final String TASK_DELETE = "delete";
    public static final String TASK_FIND = "find";
    public static final int TASK_TYPE = 0;
    public static final String TASKTYPE_TODO = "T";
    public static final String TASKTYPE_DEADLINE = "D";
    public static final String TASKTYPE_EVENT = "E";
    public static final String USER_BYE = "bye";
    public static final String TASK_MARKED = "X";
    public static final int TARGET_WORD = 1;
    public static final String EMPTY_KEYWORD_MSG = "Keyword to look for cannot be empty!!!";
    public static final String TASK_DETAILS_MISSING_MSG = "Details of task not complete!!!";
    public static final String TEXT_FILE_INCORRECT_CONTENTS =
            "Some content(s) of text file do not match the correct format! Ignoring faulty commands...";
    public static final String UNKNOWN_COMMAND_TEXT_FILE =
            "Unknown command found in text file! Ignoring and moving on...";

    public static final String TASK_DELETED = "Task deleted!";

    public static void printOut(String textToPrint) {
        System.out.println(textToPrint);
    }

    /**
     * Prints out a goodbye message upon exiting the chatbot
     */
    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints out a welcome message upon running the chatbot
     */
    public static void printWelcomeMessage() {
        String logo = "     /\\   |  ____/ __ \\| \\ | |\n"
                + "    /  \\  | |__ | |  | |  \\| |\n"
                + "   / /\\ \\ |  __|| |  | | . ` |\n"
                + "  / ____ \\| |___| |__| | |\\  |\n"
                + " /_/    \\_\\______\\____/|_| \\_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AEON, your personal TO-DO list bot!\nWhat can I do for you?");
    }
}
