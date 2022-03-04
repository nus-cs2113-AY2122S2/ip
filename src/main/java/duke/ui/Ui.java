package duke.ui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;
import duke.messages.Messages;
import duke.task.Task;
import duke.taskList.TaskList;


public class Ui {
    private static PrintStream out;
    private final Scanner in;

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * @param in
     * @param out
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * @return
     */
    public String getCommand() {
        String input = in.nextLine();
        return input.strip();
    }

    public void printIntro() {
        out.println(Messages.MESSAGE_WELCOME);
    }

    public void printBye() {
        out.println(Messages.MESSAGE_GOODBYE);
    }

    public void showLoadingError() {
        out.println(Messages.MESSAGE_LOADING_ERROR);
    }

    public void PrintStringIndexOutOfBoundsException() {
        out.println(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

    public static void printIndexOutOfBoundsException() {
        out.println(Messages.MESSAGE_INDEX_OUT_OF_BOUNDARY);
    }

    public static void printMarked() {
        out.println(Messages.MESSAGE_UNMARKED);
    }


    public static void printLine() {
        out.println(Messages.MESSAGE_BLANK_LINE);
    }

    public static void printTaskAdd() {
        out.println(Messages.MESSAGE_TASK_ADDED);
    }

    public static void printTaskRemove() {
        out.println(Messages.MESSAGE_TASK_REMOVE);
    }

    public static void printDisplayTask() {
        out.println(Messages.MESSAGE_DISPLAY_TASKS);
    }

    public static void printUnmarked() {
        out.println(Messages.MESSAGE_MARKED);
    }

    /**
     * @param filePath
     * @param filePath
     * @throws IOException
     */
    public static void printFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * @param taskList
     */
    public static void printTaskCount(TaskList taskList) {
        System.out.print("Now you have ");
        System.out.print(taskList.getSize());
        System.out.println(" tasks in the list");
    }


    /**
     * @param taskList
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        for (Task task: taskList) {
            System.out.println(task);
        }
    }


}