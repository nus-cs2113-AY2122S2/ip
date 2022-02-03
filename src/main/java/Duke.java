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
            String[] userInputArr = userInput.split(" ", 2);
            String userCommand = userInputArr[0];

            // Execute user commands
            switch(userCommand) {
            case "list":
                currChat.printTaskList();
                break;
            case "mark":
                // Get next argument which is the task no. to mark
                currChat.markTaskIndex(Integer.parseInt(userInputArr[1]));
                break;
            case "unmark":
                currChat.unmarkTaskIndex(Integer.parseInt(userInputArr[1]));
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
