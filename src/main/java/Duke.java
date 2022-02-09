import java.util.Scanner;

public class Duke {
    public static void printHorizontalLine() {
        System.out.println("    ------------------------------------------------------------");
    }

    public static void startBot(Scanner in, boolean shouldExitProgram) {
        printHorizontalLine();
        ChatBot bigBob = new ChatBot();
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
