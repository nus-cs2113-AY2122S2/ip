import java.util.Scanner;
import duke.task.*;
import duke.exceptions.*;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
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
                    if ((curTask.equals("mark") && (tasks.get(indexOfTask - 1).isDone)) ||
                        (curTask.equals("unmark") && (!tasks.get(indexOfTask - 1).isDone))) {
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
                } else if (curTask.equals("delete")) {
                    deleteTask(tasks, index);
                    index = index - 2;
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

    public static void deleteTask(ArrayList<Task> tasks, int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
    }

    public static void addTodo(String echo, ArrayList<Task> tasks, int index) {
        String description = echo.substring(echo.indexOf(" ") + 1);
        tasks.add(new Todo(description));
        System.out.println("Got it. I've added this task:  ");
        System.out.println(tasks.get(index));
    }


    public static void addEvent(String echo, ArrayList<Task> tasks, int index) {

        String event = echo.substring(echo.indexOf("/") + 4);
        String description = echo.substring(echo.indexOf(" "), echo.indexOf("/"));
        tasks.add(new Event(description, event));

        System.out.println("Got it. I've added this task:  ");
        System.out.println(tasks.get(index));
    }


    public static void addDeadline(String echo, ArrayList<Task> tasks, int index) {
        String deadline = echo.substring(echo.indexOf("/") + 4);
        String description = echo.substring(echo.indexOf(" "), echo.indexOf("/"));
        tasks.add(new Deadline(description, deadline));

        System.out.println("Got it. I've added this task:  ");
        System.out.println(tasks.get(index));
    }


    public static void toggleStatus(ArrayList<Task> tasks, int indexOfTask) {
        tasks.get(indexOfTask - 1).changeStatus();

        if (tasks.get(indexOfTask -1).isDone) {
            System.out.println("OK, I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.print(indexOfTask);
        System.out.println("." + tasks.get(indexOfTask - 1));
    }


    public static void printTasks(ArrayList<Task> tasks, int index) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks.get(i));
        }
        System.out.println("\n----------------------------------------------------------------");
    }

}