import jrobo.command.InputParser;
import jrobo.task.Task;
import jrobo.task.TaskManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JRobo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        manager.load();
        run(scanner, manager);
        manager.save();
        scanner.close();
    }

    public static void run(Scanner scanner, TaskManager manager) {
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
            try {
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
                case "delete":
                case "del":
                    manager.deleteTask(Integer.parseInt(parser.getBody().trim()));
                    break;
                case "bye":
                case "b":
                case "quit":
                case "q":
                    break label;
                default:

                    manager.addTask(parser.getBody(), parser.getSuffix(), parser.getType(), false);
                    break;
                }
            } catch (jrobo.exception.InvalidFormatException | jrobo.exception.InvalidTypeException | NumberFormatException e) {
                manager.printWithSeparator(e.getMessage());
            }
        }
        manager.farewellUser();
    }

}
