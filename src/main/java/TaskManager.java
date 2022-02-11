public class TaskManager {

    String horiLine = "____________________________________________________________\n";
    private final Task[] tManager = new Task[100];
    private int taskCount = 0;

    public TaskManager() {
    }

    public void welcome() {
        String greeting = "  Hello, I'm Baymax.\n"+
                "  Your personal task managing companion. \n" +
                "  What can I do for you? \n";
        System.out.println(horiLine + greeting + horiLine);
    }

    public void bye() {
        String goodbye = "  Bye. Hope to see you again soon! \n";
        System.out.println(horiLine + goodbye + horiLine);//bye
    }

    public void addTask(Task description) {
        this.tManager[this.taskCount] = description;
        System.out.println(horiLine);
        System.out.println(" Got it. I've added this task: \n"+
                            this.tManager[this.taskCount].description+"\n" +
                            "Now you have " + (this.taskCount+1) + " tasks in the list.");
        System.out.println(horiLine);
        this.taskCount++;
    }

    public void printTaskList() {
        System.out.println(horiLine);
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<taskCount; i++) {
            System.out.println((i+1) + "." + this.tManager[i].getStatusIcon() +
                                " " + this.tManager[i].getDescription());
        }
        System.out.println(horiLine);
    }

    public void markTask (int taskIndex) {
        this.tManager[taskIndex].markTaskDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tManager[taskIndex].getStatusIcon() + " " +
                           this.tManager[taskIndex].getDescription());
    }

    public void unmarkTask (int taskIndex) {
        this.tManager[taskIndex].unmarkTaskDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tManager[taskIndex].getStatusIcon() + " " +
                           this.tManager[taskIndex].getDescription());
    }

}

