package duke.parser;

import duke.exception.AdditionalException;

public class RequestParser extends Parser {

    public static boolean filterNewRequest(String request) throws AdditionalException {
        boolean isBye = false;
        String[] words = words = request.split(" ");
        String command = words[0].toLowerCase();
        if (command.equalsIgnoreCase("bye")) {
            isBye = true;
        } else {
            filter(request, words, command, true);
        }
        return isBye;
    }

}
