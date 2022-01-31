import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100]; //holds all tasks given
        int taskCounter = 0; //counts number of tasks
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";


        System.out.println(greeting);

        while (true) {
            String line = sc.nextLine();
            String[] commands = line.split(" ");
            int commandNumber;
            switch (commands[0]) {
            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println("  Bye; Don't restart me.");
                System.out.println("____________________________________________________________\n");
                System.exit(0);
            case "list":
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println("   [" + tasks[i].getStatusIcon() + "]" + (i + 1) + ". " + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
                break;
            case "mark":
                line = line.substring(5, line.length());
                commandNumber = Integer.parseInt(commands[1]) - 1;
                if (commandNumber >= 0 && commandNumber <= taskCounter) {
                    System.out.println("____________________________________________________________");
                    System.out.println("   I don't actually believe you completed a task, but I'll mark it anyway.");
                    System.out.println("     [X] " + tasks[commandNumber].getDescription());
                    System.out.println("____________________________________________________________");
                    tasks[commandNumber].setDone(true);
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("   You didn't even write down that task.");
                    System.out.println("____________________________________________________________");
                }
                break;
            case "unmark":
                line = line.substring(5, line.length());
                commandNumber = Integer.parseInt(commands[1]) - 1;
                if (commandNumber >= 0 && commandNumber <= taskCounter) {
                    System.out.println("____________________________________________________________");
                    System.out.println("   Unmarking a task; sharp as a marble, aren't we?");
                    System.out.println("     [] " + tasks[commandNumber].getDescription());
                    System.out.println("____________________________________________________________");
                    tasks[commandNumber].setDone(false);
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("   You didn't even write down that task.");
                    System.out.println("____________________________________________________________");
                }
                break;
            default:
                tasks[taskCounter] = new Task(line);
                taskCounter++;
                System.out.println("____________________________________________________________");
                System.out.println("  added: " + line);
                System.out.println("____________________________________________________________\n");
            }
        }
    }
}
