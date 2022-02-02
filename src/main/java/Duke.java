import java.util.Scanner;

public class Duke {

    public static void printLine() {
        System.out.println("\t" + "-----------------------------------------");
    }

    public static void greeting() {
        String logo = "\t" + "  ____        _        \n"
                + "\t" + " |  _ \\ _   _| | _____ \n"
                + "\t" + " | | | | | | | |/ / _ \\\n"
                + "\t" + " | |_| | |_| |   <  __/\n"
                + "\t" + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + " Hello from\n" + logo);
        printLine();

        System.out.println("\t" + " Hi! This is Duke!");
        System.out.println("\t" + " I'm glad to be at your service.");
        System.out.println("\t" + " What can I help you with?");
        printLine();
    }

    public static void bye() {
        System.out.println("\t" + " Bye. Hope to see you again soon!");
        printLine();
    }

    public static void echo(Task task, TaskList taskList) {
        printLine();
        System.out.println("\t" + " Got it. I've added this task:");
        System.out.println("\t" + "\t" + task);
        System.out.println("\t" + " Now you have " + taskList.getTaskCount() + " tasks in the list.");
        printLine();
    }

    public static void guide() {
        System.out.println("\t" + " use \"list\" to show the task list");
        System.out.println("\t" + " use \"todo task\" to add a task without any date/time attached to it\"");
        System.out.println("\t" + " use \"deadline task /by time\" to add a task that need to be done before a specific time/date");
        System.out.println("\t" + " use \"event task /at time\" to add a task that tasks that start at a specific time and ends at a specific time");
        System.out.println("\t" + " use \"mark taskIndex\" to mark that task as done");
        System.out.println("\t" + " use \"unmark taskIndex\" to mark that task as not done");
        System.out.println("\t" + " use \"bye\" to exit the chatbot");
        printLine();
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        greeting();

        Scanner in = new Scanner(System.in);
        String textIn = in.nextLine();
        while (!textIn.toLowerCase().equals("bye")) {
            if (textIn.equals("list")) {
                taskList.printTaskList();
            } else if (textIn.startsWith("mark")) {
                taskList.markDone(Integer.parseInt(textIn.substring(5)));
            } else if (textIn.startsWith("unmark")) {
                taskList.unmark(Integer.parseInt(textIn.substring(7)));
            } else if (textIn.startsWith("todo")) {
                Task task = new Todo(textIn.substring(5));
                taskList.addTask(task);
                echo(task, taskList);
            } else if (textIn.startsWith("deadline")) {
                int seperator = textIn.indexOf("/by");
                Task task = new Deadline(textIn.substring(9, seperator - 1), textIn.substring(seperator + 4));
                taskList.addTask(task);
                echo(task, taskList);
            } else if (textIn.startsWith("event")) {
                int seperator = textIn.indexOf("/at");
                Task task = new Event(textIn.substring(6, seperator - 1), textIn.substring(seperator + 4));
                taskList.addTask(task);
                echo(task, taskList);
            } else {
                guide();
            }
            in = new Scanner(System.in);
            textIn = in.nextLine();
        }

        bye();
    }
}
