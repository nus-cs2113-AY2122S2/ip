package taskitems;

import taskitems.exceptions.IllegalInputException;
import taskitems.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private final String PATH = "data.txt";
    private TaskList tasks = new TaskList();
    private TaskList bin = new TaskList();
    static Greet greet;

    public TaskManager() {
    }

    public int getTaskCount() {
        return tasks.size;
    }

    private void printTask(int number) {
        System.out.println(tasks.getTask(number));
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
            for(Task a : tasks.taskList) {
                dataWrite.write(a.saveString() + "\n");
            }
            dataWrite.close();
        } catch (IOException e) {
            System.out.println("Unable to save data...");
        }
    }

    public void markTask(int number) throws IllegalInputException {
        greet.printDecoration();
        if (number > tasks.size || number < 1) {
            throw new IllegalInputException();
        }
        if (tasks.getTask(number).isMarked()) {
            System.out.println("Err, this task is already marked...");
        } else {
            tasks.getTask(number).setMarked(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(tasks.getTask(number));
        }
        greet.printDecoration();
        saveData();
    }

    public void unmarkTask(int number) throws IllegalInputException {
        greet.printDecoration();
        if(number > tasks.size || number < 1){
            throw new IllegalInputException();
        }
        if(!tasks.getTask(number).isMarked()){
            System.out.println("I cannot unmark something that was never marked...");
        } else {
            tasks.getTask(number).setMarked(false);
            System.out.println("Okay Boss! The following task has been unmarked: ");
            System.out.println(tasks.getTask(number));
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
        greet.printDecoration();
        saveData();
    }

    private void addToTasks(String taskName, boolean isBackend) throws IllegalInputException {
        if (taskName.equals("")) {
            throw new IllegalInputException();
        }
        tasks.add(new Todo(taskName), true);
    }

    private void addToTasks(String type, String taskName,String date, boolean isBackend){
        if (type.equals("E")) {
            tasks.add(new Event(taskName, date), true);
        } else {
            tasks.add(new Deadline(taskName, date), true);
        }
    }

    private void markTask(int number, boolean isBackend) throws IllegalInputException {
        if (number > tasks.size || number < 1) {
            throw new IllegalInputException();
        } else {
            tasks.getTask(number).setMarked(true);
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
                    markTask(tasks.size,true);
                } catch (IllegalInputException e) {
                    System.out.println("Corrupted data has been loaded, please check task number: " + tasks.size);
                }
            }
            count++;
        }
        if (tasks.size == 0) {
            System.out.println("No saved data found");
        } else {
            System.out.println("***" + tasks.size + " out of " + (count-1) +
                    " lines of data are valid and fetched accurately." + "***");
        }
    }

    public void deleteTask(int number) throws IllegalInputException {
        greet.printDecoration();
        if(number > tasks.size || number < 1){
            throw new IllegalInputException();
        } else {
            System.out.println("The following task has been shifted to the rubbish bin");
            printTask(number);
            System.out.println("You can say \"bin\" to view deleted items.");
            bin.add(tasks.getTask(number), true);
            tasks.delete(number);
        }
        greet.printDecoration();
    }

    public void printTasks() {
        greet.printDecoration();
        if (tasks.size == 0) {
            System.out.println("You have not added any Tasks!");
        } else {
            for (int i = 1; i <= tasks.size ; i++) {
                System.out.print(i + ". ");
                System.out.println(tasks.getTask(i));
            }
        }
        greet.printDecoration();
    }

    public void printDeletedTasks() {
        greet.printDecoration();
        if (bin.size == 0) {
            System.out.println("There are no items in the rubbish bin right now");
        } else {
            System.out.println("Rubbish Bin:");
            for (int i = 1; i <= bin.size; i++) {
                System.out.println( (i) + ". " + bin.getTask(i));
            }
        }
        greet.printDecoration();
    }
}

