package duke.Ui;

public class CommandLineOutputUtil {
    private static final String MESSAGE_BORDER =
            "____________________________________________________________";

    public static void printFormat(String message) {
        System.out.println(MESSAGE_BORDER);
        System.out.println(message);
        System.out.println(MESSAGE_BORDER);
    }

    public static void greet() {
        printFormat(" Hey there! I'm Duke\n" +
                " What can I do for you? uwu");
    }

    static void bye() {
        printFormat(" Aw, are you leaving now?\n" +
                " Hope to see you again soon!");
    }
}
