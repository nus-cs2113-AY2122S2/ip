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
    public void add(Task newTask) {
        Tasks.add(newTask);
    }
    public Task get(int ndexOfTask) {
        return Tasks.get(ndexOfTask);
    }
    public void remove(int indexOfTask) {
        Tasks.remove(indexOfTask);
    }
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

    public String[] parseInput(String listName) {
        String info = listName.replaceAll("]", "\\|");
        info = info.replaceAll("\\[", "");
        info = info.replaceAll("\\(", "\\|");
        info = info.replaceAll("\\)", "");
        String[] parsedInput = info.split("\\|");
        return parsedInput;
    }

    public void mark(Task task, String status){
        if(status.equals(DONE)){
            task.isDone = true;
        }
    }

    public void loadTask() throws DukeExceptions{
        try (BufferedReader loadData = new BufferedReader(new FileReader(FILE_NAME))){
            String listName;
            while ((listName = loadData.readLine()) != null) {
                String[] data = this.parseInput(listName);
                    switch (data[0]) {
                    case TODO:
                        importTodoTask(data);
                        break;
                    case EVENT:
                        importEvent(data);
                        break;
                    case DEADLINE:
                        importDeadline(data);
                        break;
                    default:
                        throw new IllegalReadingAction();
                    }
                }
            } catch (Exception e){
            throw new IllegalReadingAction();
        }

    }

    private void importDeadline(String[] parsedInput) throws DukeExceptions {
        String oldTimeString;
        String newTimeString;
        oldTimeString = parsedInput[3].replace("by: ", "");
        timeChecker = new Time(oldTimeString);
        timeChecker.check();
        newTimeString = timeChecker.getDateString();
        Deadline newDeadline = new Deadline(parsedInput[2], newTimeString);
        mark(newDeadline, parsedInput[2]);
        Tasks.add(newDeadline);
    }

    private void importEvent(String[] parsedInput) throws DukeExceptions {
        String newTimeString;
        String oldTimeString;
        oldTimeString = parsedInput[3].replace("at: ", "");
        timeChecker = new Time(oldTimeString);
        timeChecker.check();
        newTimeString = timeChecker.getDateString();
        Event newEvent = new Event(parsedInput[2], newTimeString);
        mark(newEvent, parsedInput[2]);
        Tasks.add(newEvent);
    }

    private void importTodoTask(String[] data) {
        ToDo newToDo = new ToDo(data[2]);
        mark(newToDo, data[2]);
        Tasks.add(newToDo);
    }

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


