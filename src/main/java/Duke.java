import duke.exception.DukeException;
import duke.iomethods.IOMethods;
import duke.task.TaskManager;
import duke.fileHandler.FileHandler;

import java.io.IOException;
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
    public static void run(String line) throws DukeException {
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
        case "delete":
            taskManager.deleteTask(Integer.parseInt(description));
        default:
            taskManager.addTask("task", line);
        }
    }

    public static void main(String[] args) throws IOException {

        FileHandler.setfilePath("./IOfile.txt");
        taskManager.setTasks(FileHandler.readFile());

        hello();
        Scanner sc = new Scanner (System.in);
        String line = sc.nextLine();



        while (!line.equals("bye")){
            try {
                IOMethods.errorHandler(line);
                run(line);
            }
            catch(DukeException e) {
                System.out.println(e.toString());
            }
            finally {
                line = sc.nextLine();
            }

        }

        FileHandler.writeFile(taskManager.toString());
        IOMethods.printWithDivider("Bye. Hope to see you again soon!");

    }
}
