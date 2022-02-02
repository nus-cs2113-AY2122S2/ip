import java.util.Scanner;

public class Duke {
    public static void startBot(Scanner in, boolean shouldExitProgram) {
        final String HORIZONTAL_LINE = "    ------------------------------------------------------------";
        System.out.println(HORIZONTAL_LINE);
        ChatBot bigBob = new ChatBot();
        System.out.println(HORIZONTAL_LINE);
        Command inputCommand;
        while (!shouldExitProgram) {
            String userInput = in.nextLine();
            System.out.println(HORIZONTAL_LINE);
            inputCommand = Decoder.parseInput(userInput);
            if (inputCommand.getType() != Command.CommandType.EXITPROGRAM) {
                bigBob.executeCommand(inputCommand);
            } else {
                shouldExitProgram = true;
                bigBob.echoFarewellGreeting();
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean shouldExitProgram = false;
        startBot(in, shouldExitProgram);
    }
}
