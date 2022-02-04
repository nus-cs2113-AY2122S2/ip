package bot;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SailfishManager {
    /**
     * The directory to save the database file.
     */
    private static final Path SAVE_PATH = Path.of("data", "sailfish.txt");

    /**
     * The list of tasks stored by the bot.
     */
    private final List<Task> tasks;

    /**
     * Creates a new Sailfish task manager.
     *
     * @throws IOException If error reading database.
     */
    protected SailfishManager() throws IOException {
        this.tasks = new ArrayList<>();
        // Load the database.
        this.loadData();
    }

    /**
     * Ensure any directories and files associated with storing the database exists.
     *
     * @throws IOException Any reading/writing error.
     */
    private void ensureDatabaseExists() throws IOException {
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
    private void loadData() throws IOException {
        this.ensureDatabaseExists();

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
                this.addTask(t);
            } catch (Exception e) {
                System.out.printf("Suspicious entry found: [%s]. Skipping!\n", line);
            }
        }
    }

    /**
     * Saves current tasks into database file.
     *
     * @throws IOException Error saving data.
     */
    protected void saveData() throws IOException {
        this.ensureDatabaseExists();

        StringBuilder builder = new StringBuilder();
        for (Task t : this.tasks) {
            builder.append(t.marshal());
            builder.append("\n");
        }
        Files.write(SAVE_PATH, builder.toString().getBytes());
    }

    /**
     * Helper method to get a task at a particular index from a parsed command.
     *
     * @param command Command object containing parsed information.
     * @return Task object.
     * @throws NumberFormatException For invalid index.
     */
    protected Task getTask(Command command) throws NumberFormatException {
        int index = Integer.parseInt(command.getDesc()) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new NumberFormatException("Please specify a integer for the index and range!");
        }
        return this.getTask(index);
    }

    /**
     * Get a task at a particular index. User must ensure that index is valid. Otherwise,
     * they must be ready to catch a possible IndexOutOfBoundsException.
     *
     * @param index Index of the task in the list.
     * @return Task object.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    protected Task getTask(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    /**
     * Add a new task into the list.
     *
     * @param task Task object to add.
     */
    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Get the number of current tasks stored by the bot.
     *
     * @return Number of tasks.
     */
    protected int getNumTasks() {
        return this.tasks.size();
    }
}
