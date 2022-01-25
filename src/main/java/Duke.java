import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "-----------------------------";
        Scanner in = new Scanner(System.in);
        String input;
        String[] list = new String[100];
        int index = 0;

        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello there! I'm Duke :D");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you soon :D");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < index; i++) {
                    int numbering = i + 1;
                    System.out.println(numbering + ". " + list[i]);
                }
                System.out.println(line);
            }
            else {
                list[index] = input;
                index++;
                System.out.println("Added: " + input + System.lineSeparator() + line);
            }
        }
    }
}
