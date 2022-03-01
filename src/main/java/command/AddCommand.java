package command;

import duke.Deadline;
import duke.Task;
import duke.Event;
import duke.TaskList;
import duke.Todo;
import duke.EmptyDescriptionException;

import java.util.ArrayList;

/**
 * Subclass of Command to handle adding of tasks
 */
public class AddCommand extends Command {
    private TaskList taskList;
    private String userInput;

    /**
     * Initialises an AddCommand with the TaskList to be added onto and user input to be processed
     * @param taskList list of tasks to be added onto
     * @param userInput user input to be processed
     */
    public AddCommand(TaskList taskList, String userInput){
        this.taskList = taskList;
        this.userInput = userInput;
    }

    /**
     * Add the new Task into the existing list and output result to user
     */
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

    /**
     * Processes the user input to determine what type of task is to be added
     * @param userInput string of task described by user
     * @return Task to be added to the list of tasks
     */
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

    /**
     * Check for empty description of Task to be added
     * @param description description of Task to be checked
     * @throws EmptyDescriptionException
     */
    public static void checkEmptyDescription(String description) throws EmptyDescriptionException{
        if(description.isBlank()){
            throw new EmptyDescriptionException();
        }
    }
}
