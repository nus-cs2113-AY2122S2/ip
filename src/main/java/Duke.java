import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws InputLengthException {
        Scanner sc = new Scanner(System.in);
        ToDo[] toDos = new ToDo[100]; //holds all tasks given
        int taskCounter = 0; //counts number of tasks
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        String underscoreLine = "____________________________________________________________";

        System.out.println(greeting);

        while (true) {
            String line = sc.nextLine();
            String[] commands = line.split(" ");
            int commandNumber;
            switch (commands[0]) {
            case "bye": //end the program
                System.out.println(underscoreLine);
                System.out.println("  Bye; Don't restart me.");
                System.out.println(underscoreLine);
                System.exit(0);
            case "list": //list out all tasks
                System.out.println(underscoreLine);
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println("   " + (i + 1) + ". " + toDos[i].getStatusIcon() + toDos[i].getDescription());
                }
                System.out.println(underscoreLine);
                break;
            case "mark": //mark a task as done
                try {
                    if (args.length < 2) {
                        throw new InputLengthException();
                    }
                    commandNumber = Integer.parseInt(commands[1]) - 1;
                    if (commandNumber >= 0 && commandNumber <= taskCounter) {
                        System.out.println(underscoreLine);
                        System.out.println("   I don't actually believe you completed a task, but I'll mark it anyway.");
                        System.out.println("     [X] " + toDos[commandNumber].getDescription());
                        System.out.println(underscoreLine);
                        toDos[commandNumber].setDone(true);
                    } else {
                        throw new UnreachableTaskException();
                    }
                } catch (InputLengthException e) {
                    System.out.println(underscoreLine);
                    System.out.println("   You forgot to tell me what to mark. \n But that doesn't surprise me...");
                    System.out.println(underscoreLine);
                } catch (UnreachableTaskException e) {
                    System.out.println(underscoreLine);
                    System.out.println("   You didn't even write down that task.");
                    System.out.println(underscoreLine);
                }
                break;
            case "unmark": //mark a task as no longer done
                commandNumber = Integer.parseInt(commands[1]) - 1;
                if (commandNumber >= 0 && commandNumber <= taskCounter) {
                    System.out.println(underscoreLine);
                    System.out.println("   Unmarking a task; sharp as a marble, aren't we?");
                    System.out.println("     [] " + toDos[commandNumber].getDescription());
                    System.out.println(underscoreLine);
                    toDos[commandNumber].setDone(false);
                } else {
                    System.out.println(underscoreLine);
                    System.out.println("   You didn't even write down that task.");
                    System.out.println(underscoreLine);
                }
                break;
            case "todo": //add a todo (normal task) to the list
                try {
                    if (args.length < 2) {
                        throw new InputLengthException();
                    }
                    line = line.substring(5); //removing the first part of the command from the description
                    toDos[taskCounter] = new ToDo(line);
                    System.out.println(underscoreLine);
                    System.out.println(" Do these tasks distract you from a glaring lack of meaning\n  in your life?" +
                            "\nAnyway, I added" + " it to the list.");
                    System.out.printf("   %s %s%n", toDos[taskCounter].getStatusIcon(), line);
                    taskCounter++;
                    System.out.println(" There are now " + taskCounter + " tasks in the list.");
                    System.out.println(underscoreLine);
                } catch (InputLengthException e) {
                    System.out.println(underscoreLine);
                    System.out.println("   Todo what? You left the task blank.");
                    System.out.println("   Doing nothing would be your normal schedule.");
                    System.out.println(underscoreLine);
                }
                break;
            case "deadline": //add a deadline to the task list
                int separationLocation = line.indexOf("/by"); //used to split the command
                if (separationLocation == -1) { //checks if separationLocation exists
                    System.out.println(underscoreLine);
                    System.out.println("I'm a supercomputer, and I couldn't parse what you just typed. Impressive.");
                    System.out.println(underscoreLine);
                    break;
                }
                String description = line.substring(9, separationLocation); //first half of command
                String doBy = line.substring((separationLocation + 3));
                toDos[taskCounter] = new Deadline(description, doBy);
                System.out.println(underscoreLine);
                System.out.println(" Great, something else for you to procrastinate:");
                System.out.printf("   %s %s(by:%s)%n", toDos[taskCounter].getStatusIcon(), description, doBy);
                taskCounter++;
                System.out.printf(" There are now %d tasks in the list.%n", taskCounter);
                System.out.println(underscoreLine);
                break;
            case "event": //add an event to the task list
                separationLocation = line.indexOf("/at");
                if (separationLocation == -1) {
                    System.out.println(underscoreLine);
                    System.out.println("I'm a supercomputer, and I couldn't parse what you just typed. Impressive.");
                    System.out.println(underscoreLine);
                    break;
                }
                description = line.substring(6, separationLocation);
                String doAt = line.substring((separationLocation + 3));
                toDos[taskCounter] = new Event(description, doAt);
                System.out.println(underscoreLine);
                System.out.println(" An event! Perhaps it can distract you from your self-inflicted prison:");
                System.out.printf("   %s %s(at:%s)%n", toDos[taskCounter].getStatusIcon(), description, doAt);
                taskCounter++;
                System.out.printf(" There are now %d tasks in the list.%n", taskCounter);
                System.out.println(underscoreLine);
                break;
            default: //user formatted a command incorrectly
                System.out.println(underscoreLine);
                System.out.println("I can't understand your gibberish.");
                System.out.println(underscoreLine);
            }
        }
    }
}
