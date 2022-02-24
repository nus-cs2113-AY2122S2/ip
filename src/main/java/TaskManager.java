import DukeTask.Deadline;
import DukeTask.Event;
import DukeTask.Task;
import DukeTask.ToDo;

import java.util.ArrayList;

public class TaskManager {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    /*
    addTask is a public method for adding DukeTask.Task based on its type (TODOm DEADLINE, EVENT).
    It will throw exception if no description is provided or no time is provided for DEADLINE and EVENT task.
     */
    public static void addTask(ArrayList<String> command){
        String choice = command.get(0);
        String newTask = command.get(1);

        switch(choice) {
        case "TODO":
            tasks.add(new ToDo(newTask));
            break;
        case "DEADLINE":
            String by = command.get(2);
            tasks.add(new Deadline(newTask, by));
            break;
        case "EVENT":
            String at = command.get(2);
            tasks.add(new Event(newTask, at));
            break;
        default:
            break;
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }

    /*
    printTask is a public method for printing Tasks in current TaskList.
     */
    public void printTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i=1;i<=tasks.size();i++){
            System.out.println(i+". "+tasks.get(i-1));
        }
        System.out.println("____________________________________________________________");
    }

    /*
    changeTaskStatus is a private method for mark/ unmark a DukeTask.Task.
    It will throw exception if the index is out of range.
     */
    private void changeTaskStatus(int index, boolean markDone){
        if(markDone){//to mark it as done
            tasks.get(index-1).setDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done: ");
        }
        else{
            tasks.get(index-1).setUndone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks.get(index-1));
        System.out.println("____________________________________________________________");
    }

    /*
    deleteTask is a public method which delete a task based on its given index.
    It will throw exception if the index is out of range.
     */
    public void deleteTask(int index){
        Task deletedTask = tasks.get(index-1);
        tasks.remove(index-1);
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task: \n"+deletedTask);
        System.out.println("Now you have "+tasks.size()+" tasks in the list.");
        System.out.println("____________________________________________________________");

    }

    public void findTask(String keyword){
        int count = 1;
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for(Task task: tasks){
            if(task.getDescription().contains(keyword)){
                System.out.println(count+". "+task);
                count++;
            }
        }
        if(count==1)
            System.out.println("No result found. Try to change your keyword!");
        System.out.println("____________________________________________________________");
    }

    public void manageCommand(ArrayList<String> command){
        if(command.size()==0)
            return;
        switch(command.get(0)){
        case "LIST":
            printTasks();
            break;
        case "UNMARK":
        case "MARK":
            boolean mark = command.get(0).equals("MARK")?true:false;
            int indexMark = Integer.parseInt(command.get(1));
            changeTaskStatus(indexMark, mark);
            break;
        case "DELETE":
            int indexDelete = Integer.parseInt(command.get(1));
            deleteTask(indexDelete);
            break;
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            addTask(command);
            break;
        case "FIND":
            findTask(command.get(1));
            break;
        }
    }

}
