import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();

    public ArrayList<Task> getList() {
        return this.list;
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public boolean addTask(String text) {
        // Create new Task object with text
        Task newTask = new Task(text);

        return list.add(newTask);
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

    private String getCheckBox(boolean isComplete) {
        if (isComplete) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public void displayTask(int taskNum) {
        if (isEmpty()) {
            return;
        }

        // Iterate through the task list to find the task
        for (int i = 0; i < getList().size(); i += 1) {
            if (i == taskNum - 1) {
                // Task found
                System.out.println("\t" + getCheckBox(getList().get(i).isDone()) + " "
                        + getList().get(i).getDescription());
            }
        }
    }

    public void displayList() {
        if (isEmpty()) {
            System.out.println("Hmm, my list is empty at the moment...");
            return;
        }

        for (int i = 0; i < getList().size(); i += 1) {
            System.out.println("\t" + (i + 1) + "."
                    + getCheckBox(getList().get(i).isDone()) + " "
                    + getList().get(i).getDescription());
        }
    }


}
