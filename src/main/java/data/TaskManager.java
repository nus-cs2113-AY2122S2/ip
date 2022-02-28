package data;

import common.DukeException;
import storage.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public Task getTask(int idx) {
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

    public void markTask(int idx) {
//        if(idx > tasks.size()){
//            throw new DukeException("Task index out of bound.");
//        }
        tasks.get(idx-1).markAsDone();
    }

    public void unmarkTask(int idx) {
//        if(idx > tasks.size()){
//            throw new DukeException("Task index out of bound.");
//        }
        tasks.get(idx-1).unmark();
//        System.out.println("\t" + "-".repeat(60));
//        System.out.println("\t OK, I've marked this task as not done yet:");
//        System.out.println("\t\t " + idx + "." + tasks.get(idx-1).toString());
//        System.out.println("\t" + "-".repeat(60));
    }
}
