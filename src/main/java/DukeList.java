import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list = new ArrayList<Task>();

    // Accessors
    public ArrayList<Task> getList() {
        return this.list;
    }

    public boolean isEmpty() {
        if (list.size() == 0) {
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
            System.out.println("The list seems to be empty, please add some task");
            return;
        }

        for (int i = 0; i < getList().size(); i += 1) {
            System.out.println("\t" + (i + 1) + "."
                    + getCheckBox(getList().get(i).isDone()) + " "
                    + getList().get(i).getDescription());
        }
    }

    public boolean addTask(String text) {
        Task newTask = new Task(text);
        boolean isSuccess = list.add(newTask);

        if (isSuccess) {
            return true;
        }

        return false;
    }

}
