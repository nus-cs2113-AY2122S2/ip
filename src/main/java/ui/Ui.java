package ui;

public class Ui {
    /**
     * Starting of a programme by showing a welcome message and greeting the user.
     */
    public void startProgram() {
        showWelcomeMessage();
        greet();
    }

    /**
     * This is a method used to print a line for aesthetic purposes throughout the programme.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * This method shows an elaborate welcome message with a logo.
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    /**
     * This method gives a short greeting to the user.
     */
    public void greet() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * This method is used when exiting the programme, to bid the user farewell.
     */
    public void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * This method is used when the file location given by the user does not exist.
     * It will tell the user that the programme is creating a new file.
     * @param filePath file path of the text document that would contain relevant data.
     */
    public void showLoadingError(String filePath) {
        printLine();
        System.out.println(filePath + " file not found.\nnew file has been created");
        printLine();
    }
}
