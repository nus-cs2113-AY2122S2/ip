import java.util.Scanner;

public class Duke {

    public static void printList(Task[] list, int counter){
        for(int i = 0; i < counter; i++){
            System.out.print(i + 1 + ".");
            printTask(list[i]);
        }
    }
    public static void printTask(Task t){
       System.out.println("[" + t.getStatusIcon()+ "] " + t.taskName);
    }

    public static int getTaskNumberArgument(String input){
        int spaceIndex = input.indexOf(" ");
        String taskNum = input.substring(spaceIndex + 1);
        System.out.println(taskNum);
        return Integer.parseInt(taskNum.trim());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Task[] list=new Task[100];
        int taskCounter = 0;

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        while(!input.equalsIgnoreCase("bye")){
            // print list
            if (input.equalsIgnoreCase("list")){
                printList(list,taskCounter);
            }
            else if(input.startsWith("mark")){
                    int taskNum = getTaskNumberArgument(input);
                System.out.println(taskNum);
                    list[taskNum - 1].setDone(true);
                System.out.println("Nice! I've marked this task as done:\n");
            }
            else if(input.startsWith("unmark")){
                int taskNum = getTaskNumberArgument(input);
                list[taskNum-1].setDone(false);
                System.out.println("K, I've marked this task as not done yet:\n");
            }
            else {
                Task t = new Task(input);
                list[taskCounter] = t;
                taskCounter++;
                System.out.println("added: "+input);
            }
            input = sc.nextLine();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
