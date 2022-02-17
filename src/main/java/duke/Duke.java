package duke;

public class Duke {
    private UI ui;
    protected LocalStorage localStorage;
    private TaskList taskList;

    public Duke(String filePath) {
        this.localStorage = new LocalStorage(filePath);
        this.taskList = new TaskList(localStorage);
    }

    public void run() {
        start();
        runCommandUntilExitCommand();
        exit();
    }

    public void start() {
        this.ui = new UI();
        ui.printIntro();
    }

    public void runCommandUntilExitCommand() {
        String command;
        do {
            String input = ui.getUserCommand();
            command = CommandParser.getCommandFromUserInput(input);
            CommandParser.executeCommand(input, command, taskList);
        } while (!command.equalsIgnoreCase("bye"));
    }

    public void exit() {
        ui.printOutro();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("localStorage.csv").run();
    }
}
