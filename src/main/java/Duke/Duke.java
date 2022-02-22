package Duke;

import Duke.Helper.*;
import Duke.Helper.DisplayMessageCommand;

import Duke.Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> listArray = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String DISPLAY_LINE = "----------------------------------------------------";

    public static void exit() {
        try {
            FileCommand.saveFile(listArray);
            System.out.println("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public static void listItems () throws DukeException {
        if (listArray.size() == 0) {
            throw new DukeException(DisplayMessageCommand.displayListMessage());
        } else {
            System.out.println(DISPLAY_LINE  + System.lineSeparator() + "Here are the tasks in your list: ");
            for (int i = 0; i < listArray.size(); i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(listArray.get(i));
            }
            System.out.println(DISPLAY_LINE);
        }
    }


    public static void main(String[] args) {
        try {
            DisplayMessageCommand.displayGreetMessage();
            Scanner in = new Scanner(System.in);
            FileCommand.loadFile(listArray);
            FileWriter fw = new FileWriter("data/duke.txt", true);
            boolean isLoop = true;
            while (isLoop) {
                String message = in.nextLine();
                String messageLowerCase = message.toLowerCase();
                try {
                    if (messageLowerCase.equals("bye")) {
                        exit();
                        isLoop = false;
                    } else if (messageLowerCase.equals("list")) {
                        listItems();
                    } else if (messageLowerCase.contains("unmark")) {
                        MarkUnmarkItem.unMarkItem(listArray, message);
                    } else if (messageLowerCase.contains("mark")) {
                        MarkUnmarkItem.markItem(listArray, message);
                    } else if (messageLowerCase.contains("todo")) {
                        AddTask.addTodo(listArray, message);
                    } else if (messageLowerCase.contains("deadline")) {
                        AddTask.addDeadline(listArray, message);
                    } else if (messageLowerCase.contains("event")) {
                        AddTask.addEvent(listArray, message);
                    } else if (messageLowerCase.contains("delete")) {
                        DeleteTask.deleteTask(listArray, message);
                    } else {
                        DisplayMessageCommand.displayInvalidInputMessage();
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
