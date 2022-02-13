import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ArrayList<String> todolist = new ArrayList<String>();
        String task;
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        task=in.nextLine();
        while(true){
            if(task.equalsIgnoreCase("bye"))
                break;
            if(task.equalsIgnoreCase("list")){
                for (int i = 0; i < todolist.size(); i++)
                    System.out.println((i+1)+"."+todolist.get(i));
                task=in.nextLine();
                continue;}
            else{
                System.out.println("added:"+task);
                todolist.add(task);
                task=in.nextLine();
                continue;}
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
