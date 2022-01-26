import java.util.Scanner;

public class Duke {
    public static void startBot(Scanner in, boolean shouldExitProgram) {
        final String HORIZONTAL_LINE = "    ------------------------------------------------------------";
        System.out.println(HORIZONTAL_LINE);
        ChatBot yz = new ChatBot();
        System.out.println(HORIZONTAL_LINE);
        while (!shouldExitProgram) {
            String userInput = in.nextLine();
            System.out.println(HORIZONTAL_LINE);
            if (userInput.equals("bye")) {
                yz.echoFarewellGreeting();
                shouldExitProgram = true;
            } else {
                yz.echoCommand(userInput);
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean shouldExitProgram = false;
        startBot(in,shouldExitProgram);
    }
}
