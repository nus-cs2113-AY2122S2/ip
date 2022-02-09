package duke;

import java.util.Scanner;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.exceptions.GeneralException;
import duke.exceptions.TaskEmptyException;
import duke.exceptions.EventFormatException;
import duke.exceptions.DeadlineFormatException;

public class Duke {
    public static String boundary = "____________________________________________________________" + System.lineSeparator();
    public static Task[] taskList = new Task[100];
    public static int countTask = 0;

    public static void printList() {
        System.out.println(boundary + "Here are the tasks in your list:");
        for (int i = 0; i < countTask; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        System.out.print("Now you have " + countTask + " tasks in the list."+ System.lineSeparator() + boundary);
    }

    public static void markTask(int toMark) {
        taskList[toMark].markDone();
        System.out.println(boundary + "Nice! I've marked this task as done:" + System.lineSeparator() + taskList[toMark]);
        System.out.print(boundary);
    }

    public static void unmarkTask(int toUnmark) {
        taskList[toUnmark].markNotDone();
        System.out.println(boundary + "OK, I've marked this task as not done yet:" + System.lineSeparator() + taskList[toUnmark]);
        System.out.print(boundary);
    }

    public static void checkDescription(String request) throws TaskEmptyException {
        if (request.toLowerCase().endsWith("deadline") ||
                    request.toLowerCase().endsWith("event") ||
                    request.toLowerCase().endsWith("todo")) {
            throw new TaskEmptyException();
        }
    }

    public static void addTask(String request) throws GeneralException,
                                                              TaskEmptyException, DeadlineFormatException, EventFormatException {
        checkDescription(request);
        if (request.toLowerCase().startsWith("deadline")) {
            if ((!request.contains("/by"))) {
                throw new DeadlineFormatException();
            } else if (request.substring(9, (request.indexOf("/by"))).trim().equals("")) {
                throw new TaskEmptyException();
            }
            int byPosition = request.indexOf("/");
            taskList[countTask] = new Deadline(request.substring(9, byPosition - 1), request.substring(byPosition + 4));
        } else if (request.toLowerCase().startsWith("event")) {
            if (!request.contains("/at")) {
                throw new EventFormatException();
            } else if (request.substring(6, (request.indexOf("/at"))).trim().equals("")) {
                throw new TaskEmptyException();
            }
            int atPosition = request.indexOf("/");
            taskList[countTask] = new Event(request.substring(6, atPosition - 1), request.substring(atPosition + 4));
        } else if (request.toLowerCase().startsWith("todo")) {
            taskList[countTask] = new Todo(request.substring(5));
        } else {
            throw new GeneralException();
        }

        countTask++;

        System.out.println(boundary + "Got it. I've added this task: " + System.lineSeparator() + taskList[countTask - 1]);
        System.out.print("Now you have " + countTask + " tasks in the list."+ System.lineSeparator() + boundary);
    }

    public static void tryAddTask(String request) {
        try {
            addTask(request.trim());
        } catch (GeneralException e) {
            System.out.print(boundary + "Hmm...I'm sorry but I cannot understand this :("
                                     + System.lineSeparator() + boundary);
        } catch (TaskEmptyException e) {
            System.out.print(boundary + "Hmm...hi dear, remember to put in your task description~"
                                     + System.lineSeparator() + boundary);
        } catch (DeadlineFormatException e) {
            System.out.print(boundary + "Hmm...hi dear, when do u want to finish this by?"
                                     + System.lineSeparator() + boundary);
        } catch (EventFormatException e) {
            System.out.print(boundary + "Hmm...hi dear, when is this event happening?"
                                     + System.lineSeparator() + boundary);
        }
    }

    public static void sayHello() {
        String logo = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(boundary + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?" + System.lineSeparator() + boundary);
    }

    public static void sayGoodbye() {
        System.out.print(boundary + "Bye. Hope to see you again soon!" + System.lineSeparator() + boundary);
    }

    public static void main(String[] args) {
        sayHello();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {
            if (line.equalsIgnoreCase("list")) {
                printList();
            } else if (line.toLowerCase().startsWith("mark")) {
                markTask(Integer.parseInt(line.substring(5)) - 1);
            } else if (line.toLowerCase().startsWith("unmark")) {
                unmarkTask(Integer.parseInt(line.substring(7)) - 1);
            } else {
                tryAddTask(line);
            }
            in = new Scanner(System.in);
            line = in.nextLine();
        }
        sayGoodbye();
    }
}
