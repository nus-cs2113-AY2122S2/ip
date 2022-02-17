package duke;

import java.util.ArrayList;
import duke.exceptions.InputLengthException;
import duke.exceptions.UnreachableTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws InputLengthException {
        Scanner sc = new Scanner(System.in);
        //ToDo[] toDos = new ToDo[100]; //holds all tasks given
        ArrayList<ToDo> toDos = new ArrayList<>();
        int taskCounter = 0; //counts number of tasks
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        String underscoreLine = "____________________________________________________________";

        System.out.println(greeting);

        while (true) {
            System.out.println(taskCounter);
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
                /*
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println("   " + (i + 1) + ". " + toDos[i].getStatusIcon() + toDos[i].getDescription());
                } */
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println("   " + (i + 1) + ". " + toDos.get(i).getStatusIcon()
                            + toDos.get(i).getDescription());
                }
                System.out.println(underscoreLine);
                break;
            case "mark": //mark a task as done
                try {
                    if (commands.length < 2) {
                        throw new InputLengthException();
                    }
                    commandNumber = Integer.parseInt(commands[1]);
                    if (commandNumber >= 0 && commandNumber <= taskCounter) {
                        System.out.println(underscoreLine);
                        System.out.println("   I don't actually believe you completed a task, but I'll mark it anyway.");
                        System.out.println("     [X] " + toDos.get(commandNumber - 1).getDescription());
                        System.out.println(underscoreLine);
                        toDos.get(commandNumber - 1).setDone(true);
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
                try {
                    if (commands.length < 2) {
                        throw new InputLengthException();
                    }
                    commandNumber = Integer.parseInt(commands[1]);
                    if (commandNumber >= 0 && commandNumber <= taskCounter) {
                        System.out.println(underscoreLine);
                        System.out.println("   Unmarking a task; sharp as a marble, aren't we?");
                        System.out.println("     [] " + toDos.get(commandNumber - 1).getDescription());
                        System.out.println(underscoreLine);
                        toDos.get(commandNumber - 1).setDone(false);
                    } else {
                        throw new UnreachableTaskException();
                    }
                } catch (InputLengthException e) {
                    System.out.println(underscoreLine);
                    System.out.println("   You forgot to tell me what to unmark. \n But that doesn't surprise me...");
                    System.out.println(underscoreLine);
                } catch (UnreachableTaskException e) {
                    System.out.println(underscoreLine);
                    System.out.println("   You didn't even write down that task.");
                    System.out.println(underscoreLine);
                }
                break;
            case "todo": //add a todo (normal task) to the list
                try {
                    if (commands.length < 2) {
                        throw new InputLengthException();
                    }
                    line = line.substring(5); //removing the first part of the command from the description
                    //toDos.get(taskCounter) = new ToDo(line);
                    toDos.add(new ToDo(line));
                    System.out.println(underscoreLine);
                    System.out.println(" Do these tasks distract you from a glaring lack of meaning\n in your life?" +
                            "\n Anyway, I added" + " it to the list.");
                    System.out.printf("   %s %s%n", toDos.get(taskCounter).getStatusIcon(), line);
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
                //toDos.get(taskCounter) = new Deadline(description, doBy);
                toDos.add(new Deadline(description, doBy));
                System.out.println(underscoreLine);
                System.out.println(" Great, something else for you to procrastinate:");
                System.out.printf("   %s %s(by:%s)%n", toDos.get(taskCounter).getStatusIcon(), description, doBy);
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
                //toDos.get(taskCounter) = new Event(description, doAt);
                toDos.add(new Event(description, doAt));
                System.out.println(underscoreLine);
                System.out.println(" An event! Perhaps it can distract you from your self-inflicted prison:");
                System.out.printf("   %s %s(at:%s)%n", toDos.get(taskCounter).getStatusIcon(), description, doAt);
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
