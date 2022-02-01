import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task [100];
    static int taskIndex=0;

    public static void addTask(String task)
    {
        // we get the type of a task from the first word of String task

        int index = task.indexOf(' ');
        //todo some task /by 12th August
        //taskType = todo
        String taskType = task.substring(0,index);
        task = task.substring(index+1);
        switch(taskType)
        {
        case "todo":
            taskList[taskIndex++] = new Todo(task);
            break;
        case "deadline":
            index = task.indexOf("/");
            String by = task.substring(index+1);
            task = task.substring(0,index-1);

            index = by.indexOf(' ');
            by = by.substring(index+1);
            taskList[taskIndex++] = new Deadline(task,by);
            break;
        case "event":
            index = task.indexOf("/");
            String eventTime = task.substring(index+1);
            task = task.substring(0,index-1);

            index = eventTime.indexOf(' ');
            eventTime = eventTime.substring(index+1);
            taskList[taskIndex++] = new Event(task,eventTime);
            break;
        default:
            System.out.println("Sorry I do not know what that means");
            return;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("    "+taskList[taskIndex-1]);
        System.out.println("Now you have "+(taskIndex)+ " tasks in the list.");
    }


    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("    ____________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?\n");

        Scanner input = new Scanner(System.in);
        String task = "";

        while(!task.equals("bye")) {
            task = input.nextLine();
            if(!task.equals("bye")) {
                System.out.println("    ____________________________________");
                if(task.equals("list")) {
                    for(int i=0;i< taskList.length;i++) {
                        if(taskList[i] == null) break;
                        System.out.println(taskList[i]);
                    }
                } else if(task.contains("mark ")&& !task.contains("unmark ")) {
                    task = task.replace("mark ","");
                    int i = Integer.parseInt(task)-1;
                    if(taskList[i]!=null) {
                        taskList[i].setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    "+taskList[i]);
                    } else {
                        System.out.println("Please enter a valid task number");
                    }
                } else if(task.contains("unmark ")) {
                    task = task.replace("unmark ","");
                    int i = Integer.parseInt(task)-1;
                    if(taskList[i]!=null) {
                        taskList[i].setDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("    "+taskList[i]);

                    } else {
                        System.out.println("Please enter a valid task number");
                    }
                } else {
                    addTask(task);
                }
                System.out.println("    ____________________________________");
            }
        }
        System.out.println("    ____________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________");


    }
}
