package duke;

import java.util.Scanner;

public class Parser {

    private final Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    public static String getCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        return command;
    }

    public static String echo(String command) {
        String line = "____________________________________________________________";
        return line + "\n" + command + "\n" + line + "\n";
    }

}
