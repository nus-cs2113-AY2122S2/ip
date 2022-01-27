import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100]; //holds all tasks given
        int taskCounter = 0; //counts number of tasks
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";


        System.out.println(greeting);

        while (true) {
            String line = sc.nextLine();
            switch (line) {
            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println("  Bye; Don't restart me.");
                System.out.println("____________________________________________________________\n");
                System.exit(0);
            case "list":
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println("   " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________\n");
                break;
            default:
                tasks[taskCounter] = line;
                taskCounter++;
                System.out.println("____________________________________________________________");
                System.out.println("  added: " + line);
                System.out.println("____________________________________________________________\n");
            }
        }
    }
}
