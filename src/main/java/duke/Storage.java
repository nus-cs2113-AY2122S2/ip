package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    public static String boundary = "____________________________________________________________" + System.lineSeparator();

    // Create data directory and dukeData.txt if they don't exist.
    public Storage() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            try {
                Files.createDirectory(Paths.get("./data"));
            } catch (IOException e) {
                System.out.println("Hmm...I cannot create the data directory.");
            }
        }

        File dukeData = new File("./data/dukeData.txt");
        try {
            if (dukeData.createNewFile()) {
                System.out.println("Creating a new data file...");
            }
        } catch (IOException e) {
            System.out.println("Hmm... I cannot create the data file.");
        }
    }

    /* Write the given string to dukeData.txt.
     * @param textToWrite The String to be written into the file.
     * @throws IOException
     */
    public void writeToFile(String textToWrite) throws IOException {
        FileWriter fw = new FileWriter("./data/dukeData.txt"); // create a FileWriter in append mode
        fw.write(textToWrite);
        fw.close();
    }

    /* Add existing tasks from data file to the given task list.
     * @param tasks The task list to be written to.
     * @throws FileNotFoundException If the data file cannot be found.
     */
    public static void readToList(TaskList tasks) throws FileNotFoundException, ParseException {
        File f = new File("./data/dukeData.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            switch (currentLine.charAt(1)) {
            case 'T':
                tasks.addTodo(currentLine.substring(7));
                break;
            case 'D':
                int byIndex = currentLine.indexOf("(");
                String by = currentLine.substring(byIndex + 5, currentLine.length() - 1);
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                tasks.addDeadline(currentLine.substring(7, byIndex - 1), String.valueOf(LocalDate.parse(by, formatter)));
                break;
            case 'E':
                int atIndex = currentLine.indexOf("(");
                String at = currentLine.substring(atIndex + 5, currentLine.length() - 1);
                tasks.addEvent(currentLine.substring(7, atIndex - 1), at);
                break;
            default:
            }
            if (currentLine.charAt(4) == 'X') {
                tasks.getTask(tasks.countTask).markDone();
            }
            tasks.countTask += 1;
        }
    }

    /* Write tasks to the given file.
     * @param dataFile The data file to be written to.
     * @param tasks The task list that is read from.
     */
    public static void writeData(Storage dataFile, TaskList tasks) {
        StringBuilder toWrite = new StringBuilder();
        for (int i = 0; i < tasks.countTask; i++) {
            toWrite.append(tasks.getTask(i)).append(System.lineSeparator());
        }

        try {
            dataFile.writeToFile(toWrite.toString());
        } catch (IOException e) {
            System.out.print(boundary + "Hmm...I cannot write to the data file."
                                     + System.lineSeparator() + boundary);
        }
    }
}
