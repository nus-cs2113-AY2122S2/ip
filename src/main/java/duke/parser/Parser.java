package duke.parser;

import duke.taskmanagement.TaskManager;
import duke.userinterface.UserInterface;

import java.util.Scanner;

public class Parser {
    public void run(TaskManager tasks) {
        UserInterface ui = new UserInterface();
        Scanner in = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("bye")) {
            ui.printLine();
            userInput = in.nextLine().trim();
            String command = ui.getCommand(userInput);
            switch (command) {
            case "list":
                tasks.listTasks();
                break;
            case "todo":
                tasks.addTodo(userInput);
                break;
            case "deadline":
                tasks.addDeadline(userInput);
                break;
            case "event":
                tasks.addEvent(userInput);
                break;
            case "mark":
            case "unmark":
                tasks.markOrUnmarkTask(userInput);
                break;
            case "delete":
                tasks.deleteTask(userInput);
                break;
            case "bye":
                ui.printByeMessage();
                break;
            case "":
                ui.printNoCommandMessage();
                break;
            default:
                ui.printHelpMessage();
                break;
            }
        }
    }
}
