import java.util.Scanner;

public class Duke {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int index = 0;
        String greetings =
              "______________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "______________________________________________________________";
        System.out.println(greetings);
        String echo;

        Scanner sc = new Scanner(System.in);

        while(true) {
            echo = sc.nextLine();
            System.out.println("----------------------------------------------------------------");
            if(echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("----------------------------------------------------------------\n");
                break;
            } else if (echo.equals("list")){
                printTasks(tasks, index);
            } else if (echo.substring(0, (echo.indexOf(" "))).equals("mark")) {
                markTask(echo, tasks);
            } else if (echo.substring(0, (echo.indexOf(" "))).equals("unmark")) {
                unMarkTask(echo, tasks);
            } else {
                addTask(new Task(echo), tasks, index);
                index++;
            }
            System.out.println("----------------------------------------------------------------\n");
        }
    }

    /**
     * @param echo
     * @param tasks
     */
    public static void markTask(String echo, Task[] tasks) {
        System.out.println("Nice! I've marked this task as done: ");
        int indexOfSpace = echo.indexOf(" ");
        int indexOfTask = Integer.parseInt(echo.substring(indexOfSpace + 1));
        tasks[indexOfTask - 1] = tasks[indexOfTask - 1].markDone();
        System.out.print(indexOfTask);
        System.out.println("." + tasks[indexOfTask - 1].getStatusIcon() + tasks[indexOfTask - 1]);
    }

    /**
     * @param echo
     * @param tasks
     */
    public static void unMarkTask(String echo, Task[] tasks) {
        System.out.println("OK, I've marked this task as not done yet:");
        int indexOfSpace = echo.indexOf(" ");
        int indexOfTask = Integer.parseInt(echo.substring(indexOfSpace + 1));
        tasks[indexOfTask - 1] = tasks[indexOfTask - 1].markUndone();
        System.out.print(indexOfTask);
        System.out.println("." + tasks[indexOfTask - 1].getStatusIcon() + tasks[indexOfTask - 1]);
    }


    /**
     * @param task
     * @param tasks
     * @param index
     */
    public static void addTask(Task task, Task[] tasks, int index) {
        tasks[index] = task;
        System.out.println("added: " + task);
    }

    /**
     * @param tasks
     * @param index
     */
    public static void printTasks(Task[] tasks, int index) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks[i].getStatusIcon() + tasks[i]);
        }
    }

}