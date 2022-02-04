import java.lang.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "  Hello! I'm Duke\n"
                       +  "  What can I do for you? \n";
        String ending = "Bye. Hope to see you again soon! 、\n";
        String horiLine = "____________________________________________________________\n";

        System.out.println(horiLine + greeting + horiLine);

        Scanner task = new Scanner(System.in);
        String command = task.nextLine();
        while(!command.equals("bye")){
            System.out.println(horiLine + command+ System.lineSeparator() + horiLine);
            command = task.nextLine();
        }

        System.out.println(horiLine + ending + horiLine);


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
