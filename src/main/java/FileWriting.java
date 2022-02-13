import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {
    public static void writeToFile(String filePath, String string) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(string);
        fw.close();
    }

}
