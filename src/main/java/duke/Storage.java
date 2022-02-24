package duke;

import duke.tasklist.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import duke.tasklist.*;

/**
 * Storage class is used to load tasks from the file and saving them to taskList<ArrayList>
 * We also have different functions which we will use in our commands to update the text file as we update the taskList.
 */
public class Storage {
    private String filePath;
    private File file;
    private Scanner input;

    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            createFileAndDirectory();
        }
    }

    public String getFilePath() {
        return this.filePath;
    }


    public void createFileAndDirectory() {
        try {
            file.createNewFile();
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Error creating file.");
        }
    }

    public ArrayList<Task> loadTaskList() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists() || !file.isFile()) {
            return taskList;
        }

        while (input.hasNext()){
            String nextString = input.nextLine();
            String[] splitString = nextString.split("\\|");
            String typeTask = splitString[0].replaceAll(" ","");
            String marked = splitString[1].replaceAll(" ","");
            String desc = splitString[2];
            System.out.println(typeTask);
            System.out.println(marked);
            System.out.println(desc);
            String todo = "T";
            String deadline = "D";
            String event = "E";
            String toMark = "1";
            if (typeTask.equals(todo)) {
                taskList.add(new Todo(desc));
                if (marked.equals(toMark)) {
                    taskList.get(taskList.size()-1).markTask();
                }
            }

            else if (typeTask.equals(deadline)) {
                String deadlineDate = splitString[3];
                taskList.add(new Deadline(desc,deadlineDate));
                if (marked.equals(toMark)) {
                    taskList.get(taskList.size()-1).markTask();
                }
            }

            else if (typeTask.equals(event)) {
                String eventDate = splitString[3];
                taskList.add(new Event(desc,eventDate));
                if (marked.equals(toMark)) {
                    taskList.get(taskList.size()-1).markTask();
                }
            }
        }
        return taskList;
    }

    public void appendData(String textToAppend) throws IOException {
        String filePath = this.filePath;
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(System.getProperty( "line.separator" ));
        fw.write(textToAppend);
        fw.close();
    }

    public void writeToFile(String textToAdd) throws IOException {
        filePath = this.filePath;
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public  void updateFile(ArrayList<Task> taskList) {
        for (int i = 0; i<taskList.size(); i++) {
            System.out.println(taskList.get(i).toString());
            String taskListLine = taskList.get(i).toString();
            String marked = "0";
            String textToAdd = "";
            if ( taskListLine.charAt(4)==('X') ) {
                marked = "1";
            }

            String typeTask = Character.toString(taskListLine.charAt(1));
            if (typeTask.equals("T")) {
                textToAdd = typeTask + "|" + marked + "|" + taskListLine.substring(6);
            }

            else {
                int index = taskListLine.indexOf("(");
                int index2 = taskListLine.indexOf(")");
                textToAdd = typeTask + "|" + marked + "|" + taskListLine.substring(6,index) + "|" + taskListLine.substring(index+1,index2);
            }

            if (i==0){
                try {
                    writeToFile(textToAdd);
                } catch (IOException e) {
                    System.out.println("Failed to update first line.");
                }
            }

            else {
                try {
                    appendData(textToAdd);
                } catch (IOException e) {
                    System.out.println("Failed to append line.");
                }
            }

        }
    }
}
