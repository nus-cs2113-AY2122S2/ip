package duke.tasks;

import duke.exception.IndexOutOfRangeException;
import duke.util.PatternGenerator;
import duke.util.TaskDecoder;

import java.util.ArrayList;

/**
 * Represents the entire task list, which contains tasks.
 */

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public TaskList(TaskList load){
        this.tasks = load.tasks;
    }

    public int getNumberOfTasks(){
        return tasks.size();
    }

    public void addTask(Task task){
        tasks.add(task);
        PatternGenerator.generateLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        PatternGenerator.generateLine();
    }

    public void deleteTask(int index) throws IndexOutOfRangeException {
        if (index > tasks.size() || index < 0){
            throw new IndexOutOfRangeException();
        }
        PatternGenerator.generateLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index-1));
        tasks.remove(index-1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        PatternGenerator.generateLine();
    }

    public void list(){
        for(int i = 0; i < tasks.size(); i++){
            System.out.print(i+1);
            System.out.println("." + tasks.get(i).toString());
        }
    }

    public ArrayList<Task> getFullList(){
        return tasks;
    }

    public void mark(int index){
        PatternGenerator.generateLine();
        tasks.get(index-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + tasks.get(index-1).getStatusIcon() + "]" +tasks.get(index-1).description);
        PatternGenerator.generateLine();
    }

    public void unmark(int index){
        PatternGenerator.generateLine();
        tasks.get(index-1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + tasks.get(index-1).getStatusIcon() + "]" +tasks.get(index-1).description);
        PatternGenerator.generateLine();
    }

    public ArrayList<Task> findTask(String keyword){
        ArrayList<Task> selectedTasks = new ArrayList<>();
        for(Task task : tasks){
            if(task.getDescription().contains(keyword)){
                selectedTasks.add(task);
            }
        }
        return selectedTasks;
    }




}
