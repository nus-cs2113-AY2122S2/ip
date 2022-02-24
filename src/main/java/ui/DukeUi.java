package ui;

import duke.Storage;
import errors.Errors;
import task.Task;

import java.util.Scanner;

import static duke.Parser.getFirstWordOfCommand;
import static duke.TaskList.*;

public class DukeUi {

    public DukeUi(){
        printWelcomeLogo();
    }
    public static void printWelcomeLogo() {
        String customNameLogo = " .______.                .__    .__                                           \n" +
                "|   \\_ |______________  |  |__ |__| _________________    _____   ____  ______\n" +
                "|   || __ \\_  __ \\__  \\ |  |  \\|  |/     \\_  __ \\__  \\  /     \\ /  _ \\/  ___/\n" +
                "|   || \\_\\ \\  | \\// __ \\|   Y  \\  |  Y Y  \\  | \\// __ \\|  Y Y  (  <_> )___ \\ \n" +
                "|___||___  /__|  (____  /___|  /__|__|_|  /__|  (____  /__|_|  /\\____/____  >\n" +
                "         \\/           \\/     \\/         \\/           \\/      \\/           \\/";
        System.out.println("Hello from\n" + customNameLogo);
        System.out.println("Hello! I'm Ibrahimramos, your friendly multi-racial bot\nWhat can I do for you?");
    }
    public static void takeInputAndProcess() {
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            String command = getFirstWordOfCommand(input);
            switch (command) {
            case "list":
                Task.printList(taskList, taskCounter);
                break;
            case "mark":
                markTaskAsComplete(input);
                break;
            case "delete":
                deleteTask(input);
                break;
            case "unmark":
                unmarkTaskAsIncomplete(input);
                break;
            case "todo":
                addToDoTask(input);
                break;
            case "deadline":
                addDeadlineTask(input);
                break;
            case "event":
                addEventTask(input);
                break;
            case "exit":
                Storage.saveToFile(taskList);
                System.out.println("Bye. Hope to see you again soon!");
                return;
            default:
                System.out.println(Errors.INPUT_ERROR);
                break;
            }
            input = sc.nextLine();
        }
    }
}
