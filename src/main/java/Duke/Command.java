package Duke;

import Duke.Exception.EmptyDescriptionException;
import Duke.Exception.WrongCommandException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Task.Task;
import Duke.Task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Command {
    private final String input;
    private final String command;
    private final Parser p;
    private static final String LINE_SEPARATOR = "-----------------------------------------";

    public Command(String input) {
        this.input = input;
        this.p = new Parser(input);
        this.command = p.parseCommand();
    }
    public void executeCommand() throws IOException {
       try {
           switch (command) {
           case "bye":
               byeCommand();
               break;
           case "list":
               listCommand();
               break;
           case "mark":
               markCommand();
               break;
           case "unmark":
               unmarkCommand();
               break;
           case "todo":
               todoCommand();
               break;
           case "deadline":
               deadlineCommand();
               break;
           case "event":
               eventCommand();
               break;
           case "delete":
               deleteCommand();
               break;
           case "find":
               findCommand();
               break;
           default:
               throw new WrongCommandException();
           }
       }catch (EmptyDescriptionException e) {
           System.out.println("    It seems like you forgot to include the description of your " + command);
           System.out.println(LINE_SEPARATOR);
       } catch (WrongCommandException e) {
           System.out.println("    Oh no, I don't understand that yet!");
           System.out.println(LINE_SEPARATOR);
       }
    }

    public void  byeCommand(){
    }

    public void listCommand(){
        System.out.println(LINE_SEPARATOR);
        System.out.println("    Here the task you've written m'lord:");
        printTasks();
        System.out.println(LINE_SEPARATOR);
    }
    
    public void markCommand() throws IOException {
        int commandSeparator = input.indexOf(' ');
        String ans = input.substring(commandSeparator + 1);
        int index = Integer.parseInt(ans) - 1;
        TaskList.tasks.get(index).setIsDone(true);
        System.out.println(LINE_SEPARATOR);
        System.out.println("    I've marked the task as done m'lord:");
        printTasks();
        System.out.println(LINE_SEPARATOR);
        saveFile();
    }

    public void unmarkCommand() throws IOException {
        int commandSeparator = input.indexOf(' ');
        String ans = input.substring(commandSeparator + 1);
        int index = Integer.parseInt(ans) - 1;
        TaskList.tasks.get(index).setIsDone(false);
        System.out.println(LINE_SEPARATOR);
        System.out.println("    I've marked the task as done m'lord:");
        printTasks();
        System.out.println(LINE_SEPARATOR);
        saveFile();
    }

    public void todoCommand() throws EmptyDescriptionException, IOException {
        String task = p.parseTask(input);
        System.out.println(LINE_SEPARATOR);
        System.out.println(task);
        if (task.equalsIgnoreCase(command)) {
            throw new EmptyDescriptionException();
        }
        addTask(task, false, 'T', "");
        System.out.println("     added: " + TaskList.tasks.get(TaskList.tasks.size() - 1));
        System.out.println("     there are currently " + TaskList.tasks.size() + " tasks ");
        System.out.println(LINE_SEPARATOR);
        saveFile();
    }

    public void  deadlineCommand() throws EmptyDescriptionException, IOException {
        int timeSeparator = input.indexOf('/');
        String time = p.parseTime(input);
        String task = p.parseTask(input, timeSeparator);
        System.out.println(LINE_SEPARATOR);
        if (task.equalsIgnoreCase(command)) {
            throw new EmptyDescriptionException();
        }
        addTask(task, false, 'D', time);
        System.out.println("     added: " + TaskList.tasks.get(TaskList.tasks.size() - 1));
        System.out.println("     there are currently " + TaskList.tasks.size() + " tasks ");
        System.out.println(LINE_SEPARATOR);
        saveFile();
    }

    public void eventCommand() throws IOException, EmptyDescriptionException {
        int timeSeparator = input.indexOf('/');
        String time = p.parseTime(input);
        String task = p.parseTask(input, timeSeparator);
        System.out.println(LINE_SEPARATOR);
        if (task.equalsIgnoreCase(command)) {
            throw new EmptyDescriptionException();
        }
        addTask(task, false, 'E', time);
        System.out.println("     added: " + TaskList.tasks.get(TaskList.tasks.size() - 1));
        System.out.println("     there are currently " + TaskList.tasks.size() + " tasks ");
        System.out.println(LINE_SEPARATOR);
        saveFile();
    }

    public void deleteCommand() throws IOException, EmptyDescriptionException {
        int commandSeparator = input.indexOf(' ');
        String ans = input.substring(commandSeparator + 1);
        System.out.println(LINE_SEPARATOR);
        if (ans.equalsIgnoreCase(command)) {
            throw new EmptyDescriptionException();
        }

        System.out.println("     deleted: " + TaskList.tasks.get(Integer.parseInt(ans) - 1));
        TaskList.tasks.remove(Integer.parseInt(ans) - 1);
        System.out.println("     there are currently " + TaskList.tasks.size() + " tasks ");
        System.out.println(LINE_SEPARATOR);
        saveFile();
    }

    public void findCommand(){
        try {
            String filter = p.parseTask(input);
            ArrayList<Task> filteredTasks = (ArrayList<Task>) TaskList.tasks.stream().filter(task -> task.getDesc().toLowerCase().contains(filter.toLowerCase())).collect(toList());
            printFilteredTasks(filteredTasks);
            System.out.println(LINE_SEPARATOR);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("please give filter input");
        }
    }
    public static void addTask(String desc, boolean isDone, char type, String time) {
        TaskList.tasks.add(new Task(desc, isDone, type, time));
    }
    public void printTasks(){
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            System.out.println("    " + (i + 1) + " " + TaskList.tasks.get(i));
        }
    }

    public void saveFile() throws IOException {
        Storage storage = new Storage();
        storage.storeFile();
    }

    public String getCommand(){
        return this.command;
    }

    public void printFilteredTasks(ArrayList<Task> filteredTasks){
        for (int i = 0; i < filteredTasks.size(); i++) {
            System.out.println("    " + (i + 1) + " " + filteredTasks.get(i));
        }
    }
}
