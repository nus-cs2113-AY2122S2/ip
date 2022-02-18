package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    // Takes in a string. Executed commands will print related statements.
    public static void executeCommands (ChatSession currChat, String userInput) throws DukeException {
        String[] userInputArr;
        String userCommand;
        String[] userArguments;
        String description;

        userInputArr = userInput.split(" ", 2);
        userCommand = userInputArr[0];

        switch (userCommand) {
        case "list":
            currChat.printTaskList();
            break;
        case "mark":
            // Additional argument provided by user is the task no. to mark
            currChat.markTaskIndex(Integer.parseInt(userInputArr[1]));
            break;
        case "unmark":
            currChat.unmarkTaskIndex(Integer.parseInt(userInputArr[1]));
            break;
        case "todo":
            // Detect missing arguments
            if (userInputArr.length == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            currChat.addTask(new Todo(userInputArr[1]));
            break;
        case "deadline":
            // eg. return book /by Sunday
            userArguments = userInputArr[1].split(" /by ", 2);
            description = userArguments[0]; //eg. return book
            String by = userArguments[1]; // eg. Sunday
            currChat.addTask(new Deadline(description, by));
            break;
        case "event":
            // eg. project meeting /at Mon 2-4pm
            userArguments = userInputArr[1].split(" /at ", 2);
            description = userArguments[0]; // eg. project meeting
            String eventTime = userArguments[1]; // eg. Mon 2-4pm
            currChat.addTask(new Event(description, eventTime));
            break;
        case "delete":
            currChat.deleteTaskIndex(Integer.parseInt(userInputArr[1]));
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    // Read previous data
    public static void readData(ChatSession currChat, String folderPath, String fileName) {
        try {
            File f = new File(folderPath + "/" + fileName);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String userInput = s.nextLine();
                String[] userInputArr = userInput.split(" \\| ");
                String taskType = userInputArr[0];
                boolean isDone = userInputArr[1].equals("1");

                switch (taskType) {
                case "T":
                    currChat.addInitialTask(new Todo(isDone, userInputArr[2]));
                    break;
                case "D":
                    currChat.addInitialTask(new Deadline(isDone, userInputArr[2], userInputArr[3]));
                    break;
                case "E":
                    currChat.addInitialTask(new Event(isDone, userInputArr[2], userInputArr[3]));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            File directory = new File(folderPath);
            // Create directory if not found
            if (!directory.exists()) {
                directory.mkdir();
            }

            File f = new File(folderPath + "/" + fileName);
            // Create file if not found. If IOError, print error message
            try {
                f.createNewFile();
            } catch (IOException err) {
                System.out.println(err);
            }
        }
    }

    public static void main(String[] args) {
        // Start chat session
        ChatSession currChat = new ChatSession();
        currChat.startSession();

        // Read previous data
        readData(currChat, "data", "duke.txt");

        // Get user input
        Scanner sc = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = sc.nextLine();

            // Terminate chat session
            if (userInput.startsWith("bye")) {
                currChat.endSession();
                break;
            }

            // Try to execute commands. If unrecognised commands, print error.
            try {
                executeCommands(currChat, userInput);
            } catch (DukeException e) {
                currChat.printInvalidTask(e);
            }
        }
    }
}
