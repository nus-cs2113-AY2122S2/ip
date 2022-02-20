package duke;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;

import static java.util.stream.Collectors.toList;

public class TaskList {
    protected LocalStorage localInstance;
    protected ArrayList<Task> listOfTask;

    public TaskList(LocalStorage localStorage) {
        this.localInstance = localStorage;
        this.listOfTask = localInstance.getTasksFromFile();
        System.out.println("Total number of task loaded: " +listOfTask.size());
        UI.printBorder();
    }

    /**
     * Returns a Task from task list with the specified index.
     *
     * @param taskNumber A specified index of the task.
     * @return Task at specified index.
     */
    private Task getTaskFromListOfTask(int taskNumber) {
        // -1 to offset the counting of array list from 0
        return listOfTask.get(taskNumber - 1);
    }

    /**
     * Returns the task number specified in the user's input
     * The function processes the whole user input as a string.
     * The function assumes the task number is located as the second word of the command
     * E.g. mark 2, 2 would be the second word
     *
     * @param userInput The whole user command as String.
     * @return Task number as an int
     * @throws IndexOutOfBoundsException If the specified task number is out of range
     * @throws NumberFormatException If the specified task number is not a number
     */
    private int getTaskNumberFromInput(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        int indexOfTask = Integer.parseInt(userInput.split(" ")[1]);
        // check to see if an index of < 0 was given
        if (indexOfTask <= 0 || indexOfTask > listOfTask.size()) {
            throw new IndexOutOfBoundsException("Invalid task to be marked!");
        }
        return indexOfTask;
    }

    /**
     * Process the user's input and task type to create respective Task.
     * If the task type does not match Duke's supporting tasks
     * it would not create a Task.
     *
     * @param userInput The whole user command as String.
     * @param taskType The task type as String
     */
    public void addTaskToTaskList(String userInput, String taskType) {
        Task newTask;
        switch (taskType.toLowerCase()){
        case "deadline":
            newTask = createDeadlineTask(userInput);
            break;
        case "event":
            newTask = createEventTask(userInput);
            break;
        case "todo":
            newTask = createTodoTask(userInput);
            break;
        default:
            System.out.println("Invalid type of task given!");
            return;
        }
        if (newTask != null) {
            listOfTask.add(newTask);
            printTaskUpdate(newTask, "added");
            saveTaskListToFile();
        }
    }

    /**
     * Updates the status of the task with specified task number,
     * to either done or not done.
     * The function processes the whole user input as a string.
     * The function would get the task number
     * to be marked/unmarked from the user input.
     * If an invalid task number is provided, no task is marked.
     * Else, it would update the task status with the respective status provided.
     *
     * @param userInput The whole user command as String.
     * @param taskStatus The new status of the task as String.
     */
    public void markTaskInTaskList(String userInput, String taskStatus) {
        try {
            int indexOfTaskInList = getTaskNumberFromInput(userInput);
            // true if it is "mark", set to false if it is "unmark"
            boolean isTaskDone = taskStatus.equalsIgnoreCase("mark");
            Task taskToBeMark = getTaskFromListOfTask(indexOfTaskInList);
            taskToBeMark.setDone(isTaskDone);
            if (isTaskDone) {
                System.out.println("Nice! I'v marked this task as done:");
            } else {
                System.out.println("Okay! I'v marked this task as not done:");
            }
            System.out.println(taskToBeMark);
            saveTaskListToFile();
        } catch (IndexOutOfBoundsException idxError) {
            System.out.println("Invalid task number to be marked!");
        } catch (NumberFormatException numFormError) {
            System.out.println("Please enter a number to mark task.");
        }
    }

    /**
     * Deletes a task with specified task number.
     * The function processes the whole user input as a string.
     * The function would get the task number to be deleted from the user input.
     * If an invalid task number is provided, no task is deleted.
     * Else, the task would be deleted from the TaskList.
     *
     * @param userInput The whole user command as String.
     */
    public void deleteTaskInTaskList(String userInput) {
        try {
            int indexOfTaskInList = getTaskNumberFromInput(userInput);
            Task taskToBeDeleted = getTaskFromListOfTask(indexOfTaskInList);
            listOfTask.remove(taskToBeDeleted);
            printTaskUpdate(taskToBeDeleted, "deleted");
            saveTaskListToFile();
        } catch (IndexOutOfBoundsException idxError) {
            System.out.println("Invalid task number to be marked!");
        } catch (NumberFormatException numFormError) {
            System.out.println("Please enter a number to mark task.");
        }
    }

    /**
     * Finds and print a list of task that contains
     * a substring of the specified search string by the user input.
     * If no task matches the search string it would let the user know,
     * else, a list of task containing the search string will be printed
     *
     * @param userInput The whole user command as String.
     */
    public void findTaskInTaskList(String userInput) {
        String searchString = CommandParser.getSearchStringFromUserInput(userInput);
        if (searchString == null) {
            return;
        }
        ArrayList<Task> listOfMatchedTask = getSearchedList(searchString);
        if (listOfMatchedTask == null) {
            System.out.println("Oops! It seems that we could not find " +
                    "what you were looking for! Please try again.");
        } else {
            System.out.println("The tasks that matched your inputs are:");
            printTaskList(listOfMatchedTask);
        }
    }

    /**
     * Driver function to call printTaskList().
     */
    public void printTasksFromTaskList() {
        printTaskList(listOfTask);
    }

    /**
     * Adds deadline Task to the list and returns the same task.
     * Else, a null is returned.
     * The function processes the whole user input as a string.
     * This function would process the user input to get the
     * description and deadline date.
     * It would then create a new deadline task and add to the current list of task.
     * The newly created deadline task would be returned.
     *
     * @param userInput The whole user command as String.
     * @return The deadline task created, else null
     */
    private Deadline createDeadlineTask(String userInput) {
        Deadline newDeadlineTask = null;
        try {
            String deadlineDescription = CommandParser.getDeadlineDescription(userInput);
            String deadlineDateString = CommandParser.getDeadlineDate(userInput);
            LocalDate deadlineDate = CommandParser.getDateFormat(deadlineDateString);
            newDeadlineTask = new Deadline(deadlineDescription, deadlineDate);
        } catch (DukeException | DateTimeParseException error) {
            System.out.println(error.getMessage());
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        }

        return newDeadlineTask;
    }

    /**
     * Adds event Task to the list and returns the same task.
     * Else, a null is returned.
     * The function processes the whole user input as a string.
     * This function would process the user input to get the
     * description and event date.
     * It would then create a new event task and add to the current list of task.
     * The newly created event task would be returned.'
     *
     * @param userInput The whole user command as String.
     * @return The event task created, else null
     */
    private Event createEventTask(String userInput) {
        Event newEventTask = null;
        try {
            String eventDescription = CommandParser.getEventDescription(userInput);
            String eventDateString = CommandParser.getEventDate(userInput);
            LocalDate eventDate = CommandParser.getDateFormat(eventDateString);
            newEventTask = new Event(eventDescription, eventDate);
        } catch (DukeException | DateTimeParseException error) {
            System.out.println(error.getMessage());
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        }

        return newEventTask;
    }

    /**
     * Adds todo Task to the list and returns the same task.
     * Else, a null is returned.
     * The function processes the whole user input as a string.
     * This function would process the user input to get the
     * description.
     * It would then create a new todo task and add to the current list of task.
     * The newly created todo task would be returned.'
     *
     * @param userInput The whole user command as String.
     * @return The todo task created, else null
     */
    private Todo createTodoTask(String userInput) {
        Todo newTodoTask = null;
        try {
            String todoDescription = CommandParser.getToDoDescription(userInput);
            newTodoTask = new Todo(todoDescription);
        } catch (DukeException dukeError) {
            System.out.println(dukeError.getMessage());
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        }

        return newTodoTask;
    }

    /**
     *  Returns a list of task where task description
     *  contains the search string provided.
     *  If none of the descriptions match, return null.
     *
     * @param stringToMatch The String to match provided by the user.
     * @return List of task containing the matched string. Null if list is empty.
     */
    private ArrayList<Task> getSearchedList(String stringToMatch) {
        ArrayList<Task> listOfSearchedTask = (ArrayList<Task>) listOfTask.stream()
                .filter((t) -> t.getDescription().contains(stringToMatch))
                .collect(toList());
        if (listOfSearchedTask.size() == 0) {
            return null;
        }
        return listOfSearchedTask;
    }

    /**
     * Saves the current task list to file
     */
    public void saveTaskListToFile() {
        try {
            localInstance.saveCurrentTaskListToFile(listOfTask);
            System.out.println("Current task list has been saved!");
        } catch (IOException e) {
            System.out.println("Oops! IO exception occurred at: " +e.getMessage());
        }
    }

    /**
     * Prints an update message when a task is added or deleted
     *
     * @param taskObject The task to be printed.
     * @param commandType The type of operation, added or deleted, as String.
     */
    public void printTaskUpdate(Task taskObject, String commandType) {
        UI.printBorder();
        if (commandType.equalsIgnoreCase("added")) {
            System.out.println("Got it!. I've added this task:");
        } else if (commandType.equalsIgnoreCase("deleted")) {
            System.out.println("Got it!. I've removed this task:");
        }
        System.out.println(taskObject);
        System.out.println("Now you have " +listOfTask.size() +" tasks in the list.");
        UI.printBorder();
    }

    /**
     * Prints the list of task from listOfTaskToBePrinted
     * The function checks if the list is empty.
     * If the list is empty it would let the user know.
     * Else the list of task is printed.
     *
     * @param listOfTaskToBePrinted The list of task to be printed
     */
    private void printTaskList(ArrayList<Task> listOfTaskToBePrinted) {
        if (listOfTaskToBePrinted.size() == 0) {
            System.out.println("No task available!");
        }
        for (int i = 0 ; i < listOfTaskToBePrinted.size(); i++) {
            System.out.println(" " +(i + 1) +"." + listOfTaskToBePrinted.get(i));
        }
    }
}
