package jarvis.commands;

import jarvis.display.DisplayMessages;

public class UserList extends Task {
    private static Task[] userList= new Task[100]; //list of Tasks
    private static int listSize = 0; //keeps track of number of items in list

    public UserList(String description) {
        super(description);
    }

    public static int getListSize() {
        return listSize;
    }

    public static void insertTask(Task newTask) {
        userList[listSize] = newTask;
        DisplayMessages.taskAdded(newTask.getDescription());
        listSize++;
    }

    public static void printList() { //consider converting this function to Jarvis.DisplayMessages
        boolean isListEmpty = listSize == 0;
        if (!isListEmpty) {
            DisplayMessages.horizontalLine();
            for (int i = 0; i < listSize; i++) {
                String taskIndex = Integer.toString(i+1) + ".";
                System.out.print(taskIndex);
                userList[i].printItem();
            }
            DisplayMessages.horizontalLine();
        } else {
            DisplayMessages.emptyList();
        }
    }

    public static String getStatusIcon(int index){
        return userList[index].getStatusIcon();
    }

    public static String getTask(int index) {
        return userList[index].getDescription();
    }

    public static void markTask(int index) {
        Task targetTask = userList[index];
        DisplayMessages.horizontalLine();
        boolean isSuccessfullyMarked = targetTask.markAsDone();
        if (isSuccessfullyMarked) {
            DisplayMessages.printTaskMarked(targetTask);
        }
        DisplayMessages.horizontalLine();
    }

    public static void unmarkTask(int taskIndex) {
        Task targetTask = userList[taskIndex];
        DisplayMessages.horizontalLine();
        boolean isSuccessfullyUnmarked = targetTask.markAsUndone();
        if (isSuccessfullyUnmarked) {
            DisplayMessages.printTaskUnmarked(targetTask);
        }
        DisplayMessages.horizontalLine();
    }
}
