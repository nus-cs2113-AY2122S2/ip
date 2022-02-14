import duke.DukeException;
import duke.save;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main control class for the App
 */

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm \n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");
        save lastSave = new save("list.txt");
        ArrayList<Task> store = lastSave.fileToStore();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<Task>();



        while(!input.equals("bye")){
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i).getTask());
                    }
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Task currentTask = list.get(taskIndex - 1);
                    currentTask.setDone(true);
                    System.out.println(currentTask.getTask());
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Task currentTask = list.get(taskIndex - 1);
                    currentTask.setDone(false);
                    System.out.println(currentTask.getTask());
                } else if (input.startsWith("todo")) {
                    try {
                        if (input.split("todo").length <= 1) {
                            throw new duke.DukeException("OOPS todo cannot be empty");
                        }
                        String task = input.split("todo")[1].trim();
                        ToDo newTask = new ToDo(task);
                        list.add(newTask);
                        printTask(newTask, list.size());
                    } catch (duke.DukeException e) {
                        System.out.println("The description of a todo cannot be empty.");
                    }
                } else if (input.startsWith("deadline")) {
                    String[] processedString = input.split("/by");
                    String by = processedString[1].trim();
                    String task = processedString[0].split("deadline")[1].trim();
                    Deadline newTask = new Deadline(task, by);
                    list.add(newTask);
                    printTask(newTask, list.size());
                } else if (input.startsWith("event")) {
                    String[] processedString = input.split("/at");
                    String at = processedString[1].trim();
                    String task = processedString[0].split("event")[1].trim();
                    Event newTask = new Event(task, at);
                    list.add(newTask);
                    printTask(newTask, list.size());
                } else if (input.startsWith("complete") || input.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(input.split("")[1]);
                    Task cur = store.get(taskNumber - 1);
                    store.remove(taskNumber - 1);
                    removeTask(cur, store.size());
                } else {
                    throw new DukeException();
                }
            }   catch (DukeException e)
                {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                }
                System.out.println("______________________________________");
                input = sc.nextLine();
        }
        sc.close();
        lastSave.storeToFile(store);
        System.out.println("Bye! Hope to see you again!");
    }

    private static void removeTask(Task cur, int size) {
        System.out.println("I've removed the task: ");
        System.out.println(cur.getTask());
        System.out.println("Now you have "+size+" tasks remaining on your list.");
    }

    public static void printTask(Task newTask, int length) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getTask());
        System.out.println("Now you have " + length + " tasks in the list");
    }
//    public static void printTasks(Task[] tasks, int numTasks){
//        for (int i=0; i<numTasks; i++){
//            System.out.print((i+1) + ". ");
//            tasks[i].getTask();
//        }
    }



