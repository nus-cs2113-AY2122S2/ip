import java.util.Scanner;
public class Duke {
    public static void printWithDivider(String stringWithinDivider) {
        String breakLine = "\t____________________________________________________________";
        System.out.println(breakLine);
        stringWithinDivider = stringWithinDivider.replace("\n", "\n\t");
        System.out.println("\t" + stringWithinDivider);
        System.out.println(breakLine);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printWithDivider("Hello! I'm Duke\nWhat can I do for you?");

        //Level-1
        Scanner sc = new Scanner (System.in);

        String line = sc.nextLine();
        while (!line.equals("bye")){
            printWithDivider(line);
            line = sc.nextLine();
        }

        printWithDivider("Bye. Hope to see you again soon!");

    }
}
