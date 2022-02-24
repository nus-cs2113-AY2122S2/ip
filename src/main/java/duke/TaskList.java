package duke;

import errors.Errors;
import task.Deadline;
import task.Event;
import task.Task;
import duke.Storage;
import task.Todo;

import java.util.ArrayList;

import static duke.Parser.getTaskNumberArgument;
import static errors.Errors.INVALID_TASK_DETAILS_ERROR;


public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static int taskCounter = 0;

    public TaskList(ArrayList<Task> listFromFile){
        taskList = listFromFile;
    }
    public static void addToDoTask(String input){
        try{
            String taskDescription = Todo.getToDoTask(input);
            Todo newTodo = new Todo(taskDescription);
            taskList.add(newTodo);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            Task.printTask(newTodo);
            Task.printNumberOfTasksInList(taskCounter);
        } catch (IndexOutOfBoundsException iobe){
            System.out.println(Errors.INVALID_TASK_DETAILS_ERROR);
        }
    }

    public static void addEventTask(String input){
        try {
            Event newEvent = new Event(Event.getEventTask(input), Event.getEventDateTime(input));
            taskList.add(newEvent);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            Task.printTask(newEvent);
            Task.printNumberOfTasksInList(taskCounter);
        } catch(StringIndexOutOfBoundsException se){
            System.out.println(Errors.INVALID_TASK_DETAILS_ERROR);
        }
    }

    public static void addDeadlineTask(String input){
        try {
            Deadline newDeadline = new Deadline(Deadline.getDeadlineTask(input), Deadline.getDeadlineDate(input));
            taskList.add(newDeadline);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            Task.printTask(newDeadline);
            Task.printNumberOfTasksInList(taskCounter);
        } catch (StringIndexOutOfBoundsException se){
            System.out.println(INVALID_TASK_DETAILS_ERROR);
        }
    }

    public static void markTaskAsComplete(String input){
        try {
            int taskNum = getTaskNumberArgument(input);
            Task taskToMark = taskList.get(taskNum-1);
            taskToMark.setDone(true);
            Task.printTask(taskToMark);
            System.out.println("Nice! I've marked this task as done:\n");
        } catch (NumberFormatException nfe){
            System.out.println(Errors.INVALID_TASK_MARK_ERROR);
        }
    }

    public static void unmarkTaskAsIncomplete(String input){
        int taskNum = getTaskNumberArgument(input);
        taskList.get(taskNum-1).setDone(false);
        Task.printTask(taskList.get(taskNum-1));
        System.out.println("Ok I have marked this task as incomplete:\n");
    }

    public static void deleteTask(String input){
        try {
            int taskNum = getTaskNumberArgument(input);
            taskList.remove(taskNum-1);
            System.out.println("Ok I have deleted this task as requested:\n");
            taskCounter--;
        } catch (NumberFormatException nfe){
            System.out.println(Errors.INVALID_TASK_DELETE_ERROR);
        }
    }
}
