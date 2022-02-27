package tasks;

import exceptions.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class Storage {
    private static final ArrayList<Task> Tasks = new ArrayList<Task>();
    protected static final String FILE_NAME = "data/duke.txt";
    protected static final String DONE = "X";
    protected static final String TODO = "T";
    protected static final String EVENT = "E";
    protected static final String DEADLINE = "d";

    public int size() {
        return Tasks.size();
    }
    public void add(Task newTask) {
        Tasks.add(newTask);
    }
    public Task get(int i) {
        return Tasks.get(i);
    }
    public void remove(int i) {
        Tasks.remove(i);
    }
    public void saveTask() throws DukeExceptions {
        String content = "";
        for(int i = 0;i < Tasks.size();i++){
            content += Tasks.get(i).getListName();
            content += "\n";
        }
        if(content != null){
            try{
                File file = new File(FILE_NAME);
                if(!file.exists()){
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileWriter writeStream = new FileWriter(file);
                writeStream.write(content);
                writeStream.close();
            } catch (Exception e){
                throw new IllegalSavingAction();
            }
        }

    }

    public String[] parseInput(String listName) {
        String info = listName.replaceAll("\\]", "\\|");
        info = info.replaceAll("\\[", "");
        info = info.replaceAll("\\(", "\\|");
        info = info.replaceAll("\\)", "");
        //System.out.println(info);
        String[] data = info.split("\\|");
        return data;
    }

    public void mark(Task task, String status){
        if(status.equals(DONE)){
            task.isDone = true;
        }
    }

    public void loadTask() throws DukeExceptions{
        try (BufferedReader loadData = new BufferedReader(new FileReader(FILE_NAME))){
            String listName;
            LocalDate time;
            while ((listName = loadData.readLine()) != null) {
                String[] data = this.parseInput(listName);
                //System.out.println(listName);
                //System.out.println(Arrays.toString(data));
                    switch (data[0]) {
                    case TODO:
                        ToDo newToDo = new ToDo(data[2]);
                        mark(newToDo, data[2]);
                        Tasks.add(newToDo);
                        break;
                    case EVENT:
                        time = LocalDate.parse(data[3].replace("at: ", ""));
                        Event newEvent = new Event(data[2], time);
                        mark(newEvent, data[2]);
                        Tasks.add(newEvent);
                        break;
                    case DEADLINE:
                        time = LocalDate.parse(data[3].replace("by ", ""));
                        Deadline newDeadline = new Deadline(data[2], time);
                        mark(newDeadline, data[2]);
                        Tasks.add(newDeadline);
                        break;
                    default:
                        throw new IllegalReadingAction();
                    }
                }
            } catch (Exception e){
            throw new IllegalReadingAction();
        }

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


