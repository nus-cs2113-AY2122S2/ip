package duke;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> listOfTask = new ArrayList<>();
    private static int numOfTask = 0;

    public static Task getTaskFromListOfTask(int taskNumber) {
        // -1 to offset the counting of array list from 0
        return listOfTask.get(taskNumber - 1);
    }

    public static int getTaskNumberFromInput(String input) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(input.split(" ")[1]);
        // check to see if an index of < 0 was given
        if (index <= 0 || index > numOfTask) {
            throw new IndexOutOfBoundsException("Invalid task to be marked!");
        }
        return index;
    }

    public static void markTask(String input, String taskStatus) {
        try {
            int taskNumber = getTaskNumberFromInput(input);
            // true if it is "mark", set to false if it's not "mark"
            boolean isTaskDone = taskStatus.equalsIgnoreCase("mark");
            Task markedTask = getTaskFromListOfTask(taskNumber);
            markedTask.setDone(isTaskDone);
            if (isTaskDone) {
                System.out.println("Nice! I'v marked this task as done:");
            } else {
                System.out.println("Okay! I'v marked this task as not done:");
            }
            System.out.println(markedTask);
        } catch (IndexOutOfBoundsException idxError) {
            System.out.println("Invalid task number to be marked!");
        } catch (NumberFormatException numFormError) {
            System.out.println("Please enter a number to mark task.");
        }
    }

    public static Deadline createDeadlineTask(String input) {
        Deadline newDeadlineTask = null;
        try {
            String deadlineDescription = CommandParser.getDeadlineTaskDescription(input);
            String dueDateTime = CommandParser.getDeadlineDate(input);
            newDeadlineTask = new Deadline(deadlineDescription, dueDateTime);
        } catch (DukeException dukeError) {
            System.out.println(dukeError.getMessage());
            return null;
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        }

        return newDeadlineTask;
    }

    public static Event createEventTask(String input) {
        Event newEventTask = null;
        try {
            String eventDescription = CommandParser.getEventTaskDescription(input);
            String dueDate = CommandParser.getEventDateTime(input);
            newEventTask = new Event(eventDescription, dueDate);
        } catch (DukeException dukeError) {
            System.out.println(dukeError.getMessage());
            return null;
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        }

        return newEventTask;
    }

    public static Todo createTodoTask(String input) {
        Todo newTodoTask;
        try {
            String todoDescription = CommandParser.getToDoTaskDescription(input);
            newTodoTask = new Todo(todoDescription);
        } catch (DukeException dukeError) {
            System.out.println(dukeError.getMessage());
            return null;
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
            return null;
        }

        return newTodoTask;
    }

    public static void addTaskToTaskList(String input, String type) {
        Task newTask = null;
        switch (type.toLowerCase()){
        case "deadline":
            newTask = createDeadlineTask(input);
            break;
        case "event":
            newTask = createEventTask(input);
            break;
        case "todo":
            newTask = createTodoTask(input);
            break;
        default:
            System.out.println("Invalid type of task given!");
            return;
        }
        if (newTask != null) {
            listOfTask.add(newTask);
            numOfTask++;
            printTaskListUpdate(newTask);
        }
    }

    public static void loadTaskFromFileToProgram() {
        listOfTask = LocalStorage.getTaskFromFile();
        numOfTask = listOfTask.size();
        UI.printBorder();
        System.out.println("Total number of task loaded: " +numOfTask);
        UI.printBorder();
    }

    public static void saveTaskListToFile() {
        try {
            LocalStorage.saveCurrentTaskListToFile(listOfTask);
            System.out.println("Current task list has been saved!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printTaskListUpdate(Task newTask) {
        System.out.println("Got it!. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " +numOfTask +" tasks in the list.");
    }

    public static void printTaskList() {
        if (numOfTask == 0) {
            System.out.println("No task available!");
        }
        for (int i = 0 ; i < numOfTask; i++) {
            System.out.println(" " +(i + 1) +"." + listOfTask.get(i));
        }
    }
}
