import java.io.FileNotFoundException;

public class Main {

    private UI ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run(){
        start();
        runUntilExit();
        exit();
    }

    public void runUntilExit(){
        String rawInput;
        boolean isToExit = false;
        while(!isToExit){
            rawInput = ui.getUserCommand();
            isToExit = parser.executeCommand(rawInput);
        }

    }

    public void start(){
        try{
            ui = new UI();
            storage = new Storage(ui);
            taskList = new TaskList(ui);
            storage.load(taskList);
            parser = new Parser(ui, taskList, storage);
            ui.showWelcomeMsg();
        } catch (FileNotFoundException e){
            ui.showFileFailedToOpenMsg();
            return;
        }
    }

    public void exit(){
        ui.showGoodbyeMsg();
    }
}
