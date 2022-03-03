import baymax.data.TaskManager;
import baymax.data.Deadline;
import baymax.data.Event;
import baymax.data.Todo;

import java.lang.String;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

import baymax.data.TaskManager;
import baymax.ui.Ui;
import baymax.parse.Parser;
import baymax.storage.Storage;

public class Baymax {

    private final Ui ui;
    private TaskManager tManager;

    public Baymax(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            tManager = new TaskManager(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            tManager = new TaskManager();
        }
    }

    public void run() throws IOException {
        ui.displayWelcomeMessage();
        boolean isBye;
        isBye = false;
        while (!isBye) {
            String fullCommand = ui.getUserCommand();
            Parser parse = new Parser(ui);
            parse.parse(fullCommand, tManager);
            isBye = parse.getExit();
        }
        ui.showByeMessage();
    }

    public static void main(String[] args) throws IOException {
        new Baymax("data/Baymax.txt").run();
    }
}


//        tManager.welcome(); //greating

//        command = in.nextLine();
//        while (!command.equals("bye")) {
//
//            String[] word_split;
//            word_split = command.split(" ", 2);
//            String taskName = word_split[0];
//
//            //different functions based on command lines
//            switch (taskName) {
//                case "todo":
//                    tManager.addTask(new Todo(word_split[1]));
//                    break;
//                case "deadline":
//                    try {
//                        taskWord = word_split[1].split("/by ", 2);
//                        taskDescrip = taskWord[0];
//                        String ddl = taskWord[1];
//                        tManager.addTask(new Deadline(taskDescrip, ddl));
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println(" ☹ OOPS!!! The description of a deadline cannot be empty.");
//                    } catch (BaymaxException b){
//                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
//                    }
//                    break;
//                case "event":
//                    try {
//                        taskWord = word_split[1].split("/at ", 2);
//                        taskDescrip = taskWord[0];
//                        String eventTime = taskWord[1];
//                        tManager.addTask(new Event(taskDescrip, eventTime));
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println(" ☹ OOPS!!! The description of an event cannot be empty.");
//                    } catch (BaymaxException b){
//                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
//                    }
//                    break;
//                case "list":
//                    tManager.printTaskList();
//                    break;
//                case "mark":
//                    System.out.println(horiLine);
//                    try {
//                        tManager.markTask(Integer.parseInt(word_split[1]) - 1);
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("Please input an integer for task index.");
//                    } catch (BaymaxException b){
//                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
//                    }
//                    System.out.println(horiLine);
//                    break;
//                case "unmark":
//                    System.out.println(horiLine);
//                    try {
//                        tManager.unmarkTask(Integer.parseInt(word_split[1]) - 1);
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("Please input an integer for task index.");
//                    } catch (BaymaxException b){
//                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
//                    }
//                    System.out.println(horiLine);
//                    break;
//                case "delete":
//                    System.out.println(horiLine);
//                    try {
//                        tManager.deleteTask(Integer.parseInt(word_split[1]) - 1);
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("Please input an integer for task index.");
//                    } catch (BaymaxException b){
//                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
//                    }
//                    //check branch merge
//                    System.out.println(horiLine);
//                    break;
//                default:
//                    System.out.println(horiLine);
//                    System.out.println("Error. Please retry");
//                    System.out.println(horiLine);
//                    break;
//            }
//            command = in.nextLine();
//
//        }
//        tManager.saveTask();
//        tManager.bye(); //bye
//    }


