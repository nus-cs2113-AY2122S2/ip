package duke;


import java.io.IOException;
import java.util.Scanner;

import exceptions.UnknownCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.startProgram();
        converse();
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void converse() {
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        boolean isNotBye = !response.equals("bye");

        while (isNotBye) {
            try {
                runCommand(response, tasks, storage);
            } catch (UnknownCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (NullPointerException e) {
                System.out.println("☹ OOPS!!! Description cannot be empty!");
            }
            response = sc.nextLine();
            isNotBye = !response.equals("bye");
        }
    }

    private void runCommand(String response, TaskList tasks, Storage storage) throws UnknownCommandException {
        String[] words = response.split(" ", 2);
        String command = words[0];
        String detail = (words.length > 1) ? words[1] : null;

        boolean hasAddedTask = false;
        boolean hasUpdate = false;

        switch (command) {
        case "list":
            listTasks(tasks);
            break;
        case "mark":
            hasUpdate = markTask(response);
            break;
        case "unmark":
            hasUpdate = unmarkTask(response);
            break;
        case "todo":
            addTask(new Todo(detail));
            hasAddedTask = true;
            break;
        case "deadline":
            addDeadline(detail);
            hasAddedTask = true;
            break;
        case "event":
            addEvent(detail);
            hasAddedTask = true;
            break;
        case "delete":
            deleteTask(response);
            hasUpdate = true;
            break;
        default:
            throw new UnknownCommandException();
        }

        if (hasAddedTask || hasUpdate) {
            storage.updateAndSave(tasks.taskArrayList);
        }
    }

    public void listTasks(TaskList tasks) {
        ui.printLine();

        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks.getTaskViaIndex(i));
        }

        ui.printLine();
    }

    public void addTask(Task task) {
        ui.printLine();

        tasks.addTaskToList(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");

        ui.printLine();
    }

    public void addDeadline(String detail) {
        int byIndex = detail.indexOf("/by");
        boolean isByPresent = byIndex != -1;
        if (isByPresent) {
            String by = detail.substring(byIndex + 3).trim();
            String deadlineDesc = detail.substring(0, byIndex).trim();
            addTask(new Deadline(deadlineDesc, by));
        } else {
            System.out.println("Sorry, missing /by argument...");
        }
    }

    public void addEvent(String detail) {
        int atIndex = detail.indexOf("/at");
        boolean isAtPresent = atIndex != -1;
        if (isAtPresent) {
            String at = detail.substring(atIndex + 3).trim();
            String eventDesc = detail.substring(0, atIndex).trim();
            addTask(new Event(eventDesc, at));
        } else {
            System.out.println("Sorry, missing /at argument...");
        }
    }


    public boolean markTask(String response) {
        ui.printLine();

        try {
            String[] words = response.split(" ");
            int taskIndex = Integer.parseInt(words[1]);
            boolean isNotIndex = taskIndex > tasks.getSize() || taskIndex == 0 || taskIndex < 0;

            if (isNotIndex) {
                System.out.println("Sorry, seems like there's no such task with that index.");
                ui.printLine();
                return false;
            } else {
                Task t = tasks.getTaskViaIndex(taskIndex - 1); //list indexing to the user starts from 1 but list indexing in fact starts from 0 internally
                boolean isNotDone = !t.isDone;

                if (isNotDone) {
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                } else {
                    System.out.println("Hmm, you've already marked this task.");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You forgot to indicate an index!");
        }

        ui.printLine();
        return true;
    }

    public boolean unmarkTask(String response) {
        ui.printLine();

        try {
            String[] words = response.split(" ");
            int taskIndex = Integer.parseInt(words[1]);
            boolean isNotIndex = taskIndex > tasks.getSize() || taskIndex == 0;

            if (isNotIndex) {
                System.out.println("Sorry, seems like there's no such task with that index.");
                ui.printLine();
                return false;
            } else {
                Task t = tasks.getTaskViaIndex(taskIndex - 1); //list indexing to the user starts from 1 but list indexing in fact starts from 0 internally
                boolean isDone = t.isDone;

                if (isDone) {
                    t.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                } else {
                    System.out.println("Hmm, this task is already unmarked.");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You forgot to indicate an index!");
        }

        ui.printLine();
        return true;
    }

    private void deleteTask(String response) {
        ui.printLine();

        try {
            String[] words = response.split(" ");
            int taskIndex = Integer.parseInt(words[1]);
            boolean isNotIndex = taskIndex > tasks.getSize() || taskIndex == 0;

            if (isNotIndex) {
                System.out.println("Sorry, seems like there's no such task with that index.");
                ui.printLine();
                return;
            } else {
                Task t = tasks.getTaskViaIndex(taskIndex - 1);
                tasks.removeTaskFromList(t);
                System.out.println("Noted. I've removed this task:");
                System.out.println(t);
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You forgot to indicate an index!");
        }

        ui.printLine();
    }
}
