import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ToDo[] toDos = new ToDo[100]; //holds all tasks given
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
                    System.out.println("   " + (i + 1) + ". " + toDos[i].getStatusIcon() + toDos[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
                break;
            case "mark":
                line = line.substring(5, line.length());
                commandNumber = Integer.parseInt(commands[1]) - 1;
                if (commandNumber >= 0 && commandNumber <= taskCounter) {
                    System.out.println("____________________________________________________________");
                    System.out.println("   I don't actually believe you completed a task, but I'll mark it anyway.");
                    System.out.println("     [X] " + toDos[commandNumber].getDescription());
                    System.out.println("____________________________________________________________");
                    toDos[commandNumber].setDone(true);
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
                    System.out.println("     [] " + toDos[commandNumber].getDescription());
                    System.out.println("____________________________________________________________");
                    toDos[commandNumber].setDone(false);
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("   You didn't even write down that task.");
                    System.out.println("____________________________________________________________");
                }
                break;
            case "todo":
                line = line.substring(5, line.length()); //removing the first part of the command from the description
                toDos[taskCounter] = new ToDo(line);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + toDos[taskCounter].getStatusIcon() + " " + line);
                taskCounter++;
                System.out.println(" There are now " + taskCounter + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
                break;
            case "deadline":
                int separationLocation = line.indexOf("/by"); //used to split the command
                if (separationLocation == -1) { //checks if separationLocation exists
                    System.out.println("Invalid command format.");
                    break;
                }
                String description = line.substring(9, separationLocation); //first half of command
                String doBy = line.substring((separationLocation + 3), line.length());
                toDos[taskCounter] = new Deadline(description, doBy);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task to the list:");
                System.out.println("   " + toDos[taskCounter].getStatusIcon() + " " + description +
                        "(by:" + doBy + ")");
                taskCounter++;
                System.out.println(" There are now " + taskCounter + " tasks in the list.");
                System.out.println("____________________________________________________________\n");

                break;
            case "event":
                separationLocation = line.indexOf("/at");
                if (separationLocation == -1) {
                    System.out.println("Invalid command format.");
                    break;
                }
                description = line.substring(6, separationLocation);
                String doAt = line.substring((separationLocation + 3), line.length());
                toDos[taskCounter] = new Event(description, doAt);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task to the list:");
                System.out.println("   " + toDos[taskCounter].getStatusIcon() + " " + description +
                        "(at:" + doAt + ")");
                taskCounter++;
                System.out.println(" There are now " + taskCounter + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
                break;
            default:
                System.out.println("Can't understand your gibberish.");
            }
        }
    }
}
