package brave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class TaskManager {
    //    public static final int MAX_TASK = 100;
    public static final String LINE_SPLIT = "\t____________________________________________________________";
    private final ArrayList<Task> tasks = new ArrayList<>();
//    private final Task[] tasks = new Task[MAX_TASK];
//    private int tasksCount = 0;

    public TaskManager() {
    }


    public void initialiseTasks(String filePath) throws FileNotFoundException {
        System.out.println("initialising task!");
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int taskIndex = -1;
        while (s.hasNext()) {
            taskIndex++;
            Task selected_task;
            String[] details = s.nextLine().split(" , ", 0);
            switch (details[0]) {
            case "T":
                tasks.add(new Todo(details[2]));
                if (details[1].equals("1")) {
                    selected_task = tasks.get(taskIndex);
                    selected_task.markAsDone();
                }
                break;
            case "D":
                tasks.add(new Deadline(details[2], details[3]));
                if (details[1].equals("1")) {
                    selected_task = tasks.get(taskIndex);
                    selected_task.markAsDone();
                }
                break;
            case "E":
                tasks.add(new Event(details[2], details[3]));
                if (details[1].equals("1")) {
                    selected_task = tasks.get(taskIndex);
                    selected_task.markAsDone();
                }
                break;
            }
        }
        System.out.println("done!");
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(LINE_SPLIT);
        System.out.println("\tYepp, I have added task below!");
        System.out.println("\t" + task.description);
        System.out.println("\tYou currenly have " + tasks.size() + " task in the list");
        System.out.println(LINE_SPLIT);
    }

    public void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("You currently have no task!");
            return;
        }
        System.out.println(LINE_SPLIT);
        for (Task task : tasks) {
            System.out.println(String.format("\t%d.%s %s", tasks.indexOf(task) + 1, task.getStatusIcon(), task.getDescription()));
        }
        System.out.println(LINE_SPLIT);
    }

    public void saveTask(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String toFile = "";
            for (Task task : tasks) {
                toFile += task.getSaveFormat() + "\n";
            }
            fw.write(toFile);
            fw.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void markTask(int taskIndex) {
        Task selected_task = tasks.get(taskIndex);
        selected_task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(selected_task.getStatusIcon() + " " + selected_task.getDescription());
    }

    public void unmarkTask(int taskIndex) {
        Task selected_task = tasks.get(taskIndex);
        selected_task.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(selected_task.getStatusIcon() + " " + selected_task.getDescription());
    }

    public void deleteTask(int taskIndex) {
        Task selected_task = tasks.get(taskIndex);
        System.out.println(LINE_SPLIT);
        System.out.println("Successfully deleted task below");
        System.out.println(String.format("\t%d.%s %s", taskIndex + 1, selected_task.getStatusIcon(), selected_task.getDescription()));
        tasks.remove(taskIndex);
        System.out.println("You now have " + tasks.size() + " task remaining");
    }

    public void showWelcomeMessage() {
        System.out.println(LINE_SPLIT);
        System.out.println("\tHello I'm Brave");
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE_SPLIT);
    }

    public void showFarewellMessage() {
        System.out.println(LINE_SPLIT);
        System.out.println("\tBye, Hope to see you again soon!");
        System.out.println(LINE_SPLIT);
    }
}
