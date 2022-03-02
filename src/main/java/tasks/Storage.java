package tasks;

import exceptions.*;
import time.Time;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Task> Tasks = new ArrayList<>();
    protected static final String FILE_NAME = "data/duke.txt";
    protected static final String DONE = "X";
    protected static final String TODO = "T";
    protected static final String EVENT = "E";
    protected static final String DEADLINE = "d";
    Time timeChecker;

    public int getSize() {
        return Tasks.size();
    }

    /**
     * Adds a new task to the array list
     * @param newTask a task object
     */
    public void add(Task newTask) {
        Tasks.add(newTask);
    }
    public Task get(int indexOfTask) {
        return Tasks.get(indexOfTask);
    }

    /**
     * Deletes a task from the list
     * @param indexOfTask the index of the task that needs to be deleted
     */
    public void remove(int indexOfTask) {
        Tasks.remove(indexOfTask);
    }

    /**
     * Writes and saves the tasks to an external file
     * @throws DukeExceptions if the action fails
     */
    public void saveTask() throws DukeExceptions {
        StringBuilder content = new StringBuilder();
        for (Task task : Tasks) {
            content.append(task.getListName());
            content.append("\n");
        }
            try{
                File file = new File(FILE_NAME);
                FileWriter writeStream = new FileWriter(file);
                writeStream.write(content.toString());
                writeStream.close();
            } catch (Exception e){
                throw new IllegalSavingAction();
            }

    }

    /**
     * Parses the input from loading task and rejects redundant characters for further loading action
     * @param listName the raw input of task from the external file
     * @return the array of key information of task
     */
    public String[] parseInput(String listName) {
        String info = listName.replaceAll("]", "\\|");
        info = info.replaceAll("\\[", "");
        info = info.replaceAll("\\(", "\\|");
        info = info.replaceAll("\\)", "");
        String[] parsedInput = info.split("\\|");
        return parsedInput;
    }

    /**
     * Checks the status of the task and mark when loading
     * @param task The task that is going to be added to the list
     * @param status the status of task from the external file
     */
    public void mark(Task task, String status){
        if(status.equals(DONE)){
            task.isDone = true;
        }
    }

    /**
     * Loads tasks from external file
     * @throws DukeExceptions if the action fails
     */
    public void loadTask() throws DukeExceptions{
        //Reads input from external file line by line (task by task)
        try (BufferedReader loadData = new BufferedReader(new FileReader(FILE_NAME))){
            String listName;
            while ((listName = loadData.readLine()) != null) {
                //parses input into key information array
                String[] keyInfo = this.parseInput(listName);
                //imports task according to the information
                importTask(keyInfo);
            }
            } catch (Exception e){
            throw new IllegalReadingAction();
        }

    }

    /**
     * Imports tasks according to the key information array
     * @param keyInfo the key information array from parsing input from external file
     * @throws DukeExceptions if the action fails
     */
    private void importTask(String[] keyInfo) throws DukeExceptions {
        switch (keyInfo[0]) {
        case TODO:
            importTodoTask(keyInfo);
            break;
        case EVENT:
            importEvent(keyInfo);
            break;
        case DEADLINE:
            importDeadline(keyInfo);
            break;
        default:
            throw new IllegalReadingAction();
        }
    }

    /**
     * Imports deadline task according to the key information array
     * @param keyInfo the key information array from parsing input from external file
     * @throws DukeExceptions if time converting fails
     */
    private void importDeadline(String[] keyInfo) throws DukeExceptions {
        String oldTimeString;
        String newTimeString;
        oldTimeString = keyInfo[3].replace("by: ", "");
        timeChecker = new Time(oldTimeString);
        timeChecker.check();
        newTimeString = timeChecker.getDateString();
        Deadline newDeadline = new Deadline(keyInfo[2], newTimeString);
        mark(newDeadline, keyInfo[2]);
        Tasks.add(newDeadline);
    }

    /**
     * Imports event task according to the key information array
     * @param keyInfo the key information array from parsing input from external file
     * @throws DukeExceptions if time converting fails
     */
    private void importEvent(String[] keyInfo) throws DukeExceptions {
        String newTimeString;
        String oldTimeString;
        oldTimeString = keyInfo[3].replace("at: ", "");
        timeChecker = new Time(oldTimeString);
        timeChecker.check();
        newTimeString = timeChecker.getDateString();
        Event newEvent = new Event(keyInfo[2], newTimeString);
        mark(newEvent, keyInfo[2]);
        Tasks.add(newEvent);
    }

    /**
     * Imports todo task according to the key information array
     * @param keyInfo the key information array from parsing input from external file
     */
    private void importTodoTask(String[] keyInfo) {
        ToDo newToDo = new ToDo(keyInfo[2]);
        mark(newToDo, keyInfo[2]);
        Tasks.add(newToDo);
    }

    /**
     * Creates a external file for saving and loading task
     * @throws DukeExceptions if the action fails
     */
    public void createFile() throws DukeExceptions {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
        } catch (Exception e){
            throw new CreatingFileException();
        }
    }

}


