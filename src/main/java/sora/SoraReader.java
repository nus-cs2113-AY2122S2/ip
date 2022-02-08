package sora;

import java.util.Scanner;

public class SoraReader {
    Scanner reader = new Scanner(System.in);

    protected String getUserInput() {
        String userInput = reader.nextLine();
        String userInputTrimmed = userInput.trim();
        return userInputTrimmed;
    }
}
