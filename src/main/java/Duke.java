import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ArrayList<Task> todolist = new ArrayList<Task>();
        String task;
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        task=in.nextLine();
        while(true){
            if(task.equalsIgnoreCase("bye"))
                break;
            if(task.equalsIgnoreCase("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < todolist.size(); i++)
                    System.out.println((i+1)+". "+todolist.get(i).taskString());
                task=in.nextLine();
                continue;}
            else{
                String[] words = task.split(" ");
                if (words[0].equalsIgnoreCase("mark")){
                    try{
                        int index = Integer.parseInt(words[1]);
                        if(index>todolist.size()||index<=0) System.out.println("No task found.");
                        else{
                            todolist.get(index-1).markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println((index)+". "+todolist.get(index-1).taskString());
                        }
                    }
                    catch (NumberFormatException ex){
                        ex.printStackTrace();
                    }
                }
                else if (words[0].equalsIgnoreCase("unmark")){
                    try{
                        int index = Integer.parseInt(words[1]);
                        if(index>todolist.size()||index<=0) System.out.println("No task found.");
                        else {
                            todolist.get(index-1).markAsUndone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println((index)+". "+todolist.get(index-1).taskString());
                        }
                    }
                    catch (NumberFormatException ex){
                        ex.printStackTrace();
                    }
                }
                else{
                    System.out.println("added:"+task);
                    todolist.add(new Task(task)); }
                task=in.nextLine();
                continue;}
        }
        System.out.println("Bye.Have a nice day!");
    }
}
