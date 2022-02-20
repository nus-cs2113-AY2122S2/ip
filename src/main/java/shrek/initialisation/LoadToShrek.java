package shrek.initialisation;

import shrek.constant.Indexes;
import shrek.data.CurrentDirectory;
import shrek.commands.HandleCommands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import shrek.data.ListOfTasks;

public class LoadToShrek {
    public static void loadData() throws FileNotFoundException {
        File outputFile = new File(CurrentDirectory.OUTPUT_FILE_PATH);
        Scanner lineScanner = new Scanner(outputFile);
        while (lineScanner.hasNext()) {
            String[] splitMarkAndContents = lineScanner.nextLine().split(" ", Indexes.NUMBER_OF_TERMS_IN_SPLIT);
            HandleCommands.handleInput(splitMarkAndContents[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING], false);
            if (splitMarkAndContents[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING].equals("marked")) {
                ListOfTasks.lists.get(ListOfTasks.lists.size() + Indexes.LIST_INDEX_CORRECTION).setMark();
            }
        }
    }
}
