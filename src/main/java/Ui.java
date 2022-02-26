/**
 * Represents the user interface of the Duke application
 */

public class Ui {
    private String division = "------------------------------------------------";
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hello! I'm Duke, the greatest virtual assistant in the world :) \nWhat can I do for you?\n";
        System.out.println(division);
        System.out.println(greeting);
        System.out.println(division);
    }

    public void showLoadingError() {
        System.out.println("Could not find the specified file path to load tasks from!");
        System.out.println("Will try saving tasks to the newly specified filepath.");
        System.out.println(division);
    }


}
