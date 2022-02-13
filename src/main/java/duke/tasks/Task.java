package duke.tasks;

import duke.util.PatternGenerator;

public class Task {
    protected String description;
    protected boolean isDone;

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public static void addTask(Task task){
        tasks[taskCount] = task;
        taskCount++;
        PatternGenerator.generateLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        PatternGenerator.generateLine();
    }

    public static void listTasks(){
        for(int i = 0; i < taskCount; i++){
            System.out.print(i+1);
            System.out.println("." + tasks[i].toString());
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
        tasks[index-1].markAsDone();
        System.out.println("[" + tasks[index-1].getStatusIcon() + "]" + tasks[index-1].description);
    }

    public static void unmark(int index){
        tasks[index-1].markAsUndone();
        System.out.println("[" + tasks[index-1].getStatusIcon() + "]" + tasks[index-1].description);
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
