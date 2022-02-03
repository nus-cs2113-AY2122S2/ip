import java.util.Scanner;
import java.lang.String;

public class Brave {
    public static void main(String[] args) {
        String input;
        String[] splitInput;
        String command;
        String[] arguments;
        String description;
        Scanner in = new Scanner(System.in);
        TaskManager tasks = new TaskManager();

        tasks.showWelcomeMessage();


        while (true) {
            input = in.nextLine();
            splitInput = input.split(" ", 2);
            command = splitInput[0];

            if (command.startsWith("bye")) {
                break;
            }

            switch (command) {
            case "list":
                tasks.printTaskList();
                break;
            case "mark":
                tasks.markTask(Integer.parseInt(splitInput[1]) - 1);
                break;
            case "unmark":
                tasks.unmarkTask(Integer.parseInt(splitInput[1]) - 1);
                break;
            case "todo":
                tasks.addTask(new Todo(splitInput[1]));
                break;
            case "deadline":
                arguments = splitInput[1].split(" /by ", 2);
                description = arguments[0];
                String by = arguments[1];
                tasks.addTask(new Deadline(description, by));
                break;
            case "event":
                arguments = splitInput[1].split(" /at ", 2);
                description = arguments[0];
                String eventTime = arguments[1];
                tasks.addTask(new Event(description, eventTime));
                break;
            default:
                System.out.println("Wrong input");
                break;
            }
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye, Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }


}
