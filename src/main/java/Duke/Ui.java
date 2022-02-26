package Duke;

import java.io.IOException;
import static Duke.Parser.*;
public class Ui {
    public static void printWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void checkCommand() throws IOException, InvalidInputException {
        Parser.handleCommand();
    }

    public static void exit() {
        if(isBye())
        System.out.println("Bye. Hope to see you again soon!");
    }

}
