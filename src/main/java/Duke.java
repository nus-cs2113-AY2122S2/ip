import java.util.Scanner;

public class Duke {
    public static void storeInput() {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];

        System.out.println("Storing Up to 100 Tasks, type list to show all tasks,mark or unmark to set task status"
                + " and bye to exit");

        do {
            System.out.println("Waiting for your input");
            line = in.nextLine();
            String[] words = line.toLowerCase().split(" ");
            int taskNumber = 0;

            switch (words[0]) {
            case "list":
                printTasks(tasks);
                break;
            case "mark":
                if (words.length == 2) {
                    taskNumber = Integer.parseInt(words[1]);
                }
                if (taskNumber > Task.getNumOfTasks()) {
                    System.out.println("You don't have that many tasks ><!");
                    break;
                }
                tasks[taskNumber - 1].setDone();
                System.out.println("I have marked the task as done!");
                System.out.println(tasks[taskNumber - 1].getStatusIcon() + tasks[taskNumber - 1].getTaskDescription());
                break;
            case "unmark":
                if (words.length == 2) {
                    taskNumber = Integer.parseInt(words[1]);
                }
                if (taskNumber > Task.getNumOfTasks()) {
                    System.out.println("You don't have that many tasks ><!");
                    break;
                }
                tasks[taskNumber - 1].setUndone();
                System.out.println("I have marked the task as not done!");
                System.out.println(tasks[taskNumber - 1].getStatusIcon() + tasks[taskNumber - 1].getTaskDescription());
                break;
            default:
                int numOfTasks = Task.getNumOfTasks();
                tasks[numOfTasks] = new Task(line);
                break;
            }
        } while ((!line.equalsIgnoreCase("bye")) && Task.getNumOfTasks() < 100);
    }

    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < Task.getNumOfTasks(); i += 1) {
            System.out.println(i+1 + ". " + tasks[i].getStatusIcon() + tasks[i].getTaskDescription());
        }
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n"
                + "Hello, nice to meet you. I'm Yae! (*^▽^*)\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
        storeInput();
        System.out.println("Goodbye, see you next time!");
    }
}
