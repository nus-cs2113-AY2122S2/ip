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
                    if (response.startsWith("mark")){
                        markTask(list, taskCount, response);
                    }
                    else if (response.startsWith("unmark")){
                        unMarkTask(list, taskCount, response);
                    }
                    else {
                        list = addList(list, taskCount, response);
                        taskCount++;
                    }
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

    public static void markTask(Task[] list, int taskCount, String response){
        printLine();
        String[] words = response.split(" ");
        int taskIndex = Integer.parseInt(words[1]);
        if (taskIndex > taskCount || taskIndex == 0){
            System.out.println("Sorry, seem's like there's no such task with that index.");
            printLine();
            return;
        }
        else {
            if (list[taskIndex-1].isDone == false){
                list[taskIndex-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t[" + list[taskIndex-1].getStatusIcon() + "]" + list[taskIndex-1].description);
            }
            else {
                System.out.println("Hmm, you've already marked this task.");
            }
        }
        printLine();
        return;
    }

    public static void unMarkTask(Task[] list, int taskCount, String response){
        printLine();
        String[] words = response.split(" ");
        int taskIndex = Integer.parseInt(words[1]);
        if (taskIndex > taskCount || taskIndex == 0){
            System.out.println("Sorry, seem's like there's no such task with that index.");
            printLine();
            return;
        }
        else {
            if (list[taskIndex-1].isDone == true){
                list[taskIndex-1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t[" + list[taskIndex-1].getStatusIcon() + "]" + list[taskIndex-1].description);
            }
            else {
                System.out.println("Hmm, this task is already unmarked.");
            }
        }
        printLine();
        return;
    }
}
