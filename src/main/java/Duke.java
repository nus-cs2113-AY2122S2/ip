import java.util.Scanner;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static Task[] list = new Task[100];
    private static int taskCounter = 0;
    private static String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n"
                + HORIZONTAL_LINE + "\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE);

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            
            } else if (input.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(String.valueOf(i + 1) + "." + list[i]);
                    } else {
                        System.out.println(HORIZONTAL_LINE);
                        break;
                    }
                }

            } else if (input.contains("unmark ")) {
                int markInt = Integer.parseInt(input.substring(7)) - 1;
                list[markInt].setDone(false);
                System.out.println(HORIZONTAL_LINE + "\n"
                        + "OK, I've marked this task as not done yet:\n"
                        + list[markInt] + "\n"
                        + HORIZONTAL_LINE);

            } else if (input.contains("mark ")) {
                int markInt = Integer.parseInt(input.substring(5)) - 1;
                list[markInt].setDone(true);
                System.out.println(HORIZONTAL_LINE + "\n"
                        + "Nice! I've marked this task as done:\n"
                        + list[markInt] + "\n"
                        + HORIZONTAL_LINE);

            } else {
                Task inputTask;
                if (input.contains("todo")) {
                    String inputString = input.substring(5);
                    inputTask = new ToDo(inputString);
                } else if (input.contains("deadline")) {
                    int slashInt = input.indexOf("/");
                    String inputString = input.substring(9, slashInt);
                    String inputDate = input.substring(slashInt + 4);
                    inputTask = new Deadline(inputString, inputDate);
                } else {
                    int slashInt = input.indexOf("/");
                    String inputString = input.substring(6, slashInt);
                    String inputDate = input.substring(slashInt + 4);
                    inputTask = new Event(inputString, inputDate);
                }
                list[taskCounter] = inputTask;
                taskCounter++;
                System.out.println(HORIZONTAL_LINE + "\n"
                        + "Got it. I've added this task:\n"
                        + "  " + inputTask.toString() + "\n"
                        + "Now you have " + String.valueOf(taskCounter) + " tasks in the list.\n"
                        + HORIZONTAL_LINE);
            }
            /*
            else {

                System.out.println(HORIZONTAL_LINE + "\n"
                        + "added: " + input + "\n"
                        + HORIZONTAL_LINE);
            } */
        }

        System.out.println(HORIZONTAL_LINE + "\n"
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE);
    }
}