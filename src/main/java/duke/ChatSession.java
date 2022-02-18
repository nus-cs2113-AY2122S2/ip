package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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

    public void addInitialTask(Task task) {
        taskList.add(task);
    }

    public void addTask(Task task) {
        // Create a new Task, append to taskList
        taskList.add(task);

        // Add and save to offline file
        try {
            this.addToFile(task);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

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

    // If the chat session receives invalid input
    public void printInvalidTask(DukeException e) {
        System.out.println("____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("____________________________________________________________");
    }

    // Save file to data directory
    public void addToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true);
        String output = task.saveString() + System.lineSeparator();
        fw.write(output);
        fw.close();
    }
}
