import java.util.ArrayList;

public class TasksManager {
    private ArrayList<Task> list;
    private int numberOfTasks;

    public TasksManager() {
        this.list = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void incrementNumberOfTasks() {
        this.numberOfTasks += 1;
    }

    public boolean addTask(String text) {
        //create new task object
        boolean isSuccessful = false;

        if (text.toLowerCase().startsWith(DukeUI.todo_command)) {
            // Extract the to-do task description
            String todoDescription = text.split(" ", 2)[1];

            isSuccessful = list.add(new Todo(todoDescription));
        } else if (text.toLowerCase().startsWith(DukeUI.event_command)) {
            // Extract the event task description and date
            String eventDescriptionAndDate = text.split(" ", 2)[1];
            String eventDescription = eventDescriptionAndDate.split(DukeUI.event_option_command)[0].trim();
            String eventDate = eventDescriptionAndDate.split(DukeUI.event_option_command)[1].trim();

            isSuccessful = list.add(new Event(eventDescription, eventDate));
        } else if (text.toLowerCase().startsWith(DukeUI.deadline_command)) {
            // Extract the deadline task description and date
            String deadlineDescriptionAndDate = text.split(" ", 2)[1];
            String deadlineDescription = deadlineDescriptionAndDate.split(DukeUI.deadline_option_command)[0].trim();
            String deadlineDate = deadlineDescriptionAndDate.split(DukeUI.deadline_option_command)[1].trim();

            isSuccessful = list.add(new Deadline(deadlineDescription, deadlineDate));
        }

        if (isSuccessful) {
            incrementNumberOfTasks();
            return true;
        }

        return false;
    }

    public boolean updateDoneStatus(int taskNum, boolean status) {
        // Marks a task as either done or not done
        if (isEmpty()) {
            return false;
        }

        // Iterate through the task list to find the task
        for (int i = 0; i < getList().size(); i += 1) {
            if (i == taskNum - 1) {
                // Task found, mark as desired status
                getList().get(i).setDone(status);
                return true;
            }
        }

        // Failed to find task
        return false;
    }

    public void displayTask(int taskNum) {
        if (isEmpty()) {
            return;
        }

        int taskIndex = taskNum - 1;
        Task taskToDisplay = getList().get(taskIndex);

        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayAllTasks() {
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < getList().size(); i += 1) {
            System.out.println("\t" + (i + 1) + "." + getList().get(i).toString());
        }
    }
}