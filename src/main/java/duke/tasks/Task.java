package duke.tasks;

import duke.exception.IndexOutOfRangeException;
import duke.util.PatternGenerator;

import java.util.ArrayList;


public class Task {
    protected String description;
    protected boolean isDone;

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public static void addTask(Task task){
        tasks.add(task);
        taskCount++;
        PatternGenerator.generateLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        PatternGenerator.generateLine();
    }

    public static void deleteTask(int index) throws IndexOutOfRangeException {
        if (index > taskCount || index < 0){
            throw new IndexOutOfRangeException();
        }
        PatternGenerator.generateLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index-1));
        tasks.remove(index-1);
        taskCount--;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        PatternGenerator.generateLine();
    }

    public static void listTasks(){
        for(int i = 0; i < taskCount; i++){
            System.out.print(i+1);
            System.out.println("." + tasks.get(i).toString());
        }
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getStatusNumber(){
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public static void mark(int index){
        tasks.get(index-1).markAsDone();
        System.out.println("[" + tasks.get(index-1).getStatusIcon() + "]" +tasks.get(index-1).description);
    }

    public static void unmark(int index){
        tasks.get(index-1).markAsUndone();
        System.out.println("[" + tasks.get(index-1).getStatusIcon() + "]" +tasks.get(index-1).description);
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

    public static String getData(){
        String taskString, wholeString;
        wholeString = "";
        for (Task task:tasks){
            if (task instanceof Todo){
                taskString = task.getCategory() + "|" + task.getStatusNumber() + "|" +
                        task.description + System.lineSeparator();
            }
            else if (task instanceof Event) {
                taskString = task.getCategory() + "|" + task.getStatusNumber() + "|" +
                        task.description + ((Event) task).startEndTime + System.lineSeparator();
            }
            else if (task instanceof Deadline) {
                taskString = task.getCategory() + "|" + task.getStatusNumber() + "|" +
                        task.description + "|" + ((Deadline) task).by + System.lineSeparator();
            }
            else {
                taskString = "";
            }
            wholeString = wholeString + taskString;
        }
        return wholeString;
    }

    public String getCategory(){
        return "TK";
    }
}
