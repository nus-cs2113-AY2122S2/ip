package helper;

import taskitems.exceptions.IllegalInputException;
import taskitems.task.Task;
import taskitems.task.TaskList;


import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static Scanner reader = new Scanner(System.in);

    public void welcome () {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }
     public void goodBye () {
         print("Goodbye! Hope to see you again soon!\"");
     }

    public String readCommand () {
        String command = reader.next();
        return command;
    }

    public String readParameter () {
        String parameters = reader.nextLine();
        return parameters;
    }

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

    public void print (String sentence) {
        String[] passage = sentence.split("\n");
        System.out.println("\nDuke: " + passage[0]);
        for (int i = 1; i < passage.length; i++) {
            System.out.println("      " + passage[i]);
        }
    }
    public void print (Task task) {
        System.out.println("      " + task);
    }

    public void print (TaskList tasks) {
        for (int i = 1; i <= tasks.size ; i++) {
            System.out.println("      " + i + ". " + tasks.getTask(i));
        }
    }

    public void printCont (String sentence) {
        String[] passage = sentence.split("\n");
        for (int i = 0; i < passage.length; i++) {
            System.out.println("      " + passage[i]);
        }
    }

    public void prompt () {
        System.out.print("\nYou: ");
    }


}
