import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String filePath = "data/duke.txt";

    public static void checkFile() {
        File f = new File(filePath);
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(UI.ERROR_FAILED_TO_CREATE_FILE);
            }
        }
        System.out.println("full path: " + f.getAbsolutePath());
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void saveToFile(ArrayList<String> instructionsList) {
        String task;
        ArrayList<String> list = new ArrayList<>();
        try {
            for (int i = 1; i <= instructionsList.size(); i++) {
                task = i + ". " + instructionsList.get(i - 1);
                list.add(task);
            }
            writeToFile(filePath, String.valueOf(instructionsList));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadFromFile(ArrayList<String> instructionsList) throws IOException {

        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner scan = new Scanner(f); // create a Scanner using the File as the source
            String[] array;
            while (scan.hasNextLine()) {
                String instructions = scan.nextLine();
                instructions = instructions.replace("[", "");
                instructions = instructions.replace("]", "");
                array = instructions.split(", ", 200);
                for (int i = 0; i < array.length; i++) {
                    instructionsList.add("");
                    instructionsList.set(i, array[i]);
                    Task.number++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
