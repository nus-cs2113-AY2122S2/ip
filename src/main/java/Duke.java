import java.util.Scanner;

public class Duke {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        UI.printIntro();
        while (true) {
            String input = SC.nextLine();
            String command = CommandParser.getCommand(input);
            switch (command) {
            case "bye":
                UI.printOutro();
                System.exit(0);
                break;
            case "mark":
            case "unmark":
                TaskList.markTask(input, command);
                break;
            case "list":
                TaskList.printTaskList();
                break;
            default:
                UI.printBorder();
                TaskList.addTaskToTaskList(input, command);
                UI.printBorder();
                break;
            }
        }
    }
}
