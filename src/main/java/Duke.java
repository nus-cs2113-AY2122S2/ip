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

            if (echo.equals("list")) {
                printTasks(tasks, index);
                continue;
            } else if(echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("----------------------------------------------------------------\n");
                break;
            }

            String curTask = echo.substring(0, (echo.indexOf(" ")));
            //add StringIndexOutOfBoundsException here

            System.out.println("----------------------------------------------------------------");

            if (curTask.equals("mark")) {
                System.out.println("Nice! I've marked this task as done: ");
                //add an exception here to catch isDone == true;
                changeTaskStatus(echo, tasks);
                continue;
            } else if (curTask.equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                //add an exception here to catch isDone == false;
                changeTaskStatus(echo, tasks);
                continue;
            } else if (curTask.equals("deadline")) {
                addDeadline(echo, tasks, index);
            } else if (curTask.equals("event")) {
                addEvent(echo, tasks, index);
            } else if (curTask.equals("todo")) {
                addTodo(echo, tasks, index);
            }

            index++;
            System.out.print("Now you have ");
            System.out.println(index + " tasks in the list.");
            System.out.println("----------------------------------------------------------------");
        }
    }

    /**
     * @param echo
     * @param tasks
     * @param index
     */
    public static void addTodo(String echo, Task[] tasks, int index) {
        System.out.println("Got it. I've added this task:  ");
        String description = echo.substring(echo.indexOf(" ") + 1);

        tasks[index] = new Todo(description);
        System.out.println(tasks[index]);
    }


    /**
     * @param echo
     * @param tasks
     * @param index
     */
    public static void addEvent(String echo, Task[] tasks, int index) {
        System.out.println("Got it. I've added this task:  ");
        String event = echo.substring(echo.indexOf("/") + 4);
        String description = echo.substring(echo.indexOf(" "), echo.indexOf("/"));

        tasks[index] = new Event(description, event);
        System.out.println(tasks[index]);
    }


    /**
     * @param echo
     * @param tasks
     * @param index
     */
    public static void addDeadline(String echo, Task[] tasks, int index) {
        System.out.println("Got it. I've added this task:  ");
        String deadline = echo.substring(echo.indexOf("/") + 4);
        String description = echo.substring(echo.indexOf(" "), echo.indexOf("/"));

        tasks[index] = new Deadline(description, deadline);
        System.out.println(tasks[index]);
    }


    /**
     * @param echo
     * @param tasks
     */
    public static void changeTaskStatus(String echo, Task[] tasks) {
        int indexOfSpace = echo.indexOf(" ");
        int indexOfTask = Integer.parseInt(echo.substring(indexOfSpace + 1));
        tasks[indexOfTask - 1].changeStatus();
        System.out.print(indexOfTask);
        System.out.println("." + tasks[indexOfTask - 1]);
    }


    /**
     * @param tasks
     * @param index
     */
    public static void printTasks(Task[] tasks, int index) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks[i]);
        }
    }

}