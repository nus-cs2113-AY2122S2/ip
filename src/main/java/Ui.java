

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

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

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        return command;
    }

    public void sayGoobye() {
        this.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.printLine();
    }

    public void displayTask(Task t, int taskCount) {
        this.printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        this.printLine();
    }

    public void displayListWithStatus(ArrayList<Task> allTasks, int taskCount) {
        this.printLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= taskCount; i++){
            System.out.println(i + ". " + allTasks.get(i - 1));
        }
        this.printLine();
    }

    public void displayFoundTasks(ArrayList<Task> foundTasks) {
        this.printLine();
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 1; i <= foundTasks.size(); i++){
            System.out.println(i + ". " + foundTasks.get(i - 1));
        }
        this.printLine();
    }

    public void markAndDisplayTask(Task t) {
        t.markAsDone();
        this.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        this.printLine();
    }

    public void unmarkAndDisplayTask(Task t) {
        t.markAsUnDone();
        this.printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        this.printLine();
    }

    public void deleteAndDisplayTask(Task t, int taskCount) {
        this.printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        this.printLine();
    }

    public void raiseExceptionInDeadline(){
        this.printLine();
        System.out.println(" ☹ OOPS!!! The description or by of a deadline cannot be empty.");
        this.printLine();
    }

    public void raiseExceptionInEvent(){
        this.printLine();
        System.out.println(" ☹ OOPS!!! The description or at of an event cannot be empty.");
        this.printLine();
    }

    public void raiseExceptionInTodo(){
        this.printLine();
        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
        this.printLine();
    }

    public void raiseExceptionInCommand(){
        this.printLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
        this.printLine();
    }

    public void showLoadingError(){
        this.printLine();
        System.out.println("NO file found.");
        this.printLine();
    }
}
