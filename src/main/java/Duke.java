import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?\n");

        Scanner input = new Scanner(System.in);
        String task = "";
        String[] taskList = new String [100];
        int index=0;
        while(!task.equals("bye")) {
            task = input.nextLine();
            if(!task.equals("bye")) {
                System.out.println("    ____________________________________");
                if(task.equals("list"))
                {
                    for(int i=0;i< taskList.length;i++) {
                        if(taskList[i] == null) break;
                        System.out.println("    " + (i + 1) + ". " + taskList[i]);
                    }
                }
                else
                {
                    taskList[index++] = task;
                    System.out.println("added: " + task);
                }
                System.out.println("    ____________________________________");
            }
        }
        System.out.println("    ____________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________");


    }
}
