import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Start chat session
        ChatSession currChat = new ChatSession();
        currChat.startSession();

        boolean isLoop = true;
        while (isLoop) {
            // Get user input
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();

            // Execute user commands
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
                    break;
                }
            }
        }
    }
}
