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
                int i = 1;
                for (Task t : taskStore) {
                    System.out.println(i + "." + t);
                    ++i;
                }
                break;
            case "mark":
                // Fallthrough
            case "unmark":
                int index = Integer.parseInt(words[1]) - 1;
                if (index >= taskStore.size()) {
                    System.out.println("Invalid index!");
                    break;
                }
                Task currentTask = taskStore.get(index);
                if (command.equals("mark")) {
                    System.out.println("Okie Dokie!");
                    currentTask.setAsDone();
                } else {
                    System.out.println("Oh no! Anyways..");
                    currentTask.setAsNotDone();
                }
                System.out.println("\t" + currentTask);
                break;
            case "todo":
                ToDo newToDo = new ToDo(input.substring(5));
                taskStore.add(newToDo);
                System.out.println("New task added!");
                System.out.println("\t" + newToDo);
                System.out.println("There are " + taskStore.size() + " task(s) in the list");
                break;
            case "deadline":
                String[] deadlineTokens = input.split("/by ");
                String deadlineDescription = deadlineTokens[0].substring(9);
                String deadlineDate = deadlineTokens[1];
                Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
                taskStore.add(newDeadline);
                System.out.println("New deadline added!");
                System.out.println("\t" + newDeadline);
                System.out.println("There are " + taskStore.size() + " task(s) in the list");
                break;
            case "event":
                String[] eventTokens = input.split("/at ");
                String eventDescription = eventTokens[0].substring(6);
                String eventDate = eventTokens[1];
                Event newEvent = new Event(eventDescription, eventDate);
                taskStore.add(newEvent);
                System.out.println("New event added!");
                System.out.println("\t" + newEvent);
                System.out.println("There are " + taskStore.size() + " task(s) in the list");
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
