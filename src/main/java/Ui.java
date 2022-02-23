import taskitems.Greet;
import taskitems.exceptions.IllegalInputException;


import java.util.Scanner;

public class Ui {
    static Scanner reader = new Scanner(System.in);

    public void welcome () {
        Greet.sayHi();
    }
     public void goodBye () {
        Greet.sayBye();
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
            System.out.println("I'm sorry I believe you are missing some parameters, please try again.\n"
                    + "You can also say \"help\" to see how to use various commands.");
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.println("I'm sorry I believe you are missing some parameters, please try again.\n"
                    + "You can also say \"help\" to see how to use various commands.");
        } else if (e instanceof NumberFormatException) {
            System.out.println("This command requires an Integer that falls within the number of tasks in the list\n"
                    + "You can say \"list\" to see all your saved tasks.\n"
                    + "You can also say \"help\" to see how to use various commands.");
        }
    }


}
