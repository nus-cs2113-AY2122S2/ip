import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    public static void saveFile (ArrayList<String> instructionList) {
        String task;
        ArrayList<String> list = new ArrayList<>();
        try {
            for (int i = 1; i <= instructionList.size(); i++) {
                task = i + ". " + instructionList.get(i - 1);
                list.add(task);
            }
            writeToFile(filePath, String.valueOf(list));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
