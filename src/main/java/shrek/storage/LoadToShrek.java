package shrek.storage;

import shrek.constant.Indexes;
import shrek.data.CurrentDirectory;
import shrek.commands.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import shrek.data.TaskList;

/**
 * Loads list form output file to Shrek.
 */
public class LoadToShrek {
    /**
     * Reads each line from the output file, passing the reconstructed user input to handleInput handler
     * and sets the mark status of the task.
     *
     * @throws FileNotFoundException If output file is not found.
     */
    public static void loadData() throws FileNotFoundException {
        File outputFile = new File(CurrentDirectory.OUTPUT_FILE_PATH);
        Scanner lineScanner = new Scanner(outputFile);
        while (lineScanner.hasNext()) {
            String[] splitMarkAndContents = lineScanner.nextLine().split(" ", Indexes.NUMBER_OF_TERMS_IN_SPLIT);
            Parser.handleInput(splitMarkAndContents[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING], false);
            if (splitMarkAndContents[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING].equals("marked")) {
                TaskList.lists.get(TaskList.lists.size() + Indexes.LIST_INDEX_CORRECTION).setMark();
            }
        }
    }
}
