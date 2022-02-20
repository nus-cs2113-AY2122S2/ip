package shrek;

import shrek.commands.PrintOutput;
import shrek.commands.HandleCommands;
import shrek.initialisation.Initialise;
import java.util.Scanner;
import shrek.initialisation.SaveToOutput;

public class Shrek {

    public static void main(String[] args) {
        Initialise.initialiseShrek();
        PrintOutput.printGreeting();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            HandleCommands.handleInput(userInput, true);
            userInput = in.nextLine();
        }
        SaveToOutput.saveData();
        PrintOutput.printGoodbye();
    }
}
