import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "list.txt";

    public void getFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        String line;
        String desc;
        String time;
        int separator;
        while (s.hasNext()) {
            line = s.nextLine();
            boolean isDone = (line.charAt(2) == 'T' ? true : false);
            separator = (line.indexOf("/") == -1) ? line.length() : line.indexOf("/") + 1;
            desc = line.substring(4, separator - 1);
            time = (separator == line.length()) ? "" : line.substring(separator);
            Command.addTask(desc, isDone, line.charAt(0), time);
        }
    }

    public void storeFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            fw.write(TaskList.tasks.get(i).typeOfTask() + " " + TaskList.tasks.get(i).getIsDone() + " " + TaskList.tasks.get(i).getDesc() + "/"
                    + TaskList.tasks.get(i).getTime() + System.lineSeparator());
        }
        fw.close();
    }

    public void loadTaskList() throws IOException {
        File file = new File(FILE_PATH);

        if (file.exists()) {
            getFile(file);
        } else {
            file.createNewFile();
        }
    }
}
