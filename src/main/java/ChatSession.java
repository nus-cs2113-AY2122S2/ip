import java.util.ArrayList;

public class ChatSession {
    ArrayList<Task> taskList = new ArrayList<Task>();

    public ChatSession() {

    }

    public void startSession() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void endSession() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void addTask(Task task) {
        // Create a new Task, append to taskList
//        Task newTask = new Task(description);
        taskList.add(task);
        // Return the added task
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        System.out.println("____________________________________________________________");
    }

    public void printTaskList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i+1, taskList.get(i)));
        }
        System.out.println("____________________________________________________________");
    }

    public void markTaskIndex(int taskID) {
        String output = taskList.get(taskID - 1).markTask(); // zero-based indexing

        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(output);
        System.out.println("____________________________________________________________");
    }

    public void unmarkTaskIndex(int taskID) {
        String output = taskList.get(taskID - 1).unmarkTask(); // zero-based indexing

        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(output);
        System.out.println("____________________________________________________________");
    }
}
