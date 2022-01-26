import java.util.Scanner;

public class Main {

    public static void greet() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Hello! I'm Alexis, your trusty helper");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------------------------");
    }

    public static void exit() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-----------------------------------------------------");
    }

    public static void displayList(Task[] tasks, int numOfTasks) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println((i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].returnDescription());
        }
        System.out.println("-----------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int taskCounter = 0;
        Task[] tasks = new Task[100];

        greet();
        String input = in.nextLine();
        while (!input.equals("bye")) {

            String[] arrOfInputStrings = input.split(" ");

            if (input.equals("list")) {
                displayList(tasks, taskCounter);
            } else if (arrOfInputStrings[0].equals("mark")) {
                int inputTaskNumber = Integer.parseInt(arrOfInputStrings[1]) - 1;
                tasks[inputTaskNumber].markAsDone();
            } else if (arrOfInputStrings[0].equals("unmark")) {
                int inputTaskNumber = Integer.parseInt(arrOfInputStrings[1]) - 1;
                tasks[inputTaskNumber].markAsUndone();
            } else {
                tasks[taskCounter] = new Task(input);
                taskCounter++;
            }

            input = in.nextLine();
        }
        exit();

    }
}
