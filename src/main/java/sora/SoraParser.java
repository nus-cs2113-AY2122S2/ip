package sora;

import java.util.Scanner;

public class SoraParser {
    private Scanner reader = new Scanner(System.in);

    protected String getUserInput() {
        String userInput = reader.nextLine();
        String userInputTrimmed = userInput.trim();
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
}
