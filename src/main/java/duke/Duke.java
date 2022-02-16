package duke;

import exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
//    private static Task[] taskList = new Task [100];
    static int taskIndex=0;

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        greetUser("hi");
        startChatbot();
        greetUser("bye");
    }

    private static void startChatbot() {
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
                } else if (task.startsWith("delete")) {
                    deleteTask(task);
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
//        for(int i=0;i< taskList.length;i++) {
//            if(taskList[i] == null) break;
//            System.out.println("    "+(i+1)+": "+taskList[i]);
//        }
        int i=1;
        for(Task t:taskList) {
            System.out.println("    "+(i++ )+": "+t);
        }
    }

    public static void markTask(String task) {
        try {
            task = task.replace("mark ", "");
            int i = Integer.parseInt(task) - 1;
//            if (taskList[i] != null) {
//                taskList[i].setDone(true);
//                System.out.println("    Nice! I've marked this task as done:");
//                System.out.println("    " + taskList[i]);
//            }
            if(taskList.get(i)!=null) {
                taskList.get(i).setDone(true);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + taskList.get(i));
            } else {
                System.out.println("    Please enter a valid task number");
            }
        } catch (IndexOutOfBoundsException e) {
            if(taskList.size()==0) {
                System.out.println("    OOPS there are no tasks in the list");
            } else {
                System.out.println("    OOPS there are only " + taskList.size() + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please enter the number of the task you want to mark");
        }
    }

    public static void unmarkTask(String task) {
        try {
            task = task.replace("unmark ","");
            int i = Integer.parseInt(task)-1;
//            if(taskList[i]!=null) {
//                taskList[i].setDone(false);
//                System.out.println("    OK, I've marked this task as not done yet:");
//                System.out.println("        "+taskList[i]);
//            }
            if(taskList.get(i)!=null) {
                taskList.get(i).setDone(false);
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("    " + taskList.get(i));
            } else {
                System.out.println("    Please enter a valid task number");
            }
        } catch (IndexOutOfBoundsException e) {
            if(taskList.size()==0) {
                System.out.println("    OOPS there are no tasks in the list");
            } else {
                System.out.println("    OOPS there are only " + taskList.size() + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please enter the number of the task you want to unmark");
        }

    }

    public static void deleteTask(String task) {
        try {
            task = task.replace("delete ","");
            int i = Integer.parseInt(task)-1;
            if(taskList.get(i)!=null) {
                System.out.println("deleting task");
                Task removedTask = taskList.get(i);
                taskList.remove(i);
                System.out.println("    Noted. I've removed this task:");
                System.out.println("    "+removedTask);
                System.out.println("    Now you have "+taskList.size()+" tasks in the list");
            }
        } catch (IndexOutOfBoundsException e) {
            if(taskList.size()==0) {
                System.out.println("    OOPS there are no tasks in the list");
            } else {
                System.out.println("    OOPS there are only " + taskList.size() + " tasks in the list");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please enter the number of the task you want to delete");
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
//            System.out.println("        " + taskList[taskIndex - 1]);
            System.out.println("        " + taskList.get(taskList.size()-1));
            System.out.println("    Now you have " + (taskList.size()) + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! The decription of a "+task+" cannot be empty");
        }
    }

    private static void addAsTodo(String task) {
//        taskList[taskIndex++] = new Todo(task);
        taskList.add(new Todo(task));
    }
    private static void addAsEvent(String task) {
        int index;
        index = task.indexOf("/");
        String eventTime = task.substring(index+1);
        task = task.substring(0,index-1);

        index = eventTime.indexOf(' ');
        eventTime = eventTime.substring(index+1);
//        taskList[taskIndex++] = new Event(task,eventTime);
        taskList.add(new Event(task,eventTime));
    }

    private static void addAsDeadline(String task) {
        int index;
        index = task.indexOf("/");
        String by = task.substring(index+1);
        task = task.substring(0,index-1);

        index = by.indexOf(' ');
        by = by.substring(index+1);
//        taskList[taskIndex++] = new Deadline(task,by);
        taskList.add(new Deadline(task,by));
    }
}
