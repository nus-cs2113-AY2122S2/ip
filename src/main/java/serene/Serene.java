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
        TaskList taskList = new TaskList();
        statusOfSerene = initiateSerene(taskList);
        if (statusOfSerene == Constant.DONE) {
            return;
        }
        Ui.printWelcomeMessage();
        operateSerene(taskList);
        Ui.printExitMessage();
    }

    private static int initiateSerene(TaskList taskList) {
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
            Storage.readSavedContents(save, taskList.getTaskList(), taskList.getTaskCount());
        } catch (IOException e) {
            System.out.println(Ui.IO_FAIL_MESSAGE);
            return Constant.DONE;
        }
        return Constant.CONTINUE;
    }

    private static void operateSerene(TaskList taskList) {
        Scanner in = new Scanner(System.in);
        while (statusOfSerene != Constant.DONE) {
            String userInput = in.nextLine();
            statusOfSerene = Parser.parseInput(userInput, taskList.getTaskList());
        }
    }

}