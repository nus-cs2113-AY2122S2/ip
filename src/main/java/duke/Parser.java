package duke;

import command.*;
import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Parser {

    public Parser() {
        ;
    }

    public static String getCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        return command;
    }

    public Command parse(String fullCommand) throws DukeException {
        if(fullCommand.trim().equals("list")) {
            return new ListCommand();
        }
        else if(fullCommand.contains("unmark ")) {
            String dummy = fullCommand.trim();
            int taskOrder;
            taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DoneCommand(taskOrder, "unmark");
        }
        else if(fullCommand.contains("mark ")) {
            String dummy = fullCommand.trim();
            int taskOrder;
            taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DoneCommand(taskOrder, "mark");
        }
        else if(fullCommand.contains("delete")) {
            String dummy = fullCommand.trim();
            if(dummy.length() == 6) {
                throw new DukeException("\nThe description of delete operation cannot be empty\n");
            }
            int taskOrder = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DeleteCommand(taskOrder);
        }
        else if(fullCommand.contains("deadline")) {
            String dummy = fullCommand.trim();
            if(dummy.length() == 8) {
                throw new DukeException("\n☹ OOPS!!! The description of a deadline cannot be empty.\n");
            }
            int splitPosition = dummy.indexOf("/by");
            String description = dummy.substring(9, splitPosition);
            String by = dummy.substring(splitPosition + 4);
            return new AddCommand("deadlines",description, by);
        }
        else if(fullCommand.contains("event")) {
            String dummy = fullCommand.trim();
            if(dummy.length() == 5) {
                throw new DukeException("\n☹ OOPS!!! The description of a event cannot be empty.\n");
            }
            int splitPosition = dummy.indexOf("/at");
            String description = dummy.substring(6, splitPosition);
            String duration = dummy.substring(splitPosition + 4);
            return new AddCommand("events", description, duration);
        }
        else if(fullCommand.contains("todo")) {
            String dummy = fullCommand.trim();
            if(dummy.length() == 4) {
                throw new DukeException("\n☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
            String description = dummy.substring(5);
            return new AddCommand("todo", description, "");
        }
        else if(fullCommand.contains("find")) {
            String dummy = fullCommand.trim();
            if(dummy.length() == 4) {
                throw new DukeException("\nThe search key cannot be empty\n");
            }
            String searchKey = dummy.substring(5);
            return new FindCommand(searchKey);
        }
        else {
            throw new DukeException("\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    public static String echo(String command) {
        String line = "____________________________________________________________";
        return line + "\n" + command + "\n" + line + "\n";
    }

}
