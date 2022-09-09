import java.util.Scanner;

public class Duke {
    public static void printMsg(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = " Hello! I'm Duke\n" +
                " What can I do for you?";
        String bye = " Bye. Hope to see you again soon!";

        Task[] array = new Task[100];
        int cur = 0;

        System.out.println("Hello from\n" + logo);
        printMsg(greeting);
        Scanner myObj = new Scanner(System.in);

        String input = "";
        String[] curCommand = {"First", "Second"};
        int index = 0;

        while (!curCommand[0].equals("bye")) {
            input = myObj.nextLine();
            curCommand = input.split(" ", 2);
            System.out.println("____________________________________________________________");
            switch (curCommand[0]) {
                case "bye":
                    System.out.println(bye);
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < cur; i++) {
                        System.out.println((i + 1) + "." + array[i].toString());
                    }
                    break;
                case "mark":
                    System.out.println("Nice! I've marked this task as done:");
                    index = Integer.parseInt(curCommand[1]) - 1;
                    array[index].setDone();
                    System.out.println(array[index].toString());
                    break;
                case "unmark":
                    System.out.println("OK, I've marked this task as not done yet:");
                    index = Integer.parseInt(curCommand[1]) - 1;
                    array[index].setNotDone();
                    System.out.println(array[index].toString());
                    break;
                case "todo":
                    if (curCommand.length < 2) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        break;
                    }
                    array[cur] = new ToDo(curCommand[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(array[cur].toString());
                    System.out.println("Now you have " + (cur + 1) + " tasks in the list.");
                    cur += 1;
                    break;
                case "deadline":
                    if (curCommand.length < 2) {
                        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                        break;
                    }
                    String[] deadlineList = curCommand[1].split(" /by ", 2);
                    array[cur] = new Deadline(deadlineList[0], deadlineList[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(array[cur].toString());
                    System.out.println("Now you have " + (cur + 1) + " tasks in the list.");
                    cur += 1;
                    break;
                case "event":
                    if (curCommand.length < 2) {
                        System.out.println("OOPS!!! The description of an event cannot be empty.");
                        break;
                    }
                    String[] eventList = curCommand[1].split(" /at ", 2);
                    array[cur] = new Event(eventList[0], eventList[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(array[cur].toString());
                    System.out.println("Now you have " + (cur + 1) + " tasks in the list.");
                    cur += 1;
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
            System.out.println("____________________________________________________________");
        }
    }
}
