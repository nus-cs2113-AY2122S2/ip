import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput = "";
        TaskManager taskList = new TaskManager();
        printGreeting();
        while (!userInput.equals("bye")) {
            printLine();
            userInput = in.nextLine().trim();
            String[] words = userInput.split(" ");
            switch (words[0]) {
            case "list":
                taskList.listTasks();
                break;
            case "todo":
                taskList.addTodo(userInput);
                break;
            case "deadline":
                taskList.addDeadline(userInput);
                break;
            case "event":
                taskList.addEvent(userInput);
                break;
            case "mark":
                taskList.markTask(words[1]);
                break;
            case "unmark":
                taskList.unmarkTask(words[1]);
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "":
                System.out.println("Well, I'll wait for a command..");
                break;
            default:
                taskList.addTask(userInput);
                break;
            }
        }
    }

    private static void printLine() {
        System.out.println("------------------------------");
    }

    private static void printGreeting() {
        printLine();
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
    }
}
