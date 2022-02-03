public class DialogGenerator {
    public void printLine(){
        System.out.println("____________________________________________________________");
    }


    public void sayGoobye() {
        this.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.printLine();
    }

    public void displayTask(Task t, int taskCount){
        this.printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        this.printLine();
    }

    public void displayListWithStatus(Task[] allTasks, int taskCount) {
        this.printLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= taskCount; i++){
            System.out.println(i + ". " + allTasks[i-1]);
        }
        this.printLine();
    }

    public void markAndDisplayTask(Task t) {
        t.markAsDone();
        this.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(""+t);
        this.printLine();
    }

    public void unmarkAndDisplayTask(Task t) {
        t.markAsUnDone();
        this.printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(""+t);
        this.printLine();
    }

}
