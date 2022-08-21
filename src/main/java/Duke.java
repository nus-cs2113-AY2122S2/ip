import java.util.Scanner;

public class Duke {
    public static boolean processCommand(String command, TaskManager taskManager) {
        switch(command) {
        case "bye":
            System.out.println("Bye and see you again soon!");
            return false;
        case "list":
            taskManager.listTasks();
            break;
        default:
            taskManager.addNewTask(command);
            break;
        }
        return true;
    }

    public static void main(String[] args) {
        String command;
        Boolean online = true;
        Scanner in = new Scanner(System.in);
        String InitialGreeting = "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________";
        System.out.println(InitialGreeting);
        TaskManager taskManager = new TaskManager();
        while (online) {
            command= in.nextLine();
            online = processCommand(command, taskManager);
        }
        in.close();
    }
}
