package helper;

import taskitems.exceptions.IllegalInputException;
import taskitems.task.Task;
import taskitems.task.TaskList;


import java.util.ArrayList;
import java.util.Scanner;

// This particular class holds methods that relate to the User Interface
public class Ui {
    static Scanner reader = new Scanner(System.in);

    public void welcome () {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }
    public void goodBye () {
         print("Goodbye! Hope to see you again soon!\"");
     }

    // Method that reads in User's input, a single word command
    public String readCommand () {
        String command = reader.next();
        return command;
    }

    // Method that reads in all the parameters given after a command as a single string
    public String readParameter () {
        String parameters = reader.nextLine();
        return parameters;
    }

    // Method that prints default replies for each type of exception
    public void manageExceptions (Exception e) {
        if (e instanceof IllegalInputException) {
            print("I'm sorry I believe you are missing some parameters, please try again.\n"
                    + "You can also say \"help\" to see how to use various commands.");
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            print("I'm sorry I believe you are missing some parameters, please try again.\n"
                    + "You can also say \"help\" to see how to use various commands.");
        } else if (e instanceof NumberFormatException) {
            print("This command requires an Integer that falls within the number of tasks in the list\n"
                    + "You can say \"list\" to see all your saved tasks.\n"
                    + "You can also say \"help\" to see how to use various commands.");
        }
    }

    // Method that prints a passage
    // @param (sentence) (a string that needs to be printed to user)
    public void print (String sentence) {
        String[] passage = sentence.split("\n");
        System.out.println("\nDuke: " + passage[0]);
        for (int i = 1; i < passage.length; i++) {
            System.out.println("      " + passage[i]);
        }
    }

    // Method that prints a single task
    // @param (task) (a task that needs to be printed to user)
    public void print (Task task) {
        System.out.println("      " + task);
    }

    // Method that prints a list of tasks
    // @param (tasks) (a TaskList instance with tasks that needs to be printed to user)
    public void print (TaskList tasks) {
        for (int i = 1; i <= tasks.size ; i++) {
            System.out.println("      " + i + ". " + tasks.getTask(i));
        }
    }

    // Method that prints a continuing passage (does not require "Duke: ")
    // @param (sentence) (a string that needs to be printed to user)
    public void printCont (String sentence) {
        String[] passage = sentence.split("\n");
        for (int i = 0; i < passage.length; i++) {
            System.out.println("      " + passage[i]);
        }
    }

    // Method that prints prompt to signal user to give their input
    public void prompt () {
        System.out.print("\nYou: ");
    }

    public void printHelp() {
        print("It seems like you need some help! Fret not I am here to save the day!\n"
            + "There are 12 commands that you can use to tell me what to do.\n"
            + "I have listed them below and provided examples on how you should use them.\n\n"
            +"1. help\n"
            +"The help command brings you to this page where you can figure out how to use Duke.\n\n"
            +"2. list\n"
            + "The list command generates a list of tasks that you have already created before.\n\n"
            +"3. find\n"
            +"The find command lets you search of tasks by their names (for e.g: find homework)\n\n"
            +"4. todo\n"
            +"The todo command lets you add a todo task. A todo task requires a name to be entered as a parameter.\n"
            +"(for e.g: todo yoga)\n\n"
            +"5. deadline\n"
            + "The deadline command lets you add a task that has a deadline. A deadline task requires a name, date and time.\n"
            + "(for e.g: deadline assignment 5 /by 2022-03-01 13:00)\n"
            + "The /by keyword is required before the date and time. Dates are in YYYY-MM-DD format. Time in HH:MM format.\n\n"
            + "6. event\n"
            + "The event command lets you add a task that happens at a particular date and time.\n"
            + "An event task requires a name, date and time.\n"
            + "(for e.g: event graduation /by 2024-06-01 23:59)\n"
            + "The /at keyword is required before the date and time. Dates are in YYYY-MM-DD format. Time in HH:MM format.\n\n"
            + "7. mark\n"
            + "You can use the mark command to mark certain tasks in the list as done. Mark takes in a positive integer as a parameter.\n"
            + "(for e.g: mark 2)\n\n"
            + "8. unmark\n"
            + "You can use the unmark command to unmark marked tasks in the list. Unmark takes in a positive integer as a parameter.\n"
            + "(for e.g: unmark 2)\n\n"
            + "9. delete\n"
            + "You can use the delete command to delete a task from the list. Delete takes in a positive integer as a parameter.\n"
            + "All deleted tasks are sent to the bin which are them permanently destroyed on the exit of the app.\n"
            + "(for e.g: delete 4)\n\n"
            + "10. retrieve\n"
            + "You can use the retrieve command to recover a task from the bin. Retrieve takes in a positive integer as a parameter.\n"h
            + "(for e.g: retrieve 1)\n\n"
            + "11. bin\n"
            + "You can view items you have deleted by saying bin.\n\n"
            +"12. bye\n"
            + "You can exit the application at any point in time by saying bye.\n\n");
    }

}
