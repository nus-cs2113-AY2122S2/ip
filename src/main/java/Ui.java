import task.Task;

import java.util.ArrayList;

public class Ui {
    public static final String LINE_SEPARATOR = "____________________________________________________________\n";

    /**
     * Prints the greeting's logo of the application when the user runs the program
     */
    public static void displayLogo() {
        String logo = "                       ___\n"
                + "                      / ()\\\n"
                + "                    _|_____|_\n"
                + "                   | | === | |\n"
                + " _____  _      _   |_|  0  |_|\n"
                + "| ___| | \\    / |   ||  0  ||\n"
                + "| |__  |  \\  /  |   ||__*__||\n"
                + "|  __| |   \\/   |  |~ \\___/ ~| \n"
                + "| |___ | |\\__/| |  /=\\ /=\\ /=\\ \n"
                + "|_____||_|    |_|__[_]_[_]_[_]______________________________\n";
        String greetings = LINE_SEPARATOR
                + "Hello! I'm EM\n" + "What can I do for you?\n" + LINE_SEPARATOR;
        System.out.println(logo + greetings);
    }

    /**
     * Prints the farewell's logo of the application when the user exits the program
     */
    public static void displayFarewell() {
        String farewell = LINE_SEPARATOR
                + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR;
        System.out.println(farewell);
    }

    /**
     * Prints out all tasks being stored in the TaskList.
     * If there is nothing in the TaskList, a message "List is Empty!"
     * will be printed.
     * <p>
     * This method always prints, whether or not there is any task in
     * the TaskList. The method will print the task in the TaskList
     * as well as the increments indicating the task number.
     *
     * @param tasklist a list storing all the tasks created
     */
    public static void displayTaskList(ArrayList<Task> taskList) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        if (taskList.size() == 0) {
            System.out.println("List is Empty!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static void displayFoundTask(ArrayList<Task> taskList, ArrayList<Integer> keywordList) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the matching tasks in your list:");
        if (taskList.size() == 0) {
            System.out.println("No task found!");
        }
        for (int i = 0; i < keywordList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(keywordList.get(i)-1).toString());

        }
        System.out.println(LINE_SEPARATOR);
    }

}
