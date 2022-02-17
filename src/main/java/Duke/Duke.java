package Duke;

import Duke.Helper.DeleteTask;
import Duke.Helper.DisplayMessages;
import Duke.Helper.DukeException;
import Duke.Helper.AddTask;

import Duke.Helper.MarkUnmarkItem;
import Duke.Tasks.Task;
import Duke.Helper.FileCommand;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> listArray = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String DISPLAY_LINE= "________________________________________________\n";

    public static void exit() {
        try {
            FileCommand.saveFile(listArray);
            System.out.println("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

<<<<<<< HEAD
    public static void listItems () throws DukeException {
        if (listArray.size() == 0) {
            throw new DukeException();
        } else {
            System.out.println(DISPLAY_LINE + "Here are the tasks in your list: ");
            for (int i = 0; i < listArray.size(); i++) {
=======
    public static void listItems () throws DukeException {
        if (listArray.size() == 0) {
            throw new DukeException();
        } else {
            System.out.println(DISPLAY_LINE + "Here are the tasks in your list: ");
            for (int i = 0; i < listArray.size(); i++) {
>>>>>>> branch-Level-7
                System.out.print(i + 1 + ". ");
                System.out.println(listArray.get(i));
            }
            System.out.print(DISPLAY_LINE);
        }
    }


    public static void main(String[] args) {
<<<<<<< HEAD
        DisplayMessages.displayGreetMessage();
        Scanner in = new Scanner(System.in);
        boolean isLoop = true;
        while (isLoop) {
            String message = in.nextLine();
            String messageLowerCase = message.toLowerCase();
            if (messageLowerCase.equals("bye")) {
                exit();
                isLoop = false;
            } else if (messageLowerCase.equals("list")) {
                try {
                    listItems();
                } catch (DukeException e) {
                    DisplayMessages.displayListMessage();
                }
            } else if (messageLowerCase.contains("unmark")) {
                try {
                    MarkUnmarkItem.unMarkItem(listArray, message);
                } catch (IndexOutOfBoundsException e) {
                    DisplayMessages.displayUnmarkMessage(listArray);
                }
            } else if (messageLowerCase.contains("mark")) {
                try {
                    MarkUnmarkItem.markItem(listArray, message);
                } catch (IndexOutOfBoundsException e) {
                    DisplayMessages.displayMarkMessage(listArray);
                }
            } else if (messageLowerCase.contains("todo")) {
                try {
                    AddTask.addTodo(listArray, message);
                } catch (IndexOutOfBoundsException e) {
                    DisplayMessages.displayTodoMessage();
                } catch (DukeException e) {
                    DisplayMessages.displayTodoMessage();
                }
            } else if (messageLowerCase.contains("deadline")) {
                try {
                    AddTask.addDeadline(listArray, message);
                } catch (ArrayIndexOutOfBoundsException e) {
                    DisplayMessages.displayDeadlineMessage();
                } catch (DukeException e) {
                    DisplayMessages.displayDeadlineMessage();
                }
            } else if (messageLowerCase.contains("event")) {
                try {
                    AddTask.addEvent(listArray, message);
                } catch (ArrayIndexOutOfBoundsException e) {
                    DisplayMessages.displayEventMessage();
                } catch (DukeException e) {
                    DisplayMessages.displayEventMessage();
                }
            } else if (messageLowerCase.contains("delete")) {
                try {
                    DeleteTask.deleteTask(listArray, message);
                } catch (IndexOutOfBoundsException e) {
                    DisplayMessages.displayDeleteMessage(listArray);
                }
            } else {
                DisplayMessages.displayInvalidInputMessage();
=======
        try {
            DisplayMessages.displayGreetMessage();
            Scanner in = new Scanner(System.in);
            FileCommand.loadFile(listArray);
            FileWriter fw = new FileWriter("data/duke.txt", true);
            boolean isLoop = true;
            while (isLoop) {
                String message = in.nextLine();
                String messageLowerCase = message.toLowerCase();
                if (messageLowerCase.equals("bye")) {
                    exit();
                    isLoop = false;
                } else if (messageLowerCase.equals("list")) {
                    try {
                        listItems(listArray);
                    } catch (DukeException e) {
                        DisplayMessages.displayListMessage();
                    }
                } else if (messageLowerCase.contains("unmark")) {
                    try {
                        MarkUnmarkItem.unMarkItem(listArray, message);
                    } catch (IndexOutOfBoundsException e) {
                        DisplayMessages.displayUnmarkMessage();
                    }
                } else if (messageLowerCase.contains("mark")) {
                    try {
                        MarkUnmarkItem.markItem(listArray, message);
                    } catch (IndexOutOfBoundsException e) {
                        DisplayMessages.displayMarkMessage();
                    }
                } else if (messageLowerCase.contains("todo")) {
                    try {
                        AddTask.addTodo(listArray, message);
                    } catch (IndexOutOfBoundsException e) {
                        DisplayMessages.displayTodoMessage();
                    } catch (DukeException e) {
                        DisplayMessages.displayTodoMessage();
                    }
                } else if (messageLowerCase.contains("deadline")) {
                    try {
                        AddTask.addDeadline(listArray, message);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        DisplayMessages.displayDeadlineMessage();
                    } catch (DukeException e) {
                        DisplayMessages.displayDeadlineMessage();
                    }
                } else if (messageLowerCase.contains("event")) {
                    try {
                        AddTask.addEvent(listArray, message);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        DisplayMessages.displayEventMessage();
                    } catch (DukeException e) {
                        DisplayMessages.displayEventMessage();
                    }
                } else {
                    DisplayMessages.displayInvalidInputMessage();
                }
>>>>>>> branch-Level-7
            }
        } catch (IOException e) {
            System.out.println("IO exception here");
        }
    }
}
