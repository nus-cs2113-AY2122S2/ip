package baymax.parse;

import baymax.data.Deadline;
import baymax.data.Event;
import baymax.data.TaskManager;
import baymax.data.Todo;
import baymax.storage.Storage;
import baymax.ui.Ui;
import baymax.exception.BaymaxException;

import java.io.IOException;

public class Parser {
    private static boolean isBye;

    private static final String horiLine = "____________________________________________________________\n";

    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }
    public void setExit(boolean bye) {
        isBye = bye;
    }

    public boolean getExit() {
        return isBye;
    }

    public void parse(String fullCommand, TaskManager tManager) throws IOException {
        String[] word_split;
        word_split = fullCommand.split(" ", 2);
        String command = word_split[0];

        if (command.equals("bye")) {
            Storage.encode(tManager.getTasks(), "data/Baymax.txt");
            setExit(true);
            return;
        }

        String taskDescrip;
        switch (command) {
                case "todo":
                    tManager.addTask(new Todo(word_split[1]));
                    break;
                case "deadline":
                    try {
                        String[] taskWord = word_split[1].split("/by ", 2);
                        taskDescrip = taskWord[0];
                        String ddl = taskWord[1];
                        tManager.addTask(new Deadline(taskDescrip, ddl));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "event":
                    try {
                        String[] taskWord = word_split[1].split("/at ", 2);
                        taskDescrip = taskWord[0];
                        String eventTime = taskWord[1];
                        tManager.addTask(new Event(taskDescrip, eventTime));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(" ☹ OOPS!!! The description of an event cannot be empty.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "list":
                    ui.printTaskList(tManager.getTasks());
                    break;
                case "mark":
                    try {
                        tManager.markTask(Integer.parseInt(word_split[1]) - 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input an integer for task index.");
                    } catch (NumberFormatException e) {
                        System.out.println("Please put in integer value");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "unmark":
                    try {
                        tManager.unmarkTask(Integer.parseInt(word_split[1]) - 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input an integer for task index.");
                    } catch (NumberFormatException e) {
                        System.out.println("Please put in integer value");
                    }catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "delete":
                    try {
                        tManager.deleteTask(Integer.parseInt(word_split[1]) - 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input an integer for task index.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    //check branch merge
                    break;
                default:
                    System.out.println("Error. Please retry");
                    break;
        }
    }
}
