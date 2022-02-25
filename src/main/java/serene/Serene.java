package serene;

import serene.global.Constant;
import serene.global.Ui;
import serene.operation.Parser;
import serene.operation.Storage;
import serene.operation.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Serene {
    public static final String SAVE_FILE_PATH = "data/serene.txt";
    private static int statusOfSerene = Constant.CONTINUE;

    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        statusOfSerene = initiateSerene(tasks);
        if (statusOfSerene == Constant.DONE) {
            return;
        }
        Ui.printWelcomeMessage();
        operateSerene(tasks);
        Ui.printExitMessage();
    }

    /**
     * Creates the save file and restores tasks saved in it.
     *
     * @param tasks The task list containing the user's tasks.
     * @return Constant.DONE if IO from the save file fails, Constant.CONTINUE otherwise
     */
    private static int initiateSerene(TaskList tasks) {
        try {
            File dataDirectory = new File("data");
            if (dataDirectory.mkdir()) {
                System.out.println("Data directory created~");
            }
            File save = new File(SAVE_FILE_PATH);
            if (save.createNewFile()) {
                System.out.println("Save file created~");
                // Return as there will be no data to read from
                return Constant.CONTINUE;
            }
            Storage.readSavedContents(save, tasks);
        } catch (IOException e) {
            System.out.println(Ui.IO_FAIL_MESSAGE);
            return Constant.DONE;
        }
        return Constant.CONTINUE;
    }

    /**
     * Repeatedly takes in the user's input for the program to parse.
     *
     * @param tasks The task list containing the user's tasks
     */
    private static void operateSerene(TaskList tasks) {
        Scanner in = new Scanner(System.in);
        while (statusOfSerene != Constant.DONE) {
            String userInput = in.nextLine();
            statusOfSerene = Parser.parseInput(userInput, tasks);
        }
    }

}