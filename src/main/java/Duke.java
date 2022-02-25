public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public String diskFilePath;

    public Duke(String filePath) {
        diskFilePath = filePath; // HOW TO PASS FILEPATH TO TASKCLASS SO THAT IT KNOWS WHERE THE SAVED FILE IS?
        storage = new Storage(filePath); // ALTERNATIVELY: SHOULD FIND A WAY TO PASS STORAGE TO TASK CLASS, AND THEN HAVE "SAVETASKS" BEHAVIOR IN STORAGE
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasksFromDisk());
            ui.showGreeting();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        tasks.processTasks();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

