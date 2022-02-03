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

            String[] userArguments;
            String description;
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
            case "todo":
                currChat.addTask(new Todo(userInputArr[1]));
                break;
            case "deadline":
                userArguments = userInputArr[1].split(" /by ", 2);
                description = userArguments[0];
                String by = userArguments[1];
                currChat.addTask(new Deadline(description, by));
                break;
            case "event":
                userArguments = userInputArr[1].split(" /at ", 2);
                description = userArguments[0];
                String eventTime = userArguments[1];
                currChat.addTask(new Event(description, eventTime));
                break;
            default:
                System.out.println("____________________________________________________________");
                System.out.println("Incorrect input. Please try again.");
                System.out.println("____________________________________________________________");
                break;
            }
        }
    }
}
