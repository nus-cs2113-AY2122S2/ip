import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DEADLINE_SEPARATOR = "/by";
    public static final String EVENT_SEPARATOR = "/at";


    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int tasksCount = 0;

    public static void addTask(String taskType, String userInput) {

        String description;
        String dueDate;

        try {
            if (taskType.equalsIgnoreCase(TODO_COMMAND)) {
                description = Parser.parseDescription(null, userInput, taskType);
                if (description.isEmpty()) {
                    throw new DukeException(Ui.EMPTY_DESCRIPTION_MESSAGE);
                }
                tasks.add(new ToDo(description));
                Ui.showAddTaskMessage();
                tasksCount++;
            }
            else if (taskType.equalsIgnoreCase(DEADLINE_COMMAND)) {
                description = Parser.parseDescription(DEADLINE_SEPARATOR, userInput, taskType);
                dueDate = Parser.parseDate(DEADLINE_SEPARATOR, userInput);
                if (description.isEmpty()) {
                    throw new DukeException(Ui.EMPTY_DESCRIPTION_MESSAGE);
                }
                if (dueDate.isEmpty()) {
                    throw new DukeException(Ui.EMPTY_DATE_MESSAGE);
                }
                tasks.add(new Deadline(description,dueDate));
                Ui.showAddTaskMessage();
                tasksCount++;
            }
            else if (taskType.equalsIgnoreCase(EVENT_COMMAND)) {
                description = Parser.parseDescription(EVENT_SEPARATOR, userInput, taskType);
                dueDate = Parser.parseDate(EVENT_SEPARATOR, userInput);
                if (description.isEmpty()) {
                    throw new DukeException(Ui.EMPTY_DESCRIPTION_MESSAGE);
                }
                if (dueDate.isEmpty()) {
                    throw new DukeException(Ui.EMPTY_DATE_MESSAGE);
                }
                tasks.add(new Event(description, dueDate));
                Ui.showAddTaskMessage();
                tasksCount++;
            }
            else {
                throw new DukeException(Ui.ILLEGAL_COMMAND_MESSAGE);
            }
        } catch (DukeException e) {
            System.out.println(Ui.DIVIDER);
            System.out.println(e.getMessage());
            System.out.println(Ui.DIVIDER);
        }
    }

    public static void markAsDone(int taskIndex) {
        System.out.println(Ui.DIVIDER);
        try {
            if (tasks.get(taskIndex).getStatus() == false) {
                tasks.get(taskIndex).markAsDone();
                System.out.println(Ui.Mark_Task_MESSAGE);
                System.out.println("   " + tasks.get(taskIndex));
            }
            else {
                System.out.println(Ui.Mark_Failed_MESSAGE);
                System.out.println("   " + tasks.get(taskIndex));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.ILLEGAL_INDEX_MESSAGE);
        }
        System.out.println(Ui.DIVIDER);
    }

    public static void markAsNotDone(int taskIndex) {
        System.out.println(Ui.DIVIDER);
        try {
            if (tasks.get(taskIndex).getStatus() == true) {
                tasks.get(taskIndex).markAsNotDone();
                System.out.println(Ui.Unmark_Task_MESSAGE);
                System.out.println("   " + tasks.get(taskIndex));
            }
            else {
                System.out.println(Ui.Unmark_Failed_MESSAGE);
                System.out.println("   " + tasks.get(taskIndex));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.ILLEGAL_INDEX_MESSAGE);
        }

        System.out.println(Ui.DIVIDER);
    }

    public static void listTasks() {
        int taskIndex = 0;
        System.out.println(Ui.DIVIDER);
        System.out.println(Ui.LIST_TASK_MESSAGE);
        for (Task task : tasks) {
            taskIndex++;
            System.out.println(" " + (taskIndex) + ". " + task);
        }
        System.out.println(Ui.DIVIDER);
    }

    public static void deleteTask(int taskIndex) {
        System.out.println(Ui.DIVIDER);
        try {
            Task deletedTask = tasks.get(taskIndex);
            System.out.println(Ui.DELETE_TASK_MESSAGE);
            System.out.println("   " + deletedTask);
            tasks.remove(taskIndex);
            tasksCount--;
            System.out.println(" Now you have " + tasks.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.ILLEGAL_INDEX_MESSAGE);
        }
        System.out.println(Ui.DIVIDER);
    }

    public static void findTask(String keyword) {
        int taskIndex = 0;
        boolean flag = false;
        System.out.println(Ui.DIVIDER);
        System.out.println(" Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                taskIndex++;
                System.out.println(" " + (taskIndex) + "[" + (tasks.indexOf(task) + 1) + "]" + ". " + task);
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println(" Sorry! We couldn't find a match for " + '"' + keyword + '"' + ". Please try another keyword.");
        }
        System.out.println(Ui.DIVIDER);
    }
}
