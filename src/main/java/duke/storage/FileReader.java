package duke.storage;

import duke.parser.FileParser;
import duke.exception.AdditionalException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader extends Storage {

    public static void retrieveTasks() {
        try {
            readFromFile();
        } catch (FileNotFoundException error) {
            System.out.println("Duke.txt doesn't exist so I'M GOING TO CREATE ONE FOR YOU");
        }
    }

    private static void readFromFile() throws FileNotFoundException {
        int taskNumber = 1;
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            readAndFilterRequest(nextLine, taskNumber);
            taskNumber++;
        }
    }

    private static void readAndFilterRequest(String nextLine, int taskNumber) {
        try {
            FileParser.filterRequestsFromFile(nextLine, taskNumber);
        } catch(AdditionalException error) {
            System.out.println("Error transferring from file to tasks");
        }
    }

}
