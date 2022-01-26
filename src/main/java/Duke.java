import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

         */

        Scanner s = new Scanner(System.in);

        String INDENT_LINE = "____________________________________________________________\n";
        String GREET_STRING = INDENT_LINE +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                INDENT_LINE;
        String BYE_STRING = INDENT_LINE +
                "     Bye. Hope to see you again soon!\n" +
                INDENT_LINE;
        String input = "";

        System.out.println(GREET_STRING);
        input = s.nextLine();
        while (!input.equals("bye")){
            System.out.println(INDENT_LINE+"    "+input+System.lineSeparator()+INDENT_LINE);
            input = s.nextLine();
        }

        System.out.println(BYE_STRING);

    }
}
