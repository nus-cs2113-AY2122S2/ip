package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void printLine() {
        System.out.println("-----------------------------------------");
    }

    /**
     * Read task from the file and add to the taskList.
     *
     * @param newTask task in the file.
     */
    public void readTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Add the task that user inputs to the taskList.
     * Show the confirmation message of the task is added successfully.
     * Show the total number of task in the taskList.
     *
     * @param newTask new task which user inputted.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Print all the task in the taskList in the order of adding time.
     * If the task is empty, print that there is no task.
     */
    public void printTaskList() {
        printLine();
        if (taskList.size() == 0) {
            System.out.println(" List is empty! No task is here!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + "." + taskList.get(i));
            }
        }
        printLine();
    }

    /**
     * Return the total number of task added in the taskList.
     *
     * @return total number of the task in the taskList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Return the task at that index.
     *
     * @param index index of the task in the taskList.
     * @return the task at that index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Mark the task at that index to Done.
     * Show that the task has mark as Done.
     *
     * @param taskId index of the task in the taskList.
     */
    public void markDone(int taskId) {
        taskList.get(taskId - 1).setDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("\t" + taskList.get(taskId - 1));
        printLine();
    }

    /**
     * Read the status of the task from the file.
     * Update the status of the task to the taskList.
     *
     * @param taskId index of the task in the file.
     */
    public void readStatus(int taskId) {
        taskList.get(taskId - 1).setDone();
    }

    /**
     * Mark the task at that index to Not Done.
     * Show that the task has mark as Not Done.
     *
     * @param taskId index of the task in the taskList.
     */
    public void unmark(int taskId) {
        taskList.get(taskId - 1).setNotDone();
        printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("\t" + taskList.get(taskId - 1));
        printLine();
    }

    /**
     * Delete the task at that index.
     * Show that the task has deleted and the new total number
     * of task in the taskList.
     *
     * @param taskId index of the task in the taskList.
     */
    public void deleteTask(int taskId) {
        printLine();
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("\t" + taskList.get(taskId - 1));
        taskList.remove(taskId - 1);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Iterate through all the tasks and find and
     * print the task contains the keyword.
     * If no task is found, print no task found.
     *
     * @param keywords keyword which the user want to search.
     */
    public void findTask(String keywords) {
        boolean isFound = false;
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keywords) || taskList.get(i).getDate().contains(keywords)) {
                if (!isFound) {
                    System.out.println(" Here are the matching tasks in your list:");
                }
                System.out.println(" " + count + "." + taskList.get(i));
                count++;
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println(" No task is found.");
        }
        printLine();
    }

}
