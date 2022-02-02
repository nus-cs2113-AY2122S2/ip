import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        manager.welcomeUser();
        label:
        while (true) {
            String input = scanner.nextLine();
            InputParser parser = new InputParser(input);

            if (!parser.isValidCommand()) {
                manager.giveError();
                continue;
            }
            String command = parser.getPrefix();
            switch (command) {
            case "mark":
            case "m":
                manager.markTask(parser.getBody());
                break;
            case "unmark":
            case "um":
                manager.unmarkTask(parser.getBody());
                break;
            case "list":
            case "ls":
                manager.displayTaskList();
                break;
            case "bye":
            case "b":
            case "quit":
            case "q":
                break label;
            default:
                manager.addTask(parser.getBody(), parser.getSuffix(), parser.getType());
                break;
            }
        }
        manager.farewellUser();
        scanner.close();
    }
}
