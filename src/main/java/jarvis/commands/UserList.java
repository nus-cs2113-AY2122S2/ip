package jarvis.commands;

import jarvis.display.DisplayMessages;
import java.util.ArrayList;

public class UserList extends Task {
    private static ArrayList<Task> userList= new ArrayList<>(); //list of Tasks

    public UserList(String description) {
        super(description);
    }

    public static int getListSize() {
        return userList.size();
    }

    public static void insertTask(Task newTask) {
        userList.add(newTask);
        DisplayMessages.taskAdded(newTask.getDescription());
    }

    public static void printList() { //consider converting this function to Jarvis.DisplayMessages
        if (!userList.isEmpty()) {
            DisplayMessages.horizontalLine();
            for (int i = 0; i < userList.size(); i++) {
                String taskIndex = Integer.toString(i+1) + ".";
                System.out.println(taskIndex.toString() + userList.get(i).getFullTask());
            }
            DisplayMessages.horizontalLine();
        } else {
            DisplayMessages.emptyList();
        }
    }

    public static String getTask(int index) {
        return userList.get(index).getDescription();
    }

    public static void markTask(int index) {
        Task targetTask = userList.get(index);
        boolean isSuccessfullyMarked = targetTask.markAsDone();
        if (isSuccessfullyMarked) {
            DisplayMessages.printTaskMarked(targetTask);
        }
    }

    public static void unmarkTask(int taskIndex) {
        Task targetTask = userList.get(taskIndex);
        boolean isSuccessfullyUnmarked = targetTask.markAsUndone();
        if (isSuccessfullyUnmarked) {
            DisplayMessages.printTaskUnmarked(targetTask);
        }
    }

    public static void removeTask(int taskIndex) {
        Task taskRemoved = userList.get(taskIndex);
        userList.remove(taskIndex);
        DisplayMessages.taskDeleted(taskRemoved, userList.size());
    }
}
