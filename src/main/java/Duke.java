import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task [100];
    static int taskIndex=0;

    public static void main(String[] args) {

        greetUser("hi");
        startChatbot();
        greetUser("bye");
    }

    private static void startChatbot() {
        Scanner input = new Scanner(System.in);
        String task = "";
        while(true) {
            task = input.nextLine();
            System.out.println("    ____________________________________");
            if(task.equals("bye")){
                break;
            } else if(task.equals("list")) {
                listTasks();
            } else if(task.contains("mark ")&& !task.contains("unmark ")) {
                markTask(task);
            } else if(task.contains("unmark ")) {
                unmarkTask(task);
            } else {
                addTask(task);
            }
            System.out.println("    ____________________________________");
        }
    }

    private static void greetUser(String greeting) {
        if(greeting.equals("hi")) {
            String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo);
            System.out.println("    ____________________________________");
            System.out.println("    Hello! I'm Duke");
            System.out.println("    What can I do for you?\n");
        }
        else {
            System.out.println("    ____________________________________");
            System.out.println("    Bye. Hope to see you again soon!");
            System.out.println("    ____________________________________");
        }
    }

    public static void listTasks() {
        System.out.println("    Here are the tasks in your list:");
        for(int i=0;i< taskList.length;i++) {
            if(taskList[i] == null) break;
            System.out.println("    "+(i+1)+": "+taskList[i]);
        }
    }

    public static void markTask(String task) {
        task = task.replace("mark ","");
        int i = Integer.parseInt(task)-1;
        if(taskList[i]!=null) {
            taskList[i].setDone(true);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("    "+taskList[i]);
        } else {
            System.out.println("    Please enter a valid task number");
        }
    }

    public static void unmarkTask(String task) {
        task = task.replace("unmark ","");
        int i = Integer.parseInt(task)-1;
        if(taskList[i]!=null) {
            taskList[i].setDone(false);
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("        "+taskList[i]);

        } else {
            System.out.println("    Please enter a valid task number");
        }
    }

    public static void addTask(String task) {
        int index = task.indexOf(' ');
        String taskType = task.substring(0,index);
        task = task.substring(index+1);
        switch(taskType) {
        case "todo":
            addAsTodo(task);
            break;
        case "deadline":
            addAsDeadline(task);
            break;
        case "event":
            addAsEvent(task);
            break;
        default:
            System.out.println("    Sorry I do not know what that means");
            return;
        }
        System.out.println("    Got it. I've added this task:");
        System.out.println("        "+taskList[taskIndex-1]);
        System.out.println("    Now you have "+(taskIndex)+ " tasks in the list.");
    }

    private static void addAsTodo(String task) {
        taskList[taskIndex++] = new Todo(task);
    }
    private static void addAsEvent(String task) {
        int index;
        index = task.indexOf("/");
        String eventTime = task.substring(index+1);
        task = task.substring(0,index-1);

        index = eventTime.indexOf(' ');
        eventTime = eventTime.substring(index+1);
        taskList[taskIndex++] = new Event(task,eventTime);
    }

    private static void addAsDeadline(String task) {
        int index;
        index = task.indexOf("/");
        String by = task.substring(index+1);
        task = task.substring(0,index-1);

        index = by.indexOf(' ');
        by = by.substring(index+1);
        taskList[taskIndex++] = new Deadline(task,by);
    }
}
