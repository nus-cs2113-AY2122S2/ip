import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) throws IOException {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner input = new Scanner(System.in);
        String filePath = "list.txt";
        File file = new File(filePath);
        if (file.exists()) {
            readFile(filePath);
        } else {
            file.createNewFile();
        }
        System.out.println("    _________________________________________");
        System.out.println("    Hello I'm Duke\n    What can I help you with?");
        System.out.println("    _________________________________________");
        String ans = "";

        String command = "";
        String time = "";
        int commandSeparator;
        int timeSeparator;
        int index;
        boolean loopInput = true;
        while (loopInput) {
            ans = input.nextLine();
            commandSeparator = ans.indexOf(' ');
            if (commandSeparator == -1) {
                command = ans;
            } else {
                command = ans.substring(0, commandSeparator);
            }
            timeSeparator = ans.indexOf('/');
            try {
                switch (command) {
                    case "bye":
                        loopInput = false;
                        break;
                    case "list":
                        System.out.println("    _________________________________________");
                        System.out.println("    Here the task you've written m'lord:");
                        printTasks();
                        System.out.println("    _________________________________________\n");
                        break;
                    case "mark":
                        ans = ans.substring(commandSeparator + 1);
                        index = Integer.parseInt(ans) - 1;
                        mark(index);
                        System.out.println("    _________________________________________");
                        System.out.println("    I've marked the task as done m'lord:");
                        printTasks();
                        System.out.println("    _________________________________________\n");
                        writeToFile(filePath);
                        break;
                    case "unmark":
                        ans = ans.substring(commandSeparator + 1);
                        index = Integer.parseInt(ans) - 1;
                        unmark(index);
                        System.out.println("    _________________________________________");
                        System.out.println("    I've revert the task to active m'lord:");
                        printTasks();
                        System.out.println("    _________________________________________\n");
                        writeToFile(filePath);
                        break;
                    case "todo":
                        ans = ans.substring(commandSeparator + 1);
                        System.out.println("    _________________________________________");
                        System.out.println(ans);
                        if (ans.equalsIgnoreCase(command)) {
                            throw new EmptyDescriptionException();
                        }
                        addTask(ans, false, 'T', time);
                        System.out.println("     added: " + tasks.get(tasks.size() - 1));
                        System.out.println("     there are currently " + tasks.size() + " tasks ");
                        System.out.println("    _________________________________________\n");
                        writeToFile(filePath);
                        break;
                    case "deadline":
                        // timeSeparator = (timeSeparator == -1 ? ans.length() : timeSeparator);
                        if (timeSeparator == -1) {
                            time = "";
                        } else {
                            time = ans.substring(timeSeparator + 4);
                        }
                        ans = ans.substring(commandSeparator + 1, timeSeparator);
                        System.out.println("    _________________________________________");
                        if (ans.equalsIgnoreCase(command)) {
                            throw new EmptyDescriptionException();
                        }
                        addTask(ans, false, 'D', time);
                        System.out.println("     added: " + tasks.get(tasks.size() - 1));
                        System.out.println("     there are currently " + tasks.size() + " tasks ");
                        System.out.println("    _________________________________________\n");
                        writeToFile(filePath);
                        break;
                    case "event":
                        // timeSeparator = (timeSeparator == -1 ? ans.length() : timeSeparator);
                        if (timeSeparator == -1) {
                            time = "";
                        } else {
                            time = ans.substring(timeSeparator + 4);
                        }
                        ans = ans.substring(commandSeparator + 1, timeSeparator);
                        System.out.println("    _________________________________________");
                        if (ans.equalsIgnoreCase(command)) {
                            throw new EmptyDescriptionException();
                        }
                        addTask(ans, false, 'E', time);
                        System.out.println("     added: " + tasks.get(tasks.size() - 1));
                        System.out.println("     there are currently " + tasks.size() + " tasks ");
                        System.out.println("    _________________________________________\n");
                        writeToFile(filePath);
                        break;
                    case "delete":
                        // timeSeparator = (timeSeparator == -1 ? ans.length() : timeSeparator);
                        ans = ans.substring(commandSeparator + 1);
                        System.out.println("    _________________________________________");
                        if (ans.equalsIgnoreCase(command)) {
                            throw new EmptyDescriptionException();
                        }

                        System.out.println("     deleted: " + tasks.get(Integer.parseInt(ans) - 1));
                        deleteTask(Integer.parseInt(ans) - 1);
                        System.out.println("     there are currently " + tasks.size() + " tasks ");
                        System.out.println("    _________________________________________\n");
                        writeToFile(filePath);
                        break;
                    default:
                        throw new WrongCommandException();
                }
            } catch (EmptyDescriptionException e) {
                System.out.println("    It seems like you forgot to include the description of your " + command);
                System.out.println("    _________________________________________\n");
            } catch (WrongCommandException e) {
                System.out.println("    Oh no, I don't understand that yet!");
                System.out.println("    _________________________________________\n");
            }
        }
        System.out.println("    _________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________________");

        input.close();

    }

    public static void addTask(String desc, boolean isDone, char type, String time) {
        tasks.add(new Task(desc, isDone, type, time));
    }

    public static void deleteTask(int del) {
        tasks.remove(del);
    }

    public static void mark(int i) {
        tasks.get(i).setIsDone(true);
    }

    public static void unmark(int i) {
        tasks.get(i).setIsDone(false);
    }

    public static void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + " " + tasks.get(i));
        }
    }

    public static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String line;
        String desc;
        String time;
        int separator;
        while (s.hasNext()) {
            line = s.nextLine();
            boolean isDone = (line.charAt(2) == 'T' ? true : false);
            separator = (line.indexOf("/") == -1) ? line.length() : line.indexOf("/") + 1;
            desc = line.substring(4, separator - 1);
            time = (separator == line.length()) ? "" : line.substring(separator);
            addTask(desc, isDone, line.charAt(0), time);
        }
    }

    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).typeOfTask() + " " + tasks.get(i).getIsDone() + " " + tasks.get(i).getDesc() + "/"
                    + tasks.get(i).getTime() + System.lineSeparator());
        }
        fw.close();
    }
}
