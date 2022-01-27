import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Task[] list = new Task[100];
        int taskCount = 0;

        greet();
        converse(list, taskCount);
        exit();
    }

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void greet(){
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void exit(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void converse(Task[] list, int taskCount){
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        while (!(response.equals("bye"))){
            switch(response){
                case "list":
                    listTasks(list, taskCount);
                    break;
                case "bye":
                    return;
                default:
                    list = addList(list, taskCount, response);
                    taskCount++;
            }
            response = sc.nextLine();
        }

    }

    public static void listTasks(Task[] list, int taskCount){
        printLine();
        for (int i = 0; i < taskCount; i++){
            System.out.println(Integer.toString(i+1) + ". [" + list[i].getStatusIcon() + "] " + list[i].description);
        }
        printLine();
    }

    public static Task[] addList(Task[] list, int taskCount, String response){
        printLine();
        list[taskCount] = new Task(response);
        System.out.println("added: " + response);
        printLine();
        return list;
    }
}
