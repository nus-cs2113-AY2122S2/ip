package tasks;

import exceptions.*;

public class TaskManager {
    Storage Tasks = new Storage();
    //response of adding
    protected static final String ADD_RESPONSE = "ψ(._. )> Got it. I've added this task:\n";
    protected static final String MARK_RESPONSE = "ψ(._. )> Nice! I've marked this task as done:\n";
    protected static final String UNMARK_RESPONSE = "ψ(._. )> OK, I've marked this task as not done yet:\n";
    protected static final String LIST_RESPONSE = "o(≧v≦)o Here are the tasks in your list:\n";
    protected static final String DELETE_RESPONSE = "ψ(._. )> Okay! I've deleted this task:\n";
    protected static final String NOT_FOUND_RESPONSE = "(￣ε￣；) Sorry, I can't find any result from the list.";
    protected static final String FOUND_RESPONSE = "o(≧v≦)o Here are the matching tasks in your list:";

    public String taskNumberMsg(int taskNumber){
        StringBuilder content = new StringBuilder();
        content.append("Now you have ").append(taskNumber).append(" tasks in your list.");
        return content.toString();
    }
    public void saveTask() throws DukeExceptions {
        Tasks.saveTask();
    }

    public void loadTask() throws DukeExceptions {
        Tasks.loadTask();
    }

    public void createFile() throws DukeExceptions {
        Tasks.createFile();
    }
    /**
     * Adds a deadline task to the list
     * @param name refers to the name of the task
     * @param by refers to the deadline of the task
     */
    public String addDeadline(String name, String by) {
        Deadline newDeadline = new Deadline(name, by);
        Tasks.add(newDeadline);
        int s = Tasks.getSize();
        return ADD_RESPONSE + newDeadline.getListName() +
                "\n" + this.taskNumberMsg(s);
    }

    /**
     * Adds an event task to the list
     * @param name refers to the name of the task
     * @param at refers to the happening time of the event
     */
    public String addEvent(String name, String at) {
        StringBuilder content = new StringBuilder(ADD_RESPONSE);
        Event newEvent = new Event(name, at);
        Tasks.add(newEvent);
        int s = Tasks.getSize();
        content.append(newEvent.getListName()).append("\n").append(this.taskNumberMsg(s));
        return content.toString();
    }

    /**
     * Adds a todo task to the list
     * @param name refers to the name of the todo task
     */
    public String addToDo(String name) {
        StringBuilder content = new StringBuilder(ADD_RESPONSE);
        ToDo newToDo = new ToDo(name);
        Tasks.add(newToDo);
        int s = Tasks.getSize();
        content.append(newToDo.getListName()).append("\n").append(this.taskNumberMsg(s));
        return  content.toString();
    }

    /**
     * Lists all tasks in the list with adding time order
     */
    public String listTask() throws DukeExceptions{
        if(Tasks.getSize() == 0){
            throw new EmptyListException();
        }else {
            StringBuilder content = new StringBuilder(LIST_RESPONSE);
            for (int i = 0; i < Tasks.getSize(); i++) {
                String index = String.valueOf(i + 1);
                String name = index + ". " + Tasks.get(i).getListName();
                content.append(name);
                if(i < Tasks.getSize() - 1){
                    content.append("\n");
                }
            }
            return content.toString();
        }

    }

    /**
     * Marks specific task in the list as done
     * @param indexOfTask refers to the indexOfTask of the task in adding time order
     */
    public String markTask(int indexOfTask) throws DukeExceptions{
        if(isOutOfBoundary(indexOfTask)) {
            //Beyonds boundaries
            throw new IllegalIndexException();
        }else {
            Tasks.get(indexOfTask - 1).mark();
            return MARK_RESPONSE + Tasks.get(indexOfTask - 1).listName;
        }
    }

    /**
     * Unmarks specific task in the list as undone
     * @param indexOfTask refers to the indexOfTask of the task in adding time order
     */
    public String unmarkTask(int indexOfTask) throws DukeExceptions{
        if(isOutOfBoundary(indexOfTask)){
            throw new IllegalIndexException();
        }else {
            Tasks.get(indexOfTask - 1).unmark();
            return UNMARK_RESPONSE + Tasks.get(indexOfTask - 1).listName;

        }
    }

    private boolean isOutOfBoundary(int indexOfTask) {
        return indexOfTask < 1 || indexOfTask > Tasks.getSize();
    }


    public String deleteTask(int indexOfTask) throws DukeExceptions{
        if(isOutOfBoundary(indexOfTask)) {
            //Beyonds boundaries
            throw new IllegalIndexException();
        }else {
            String content =  DELETE_RESPONSE + Tasks.get(indexOfTask - 1).listName;
            Tasks.remove(indexOfTask-1);
            int s = Tasks.getSize();
            content += "\n" + this.taskNumberMsg(s);
            return content;
        }
    }

    public String searchTask(String keywords) throws DukeExceptions{
        if(Tasks.getSize() == 0){
            throw new EmptyListException();
        }else {
            StringBuilder content = new StringBuilder();
            int count  = 0;
            for (int i = 0; i < Tasks.getSize(); i++) {
                if (Tasks.get(i).getTaskName().contains(keywords)) {
                    content.append("\n");
                    String index = String.valueOf(count + 1);
                    String name = index + ". " + Tasks.get(i).getListName();
                    content.append(name);
                    count ++;
                }
            }
            if(count == 0){
                return NOT_FOUND_RESPONSE;
            }
            return FOUND_RESPONSE + content;
        }
    }

}
