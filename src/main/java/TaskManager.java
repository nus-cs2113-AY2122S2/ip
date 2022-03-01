import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static ArrayList<Task> tasks = new ArrayList<>();
    private int tasksCount = 0;

    public void addTask(String typeOfTask, String userInput) {

        try {
            String[] inputs = userInput.split(" ");
            String task = inputs[1];

            if (typeOfTask.equalsIgnoreCase(TODO_COMMAND)) {
                tasks.add(new ToDo(userInput.substring(userInput.indexOf(" ") + 1)));
                System.out.println(" Got it. I've added this task: ");
                System.out.println("   " + tasks.get(tasksCount));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list");
                tasksCount++;
            }
            else if (typeOfTask.equalsIgnoreCase(DEADLINE_COMMAND)) {
                tasks.add(new Deadline(userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/by")), userInput.substring(userInput.indexOf("/by") + 4)));
                System.out.println(" Got it. I've added this task: ");
                System.out.println("   " + tasks.get(tasksCount));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list");
                tasksCount++;
            }
            else if (typeOfTask.equalsIgnoreCase(EVENT_COMMAND)) {
                tasks.add(new Event(userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/at")), userInput.substring(userInput.indexOf("/at") + 4)));
                System.out.println(" Got it. I've added this task: ");
                System.out.println("   " + tasks.get(tasksCount));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list");
                tasksCount++;
            }
            else {
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(" ☹ OOPS!!! The description of a task cannot be empty.");
        }

    }

    public void markAsDone(int taskIndex) {
        if (tasks.get(taskIndex).getStatus() == false) {
            tasks.get(taskIndex).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex));
        }
        else {
            System.out.println(" ☹ OOPS!! This task has already been marked as done:");
            System.out.println("   " + tasks.get(taskIndex));
        }
    }

    public void markAsNotDone(int taskIndex) {
        if (tasks.get(taskIndex).getStatus() == true) {
            tasks.get(taskIndex).markAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskIndex));
        }
        else {
            System.out.println(" ☹ OOPS!! This task has not been marked as done:");
            System.out.println("   " + tasks.get(taskIndex));
        }
    }

    public void listTasks() {

        int taskIndex = 0;
        System.out.println(" Here are the tasks in your list:");
        for (Task task : tasks) {
            taskIndex++;
            System.out.println(" " + (taskIndex) + ". " + task);
        }
    }

    public void deleteTask(int taskIndex) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + tasks.get(taskIndex));
        tasks.remove(taskIndex);
        tasksCount--;
        System.out.println(" Now you have " + tasks.size() + " tasks in the list");
    }

    public void saveData() {
        try {
            File dataFile = new File("Duke.txt");
            dataFile.createNewFile();
            FileOutputStream oFile = new FileOutputStream(dataFile, false);
            FileWriter dataWriter =  new FileWriter(dataFile);
            for (Task task : tasks) {
                dataWriter.write(task.toString() + System.lineSeparator());
            }
            dataWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readDataStatus(int taskIndex, char status) {
        if (status == 'X') {
            tasks.get(taskIndex).markAsDone();
        }
    }

    public void readData() {
        try {
            File dataFile = new File("Duke.txt");
            Scanner dataReader = new Scanner(dataFile);
            while (dataReader.hasNextLine()) {
                String data = dataReader.nextLine();
                String typeOfTask = data.substring(data.indexOf("[") + 1, data.indexOf("]"));
                char taskStatus = data.charAt(4);
                switch (typeOfTask) {
                case "T":
                    tasks.add(new ToDo(data.substring(7)));
                    break;
                case "D":
                    tasks.add(new Deadline(data.substring(7, data.indexOf("(by")), data.substring(data.indexOf("(by") + 5, data.indexOf(")"))));
                    break;
                case "E":
                    tasks.add(new Event(data.substring(7, data.indexOf("(at")),data.substring(data.indexOf("(at") + 5, data.indexOf(")"))));
                    break;
                }
                readDataStatus(tasksCount, taskStatus);
                tasksCount++;
            }
        } catch (FileNotFoundException e) {
        }
    }
}
