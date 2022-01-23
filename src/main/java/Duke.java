import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        initialDisplay();

        //get reply from user and change
        String reply = input.nextLine();
        while(!reply.equalsIgnoreCase("BYE")){
            System.out.println("____________________________________________________________");
            System.out.println(reply);
            System.out.println("____________________________________________________________");
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
