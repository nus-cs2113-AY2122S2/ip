package duke;

public class Ui {
    String line = "____________________________________________________________\n";
    /**
     * A public constructor to construct Ui
     */
    public Ui() {
        ;
    }

    /**
     * A method to return Duke's logo
     *
     * @return A String, Duke's logo
     */
    public static String printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }

    /**
     *A method to return greeting message of duke
     *
     * @return A String, duke's greeting message
     */
    public static String greet() {
        return "Hello! I'm Duke,\nWhat can I do for you?";
    }

    public String generateResponse(String messages) {
        return line + messages + line;
    }
}
