import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        initialDisplay();

        //get reply from user and change
        String reply = input.nextLine();

        while(!reply.equalsIgnoreCase("BYE")){

            if(reply.equalsIgnoreCase("LIST")){
                taskManager.printTasks();
                String choice = taskManager.taskChoice();
                while(choice=="finishedLoop"){
                    choice = taskManager.taskChoice();
                }
                reply = choice;
                continue;
            }

            taskManager.addTask(reply);

            reply = input.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
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
