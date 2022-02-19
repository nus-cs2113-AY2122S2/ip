package duke;

import duke.exception.*;
import duke.storage.StorageFile;
import duke.tasks.*;
import duke.util.Parser;
import duke.util.PatternGenerator;
import duke.util.Ui;
import duke.Commands.*;
import java.io.File;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static String dataPath;
    private StorageFile storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String dataPath) throws InvalidDataPathException {
        ui = new Ui();
        storage = new StorageFile(dataPath);
        try{
            tasks = new TaskList(storage.load());
        }catch (InvalidDataPathException e){
            ui.showLoadingError();
            tasks = new TaskList(storage.load());
        }
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        Command command;
        String input;
        while (true){
            input = sc.nextLine();
            try{
                command = new Parser().parseCommand(input);
                executeCommand(command);
            } catch (NonExistentCommandException e){
                PatternGenerator.generateArrows();
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                PatternGenerator.generateLine();
            } catch (IllegalFormatException e) {
                System.out.println("I'm sorry but the format is not correct.");
            } catch (IndexOutOfRangeException e) {
                System.out.println("Index out of range :(");
            }

            if(input.equals("exit")){
                break;
            }
        }
    }

    public static void main(String[] args) throws InvalidDataPathException {
        new Duke(getPath()).run();
    }

    public static String getPath(){
        String workingDir = System.getProperty("user.dir");
        String path = workingDir + "/Data/duke.txt";
        return path;
    }


    private void executeCommand(Command command) throws IndexOutOfRangeException {
        command.setData(tasks);
        command.execute();
        storage.save(tasks);
    }



}
