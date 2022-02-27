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


}
