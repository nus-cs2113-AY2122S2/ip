import utilities.*;
import tasks.*;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) throws DukeException {
        DialogGenerator dialog = new DialogGenerator();

        greeting(dialog);
        manageUserInput(dialog);

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

    /**
     * utilities.DukeException to represent exceptions specific to Duke.
     */
    public static void manageUserInput(DialogGenerator dialog) throws DukeException {
        final int TASK_LENGTH = 100;
        int index;
        int taskCount = 0;
        boolean notQuit = true;

        Task t;
        Task[] allTasks = new Task[TASK_LENGTH];

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
                    t = allTasks[index];
                    dialog.markAndDisplayTask(t);
                    break;

                case "unmark":
                    index = Integer.parseInt(command.split(" ")[1]) - 1;
                    t = allTasks[index];
                    dialog.unmarkAndDisplayTask(t);
                    break;

                case "list":
                    dialog.displayListWithStatus(allTasks, taskCount);
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
                    allTasks[taskCount] = t;
                    taskCount += 1;
                    dialog.displayTask(t, taskCount);
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
                    allTasks[taskCount] = t;
                    taskCount += 1;
                    dialog.displayTask(t, taskCount);
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
                    allTasks[taskCount] = t;
                    taskCount += 1;
                    dialog.displayTask(t, taskCount);
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
