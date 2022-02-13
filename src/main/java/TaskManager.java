import DukeException.DukeEmptyException;
import DukeException.DukeInvalidInputException;
import DukeTask.Deadline;
import DukeTask.Event;
import DukeTask.Task;
import DukeTask.ToDo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskManager {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static final List<String> choiceList = Arrays.asList(new String[]{"TODO", "DEADLINE", "EVENT"});

    /*
    addTask is a public method for adding DukeTask.Task based on its type (TODOm DEADLINE, EVENT).
    It will throw exception if no description is provided or no time is provided for DEADLINE and EVENT task.
     */
    public static void addTask(String reply) throws DukeEmptyException, DukeInvalidInputException {
        String choice = reply.split(" ")[0];
        String newTask = reply.replace(choice+" ", "");

        //if the description of newTask is empty
        if(reply.split(" ").length==1 && choiceList.contains(choice.toUpperCase())){
            DukeEmptyException e = new DukeEmptyException(choice.toUpperCase());
            throw e;
        }

        switch(choice.toUpperCase()) {
        case "TODO":
            tasks.add(new ToDo(newTask));
            break;
        case "DEADLINE":
            int startIndexforBy = newTask.indexOf("/by");
            String by = newTask.substring(startIndexforBy + 4);
            String newDeadline = newTask.substring(0, startIndexforBy - 1);
            tasks.add(new Deadline(newDeadline, by));
            break;
        case "EVENT":
            int startIndexforAt = newTask.indexOf("/at");
            String at = newTask.substring(startIndexforAt + 4);
            String newEvent = newTask.substring(0, startIndexforAt - 1);
            tasks.add(new Event(newEvent, at));
            break;
        default:
            DukeInvalidInputException e = new DukeInvalidInputException("Invalid choice");
            throw e;
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }

    /*
    printTask is a public method for printing Tasks in current TaskList.
     */
    public void printTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i=1;i<=tasks.size();i++){
            System.out.println(i+". "+tasks.get(i-1));
        }
        System.out.println("____________________________________________________________");
    }

    /*
    changeTaskStatus is a private method for mark/ unmark a DukeTask.Task.
    It will throw exception if the index is out of range.
     */
    private void changeTaskStatus(int index, boolean markDone) throws DukeInvalidInputException{
        if(index> tasks.size() || index<=0){
            DukeInvalidInputException e = new DukeInvalidInputException("Invalid input");
            throw e;
        }
        if(markDone){//to mark it as done
            tasks.get(index-1).setDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done: ");
        }
        else{
            tasks.get(index-1).setUndone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks.get(index-1));
        System.out.println("____________________________________________________________");
    }

    /*
    relatedToMark is a public method which checks if the user input is related to "mark" or "unmark" action.
    If yes, it will call changeTaskStatus to do the corresponding task and return true. Otherwise, it returns false.
    It is able to handle DukeInvalidException and NumberFormatException from changeTaskStatus method.
     */
    public boolean relatedToMark(String reply){
        String[] words = reply.split(" ");
        if (words[0].equalsIgnoreCase("mark") || words[0].equalsIgnoreCase("unmark")) {
            if(words.length==1) {
                System.out.println("OOPS!!! The mark/ unmark index should not be empty!");
                return true;
            }
            try {
                int index = Integer.parseInt(words[1]);
                boolean markDone = words[0].equalsIgnoreCase("mark")? true: false;
                changeTaskStatus(index, markDone);
            } catch (DukeInvalidInputException | NumberFormatException e) {
                System.out.println("OOPS!!! The mark index you've entered is invalid!");
            }
            finally {
                return true;
            }
        }
        return false;
    }

    /*
    saveData is a public method to save current data into the hard disk (relative path: src\\DataSrc\\taskList.txt)
     */
    public void saveData() throws IOException {
        FileWriter writer = new FileWriter("src\\DataSrc\\taskList.txt");
        for(Task task: tasks){
            if(task!=null){
                boolean taskDone = task.getStatusIcon().equalsIgnoreCase("X");
                String taskType = task.toString().substring(1,2);
                String taskInfo = taskType+","+taskDone+","+task.getDescription();
                switch(taskType){
                case "D":
                    Deadline deadline = (Deadline) task;
                    taskInfo = taskInfo + "," + deadline.getBy();
                    break;
                case "E":
                    Event event = (Event) task;
                    taskInfo = taskInfo + "," + event.getAt();
                    break;
                }
                writer.write(taskInfo + System.lineSeparator());
            }
            else
                break;
        }
        writer.close();
    }

    /*
    readData is a public method to read data from hard disk
     */
    public void readData() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src\\DataSrc\\taskList.txt"));
        String taskLine;
        while ((taskLine = in.readLine()) != null) {
            String[] task = taskLine.split(",");
            switch (task[0]) {
            case "T":
                ToDo todo = new ToDo(task[2]);
                tasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(task[2], task[3]);
                tasks.add(deadline);
                break;
            case "E":
                Event event = new Event(task[2], task[3]);
                tasks.add(event);
                break;
            }
            if (task[1].equals("true"))
                tasks.get(tasks.size() - 1).setDone();
        }
        in.close();
    }

    /*
    deleteTask is a public method which delete a task based on its given index.
    It will throw exception if the index is out of range.
     */
    public void deleteTask(int index){
        Task deletedTask = tasks.get(index-1);
        tasks.remove(index-1);
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task: \n"+deletedTask);
        System.out.println("Now you have "+tasks.size()+" tasks in the list.");
        System.out.println("____________________________________________________________");

    }

}
