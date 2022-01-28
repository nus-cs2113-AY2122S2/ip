import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        print("Hello from JRobo! I'm your personal assistant!\n\tNice to meet you. What can I do for you?");

        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.startsWith("mark")) {
                markTask(tasks, input);
                continue;
            }
            if (input.startsWith("unmark")) {
                unmarkTask(tasks, input);
                continue;
            }
            if (input.equals("list")) {
                displayList(tasks);
                continue;
            }
            if (input.equals("bye")) {
                break;
            }
            Task task = new Task(input);
            tasks.add(task);
            print("added: " + input);
        }

        print("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void print(String s) {
        System.out.println("\t____________________________________________________________\n\t" + s + "\n\t____________________________________________________________");
    }

    public static void displayList(ArrayList<Task> tasks) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        int index = 1;
        for (Task t : tasks) {
            System.out.println("\t" + index++ + ".[" + t.getStatusIcon() + "] " + t.getDescription());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void markTask(ArrayList<Task> tasks, String input) {
        String numberString = input.substring(4).trim();
        try {
            int taskNumber = Integer.parseInt(numberString);
            Task task = tasks.get(taskNumber - 1);
            if (task.getStatusIcon().equals("X")) {
                print("This task is already marked");
                return;
            }
            task.markAsDone();
            print("Nice! I've marked this task as done:\n\t\t[" + task.getStatusIcon() + "] "
                    + task.getDescription());
        } catch (NumberFormatException e) {
            print("Invalid command");
        } catch (IndexOutOfBoundsException e) {
            print("Please enter a valid task number to mark");
        }
    }

    public static void unmarkTask(ArrayList<Task> tasks, String input) {
        String numberString = input.substring(6).trim();
        try {
            int taskNumber = Integer.parseInt(numberString);
            Task task = tasks.get(taskNumber - 1);
            if (!task.getStatusIcon().equals("X")) {
                print("This task is already unmarked");
                return;
            }
            task.markAsUndone();
            print("Nice! I've marked this task as undone:\n\t\t[" + task.getStatusIcon() + "] "
                    + task.getDescription());
        } catch (NumberFormatException e) {
            print("Invalid command");
        } catch (IndexOutOfBoundsException e) {
            print("Please enter a valid task number to unmark");
        }
    }
}
