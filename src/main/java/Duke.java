import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lineSeparator = "___________________________________";
        String startDuke =
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n"
                    + "___________________________________\n"
                    + "Hello! Duke here!\n"
                    + "How can I help?\n"
                    + "___________________________________\n";

        String endDuke =
                  "___________________________________\n"
                + "Goodbye. See you next time!\n"
                + "___________________________________\n";

        System.out.println(startDuke);

        Scanner userInput = new Scanner(System.in);
        String line;
        String[] taskArray = new String[100];
        int taskCount = 0;

        while (true) {
            line = userInput.nextLine();
            if ("bye".equals(line)) {
                System.out.println(endDuke);
                break;
            } else if ("list".equals(line)) {
                System.out.println(lineSeparator);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i+1 + ". " + taskArray[i]);
                }
                System.out.println(lineSeparator);
            } else {
                taskArray[taskCount] = line;
                taskCount++;
                System.out.println(lineSeparator);
                System.out.println("Added: " + line);
                System.out.println(lineSeparator);
            }
            System.out.println();
        }
    }
}
