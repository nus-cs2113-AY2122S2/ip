import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lineSeparator = "___________________________________\n";
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
        while (true) {
            line = userInput.nextLine();
            if ("bye".equals(line)) {
                System.out.println(endDuke);
                break;
            }
            System.out.println(lineSeparator + line + "\n" + lineSeparator);
        }
    }
}
