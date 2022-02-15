import taskitems.Greet;
import taskitems.exceptions.IllegalInputException;
import taskitems.TaskManager;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    static Greet greet;
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        try {
            taskManager.loadData();
        } catch (FileNotFoundException f) {
            System.out.println("No saved data found");
        }
        greet.sayHi();
        taskLoop(taskManager);
        //echo();
        greet.sayBye();
    }

    public static void taskLoop(TaskManager taskManager){
        greet.printDecoration();
        boolean isDone = false;
        while (!isDone) {
            String command = reader.next();
            String taskName = reader.nextLine();
            switch (command) {
            case "todo":
                try {
                    taskManager.addToTasks(taskName.trim());
                } catch (IllegalInputException inputException) {
                    greet.printDecoration();
                    System.out.println("I'm sorry you can't have an empty todo task, try giving it a name.");
                    greet.printDecoration();
                }

                break;
            case "deadline":
                try {
                    taskManager.addToTasks("D",taskName.trim().split(" /by ")[0],taskName.trim().split(" /by ")[1]);
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    greet.printDecoration();
                    System.out.println("You seemed to have missed out a few key details required to create a deadline.\n" +
                            "Say \"help\" to see how to use various commands.");
                    greet.printDecoration();
                }

                break;
            case "event":
                try {
                    taskManager.addToTasks("E",taskName.trim().split(" /at ")[0],taskName.trim().split(" /at ")[1]);
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    greet.printDecoration();
                    System.out.println("You seemed to have missed out a few key details required to create an event.\n" +
                            "Say \"help\" to see how to use various commands.");
                    greet.printDecoration();
                }
                break;
            case "list":
                taskManager.printTasks();
                break;
            case "mark":
                try {
                    taskManager.markTask(Integer.parseInt(taskName.trim()));
                } catch (IllegalInputException inputException){
                    System.out.println("Invalid Number given, you can only mark tasks that are in the list.\n" +
                            "Say \"list\" to view your list of tasks");
                    greet.printDecoration();
                } catch (NumberFormatException numberFormatException)
                {
                    greet.printDecoration();
                    System.out.println("You are required to give an integer value to mark an item in the list.\n" +
                            "Say \"help\" to see how to use various commands.");
                    greet.printDecoration();
                }
                break;
            case "unmark":
                try{
                    taskManager.unmarkTask(Integer.parseInt(taskName.trim()));
                } catch (IllegalInputException inputException){
                    System.out.println("Invalid Number given, you can only unmark tasks that are in the list.\n" +
                            "Say \"list\" to view your list of tasks.");
                    greet.printDecoration();
                } catch (NumberFormatException numberFormatException)
                {
                    greet.printDecoration();
                    System.out.println("You are required to give an integer value to unmark an item in the list.\n" +
                            "Say \"help\" to see how to use various commands.");
                    greet.printDecoration();
                }
                break;
            case "bye":
                taskManager.saveData();
                isDone = true;
                break;
            case "help":
                greet.printDecoration();
                System.out.println("Sorry, I am still trying to get my things together and learn new things.\n" +
                        "I will update this portion as soon as I learn what I should be doing.");
                greet.printDecoration();
                break;
            default:
                greet.printDecoration();
                System.out.println("Invalid Command!");
                greet.printDecoration();
            }
        }
    }

    public static void echo(){
        greet.printDecoration();
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
