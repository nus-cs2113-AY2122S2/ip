import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();

    }

    public void run() throws IOException, EmptyDescriptionException {
        storage.loadTaskList();
        ui.printWelcomeMessage();

        while (true){
            String input = ui.readInput();
            Command c = new Command(input);
            c.executeCommand();
            if (c.getCommand().equals("bye")){
                ui.printGoodByeMessage();
                storage.storeFile();
                break;
            }

        }
    }

    public static void main(String[] args) throws IOException, EmptyDescriptionException {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        new Duke().run();

    }

//    public static void addTask(String desc, boolean isDone, char type, String time) {
//        tasks.add(new Task(desc, isDone, type, time));
//    }
}
