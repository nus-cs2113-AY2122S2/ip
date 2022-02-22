package duke.userinterface;

import duke.task.Task;

import java.util.ArrayList;

public class UserInterface {

    public void printLine() {
        System.out.println("------------------------------");
    }

    public void printGreeting() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printNoCommandMessage() {
        System.out.println("Well, I'll wait for a command..");
    }

    public void printHelpMessage() {
        System.out.println("Sorry I don't know what you mean.");
        System.out.println("There are currently eight keyword commands: ");
        System.out.println("1. list, 2. todo, 3. deadline, 4.event, 5. mark, 6. unmark, 7. delete, 8. bye");
    }

    public void printEmptyDescriptionMessage() {
        System.out.println("Oops! Please type in a description!");
    }

    public void printEmptyTimingDetailsMessage() {
        System.out.println("Oops! Please indicate the time for the deadline you want to add!");
    }

    public void printIOExceptionMessageLoad() {
        System.out.println("Oops! Something went wrong with loading the data!");
    }
    public void printIOExceptionMessageWrite() {
        System.out.println("Oops! Something went wrong with writing the data!");
    }
    public void printIOExceptionMessageRead() {
        System.out.println("Oops! Something went wrong with reading the data!");
    }

    public void printOutOfBoundsExceptionMessage() {
        System.out.println("Oops! The task number given is not in range. Try again!");
    }
    public void printNumberFormatExceptionMessage() {
        System.out.println("Oops! Please give an integer value. Try again!");
    }

    public void printTasks(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        } else {
            System.out.println("Wow, such empty");
        }
    }

    public void printMessageForAdding(ArrayList<Task> tasks, Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.size() + "." + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void printMarkOrUnmarkMessage(ArrayList<Task> tasks, String command, int number) {
        if (command.equals("mark")) {
            tasks.get(number).setIsMarked();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            tasks.get(number).unsetIsMarked();
            System.out.println("Nice! I've unmarked this task as done:");
        }
        System.out.println(number + 1 + "." + tasks.get(number).toString());
    }

    public String getCommand(String userInput) {
        String[] words = userInput.split(" ");
        String command = words[0];
        return command;
    }

    public void printTask(ArrayList<Task> tasks, int number) {
        System.out.println("Noted. I've removed this task:\n" + tasks.get(number).toString());
    }
}
