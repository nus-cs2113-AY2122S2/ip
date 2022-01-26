import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String endOfSection = "\n___________________________________________________\n";

        System.out.println(logo + "\nHello! I'm Duke\nWhat can I do for you?" + endOfSection);

        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(line + endOfSection);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!" + endOfSection);
    }
}
