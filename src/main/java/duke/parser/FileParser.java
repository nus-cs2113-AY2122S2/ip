package duke.parser;

import duke.exception.AdditionalException;

public class FileParser extends Parser {

    public static void filterRequestsFromFile(String nextLine, int taskNumber) throws AdditionalException {
        String[] words = words = nextLine.split("\\|");
        String command = words[0].toLowerCase();
        String toMark = words[1].toLowerCase();
        String[] actualRequest = new String[words.length - 1];
        actualRequest[0] = command;
        String request = command;
        request = removeToMark(words, actualRequest, request);
        filter(request, actualRequest, command, false);
        updateTaskIfMarked(toMark, taskNumber);
    }

    private static void updateTaskIfMarked(String toMark, int taskNumber) throws AdditionalException {
        String request;
        if (toMark.equalsIgnoreCase("true")) {
            String[] actualRequest = new String[2];
            actualRequest[0] = "mark";
            actualRequest[1] = Integer.toString(taskNumber);
            request = "mark " + Integer.toString(taskNumber);
            filter(request, actualRequest, "mark", false);
        }
    }

    private static String removeToMark(String[] words, String[] actualRequest, String request) {
        for (int i = 2; i < words.length; i++) {
            actualRequest[i - 1] = words[i];
            request += " ";
            request += words[i];
        }
        return request;
    }

}
