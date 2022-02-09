import DukeException.DukeEmptyException;
import DukeException.DukeInvalidInputException;
import DukeTask.Deadline;
import DukeTask.Event;
import DukeTask.Task;
import DukeTask.ToDo;

import java.util.Arrays;
import java.util.List;

public class TaskManager {
    protected static Task[] tasks = new Task[100];
    protected static int inputAmount = 0;
    protected static final List<String> choiceList = Arrays.asList(new String[]{"TODO", "DEADLINE", "EVENT"});

    /*
    addTask is a public method for adding DukeTask.Task based on its type (TODOm DEADLINE, EVENT).
    It will throw exception if no description is provided or no time is provided for DEADLINE and EVENT task.
     */
    public static void addTask(String reply) throws DukeEmptyException, DukeInvalidInputException {
        String choice = reply.split(" ")[0];
        String newTask = reply.replace(choice+" ", "");

        //if the description of newTask is empty
        if(reply.split(" ").length==1 && choiceList.contains(choice.toUpperCase())){
            DukeEmptyException e = new DukeEmptyException(choice.toUpperCase());
            throw e;
        }

        switch(choice.toUpperCase()) {
            case "TODO":
                ToDo todo = new ToDo(newTask);
                tasks[inputAmount++] = todo;
                break;
            case "DEADLINE":
                int startIndexforBy = newTask.indexOf("/by");
                String by = newTask.substring(startIndexforBy + 4);
                String newDeadline = newTask.substring(0, startIndexforBy - 1);
                Deadline deadline = new Deadline(newDeadline, by);
                tasks[inputAmount++] = deadline;
                break;
            case "EVENT":
                int startIndexforAt = newTask.indexOf("/at");
                String at = newTask.substring(startIndexforAt + 4);
                String newEvent = newTask.substring(0, startIndexforAt - 1);
                Event event = new Event(newEvent, at);
                tasks[inputAmount++] = event;
                break;
            default:
                DukeInvalidInputException e = new DukeInvalidInputException("Invalid choice");
                throw e;
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks[inputAmount-1]);
        System.out.println("Now you have " + inputAmount + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }

    /*
    printTask is a public method for printing Tasks in current TaskList.
     */
    public void printTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i=1;i<=inputAmount;i++){
            System.out.println(i+". "+tasks[i-1]);
        }
        System.out.println("____________________________________________________________");
    }

    /*
    changeTaskStatus is a private method for mark/ unmark a DukeTask.Task.
    It will throw exception if the index is out of range.
     */
    private void changeTaskStatus(int index, boolean markDone) throws DukeInvalidInputException{
        if(index> inputAmount || index<=0){
            DukeInvalidInputException e = new DukeInvalidInputException("Invalid input");
            throw e;
        }
        if(markDone){//to mark it as done
            tasks[index-1].setDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done: ");
        }
        else{
            tasks[index-1].setUndone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks[index-1]);
        System.out.println("____________________________________________________________");
    }

    /*

     */
    public boolean relatedToMark(String reply){
        String[] words = reply.split(" ");
        if (words[0].equalsIgnoreCase("mark") || words[0].equalsIgnoreCase("unmark")) {
            if(words.length==1) {
                System.out.println("OOPS!!! The mark/ unmark index should not be empty!");
                return true;
            }
            try {
                int index = Integer.parseInt(words[1]);
                boolean markDone = words[0].equalsIgnoreCase("mark")? true: false;
                changeTaskStatus(index, markDone);
            } catch (DukeInvalidInputException | NumberFormatException e) {
                System.out.println("OOPS!!! The mark index you've entered is invalid!");
            }
            finally {
                return true;
            }
        }
        return false;
    }



}
