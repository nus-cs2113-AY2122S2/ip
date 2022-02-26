package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Duke.UserInput.*;
public class Storage {
    protected static File f = new File(filePath);
    protected static File folder = new File("data");

    public static void initializeData() throws FileNotFoundException {
        userInput = new ArrayList<>();
        if (!folder.exists())
            folder.mkdir();
        if (f.exists())
            fileReading(filePath);
    }

    public static void fileReading(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            var str = s.nextLine();
            if (str.charAt(3) == 'T') {
                UserInput.userInput.add(new Todo(str.substring(11)));
                if (str.charAt(7) == 'X')
                    UserInput.userInput.get(inputCount).markAsDone();
            } else if (str.charAt(3) == 'D') {
                UserInput.userInput.add(new Deadline(str.substring(11, str.indexOf("(by: ")), str.substring(str.indexOf("(by: ") + 5,str.indexOf(")") )));
                if (str.charAt(7) == 'X')
                    UserInput.userInput.get(inputCount).markAsDone();
            } else if (str.charAt(3) == 'E') {
                UserInput.userInput.add(new Event(str.substring(11, str.indexOf("(at: ")), str.substring(str.indexOf("(at: ") + 5,str.indexOf(")") )));
                if (str.charAt(7) == 'X')
                    UserInput.userInput.get(inputCount).markAsDone();
            }
            inputCount++;
        }
    }
    public static void writeToFile() throws IOException {
        File tempFile = new File("data/tempdata.txt");
        boolean a = tempFile.createNewFile();
        FileWriting fileWriting = new FileWriting();
        try {
            for (int i = 0; i < inputCount; i++) {
                fileWriting.writeToFile("data/tempdata.txt", (i + 1) + ".[" + UserInput.userInput.get(i).getIcon() + "] " + "[" + UserInput.userInput.get(i).getStatusIcon() + "] " + UserInput.userInput.get(i).description + UserInput.userInput.get(i).showDate() + System.lineSeparator());
            }
            f.delete();
            tempFile.renameTo(f);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
