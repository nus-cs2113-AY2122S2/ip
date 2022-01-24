import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
         */

        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskStore = new ArrayList<Task>();

        System.out.println("----------------------------------");
        System.out.println("Hi! I'm Bim!");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------");
        while(in.hasNext()) {
            String input = in.nextLine();

            if (input.equals("bye")) {
                System.out.println("Have a nice day!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < taskStore.size(); i++) {
                    Task currentTask = taskStore.get(i);
                    System.out.println((i + 1) + ". " + currentTask.getDescription());
                }
            } else {
                Task newTask = new Task(input);
                taskStore.add(newTask);
                System.out.println("added: " + input);
            }

            System.out.println("----------------------------------");
        }
    }
}
