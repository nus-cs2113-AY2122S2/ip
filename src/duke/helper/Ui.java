package duke.helper;

public class Ui {
    public static final String LINEBREAK = "____________________________________________________________";
    public void printExceptionMessage(String message) {
        System.out.println(LINEBREAK);
        System.out.println(" Error: " + message);
        System.out.println(LINEBREAK);
    }

    public void printMarkedMessage(boolean mark, String taskName) {
        System.out.println(LINEBREAK);
        if (mark) {
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("  [X] " + taskName);
        }else {
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + taskName);
        }
        System.out.println(LINEBREAK);
    }

    public void printAddTaskMessage(String task, int taskCount) {
        System.out.println(LINEBREAK);
        System.out.println(" added: " + task);
        System.out.println(" Total number of tasks now: " + taskCount);
        System.out.println(LINEBREAK);
    }

    public void printStartUpMessage(boolean isLoaded) {
        System.out.println(" Hello! I'm Duke\n What can I do for you?");
        System.out.println(LINEBREAK);
        if (isLoaded) {
            System.out.println(" Loaded Save File.");
            System.out.println(LINEBREAK);
        }
    }

    public void printExitMessage() {
        System.out.println(LINEBREAK);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINEBREAK);
    }

    public void printDeleteMessage(String task, int taskCount) {
        System.out.println(LINEBREAK);
        System.out.println(" I have removed this task:");
        System.out.println(task);

        System.out.println(" Total number of tasks now: " + taskCount);
        System.out.println(LINEBREAK);
    }
}
