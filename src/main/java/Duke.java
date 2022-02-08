import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    //fields
    private static TaskManager taskManager = new TaskManager();

    //methods
    public static void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        IOMethods.printWithDivider("Hello! I'm Duke\nWhat can I do for you?");
    }


    //main
    public static void run (String line) {
        ArrayList<String> words = IOMethods.splitToTwo(line, " ");
        String command = words.get(0);
        String description = words.size()>= 2 ? words.get(1) : null;

        switch(command) {
        case "mark":
            taskManager.markCompleted(Integer.parseInt(description));
            break;
        case "unmark":
            taskManager.unmarkCompleted(Integer.parseInt(description));
            break;
        case "list" :
            IOMethods.printWithDivider(taskManager.toString());
            break;
        case "todo":
        case "deadline":
        case "event":
            taskManager.addTask(command, description);
            break;
        default:
            taskManager.addTask("task", line);
        }
    }

    public static void main(String[] args) {

        hello();

        Scanner sc = new Scanner (System.in);
        String line = sc.nextLine();

        while (!line.equals("bye")){
            run(line);
            line = sc.nextLine();
        }
        IOMethods.printWithDivider("Bye. Hope to see you again soon!");

    }
}
