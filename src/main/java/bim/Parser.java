package bim;

import java.util.Scanner;

public class Parser {
    private static final int EXPECTED_ARG_NUMBER = 2;

    private static final Scanner scanner = new Scanner(System.in);

    public Parser() { }

    public boolean isValidArgument(String commandArg) {
        return !commandArg.isEmpty();
    }

    public boolean isValidIndex(int taskCount, int index) {
        return index < taskCount && index >= 0;
    }

    public String readInput() {
        String input;

        do {
            input = scanner.nextLine();
        } while (input.isBlank());

        return input;
    }

    public String parseCommand(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0].toLowerCase();
        return command;
    }

    public String parseCommandArg(String input) {
        String[] words = input.split(" ", 2);
        String commandArg = "";
        if (words.length > 1) {
            commandArg = words[1].toLowerCase();
        }
        return commandArg;
    }

    public int parseIndex(String commandArg) {
        int index;
        try {
            index = Integer.parseInt(commandArg) - 1;
        } catch (NumberFormatException exception) {
            index = -1;
        }
        return index;
    }

    public boolean isValidArgument(String delimiter, String commandArg) {
        if (commandArg.contains(delimiter)) {
            String[] arguments = commandArg.split(delimiter);
            return arguments.length == EXPECTED_ARG_NUMBER;
        }
        return false;
    }
}
