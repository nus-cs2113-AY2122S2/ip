import java.util.Scanner;

public class Duke {

    public static void printLine(){
        System.out.println("\t" + "-----------------------------------------");
    }

    public static void greeting(){
        String logo = "\t" + " ____        _        \n"
                + "\t" + "|  _ \\ _   _| | _____ \n"
                + "\t" + "| | | | | | | |/ / _ \\\n"
                + "\t" + "| |_| | |_| |   <  __/\n"
                + "\t" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + "Hello from\n" + logo);
        printLine();

        System.out.println("\t" + "Hi! This is Duke!");
        System.out.println("\t" + "I'm glad to be at your service.");
        System.out.println("\t" + "What can I help you with?");
        printLine();
    }

    public static void bye() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLine();
    }

    public static void echo(String task) {
        printLine();
        System.out.println("\t" + "added:  " + task);
        printLine();
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        greeting();

        Scanner in = new Scanner(System.in);
        String textIn = in.nextLine();
        while(!textIn.toLowerCase().equals("bye")){
            if(textIn.equals("list")){
                taskList.printTaskList();
            } else if (textIn.startsWith("mark")) {
                taskList.markDone(Integer.parseInt(textIn.substring(5)));
            } else if (textIn.startsWith("unmark")) {
                taskList.unmark(Integer.parseInt(textIn.substring(7)));
            } else {
                echo(textIn);
                Task task = new Task(textIn);
                taskList.addTask(task);
            }
            in = new Scanner(System.in);
            textIn = in.nextLine();
        }

        bye();
    }
}
