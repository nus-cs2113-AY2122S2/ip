package bot;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * StorageManager class provides convenience methods for working with the computer's file system.
 *
 * This is required to store and load the list of tasks kept by the bot.
 */
public class StorageManager {
    /**
     * The directory to save the database file.
     */
    private static final Path SAVE_PATH = Path.of("data", "sailfish.txt");

    /**
     * Ensure any directories and files associated with storing the database exists.
     *
     * @throws IOException Any reading/writing error.
     */
    private static void ensureDatabaseExists() throws IOException {
        // Ensure that the directory actually exists.
        Files.createDirectories(SAVE_PATH.getParent());

        // Attempt to create the actual database file.
        try {
            Files.createFile(SAVE_PATH);
        } catch (FileAlreadyExistsException ignored) {
        }
    }

    /**
     * Loads database. If no current database exists, then it creates a new one.
     *
     * @throws IOException Any reading/writing error.
     */
    protected static void loadData(List<Task> taskList) throws IOException {
        ensureDatabaseExists();

        // Read contents of file.
        List<String> lines = Files.readAllLines(SAVE_PATH);
        for (String line : lines) {
            // Split the line by delimiter.
            String[] parts = Arrays.stream(line.split("\\|")).map(String::trim).toArray(String[]::new);
            try {
                if (parts.length < 1) {
                    throw new IllegalArgumentException();
                }
                Task t;
                switch (parts[0]) {
                case "T":
                    t = Todo.unMarshal(parts);
                    break;
                case "D":
                    t = Deadline.unMarshal(parts);
                    break;
                case "E":
                    t = Event.unMarshal(parts);
                    break;
                default:
                    throw new IndexOutOfBoundsException();
                }
                taskList.add(t);
            } catch (Exception e) {
                System.out.printf("Suspicious entry found: [%s]. Skipping!\n", line);
            }
        }
    }

    /**
     * Saves tasks into database file.
     *
     * @throws IOException Error saving data.
     */
    protected static void saveData(List<Task> taskList) throws IOException {
        ensureDatabaseExists();

        StringBuilder builder = new StringBuilder();
        for (Task t : taskList) {
            builder.append(t.marshal());
            builder.append("\n");
        }
        Files.write(SAVE_PATH, builder.toString().getBytes());
    }
}
