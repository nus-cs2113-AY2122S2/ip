import jdk.swing.interop.SwingInterOpUtils;

import java.util.Locale;
import java.util.Scanner;

public class TaskManager {
    private static final String INDENT = "    ";
    private static final String LINE="-------------------------------------------";
    private static int taskCount=0;
    private static Task[] taskList = new Task[100] ;

    public static void main(String[] args) {
        System.out.println(INDENT + "Hi, I am XiaoAi TongXue ;D");
        System.out.println(INDENT + LINE);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String action = input.split(" ")[0].toLowerCase(Locale.ROOT);
        System.out.println(INDENT + LINE);
        while(!action.equals("bye")){
            if(action.equals("list")){
                System.out.println(INDENT+"Here are the tasks in your list:");
                for(int i =0 ; i<taskCount; i++){
                    System.out.println(INDENT+(i+1) + "." + taskList[i]);
                }
            }
            else if(action.equals("todo") || action.equals("deadline") || action.equals("event")){
                int descriptionIdx = input.indexOf(" ")+1;
                Task newTask = null;
                switch(action){
                case "todo":

                    String description = input.substring(descriptionIdx);
                    newTask = new Todo(description); // 好愚蠢
                    break;
                case "deadline":
                    int byIdx = input.indexOf("/")+4;
                    description = input.substring(descriptionIdx, byIdx-5);
                    String by = input.substring(byIdx);
                    newTask = new Deadline(description,by);
                    break;
                case "event":
                    int atIdx = input.indexOf("/")+4;
                    description = input.substring(atIdx);
                    String at = input.substring(atIdx);
                    newTask = new Event(description,at);
                }
                addTask(newTask);
            }

            else if(action.equals("unmark")){
                System.out.println(INDENT+"Ok, I've marked this task as not done yet:");
                int idx = Integer.parseInt(input.split(" ")[1]);
                taskList[idx-1].markAsUndone();
                System.out.println(INDENT + taskList[idx-1].toString());

            }
            else if(action.equals("mark")){
                System.out.println(INDENT+"Nice! I have marked this task as done:");
                int idx = Integer.parseInt(input.split(" ")[1]);
                taskList[idx-1].markAsDone();
                System.out.println(INDENT + taskList[idx-1].toString());

            }
            else{
                System.out.println(INDENT+input);
            }
            System.out.println(INDENT+LINE);
            input = sc.nextLine();
            action = input.split(" ")[0].toLowerCase(Locale.ROOT);
            System.out.println(INDENT+LINE);
        }
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + LINE);
    }

    public static void addTask(Task t){
        taskList[taskCount] = t;
        System.out.println(INDENT+"Got it. I've added this task:");
        System.out.println(INDENT+t.description);
        taskCount++;
        System.out.println(INDENT+"Now you have " + taskCount + " tasks in the list.");

    }
    
}