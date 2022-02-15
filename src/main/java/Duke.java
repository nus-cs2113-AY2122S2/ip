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
            String[] words = task.split(" ",2);
            switch (words[0].toLowerCase()){
                case "list":{
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < todolist.size(); i++)
                        System.out.println((i+1)+". "+todolist.get(i).toString());
                    break;
                }
                case "mark":{
                    if (words.length==1)
                        System.out.println("☹ OOPS!!! Please tell me the task you want to mark.");
                    else{
                        try{
                            int index = Integer.parseInt(words[1]);
                            if(index>todolist.size()||index<=0) System.out.println("No task found.");
                            else{
                                todolist.get(index-1).markAsDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println((index)+". "+todolist.get(index-1).toString());
                            }
                        }
                        catch (NumberFormatException e){
                            System.out.println("☹ OOPS!!! Please tell me the task number you want to mark.");
                        }
                    }
                    break;
                }
                case "unmark":{
                    if (words.length==1)
                        System.out.println("☹ OOPS!!! Please tell me the task you want to unmark.");
                    else{
                        try{
                            int index = Integer.parseInt(words[1]);
                            if(index>todolist.size()||index<=0) System.out.println("No task found.");
                            else {
                                todolist.get(index-1).markAsUndone();
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println((index)+". "+todolist.get(index-1).toString());
                            }
                        }
                        catch (NumberFormatException ex){
                            System.out.println("☹ OOPS!!! Please tell me the task number you want to unmark.");
                        }
                    }
                    break;
                }
                case "todo": {
                    if (words.length == 1)
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    else {
                        todolist.add(new ToDo(words[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + todolist.get(todolist.size() - 1).toString());
                        System.out.println("Now you have " + todolist.size() + " tasks in the list.");
                    }
                    break;
                }
                case "deadline": {
                    if (words.length==1)
                        System.out.println("☹ OOPS!!! The information about the deadline is incomplete.");
                    else{
                        try{
                            String[] words1 = words[1].split("/by", 2);
                            todolist.add(new Deadline(words1[0], words1[1]));
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + todolist.get(todolist.size() - 1).toString());
                            System.out.println("Now you have " + todolist.size() + " tasks in the list.");
                        }
                        catch(ArrayIndexOutOfBoundsException e){
                            System.out.println("Could you type in the deadline with format:" +
                                    " deadline {your task} /by {your deadline}?");
                        }
                    }
                    break;
                }
                case "event": {
                    if (words.length==1)
                        System.out.println("☹ OOPS!!! The information about the event is incomplete.");
                    else{
                        try{
                            String[] words1 = words[1].split("/at", 2);
                            todolist.add(new Event(words1[0], words1[1]));
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + todolist.get(todolist.size() - 1).toString());
                            System.out.println("Now you have " + todolist.size() + " tasks in the list.");
                        }
                        catch(ArrayIndexOutOfBoundsException e){
                            System.out.println("Could you type in the event with format:" +
                                    " event {your task} /at {your event time}?");
                        }
                    }
                    break;
                }
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            task=in.nextLine();
        }
        System.out.println("Bye.Have a nice day!");
    }
}
