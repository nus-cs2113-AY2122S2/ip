public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public TaskManager() {
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void addToTasks(String taskName){
        Greet.printDecoration();
        this.tasks[this.taskCount] = new Task(taskName);
        System.out.println("added: " + taskName);
        this.taskCount++;
        Greet.printDecoration();
    }

    public void printTasks(){
        Greet.printDecoration();
        if (this.taskCount == 0){
            System.out.println("You have not added any Tasks!");
        }
        else{
            for(int i = 0; i < this.taskCount ; i++){
                System.out.println(i+1 + ". " + tasks[i].getName());
            }
        }
        Greet.printDecoration();
    }
}
