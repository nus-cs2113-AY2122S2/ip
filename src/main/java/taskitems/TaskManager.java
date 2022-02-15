package taskitems;

import taskitems.exceptions.IllegalInputException;
import taskitems.task.Deadline;
import taskitems.task.Event;
import taskitems.task.Task;
import taskitems.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private final String PATH = "data.txt";
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;
    static Greet greet;

    public TaskManager() {
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void saveData() {
        File data = new File(PATH);
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to save data...");
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(PATH,false);
            for(Task a : tasks) {
                dataWrite.write(a.saveString() + "\n");
            }
            dataWrite.close();
        } catch (IOException e) {
            System.out.println("Unable to save data...");
        }
    }

    public void markTask(int number) throws IllegalInputException {
        greet.printDecoration();
        if (number > taskCount || number < 1) {
            throw new IllegalInputException();
        }
        if (tasks.get(number - 1).isMarked()) {
            System.out.println("Err, this task is already marked...");
        } else {
            tasks.get(number - 1).setMarked(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(tasks.get(number - 1));
        }
        greet.printDecoration();
        saveData();
    }

    public void unmarkTask(int number) throws IllegalInputException {
        greet.printDecoration();
        if(number > taskCount || number < 1){
            throw new IllegalInputException();
        }
        if(!tasks.get(number - 1).isMarked()){
            System.out.println("I cannot unmark something that was never marked...");
        } else {
            tasks.get(number - 1).setMarked(false);
            System.out.println("Okay Boss! The following task has been unmarked: ");
            System.out.println(tasks.get(number - 1));
        }
        greet.printDecoration();
        saveData();
    }

    public void addToTasks(String taskName) throws IllegalInputException {
        if (taskName.equals("")) {
            throw new IllegalInputException();
        }
        greet.printDecoration();
        tasks.add(new Todo(taskName));
        System.out.println("added: " + tasks.get(taskCount));
        taskCount++;
        System.out.println("You now have " + taskCount + " tasks in the list.");
        greet.printDecoration();
        saveData();
    }

    public void addToTasks(String type, String taskName,String date){
        greet.printDecoration();
        if (type.equals("E")) {
            tasks.add(new Event(taskName, date));
        } else {
            tasks.add(new Deadline(taskName, date));
        }
        System.out.println("added: " + tasks.get(taskCount));
        taskCount++;
        System.out.println("You now have " + taskCount + " tasks in the list.");
        greet.printDecoration();
        saveData();
    }

    private void addToTasks(String taskName, boolean isBackend) throws IllegalInputException {
        if (taskName.equals("")) {
            throw new IllegalInputException();
        }
        tasks.add(new Todo(taskName));
        taskCount++;
    }

    private void addToTasks(String type, String taskName,String date, boolean isBackend){
        if (type.equals("E")) {
            tasks.add(new Event(taskName, date));
        } else {
            tasks.add(new Deadline(taskName, date));
        }
        taskCount++;
    }

    private void markTask(int number, boolean isBackend) throws IllegalInputException {
        if (number > taskCount || number < 1) {
            throw new IllegalInputException();
        } else {
            tasks.get(number - 1).setMarked(true);
        }
    }

    public void loadData() throws FileNotFoundException {
        greet.printDecoration();
        System.out.println("***Please wait while I fetch past data...***");
        File data = new File(PATH);
        Scanner reader = new Scanner(data);
        int count = 1;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            String[] dataLine = line.split(",");
            boolean hasFailed = false;
            switch (dataLine[0]) {
            case "T":
                try {
                    addToTasks(dataLine[1],true);
                } catch (IllegalInputException e) {
                    System.out.println("Line " + count + " is corrupted.");
                    hasFailed = true;
                }
                break;
            case "D":
                try {
                    addToTasks("D",dataLine[1],dataLine[3],true);
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    System.out.println("Line " + count + " is corrupted.");
                    hasFailed = true;
                }
                break;
            case "E":
                try {
                    addToTasks("E",dataLine[1],dataLine[3],true);
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    System.out.println("Line " + count + " is corrupted.");
                    hasFailed = true;
                }
                break;
            default:
                System.out.println("Line " + count + " is corrupted,");
                hasFailed = true;
                break;
            }
            if (!hasFailed && dataLine[2].equals("1")) {
                try {
                    markTask(taskCount,true);
                } catch (IllegalInputException e) {
                    System.out.println("Corrupted data has been loaded, please check task number: " + taskCount);
                }
            }
            count++;
        }
        if (taskCount == 0) {
            System.out.println("No saved data found");
        } else {
            System.out.println("***" + taskCount + " out of " + (count-1) +
                    " lines of data are valid and fetched accurately." + "***");
        }
    }

    public void printTasks() {
        greet.printDecoration();
        if (taskCount == 0) {
            System.out.println("You have not added any Tasks!");
        } else {
            for (int i = 0; i < taskCount ; i++) {
                System.out.print(i+1 + ". ");
                System.out.println(tasks.get(i));
            }
        }
        greet.printDecoration();
    }
}

