import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int num = 0;
    private static final String line = "_______________"
                               + "_______________"
                               + "_______________"
                               + "_______________";

    public static void list() {
        int index = 1;
        for (int i = 0; i < num; i++) {
            System.out.println(index + ". ["
                               + tasks[i].getStatusIcon()
                               + "] "
                               + tasks[i].getDescription());
            index++;
        }
    }

    public static void taskManager(String input) {
        System.out.println(line);
        String[] words = input.split(" ");
        int i;
        switch(words[0]) {
        case "list":
            list();
            break;
        case "mark":
            i = Integer.parseInt(words[1]) - 1;
            tasks[i].markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                               + "  [" + tasks[i].getStatusIcon()
                               + "] "
                               + tasks[i].getDescription());
            break;
        case "unmark":
            i = Integer.parseInt(words[1]) - 1;
            tasks[i].unmark();
            System.out.println("Ok, I've marked this task as" +
                    " not done yet:\n  ["
                    + tasks[i].getStatusIcon()
                    + "] "
                    + tasks[i].getDescription());
            break;
        default:
            Task t = new Task(input);
            tasks[num++] = t;
            System.out.println("added: " + input);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(line);
        System.out.print("Hello! I'm Bob,\n"
                + "your friendly neighbourhood assistant.\n"
                + "How can I help you today?\n");
        System.out.println(line);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            taskManager(input);
            input = in.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon! :)");
        System.out.print(line);
    }
}
