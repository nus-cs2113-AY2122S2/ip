package data;

import common.DukeException;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
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

    public Task getTask(int idx) throws DukeException {
        if(idx <= 0 || idx > tasks.size()) {
            throw new DukeException("Index out of bound. Failed to get task " + idx + ".");
        }

        return tasks.get(idx - 1);
    }

    public void deleteTask(int idx) throws DukeException{
        if(idx <= 0 || idx > tasks.size()){
            throw new DukeException("Task index out of bound. I cannot delete task " + idx + ".");
        }

        tasks.remove(idx - 1);
    }

    public void markTask(int idx) throws DukeException {
        if(idx <= 0 || idx > tasks.size()){
            throw new DukeException("Task index out of bound. I cannot mark task " + idx + ".");
        }
        tasks.get(idx-1).markAsDone();
    }

    public void unmarkTask(int idx) throws DukeException {
        if(idx <= 0 || idx > tasks.size()){
            throw new DukeException("Task index out of bound. I cannot unmark task " + idx + ".");
        }
        tasks.get(idx-1).unmark();
    }

    public ArrayList<Integer> findTask(String keyword) {
        ArrayList<Integer> results = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getDescription().contains(keyword)) {
                results.add(i + 1);
            }
        }

        return results;
    }
}
