package data;

import common.DukeException;
import storage.FileManager;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private FileManager fileManager = new FileManager();

    public TaskManager() {

    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getNoOfTasks () {
        return tasks.size();
    }


//    public void addTask(String option, String taskDescription) throws DukeException{
//        if(option.equals("todo")) {
//            taskDescription = taskDescription.trim();
//            tasks.add(new Todo(taskDescription));
//            return;
//        }
//
//        if(option.equals("deadline")) {
//            try {
//                String[] descriptions = taskDescription.split("/by", 2);
//                descriptions[0] = descriptions[0].trim();
//                descriptions[1] = descriptions[1].trim();
//                tasks.add(new Deadline(descriptions[0], descriptions[1]));
//            } catch (IndexOutOfBoundsException e) {
//                throw new DukeException("Deadline is not specified.");
//            }
//        } else if(option.equals("event")) {
//            try {
//                String[] descriptions = taskDescription.split("/at", 2);
//                descriptions[0] = descriptions[0].trim();
//                descriptions[1] = descriptions[1].trim();
//                tasks.add(new Event(descriptions[0], descriptions[1]));
//            } catch (IndexOutOfBoundsException e){
//                throw new DukeException("Event time is not specified.");
//            }
//        }
//    }

    public Task getTask(int idx) throws DukeException {
        if(idx <= 0 || idx > tasks.size()) {
            throw new DukeException(". Index out of bound." + "Failed to get task " + idx + ".");
        }

        return tasks.get(idx - 1);
    }

    public void deleteTask(int idx) {
//        if(idx < 0 || idx > tasks.size()){
//            throw new DukeException("Task index out of bound.");
//        }
//
//        Task deleted = tasks.get(idx - 1);
        tasks.remove(idx - 1);
    }

    public void markTask(int idx) throws DukeException {
        if(idx <=0 || idx > tasks.size()){
            throw new DukeException("Task index out of bound. I cannot mark task " + idx + ".");
        }
        tasks.get(idx-1).markAsDone();
    }

    public void unmarkTask(int idx) throws DukeException {
        if(idx <=0 || idx > tasks.size()){
            throw new DukeException("Task index out of bound. I cannot unmark task " + idx + ".");
        }
        tasks.get(idx-1).unmark();
    }
}
