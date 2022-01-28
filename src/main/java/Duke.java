import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskStore = new ArrayList<Task>();

        System.out.println("----------------------------------");
        System.out.println("Hi! I'm Bim!");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------");

        while (in.hasNext()) {
            String input = in.nextLine();
            String[] words = input.split(" ");
            String command = words[0];

            switch (command) {
            case "bye":
                System.out.println("Have a nice day!");
                System.exit(0);
            case "list":
                if (taskStore.isEmpty()) {
                    System.out.println("404 Not Found");
                }
                for (int i = 0; i < taskStore.size(); i++) {
                    Task currentTask = taskStore.get(i);
                    System.out.println((i + 1) + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                }
                break;
            case "mark":
                // Fallthrough
            case "unmark":
                int index = Integer.parseInt(words[1]) - 1;
                Task currentTask = taskStore.get(index);
                if (command.equals("mark")) {
                    System.out.println("Okie Dokie!");
                    currentTask.setAsDone();
                } else {
                    System.out.println("Oh no! Anyways..");
                    currentTask.setAsNotDone();
                }
                System.out.println("\t[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                break;
            default:
                Task newTask = new Task(input);
                taskStore.add(newTask);
                System.out.println("added: " + input);
                break;
            }
            System.out.println("----------------------------------");
        }
    }
}
