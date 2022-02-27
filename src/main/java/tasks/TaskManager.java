package tasks;

import UI.*;
import exceptions.*;

import java.time.LocalDate;

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
    Chatbox chatbox = new Chatbox();

    public String taskNumberMsg(int taskNumber){
        return "Now you have " + taskNumber + " tasks in your list.";
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
     * Adds a general task in the list
     * @param name refers to the name of the task
     */
    public void addTask(String name) {
        Task newTask = new Task(name);
        Tasks.add(newTask);
        chatbox.setContent("added: " + name);
        chatbox.chatboxPrinter();
    }

    /**
     * Adds a deadline task to the list
     * @param name refers to the name of the task
     * @param by refers to the deadline of the task
     */
    public String addDeadline(String name, String by) {
        Deadline newDeadline = new Deadline(name, by);
        Tasks.add(newDeadline);
        int s = Tasks.size();
        return ADD_RESPONSE + newDeadline.getListName() +
                "\n" + this.taskNumberMsg(s);
    }

    /**
     * Adds an event task to the list
     * @param name refers to the name of the task
     * @param at refers to the happening time of the event
     */
    public String addEvent(String name, String at) {
        Event newEvent = new Event(name, at);
        Tasks.add(newEvent);
        int s = Tasks.size();
        return ADD_RESPONSE + newEvent.getListName() +
                "\n" + this.taskNumberMsg(s);
    }

    /**
     * Adds a todo task to the list
     * @param name refers to the name of the todo task
     */
    public String addToDo(String name) {
        ToDo newToDo = new ToDo(name);
        Tasks.add(newToDo);
        int s = Tasks.size();
        return ADD_RESPONSE + newToDo.getListName() + "\n" +
                this.taskNumberMsg(s);
    }

    /**
     * Lists all tasks in the list with adding time order
     */
    public String listTask() throws DukeExceptions{
        if(Tasks.size() == 0){
            throw new EmptyListException();
        }else {
            String content = LIST_RESPONSE;
            for (int i = 0; i < Tasks.size(); i++) {
                String index = String.valueOf(i + 1);
                String name = index + ". " + Tasks.get(i).getListName();
                content += name;
                if(i < Tasks.size() - 1){
                    content += "\n";
                }
            }
            return content;
        }

    }

    /**
     * Marks specific task in the list as done
     * @param n refers to the index of the task in adding time order
     */
    public String markTask(int n) throws DukeExceptions{
        System.out.println(n);
        if(n < 1 || n > Tasks.size()) {
            //Beyonds boundaries
            throw new IllegalIndexException();
        }else {
            Tasks.get(n - 1).mark();
            return MARK_RESPONSE + Tasks.get(n - 1).listName;
        }
    }

    /**
     * Unmarks specific task in the list as undone
     * @param n refers to the index of the task in adding time order
     */
    public String unmarkTask(int n) throws DukeExceptions{
        if(n < 1 || n > Tasks.size()){
            throw new IllegalIndexException();
        }else {
            Tasks.get(n - 1).unmark();
            return UNMARK_RESPONSE + Tasks.get(n - 1).listName;

        }
    }

    public String deleteTask(int n) throws DukeExceptions{
        if(n < 1 || n > Tasks.size()) {
            //Beyonds boundaries
            throw new IllegalIndexException();
        }else {
            String content =  DELETE_RESPONSE + Tasks.get(n - 1).listName;
            Tasks.remove(n-1);
            int s = Tasks.size();
            content += "\n" + this.taskNumberMsg(s);
            return content;
        }
    }

    public String searchTask(String keywords) throws DukeExceptions{
        if(Tasks.size() == 0){
            throw new EmptyListException();
        }else {
            String content = "";
            int count  = 0;
            for (int i = 0; i < Tasks.size(); i++) {
                if (Tasks.get(i).getListName().contains(keywords)) {
                    content += "\n";
                    String index = String.valueOf(count + 1);
                    String name = index + ". " + Tasks.get(i).getListName();
                    content += name;
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
