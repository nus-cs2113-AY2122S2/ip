import java.util.Scanner;

public class Duke {
    public static boolean processInput(String input, ChatBot bigBob) {
        boolean shouldExitProgram = false;
        int taskIndex;
        if (input.equals("bye")) {
            bigBob.echoFarewellGreeting();
            shouldExitProgram = true;
        } else if (input.equals("list")) {
            bigBob.printList();
        } else if (input.startsWith("mark")) {
            taskIndex = Integer.parseInt(input.substring(5, input.length())) - 1;
            bigBob.updateTaskStatusInList(true, taskIndex);
        } else if (input.startsWith("unmark")) {
            taskIndex = Integer.parseInt(input.substring(7, input.length())) - 1;
            bigBob.updateTaskStatusInList(false, taskIndex);
        } else {
            bigBob.addTaskToList(input);
        }
        return shouldExitProgram;
    }

    public static void startBot(Scanner in, boolean shouldExitProgram) {
        final String HORIZONTAL_LINE = "    ------------------------------------------------------------";
        System.out.println(HORIZONTAL_LINE);
        ChatBot bigBob = new ChatBot();
        System.out.println(HORIZONTAL_LINE);
        while (!shouldExitProgram) {
            String userInput = in.nextLine();
            System.out.println(HORIZONTAL_LINE);
            shouldExitProgram = processInput(userInput, bigBob);
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean shouldExitProgram = false;
        startBot(in, shouldExitProgram);
    }
}
