import java.util.Scanner;

public class Duke {
    private static String tasks[] = new String[100];
    private static int taskCount = 0;

    public static void printLine(){
        System.out.println("-----------------------------------------");
    }

    public static void greeting(){
        System.out.println("Hi! This is Duke!");
        System.out.println("I'm glad to be at your service.");
        System.out.println("What can I help you with?");
        printLine();
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void echo() {
        Scanner in = new Scanner(System.in);
        while(true){
            String text = in.nextLine();
            printLine();

            if (text.toLowerCase().equals("bye")){
                break;
            } else if (text.equals("list")) {
                printList();
            } else {
                addTask(text);
            }
        }
    }

    public static void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("added: " + task);
        printLine();
    }

    public static void printList(){
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + ". " + tasks[i]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();
        greeting();

        echo();

        bye();
    }
}
