package duke;

/**
 * Handles UI that is shown to user
 */
public class Ui {
    /**
     * Shows welcome message to user
     */
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("================================================");
    }

    /**
     * Shows goodbye message to user before termination of application
     */
    public void showGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
