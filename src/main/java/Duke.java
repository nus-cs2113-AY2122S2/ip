import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?\n");
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String exitMessage = "bye";
            if(line.equals(exitMessage)){
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }
            System.out.println(line);
        }
    }
}
