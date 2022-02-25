package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    /**
     * Saves the list of Tasks to hard drive
     * Will be called whenever a task is added or deleted
     *
     * @param userLists ArrayList of user tasks
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
     * Write the list of user tasks to file specified
     * by the path
     *
     * @param pathName string with the path of file
     * @param userList Arraylist of user tasks
     * @throws IOException Unable to write to file
     */
    public static void writeToFile(String pathName, ArrayList<Task> userList) throws IOException {
        FileWriter fileWriter = new FileWriter(pathName);
        fileWriter.write(TaskList.listTask(userList));
        fileWriter.close();
    }

    /**
     * Load the file duke.txt and parse the strings
     * inside the file to add those tasks back to
     * the ArrayList of user tasks
     *
     * @param userLists ArrayList of user tasks
     */
    public static void loadSaveFile(ArrayList<Task> userLists) {
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
                        continue;
                    }
                    String task = matcher.group("task");
                    switch (task) {
                    case "[T]":
                        String regexTodo = "(?<task>\\[[TDE]])(?<mark>\\[[\\s|X]])"+
                                "(?<description>\\D*)";
                        Matcher matcherTodo =  regexMatching(regexTodo, listContent);
                        if (!matcherTodo.find()) {
                            continue;
                        }
                        String description = matcherTodo.group("description").trim();
                        Todo newTodo = new Todo(description);
                        if (matcherTodo.group("mark").equals("[X]")) {
                            newTodo.setMark();
                        }
                        userLists.add(newTodo);
                        break;
                    case "[D]":
                        String regexDeadline = "(?<task>\\[[TDE]])(?<mark>\\[[\\s|X]])"+
                                "(?<description>\\D*)(?<time>\\(at\\S*)";
                        Matcher matcherDeadline =  regexMatching(regexDeadline, listContent);
                        if (!matcherDeadline.find()) {
                            continue;
                        }
                        description = matcherDeadline.group("description").trim();
                        String time = matcherDeadline.group("time");
                        Deadline newDeadline = new Deadline(description, time);
                        if (matcherDeadline.group("mark").equals("[X]")) {
                            newDeadline.setMark();
                        }
                        userLists.add(newDeadline);
                        break;
                    case "[E]":
                        String regexEvent = "(?<task>\\[[TDE]])(?<mark>\\[[\\s|X]])"+
                                "(?<description>\\D*)(?<time>\\(at\\S*)";
                        Matcher matcherEvent =  regexMatching(regexEvent, listContent);
                        if (matcherEvent.find()) {
                            continue;
                        }
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
            }
        } catch (DukeExceptionTiming e) {
            //do nothing
        } catch (FileNotFoundException e) {
            //do nothing
        }
    }

    /**
     * Match the given input with the given String Regex
     *
     * @param regex Given regex pattern
     * @param input Given input to match regex against
     * @return Matcher class after matching the input with regex
     */
    public static Matcher regexMatching(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
