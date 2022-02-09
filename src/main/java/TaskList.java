public class TaskList {
    private Task[] taskList = new Task[100];
    private int taskCount = 0;

    public void printLine(){
        System.out.println("\t" + "-----------------------------------------");
    }

    public void addTask(Task newTask){
        taskList[taskCount] = newTask;
        taskCount += 1;
    }

    public void printTaskList(){
        printLine();
        System.out.println("\t" + " Here are the tasks in your list:");
        for(int i = 0 ; i < taskCount; i++){
            System.out.println("\t" + " " + (i + 1) + "." + taskList[i]);
        }
        printLine();
    }

    public void markDone(int taskId){
        taskList[taskId-1].setDone();
        printLine();
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t" + taskList[taskId-1]);
        printLine();
    }

    public void unmark(int taskId){
        taskList[taskId-1].setNotDone();
        printLine();
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println("\t" + taskList[taskId-1]);
        printLine();
    }

    public int getTaskCount() {
        return taskCount;
    }
}
