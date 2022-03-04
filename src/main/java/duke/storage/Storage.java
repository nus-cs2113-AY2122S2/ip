package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    /**
     * Constructor of the Storage class
     *
     * @return an instance of Storage
     */
    public Storage() {

    }

    /**
     * Creates a list from an input file, and adds all items in the list
     * to a given TaskList object
     *
     * @param fileString The file path to the file to be read from
     * @param toDos The TaskList to add all tasks to
     * @param taskCounter The current number of tasks in the list
     * @return The number of tasks at the end of creation
     * @throws IOException If file is not found
     * @throws StringIndexOutOfBoundsException If the method attempts to access an index out of bounds of the TaskList
     */
    public static int listCreate(String fileString, TaskList toDos, int taskCounter) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileString));
            for (String line; (line = reader.readLine()) != null; ) {
                if (line.charAt(1) == 'T') {
                    toDos.add(new ToDo(line.substring(6)));
                    if (line.charAt(4) == 'X') {
                        toDos.get(taskCounter).setDone(true);
                    }
                    taskCounter++;
                } else if (line.charAt(1) == 'D') {
                    toDos.add(new Deadline(line.substring(6, line.indexOf('(')),
                            line.substring(line.indexOf('(') + 5, line.length() - 1)));
                    if (line.charAt(4) == 'X') {
                        toDos.get(taskCounter).setDone(true);
                    }
                    taskCounter++;
                } else if (line.charAt(1) == 'E') {
                    toDos.add(new Event(line.substring(6, line.indexOf('(')),
                            line.substring(line.indexOf('(') + 5, line.length() - 1)));
                    if (line.charAt(4) == 'X') {
                        toDos.get(taskCounter).setDone(true);
                    }
                    taskCounter++;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("list creation: File not found");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("string out of bounds");
            return taskCounter;
        }

        return taskCounter;
    }

    /**
     * Writes all tasks in a TaskList to a file
     *
     * @param fileString The path to the file to write tasks to
     * @param toDos The TaskList where files are read from to write to the file
     */
    public static void fileWrite(String fileString, TaskList toDos) {
        try {
            new FileWriter(fileString, false).close();
            FileWriter myWriter = new FileWriter(fileString);
            for (int i = 0; i < toDos.taskCounter; i++) {
                myWriter.write(toDos.get(i).getStatusIcon() + toDos.get(i).getDescription() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }


}
