package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class DiskManager {
    private static final String PATH = "./tasks.txt";
    private static final File TASKS_TXT = new File(PATH);

    public static void diskInit() throws IOException {
        if (!TASKS_TXT.exists()) {
            try {
                TASKS_TXT.createNewFile();
            } catch (IOException exception) {
                throw exception;
            }
        }
        readDisk();
    }

    public static void readDisk() throws IOException {
        Scanner sc = new Scanner(TASKS_TXT);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.equals(System.lineSeparator())) {
                continue;
            }
            String[] token = line.split("\\|");
            String command_string = token[0].trim();
            String isDone_string = token[1].trim();
            String[] args = new Command(command_string).getCommandTokens();
            String[] command_mark = new String[2];
            command_mark[0] = "mark";
            try {
                switch (args[0]) {
                case "event":
                    TaskManager.addEvents(args, false);
                    markIfDone(isDone_string, command_mark);
                    break;
                case "deadline":
                    TaskManager.addDeadlines(args, false);
                    markIfDone(isDone_string, command_mark);
                    break;
                case "todo":
                    TaskManager.addToDoes(args, false);
                    markIfDone(isDone_string, command_mark);
                    break;
                }
            } catch (DukeException exception) {
                throw new IOException("when reading from file" + exception.getMessage());
            }
        }
        sc.close();
    }

    private static void markIfDone(String isDone_string, String[] command_mark) throws DukeException {
        if (isDone_string.equals("X")) {
            command_mark[1] = Integer.toString(TaskManager.getTaskNumber());
            TaskManager.mark(command_mark, false);
        }
    }

    public static void appendToDisk(Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(TASKS_TXT, true);
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] instanceof Deadline) {
                Deadline ddl = (Deadline) tasks[i];
                fw.write("deadline \"" + ddl.getContent() + "\" /by \"" + ddl.getDeadlineTime() + "\" | " + (ddl.isDone() ? "X" : "_") + System.lineSeparator());
            } else if (tasks[i] instanceof Event) {
                Event event = (Event) tasks[i];
                fw.write("event \"" + event.getContent() + "\" /at \"" + event.getSchedule() + "\" | " + (event.isDone() ? "X" : "_") + System.lineSeparator());
            } else if (tasks[i] instanceof ToDo) {
                ToDo todo = (ToDo) tasks[i];
                fw.write("todo \"" + todo.getContent() + "\" | " + (todo.isDone() ? "X" : "_") + System.lineSeparator());
            }
        }
        fw.close();
    }

    public static void syncWithDisk() throws IOException {
        emptyDisk();
        Task[] tasks = TaskManager.getCurrentTasks();
        appendToDisk(tasks);
    }

    private static void emptyDisk() throws IOException {
        FileWriter fw = new FileWriter(TASKS_TXT, false);
        fw.close();
    }
}
