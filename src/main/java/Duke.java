import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void userInterface() throws DukeException {
        Task[] userLists = new Task[]{};
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
                    userLists = addTask(newTodo, userLists);
                    break;
                case "deadline":
                    Deadline newDeadline = new Deadline(
                            userInputTokens.getDescription(),
                            userInputTokens.getTime());
                    userLists = addTask(newDeadline, userLists);
                    break;
                case "event":
                    //find index in user input tokens which contains the time separator
                    Event newEvent = new Event(
                            userInputTokens.getDescription(),
                            userInputTokens.getTime());
                    userLists = addTask(newEvent, userLists);
                    break;
                case "mark":
                    int markIndex = userInputTokens.getMarkIndex();
                    if (markIndex > userLists.length) {
                        throw new DukeExceptionMarkBounds();
                    }
                    userLists[markIndex].setMark();
                    System.out.println(
                            wrapMessage("Nice! I've marked this task as done:\n" +
                                    userLists[userInputTokens.getMarkIndex()].toString()
                            ));
                    break;
                case "unmark":
                    markIndex = userInputTokens.getMarkIndex();
                    if (markIndex > userLists.length) {
                        throw new DukeExceptionMarkBounds();
                    }
                    userLists[userInputTokens.getMarkIndex()].unMark();
                    System.out.println(
                            wrapMessage("OK, I've marked this task as not done yet:\n" +
                                    userLists[userInputTokens.getMarkIndex()].toString()
                            ));
                    break;
                default:
                }
            } catch (DukeExceptionCommand e) {
                System.out.println(wrapMessage("OOPS!!! I'm sorry, but I don't know what that means :-("));
            } catch (DukeExceptionDescription e) {
                System.out.printf(wrapMessage("OOPS!!! The description of a %s cannot be empty!\n"),
                        tokens[0]);
            } catch (DukeExceptionTiming e) {
                System.out.printf(wrapMessage("OOPS!!! The time of this %s cannot be empty!\n"),
                        tokens[0]);
            } catch (DukeExceptionList e) {
                System.out.println(wrapMessage("OOPS!!! List should not have any other text after!"));
            } catch (DukeExceptionMark e) {
                System.out.printf(wrapMessage("%s needs a number as an input\n"), tokens[0]);
            } catch (DukeExceptionMarkBounds e) {
                System.out.println(wrapMessage("Number provided is not in the list"));
            }
            userInput = input.nextLine();
        }
    }

    /**
     * add a new task to the existing
     * array of tasks
     *
     * @param task
     * @param userLists
     * @return Array of Tasks
     */
    public static Task[] addTask (Task task, Task[] userLists) {
        userLists = Arrays.copyOf(userLists, userLists.length + 1);
        userLists[userLists.length - 1] = task;
        String userInput = wrapMessage(
                String.format("Got it. I've added this task:\n" +
                              " %s \n" +
                              "Now you have %d tasks in the list",
                task.toString(), userLists.length));
        System.out.println(userInput);
        saveList(userLists);

        return userLists;
    }

    /**
     * Saves the list of Tasks to hard drive
     * Will be called whenever a task is added or deleted
     */
    public static void saveList(Task[] userLists) {
        String pathName = "./data/duke.txt";
        File file = new File(pathName);
        try {
            if (file.createNewFile()) {
                //file has already been created
                writeToFile(pathName, userLists);
            } else {
                //new file created
                writeToFile(pathName, userLists);
            }
        } catch (IOException e) {
            file.mkdir();
            saveList(userLists);
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static void writeToFile(String pathName, Task[] list) throws IOException {
        FileWriter fileWriter = new FileWriter(pathName);
        fileWriter.write(listTask(list));
        fileWriter.close();
    }

    /**
     * Change tasks into a printable list
     * of current tasks the user have
     *
     * @param tasks
     * @return String of all the tasks
     */
    public static String listTask(Task[] tasks) {
        String allTasks = "";
        for (int i = 1; i <= tasks.length; i++) {
            allTasks = allTasks + " " + i + "." + tasks[i-1].toString();
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
