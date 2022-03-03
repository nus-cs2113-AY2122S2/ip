import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String SEPARATOR = "-------------------------------------------";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private static final String filePath = "./data/data.txt";

    public static void loadData() {
        try{
            File data = new File(filePath);
            Scanner line = new Scanner(data);
            String task, description, isDone;
            while (line.hasNext()) {
                task = line.nextLine();
                isDone = task.substring(4, 5);
                description = task.substring(7);
                if (task.startsWith("[T]")) {
                    ToDo toDo = new ToDo(description);
                    toDo.setDone(isDone.equals("X"));
                    tasks.add(toDo);
                } else if (task.startsWith("[D]")) {
                    int separation = description.indexOf("(");
                    String dueDate = description.substring(separation + 1);
                    description = description.substring(0, separation - 6);
                    Deadline deadline = new Deadline(description, dueDate);
                    deadline.setDone(isDone.equals("X"));
                    tasks.add(deadline);
                } else if (task.startsWith("[E]")) {
                    int separation = description.indexOf("(");
                    String timing = description.substring(separation + 1);
                    description = description.substring(0, separation - 5);
                    Event event = new Event(description, timing);
                    event.setDone(isDone.equals("X"));
                    tasks.add(event);
                }
            }
            line.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found");
        }
    }

    public static void writeData() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write("");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fileWriter.write(task.toString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something Happened");
        }
    }

    /**
     * Prints the greeting of Cheems.
     */
    public static void greet() {
        String logo = "   ________                            \n"
                + "  / ____/ /_  ___  ___  ____ ___  _____\n"
                + " / /   / __ \\/ _ \\/ _ \\/ __ `__ \\/ ___/\n"
                + "/ /___/ / / /  __/  __/ / / / / (__  ) \n"
                + "\\____/_/ /_/\\___/\\___/_/ /_/ /_/____/  \n"
                + "                                       \n"
                + SEPARATOR;
        String greet = "Hemlo from\n" + logo + "\nWhamt cam cheems do for you?";
        System.out.println(greet);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the farewell of Cheems.
     */
    public static void farewell() {
        String farewell = "Goomdbye. See you nemxt time frem!";
        System.out.println(farewell);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints all the Tasks that are stored
     */
    public static void printTasks() {
        String entryNumber;
        System.out.println("Hemre are the tamsks im youmr limst:");
        for (int i = 0; i < tasks.size(); i++) {
            entryNumber = String.valueOf(i + 1) + ". ";
            System.out.print(entryNumber);
            System.out.println(tasks.get(i));
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Sets the selected Task status to Done and prints the selected Task.
     *
     * @param command The command given from input.
     */
    public static void handleMark(String command) {
        try{
            int taskPosition = Integer.parseInt(command.substring(5)) - 1;
            Task task = tasks.get(taskPosition);
            task.setDone(true);
            System.out.println("Ok! I hamve markemd the tamsk:");
            System.out.println(task);
        } catch (NullPointerException e) {
            System.out.println("Error! You don't have that many tasks");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error! Please enter a valid task number");
        } catch (NumberFormatException e) {
            System.out.println("Error! Please input a number");
        } finally{
            System.out.println(SEPARATOR);
            writeData();
        }
    }

    /**
     * Sets the selected Task status to not Done and prints the selected Task.
     *
     * @param command The command given from input.
     */
    public static void handleUnmark(String command) {
        try{
            int taskPosition = Integer.parseInt(command.substring(7)) - 1;
            Task task = tasks.get(taskPosition);
            task.setDone(false);
            System.out.println("Oof! I hamve unmarkemd the tamsk: ");
            System.out.println(task);
        } catch (NullPointerException e) {
            System.out.println("Error! Please enter a valid task number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error! Please enter a valid task number");
        } catch (NumberFormatException e) {
            System.out.println("Error! Please enter a valid task number");
        } finally{
            System.out.println(SEPARATOR);
            writeData();
        }
    }

    /**
     * Creates a new Deadline and prints the new Deadline.
     *
     * @param command The command given from input
     */
    public static void addDeadline(String command) {
        try{
            int dueDateIndex = command.indexOf("/") + 1;
            if (dueDateIndex == 0) {
                throw new CheemsException();
            } 
            String dueDate = command.substring(dueDateIndex);
            String description = command.substring(9, dueDateIndex - 1);
            Deadline newDeadline = new Deadline(description, dueDate);
            tasks.add(newDeadline);
            System.out.println("I hamve addemd: ");
            System.out.println(newDeadline);
        } catch (CheemsException e) {
            System.out.println("Error! Please follow the format given");
        } finally {
            System.out.println(SEPARATOR);
            writeData();
        }
    }

    /**
     * Creates a new ToDo and prints the new ToDo.
     *
     * @param command The command given from input
     */
    public static void addToDo(String command) {
        try {
            String description = command.substring(5);
            ToDo newToDo = new ToDo(description);
            tasks.add(newToDo);
            System.out.println("I hamve addemd: ");
            System.out.println(newToDo);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error! Tomdo cannomt be empty");
        } finally {
            System.out.println(SEPARATOR);
            writeData();
        }
    }

    /**
     * Creates a new Event and prints the new Event.
     *
     * @param command The command given from input
     */
    public static void addEvent(String command) {
        try {
            int timingIndex = command.indexOf("/") + 1;
            if (timingIndex == 0) {
                throw new CheemsException();
            }
            String timing = command.substring(timingIndex);
            String description = command.substring(6, timingIndex - 1);
            Event newEvent = new Event(description, timing);
            tasks.add(newEvent);
            System.out.println("I hamve addemd: ");
            System.out.println(newEvent);
        } catch (CheemsException e) {
            System.out.println("Error! Please follow the format given");
        } finally {
            System.out.println(SEPARATOR);
            writeData();
        }
    }

    /**
     * Deletes the Task with the corresponding task number.
     *
     * @param command The command given from input
     */
    public static void deleteTask(String command) {
        int taskNumber = Integer.parseInt(command.substring(7));
        Task task = tasks.remove(taskNumber - 1);
        System.out.println("Succesfully removed: ");
        System.out.println(taskNumber + ". " + task);
        writeData();
    }

    /**
     * Keeps asking for inputs until the input is "bye".
    */
    public static void askInput() {
        String command = in.nextLine();
        System.out.println(SEPARATOR);
        do {
            try{
                if (command.startsWith("list")) {
                    printTasks();
                } else if (command.startsWith("mark")) {
                    handleMark(command);
                } else if (command.startsWith("unmark")) {
                    handleUnmark(command);
                } else if (command.startsWith("deadline")) {
                    addDeadline(command);
                } else if (command.startsWith("todo")) {
                    addToDo(command);
                } else if (command.startsWith("event")) {
                    addEvent(command);
                } else if (command.startsWith("delete")) {
                    deleteTask(command);
                } else if (command.startsWith("bye")) {
                    break;
                } else {
                    throw new CheemsException();
                }
            } catch (CheemsException e) {
                System.out.println("I don't know what that means sorry :(");
                System.out.println(SEPARATOR);
            }
            command = in.nextLine();
            System.out.println(SEPARATOR);
        } while (!command.equals("bye"));
    }

    public static void main(String[] args) {
        loadData();
        greet();
        askInput();
        farewell();
    }
}
