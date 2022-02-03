import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {
        DialogGenerator dialog = new DialogGenerator();

        greeting(dialog);
        manageUserInput(dialog);

    }

    public static void greeting(DialogGenerator dialog) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(""+logo);
        dialog.printLine();
        System.out.println("Hello I'm Duke.");
        System.out.println("What can I do for you?");
        dialog.printLine();
        System.out.println();
    }

    public static void manageUserInput(DialogGenerator dialog){
        final int TASK_LENGTH = 100;
        int index;
        int taskCount = 0;
        boolean notQuit = true;

        Task t;
        Task[] allTasks = new Task[TASK_LENGTH];

        while (notQuit) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine();

            switch (command.split(" ")[0]) {
                case "bye":
                    dialog.sayGoobye();
                    notQuit = false;
                    break;

                case "mark":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    t = allTasks[index];
                    dialog.markAndDisplayTask(t);
                 break;

                case "unmark":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    t = allTasks[index];
                    dialog.unmarkAndDisplayTask(t);
                    break;

                case "list":
                    dialog.displayListWithStatus(allTasks, taskCount);
                    break;

                case "todo":
                    t = new Todo(command.replace("todo ", ""));
                    allTasks[taskCount] = t;
                    taskCount += 1;
                    dialog.displayTask(t, taskCount);
                    break;

                case "deadline":
                    t = new Deadline(command.split(" /by ")[0].replace("deadline ", ""), command.split(" /by ")[1]);
                    allTasks[taskCount] = t;
                    taskCount += 1;
                    dialog.displayTask(t, taskCount);
                    break;

                case "event":
                    t = new Event(command.split(" at/ ")[0].replace("event ",""), command.split(" /at ")[1]);
                    allTasks[taskCount] = t;
                    taskCount += 1;
                    dialog.displayTask(t, taskCount);
                    break;

                default:
                    t = new Task(command);
                    allTasks[taskCount] = t;
                    taskCount += 1;
                    dialog.displayTask(t, taskCount);
                    break;
            }
        }
    }

}
