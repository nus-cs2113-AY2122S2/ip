package duke;

import exceptions.DukeException;

/**
 * Parser class that will parse the user input and make sense of it.
 */
public class Parser {
    /**
     * Parses the main input by the user to determine the command that Duke will perform - add, list, delete etc.
     * @param input Input string by the user that contains the command along with the task to interact with (optional).
     * @return Command parsed from the input string.
     */
    public String parseCommand(String input) {
        String command;
        if (input.equals("bye")) {
            command =  "bye";
        } else if (input.equals("list")) {
            command =  "list";
        } else if (input.startsWith("mark ")) {
            command =  "mark";
        } else if (input.startsWith("unmark ")) {
            command =  "unmark";
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            command =  "add";
        } else if (input.startsWith("delete")) {
            command =  "delete";
        } else if (input.startsWith("find")) {
            command =  "find";
        } else {
            command =  "error";
        }
        return command;
    }
}
