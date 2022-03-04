import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads input from user constantly until user decides to end the program.
 * It also first loads current task in file if exists when starting up
 */
public class TaskManager {
    private final String BOT_NAME = "[iWish]: ";
    private ArrayList<Task> taskList = new ArrayList<>();
    private TaskFileManager taskFileManager = new TaskFileManager();
    private UiManager uiManager = new UiManager(BOT_NAME);
    private CommandParser commandParser = new CommandParser();

    public void start() {
        String userInput = "";
        LoadCommand load = new LoadCommand("wishlist.txt");
        load.execute(taskList, uiManager, taskFileManager);
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                userInput = in.nextLine();
                Command command = commandParser.parse(userInput);
                command.execute(taskList, uiManager, taskFileManager);
                isExit = command.isExit();
            } catch (DukeWrongCommandException error) {
                uiManager.printError(error.getMessage());
            }
        }
    }
}


