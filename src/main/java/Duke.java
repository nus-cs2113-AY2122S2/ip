import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
         */

        Scanner in = new Scanner(System.in);

        System.out.println("----------------------------------");
        System.out.println("Hello! I'm Bim!");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------");
        while(in.hasNext()) {
            String input = in.nextLine();

            if (input.equals("bye")) {
                System.out.println("Have a nice day!");
                break;
            } else {
                System.out.println(input);
            }
            System.out.println("----------------------------------");
        }
    }
}
