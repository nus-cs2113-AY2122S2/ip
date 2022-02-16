package util.miscellaneous;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import util.task.Deadline;
import util.task.Event;
import util.task.Task;
import util.exception.NoDateException;
import util.exception.NoTaskException;
import util.exception.NoItemException;
import util.task.Todo;

public class DukeOperation implements Chatbot {
    public static void linePrinter() {
        System.out.print("\t");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printGreeting() {
        linePrinter();
        System.out.println("\t" + GREETING_MSG_01);
        System.out.println("\t" + GREETING_MSG_02);
        linePrinter();

    }

    public static void printList(ArrayList<Task> tasks) {
        linePrinter();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + " " + Integer.toString(i + 1) + " " + (tasks.get(i)).toString());
        }

        linePrinter();
    }

    public static void deleteItem(ArrayList<Task> tasks, int index) {
        linePrinter();
        System.out.println("\t" + DELETE_MSG);
        System.out.println("\t" + "   " + (tasks.get(index)).toString());
        linePrinter();

        tasks.remove(index);
    }

    public static void printMark(ArrayList<Task> tasks, int markedItem) {
        linePrinter();
        System.out.println("\t" + MARKED_MSG);
        System.out.println("\t" + "   " + (tasks.get(markedItem)).toString());
        linePrinter();

    }

    public static void printUnmark(ArrayList<Task> tasks, int unmarkedItem) {
        linePrinter();
        System.out.println("\t" + UNMARKED_MSG);
        System.out.println("\t" + "   " + (tasks.get(unmarkedItem)).toString());
        linePrinter();

    }

    public static void checkFilePath() throws IOException {
        File file = new File(FILEPATH);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    public static void saveToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(FILEPATH);
        for (int i = 0; i < tasks.size(); i++) {
            writer.write((tasks.get(i).toString()));
            writer.write(System.lineSeparator());
        }
        writer.close();
    }

    public static void saveData(ArrayList<Task> tasks) {
        try {
            checkFilePath();
            saveToFile(tasks);
        } catch (IOException e) {
            echo(IO_ERROR_MSG);
        }
    }

    public static void loadData(ArrayList<Task> tasks) {
        try {
            File file = new File(FILEPATH);
            Scanner input1 = new Scanner(file);
            String load;

            while (input1.hasNext()) {
                load = input1.nextLine();
                boolean needUpdateTaskStatus = false;
                boolean isLoadingData = true;

                if(load.indexOf(DONE_STATUS) == TASK_STATUS_INDEX) {
                    needUpdateTaskStatus = true;
                }

                if (load.startsWith(DEADLINE_TYPE)) {
                    String time = load.substring(load.lastIndexOf("(by: "));
                    load = ADD_DEADLINE_CMD + load.substring(TASK_DETAIL, load.indexOf("by ")) + DEADLINE_OF_TASK_CMD + " " + time;
                } else if (load.startsWith(EVENT_TYPE)) {
                    String time = load.substring(load.lastIndexOf("(by: "));
                    load = ADD_EVENT_CMD + load.substring(TASK_DETAIL, load.indexOf("by ")) + DURATION_OF_EVENT_CMD + " " + time;
                } else {
                    load = ADD_TODO_CMD + load.substring(TASK_DETAIL);
                }

                loadAndRun(tasks, load, isLoadingData, needUpdateTaskStatus);
            }
        } catch (FileNotFoundException e) {
            echo(NO_PREVIOUS_RECORD);
        }
    }

    public static void exitLine() {
        linePrinter();
        System.out.println("\t" + GOODBYE_MSG);
        linePrinter();
    }

    public static void echo (String line) {
        linePrinter();
        System.out.println("\t" + " " + line);
        linePrinter();
    }

    public static CommandType findCommandType(String line) {
        CommandType c;

        if (line.startsWith(ADD_TODO_CMD)) {
            c = CommandType.TODO;
        } else if (line.startsWith(ADD_DEADLINE_CMD)) {
            c = CommandType.DEADLINE;
        } else if (line.startsWith(ADD_EVENT_CMD)) {
            c = CommandType.EVENT;
        } else if (line.startsWith(MARK_TASK_CMD)) {
            c= CommandType.MARK;
        } else if (line.startsWith(UNMARK_TASK_CMD)){
            c = CommandType.UNMARK;
        } else if (line.equals(LIST_TASKS_CMD)){
            c = CommandType.LIST;
        } else if (line.startsWith(DELETE_CMD)){
            c = CommandType.DEL;
        } else if (line.equals(SAVE_CMD)) {
            c = CommandType.SAVE;
        } else {
            c = CommandType.NIL;
        }

        return c;
    }

    public static void checkCommand(ArrayList<Task> tasks,String line, CommandType c) throws NoTaskException, NoDateException, NoItemException {
        switch (c) {
        case TODO:
            String todo = line.substring(TODO_TASK_INDEX);

            if ((todo.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            break;
        case DEADLINE:
            String by = line.substring(line.indexOf(DEADLINE_OF_TASK_CMD) + TIME_INDEX);
            String deadline = line.substring(DEADLINE_TASK_INDEX, line.indexOf(DEADLINE_OF_TASK_CMD));

            if ((deadline.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            if ((by.trim()).isEmpty()) {
                throw new NoDateException();
            }
            break;
        case EVENT:
            String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
            String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));

            if ((event.trim()).isEmpty()) {
                throw new NoTaskException();
            }

            if ((at.trim()).isEmpty()) {
                throw new NoDateException();
            }

            break;
        case MARK:
            int markedItem = Integer.parseInt(line.substring(MARKED_ITEM_INDEX)) - 1;

            if ((markedItem < 0) || (markedItem >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        case UNMARK:
            int unmarkedItem = Integer.parseInt(line.substring(UNMARKED_ITEM_INDEX)) - 1;

            if ((unmarkedItem < 0) || (unmarkedItem >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        case DEL:
            int index = Integer.parseInt(line.substring(DELETE_INDEX)) - 1;

            if ((index < 0) || (index >= tasks.size())) {
                throw new NoItemException();
            }

            break;
        default:
            break;
        }
    }

    public static int handleError(ArrayList<Task> tasks,String line, CommandType c) {
        try {
            checkCommand(tasks, line, c);
        } catch (IndexOutOfBoundsException e01) {
            echo(ITEM_NOT_EXIST_MSG);
            return 1;
        } catch (NoDateException e02) {
            echo(NO_DATE_MSG);
            return 1;
        } catch (NoTaskException e03) {
            echo(NO_TASK_MSG);
            return 1;
        } catch (NoItemException e04) {
            echo(ITEM_NOT_EXIST_MSG);
            return 1;
        } finally {
            return 0;
        }
    }

    public static void loadAndRun(ArrayList<Task> tasks, String line, boolean isLoadingData, boolean needUpdateTaskStatus) {
        CommandType command = findCommandType(line);

        int i = handleError(tasks, line, command);
        if (i == ERROR_INDICATION_NUMBER) {
            return;
        }

        switch (command) {
        case TODO:
            tasks.add(new Todo(line.substring(TODO_TASK_INDEX))) ;

            if(needUpdateTaskStatus) {
                (tasks.get(tasks.size() - 1)).mark();
            }

            if (!isLoadingData) {
                echo("Added " + tasks.get(tasks.size() - 1) + " to the list");
            }

            break;
        case DEADLINE:
            String by = line.substring(line.indexOf(DEADLINE_OF_TASK_CMD) + TIME_INDEX);
            String deadline = line.substring(DEADLINE_TASK_INDEX, line.indexOf(DEADLINE_OF_TASK_CMD));
            tasks.add(new Deadline(deadline, by));

            if(needUpdateTaskStatus) {
                (tasks.get(tasks.size() - 1)).mark();
            }

            if (!isLoadingData) {
                echo("Added " + tasks.get(tasks.size() - 1) + " to the list");
            }

            break;
        case EVENT:
            String at = line.substring(line.indexOf(DURATION_OF_EVENT_CMD) + TIME_INDEX);
            String event = line.substring(EVENT_TASK_INDEX, line.indexOf(DURATION_OF_EVENT_CMD));
            tasks.add(new Event(event, at));

            if(needUpdateTaskStatus) {
                (tasks.get(tasks.size() - 1)).mark();
            }

            if (!isLoadingData) {
                echo("Added " + tasks.get(tasks.size() - 1) + " to the list");
            }

            break;
        case MARK:
            int markedItem = Integer.parseInt(line.substring(MARKED_ITEM_INDEX)) - 1;
            (tasks.get(markedItem)).mark();
            printMark(tasks, markedItem);
            break;
        case UNMARK:
            int unmarkedItem = Integer.parseInt(line.substring(UNMARKED_ITEM_INDEX)) - 1;
            (tasks.get(unmarkedItem)).unmark();
            printUnmark(tasks, unmarkedItem);
            break;
        case LIST:
            printList(tasks);
            break;
        case DEL:
            int index = Integer.parseInt(line.substring(DELETE_INDEX)) - 1;
            deleteItem(tasks, index);
            break;
        case SAVE:
            saveData(tasks);
            break;
        case NIL:
            echo(line);
            break;
        default:
            break;
        }
    }
}
