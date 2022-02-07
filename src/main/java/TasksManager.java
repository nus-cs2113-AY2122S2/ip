import java.util.ArrayList;

public class TasksManager {
    private static final int EVENT_DEADLINE_TASK_NUMBER_OF_PARAMETERS = 2;

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
        // Create new Task object with text
        boolean isTaskAdded;
        String taskType = extractTaskType(text);

        switch (taskType) {
        case SoraUI.ADD_TODO_COMMAND_KEYWORD:
            String todoDescription = removeCommandKeyword(text);
            isTaskAdded = list.add(new Todo(todoDescription));
            break;
        case SoraUI.ADD_EVENT_COMMAND_KEYWORD:
            String[] eventDescriptionAndDate = extractDescriptionAndDate(text, SoraUI.ADD_EVENT_OPTION_KEYWORD);
            isTaskAdded = list.add(new Event(eventDescriptionAndDate));
            break;
        case SoraUI.ADD_DEADLINE_COMMAND_KEYWORD:
            String[] deadlineDescriptionAndDate = extractDescriptionAndDate(text, SoraUI.ADD_DEADLINE_OPTION_KEYWORD);
            isTaskAdded = list.add(new Deadline(deadlineDescriptionAndDate));
            break;
        default:
            isTaskAdded = false;
        }

        if (isTaskAdded) {
            incrementNumberOfTasks();
            return true;
        }

        return false;
    }

    private String extractTaskType(String text) {
        String taskType = text.toLowerCase().split(" ", 2)[0];
        return taskType;
    }

    private String removeCommandKeyword(String text) {
        String description = text.split(" ", 2)[1];
        return description;
    }

    private String[] extractDescriptionAndDate(String text, String optionKeyword) {
        String taskDescriptionAndDateString = removeCommandKeyword(text);
        String[] taskDescriptionAndDate = new String[EVENT_DEADLINE_TASK_NUMBER_OF_PARAMETERS];

        for (int i = 0; i < taskDescriptionAndDate.length; i += 1) {
            taskDescriptionAndDate[i] = taskDescriptionAndDateString.split(optionKeyword)[i].trim();
        }

        return taskDescriptionAndDate;
    }

    public boolean updateDoneStatus(int taskNum, boolean status) {
        // Marks a task as either done or not done
        if (isEmpty()) {
            return false;
        }

        // Calculate index number of task in the ArrayList
        int indexNum = taskNum - 1;

        // Update the task status
        getList().get(indexNum).setDone(status);
        return true;
    }

    public void displayTask(int taskNum) {
        if (isEmpty()) {
            return;
        }

        int taskIndex = taskNum - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayLastAddedTask() {
        if (isEmpty()) {
            return;
        }

        int taskIndex = getNumberOfTasks() - 1;
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
