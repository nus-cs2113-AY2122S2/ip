import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile(){
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Initialising...");
        File file = new File(filePath);
        int numTasks = 0;
        try {
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\n");
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] details = task.split(",", 0);
                switch (details[0]) {
                case "T":
                    tasks.add(new Todo(details[2]));
                    if (Objects.equals(details[1], "1")) {
                        tasks.get(numTasks).setDone(true);
                    }
                    numTasks++;
                    break;
                case "D":
                    tasks.add(new Deadline(details[2], details[3]));
                    if (Objects.equals(details[1], "1")) {
                        tasks.get(numTasks).setDone(true);
                    }
                    numTasks++;
                    break;
                case "E":
                    tasks.add(new Event(details[2], details[3], details[4]));
                    if (Objects.equals(details[1], "1")) {
                        tasks.get(numTasks).setDone(true);
                    }
                    numTasks++;
                    break;
                }
            }
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("No saved file found!");
        }
        System.out.println("______________________________________");

        return tasks;
    }

    public void saveFile(TaskList tasks){
        String toFile = "";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                toFile += tasks.getTask(i).getString() + "\n";
            }
            writer.write(toFile);
            writer.close();
            System.out.println("Saved!");
            System.out.println("______________________________________");
        } catch (Exception e) {
            System.out.println("An error has occurred when saving!");
        }
    }
}
