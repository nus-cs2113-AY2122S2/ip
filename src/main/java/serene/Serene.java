package serene;

import serene.global.Constant;
import serene.global.Ui;
import serene.operation.Parser;
import serene.operation.Storage;
import serene.operation.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Serene {
    public static final String SAVE_FILE_PATH = "data/serene.txt";
    private static int statusOfSerene = Constant.CONTINUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        try {
            initiateSerene(taskList);
        } catch (IOException e) {
            System.out.println(Ui.IO_FAIL_MESSAGE);
            Ui.printExitMessage();
            return;
        }
        Ui.printWelcomeMessage();
        operateSerene(in, taskList);
        Ui.printExitMessage();
    }

    private static void initiateSerene(TaskList taskList) throws IOException {
        File dataDirectory = new File("data");
        if (dataDirectory.mkdir()) {
            System.out.println("Data directory created~");
        }
        File save = new File(SAVE_FILE_PATH);
        if (save.createNewFile()) {
            System.out.println("Save file created~");
            return;
        }
        try {
            Storage.readSavedContents(save, taskList.getTaskList(), taskList.getTaskCount());
        } catch (FileNotFoundException e) {
            System.out.println(Ui.IO_FAIL_MESSAGE);
        }
    }

    private static void operateSerene(Scanner in, TaskList taskList) {
        while (statusOfSerene != Constant.DONE) {
            String userInput = in.nextLine();
            statusOfSerene = Parser.parseInput(userInput, taskList.getTaskList());
        }
    }

}