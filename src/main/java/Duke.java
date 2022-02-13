import DukeException.DukeEmptyException;
import DukeException.DukeInvalidInputException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        try{
            taskManager.readData();
        }catch(IOException e) {
            e.printStackTrace();
        }

        initialDisplay();

        //get reply from user and change
        String reply = input.nextLine();

        while(!reply.equalsIgnoreCase("BYE")){

            if(reply.equalsIgnoreCase("LIST")){
                taskManager.printTasks();
            }
            else if(reply.split(" ")[0].equalsIgnoreCase("DELETE")){
                try{
                    taskManager.deleteTask(Integer.parseInt(reply.split(" ")[1]));
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("OOPS!!! The delete index you entered cannot be empty.");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("OOPS!!! The delete index you entered is invalid.");
                }
            }
            //check if the reply is about mark/ unmark.
            //If it is related to mark/unmark, relatedToMark will mark/unmark related task
            else if(!taskManager.relatedToMark(reply)) try {
                taskManager.addTask(reply);
            } catch(DukeInvalidInputException e){
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch(DukeEmptyException e){ //The task has no description
                System.out.println("OOPS!!! The description of a "+e.getMessage()+" cannot be empty.");
            } catch(StringIndexOutOfBoundsException e){ //The task of DEADLINE/ EVENT has no time details
                System.out.println("OOPS!!! The time detail of this task cannot be empty.");
            }
            reply = input.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        try {
            taskManager.saveData();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void initialDisplay(){
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


}
