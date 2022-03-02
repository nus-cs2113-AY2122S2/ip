package jarvis.commands;

import jarvis.Formatter;
import jarvis.display.DisplayMessages;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class UserList extends Task {
    private static ArrayList<Task> userTaskList = new ArrayList<>();

    private static final String TASK_ICON = "T";
    private static final String EVENT_ICON = "E";
    private static final String DEADLINE_ICON = "D";

    public UserList(String description) {
        super(description);
    }

    public static int getListSize() {
        return userTaskList.size();
    }

    public static void insertTask(Task newTask) {
        userTaskList.add(newTask);
        DisplayMessages.taskAdded(newTask.getDescription());
    }

    public static void printList() {
        if (!userTaskList.isEmpty()) {
            DisplayMessages.horizontalLine();
            for (int i = 0; i < userTaskList.size(); i++) {
                String taskIndex = Integer.toString(i+1) + ".";
                System.out.println(taskIndex.toString() + userTaskList.get(i).getFullTask());
            }
            DisplayMessages.horizontalLine();
        } else {
            DisplayMessages.emptyList();
        }
    }

    public static String getStatusIcon(int index){
        return userTaskList.get(index).getStatusIcon();
    }

    public static String getTask(int index) {
        return userTaskList.get(index).getDescription();
    }

    public static void markTask(int index, boolean toPrintMessage) {
        Task targetTask = userTaskList.get(index);
        DisplayMessages.horizontalLine();
        boolean isSuccessfullyMarked = targetTask.markAsDone();
        if (isSuccessfullyMarked && toPrintMessage) {
            DisplayMessages.printTaskMarked(targetTask);
            DisplayMessages.horizontalLine();
        }
    }

    public static void unmarkTask(int taskIndex) {
        Task targetTask = userTaskList.get(taskIndex);
        DisplayMessages.horizontalLine();
        boolean isSuccessfullyUnmarked = targetTask.markAsUndone();
        if (isSuccessfullyUnmarked) {
            DisplayMessages.printTaskUnmarked(targetTask);
        }
    }

    public static void removeTask(int taskIndex) {
        Task taskRemoved = userTaskList.get(taskIndex);
        userTaskList.remove(taskIndex);
        DisplayMessages.taskDeleted(taskRemoved, userTaskList.size());
    }

    protected static void parseSavedData(String data) {
        String[] dataArray = data.split(" ");
        String taskType = dataArray[0];
        int separatorIndex = Formatter.indexOf(dataArray, "|");
        String description = "";
        String date = "";
        boolean taskIsDone = dataArray[1].equals("YES") ? true : false;
        if (separatorIndex == -1) {
            description = Formatter.parseUserInput(dataArray, 2, dataArray.length);
        } else {
            //saved Task is either an event or deadline
            description = Formatter.parseUserInput(dataArray, 2, separatorIndex);
            date = Formatter.parseUserInput(dataArray, separatorIndex+1, dataArray.length);
        }

        switch (taskType) {
        case TASK_ICON:
            userTaskList.add(new Task(description));
            break;
        case DEADLINE_ICON:
            userTaskList.add(new Deadline(description, date));
            break;
        case EVENT_ICON:
            userTaskList.add(new Event(description, date));
            break;
        default:
            break;
        }
        if (taskIsDone) {
            markTask(userTaskList.size()-1, false);
        }
    }

    public static void loadFile(File savedFile) {
        try {
            Scanner fileReader = new Scanner(savedFile);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                parseSavedData(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            DisplayMessages.loadError();
        }
    }

    protected static String getData() {
        StringBuffer data = new StringBuffer();
        for (Task t : userTaskList) {
            String taskData = t.exportData() + "\n";
            data.append(taskData);
        }
        return data.toString();
    }

    public static void saveData() {
        File filename = new File("data/Jarvis.txt");
        try {
            filename.getParentFile().mkdirs();
            filename.createNewFile();
            FileWriter fileUpdater = new FileWriter(filename);
            String data = getData();
            fileUpdater.write(data);
            fileUpdater.close();
        } catch (IOException e) {
            DisplayMessages.saveError();
        }
    }

    public static ArrayList<Task> getSearchResult(String keyword) {
        ArrayList<Task> resultList = new ArrayList<>();
        for (Task t : userTaskList) {
            String taskDescription = t.getDescription();
            if (taskDescription.contains(keyword)) {
                resultList.add(t);
            }
        }
        return resultList;
    }
}
