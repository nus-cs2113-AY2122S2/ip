package duke;

import exceptions.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Duke {
    private static Task[] taskList = new Task [100];
    static int taskIndex=0;

    public static void main(String[] args) {

        greetUser("hi");
        startChatbot();
        greetUser("bye");
    }

    private static void startChatbot() {
        readTaskFile();
        Scanner input = new Scanner(System.in);
        String task = "";
        while(true) {
            try {
                task = input.nextLine();
                System.out.println("    ____________________________________");
                if (task.equals("bye")) {
                    break;
                } else if (task.equals("list")) {
                    listTasks();
                } else if (task.startsWith("mark ")) {
                    markTask(task);
                } else if (task.startsWith("unmark ")) {
                    unmarkTask(task);
                } else if (task.startsWith("todo") || task.startsWith("deadline") || task.startsWith("event")) {
                    addTask(task);
                } else {
                    throw new DukeException();
                }
                System.out.println("    ____________________________________");
            } catch (DukeException e) {
                System.out.println("    Sorry I do not know what that means!");
            }

        }
    }

    private static void greetUser(String greeting) {
        if(greeting.equals("hi")) {
            String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo);
            System.out.println("    ____________________________________");
            System.out.println("    Hello! I'm Duke");
            System.out.println("    What can I do for you?\n");
        }
        else {
            System.out.println("    ____________________________________");
            System.out.println("    Bye. Hope to see you again soon!");
            System.out.println("    ____________________________________");
        }
    }

    public static void listTasks() {
        System.out.println("    Here are the tasks in your list:");
        for(int i=0;i< taskList.length;i++) {
            if(taskList[i] == null) break;
            System.out.println("    "+(i+1)+": "+taskList[i]);
        }
    }

    public static void markTask(String task) {
        try {
            task = task.replace("mark ", "");
            int i = Integer.parseInt(task) - 1;
            if (taskList[i] != null) {
                taskList[i].setDone(true);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + taskList[i]);
                writeTaskFile();
            } else {
                System.out.println("    Please enter a valid task number");
            }
        } catch (IndexOutOfBoundsException e) {
            if(taskIndex==0) {
                System.out.println("    OOPS there are no tasks in the list");
            } else {
                System.out.println("    OOPS there are only " + taskIndex + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please enter the number of the task you want to mark");
        }
    }

    public static void unmarkTask(String task) {
        try {
            task = task.replace("unmark ","");
            int i = Integer.parseInt(task)-1;
            if(taskList[i]!=null) {
                taskList[i].setDone(false);
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("        "+taskList[i]);
                writeTaskFile();
            } else {
                System.out.println("    Please enter a valid task number");
            }
        } catch (IndexOutOfBoundsException e) {
            if(taskIndex==0) {
                System.out.println("    OOPS there are no tasks in the list");
            } else {
                System.out.println("    OOPS there are only " + taskIndex + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please enter the number of the task you want to unmark");
        }

    }

    public static void addTask(String task) {
        try {
            int index = task.indexOf(' ');
            String taskType = task.substring(0, index);
            task = task.substring(index + 1);
            switch (taskType) {
            case "todo":
                addAsTodo(task);
                break;
            case "deadline":
                addAsDeadline(task);
                break;
            case "event":
                addAsEvent(task);
                break;
            default:
                System.out.println("    Sorry I do not know what that means");
                return;
            }
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + taskList[taskIndex - 1]);
            System.out.println("    Now you have " + (taskIndex) + " tasks in the list.");
            writeTaskFile();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! The decription of a "+task+" cannot be empty");
        }
    }

    private static void addAsTodo(String task) {
        taskList[taskIndex++] = new Todo(task);
    }
    private static void addAsEvent(String task) {
        int index;
        index = task.indexOf("/");
        String eventTime = task.substring(index+1);
        task = task.substring(0,index-1);

        index = eventTime.indexOf(' ');
        eventTime = eventTime.substring(index+1);
        taskList[taskIndex++] = new Event(task,eventTime);
    }

    private static void addAsDeadline(String task) {
        int index;
        index = task.indexOf("/");
        String by = task.substring(index+1);
        task = task.substring(0,index-1);

        index = by.indexOf(' ');
        by = by.substring(index+1);
        taskList[taskIndex++] = new Deadline(task,by);
    }

    //we can perform a read task file at the start of every operation to update the taskList from there
    //then we can write into the task file everytime something is changed- after each task whether it is an add or
    // delete or mark or unmark we should update the file each time
    private static void readTaskFile() {
        try {
            File dukeFile = new File("data/duke.txt");
            if(dukeFile.createNewFile()) {
                System.out.println("    I have created \"duke.txt\" to store your tasks: "+dukeFile.getName());
            } else {
                System.out.println("    I have restored your tasks from the last session!");
            }
            Scanner s = new Scanner(dukeFile);
            List<String> taskInfo;
            while(s.hasNext()) {
                taskInfo = Arrays.asList(s.nextLine().split(" \\| "));
//                System.out.println(taskInfo);
                switch(taskInfo.get(0)) {
                case "T":
                    taskList[taskIndex++] = new Todo(taskInfo.get(2));
                    taskList[taskIndex-1].isDone = Objects.equals(taskInfo.get(1), "1");
                    break;
                case "E":
                    taskList[taskIndex++] = new Event(taskInfo.get(2),taskInfo.get(3));
                    taskList[taskIndex-1].isDone = Objects.equals(taskInfo.get(1), "1");
                    break;
                case "D":
                    taskList[taskIndex++] = new Deadline(taskInfo.get(2),taskInfo.get(3));
                    taskList[taskIndex-1].isDone = Objects.equals(taskInfo.get(1), "1");
                    break;
                }
            }
            listTasks();
        } catch (IOException e) {
            System.out.println("Could not create file");
            e.printStackTrace();
        }
    }
    private static void writeTaskFile() {
        try {
            File dukeFile = new File("data/duke.txt");
            FileWriter fw = new FileWriter(dukeFile.getAbsolutePath());
            String taskType="";
            for(Task t: taskList) {
                if (t==null) {
                    break;
                }
                if(t instanceof Todo) {
                    taskType = "T";
                    fw.write(taskType+" | "+(t.isDone()?1:0)+" | "+ t.getTitle());
                    fw.write(System.lineSeparator());
                }
                else if(t instanceof Deadline) {
                    taskType = "D";
                    fw.write(taskType+" | "+(t.isDone()?1:0)+" | "+ t.getTitle()+" | "+((Deadline) t).getBy());
                    fw.write(System.lineSeparator());
                }
                else if (t instanceof Event) {
                    taskType = "E";
                    fw.write(taskType+" | "+(t.isDone()?1:0)+" | "+ t.getTitle()+" | "+ ((Event) t).getEventTime());
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
