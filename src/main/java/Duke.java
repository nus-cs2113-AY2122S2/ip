import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        String[] task = new String[100];
        int numberOfTask = 0;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        command = in.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int i=0; i<numberOfTask; i++) {
                    System.out.println("\t" + (i+1) + "." + task[i]);
                }
                System.out.println("\t____________________________________________________________");
            }
            else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tadded: " + command);
                System.out.println("\t____________________________________________________________");
                task[numberOfTask] = command;
                numberOfTask++;
            }

            command = in.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye, Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
