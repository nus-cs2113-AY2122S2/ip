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
            System.out.println((i+1) + ".[" + tasks[i].typeOfTask() + "][" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].fullDescription());
        }
        System.out.println("-----------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int taskCounter = 0;
        Task[] tasks = new Task[100];

        greet();
        String input = in.nextLine();
        int inputTaskNumber;

        while (!input.equals("bye")) {

            String[] arrOfInputStrings = input.split(" ");

            switch(arrOfInputStrings[0]) {
            case "list":
                displayList(tasks, taskCounter);
                break;
            case "todo":
                tasks[taskCounter] = new Todo(input.substring(5));
                taskCounter++;
                break;
            case "deadline":
                String[] splitDeadlineDescription = input.substring(9).split(" /by ");
                tasks[taskCounter] = new Deadline(splitDeadlineDescription[0], splitDeadlineDescription[1]);
                taskCounter++;
                break;
            case "event":
                String[] splitEventDescription = input.substring(6).split(" /at ");
                tasks[taskCounter] = new Event(splitEventDescription[0], splitEventDescription[1]);
                taskCounter++;
                break;
            case "mark":
                inputTaskNumber = Integer.parseInt(arrOfInputStrings[1]) - 1;
                tasks[inputTaskNumber].markAsDone();
                break;
            case "unmark":
                inputTaskNumber = Integer.parseInt(arrOfInputStrings[1]) - 1;
                tasks[inputTaskNumber].markAsUndone();
                break;
            default:
                System.out.println("-----------------------------------------------------");
                System.out.println("Invalid input\nPlease use the format: [type_of_task] [task_description]");
                System.out.println("-----------------------------------------------------");
                break;
            }

            input = in.nextLine();
        }
        exit();

    }
}
