import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @param allTasks
     * @throws IOException
     */
    public void writeToDukeFile(ArrayList<Task> allTasks) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        for(Task t:allTasks){
            fw.write(t.toString()+"\n");
        }
        fw.close();
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        Task t;
        BufferedReader bufReader = new BufferedReader(new FileReader(this.filePath));

        ArrayList<Task> allTasks = new ArrayList<>();
        String line = bufReader.readLine();
        char taskType, taskMarked;
        String taskDetail;

        while (line != null) {

            taskType = line.charAt(1);
            taskMarked = line.charAt(4);
            taskDetail = line.substring(6);
            switch (taskType){
                case 'T':
                    t = convertTodo(taskDetail);
                    break;
                case 'D':
                    t = convertDeadline(taskDetail);
                    break;
                case 'E':
                    t = convertEvent(taskDetail);
                    break;
                default:
                    t = null;
                    break;
            }
            if(taskMarked=='X'){
                t.markAsDone();
            }
            else{
                t.markAsUnDone();
            }
            line = bufReader.readLine();
            allTasks.add(t);
        }
        bufReader.close();

        return allTasks;
    }

    /**
     *
     * @param taskDetail
     * @return
     */
    private Todo convertTodo(String taskDetail){
        Todo todo = new Todo(taskDetail);
        return todo;
    }

    /**
     *
     * @param taskDetail
     * @return
     */
    private Deadline convertDeadline(String taskDetail){
        String description, by;
        description = taskDetail.split("by:")[0].trim();
        by = taskDetail.split("by:")[1].trim();
        Deadline deadline = new Deadline(description, by);
        return deadline;
    }

    /**
     * 
     * @param taskDetail
     * @return
     */
    private Event convertEvent(String taskDetail){
        String description, at;
        description = taskDetail.split("at:")[0].trim();
        at = taskDetail.split("at:")[1].trim();
        Event event = new Event(description, at);
        return event;
    }
}
