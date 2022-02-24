import DukeException.DukeInvalidInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Parser parser = new Parser();

    private static TaskManager taskManager = new TaskManager();

    private void initialDisplay(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private void endDisplay(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Receive user input and interact with user.
     */
    public void run(){
        Scanner input = new Scanner(System.in);

        initialDisplay();

        //get reply from user and change
        String reply = input.nextLine();

        while(!reply.equalsIgnoreCase("BYE")){
            try{
                ArrayList<String> userCommand = parser.parseInput(reply);
                taskManager.manageCommand(userCommand);
            }catch (DukeInvalidInputException e){
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }catch (IndexOutOfBoundsException e){
                System.out.println("OOPS!!! The index you entered is invalid!");
            }
            finally {
                reply = input.nextLine();
            }
        }

        endDisplay();

    }
}
