package duke.ui;

public class Ui {
    public static void printWelcomeMessage() {
        System.out.println("____________________________________________________________\n"
                + "Hello, nice to meet you. I'm Yae! (*^▽^*)\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
    }

    public static void printDefaultErrorMessage() {
        System.out.println("Sorry, I did not understand that command. Input help to find out more.");
    }

    public static void printMissingDescriptionErrorMessage() {
        System.out.println("Description/Task Number could not be found!");
    }

    public static void printMissingDateErrorMessage() {
        System.out.println("Date could not be found!");
    }

    public static void printHelp() {
        System.out.println("Here are the list of commands:");
        System.out.println("list: Lists current tasks.");
        System.out.println("mark: Marks task as done. (e.g. mark <task number>)\n"
                + "unmark: Marks task as not done. (e.g. unmark <task number>)");
        System.out.println("todo: adds a todo. (e.g. todo <description>)");
        System.out.println("deadline: adds a deadline. (e.g. deadline <description> /by <date>)");
        System.out.println("event: adds an event. (e.g. event <description> /at <date>)");
        System.out.println("delete: delete a task. (e.g. delete <task number>)");
    }
}
