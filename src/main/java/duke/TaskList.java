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
        int indexOfTask = Integer.parseInt(input.split(" ")[1]);
        // check to see if an index of < 0 was given
        if (indexOfTask <= 0 || indexOfTask > listOfTask.size()) {
            throw new IndexOutOfBoundsException("Invalid task to be marked!");
        }
        return indexOfTask;
    }

    public void addTaskToTaskList(String userInput, String taskType) {
        Task newTask = null;
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

    public void printTasksFromTaskList() {
        printTaskList(listOfTask);
    }

    private Deadline createDeadlineTask(String userInput) {
        Deadline newDeadlineTask = null;
        try {
            String deadlineDescription = CommandParser.getDeadlineDescription(userInput);
            String deadlineDateString = CommandParser.getDeadlineDate(userInput);
            LocalDate deadlineDate = CommandParser.getDateFormat(deadlineDateString);
            newDeadlineTask = new Deadline(deadlineDescription, deadlineDate);
        } catch (DukeException dukeError) {
            System.out.println(dukeError.getMessage());
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        } catch (DateTimeParseException dateError) {
            System.out.println(dateError.getMessage());
        }

        return newDeadlineTask;
    }

    private Event createEventTask(String userInput) {
        Event newEventTask = null;
        try {
            String eventDescription = CommandParser.getEventDescription(userInput);
            String eventDateString = CommandParser.getEventDate(userInput);
            LocalDate eventDate = CommandParser.getDateFormat(eventDateString);
            newEventTask = new Event(eventDescription, eventDate);
        } catch (DukeException dukeError) {
            System.out.println(dukeError.getMessage());
        } catch (StringIndexOutOfBoundsException idxError) {
            System.out.println("Please check your command and formatting again!");
        } catch (DateTimeParseException dateError) {
            System.out.println(dateError.getMessage());
        }

        return newEventTask;
    }

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

    private ArrayList<Task> getSearchedList(String stringToMatch) {
        ArrayList<Task> listOfSearchedTask = (ArrayList<Task>) listOfTask.stream()
                .filter((t) -> t.getDescription().contains(stringToMatch))
                .collect(toList());
        if (listOfSearchedTask.size() == 0) {
            return null;
        }
        return listOfSearchedTask;
    }

    public void saveTaskListToFile() {
        try {
            localInstance.saveCurrentTaskListToFile(listOfTask);
            System.out.println("Current task list has been saved!");
        } catch (IOException e) {
            System.out.println("Oops! IO exception occurred at: " +e.getMessage());
        }
    }

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

    private void printTaskList(ArrayList<Task> listOfTaskToBePrinted) {
        if (listOfTaskToBePrinted.size() == 0) {
            System.out.println("No task available!");
        }
        for (int i = 0 ; i < listOfTaskToBePrinted.size(); i++) {
            System.out.println(" " +(i + 1) +"." + listOfTaskToBePrinted.get(i));
        }
    }
}
