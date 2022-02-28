package command;

import duke.Deadline;
import duke.Task;
import duke.Event;
import duke.TaskList;
import duke.Todo;
import duke.EmptyDescriptionException;

import java.util.ArrayList;

public class AddCommand extends Command {
    private TaskList taskList;
    private String userInput;

    public AddCommand(TaskList taskList, String userInput){
        this.taskList = taskList;
        this.userInput = userInput;
    }

    public void execute(){
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("================================================");
        try {
            Task addedTask = addTask(userInput);
            if(addedTask != null) {
                taskList.addTask(addedTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(taskList.getTaskCount()-1).printTask());
                System.out.println("Now you have " + (taskList.getTaskCount()) + " tasks in the list.");
            }else{
                System.out.println("Please try again!");
            }
        } catch (NullPointerException e) {
            System.out.println("Please try again!");
        }
        System.out.println("================================================");
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
                checkEmptyDescription(userInput.substring(9));
                return new Deadline(userInput.substring(9));
            }catch(EmptyDescriptionException | StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            }
        }else if (userInput.startsWith("event")){
            try{
                checkEmptyDescription(userInput.substring(6));
                return new Event(userInput.substring(6));
            }catch(EmptyDescriptionException | StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of an event cannot be empty.");
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
}
