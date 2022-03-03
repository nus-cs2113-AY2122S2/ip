package duke;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();
    Storage storage;
    void TaskList() {
        storage = new Storage();
    }
    public void listTasks() {
        System.out.println("    Here are the tasks in your list:");
        int i=1;
        for (Task t: taskList) {
            System.out.println("    "+(i++ )+": "+t);
        }
    }

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

    public void deleteTask(String task) {
        try {
            storage = new Storage();
            task = task.replace("delete ","");
            int i = Integer.parseInt(task)-1;
            if (taskList.get(i) != null) {
                System.out.println("deleting task");
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

    private void addAsTodo(String task) {
        taskList.add(new Todo(task));
        showAddedTask();
    }

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
        } catch (DukeException e) {
            System.out.println("    Please enter a time for your event");
        }

    }

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

    private void listMatches(ArrayList<Task> matches) {
        System.out.println("    Here are the matching tasks in your list:");
        int i=1;
        for(Task t: matches) {
            System.out.println("    "+(i++ )+": "+t);
        }
    }
}
