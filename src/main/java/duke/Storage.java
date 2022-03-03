package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    public Storage() {

    }

    public static int listCreate(String fileString, TaskList toDos, int taskCounter) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileString));
            for (String line; (line = reader.readLine()) != null; ) {
                if (line.charAt(1) == 'T') {
                    toDos.add(new ToDo(line.substring(6, line.length())));
                    if (line.charAt(4) == 'X') {
                        toDos.get(taskCounter).setDone(true);
                    }
                    taskCounter++;
                } else if (line.charAt(1) == 'D') {
                    String[] commands = line.split("by");
                    toDos.add(new Deadline(line.substring(6, line.indexOf('(')),
                            line.substring(line.indexOf('(') + 5, line.length() - 1)));
                    if (line.charAt(4) == 'X') {
                        toDos.get(taskCounter).setDone(true);
                    }
                    taskCounter++;
                } else if (line.charAt(1) == 'E') {
                    String[] commands = line.split("at");
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
