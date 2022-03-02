package jarvis.commands;

import jarvis.Parser;
import jarvis.display.Ui;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TaskList extends Task {
    private static ArrayList<Task> userTaskList = new ArrayList<>();

    private static final String TASK_ICON = "T";
    private static final String EVENT_ICON = "E";
    private static final String DEADLINE_ICON = "D";

    public TaskList(String description) {
        super(description);
    }

    /**
     * Getter function for retrieving size of user's list.
     *
     * @return size of list as an int
     */
    public static int getListSize() {
        return userTaskList.size();
    }

    /**
     * Function to insert a Task into the back of the user's list.
     *
     * @param newTask Task argument to be added to user's list.
     */
    public static void insertTask(Task newTask) {
            userTaskList.add(newTask);
            Ui.taskAdded(newTask.getDescription());
    }

    /**
     * Function to print the user's list of tasks, with the index being printed before the Task's icon, status and
     * description. Includes a check for when user's list is empty.
     */
    public static void printList() {
        if (!userTaskList.isEmpty()) {
            Ui.horizontalLine();
            for (int i = 0; i < userTaskList.size(); i++) {
                String taskIndex = Integer.toString(i+1) + ".";
                System.out.println(taskIndex.toString() + userTaskList.get(i).getFullTask());
            }
            Ui.horizontalLine();
        } else {
            Ui.emptyList();
        }
    }

    /**
     * Getter function for retrieving the status icon of a task
     *
     * @param index Index of element to be checked
     * @return a String to represent Task type
     */
    public static String getStatusIcon(int index){
        return userTaskList.get(index).getStatusIcon();
    }

    /**
     * Getter function for retrieving the description of a task
     *
     * @param index Index of element to be retrieved
     * @return a String to represent the Task's description
     */
    public static String getTask(int index) {
        return userTaskList.get(index).getDescription();
    }

    /**
     * Driver function to mark a task. Function is reused for when loading user's saved data so a flag is
     * included to prevent printing default marked task message when loading user's data.
     *
     * @param index Index of the task to be marked
     * @param toPrintMessage a flag to indicate if default messages should be called
     */
    public static void markTask(int index, boolean toPrintMessage) {
        Task targetTask = userTaskList.get(index);
        Ui.horizontalLine();
        boolean isSuccessfullyMarked = targetTask.markAsDone();
        if (isSuccessfullyMarked && toPrintMessage) {
            Ui.printTaskMarked(targetTask);
            Ui.horizontalLine();
        }
    }

    /**
     * Driver function to unmark a task. Includes call to print default message when task is successfully processed,
     * regardless if task is unmarked, or already unmarked.
     *
     * @param taskIndex Index of the task to be unmarked
     */
    public static void unmarkTask(int taskIndex) {
        Task targetTask = userTaskList.get(taskIndex);
        Ui.horizontalLine();
        boolean isSuccessfullyUnmarked = targetTask.markAsUndone();
        if (isSuccessfullyUnmarked) {
            Ui.printTaskUnmarked(targetTask);
        }
    }


    /**
     * Function to remove a Task from the user's list of tasks.
     *
     * @param taskIndex Index of task to be removed from list
     */
    public static void removeTask(int taskIndex, boolean toPrintMessage) {
        Task taskRemoved = userTaskList.get(taskIndex);
        userTaskList.remove(taskIndex);
        if (toPrintMessage) {
            Ui.taskDeleted(taskRemoved, userTaskList.size());
        }
    }

    /**
     * Function for when Jarvis is booting up to process saved file's data. Takes in 1 line of saved data at a
     * time through the data argument. Includes parsing of data and calling functions to add tasks to list. This assumes
     * that the saved data file is perfectly formatted and uncorrupted/untempered.
     *
     * @param data Next line of data in the user's stored data file
     */
    protected static void parseSavedData(String data) {
        String[] dataArray = data.split(" ");
        String taskType = dataArray[0];
        int separatorIndex = Parser.indexOf(dataArray, "|");
        String description = "";
        String day = "";
        String time = "";
        boolean taskIsDone = dataArray[1].equals("YES");
        if (separatorIndex == -1) {
            description = Parser.parseUserInput(dataArray, 2, dataArray.length);
        } else {
            //saved Task is either an event or deadline
            description = Parser.parseUserInput(dataArray, 2, separatorIndex);
            day = dataArray[separatorIndex+1];
            time = dataArray[separatorIndex+2];
        }

        switch (taskType) {
        case TASK_ICON:
            userTaskList.add(new Task(description));
            break;
        case DEADLINE_ICON:
            userTaskList.add(new Deadline(description, day, time));
            break;
        case EVENT_ICON:
            userTaskList.add(new Event(description, day, time));
            break;
        default:
            break;
        }
        if (taskIsDone) {
            markTask(userTaskList.size()-1, false);
        }
    }

    /**
     * Driver function to call when Jarvis is booting up to load any saved user's data file from previous
     * session of usage. File checking is done before this command is called by the main Jarvis function so this
     * function assumes that the file pointed to by the argument exists.
     *
     * @param savedFile File argument that points to the user's saved data file
     */
    public static void loadFile(File savedFile) {
        try {
            Scanner fileReader = new Scanner(savedFile);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                parseSavedData(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            Ui.loadError();
        }
    }

    /**
     * Function that is called when Jarvis is exiting to extract all current tasks in User's list. Uses a StringBuffer
     * to store the extracted data up till then before converting to a String to return
     *
     * @return Formatted data of user's current list to be stored on the user's list.
     */
    protected static String getData() {
        StringBuffer data = new StringBuffer();
        for (Task t : userTaskList) {
            String taskData = t.exportData() + "\n";
            data.append(taskData);
        }
        return data.toString();
    }

    /**
     * Driver function for when Jarvis exits and calls other functions to extract current user's list of Tasks and data.
     * Creates new saved data file if it does not exist yet.
     */
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
            Ui.saveError();
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
