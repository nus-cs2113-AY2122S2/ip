package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private static Ui ui;

    public Parser() {
        ui = new Ui();
    }
    public static String parseCommandFromString(String inputCommand) {
        String[] command = inputCommand.split(" ");
        return command[0];
    }

    public static int parseMarkIndexFromString(String inputCommand) {
        String[] commandArr = inputCommand.split(" ");
        int markNum = Integer.parseInt(commandArr[1]) - 1;
        return markNum;
    }

    public static int parseUnMarkIndexFromString(String inputCommand) {
        String[] commandArr = inputCommand.split(" ");
        int UnMarkNum = Integer.parseInt(commandArr[1]) - 1;
        return UnMarkNum;
    }

    public static int parseDeleteIndexFromString(String inputCommand) {
        String[] commandArr = inputCommand.split(" ");
        int deleteIndex = Integer.parseInt(commandArr[1]) - 1;
        return deleteIndex;
    }

    public String parseTaskTypeFromString(String inCommand) {
        String[] sentenceArr = inCommand.split(" ", 2);
        return sentenceArr[0];
    }

    public String[] parseTaskDescriptionFromString(String inCommand) {
        String[] sentenceArr = inCommand.split(" ", 2);
        return sentenceArr;
    }

    public String parseToDoActionFromDescription(String[] taskDescription) {
        return taskDescription[1];
    }

    public String[] parseDeadLineActionFromDescription(String[] taskDescription) {
        return taskDescription[1].split("/by", 2);
    }

    public String[] parseEventsActionFromDesciption(String[] taskDescription) {
        return taskDescription[1].split("/at", 2);
    }

    public String[] parseLineFromFile(String line) {
        return line.split("-", 3);
    }

    public LocalDate parseDateFormatFromString(String dateInString)  {
        LocalDate date = LocalDate.now();
        try {
            date = LocalDate.parse(dateInString.trim());
        } catch (DateTimeParseException invalidDate) {
            ui.printInvalidDateException();
        }
        return date;
    }
}
