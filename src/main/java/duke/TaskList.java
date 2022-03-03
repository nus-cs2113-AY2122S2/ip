package duke;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

/**
 * Class to contain the list of tasks and methods to interact with the list.
 */
public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();
    Storage storage;
    void TaskList() {
        storage = new Storage();
    }

    /**
     * Lists the tasks from the task list.
     */
    public void listTasks() {
        System.out.println("    Here are the tasks in your list:");
        int i=1;
        for (Task t: taskList) {
            System.out.println("    "+(i++ )+": "+t);
        }
    }

    /**
     * Marks a task denoted by its index as complete.
     * @param task Input string that contains the mark command and the task index to be marked.
     */
    public void markTask(String task) {
        try {
            storage = new Storage();
            task = task.replace("mark ", "");
            int i = Integer.parseInt(task) - 1;
            if (taskList.get(i) != null) {
                taskList.get(i).setDone(true);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + taskList.get(i));
                storage.writeTaskFile();
            } else {
                System.out.println("    Please enter a valid task number");
            }
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                System.out.println("    OOPS there are no tasks in the list");
            } else {
                System.out.println("    OOPS there are only " + taskList.size() + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please enter the number of the task you want to mark");
        }
    }

    /**
     * Unmarks a task denoted by its index as complete.
     * @param task Input string that contains the unmark command and the task index to be unmarked.
     */
    public void unmarkTask(String task) {
        try {
            storage = new Storage();
            task = task.replace("unmark ","");
            int i = Integer.parseInt(task)-1;
            if (taskList.get(i) != null) {
                taskList.get(i).setDone(false);
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("    " + taskList.get(i));
                storage.writeTaskFile();
            } else {
                System.out.println("    Please enter a valid task number");
            }
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                System.out.println("    OOPS there are no tasks in the list");
            } else {
                System.out.println("    OOPS there are only " + taskList.size() + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please enter the number of the task you want to unmark");
        }

    }

    /**
     * Deleted a task denoted by its index from the task list.
     * @param task Input string that contains the delete command and the task index to be deleted.
     */
    public void deleteTask(String task) {
        try {
            storage = new Storage();
            task = task.replace("delete ","");
            int i = Integer.parseInt(task)-1;
            if (taskList.get(i) != null) {
                Task removedTask = taskList.get(i);
                taskList.remove(i);
                System.out.println("    Noted. I've removed this task:");
                System.out.println("    "+removedTask);
                System.out.println("    Now you have "+taskList.size()+" tasks in the list");
                storage.writeTaskFile();
            }
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                System.out.println("    OOPS there are no tasks in the list");
            } else {
                System.out.println("    OOPS there are only " + taskList.size() + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please enter the number of the task you want to delete");
        }
    }

    /**
     * Adds a task to the task list after determining the type of the task.
     * @param task Input string that contains the task type and the task desccription.
     * @throws DukeException If the task type is present but the task description is empty.
     */
    public void addTask(String task) {
        try {
            storage = new Storage();
            int index = task.indexOf(' ');
            String taskType = task.substring(0, index);
            task = task.substring(index + 1);
            if (task.equals("")) {
                throw new DukeException();
            }
            switch (taskType) {
            case "todo":
                addAsTodo(task);
                break;
            case "deadline":
                addAsDeadline(task);
                break;
            case "event":
                addAsEvent(task);
                break;
            default:
                System.out.println("    Sorry I do not know what that means");
                break;
            }
        } catch (IndexOutOfBoundsException | DukeException e) {
            if(task.equals("")) {
                System.out.println("    OOPS!!! The description of a task cannot be empty");
            }
            else {
                System.out.println("    OOPS!!! The description of a " + task + " cannot be empty");
            }
        }
        storage.writeTaskFile();
    }


    /**
     * Adds a task as a Todo class instance.
     * @param task Description of the todo that needs to be added.
     */
    private void addAsTodo(String task) {
        taskList.add(new Todo(task));
        showAddedTask();
    }

    /**
     * Adds a task as an Event class instance.
     * @param task Description of the event and the event time that need to be added.
     */
    private void addAsEvent(String task) {
        try {
            int index;
            index = task.indexOf("/");
            String eventTime = task.substring(index+1);
            task = task.substring(0,index-1);

            index = eventTime.indexOf(' ');
            eventTime = eventTime.substring(index+1);
            if (eventTime.equals("")) {
                throw new DukeException();
            } else {
                taskList.add(new Event(task,eventTime));
                showAddedTask();
            }
        } catch (DukeException | IndexOutOfBoundsException e) {
            System.out.println("    Please enter a time for your event");
        }

    }

    /**
     * Adds a task as a Deadline class instance
     * @param task Description of the deadline and the deadline date that need to be added.
     */
    private void addAsDeadline(String task) {
        try {
            int index;
            index = task.indexOf("/");
            String by = task.substring(index + 1);
            task = task.substring(0, index - 1);

            index = by.indexOf(' ');
            by = by.substring(index + 1);
            LocalDateTime byDate = extractDeadlineDate(by);

            //if we get a valid date store as a valid date, else store as a string
            if (byDate != null) {
                taskList.add(new Deadline(task, byDate));
                showAddedTask();
            } else if (by.equals("")) {
                throw new DukeException();
            } else {
                taskList.add(new Deadline(task, by));
                showAddedTask();
            }
        } catch (DukeException | IndexOutOfBoundsException e) {
            System.out.println("    Please enter a date for your deadline");
        }
    }

    /**
     * Extracts the deadline date from the string as a datetime object.
     * @param by String that contains the deadline date.
     * @return
     */
    private LocalDateTime extractDeadlineDate(String by) {
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM/yyyy[ HH:mm]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();
            LocalDateTime d1 = LocalDateTime.parse(by,formatter);
            return d1;
        } catch (DateTimeParseException e) {
            return null;
        }

    }

    private void showAddedTask() {
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + taskList.get(taskList.size()-1));
        System.out.println("    Now you have " + (taskList.size()) + " tasks in the list.");
    }

    /**
     * Finds a task from the tasklist specified by a keyword.
     * @param taskKeyword Search keyword used to search for a particular task.
     */
    public void findTasks(String taskKeyword) {
        taskKeyword = taskKeyword.replace("find ","");
        ArrayList<Task> matches = new ArrayList<>();
        for(Task t: taskList) {
            if (t.title.contains(taskKeyword)) {
                matches.add(t);
            }
        }
        if (matches.size() == 0) {
            System.out.println("    There are no tasks that match \""+taskKeyword+"\"");
        }
        else {
            listMatches(matches);
        }
    }

    /**
     * Lists the matches found after a task is searched.
     * @param matches Arraylist that contains the matches found for after a search.
     */
    private void listMatches(ArrayList<Task> matches) {
        System.out.println("    Here are the matching tasks in your list:");
        int i=1;
        for(Task t: matches) {
            System.out.println("    "+(i++ )+": "+t);
        }
    }
}
