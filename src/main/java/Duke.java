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
            System.out.println("----------------------------------------------------------------");

            try{

                if (echo.equals("todo")) {
                    throw new DukeException("ERROR", "todo");
                } else if (echo.equals("deadline")) {
                    throw new DukeException("ERROR", "deadline");
                } else if (echo.equals("event")) {
                    throw new DukeException("ERROR", "event");
                }

                String curTask = echo.substring(0, (echo.indexOf(" ")));

                if (curTask.equals("mark") || curTask.equals("unmark")) {
                    int indexOfSpace = echo.indexOf(" ");
                    int indexOfTask = Integer.parseInt(echo.substring(indexOfSpace + 1));
                    if ((curTask.equals("mark") && (tasks[indexOfTask - 1].isDone)) ||
                        (curTask.equals("unmark") && (!tasks[indexOfTask - 1].isDone))) {
                        throw new ChangeStatusException("This task is already marked/unmarked");
                    }
                    toggleStatus(tasks, indexOfTask);
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

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
                System.out.println("----------------------------------------------------------------");
            } catch (NullPointerException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but seems like there is no such task :-( ");
                System.out.println("----------------------------------------------------------------");
            } catch (ChangeStatusException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but seems like this task is already marked/unmarked");
                System.out.println("----------------------------------------------------------------");
            } catch (DukeException e) {
                System.out.print("☹ OOPS!!! The description of a ");
                System.out.print(e);
                System.out.println(" cannot be empty");
                System.out.println("----------------------------------------------------------------");
            }
        }
    }


    public static void addTodo(String echo, Task[] tasks, int index) {
        String description = echo.substring(echo.indexOf(" ") + 1);
        tasks[index] = new Todo(description);

        System.out.println("Got it. I've added this task:  ");
        System.out.println(tasks[index]);
    }


    public static void addEvent(String echo, Task[] tasks, int index) {

        String event = echo.substring(echo.indexOf("/") + 4);
        String description = echo.substring(echo.indexOf(" "), echo.indexOf("/"));
        tasks[index] = new Event(description, event);

        System.out.println("Got it. I've added this task:  ");
        System.out.println(tasks[index]);
    }


    public static void addDeadline(String echo, Task[] tasks, int index) {
        String deadline = echo.substring(echo.indexOf("/") + 4);
        String description = echo.substring(echo.indexOf(" "), echo.indexOf("/"));
        tasks[index] = new Deadline(description, deadline);

        System.out.println("Got it. I've added this task:  ");
        System.out.println(tasks[index]);
    }


    public static void toggleStatus(Task[] tasks, int indexOfTask) {
        tasks[indexOfTask - 1].changeStatus();

        if (tasks[indexOfTask -1].isDone) {
            System.out.println("OK, I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.print(indexOfTask);
        System.out.println("." + tasks[indexOfTask - 1]);
    }


    public static void printTasks(Task[] tasks, int index) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks[i]);
        }
        System.out.println("\n----------------------------------------------------------------");
    }

}