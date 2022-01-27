import java.util.Scanner;
import java.lang.String;

public class Brave {
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        Task[] task = new Task[100];
        int numberOfTask = 0;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Brave");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        command = in.nextLine();

        while (!command.equals("bye")) {
            if (command.split(" ")[0].equals("mark")) {
                String[] words = command.split(" ");
                int taskIndex = Integer.parseInt(words[1]) - 1;
                task[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task[taskIndex].getStatusIcon() + " " + task[taskIndex].getDescription());
            }

            else if (command.split(" ")[0].equals("unmark")) {
                String[] words = command.split(" ");
                int taskIndex = Integer.parseInt(words[1]) - 1;
                task[taskIndex].unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task[taskIndex].getStatusIcon() + " " + task[taskIndex].getDescription());
            }

            else if (command.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int i=0; i<numberOfTask; i++) {
                    System.out.println("\t" + (i+1) + "." + task[i].getStatusIcon() + " " + task[i].getDescription());
                }
                System.out.println("\t____________________________________________________________");
            }
            else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tadded: " + command);
                System.out.println("\t____________________________________________________________");
                task[numberOfTask] = new Task(command);
                numberOfTask++;
            }

            command = in.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye, Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
