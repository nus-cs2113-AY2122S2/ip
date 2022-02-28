package duke;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    public TaskList(ArrayList<Task> listOfTasks){
        setListOfTasks(listOfTasks);
    }

    public TaskList(){
        ArrayList<Task> listOfTasks = new ArrayList<>();
        setListOfTasks(listOfTasks);
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    public void setListOfTasks(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task newTask){
        getListOfTasks().add(newTask);
    }

    public String updateTask (int taskIndex, boolean isTaskDone) throws DukeException {
        String updateTaskMessage;
        if(taskIndex > getListSize()){
            throw new DukeException(DukeExceptionCause.TASKINDEXOUTOFRANGE);
        }
        Task taskToUpdate = getListOfTasks().get(taskIndex);
        if(isTaskDone) {
            taskToUpdate.setDone(true);
        }else{
            taskToUpdate.setDone(false);
        }
        updateTaskMessage = taskToUpdate.getTaskUpdatedMessage();
        return updateTaskMessage;
    }
    public int getListSize(){
        return listOfTasks.size();
    }

    public Task getTask(int taskIndex){
        return getListOfTasks().get(taskIndex);
    }

    public String removeTask(int taskIndex){
        String AcknowledgementMessage = getListOfTasks().get(taskIndex).removeTaskMessage();
        getListOfTasks().remove(taskIndex);
        return AcknowledgementMessage;
    }

    public TaskList findTasks(String keyWord){
        TaskList listOfMatchingTask = new TaskList();
        for (int i = 0; i < getListSize(); i++){
            String taskDescription = listOfTasks.get(i).getTaskDescription();
            if (taskDescription.contains(keyWord)){
                listOfMatchingTask.addTask(listOfTasks.get(i));
            }
        }
        return listOfMatchingTask;
    }
}
