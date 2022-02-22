package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    TaskList tasks = new TaskList();
    public void Storage() {
        tasks = new TaskList();
    }
    public  void readTaskFile() {
        try {
            File directory = new File("data");
            if(!directory.exists()) {
                directory.mkdir();
            }
            File dukeFile = new File("data/duke.txt");
            if(dukeFile.createNewFile()) {
                System.out.println("    I have created \"duke.txt\" to store your tasks: "+dukeFile.getName());
            } else {
                System.out.println("    I have restored your tasks from the last session!");
            }
            Scanner s = new Scanner(dukeFile);
            List<String> taskInfo;
            while(s.hasNext()) {
                taskInfo = Arrays.asList(s.nextLine().split(" \\| "));
                switch(taskInfo.get(0)) {
                case "T":
                    tasks.taskList.add(new Todo(taskInfo.get(2)));
                    tasks.taskList.get(tasks.taskList.size()-1).isDone = taskInfo.get(1).equals("1");
                    break;
                case "E":
                    tasks.taskList.add(new Event(taskInfo.get(2),taskInfo.get(3)));
                    tasks.taskList.get(tasks.taskList.size()-1).isDone = taskInfo.get(1).equals("1");
                    break;
                case "D":
                    tasks.taskList.add(new Deadline(taskInfo.get(2),taskInfo.get(3)));
                    tasks.taskList.get(tasks.taskList.size()-1).isDone = taskInfo.get(1).equals("1");
                    break;
                }
            }
            tasks.listTasks();
        } catch (IOException e) {
            System.out.println("Could not create file");
            e.printStackTrace();
        }
    }

    public void writeTaskFile() {
        try {
            File dukeFile = new File("data/duke.txt");
            FileWriter fw = new FileWriter(dukeFile.getAbsolutePath());
            String taskType="";
            for(Task t: tasks.taskList) {
                if (t==null) {
                    break;
                }
                if(t instanceof Todo) {
                    taskType = "T";
                    fw.write(taskType+" | "+(t.isDone()?1:0)+" | "+ t.getTitle());
                    fw.write(System.lineSeparator());
                }
                else if(t instanceof Deadline) {
                    taskType = "D";
                    fw.write(taskType+" | "+(t.isDone()?1:0)+" | "+ t.getTitle()+" | "+((Deadline) t).getBy());
                    fw.write(System.lineSeparator());
                }
                else if (t instanceof Event) {
                    taskType = "E";
                    fw.write(taskType+" | "+(t.isDone()?1:0)+" | "+ t.getTitle()+" | "+ ((Event) t).getEventTime());
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not write into file");
            e.printStackTrace();
        }
    }
}
