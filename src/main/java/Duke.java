import java.lang.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "  Hello, I'm Baymax.\n"+
                          "  Your personal task managing companion. \n" +
                          "  What can I do for you? \n";
        String ending = "Bye. Hope to see you again soon! \n";
        String horiLine = "____________________________________________________________\n";

        System.out.println(horiLine + greeting + horiLine);
        //greetings

        String[] list = new String[100];
        int commCount = 0;

        Scanner task = new Scanner(System.in);
        String command = task.nextLine();
        while(!command.equals("bye")){
            System.out.println(horiLine + "added: "+ command+ System.lineSeparator() + horiLine);
            list[commCount] = command;
            commCount++;
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
