package duke;// import libraries here
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static ArrayList<Boolean> dones = new ArrayList<>();
    private static ArrayList<String> types = new ArrayList<>();
    private static ArrayList<String> dates = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        loadTasks();
        getCommand();
    }

    private static void getCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        // get user input
        input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                showList();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("done")) {
                int number = Integer.parseInt(input.substring(input.length() - 1));
                markAsDone(number);
                saveTasks();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("delete")) {
                int number = Integer.parseInt(input.substring(input.length() - 1));
                deleteTask(number);
                saveTasks();
                input = in.nextLine();
                continue;
            }
            if (input.startsWith("find")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("Here are the matching tasks in your list:");
                String keyword = input.split(" ")[1].trim();
                for (int i = 0; i < tasks.size(); i++) {
                    String description = tasks.get(i);
                    String type = types.get(i);
                    boolean isDone = dones.get(i);
                    String date = dates.get(i);
                    if (description.contains(keyword)) {
                        System.out.printf("     %d.", i + 1);
                        switch (type) {
                            case "T":
                                Todo todo = new Todo(description);
                                System.out.println(todo.toString(isDone));
                                break;
                            case "D":
                                Deadline deadline = new Deadline(description, date);
                                System.out.println(deadline.toString(isDone));
                                break;
                            case "E":
                                Event event = new Event(description, date);
                                System.out.println(event.toString(isDone));
                                break;
                        }
                    }
                }
                System.out.println("    ____________________________________________________________");
                input = in.nextLine();
                continue;
            }
            try {
                checkCommand(input);
            } catch (InvalidCommandException | EmptyDescriptionException e) {
                input = in.nextLine();
                continue;
            }
            // Command is valid, handle the command
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it. I've added this task: ");
            handleCommand(input);
            saveTasks();
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
            input = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     *
     * @param line
     * @throws EmptyDescriptionException
     * @throws InvalidCommandException
     */
    private static void checkCommand(String line) throws EmptyDescriptionException, InvalidCommandException {
        Set<String> validCommands = Set.of("todo", "deadline", "event", "find");
        String[] splitLine = line.split(" ");
        String type = splitLine[0];
        if (!validCommands.contains(type)) {
            throw new InvalidCommandException();
        }
        if (splitLine.length == 1) {
            throw new EmptyDescriptionException();
        }
    }

    private static void loadTasks() {
        String pathName = "./data/";
        String fileName = "duke.txt";
        File file = new File(pathName + fileName);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitLine = line.split(" \\| ");
                String type = splitLine[0];
                boolean done = Boolean.parseBoolean(splitLine[1]);
                String description = splitLine[2];
                String date;
                types.add(type);
                dones.add(done);
                tasks.add(description);
                if (!type.equals("T")) {
                    date = splitLine[3];
                } else {
                    date = "";
                }
                dates.add(date);
            }
        } catch (FileNotFoundException e) {
            createFileOrFolder(pathName, fileName);
        }
    }

    /**
     *
     * @param pathName
     * @param fileName
     */
    private static void createFileOrFolder(String pathName, String fileName) {
        try {
            Path path = Paths.get(pathName);
            Files.createDirectory(path);
            Path file = Paths.get(pathName + fileName);
            Files.createFile(file);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void saveTasks() {
        String pathName = "./data/";
        String fileName = "duke.txt";
        String separator = " | ";
        flushFile(pathName, fileName);
        for (int i = 0; i < tasks.size(); i++) {
            StringBuilder sb = new StringBuilder();
            String description = tasks.get(i);
            boolean done = dones.get(i);
            String type = types.get(i);
            String date = dates.get(i);
            sb.append(type);
            sb.append(separator);
            sb.append(done);
            sb.append(separator);
            sb.append(description);
            if (!date.equals("")) {
                sb.append(separator);
                sb.append(date);
            }
            String textToAppend = sb.toString();
            try {
                appendToFile(pathName + fileName, textToAppend + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     *
     * @param pathName
     * @param fileName
     */
    private static void flushFile(String pathName, String fileName) {
        File file = new File(pathName + fileName);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     *
     * @param textToAppend
     * @param filePath
     * @throws IOException
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     *
     * @param line
     */
    private static void handleCommand(String line) {
        String description;
        String date;
        dones.add(false);
        if (line.startsWith("todo")) {
            types.add("T");
            description = line.substring(5);
            Todo todo = new Todo(description);
            tasks.add(description);
            dates.add("");
            System.out.println("       " + todo.toString(false));
        } else if (line.startsWith("deadline")) {
            types.add("D");
            int separator = line.indexOf("/by");
            description = line.substring(9, separator - 1);
            date = line.substring(separator + 4);
            Deadline deadline = new Deadline(description, date);
            tasks.add(description);
            dates.add(date);
            System.out.println("       " + deadline.toString(false));
        } else if (line.startsWith("event")) {
            types.add("E");
            int separator = line.indexOf("/at");
            description = line.substring(6, separator - 1);
            date = line.substring(separator + 4);
            Event event = new Event(description, date);
            tasks.add(description);
            dates.add(date);
            System.out.println("       " + event.toString(false));
        } else {
            types.add("unknown");
        }
    }

    private static void showList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i);
            String date = dates.get(i);
            String type = types.get(i);
            boolean isDone = dones.get(i);
            System.out.printf("     %d.", i + 1);
            switch (type) {
            case "T":
                Todo todo = new Todo(description);
                System.out.println(todo.toString(isDone));
                break;
            case "D":
                Deadline deadline = new Deadline(description, date);
                System.out.println(deadline.toString(isDone));
                break;
            case "E":
                Event event = new Event(description, date);
                System.out.println(event.toString(isDone));
                break;
            default:
                System.out.println("       Unknown Type");
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     *
     * @param number
     */
    private static void markAsDone(int number) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        int index = number - 1;
        String description = tasks.get(index);
        String date = dates.get(index);
        String type = types.get(index);
        // mark as done
        dones.set(index, true);
        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            System.out.println("       " + todo.toString(true));
            break;
        case "D":
            Deadline deadline = new Deadline(description, date);
            System.out.println("       " + deadline.toString(true));
            break;
        case "E":
            Event event = new Event(description, date);
            System.out.println("       " + event.toString(true));
            break;
        default:
            System.out.println("       Unknown Type");
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     *
     * @param number
     */
    private static void deleteTask(int number) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        int index = number - 1;
        String description = tasks.get(index);
        String date = dates.get(index);
        String type = types.get(index);
        boolean done = dones.get(index);
        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                System.out.println("       " + todo.toString(done));
                break;
            case "D":
                Deadline deadline = new Deadline(description, date);
                System.out.println("       " + deadline.toString(done));
                break;
            case "E":
                Event event = new Event(description, date);
                System.out.println("       " + event.toString(done));
                break;
            default:
                System.out.println("       Unknown Type");
        }
        tasks.remove(index);
        dates.remove(index);
        types.remove(index);
        dones.remove(index);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm duke.Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

}