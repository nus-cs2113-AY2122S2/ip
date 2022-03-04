package duke;

public class Parser {
    public static void parse(String fullCommand) {
        // Do Something
        final String commandWord = fullCommand.split(" ", 2)[0];
        final String arguments = fullCommand.split(" ", 2)[1];

        switch (commandWord) {
        case "list":
            // return new listCommand();
        }

    }
}
