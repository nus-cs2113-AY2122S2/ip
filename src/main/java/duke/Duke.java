package duke;

import duke.task.Task;

import java.util.Scanner;

public class Duke {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        LocalStorage.initializeLocalFileStorage();
        TaskList.loadTaskFromFileToProgram();

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
                TaskList.saveTaskListToFile();
                break;
            case "list":
                TaskList.printTaskList();
                break;
            default:
                UI.printBorder();
                TaskList.addTaskToTaskList(input, command);
                TaskList.saveTaskListToFile();
                UI.printBorder();
                break;
            }
        }
    }
}
