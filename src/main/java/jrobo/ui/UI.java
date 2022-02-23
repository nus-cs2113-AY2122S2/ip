package jrobo.ui;

import jrobo.command.InputParser;
import jrobo.task.TaskManager;

import java.util.Scanner;

public class UI {
    Scanner scanner;
    TaskManager manager;

    public UI(Scanner scanner, TaskManager manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void setView() {
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
