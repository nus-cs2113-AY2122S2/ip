package baymax.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import baymax.data.Deadline;
import baymax.data.Event;
import baymax.data.Task;
import baymax.data.Todo;



public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {

        File file = new File(filePath);
        if(!file.exists()) {
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        decode(s, tasks);
        return tasks;
    }

    public static void decode(Scanner s, ArrayList<Task> tasks) {
        int taskIndex = -1;
        while(s.hasNext()){
            taskIndex++;
            Task temp;
            String[] word_split = s.nextLine().split(" / ");
            //System.out.println(word_split[0]+" sdf " + word_split[1] + " df "+ word_split[2]+ " df "+ word_split[3]);
            switch(word_split[0]){
                case "T":
                    tasks.add(new Todo(word_split[2]));
                    if (word_split[1].equals("1")) {
                        temp = tasks.get(taskIndex);
                        temp.markTaskDone();
                    }
                    break;
                case "D":
                    tasks.add(new Deadline(word_split[2],word_split[3]));
                    if (word_split[1].equals("1")) {
                        temp = tasks.get(taskIndex);
                        temp.markTaskDone();
                    }
                    break;
                case "E":
                    tasks.add(new Event(word_split[2],word_split[3]));
                    if (word_split[1].equals("1")) {
                        temp = tasks.get(taskIndex);
                        temp.markTaskDone();
                    }
                    break;
                default:
                    System.out.println("Error accur, please check.");
                    break;
            }
        }

    }

    public void save(ArrayList<Task> tasks) throws IOException {
        encode(tasks, filePath);
    }

    public static void encode(ArrayList<Task> tasks, String filePath) throws IOException {
        FileWriter fw = new FileWriter("data/Baymax.txt");
        for(int i = 0; i < tasks.size(); i++){
            fw.write(tasks.get(i).saveInfo() + "\n");
        }
        fw.close();
    }
}


