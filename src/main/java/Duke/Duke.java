package Duke;

import Duke.Helper.DisplayMessages;
import Duke.Helper.DukeException;
import Duke.Helper.AddTask;
import Duke.Helper.FileCommand;
import Duke.Tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> listArray = new ArrayList<>();
    public static int itemNumber;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String DISPLAY_LINE= "________________________________________________\n";

//    public static void loadFile() throws IOException {
//        try {
//            File f = new File("data/duke.txt");
//            f.getParentFile().mkdirs();
//            f.createNewFile();
//            Scanner s = new Scanner(f);
//            while (s.hasNext()) {
//                String message = s.nextLine();
//                String[] splitMessage = message.split(" \\| ");
//                String type = splitMessage[0];
//                Integer status = Integer.parseInt(splitMessage[1]);
//                String description = splitMessage[2];
//                switch (type) {
//                case "T":
//                    listArray.add(new Todo(description, status));
//                    break;
//                case "E":
//                    String at = splitMessage[3];
//                    listArray.add(new Event(description, status, at));
//                    break;
//                case "D":
//                    String by = splitMessage[3];
//                    listArray.add(new Deadline(description, status, by));
//                    break;
//                default:
//                    break;
//                }
//            }
//            s.close();
//        } catch (IOException e) {
//            System.out.println("IO exception");
//        }
//    }
//
//    public static void saveFile() throws IOException {
//        try {
//            FileWriter fw = new FileWriter("data/duke.txt");
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < listArray.size(); i++) {
//                sb.append(listArray.get(i).saveTasks());
//            }
//            fw.write(sb.toString());
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("IO exception");
//        }
//    }

    public static void exit() {
        try {
            FileCommand.saveFile(listArray);
            System.out.println("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public static void listItems (List<Task> array) throws DukeException {
        if (array.size() == 0) {
            throw new DukeException();
        } else {
            System.out.println(DISPLAY_LINE + "Here are the tasks in your list: ");
            for (int i = 0; i < array.size(); i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(array.get(i));
            }
            System.out.print(DISPLAY_LINE);
        }
    }


    public static void main(String[] args) {
        try {
            itemNumber = 0;
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
                        DisplayMessages.displayUnmarkMessage(itemNumber);
                    }
                } else if (messageLowerCase.contains("mark")) {
                    try {
                        MarkUnmarkItem.markItem(listArray, message);
                    } catch (IndexOutOfBoundsException e) {
                        DisplayMessages.displayMarkMessage(itemNumber);
                    }
                } else if (messageLowerCase.contains("todo")) {
                    try {
                        AddTask.addTodo(listArray, message);
                        itemNumber++;
                    } catch (IndexOutOfBoundsException e) {
                        DisplayMessages.displayTodoMessage();
                    } catch (DukeException e) {
                        DisplayMessages.displayTodoMessage();
                    }
                } else if (messageLowerCase.contains("deadline")) {
                    try {
                        AddTask.addDeadline(listArray, message);
                        itemNumber++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        DisplayMessages.displayDeadlineMessage();
                    } catch (DukeException e) {
                        DisplayMessages.displayDeadlineMessage();
                    }
                } else if (messageLowerCase.contains("event")) {
                    try {
                        AddTask.addEvent(listArray, message);
                        itemNumber++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        DisplayMessages.displayEventMessage();
                    } catch (DukeException e) {
                        DisplayMessages.displayEventMessage();
                    }
                } else {
                    DisplayMessages.displayInvalidInputMessage();
                }
            }
        } catch (IOException e) {
            System.out.println("IO exception here");
        }
    }
}
