package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static duke.Duke.*;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void loadTasks() {
        String pathName = "./data/";
        String fileName = "duke.txt";
        File file = new File(pathName + fileName);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitLine = line.split(" \\| ");
                String type = splitLine[0];
                boolean done = Boolean.parseBoolean(splitLine[1]);
                String description = splitLine[2];
                String date;
                types.add(type);
                dones.add(done);
                tasks.add(description);
                if (!type.equals("T")) {
                    date = splitLine[3];
                } else {
                    date = "";
                }
                dates.add(date);
            }
        } catch (FileNotFoundException e) {
            createFileOrFolder(pathName, fileName);
        }
    }
    /**
     *
     * @param pathName
     * @param fileName
     */
    public void createFileOrFolder(String pathName, String fileName) {
        try {
            Path path = Paths.get(pathName);
            Files.createDirectory(path);
            Path file = Paths.get(pathName + fileName);
            Files.createFile(file);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void saveTasks() {
        String pathName = "./data/";
        String fileName = "duke.txt";
        String separator = " | ";
        flushFile(pathName, fileName);
        for (int i = 0; i < tasks.size(); i++) {
            StringBuilder sb = new StringBuilder();
            String description = tasks.get(i);
            boolean done = dones.get(i);
            String type = types.get(i);
            String date = dates.get(i);
            sb.append(type);
            sb.append(separator);
            sb.append(done);
            sb.append(separator);
            sb.append(description);
            if (!date.equals("")) {
                sb.append(separator);
                sb.append(date);
            }
            String textToAppend = sb.toString();
            try {
                appendToFile(pathName + fileName, textToAppend + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     *
     * @param pathName
     * @param fileName
     */
    public void flushFile(String pathName, String fileName) {
        File file = new File(pathName + fileName);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     *
     * @param textToAppend
     * @param filePath
     * @throws IOException
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

}
