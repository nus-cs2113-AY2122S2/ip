import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void userInterface() throws DukeException {
        ArrayList<Task> userLists = new ArrayList<>();
        loadSaveFile(userLists);
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        //main handler for receiving input
        while (!userInput.equals("bye")) {
            String[] tokens = userInput.split(" ");
            try {
                Tokenise userInputTokens = new Tokenise(userInput);
                //change user input into an array of tokens
                switch (userInputTokens.getTokens()[0]) {
                case "list":
                    String allTasks = "Here are the tasks in your list:\n" +
                            listTask(userLists);
                    allTasks = wrapMessage(allTasks);
                    System.out.println(allTasks);
                    break;
                case "todo":
                    Todo newTodo = new Todo(userInputTokens.getDescription());
                    addTask(newTodo, userLists);
                    break;
                case "deadline":
                    Deadline newDeadline = new Deadline(
                            userInputTokens.getDescription(),
                            userInputTokens.getTime());
                    addTask(newDeadline, userLists);
                    break;
                case "event":
                    //find index in user input tokens which contains the time separator
                    Event newEvent = new Event(
                            userInputTokens.getDescription(),
                            userInputTokens.getTime());
                    addTask(newEvent, userLists);
                    break;
                case "mark":
                    int markIndex = userInputTokens.getMarkIndex();
                    if (markIndex > userLists.size()) {
                        throw new DukeExceptionMarkBounds();
                    }
                    userLists.get(markIndex).setMark();
                    System.out.println(
                            wrapMessage("Nice! I've marked this task as done:\n " +
                                    userLists.get(userInputTokens.getMarkIndex()).toString()
                            ));
                    break;
                case "unmark":
                    markIndex = userInputTokens.getMarkIndex();
                    if (markIndex > userLists.size()) {
                        throw new DukeExceptionMarkBounds();
                    }
                    userLists.get(userInputTokens.getMarkIndex()).unMark();
                    System.out.println(
                            wrapMessage("OK, I've marked this task as not done yet:\n " +
                                    userLists.get(userInputTokens.getMarkIndex()).toString()
                            ));
                    break;
                case "delete":
                    int deleteIndex = userInputTokens.getMarkIndex();
                    deleteTask(userLists, deleteIndex);
                    break;
                default:
                }
            } catch (DukeExceptionCommand e) {
                System.out.println(wrapMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
            } catch (DukeExceptionDescription e) {
                System.out.printf(wrapMessage("OOPS!!! The description of a %s cannot be empty!\n"),
                        tokens[0]);
            } catch (DukeExceptionTiming e) {
                System.out.printf(wrapMessage("OOPS!!! The time of this %s cannot be empty!\n"),
                        tokens[0]);
            } catch (DukeExceptionList e) {
                System.out.println(wrapMessage("OOPS!!! List should not have any other text after!\n"));
            } catch (DukeExceptionMark e) {
                System.out.printf(wrapMessage("%s needs a number as an input\n"), tokens[0]);
            } catch (DukeExceptionMarkBounds e) {
                System.out.println(wrapMessage("Number provided is not in the list\n"));
            }
            userInput = input.nextLine();
        }
        saveList(userLists);
    }

    /**
     * add a new task to the existing
     * array of tasks
     *
     * @param task
     * @param userLists
     */
    public static void addTask (Task task, ArrayList<Task> userLists) {
        userLists.add(task);
        String userInput = wrapMessage(
                String.format("Got it. I've added this task:\n" +
                                "  %s" +
                                " Now you have %d tasks in the list\n",
                        task.toString(), userLists.size()));
        System.out.println(userInput);
    }

    /**
     * Deletes a task from the list of tasks
     *
     * @param userLists
     * @param index
     */
    public static void deleteTask (ArrayList<Task> userLists, int index) throws
            DukeExceptionMarkBounds {
        try {
            Task removedTask = userLists.remove(index);
            String userInput = wrapMessage(
                    String.format("Noted. I've removed this task:\n" +
                                    "  %s" +
                                    " Now you have %d tasks in the list\n",
                            removedTask, userLists.size()));
            System.out.println(userInput);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeExceptionMarkBounds();
        }
    }

    /**
     * Saves the list of Tasks to hard drive
     * Will be called whenever a task is added or deleted
     */
    public static void saveList(ArrayList<Task> userLists) {
        String pathName = "./data/duke.txt";
        File file = new File(pathName);
        try {
            if (file.createNewFile()) {
                //new file created
                writeToFile(pathName, userLists);
            } else {
                //file has already been created
                writeToFile(pathName, userLists);
            }
        } catch (IOException e) {
            File directory = new File("./data/");
            boolean isDirCreated = directory.mkdir();
            if (isDirCreated) {
                saveList(userLists);
            }
        }
    }

    /**
     *
     * @param pathName
     * @param list
     * @throws IOException
     */
    public static void writeToFile(String pathName, ArrayList<Task> list) throws IOException {
        FileWriter fileWriter = new FileWriter(pathName);
        fileWriter.write(listTask(list));
        fileWriter.close();
    }

    /**
     *
     */
    private static void loadSaveFile(ArrayList<Task> userLists) {
        String pathName = "./data/duke.txt";
        File file = new File(pathName);
        try {
            if (file.exists()) {
                //save file exists, load in save file
                Scanner fileContent = new Scanner(file);
                while (fileContent.hasNext()) {
                    String listContent = fileContent.nextLine();
                    String regexTask = "(?<task>\\[[TDE]])";
                    Matcher matcher =  regexMatching(regexTask, listContent);
                    if (!matcher.find()) {
                        return;
                    }
                    String task = matcher.group("task");
                    switch (task) {
                    case "[T]":
                        String regexTodo = "(?<task>\\[[TDE]])(?<mark>\\[[\\s|X]])(?<description>\\D*)";
                        Matcher matcherTodo =  regexMatching(regexTodo, listContent);
                        matcherTodo.find();
                        String description = matcherTodo.group("description").trim();
                        Todo newTodo = new Todo(description);
                        if (matcherTodo.group("mark").equals("[X]")) {
                            newTodo.setMark();
                        }
                        userLists.add(newTodo);
                        break;
                    case "[D]":
                        String regexDeadline = "(?<task>\\[[TDE]])(?<mark>\\[[\\s|X]])(?<description>\\D*)(?<time>\\(at\\S*)";
                        Matcher matcherDeadline =  regexMatching(regexDeadline, listContent);
                        matcherDeadline.find();
                        description = matcherDeadline.group("description").trim();
                        String time = matcherDeadline.group("time");
                        Deadline newDeadline = new Deadline(description, time);
                        if (matcherDeadline.group("mark").equals("[X]")) {
                            newDeadline.setMark();
                        }
                        userLists.add(newDeadline);
                        break;
                    case "[E]":
                        String regexEvent = "(?<task>\\[[TDE]])(?<mark>\\[[\\s|X]])(?<description>\\D*)(?<time>\\(at\\S*)";
                        Matcher matcherEvent =  regexMatching(regexEvent, listContent);
                        matcherEvent.find();
                        description = matcherEvent.group("description").trim();
                        time = matcherEvent.group("time");
                        Event newEvent = new Event(description, time);
                        if (matcherEvent.group("mark").equals("[X]")) {
                            newEvent.setMark();
                        }
                        userLists.add(newEvent);
                        break;
                    }
                }
            } else {
                //save file does not exist, do nothing
                return;
            }
        } catch (IOException e) {
            //do nothing
        }
    }

    public static Matcher regexMatching(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    /**
     * Change tasks into a printable list
     * of current tasks the user have
     *
     * @param tasks
     * @return String of all the tasks
     */
    public static String listTask(ArrayList<Task> tasks) {
        String allTasks = "";
        for (int i = 1; i <= tasks.size(); i++) {
            allTasks = allTasks + " " + i + "." + tasks.get(i - 1).toString();
        }
        return allTasks;
    }

    public static String wrapMessage(String message) {
        String divider = "____________________________________________________________\n";
        message = String.format("%s %s%s", divider, message, divider);
        return message;
    }

    public static void welcomeMessage() {
        String welcome= "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(welcome);
    }

    public static void byeMessage() {
        String goodBye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(goodBye);
    }

    public static void main(String[] args) throws DukeException {
        welcomeMessage();
        userInterface();
        byeMessage();
    }
}
