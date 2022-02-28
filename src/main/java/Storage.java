import Tasks.*;
import java.io.*;
import java.util.*;

/**
 * Include all the file operations such as loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Find the file according to the given filepath. If the file has not been created, create the file/
     * @param filePath
     */
    public static void create(String filePath){
        try {
            File data = new File(filePath);
            if (data.createNewFile()) {
                System.out.println("No data file detected, I have created a new data file for you!");
            } else {
                System.out.println("I have found the file and loading the data for you!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during file searching.");
        }
    }

    /**
     * Save the information into the file.
     * @param textToAdd Texts going to be saved in the file.
     * @param filePath
     * @throws IOException
     */

    public static void save(String textToAdd,String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath,false);
        try {
            fw.write(textToAdd+System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong, the data is not successfully saved in the file.");
        }
        fw.close();
    }

    /**
     * Add the information in the given file into an arraylist.
     * @return Arraylist loaded from the file.
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> todolist = new ArrayList<Task>();
        BufferedReader br = new BufferedReader(new FileReader(this.filePath));
        String taskLine;
        while ((taskLine = br.readLine())!=null) {
            String[] t = taskLine.split(",");
            switch (t[0]) {
                case "T":
                    ToDo todo = new ToDo(t[2]);
                    todolist.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(t[2], t[3]);
                    todolist.add(deadline);
                    break;
                case "E":
                    Event event = new Event(t[2], t[3]);
                    todolist.add(event);
                    break;
            }
            if (t.length>1&&t[1].equals("true"))
                todolist.get(todolist.size() - 1).markAsDone();
        }
        br.close();
        System.out.println("I have successfully loaded the file for you!");
        return todolist;
    }

    /**
     * format the information in the arraylist into a string.
     * @param taskList Arraylist going to be formatted.
     * @return String formatted from the tasklist.
     */

    public static String format(ArrayList<Task> taskList){
        String taskAllInfo="";
        for(Task k: taskList){
            String taskInfo = k.getType()+","+k.getStatus()+","+k.getDescription();
            if(k.getType()=="D") {
                Deadline d = (Deadline) k;
                taskInfo = taskInfo + "," + d.getBy();
            }
            else if(k.getType()=="E"){
                Event e = (Event) k;
                taskInfo = taskInfo + "," + e.getAt();
            }
            taskAllInfo+=taskInfo+"\n";
        }
        if (taskAllInfo!=null) taskAllInfo=taskAllInfo.substring(0, taskAllInfo.length() - 1);
        return taskAllInfo;
    }
}
