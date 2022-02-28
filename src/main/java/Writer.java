import java.io.File;
import java.io.FileWriter;

public class Writer {
    private static final String PATH = "./data/";
    private static final String FILE = "duke.txt";

    public Writer() {
        try {
            File dir = new File(PATH);
            dir.mkdir();
            File file = new File(PATH + "/" + FILE);
            file.createNewFile();
        } catch (Exception e) { //Future task: exception
            Ui.printError(e);
        }
    }

    public void writeFile(String str) {
        try {
            FileWriter fileWriter = new FileWriter(PATH + "/" + FILE, false);
            fileWriter.write(str);
            fileWriter.close();
        } catch (Exception e) { //Future task: exception
            Ui.printError(e);
        }
    }
}
