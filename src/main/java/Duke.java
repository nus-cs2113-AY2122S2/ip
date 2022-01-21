import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        ArrayList<Task> store = new ArrayList<Task>();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println(i + 1 + ". " + store.get(i).getTask());
                }
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                Task currentTask = store.get(taskIndex - 1);
                currentTask.setDone(true);
                System.out.println(currentTask.getTask());
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                Task currentTask = store.get(taskIndex - 1);
                currentTask.setDone(false);
                System.out.println(currentTask.getTask());
            } else if (userInput.startsWith("todo")) {
                String task = userInput.split("todo")[1].trim();
                ToDo newTask = new ToDo(task);
                store.add(newTask);
                printTask(newTask, store.size());
            } else if (userInput.startsWith("deadline")) {
                String[] processedString = userInput.split("/by");
                String by = processedString[1].trim();
                String task = processedString[0].split("deadline")[1].trim();
                Deadline newTask = new Deadline(task, by);
                store.add(newTask);
                printTask(newTask, store.size());
            } else if (userInput.startsWith("event")) {
                String[] processedString = userInput.split("/at");
                String at = processedString[1].trim();
                String task = processedString[0].split("event")[1].trim();
                Event newTask = new Event(task, at);
                store.add(newTask);
                printTask(newTask, store.size());
            } else {
                Task newTask = new Task(userInput);
                store.add(newTask);
                System.out.println("added: " + userInput);
            }
            userInput = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printTask(Task newTask, int length) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getTask());
        System.out.println("Now you have " + length + " tasks in the list");
    }
}
