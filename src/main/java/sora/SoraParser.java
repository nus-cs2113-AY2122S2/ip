package sora;

import java.util.Scanner;

public class SoraParser {
    public static final String[] ILLEGAL_CHARACTERS = {
            "|"
    };

    private Scanner reader = new Scanner(System.in);

    protected String getUserInput() throws IllegalCharacterException {
        String userInput = reader.nextLine();
        String userInputTrimmed = userInput.trim();

        boolean hasIllegalCharacters = checkForIllegalCharacters(userInputTrimmed);

        if (hasIllegalCharacters) {
            throw new IllegalCharacterException(IllegalCharacterException.ILLEGAL_CHARACTER_MSG,
                    "getUserInput", "SoraParser");
        }

        return userInputTrimmed;
    }

    protected String extractCommand(String userRawInput) {
        String userCommand = userRawInput.toLowerCase().split(" ", 2)[0];
        return userCommand;
    }

    protected int getTaskNumberFromCommand(String userRawInput) {
        int taskNum = Integer.parseInt(userRawInput.split(" ")[1]);
        return taskNum;
    }

    private boolean checkForIllegalCharacters(String userRawInput) {
        for (String illegalChar : ILLEGAL_CHARACTERS) {
            if (userRawInput.contains(illegalChar)) {
                return true;
            }
        }

        return false;
    }
}
