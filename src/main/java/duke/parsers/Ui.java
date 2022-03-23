package duke.parsers;

import duke.exceptions.InputLengthException;
import duke.exceptions.UnreachableTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.util.LinkedList;
import java.util.Scanner;

public class Ui {

    /** Greeting to display to user */
    String greeting;
    /** Buffer line used extensively when printing to user */
    String underscoreLine;
    /** List of all tasks in Duke's list */
    TaskList toDos;
    /** A scanner to take in user input */
    Scanner sc;
    /** A string to represent the user's input line, changed every time the user enters a new command */
    String line;
    /** An array of the user's commands split by spaces. Changes every time the user enters a new command */
    String[] commands;
    /** The number of commands in the string array commands */
    int commandNumber;

    /**
     * Constructor of the Ui class
     *
     * @return An instance of Ui
     */
    public Ui(TaskList toDos) {
        greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        underscoreLine = "____________________________________________________________";
        this.toDos = toDos;

    }

    /**
     * Prints a greeting to the user
     */
    public void printGreeting() {
        System.out.println(greeting);
    }

    private void bye() {
        System.out.println(underscoreLine);
        System.out.println("  Bye; Don't restart me.");
        System.out.println(underscoreLine);
        System.exit(0);
    }

    private void list() {
        System.out.println(underscoreLine);
        for (int i = 0; i < toDos.taskCounter; i++) {
            System.out.println("   " + (i + 1) + ". " + toDos.get(i).getStatusIcon()
                    + toDos.get(i).getDescription());
        }
        System.out.println(underscoreLine);
    }

    private void mark() {
        try {
            if (commands.length < 2) {
                throw new InputLengthException();
            }
            commandNumber = Integer.parseInt(commands[1]);
            if (commandNumber > 0 && commandNumber <= toDos.taskCounter) {
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
    }

    private void unmark() {
        try {
            if (commands.length < 2) {
                throw new InputLengthException();
            }
            commandNumber = Integer.parseInt(commands[1]);
            if (commandNumber > 0 && commandNumber <= toDos.taskCounter) {
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
    }

    private void todo() {
        try {
            if (commands.length < 2) {
                throw new InputLengthException();
            }
            line = line.substring(5); //removing the first part of the command from the description
            //toDos.get(toDos.taskCounter) = new ToDo(line);
            toDos.add(new ToDo(line));
            System.out.println(underscoreLine);
            System.out.println(" Do these tasks distract you from a glaring lack of meaning\n in your life?" +
                    "\n Anyway, I added" + " it to the list.");
            System.out.printf("   %s %s%n", toDos.get(toDos.taskCounter - 1).getStatusIcon(), line);

            System.out.println(" There are now " + toDos.taskCounter + " tasks in the list.");
            System.out.println(underscoreLine);
        } catch (InputLengthException e) {
            System.out.println(underscoreLine);
            System.out.println("   Todo what? You left the task blank.");
            System.out.println("   Doing nothing would be your normal schedule.");
            System.out.println(underscoreLine);
        }
    }

    private void deadline() {
        try {
            int separationLocation = line.indexOf("/by"); //used to split the command

            String description = line.substring(9, separationLocation); //first half of command
            String doBy = line.substring((separationLocation + 3));
            //toDos.get(toDos.taskCounter) = new Deadline(description, doBy);
            toDos.add(new Deadline(description, doBy));
            if (toDos.get(toDos.taskCounter - 1) instanceof Deadline) {
                doBy = (toDos.get(toDos.taskCounter - 1)).getdateTimeString();
            }
            System.out.println(underscoreLine);
            System.out.println(" Great, something else for you to procrastinate:");
            System.out.printf("   %s %s(by:%s)%n", toDos.get(toDos.taskCounter - 1).getStatusIcon(), description, doBy);


            System.out.printf(" There are now %d tasks in the list.%n", toDos.taskCounter);
            System.out.println(underscoreLine);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(underscoreLine);
            System.out.println("I'm a supercomputer, and I couldn't parse what you just typed. Impressive.");
            System.out.println(underscoreLine);
        }
    }

    private void event() {
        try {
            int separationLocation = line.indexOf("/at");
            String description = line.substring(6, separationLocation);
            String doAt = line.substring((separationLocation + 3));
            //toDos.get(toDos.taskCounter) = new Event(description, doAt);
            toDos.add(new Event(description, doAt));
            if (toDos.get(toDos.taskCounter - 1) instanceof Event) {
                doAt = (toDos.get(toDos.taskCounter - 1)).getdateTimeString();
            }
            System.out.println(underscoreLine);
            System.out.println(" An event! Perhaps it can distract you from your self-inflicted prison:");
            System.out.printf("   %s %s(at:%s)%n", toDos.get(toDos.taskCounter - 1).getStatusIcon(), description, doAt);

            System.out.printf(" There are now %d tasks in the list.%n", toDos.taskCounter);
            System.out.println(underscoreLine);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(underscoreLine);
            System.out.println("I'm a supercomputer, and I couldn't parse what you just typed. Impressive.");
            System.out.println(underscoreLine);
        }
    }

    private void delete() {
        try {
            if (commands.length < 2) {
                throw new InputLengthException();
            }
            commandNumber = Integer.parseInt(commands[1]);
            if (commandNumber > 0 && commandNumber <= toDos.taskCounter) {
                System.out.println(underscoreLine);
                System.out.println(" Forgoing further responsibilties, I see.");
                System.out.printf(" Removed:   %s %s%n", toDos.get(commandNumber - 1).getStatusIcon(),
                        toDos.get(commandNumber - 1).getDescription());

                System.out.println(" There are now " + (toDos.taskCounter - 1) + " tasks in the list.");
                System.out.println(underscoreLine);
                toDos.remove(commandNumber - 1);
            } else {
                throw new UnreachableTaskException();
            }
        } catch (InputLengthException e) {
            System.out.println(underscoreLine);
            System.out.println("You forgot the whole other half of the command... why am I not surprised?");
            System.out.println(underscoreLine);
        } catch (UnreachableTaskException e) {
            System.out.println(underscoreLine);
            System.out.println("You never wrote down such a task. But of course you want to delete it.");
            System.out.println(underscoreLine);
        }
    }

    private void find() {
        try {
            String toBeFound = "";
            LinkedList<String> tasksFound = new LinkedList<String>();
            if (commands.length < 2) {
                throw new InputLengthException();
            }

            for (int i = 1; i < commands.length; i++) {
                toBeFound = commands[i].toLowerCase();

                for (int x = 0; x < toDos.taskCounter; x++) {
                    if (toDos.get(x).getDescription().toLowerCase().contains(toBeFound) &&
                            !tasksFound.contains(toDos.get(x).getDescription())) {
                        tasksFound.add(toDos.get(x).getDescription());
                    }
                }
            }


            if (tasksFound.size() == 0) {
                System.out.println(underscoreLine);
                System.out.println("Not a single task matching that search. What a shock.");
                System.out.println(underscoreLine);
            } else {
                System.out.println(underscoreLine);
                System.out.println("Here's everything that matches item(s) in your search.");
                System.out.println("You won't hear me say it much, but good job\n" +
                        "taking the initiative here.\n");
                for (String string : tasksFound) {
                    System.out.println(string);
                }
                System.out.println(underscoreLine);
            }
        } catch (InputLengthException e) {
            System.out.println(underscoreLine);
            System.out.println("You forgot the whole other half of the command... why am I not surprised?");
            System.out.println(underscoreLine);
        }
    }

    /**
     * Parses a line from the user and runs the command desired
     * If the command cannot be parsed, parseLine handles the error
     *
     */
    public void parseLine() {

        sc = new Scanner(System.in);
        line = sc.nextLine();
        commands = line.split(" ");

        switch (commands[0]) {
        case "bye": //end the program
            this.bye();
            //program will terminate on the line before this
        case "list": //list out all tasks
            this.list();
            break;
        case "mark": //mark a task as done
            this.mark();
            break;
        case "unmark": //mark a task as no longer done
            this.unmark();
            break;
        case "todo": //add a todo (normal task) to the list
            this.todo();
            break;
        case "deadline": //add a deadline to the task list
            this.deadline();
            break;
        case "event": //add an event to the task list
            this.event();
            break;
        case "delete":
            this.delete();
            break;
        case "find":
            this.find();
            break;
        default: //user formatted a command incorrectly
            System.out.println(underscoreLine);
            System.out.println("I can't understand your gibberish.");
            System.out.println(underscoreLine);
        }
    }
}
