import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String indentation = "._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._.._._.";
        System.out.println(indentation);
        System.out.println("Hello! I'm Tom");
        System.out.println("What can I do for you?");
        System.out.println(indentation);

        while(true) {
            Scanner input1 = new Scanner(System.in);
            String command = input1.nextLine();
            System.out.println(indentation);
            if (command.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            break;
        }else{
                    System.out.println(command);
                    System.out.println(indentation);
            }
        }
    }
        //System.out.println(indentation);
}
