import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String TEXT_FILE_PATH = "./data.txt";
    private final TaskList taskList = new TaskList();

    /**
     * Loads data from the txt file and puts the task in the taskList
     * @return TaskList object with taskList containing the saved tasks
     */
    public TaskList loadData() {
        try{
            File data = new File(TEXT_FILE_PATH);
            Scanner line = new Scanner(data);
            String task, description, isDone;
            while (line.hasNext()) {
                task = line.nextLine();
                isDone = task.substring(4, 5);
                description = task.substring(7);
                if (task.startsWith("[T]")) {
                    ToDo toDo = new ToDo(description);
                    toDo.setDone(isDone.equals("X"));
                    taskList.addTask(toDo);
                } else if (task.startsWith("[D]")) {
                    int separation = description.indexOf("(");
                    int dueDateEnd = description.indexOf(")");
                    String dueDate = description.substring(separation + 1, dueDateEnd);
                    description = description.substring(0, separation - 6);
                    Deadline deadline = new Deadline(description, dueDate);
                    deadline.setDone(isDone.equals("X"));
                    taskList.addTask(deadline);
                } else if (task.startsWith("[E]")) {
                    int separation = description.indexOf("(");
                    int timingEnd = description.indexOf(")");
                    String timing = description.substring(separation + 1, timingEnd);
                    description = description.substring(0, separation - 5);
                    Event event = new Event(description, timing);
                    event.setDone(isDone.equals("X"));
                    taskList.addTask(event);
                }
            }
            line.close();
        } catch (FileNotFoundException e) {
            // Do nothing since it will write new file.
        }
        return taskList;
    }

    /**
     * Write the tasks to the txt file
     * @param taskList Array List of Tasks
     */
    public void writeData(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(TEXT_FILE_PATH);
            fileWriter.write("");
            ArrayList<Task> tasks = taskList.getTasks();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fileWriter.write(task.toString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to write data");
        }
    }
}
