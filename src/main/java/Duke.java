import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] commandHistory = new String[100];
        int numCommands = 0;
        boolean isLoop = true;

        ChatSession currChat = new ChatSession();
        currChat.startSession();

        while (isLoop) {
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();

            if (userInput.startsWith("mark")) {
                String[] userInputArr = userInput.split(" ");
                currChat.markTaskIndex(Integer.parseInt(userInputArr[1]));
            } else if (userInput.startsWith("unmark")) {
                String[] userInputArr = userInput.split(" ");
                currChat.unmarkTaskIndex(Integer.parseInt(userInputArr[1]));
            } else {
                switch (userInput) {
                    case "list":
                        currChat.printTaskList();
                        break;
                    case "bye":
                        currChat.endSession();
                        isLoop = false;
                        break;
                    default:
                        currChat.addTask(userInput);
                }
            }
        }
    }
}
