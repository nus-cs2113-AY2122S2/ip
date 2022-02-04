import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int index = 0;
        String greetings =
              "______________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "______________________________________________________________";
        System.out.println(greetings);
        String echo = greetings;

        Scanner sc = new Scanner(System.in);

        while(true) {
            echo = sc.nextLine();
            System.out.println("----------------------------------------------------------------");
            if(echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("----------------------------------------------------------------\n");
                break;
            } else if (echo.equals("list")){
                printTasks(tasks);
            } else {
                addTask(new Task(echo), tasks, index);
                index++;
            }
            System.out.println("----------------------------------------------------------------\n");
        }
    }

    public static void addTask(Task task, Task[] tasks, int index) {
        tasks[index] = task;
        System.out.println("added: " + task);
    }

    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(tasks);
        }
    }
}