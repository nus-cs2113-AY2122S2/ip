public class Task {
    protected String description;
    protected boolean isDone;

    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public static void addTask(String taskText){
        Task newTask = new Task(taskText);
        taskList[taskCount] = newTask;
        taskCount++;
    }

    public static void listTasks(){
        for(int i = 0; i < taskCount; i++){
            System.out.print(i+1);
            System.out.println("." + "[" + taskList[i].getStatusIcon() + "]" + taskList[i].description);
        }
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public static void mark(int index){
        taskList[index-1].markAsDone();
        System.out.println("[" + taskList[index-1].getStatusIcon() + "]" + taskList[index-1].description);
    }

    public static void unmark(int index){
        taskList[index-1].markAsUndone();
        System.out.println("[" + taskList[index-1].getStatusIcon() + "]" + taskList[index-1].description);
    }
}
