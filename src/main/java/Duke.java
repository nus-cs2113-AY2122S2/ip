import java.util.Scanner;

public class Duke {
    private static final String[] storage = new String[100];
    private static int num = 0;
    private static final String line = "_______________"
                               + "_______________"
                               + "_______________"
                               + "_______________";

    public static void list() {
        int index = 1;
        for (int i = 0; i < num; i++) {
            System.out.println(index + ". " + storage[i]);
            index++;
        }
    }

    public static void taskManager(String input) {
        System.out.println(line);
        if (input.equals("list")) {
            list();
        } else {
            storage[num++] = input;
            System.out.println("added: " + input);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println(line);
        System.out.print("Hello! I'm Bob,\n"
                + "your friendly neighbourhood assistant.\n"
                + "How can I help you today?\n");
        System.out.println(line);
        input = in.nextLine();
        while (!input.equals("bye")) {
            taskManager(input);
            input = in.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon! :)");
        System.out.print(line);
    }
}
