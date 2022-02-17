import tasks.*;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Duke {



    public static void main(String[] args) throws DukeException, IOException {
        DialogGenerator dialog = new DialogGenerator();

        greeting(dialog);
        manageUserInput(dialog);

    }

    public void checkFileExist() throws IOException {

        File dukeTxt = new File("./data/duke.txt");
        if (!dukeTxt.createNewFile()){

        }
    }

    public static void greeting(DialogGenerator dialog) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(""+logo);
        dialog.printLine();
        System.out.println("Hello I'm Duke.");
        System.out.println("What can I do for you?");
        dialog.printLine();
        System.out.println();
    }

    private static void writeToDukeFile(ArrayList<Task> allTasks) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        for(Task t:allTasks){
            fw.write(t.toString()+"\n");
        }
        fw.close();
    }

    /**
     * java.DukeException to represent exceptions specific to main.
     */
    public static void manageUserInput(DialogGenerator dialog) throws DukeException, IOException {
        int index;
        boolean notQuit = true;

        Task t;
        ArrayList<Task> allTasks = new ArrayList<Task>();

        File dataDir = new File("./data");
        File dataText = new File("./data/duke.txt");
        if (!dataDir.exists()){
            dataDir.mkdirs();
        }
        if (!dataText.exists()){
            dataText.createNewFile();
        }
        while (notQuit) {
            String command;
            String detail = null;
            String description = null;
            String at = null;
            String by = null;

            Scanner in = new Scanner(System.in);
            command = in.nextLine();

            switch (command.split(" ")[0]) {
                case "bye":
                    dialog.sayGoobye();
                    notQuit = false;
                    break;

                case "mark":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    t = allTasks.get(index);
                    dialog.markAndDisplayTask(t);
                    break;

                case "unmark":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    t = allTasks.get(index);
                    dialog.unmarkAndDisplayTask(t);
                    break;

                case "list":
                    dialog.displayListWithStatus(allTasks, allTasks.size());
                    break;

                case "delete":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    dialog.deleteAndDisplayTask(allTasks.get(index), allTasks.size() - 1);
                    allTasks.remove(index);
                    writeToDukeFile(allTasks);
                    break;

                case "todo":
                    try{
                        String[] str = command.split(" ");
                        if(str.length < 2){
                            throw new DukeException();
                        }
                        description = command.replace("todo ","");
                    }catch (DukeException e){
                        dialog.raiseExceptionInTodo();
                        break;
                    }

                    t = new Todo(description);
                    allTasks.add(allTasks.size(), t);
                    dialog.displayTask(t, allTasks.size());
                    writeToDukeFile(allTasks);
                    break;

                case "deadline":
                    try{
                        String[] str = command.split(" ");
                        if(str.length < 2){
                            throw new DukeException();
                        }
                        detail = command.replace("deadline ","");
                        description = detail.split("/by")[0];
                        by = detail.split("/by")[1];
                        /**
                         * To avoid content with only spacing.
                         */
                        if(by.replace(" ", "")==""){
                            throw new DukeException();
                        }
                    }catch (DukeException e){
                        dialog.raiseExceptionInDeadline();
                        break;
                    }catch (ArrayIndexOutOfBoundsException e){
                        dialog.raiseExceptionInDeadline();
                        break;
                    }

                    t = new Deadline(description, by);
                    allTasks.add(t);
                    dialog.displayTask(t, allTasks.size());
                    writeToDukeFile(allTasks);
                    break;

                case "event":
                    try{
                        String[] str = command.split(" ");
                        if(str.length < 2){
                            throw new DukeException();
                        }
                        detail = command.replace("event ","");
                        description = detail.split("/at")[0];
                        at = detail.split("/at")[1];
                        /**
                         * To avoid content with only spacing.
                         */
                        if(at.replace(" ", "")==""){
                            throw new DukeException();
                        }
                    }catch (DukeException e){
                        dialog.raiseExceptionInEvent();
                        break;
                    }catch (ArrayIndexOutOfBoundsException e){
                        dialog.raiseExceptionInEvent();
                        break;
                    }

                    t = new Event(description, at);
                    allTasks.add(t);
                    dialog.displayTask(t, allTasks.size());
                    writeToDukeFile(allTasks);
                    break;

                default:
                    try{
                        throw new DukeException();
                    }catch (DukeException e){
                        dialog.raiseExceptionInCommand();
                    }
                    break;
            }
        }
    }
}
