package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.util.ArrayList;

public class Parser {
    private static ArrayList<String> splitToTwo(String line, String delimiter) {
        ArrayList<String> words = new ArrayList<>();
        int divider = line.indexOf(delimiter);

        words.add(line);

        if (divider != -1) {
            words.set(0, line.substring(0, divider));
            words.add(line.substring(divider+1));
        }
        return words;
    }

    private static String getNextWord(String line, String word) {
        String nextWord = "";
        try {
            int indexOfWord = line.indexOf(word);
            String nextPart = line.substring(indexOfWord + word.length() + 1);
            int indexOfSpace = nextPart.indexOf(" ");
            if (indexOfSpace == -1) {
                nextWord = nextPart;
            } else {
                nextWord = nextPart.substring(0, indexOfSpace);
            }
        }

        catch (IndexOutOfBoundsException e){

        }
        return nextWord;
    }

    /**
     * Checks input command for any error
     * @param input input command to be checked
     * @throws DukeException if there is an error
     */
    private static void errorCheck(String input) throws DukeException {
        String[] words = input.split(" ");
        String command = words[0];

        switch (command) {
        case "todo":
        case "event":
        case "deadline":
            String taskName = getNextWord(input, command);
            if (taskName.equals("")) {
                String errorMsg = String.format("%s requires a name\n", command);
                throw new DukeException(errorMsg);
            } else {
                if (command.equals("event") || command.equals("deadline")) {
                    int indexOfSlash = input.indexOf("/");
                    String date = indexOfSlash == -1 ? "" : input.substring(indexOfSlash);
                    if (date.length() <= 1) {
                        String errorMsg = String.format("%s requires a valid date in the format taskName /date" +
                                "date could be a string or in /dd-MM-yyyy hh:mm format\n", command);
                        throw new DukeException(errorMsg);
                    }
                }
            }
            break;

        case "bye":
        case "list":
            if (!getNextWord(input, command).equals("")) {
                String errorMsg = String.format("Command not understood");
                throw new DukeException(errorMsg);
            }
            break;
        case "find":
            String keyword = getNextWord(input, command);
            if (keyword.equals("")) {
                String errorMsg = String.format("%s requires a word\n", command);
                throw new DukeException(errorMsg);
            }
            break;
        case "mark":
        case "unmark":
        case "delete":
            try {
                Integer.parseInt(getNextWord(input, command));
            } catch (NumberFormatException e) {
                String errorMsg = String.format("Please enter a valid index for mark/unmark");
                throw new DukeException(errorMsg);
            }
            break;


        default:
            String errorMsg = String.format("Command not understood");
            throw new DukeException(errorMsg);
        }
        return;
    }

    /**
     * Parses the input command and return the corresponding command. It first calls errorCheck(fullCommand)
     * to check for error, if there is no error, it returns the correct command.
     * @param fullCommand input command
     * @return Command object corresponding to the input command
     * @throws DukeException if there is an error
     */
    public static Command parse(String fullCommand) throws DukeException {

        try {
            errorCheck(fullCommand);
            ArrayList<String> words = splitToTwo(fullCommand, " ");
            String command = words.get(0);
            String description = words.size() >= 2 ? words.get(1) : null;

            switch (command) {
            case "mark":
                return new MarkCommand(Integer.parseInt(description));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(description));
            case "list":
                return new ListCommand();
            case "todo":
            case "deadline":
            case "event":
                ArrayList<String> description_split = Parser.splitToTwo(description, "/");
                String taskName = description_split.get(0);
                String addInfo = description_split.size() >= 2 ? description_split.get(1) : null;
                return new AddCommand(command, taskName, addInfo);
            case "delete":
                return new DeleteCommand(Integer.parseInt(description));
            case "find":
                return new FindCommand(description);
            case "bye":
                return new ByeCommand();
            default:
                return null;
            }
        }
            catch(DukeException e) {
                throw e;
            }
        }

    /**
     * Parses string into task. Used by Storage to parse input file into task list.
     * @param input String to be parsed
     * @return task object correspond to the String
     */
    public static Task parseToTask(String input) {
        int indexOfSpace = input.indexOf(" ");
        String taskType = input.substring(indexOfSpace + 2, indexOfSpace+3);
        String status = input.substring(indexOfSpace + 5, indexOfSpace + 6);
        String nameAndDate = input.substring(indexOfSpace+ 7);
        String name= "", date = "";
        if (!taskType.equals("T")) {
            name = nameAndDate.substring(0, nameAndDate.indexOf("("));
            date = nameAndDate.substring(nameAndDate.indexOf("(") + 1, nameAndDate.indexOf(")"));
        }
        Task task = new Task("");
        switch (taskType) {
        case "T":
            task = new ToDo(nameAndDate);
            break;
        case "D":
            task = new Deadline(name, date);
            break;
        case "E":
            task = new Event(name, date);
            break;
        }
        if (status.equals("X")) {
            task.setCompleted(true);
        }
        return task;
    }
 }
