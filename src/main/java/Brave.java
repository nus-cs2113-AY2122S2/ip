import java.util.Scanner;
import java.lang.String;

public class Brave {
    public static void main(String[] args) {
        String input;
        String[] splitInputs;
        String command;
        String[] arguments;
        String description;
        Scanner in = new Scanner(System.in);
        TaskManager tasks = new TaskManager();

        tasks.showWelcomeMessage();

        while (true) {
            input = in.nextLine();
            splitInputs = input.split(" ", 2);
            command = splitInputs[0]; //e.g. mark 2 -> take the first word as the command -> "mark"

            if (command.equals("bye")) {
                tasks.showFarewellMessage();
                break;
            }

            switch (command) {
            case "list":
                tasks.printTaskList();
                break;
            case "mark":
                tasks.markTask(Integer.parseInt(splitInputs[1]) - 1); // 0 indexing
                break;
            case "unmark":
                tasks.unmarkTask(Integer.parseInt(splitInputs[1]) - 1); // 0 indexing
                break;
            case "todo":
                tasks.addTask(new Todo(splitInputs[1]));
                break;
            case "deadline":
                // To-do validate arguments~
                arguments = splitInputs[1].split(" /by ", 2);
                description = arguments[0];
                String by = arguments[1];
                tasks.addTask(new Deadline(description, by));
                break;
            case "event":
                // To-do validate arguments~
                arguments = splitInputs[1].split(" /at ", 2);
                description = arguments[0];
                String eventTime = arguments[1];
                tasks.addTask(new Event(description, eventTime));
                break;
            default:
                System.out.println("Wrong input, available command are -> list/mark/unmark/todo/deadline/event");
                break;
            }
        }
    }


}
