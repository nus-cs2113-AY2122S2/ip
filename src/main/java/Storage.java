import DukeTask.Deadline;
import DukeTask.Event;
import DukeTask.Task;
import DukeTask.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    /**
     * Save current data into the hard disk (relative path: src\\DataSrc\\taskList.txt)
     * @throws IOException if the file/ file path does not exist.
     */
    public void saveData() throws IOException {
        FileWriter writer = new FileWriter(".\\src\\DataSrc\\taskList.txt");
        for(Task task: TaskManager.tasks){
            boolean taskDone = task.getStatusIcon().equalsIgnoreCase("X");
            String taskType = task.toString().substring(1,2);
            String taskInfo = taskType+","+taskDone+","+task.getDescription();
            switch(taskType){
            case "D":
                Deadline deadline = (Deadline) task;
                taskInfo = taskInfo + "," + deadline.getBy();
                break;
            case "E":
                Event event = (Event) task;
                taskInfo = taskInfo + "," + event.getAt();
                break;
            }
            writer.write(taskInfo + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Read data from hard disk
     * @throws IOException if the file/ file path does not exist.
    */
    public void readData() throws IOException {
        try{
            BufferedReader in = new BufferedReader(new FileReader(".\\src\\DataSrc\\taskList.txt"));
        } catch(IOException e){
            System.out.println("Welcome new user!");
            createFile("./src/DataSrc");
        }
        BufferedReader in = new BufferedReader(new FileReader(".\\src\\DataSrc\\taskList.txt"));
        String taskLine;
        while ((taskLine = in.readLine()) != null) {
            String[] task = taskLine.split(",");
            switch (task[0]) {
            case "T":
                ToDo todo = new ToDo(task[2]);
                TaskManager.tasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(task[2], task[3]);
                TaskManager.tasks.add(deadline);
                break;
            case "E":
                Event event = new Event(task[2], task[3]);
                TaskManager.tasks.add(event);
                break;
            }
            if (task[1].equals("true"))
                TaskManager.tasks.get(TaskManager.tasks.size() - 1).setDone();
        }
        in.close();
    }

    private void createFile(String path){
        try {
            File f = new File(path);
            if(!f.exists()){
                f.mkdirs();
            }
            Files.createFile(Paths.get("./src/DataSrc/taskList.txt"));
            System.out.println("File created successfully!");
        }catch(IOException e){
            System.out.println("File created unsuccessfully!");
        }
    }
}
