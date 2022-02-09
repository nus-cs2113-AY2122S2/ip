package duke.main;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.Event;
import duke.task.Todo;
import java.util.Scanner;

public class Duke{
    public static final String LINEBREAK = "____________________________________________________________";
    public static Task[] list = new Task[100];
    public static Scanner in = new Scanner(System.in);
    public static int taskCount = 0;
    public static void listTasks(){
        System.out.println(LINEBREAK);
        for (int j = 0; j < taskCount; j++){
            System.out.println(Integer.toString(j + 1) + list[j]);
        }
        System.out.println(LINEBREAK);
    }
    public static void updateMarkTask(String line, boolean mark){
        int index;
        try{
            index = Integer.parseInt(line.split(" ")[1]) - 1;
            if (index >= taskCount || index < 0){
                throw new DukeException("Error: Task Index is out of bounds.");
            }
        }catch (NumberFormatException e){
            System.out.println(LINEBREAK);
            System.out.println(" Error: Invalid index (Not an integer).");
            System.out.println(LINEBREAK);
            return;
        }catch (DukeException e){
            System.out.println(LINEBREAK);
            System.out.println(e.getMessage());
            System.out.println(LINEBREAK);
            return;
        }
        System.out.println(LINEBREAK);
        if (mark){
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("  [X] " + list[index].getName());
        }else{
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + list[index].getName());
        }
        System.out.println(LINEBREAK);
        list[index].setMarked(mark);
    }
    public static void addNewTask(String line){
        list[taskCount] = new Task(line, false);
        String taskType = line.split(" ")[0];
        if (line.length() > taskType.length()){
            line = line.substring(taskType.length() + 1);
        }else{
            line = "";
        }
        try {
            switch (taskType) {
            case "todo":
                if (line.equals("")){
                    throw new DukeException("Error: Argument of todo should not be empty.");
                }
                list[taskCount] = new Todo(line, false);
                break;
            case "deadline":
                String[] taskNameAndDeadline = line.split(" /by ");
                if (taskNameAndDeadline.length < 2){
                    throw new DukeException("Error: A Deadline Task should have the deadline.");
                }
                list[taskCount] = new Deadline(taskNameAndDeadline[0], false, taskNameAndDeadline[1]);
                break;
            case "event":
                String[] taskNameAndTiming = line.split(" /at ");
                if (taskNameAndTiming.length < 2){
                    throw new DukeException("Error: An Event Task should have the event timing.");
                }
                list[taskCount] = new Event(taskNameAndTiming[0], false, taskNameAndTiming[1]);
                break;
            default:
                throw new DukeException("Error: Command not recognised.");
            }
        }catch (DukeException e){
            System.out.println(LINEBREAK);
            System.out.println(e.getMessage());
            System.out.println(LINEBREAK);
            return;
        }

        System.out.println(LINEBREAK);
        System.out.println(" added: " + list[taskCount]);
        System.out.println(" Total number of tasks now: " + (taskCount + 1));
        System.out.println(LINEBREAK);
        taskCount++;
    }
    public static void waitForInput(){
        while (true){
            String line = in.nextLine();
            if (line.equals("bye")){
                break;
            }
            if (line.equals("list")){
                listTasks();
            }else if (line.split(" ")[0].equals("mark")){
                updateMarkTask(line, true);
            }else if (line.split(" ")[0].equals("unmark")){
                updateMarkTask(line, false);
            }else{
                addNewTask(line);
            }

        }
    }
    public static void main(String[] args){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINEBREAK);
        System.out.println(" Hello! I'm Duke\n What can I do for you?");
        System.out.println(LINEBREAK);

        waitForInput();
        System.out.println(LINEBREAK);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINEBREAK);
    }
}
