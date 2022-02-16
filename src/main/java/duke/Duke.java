package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void printHorizontalLine() {
        System.out.println("    ------------------------------------------------------------");
    }

    public static void startBot(Scanner in, boolean shouldExitProgram) {
        printHorizontalLine();
        ChatBot bigBob;
        try {
            bigBob = new ChatBot();
        } catch(IOException io){
            if(io.getMessage().equals("Folder is unable to be created")){
                System.out.println("File was not found and the parent folder of the file is unable to be created.");
            }
            System.out.println("File was not found and the file is unable to be created.");
            return;
        }
        printHorizontalLine();
        Command inputCommand;
        while (!shouldExitProgram) {
            String userInput = in.nextLine();
            printHorizontalLine();
            try {
                inputCommand = Decoder.parseInput(userInput);
            } catch (DukeException de) {
                bigBob.processExceptions(de);
                printHorizontalLine();
                continue;
            }
            if (inputCommand.getType() == Command.CommandType.EXITPROGRAM) {
                shouldExitProgram = true;
                bigBob.echoFarewellGreeting();
                printHorizontalLine();
                continue;
            }
            bigBob.executeCommand(inputCommand);
            printHorizontalLine();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean shouldExitProgram = false;
        startBot(in, shouldExitProgram);
    }
}
