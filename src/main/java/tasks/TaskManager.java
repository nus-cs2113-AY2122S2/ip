package tasks;

import exceptions.*;

public class TaskManager {
    Storage Tasks = new Storage();
    //response messages of different action
    protected static final String ADD_RESPONSE = "ψ(._. )> Got it. I've added this task:\n";
    protected static final String MARK_RESPONSE = "ψ(._. )> Nice! I've marked this task as done:\n";
    protected static final String UNMARK_RESPONSE = "ψ(._. )> OK, I've marked this task as not done yet:\n";
    protected static final String LIST_RESPONSE = "o(≧v≦)o Here are the tasks in your list:\n";
    protected static final String DELETE_RESPONSE = "ψ(._. )> Okay! I've deleted this task:\n";
    protected static final String NOT_FOUND_RESPONSE = "(￣ε￣；) Sorry, I can't find any result from the list.";
    protected static final String FOUND_RESPONSE = "o(≧v≦)o Here are the matching tasks in your list:";

    /**
     * Generates a message that contains the number of tasks in the list currently
     * @return the message that contains the number of tasks in the list
     */
    public String taskNumberMsg(){
        int taskNumber = Tasks.getSize();
        StringBuilder content = new StringBuilder();
        content.append("Now you have ").append(taskNumber).append(" tasks in your list.");
        return content.toString();
    }

    /**
     * Saves the tasks in the list to the external file
     * @throws DukeExceptions if the action fails
     */
    public void saveTask() throws DukeExceptions {
        Tasks.saveTask();
    }

    /**
     * Loads the tasks to the arraylist from external file
     * @throws DukeExceptions if the action fails
     */
    public void loadTask() throws DukeExceptions {
        Tasks.loadTask();
    }

    /**
     * Creates an external file for saving and loading task if there's no such file in to correspond folder
     * @throws DukeExceptions if the action fails
     */
    public void createFile() throws DukeExceptions {
        Tasks.createFile();
    }
    /**
     * Adds a deadline task to the list
     * @param name refers to the name of the task
     * @param by refers to the deadline of the task
     * @return content.toString() the message that is going to display on the user interface, contains the
     * response of successful action, information of the new task and the number of task in the list after
     * adding.
     */
    public String addDeadline(String name, String by) {
        Deadline newDeadline = new Deadline(name, by);
        Tasks.add(newDeadline);
        return ADD_RESPONSE + newDeadline.getListName() +
                "\n" + this.taskNumberMsg();
    }

    /**
     * Adds an event task to the list
     * @param name refers to the name of the task
     * @param at refers to the happening time of the event
     * @return content.toString() the message that is going to display on the user interface, contains the
     * response of successful action, information of the new task and the number of task in the list after
     * adding.
     */
    public String addEvent(String name, String at) {
        StringBuilder content = new StringBuilder(ADD_RESPONSE);
        Event newEvent = new Event(name, at);
        Tasks.add(newEvent);
        content.append(newEvent.getListName()).append("\n").append(this.taskNumberMsg());
        return content.toString();
    }

    /**
     * Adds a todo task to the list
     * @param name refers to the name of the todo task
     * @return content.toString() the message that is going to display on the user interface, contains the
     * response of successful action, information of the new task and the number of task in the list after
     * adding.
     */
    public String addToDo(String name) {
        StringBuilder content = new StringBuilder(ADD_RESPONSE);
        ToDo newToDo = new ToDo(name);
        Tasks.add(newToDo);
        content.append(newToDo.getListName()).append("\n").append(this.taskNumberMsg());
        return  content.toString();
    }

    /**
     * Lists all tasks in the list with adding time order
     * @return content.toString() the message that is going to display on the user interface, contains the
     * response of successful action and the information of the all the task in the list.
     * @throws DukeExceptions if there's no task in the list currently
     */
    public String listTask() throws DukeExceptions{
        //checks if there's any task in the list, otherwise throws an exception
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
        //Checks whether the given task index is out of the range
        if(isOutOfBoundary(indexOfTask)){
            throw new IllegalIndexException();
        }else {
            Tasks.get(indexOfTask - 1).unmark();
            return UNMARK_RESPONSE + Tasks.get(indexOfTask - 1).listName;

        }
    }

    /**
     * Checks whether the given task index is out of the range
     * @param indexOfTask the index of the task that need to be checked
     * @return True if the index is out of boundaries
     */
    private boolean isOutOfBoundary(int indexOfTask) {
        return indexOfTask < 1 || indexOfTask > Tasks.getSize();
    }

    /**
     * Delete the specific task by given index
     * @param indexOfTask the index of the task that need to be deleted
     * @return content.toString() the message that is going to display on the user interface, contains the
     * response of successful action, information of the deleted task and the number of task in the list after
     * deleting.
     * @throws DukeExceptions if the task is not appeared in the list
     */
    public String deleteTask(int indexOfTask) throws DukeExceptions{
        //Checks whether the given task index is out of the range
        if(isOutOfBoundary(indexOfTask)) {
            throw new IllegalIndexException();
        }else {
            String content =  DELETE_RESPONSE + Tasks.get(indexOfTask - 1).listName;
            Tasks.remove(indexOfTask-1);
            content += "\n" + this.taskNumberMsg();
            return content;
        }
    }

    /**
     * Searches the matching tasks and lists them by the keyword given by user
     * @param keywords the keyword given by user
     * @return content.toString() the message that is going to display on the user interface, contains the
     * response of successful action and the information of the matching tasks.
     * @throws DukeExceptions if the list is empty
     */
    public String searchTask(String keywords) throws DukeExceptions{
        //checks if there's any task in the list, otherwise throws an exception
        if(Tasks.getSize() == 0){
            throw new EmptyListException();
        }else {
            StringBuilder content = new StringBuilder();
            int count  = 0; //count if there's any matching task
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
