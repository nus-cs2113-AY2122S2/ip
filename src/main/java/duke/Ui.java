package duke;

import duke.task.Task;

import java.util.List;

public class Ui {
    public static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm \n" + logo);
        System.out.println("What can I do for you?\n");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Ui feedback of succesful toggling of task's mark status
     * @param isDone
     * @param status
     */
    public static void printMarkTaskStatus(Boolean isDone, String status){
        if (isDone) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I've marked this task as not done yet: ");
        }
        System.out.println(status);
    }


    /**
     * Ui feedback of succesful removal of task from TaskList
     * @param status 
     * @param size Number of lists in task
     */
    public static void printRemovedTask(String status, Integer size){
        System.out.println(String.format("Noted. I've removed this task"));
        System.out.println(String.format("%s", status));
        System.out.println(String.format("Now you have %d tasks in the list", size));
    }


    /**
     * Prints an error that item is not a number
     * @param item The variable 
     */
    public static void printNaNError(String item){
        System.out.println("OOPS, " + item + " provided is NOT a number");
    }


    /**
     * Prints an error when given idx and list size leads to index out of bounds
     * @param idx list idx
     * @param size size of the list
     */
    public static void printIndexError(Integer idx, Integer size){
        System.out.println(String.format("OOPS, %d exceed your list of size %d", idx, size));
    }

    public static void printParamMissing(String funcName){
        System.out.println("Parameters for " + funcName + " are missing");
    }

    /**
     * A prompt to type something when given nothing in command line input
     */
    public static void printEmptyInput(){
        System.out.println("Please type something\n");
    }

    public static void printEmptyDescription(){
        System.out.println("☹ OOPS!!! The description cannot be empty.");
    }

    // public static void printFileOpenError(){

    // }

    /**
     * Prints out the tasks of a given task list. Will print 
     * empty message if list is empty
     * @param taskList ArrayList of Tasks
     */
    public static void printTasks(List<Task> taskList){
        if (taskList.size() == 0){
//            System.out.println("Seems like you're free of any task!");
            System.out.println("No tasks found!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int idx = 1;
        for (Task todo : taskList) {
            System.out.println(String.format("%d. %s", idx, todo.getStatus()));
            idx += 1;
        }
    }

    /**
     * Ui feedback off successful task adding
     * @param tasks TaskList object
     * @param newTask Newly added task
     */
    public static void printTaskAdded(TaskList tasks, Task newTask){
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask.getStatus());
        Ui.printNumberOfTasks(tasks);
    }

    /**
     * Ui output when querying number of tasks on the list
     * @param tasks TaskList object
     */
    public static void printNumberOfTasks(TaskList tasks){
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getTaskListSize()));

    }

    public static void printInvalidCommand(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("Type 'help' command to get to know the rest of the commands");
    }

    /**
     * Ui explanation of summary of commands and usuage
     */
    public static void printHelp() {
        System.out.println( "Here are the list of things I can do for you:\n" +
                "1. Adding todos: add todo NAME\n" +
                "2. Adding events: add event NAME /at DATE\n" +
                "3. Adding deadlines: add deadline /by DATE\n" +
                "4. Listing tasks: list\n" +
                "5. Marking tasks: mark INDEX\n" +
                "5. Unmarking tasks: unmark INDEX\n" +
                "6. Searching tasks: find KEYWORD\n" +
                "7. Deleting tasks: delete INDEX\n" +
                "8. Command summary: help\n" +
                "9. Exiting the program : bye");
    }

}
