package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("================================================");

        int i = 0;
        int taskStartIndex, taskDeleteIndex;
        String userInput;
        Scanner in = new Scanner(System.in);
        do{
            userInput = in.nextLine();

            if(userInput.startsWith("mark")){
                taskStartIndex = Integer.parseInt(userInput.substring(5));
                try {
                    tasks.get(taskStartIndex-1).markAsDone();
                    System.out.println(tasks.get(taskStartIndex-1).printTask());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist!");
                } catch (NumberFormatException e){
                    System.out.println("Please use numbers only to specify task!");
                }
            }else if(userInput.startsWith("unmark")){
                taskStartIndex = Integer.parseInt(userInput.substring(7));
                try {
                    tasks.get(taskStartIndex-1).markAsUndone();
                    System.out.println(tasks.get(taskStartIndex-1).printTask());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist!");
                } catch (NumberFormatException e){
                    System.out.println("Please use numbers only to specify task!");
                }
            }else if(userInput.startsWith("delete")){
                taskDeleteIndex = Integer.parseInt(userInput.substring(7));
                try {
                    deleteTask(taskDeleteIndex);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(taskDeleteIndex-1).printTask());
                    i--;
                    System.out.println("Now you have " + i + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist!");
                } catch (NumberFormatException e){
                    System.out.println("Please use numbers only to specify task!");
                }
            } else{
                switch(userInput){
                case "bye":
                    System.out.println("================================================");
                    break;
                case "list":
                    listTasks(i);
                    break;
                default:
                    System.out.println("================================================");
                    try {
                        tasks.add(addTask(userInput));
                        System.out.println(tasks.get(i).printTask());
                        i++;
                        System.out.println("Now you have " + (i) + " tasks in the list.");
                    } catch (NullPointerException e) {
                        System.out.println("Please try again!");
                    }
                    System.out.println("================================================");
                    break;
                }
            }
        }while(!userInput.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static Task addTask(String userInput){
        if (userInput.startsWith("todo")) {
            try{
                checkEmptyDescription(userInput.substring(5));
                return new Todo(userInput.substring(5));
            }catch(EmptyDescriptionException | StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (userInput.startsWith("deadline")) {
            try{
                checkEmptyDescription(userInput.substring(5));
                return new Deadline(userInput.substring(9));
            }catch(EmptyDescriptionException | StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }
        }else if (userInput.startsWith("event")){
            try{
                checkEmptyDescription(userInput.substring(5));
                return new Event(userInput.substring(6));
            }catch(EmptyDescriptionException | StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }
        }else{
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return null;
    }

    public static void deleteTask(int index){
        tasks.remove(index);
    }

    public static void checkEmptyDescription(String description) throws EmptyDescriptionException{
        if(description.isBlank()){
            throw new EmptyDescriptionException();
        }
    }

    public static void listTasks(int numOfTasks){
        System.out.println("================================================");
        System.out.println("Here are the tasks in your list:");
        for(int j=0; j<numOfTasks; j++){
            System.out.println((j+1) + "." + tasks.get(j).printTask());
        }
        System.out.println("================================================");
    }
}
