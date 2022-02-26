package duke;

import exceptions.DukeException;

public class Parser {
    public String parseCommand(String input) {
        if (input.equals("bye")) {
            return "bye";
        } else if (input.equals("list")) {
            return "list";
        } else if (input.startsWith("mark ")) {
            return "mark";
        } else if (input.startsWith("unmark ")) {
            return "unmark";
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return "add";
        } else if (input.startsWith("delete")) {
            return "delete";
        } else if(input.startsWith("find")) {
            return "find";
        } else {
            return "error";
        }
    }
}
