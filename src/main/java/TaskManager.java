public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public TaskManager() {
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void markTask(int number){
        Greet.printDecoration();
        if(number > taskCount || number < 1){
            System.out.println("Invalid Number! Let's try again...");
        }
        else {
            if(tasks[number - 1].isMarked())
            {
                System.out.println("Err, this task is already marked...");
            }
            else {
                tasks[number - 1].setMarked(true);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[X]" + ' ' + tasks[number - 1].getName());
            }
        }
        Greet.printDecoration();
    }

    public void unmarkTask(int number){
        Greet.printDecoration();
        if(number > taskCount || number < 1){
            System.out.println("Invalid Number! Let's try again...");
        }
        else {
            if(!tasks[number - 1].isMarked()){
                System.out.println("I cannot unmark something that was never marked...");
            }
            else {
                tasks[number - 1].setMarked(false);
                System.out.println("Okay Boss! The following task has been unmarked: ");
                System.out.println("[ ]" + ' ' + tasks[number - 1].getName());
            }
        }
        Greet.printDecoration();
    }

    public void addToTasks(String taskName){
        Greet.printDecoration();
        tasks[taskCount] = new Task(taskName);
        System.out.println("added: " + taskName);
        taskCount++;
        Greet.printDecoration();
    }

    public void printTasks(){
        Greet.printDecoration();
        if (taskCount == 0){
            System.out.println("You have not added any Tasks!");
        }
        else{
            for(int i = 0; i < taskCount ; i++){
                if(tasks[i].isMarked())
                {
                    System.out.println(i+1 + ". " + "[X]" + ' '  + tasks[i].getName());
                }
                else{
                    System.out.println(i+1 + ". " + "[ ]" + ' '  + tasks[i].getName());
                }
            }
        }
        Greet.printDecoration();
    }
}
