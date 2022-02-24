package storage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String DIR_PATH = "data";
    private static final String fileName = "duke.txt";

    public FileManager(){

    }

    public ArrayList<String> loadData() throws IOException {
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
        return records;
    }

    public void saveData(ArrayList<String> records) throws IOException{
        FileWriter fw = new FileWriter(DIR_PATH + File.separator + fileName);
        for(int i = 0; i < records.size(); i++){
            fw.write(records.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
