package duke.tasklist;
import java.util.ArrayList;
import duke.DukeException;
import java.io.IOException;

import duke.Storage;


public class TaskList {
    private ArrayList<Task> taskList;


    public TaskList(ArrayList<Task> a) {
        this.taskList = a;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public int getTaskSize() {
        return this.taskList.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list : ");
        for (int i = 0; i<taskList.size(); i++) {
            System.out.println(i+1 + "." + taskList.get(i).toString());
        }
    }

    public ArrayList<Task> deleteTask(int indexValue2) {

        Task removedTask = taskList.get(indexValue2);
        taskList.remove(indexValue2);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        return this.taskList;
    }

    public String addTodo(String desc) {

        System.out.println("Got it. I've added this task:");
        taskList.add(new Todo(desc));
        System.out.println(taskList.get(taskList.size()-1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        String textToAdd = "T" + "|" + " 0 " + "|" + desc;
        // return this string so Storage can use to add to text file
        return textToAdd;

    }

    public String addDeadline(String desc, String by) {
        taskList.add(new Deadline(desc, by));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        String textToAdd = "D" + "|" + " 0 " + "|" + desc + "|" + by;
        return textToAdd;

    }

    public String addEvent(String desc, String by)  {

        taskList.add(new Event(desc,by));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size()-1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        String textToAdd = "E" + "|" + " 0 " + "|" + desc + "|" + by;
        return textToAdd;
    }

    public ArrayList<Task> markTask(int indexValue2) {
        taskList.get(indexValue2).markTask();
        return(this.taskList);
    }

    public ArrayList<Task> unmarkTask(int indexValue2) {
        taskList.get(indexValue2).unmarkTask();
        return(this.taskList);
    }

    public void find(String keyword){
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i<taskList.size(); i++) {
            if (taskList.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(i+1 + "." + taskList.get(i).toString());
            }

        }

    }


}