package duke.iomethods;

import duke.exception.DukeException;

import java.util.ArrayList;

public class IOMethods {
    public static ArrayList<String> splitToTwo(String line, String delimiter) {
        ArrayList<String> words = new ArrayList<>();
        int divider = line.indexOf(delimiter);

        words.add(line);

        if (divider != -1) {
            words.set(0, line.substring(0, divider));
            words.add(line.substring(divider+1));
        }
        return words;
    }

    public static void printWithDivider(String stringWithinDivider) {
        String breakLine = "\t____________________________________________________________";
        System.out.println(breakLine);
        stringWithinDivider = stringWithinDivider.replace("\n", "\n\t");
        System.out.println("\t" + stringWithinDivider);
        System.out.println(breakLine);
    }

    public static void errorHandler(String input) throws DukeException {
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
                        String errorMsg = String.format("%s requires a valid date in the format taskName /date\n", command);
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

        case "mark":
        case "unmark":
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

    public static String getNextWord(String line, String word) {
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
}
