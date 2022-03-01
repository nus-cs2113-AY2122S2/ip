package duke;

import duke.deadline.Deadline;
import duke.duke_exception.DukeException;
import duke.event.Event;
import duke.event.ToDo;
import duke.task.Task;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import duke.Storage;

public class TaskList {
    private List<Task> taskList;
    private Storage fileClass;
    public TaskList(){
        taskList = new ArrayList<Task>();
        fileClass = new Storage();
        loadTaskList();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Integer getTaskListSize() {
        return taskList.size();
    }
    private void parseJSONTasks(String saveStr){
        // ATTEMPTING PARSE
        JSONArray ja = (JSONArray) JSONValue.parse(saveStr);
        for(Object o: ja){
            if ( o instanceof JSONObject) {
                JSONObject jo = (JSONObject)o;
                String type = (String)jo.get("type");
//                System.out.println("type: " + type);
                Task toAdd = null;
                if (type.equals("TODO")) {
                    toAdd = new ToDo((String)jo.get("description"));
                } else if (type.equals("DEADLINE")) {
                    toAdd = new Deadline((String)jo.get("description"),(String)jo.get("time"));
                } else {
                    toAdd = new Event((String)jo.get("description"),(String)jo.get("time"));
                }
                taskList.add(toAdd);
            }
        }
    }

    public void loadTaskList() {
        String saveStr = fileClass.readSaveFile();
        parseJSONTasks(saveStr);
    }

    public void saveTaskList(){
        String storeStr = serializeTaskList();
        fileClass.writeSaveFile(storeStr);
    }

    private String serializeTaskList() {
        JSONArray toStore = new JSONArray();
        for (Task task : taskList) {
            toStore.add(task.serialize());
        }
        String storeStr = toStore.toString();
        return storeStr;
    }


    public Task addTodo(String params) throws DukeException {
        Ui.printEmptyDescription();
        if (params.strip().length() == 0) {
            Ui.printEmptyDescription();
            return null;
        }
        Task newTask = new ToDo(params);
        addTask(newTask);
        return newTask;
    }

    public Task addDeadline(String params) throws DukeException {
        if (params.strip().length() == 0) {
            Ui.printEmptyDescription();
//            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            return null;
        }
        String[] params2 = params.split("/");

        if (params2.length < 2) {
            System.out.println("☹ OOPS!!! Deadlines should have two parts");
            return null;
        }
//        System.out.println(params2[0]);
//        System.out.println(params2[1]);

        if (params2[0].strip().length() == 0){
            Ui.printEmptyDescription();
            return null;
        }
        if (!params2[1].contains("by")) {
            System.out.println("☹ OOPS!!! Deadlines can only take in /by");
            return null;
        }
        if (params2[1].strip().length() < 3){
            System.out.println("☹ OOPS!!! Missing /by");
            return null;
        }
        Task newTask = new Deadline(params2[0], params2[1]);
        addTask(newTask);
        return newTask;
    }

    public Task addEvent(String params) throws DukeException {
        if (params.strip().length() == 0) {
            Ui.printEmptyDescription();
//            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            return null;
        }
        String[] params2 = params.split("/");
//        System.out.println(params);
//        System.out.println(params2.length);
//        System.out.println(params2);
        if (params2.length < 2 ) {
            System.out.println("☹ OOPS!!! Events should have two parts");
            return null;
        }
        if (params2[0].strip().length() == 0){
            Ui.printEmptyDescription();
            return null;
        }
        if (!params2[1].contains("at")) {
            System.out.println("☹ OOPS!!! Events can only take in /at");
            return null;
        }
        if (params2[1].strip().length() < 3){
            System.out.println("☹ OOPS!!! Missing /at");
            return null;
        }

        Task newTask = new Event(params2[0], params2[1]);
        addTask(newTask);
        return newTask;
    }

    public void addTask(Task newTask){
        taskList.add(newTask);
    }

    public void removeTaskByIdx(Integer idx){
        taskList.remove(idx);
    }

    public Task getTaskByIdx(Integer idx){
        return taskList.get(idx);
    }

    public List<Task> findTasks(String query){
        List<Task> queryResult = new ArrayList<Task>();
        for (Task t : taskList){
            String description = t.getDescription();
            boolean isFound = description.contains(query);
            if (isFound){
                queryResult.add(t);
            }
        }
        return queryResult;
    }

}
