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

public class TaskList {
    private LocalStorage localInstance;
    private ArrayList<Task> listOfTask;

    public TaskList(LocalStorage localStorage) {
        this.localInstance = localStorage;
        this.listOfTask = localInstance.getTasksFromFile();
        System.out.println("Total number of task loaded: " +listOfTask.size());
        UI.printBorder();
    }

    private Task getTaskFromListOfTask(int taskNumber) {
        // -1 to offset the counting of array list from 0
        return listOfTask.get(taskNumber - 1);
    }

    private int getTaskNumberFromInput(String input) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(input.split(" ")[1]);
        // check to see if an index of < 0 was given
        if (index <= 0 || index > listOfTask.size()) {
            throw new IndexOutOfBoundsException("Invalid task to be marked!");
        }
        return index;
    }

    public void addTaskToTaskList(String input, String type) {
        Task newTask;
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
            printTaskListUpdate(newTask, "added");
            saveTaskListToFile();
        }
    }

    public void markTask(String input, String taskStatus) {
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
            saveTaskListToFile();
        } catch (IndexOutOfBoundsException idxError) {
            System.out.println("Invalid task number to be marked!");
        } catch (NumberFormatException numFormError) {
            System.out.println("Please enter a number to mark task.");
        }
    }

    public void deleteTask(String input) {
        try {
            int taskNumber = getTaskNumberFromInput(input);
            Task toBeDeletedTask = getTaskFromListOfTask(taskNumber);
            listOfTask.remove(toBeDeletedTask);
            printTaskListUpdate(toBeDeletedTask, "deleted");
            saveTaskListToFile();
        } catch (IndexOutOfBoundsException idxError) {
            System.out.println("Invalid task number to be marked!");
        } catch (NumberFormatException numFormError) {
            System.out.println("Please enter a number to mark task.");
        }
    }

    public void findTaskInTaskList(String input) {
        String searchString = CommandParser.getSearchStringFromUserInput(input);
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

    public void printTasksFromTaskList() {
        printTaskList(listOfTask);
    }

    private Deadline createDeadlineTask(String input) {
        Deadline newDeadlineTask = null;
        try {
            String deadlineDescription = CommandParser.getDeadlineTaskDescription(input);
            String dueDateTime = CommandParser.getDeadlineDate(input);
            LocalDate deadlineDate = CommandParser.getDateFormat(dueDateTime);
            newDeadlineTask = new Deadline(deadlineDescription, deadlineDate);
        } catch (DukeException dukeError) {
            System.out.println(dukeError.getMessage());
            return null;
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        } catch (DateTimeParseException dateError) {
            System.out.println(dateError.getMessage());
        }

        return newDeadlineTask;
    }

    private Event createEventTask(String input) {
        Event newEventTask = null;
        try {
            String eventDescription = CommandParser.getEventTaskDescription(input);
            String dueDate = CommandParser.getEventDateTime(input);
            LocalDate eventDate = CommandParser.getDateFormat(dueDate);
            newEventTask = new Event(eventDescription, eventDate);
        } catch (DukeException dukeError) {
            System.out.println(dukeError.getMessage());
            return null;
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        } catch (DateTimeParseException dateError) {
            System.out.println(dateError.getMessage());
        }

        return newEventTask;
    }

    private Todo createTodoTask(String input) {
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

    public void saveTaskListToFile() {
        try {
            localInstance.saveCurrentTaskListToFile(listOfTask);
            System.out.println("Current task list has been saved!");
        } catch (IOException e) {
            System.out.println("Oops! IO exception occurred at: " +e.getMessage());
        }
    }

    public void printTaskListUpdate(Task taskObject, String commandType) {
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

    public void printTaskList() {
        if (listOfTask.size() == 0) {
            System.out.println("No task available!");
        }
        for (int i = 0 ; i < listOfTask.size(); i++) {
            System.out.println(" " +(i + 1) +"." + listOfTask.get(i));
        }
    }
}
