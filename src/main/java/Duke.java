import java.util.Scanner;

public class Duke {
    public static Boolean checkIsInteger(String toCheck) {
        try {
            int intValue = Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean processCommand(String command, TaskManager taskManager) {
        switch(command) {
        case "bye":
            System.out.println("Bye and see you again soon!");
            return false;
        case "list":
            taskManager.listTasks();
            return true;
        default:
            break;
        }

        String[] arrOfCommand = command.split(" ");

        if ((arrOfCommand[0].equals("mark") && arrOfCommand.length > 1) && checkIsInteger(arrOfCommand[1])) {
            taskManager.markTasks(true, Integer.parseInt(arrOfCommand[1]));
        } else if ((arrOfCommand[0].equals("unmark") && arrOfCommand.length > 1) && checkIsInteger(arrOfCommand[1])) {
            taskManager.markTasks(false, Integer.parseInt(arrOfCommand[1]));
        } else {
            taskManager.addNewTask(command);
        }
        return true;
    }

    public static void main(String[] args) {
        String command;
        Boolean isRunning = true;
        Scanner in = new Scanner(System.in);
        String InitialGreeting = "Greetings, I am Azmuth\n" +
                "What can I do for you?\n" +
                "____________________";
        System.out.println(InitialGreeting);
        TaskManager taskManager = new TaskManager();
        while (isRunning) {
            command= in.nextLine();
            isRunning = processCommand(command, taskManager);
        }
        in.close();
    }
}
