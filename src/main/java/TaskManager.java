public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;
    static Greet greet;

    public TaskManager() {
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void markTask(int number){
        greet.printDecoration();
        if (number > taskCount || number < 1) {
            System.out.println("Invalid Number! Let's try again...");
            greet.printDecoration();
            return;
        }
        if (tasks[number - 1].isMarked()) {
            System.out.println("Err, this task is already marked...");
        } else {
            tasks[number - 1].setMarked(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(tasks[number - 1]);
        }
        greet.printDecoration();

    }

    public void unmarkTask(int number){
        greet.printDecoration();
        if(number > taskCount || number < 1){
            System.out.println("Invalid Number! Let's try again...");
            greet.printDecoration();
            return;
        }
        if(!tasks[number - 1].isMarked()){
            System.out.println("I cannot unmark something that was never marked...");
        } else {
            tasks[number - 1].setMarked(false);
            System.out.println("Okay Boss! The following task has been unmarked: ");
            System.out.println(tasks[number - 1]);
        }
        greet.printDecoration();
    }

    public void addToTasks(String taskName){
        greet.printDecoration();
        tasks[taskCount] = new Todo(taskName);
        System.out.println("added: " + tasks[taskCount]);
        taskCount++;
        System.out.println("You now have " + taskCount + " tasks in the list.");
        greet.printDecoration();
    }

    public void addToTasks(String type, String taskName,String date){
        greet.printDecoration();
        if (type.equals("E")) {
            tasks[taskCount] = new Event(taskName,date);
        } else {
            tasks[taskCount] = new Deadline(taskName,date);
        }
        System.out.println("added: " + tasks[taskCount]);
        taskCount++;
        System.out.println("You now have " + taskCount + " tasks in the list.");
        greet.printDecoration();
    }

    public void printTasks(){
        greet.printDecoration();
        if (taskCount == 0) {
            System.out.println("You have not added any Tasks!");
        } else {
            for (int i = 0; i < taskCount ; i++) {
                System.out.print(i+1 + ". ");
                System.out.println(tasks[i]);
            }
        }
        greet.printDecoration();
    }
}

