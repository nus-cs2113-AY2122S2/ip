package controller;

import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Storage {
    private static final String FILE_SEPARATOR = " | ";
    private String filePath;

    public Storage(String path) {
        filePath = path;
    }


    public void save(ArrayList<Task> al) {
        StringBuilder sb = new StringBuilder();
        List newList = new ArrayList();
        for (int i = 0; i < al.size(); i++) {
            Task task = al.get(i);
            if (task == null) break;
            String taskType = task.getTypeIcon();
            String taskStatus = task.getStatusIcon();
            String taskDetails = task.getDescription();
            sb.append(taskType);
            sb.append(FILE_SEPARATOR);
            sb.append(taskStatus);
            sb.append(FILE_SEPARATOR);
            sb.append(taskDetails);
            if (taskType.equals("D") || taskType.equals("E")) {
                sb.append(FILE_SEPARATOR);
                sb.append(task.getTime());
            }
            sb.append(System.lineSeparator());
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong:" + e.getMessage());
        }
    }

    private void writeToFile(String data) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }

    public String load() throws java.io.FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            sb.append(s.nextLine());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}



