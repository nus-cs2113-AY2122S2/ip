package aeon;

import static aeon.controller.Command.CommandProcessor;

public class Aeon {

    public static void main(String[] args) {
            printWelcomeMessage();
            CommandProcessor();
            printGoodbyeMessage();
    }

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

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}

