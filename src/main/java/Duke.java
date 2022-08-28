import java.util.Scanner;

public class Duke {
    private static void printGreetingMessage() {
        String InitialGreeting = "Greetings, I am Azmuth\n" +
                "What can I do for you?\n" +
                "____________________";
        System.out.println(InitialGreeting);
    }

    public static void runBot() {
        String command;
        Scanner in = new Scanner(System.in);
        printGreetingMessage();
        TaskManager taskManager = new TaskManager();
        while (true) {
            command= in.nextLine();
            if(!Parser.isOnline(command, taskManager)) {
                break;
            }
        }
        in.close();
    }
    public static void main(String[] args) {
        runBot();
    }
}
