package duke;

import duke.task.Task;
import java.util.ArrayList;

public class ChatSession {
    // Store subclasses of tasks
    ArrayList<Task> taskList;

    public ChatSession() {
        this.taskList = new ArrayList<Task>();
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
        // zero-based indexing
        String output = taskList.get(taskID - 1).markTask();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(output);
        System.out.println("____________________________________________________________");
    }

    public void unmarkTaskIndex(int taskID) {
        // zero-based indexing
        String output = taskList.get(taskID - 1).unmarkTask();
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(output);
        System.out.println("____________________________________________________________");
    }

    public void deleteTaskIndex(int taskID) {
        String output = taskList.remove(taskID - 1).toString();
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task");
        System.out.println(output);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        System.out.println("____________________________________________________________");
    }

    // If the chat session receives invalid input
    public void printInvalidTask(DukeException e) {
        System.out.println("____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("____________________________________________________________");
    }
}
