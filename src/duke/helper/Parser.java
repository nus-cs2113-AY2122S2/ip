package duke.helper;

import duke.main.DukeException;

import java.util.Scanner;

public class Parser {
    protected static Scanner in = new Scanner(System.in);
    protected static String line;
    public static final int INVALID = -1;

    public String getLine() {
        line = in.nextLine();
        return line;
    }

    public boolean isMarkCommand() {
        return line.split(" ")[0].equals("mark");
    }

    public boolean isUnmarkCommand() {
        return line.split(" ")[0].equals("unmark");
    }

    public boolean isDeleteCommand() {
        return line.split(" ")[0].equals("delete");
    }

    public boolean isExitCommand() {
        return line.equals("bye");
    }

    public boolean isListCommand() {
        return line.equals("list");
    }

    public int parseIndex(Ui ui, int taskCount) {
        int index;
        try {
            index = Integer.parseInt(line.split(" ")[1]) - 1;
            if (index >= taskCount || index < 0) {
                throw new DukeException("Task Index is out of bounds.");
            }
        }catch (NumberFormatException | DukeException e) {
            ui.printExceptionMessage(e.getMessage());
            return INVALID;
        }
        return index;
    }
}
