package duke;

import duke.exception.DukeException;
import task.Deadlines;
import task.Events;
import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "duke.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * The constructor of storage
     * @throws DukeException
     */

    public Storage() throws DukeException {
        try {
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }

            File dataFile = new File(filePath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File ERROR");
        }
    }

    /**
     * A method that will save the taskList into the duke.txt
     * @param tasks duke's taskList
     * @throws DukeException
     */
    public void saveTaskList(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            int amount = tasks.size();
            fw.write(String.format("%d\n", amount));
            for(int i = 1; i <= amount; i++) {
                Task currTask = tasks.getTask(i);
                if(currTask instanceof Deadlines) {
                    fw.write(String.format("%d. Deadline:\n", i));
                    fw.write(currTask.getDescription() + "\n");
                    fw.write(String.format("[%s]\n", currTask.getStatusIcon()));
                    fw.write(((Deadlines) currTask).getBy() + "\n");
                }
                else if(currTask instanceof Events) {
                    fw.write(String.format("%d. Event:\n", i));
                    fw.write(currTask.getDescription() + "\n");
                    fw.write(String.format("[%s]\n", currTask.getStatusIcon()));
                    fw.write(((Events) currTask).getDuration() + "\n");
                }
                else {
                    fw.write(String.format("%d. Todo:\n", i));
                    fw.write(currTask.getDescription() + "\n");
                    fw.write(String.format("[%s]\n", currTask.getStatusIcon()));
                    fw.write(" \n");
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot save task list to file: " + filePath.toString());
        }
    }

    /**
     * A method that will load duke.txt and store the taskList into the current duke's taskList
     * @return The taskList of the duke.txt
     * @throws DukeException
     */
    public TaskList loadTaskList() throws DukeException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            TaskList result = new TaskList();
            if(!scanner.hasNext()) {
                return result;
            }
            int n = scanner.nextInt();
            scanner.nextLine();//read enter
            String data;
            boolean isDone;
            String taskTime;

            for(int i = 0; i < n; i++) {
                data = scanner.nextLine();
                if(data.contains("Deadline")) {
                    String description = scanner.nextLine();
                    if(scanner.nextLine().charAt(1) == 'X') {
                        isDone = true;
                    }
                    else {
                        isDone = false;
                    }
                    taskTime = scanner.nextLine();
                    Deadlines deadline = new Deadlines(description, taskTime, isDone);
                    result.add(deadline);
                }
                else if(data.contains("Event")) {
                    String description = scanner.nextLine();
                    if(scanner.nextLine().charAt(1) == 'X') {
                        isDone = true;
                    }
                    else {
                        isDone = false;
                    }
                    taskTime = scanner.nextLine();
                    Events event = new Events(description, taskTime, isDone);
                    result.add(event);
                }
                else if(data.contains("Todo")) {
                    String description = scanner.nextLine();
                    if(scanner.nextLine().charAt(1) == 'X') {
                        isDone = true;
                    }
                    else {
                        isDone = false;
                    }
                    taskTime = scanner.nextLine();
                    Task todo = new Task(description, isDone);
                    result.add(todo);
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot load task list from file: " + filePath.toString());
        }
    }
}
