package storage;

import data.Deadline;
import data.Event;
import data.Task;
import data.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String DIR_PATH = "data";
    private static final String fileName = "duke.txt";

    public FileManager(){

    }

    public ArrayList<Task> loadData() throws IOException {
        File dir = new File(DIR_PATH);
        if(!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(DIR_PATH + File.separator + fileName);
        if(!file.exists()) {
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        ArrayList<String> records = new ArrayList<>();
        while(s.hasNextLine()) {
            records.add(s.nextLine());
        }

        if(!records.isEmpty() && records.get(records.size()-1).equals(System.lineSeparator())){
            records.remove(records.size()-1);
        }
        return decodeData(records);
    }

    public void saveData(ArrayList<Task> tasks) throws IOException{
        FileWriter fw = new FileWriter(DIR_PATH + File.separator + fileName);
        ArrayList<String> records = encodeData(tasks);
        for(int i = 0; i < records.size(); i++){
            fw.write(records.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Task> decodeData(ArrayList<String> records) {
        ArrayList<Task> tasks = new ArrayList<>();
        for(String record: records) {
            String[] data = record.split("/");
            for (int i = 0; i < data.length; i++){
                data[i] = data[i].trim();
            }

            switch (data[0]) {
            case "T":
                tasks.add(new Todo(data[2], Boolean.parseBoolean(data[1])));
                break;
            case "D":
                tasks.add(new Deadline(data[2], data[3], Boolean.parseBoolean(data[1])));
                break;
            case "E":
                tasks.add(new Event(data[2], data[3], Boolean.parseBoolean(data[1])));
                break;
            default:
                break;
            }
        }

        return tasks;
    }

    public ArrayList<String> encodeData(ArrayList<Task> tasks) {
        ArrayList<String> records = new ArrayList<>();
        for (Task task : tasks) {
            records.add(task.getInfo());
        }

        return records;
    }
}
