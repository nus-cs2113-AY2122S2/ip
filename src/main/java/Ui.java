import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    /**
     * display a line separator
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * display greeting message
     */
    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(""+logo);
        this.printLine();
        System.out.println("Hello I'm Duke.");
        System.out.println("What can I do for you?");
        this.printLine();
        System.out.println();
    }

    /**
     * read user input
     *
     * @return
     */
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        return command;
    }

    /**
     * display goodbye message
     */
    public void sayGoobye() {
        this.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.printLine();
    }

    /**
     * display added task
     *
     * @param t
     * @param taskCount
     */
    public void displayTask(Task t, int taskCount) {
        this.printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        this.printLine();
    }

    /**
     * display task list
     *
     * @param allTasks
     * @param taskCount
     */
    public void displayListWithStatus(ArrayList<Task> allTasks, int taskCount) {
        this.printLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= taskCount; i++){
            System.out.println(i + ". " + allTasks.get(i - 1));
        }
        this.printLine();
    }

    /**
     * display founded tasks
     *
     * @param foundTasks
     */
    public void displayFoundTasks(ArrayList<Task> foundTasks) {
        this.printLine();
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 1; i <= foundTasks.size(); i++){
            System.out.println(i + ". " + foundTasks.get(i - 1));
        }
        this.printLine();
    }

    /**
     * display mark done result
     *
     * @param t
     */
    public void markAndDisplayTask(Task t) {
        t.markAsDone();
        this.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        this.printLine();
    }

    /**
     * display mark undone result
     *
     * @param t
     */
    public void unmarkAndDisplayTask(Task t) {
        t.markAsUnDone();
        this.printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        this.printLine();
    }

    /**
     * display delete task result
     *
     * @param t
     * @param taskCount
     */
    public void deleteAndDisplayTask(Task t, int taskCount) {
        this.printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        this.printLine();
    }

    /**
     * display error message
     */
    public void raiseExceptionInDeadline(){
        this.printLine();
        System.out.println(" ☹ OOPS!!! The description or by of a deadline cannot be empty.");
        this.printLine();
    }

    /**
     * display error message
     */
    public void raiseExceptionInEvent(){
        this.printLine();
        System.out.println(" ☹ OOPS!!! The description or at of an event cannot be empty.");
        this.printLine();
    }

    /**
     * display error message
     */
    public void raiseExceptionInTodo(){
        this.printLine();
        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
        this.printLine();
    }

    /**
     * display error message
     */
    public void raiseExceptionInCommand(){
        this.printLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
        this.printLine();
    }

    /**
     * display error message
     */
    public void raiseExceptionInIndex(){
        this.printLine();
        System.out.println("☹ OOPS!!! Please use a valid index number.");
        this.printLine();
    }

    /**
     * display error message
     */
    public void showLoadingError(){
        this.printLine();
        System.out.println("NO file found.");
        this.printLine();
    }
}
