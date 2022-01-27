import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm \n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<Task>();


        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++){
                    System.out.println(i + 1 + ". " + list.get(i).getTask());
                }
            }
            else if (input.startsWith("mark")){
                int taskIndex=Integer.parseInt(input.split(" ")[1]);
                Task currentTask=list.get(taskIndex -1);
                currentTask.setDone(true);
                System.out.println(currentTask.getTask());
            }else if(input.startsWith("unmark"))
            {
                int taskIndex=Integer.parseInt(input.split(" ")[1]);
                Task currentTask=list.get(taskIndex -1);
                currentTask.setDone(false);
                System.out.println(currentTask.getTask());
            }
            else{
                Task newTask=new Task(input);
                list.add(newTask);
                System.out.println("added:"+input);
            }
            System.out.println("______________________________________");
            input=sc.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");
    }
    public static void printTasks(Task[] tasks, int numTasks){
        for (int i=0; i<numTasks; i++){
            System.out.print((i+1) + ". ");
            tasks[i].getTask();
        }
    }
}


