package duke.tasks;

import duke.Storage;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a person's list of Tasks that they must complete.
 * This class supports the modification of Tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Load the Task List from the file.
     * @throws IOException
     */
    public void loadTaskList() throws IOException {
        try {
            tasks = storage.loadFile();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Process Tasks after parsing it and use the command accordingly.
     * @throws IOException
     */
    public void processTasks() throws IOException {
        String by = new String();
        String at = new String();

            while(true){
                String userInput = Ui.getInput();
                String choice = userInput.split(" ")[0];
                System.out.println("----------------------------------------------");
                if (choice.equals("bye")) {
                    Storage.saveFile(tasks);
                    break;
                }
                switch (choice) {
                    case "list":
                        listTask();
                        break;
                    case "delete":
                        try{
                            int taskIndexDelete = Integer.parseInt(userInput.replace("delete","").trim());
                            deleteTask(taskIndexDelete);
                        }
                        catch (NumberFormatException e) {
                            Ui.printNumberFormatMsg();
                        }
                        break;
                    case "find":
                        String keyWord = userInput.replace("find","").trim();
                        findTask(keyWord);
                        break;
                    case "mark":
                        try {
                            int taskIndexMark = Integer.parseInt(userInput.replace("mark","").trim());
                            markTask(taskIndexMark);
                        }
                        catch (NumberFormatException e) {
                            Ui.printNumberFormatMsg();
                        }
                        break;
                    case "unmark":
                        try {
                            int taskIndexUnmark = Integer.parseInt(userInput.replace("unmark","").trim());
                            unmarkTask(taskIndexUnmark);
                        }
                        catch (NumberFormatException e) {
                            Ui.printNumberFormatMsg();
                        }
                        break;
                    case "todo":
                        String toDoDescription = userInput.replace("todo","").trim();
                        if (toDoDescription.equals("")) {
                            Ui.printDescriptionMissingMsg();
                        }
                        else {
                            Task t = new ToDo(toDoDescription, false);
                            addTask(t);
                        }
                        break;
                    case "deadline":
                        String deadlineDescription = userInput.replace("deadline","").trim();
                        String[] parsedDeadlineInput = deadlineDescription.split("/by");
                        try {
                            by = parsedDeadlineInput[1].trim();
                            deadlineDescription = parsedDeadlineInput[0].trim();
                            if (deadlineDescription.equals("")) {
                                Ui.printDescriptionMissingMsg();
                            }
                            else {
                                Task d = new Deadline(deadlineDescription, false, by);
                                addTask(d);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            Ui.printDeadlineErrorMsg();
                        }
                        break;
                    case "event":
                        String eventDescription = userInput.replace("event","").trim();
                        String[] parsedEventInput = eventDescription.split("/at");
                        try {
                            at = parsedEventInput[1].trim();
                            eventDescription = parsedEventInput[0].trim();
                            if (eventDescription.equals("")) {
                                Ui.printDescriptionMissingMsg();
                            }
                            else {
                                Task e = new Event(eventDescription, false, at);
                                addTask(e);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            Ui.printEventErrorMsg();
                        }
                        break;
                    default:
                        Ui.printCommandErrorMsg();
                        break;
                }
                Storage.saveFile(tasks);
                System.out.println("----------------------------------------------");
            }
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i<tasks.size() ; i++)
            System.out.println(i+1 + "." + tasks.get(i));
    }

    private static void markTask(int taskIndex) {
        try {
            tasks.get(taskIndex-1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex-1));
        }
        catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundMsg();
        }
    }

    private static void unmarkTask(int taskIndex) {
        try {
            tasks.get(taskIndex-1).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskIndex-1));
        }
        catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundMsg();
        }
    }

    private static void addTask(Task t) {
        tasks.add(t);
        System.out.println("Got it! I've added this task: ");
        System.out.println(t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(int taskIndex) {
        try{
            Task deletedTask = tasks.get(taskIndex -1);
            tasks.remove(taskIndex -1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
        catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundMsg();
        }

    }

    private static void findTask(String keyWord) {

        int i = 1;
        for(Task t: tasks) {
            if(t.description.contains(keyWord)){
                if(i == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println(i +"."+ t);
                i++;
            }
        }
        if(i == 1) {
            Ui.printKeyWordMsg();
        }
    }
}
