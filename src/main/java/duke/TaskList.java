package duke;

import errors.Errors;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.util.ArrayList;

import static duke.Parser.getTaskDetailsForFind;
import static duke.Parser.getTaskNumberArgument;
import static errors.Errors.INVALID_TASK_DETAILS_ERROR;
import static task.Task.printTask;

/**
 * This class is for all operations involving Tasklist which stores all the tasks
 *
 */

public class TaskList {

    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static int taskCounter = 0;

    /**
     * Constructor used to initialise task list from arraylist that is retrieved from file
     *
     * @param listFromFile arraylist derived from file
     *
     */
    public TaskList(ArrayList<Task> listFromFile){
        taskList = listFromFile;
    }

    /**
     * Add task of type todo to taskList
     *
     * @param input task details for task type todo
     *
     */
    public static void addToDoTask(String input){
        try{
            String taskDescription = Todo.getToDoTask(input);
            Todo newTodo = new Todo(taskDescription);
            taskList.add(newTodo);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            printTask(newTodo);
            TaskList.printNumberOfTasksInList(taskCounter);
        } catch (IndexOutOfBoundsException iobe){
            System.out.println(Errors.INVALID_TASK_DETAILS_ERROR);
        }
    }
    /**
     * Add task of type event to taskList
     *
     * @param input task details for task type event
     *
     */
    public static void addEventTask(String input){
        try {
            Event newEvent = new Event(Event.getEventTask(input), Event.getEventDateTime(input));
            taskList.add(newEvent);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            printTask(newEvent);
            TaskList.printNumberOfTasksInList(taskCounter);
        } catch(StringIndexOutOfBoundsException se){
            System.out.println(Errors.INVALID_TASK_DETAILS_ERROR);
        }
    }
    /**
     * Add task of type deadline to taskList
     *
     * @param input task details for task type deadline
     *
     */
    public static void addDeadlineTask(String input){
        try {
            Deadline newDeadline = new Deadline(Deadline.getDeadlineTask(input), Deadline.getDeadlineDate(input));
            taskList.add(newDeadline);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            printTask(newDeadline);
        } catch (StringIndexOutOfBoundsException se){
            System.out.println(INVALID_TASK_DETAILS_ERROR);
        }
    }
    /**
     * Mark a task in taskList as complete based on index
     *
     * @param input user input of task they want to mark as complete
     *
     */
    public static void markTaskAsComplete(String input){
        try {
            int taskNum = getTaskNumberArgument(input);
            taskList.get(taskNum-1).setDone(true);
            printTask(taskList.get(taskNum-1));
            System.out.println("Nice! I've marked this task as done:\n");
        } catch (NumberFormatException nfe){
            System.out.println(Errors.INVALID_TASK_MARK_ERROR);
        }
    }

    /**
     * print all tasks in arraylist
     *
     * @param list Arraylist of tasks to traverse and print out
     *
     */
    public static void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1 + ".");
            printTask(list.get(i));
        }
        printNumberOfTasksInList(list.size());
    }

    /**
     * Pretty print number of tasks in list based on input
     *
     * @param taskCounter counter to print out neatly
     *
     */
    public static void printNumberOfTasksInList(int taskCounter) {
        System.out.println("Now you have " + taskCounter + " tasks in the list.");
    }

    /**
     * Mark a task in taskList as incomplete (using unmark command) based on index
     *
     * @param input user input of task they want to mark/unmark as incomplete
     *
     */
    public static void markTaskAsIncomplete(String input){
        int taskNum = getTaskNumberArgument(input);
        taskList.get(taskNum-1).setDone(false);
        printTask(taskList.get(taskNum-1));
        System.out.println("Ok I have marked this task as incomplete:\n");
    }

    /**
     * Delete a task in taskList based on index
     *
     * @param input user input of task they want to mark as complete
     *
     */
    public static void deleteTask(String input) {
        try {
            int taskNum = getTaskNumberArgument(input);
            taskList.remove(taskNum - 1);
            System.out.println("Ok I have deleted this task as requested:\n");
            if (taskCounter > 0){
                taskCounter--;
            }
        } catch (NumberFormatException nfe) {
            System.out.println(Errors.INVALID_TASK_DELETE_ERROR);
        } catch (IndexOutOfBoundsException i) {
            System.out.println(Errors.INVALID_TASK_MARK_ERROR);
        }
    }

    /**
     * Find a task in taskList based on matchingsearch input string
     *
     * @param input user input of task they want to find all occurances of search string
     *
     */
    public static void findTask(String input) {
        ArrayList <Task> matchingTaskList = new ArrayList<Task>();
        for (Task listTask : taskList){
            if (listTask.getTaskName().trim().contains(getTaskDetailsForFind(input))){
                matchingTaskList.add(listTask);
            }
        }
        printList(matchingTaskList);
    }
}
