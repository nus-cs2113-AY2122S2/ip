package duke.tasks;

import duke.exception.IndexOutOfRangeException;
import duke.util.PatternGenerator;
import duke.util.TaskDecoder;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public TaskList(TaskList load){
        this.tasks = load.tasks;
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
        tasks.get(index-1).markAsDone();
        System.out.println("[" + tasks.get(index-1).getStatusIcon() + "]" +tasks.get(index-1).description);
    }

    public void unmark(int index){
        tasks.get(index-1).markAsUndone();
        System.out.println("[" + tasks.get(index-1).getStatusIcon() + "]" +tasks.get(index-1).description);
    }




}
