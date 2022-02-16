import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        FileManager.create();
        ArrayList<Task> todolist = new ArrayList<Task>();
        BufferedReader br = new BufferedReader(new FileReader("data.txt"));
        String taskLine;
        while ((taskLine = br.readLine())!=null) {
            String[] t = taskLine.split(",");
            switch (t[0]) {
                case "T":
                    ToDo todo = new ToDo(t[2]);
                    todolist.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(t[2], t[3]);
                    todolist.add(deadline);
                    break;
                case "E":
                    Event event = new Event(t[2], t[3]);
                    todolist.add(event);
                    break;
            }
            if (t.length>1&&t[1].equals("true"))
                 todolist.get(todolist.size() - 1).markAsDone();
        }
        br.close();
        String task;
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
                        catch (NumberFormatException ex){
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
                case "delete":{
                    if (words.length==1)
                        System.out.println("☹ OOPS!!! Please tell me the task you want to delete.");
                    else{
                        try{
                            int index = Integer.parseInt(words[1]);
                            if(index>todolist.size()||index<=0) System.out.println("No task found.");
                            else{
                                System.out.println("Noted. I've removed this task:");
                                System.out.println((index)+". "+todolist.get(index-1).toString());
                                todolist.remove(index-1);
                                System.out.println("Now you have " + todolist.size() + " tasks in the list.");
                            }
                        }
                        catch (NumberFormatException ex){
                            System.out.println("☹ OOPS!!! Please tell me the task number you want to delete.");
                        }
                    }
                    break;
                }
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            task=in.nextLine();
        }
        String taskAllInfo="";
        for(Task k: todolist){
            String taskInfo = k.getType()+","+k.getStatus()+","+k.getDescription();
            if(k.getType()=="D") {
                Deadline d = (Deadline) k;
                taskInfo = taskInfo + "," + d.getBy();
            }
            else if(k.getType()=="E"){
                Event e = (Event) k;
                taskInfo = taskInfo + "," + e.getAt();
            }
            taskAllInfo+=taskInfo+"\n";
        }
        if (taskAllInfo!=null) taskAllInfo=taskAllInfo.substring(0, taskAllInfo.length() - 1);
        FileManager.save(taskAllInfo);
        System.out.println("Bye.Have a nice day!");
    }
}

