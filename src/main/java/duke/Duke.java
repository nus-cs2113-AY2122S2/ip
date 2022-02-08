package duke;

import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
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
        int taskStartIndex;
        String userInput;
        Scanner in = new Scanner(System.in);
        do{
            userInput = in.nextLine();

            if(userInput.startsWith("mark")){
                taskStartIndex = Integer.parseInt(userInput.substring(5));
                tasks[taskStartIndex-1].markAsDone();
                System.out.println(tasks[taskStartIndex-1].printTask());
            }else if(userInput.startsWith("unmark")){
                taskStartIndex = Integer.parseInt(userInput.substring(7));
                tasks[taskStartIndex-1].markAsUndone();
                System.out.println(tasks[taskStartIndex-1].printTask());
            }else{
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
                        tasks[i] = addTask(userInput);
                        System.out.println(tasks[i].printTask());
                        System.out.println("Now you have " + (i+1) + " tasks in the list.");
                        i++;
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

    public static void checkEmptyDescription(String description) throws EmptyDescriptionException{
        if(description.isBlank()){
            throw new EmptyDescriptionException();
        }
    }

    public static void listTasks(int numOfTasks){
        System.out.println("================================================");
        System.out.println("Here are the tasks in your list:");
        for(int j=0; j<numOfTasks; j++){
            System.out.println((j+1) + "." + tasks[j].printTask());
        }
        System.out.println("================================================");
    }
}
