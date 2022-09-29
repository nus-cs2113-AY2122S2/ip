package Misc;

import Commands.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    /**
     * Parse the given command.
     * @param command Command string.
     */
    public static Command parse(String command) throws DukeException {
        ArrayList<String> elements = new ArrayList<>(Arrays.asList(command.split(" ", 2)));
        if (elements.size() > 1) {
            if (elements.get(1).contains("/by")) {
                String[] list = elements.get(1).split(" /by ", 2);
                elements.remove(1);
                elements.addAll(Arrays.asList(list));
            } else if (elements.get(1).contains("/at")) {
                String[] list = elements.get(1).split(" /at ", 2);
                elements.remove(1);
                elements.addAll(Arrays.asList(list));
            }
        } else if (elements.get(0).equals("mark") | elements.get(0).equals("unmark")
            | elements.get(0).equals("unmark") | elements.get(0).equals("find")) {
            throw new DukeException(5);
        }
        switch (elements.get(0)) {
            case "list":
                return new ListCommand();
            case "mark": case "unmark":
                return new MarkCommand(elements);
            case "todo": case "deadline": case "event":
                return new AddCommand(elements);
            case "delete":
                return new DeleteCommand(elements);
            case "find":
                return new FindCommand(elements);
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException(0);
        }
    }
}
