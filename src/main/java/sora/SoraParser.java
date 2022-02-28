package sora;

import java.util.Scanner;

/**
 * This class manages the inputs entered by the user as well as parsing it to provide specific information
 * as required by Sora's components.
 */
public class SoraParser {
    // Specifies a set of characters that the user is not allowed to use in his/her input.
    public static final String[] ILLEGAL_CHARACTERS = {
            "|"
    };

    private Scanner reader = new Scanner(System.in);

    /**
     * Reads in a user's input. Thereafter, leading and trailing whitespaces are removed for program stability.
     * In addition, the user's input is checked for illegal characters and if any are found, an
     * IllegalCharacterException is thrown.
     *
     * @return A string containing the user's sanitised input.
     * @throws IllegalCharacterException If the user's input contains an illegal character as specified in the
     * ILLEGAL_CHARACTERS string array.
     */
    protected String getUserInput() throws IllegalCharacterException {
        String userInput = reader.nextLine();
        String userInputTrimmed = userInput.trim();

        boolean hasIllegalCharacters = checkForIllegalCharacters(userInputTrimmed);

        if (hasIllegalCharacters) {
            throw new IllegalCharacterException(IllegalCharacterException.ILLEGAL_CHARACTER_MSG);
        }

        return userInputTrimmed;
    }

    /**
     * Returns the command keyword the user has entered in his/her input and wants Sora to execute.
     *
     * @param userInput The user's full input.
     * @return A string containing the command keyword the user has entered.
     */
    protected String extractCommand(String userInput) {
        String userCommand = userInput.toLowerCase().split(" ", 2)[0];
        return userCommand;
    }

    /**
     * Returns the task number the user has entered in his/her input.
     *
     * @param userInput The user's full input.
     * @return The task number that the user has entered in his/her input.
     */
    protected int getTaskNumberFromCommand(String userInput) {
        try {
            int taskNum = Integer.parseInt(userInput.split(" ")[1]);
            return taskNum;
        } catch (NumberFormatException e) {
            // Re-throw it to calling method to handle, which should send it to SoraExceptionHandler
            throw e;
        }
    }

    /**
     * Checks if the user's input contains at least one character that is specified in the
     * ILLEGAL_CHARACTERS string array.
     *
     * @param userInput The user input that will be checked for illegal characters.
     * @return true if the user's input contains at least one illegal character. Otherwise, false is returned.
     */
    private boolean checkForIllegalCharacters(String userInput) {
        for (String illegalChar : ILLEGAL_CHARACTERS) {
            if (userInput.contains(illegalChar)) {
                return true;
            }
        }

        return false;
    }

    public String getSearchString(String userRawInput) throws InvalidCommandException {
        String[] commandAndSearchPhrase = userRawInput.split(" ", 2);
        boolean hasTwoElements = (commandAndSearchPhrase.length == 2);

        if (!hasTwoElements) {
            throw new InvalidCommandException(InvalidCommandException.FIND_NO_SEARCH_STRING);
        }

        return commandAndSearchPhrase[1];
    }
}
