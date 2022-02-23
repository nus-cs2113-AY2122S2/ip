import taskitems.Greet;
import taskitems.exceptions.IllegalInputException;
import taskitems.TaskManager;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    static Greet greet;
    static Scanner reader = new Scanner(System.in);
    static Ui ui = new Ui();
    static Parser parser = new Parser();

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        ui.welcome();
        taskLoop(taskManager);
        ui.goodBye();
    }

    public static void taskLoop(TaskManager taskManager){
        boolean isDone = false;
        while (!isDone) {
            String command = ui.readCommand();
            String taskName = ui.readParameter();
            switch (command) {
            case "todo":
                try {
                    taskManager.addToTasks(parser.parseTodo(taskName));
                } catch (IllegalInputException inputException) {
                    ui.manageExceptions(inputException);
                }
                break;
            case "deadline":
                try {
                    String[] parameters = parser.parseDeadline(taskName);
                    taskManager.addToTasks("D",parameters[0],parameters[1]);
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    ui.manageExceptions(outOfBoundsException);
                } catch (IllegalInputException inputException) {
                    ui.manageExceptions(inputException);
                }
                break;
            case "event":
                try {
                    String[] parameters = parser.parseEvent(taskName);
                    taskManager.addToTasks("E",parameters[0],parameters[1]);;
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    ui.manageExceptions(outOfBoundsException);
                } catch (IllegalInputException inputException) {
                    ui.manageExceptions(inputException);
                }
                break;
            case "list":
                taskManager.printTasks();
                break;
            case "mark":
                try {
                    taskManager.markTask(parser.parseMark(taskName));
                } catch (NumberFormatException numberFormatException) {
                    ui.manageExceptions(numberFormatException);
                }
                break;
            case "unmark":
                try{
                    taskManager.unmarkTask(parser.parseMark(taskName));
                } catch (NumberFormatException numberFormatException) {
                    ui.manageExceptions(numberFormatException);
                }
                break;
            case "delete":
                try{
                    taskManager.deleteTask(parser.parseDelete(taskName));
                } catch (NumberFormatException numberFormatException) {
                    ui.manageExceptions(numberFormatException);
                }
                break;
            case "bin":
                taskManager.printDeletedTasks();
                break;
            case "bye":
                isDone = true;
                break;
            case "help":
                System.out.println("Sorry, I am still trying to get my things together and learn new things.\n" +
                        "I will update this portion as soon as I learn what I should be doing.");
                break;
            default:
                System.out.println("Invalid Command!");
            }
        }
    }

    public static void echo(){
        boolean isDone = false;
        while (!isDone) {
            String toRepeat = reader.nextLine();
            if (toRepeat.toLowerCase().equals("bye")) {
                isDone = true;
                break;
            } else {
                System.out.println(toRepeat);
            }
        }
    }
}
