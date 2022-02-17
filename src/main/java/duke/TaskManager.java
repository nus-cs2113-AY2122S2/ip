package duke;

import java.util.Scanner;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {

    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int idx;
        displaySupportedCmds();

        String input = sc.nextLine();
        String[] inputs = input.split(" ", 2);
        while(!inputs[0].equals("bye")) {
            switch (inputs[0]) {
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                try {
                    addTask(inputs[0], inputs[1]);
                    System.out.println("\t" + "-".repeat(60));
                    System.out.println("\t Got it. I've added this task:");
                    System.out.println("\t\t" + tasks.get(tasks.size() - 1).toString());
                    System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("\t" + "-".repeat(60));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\t" + "-".repeat(60));
                    System.out.println("\t ☹ OOPS!!! The description of a " + inputs[0] + " cannot be empty.");
                    System.out.println("\t" + "-".repeat(60));
                } catch (DukeException e) {
                    System.out.println("\t" + "-".repeat(60));
                    System.out.println("\t " + e.getMessage());
                    System.out.println("\t" + "-".repeat(60));
                }
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    idx = Integer.parseInt(inputs[1]);
                    markTask(idx);
                } catch (NumberFormatException e) {
                    System.out.println("\t" + "-".repeat(60));
                    System.out.println("\t Index is not an integer. Please try again.");
                    System.out.println("\t" + "-".repeat(60));
                } catch (DukeException e) {
                    System.out.println("\t" + "-".repeat(60));
                    System.out.println("\t " + e.getMessage() + " Please try again.");
                    System.out.println("\t" + "-".repeat(60));
                }
                break;
            case "unmark":
                try {
                    idx = Integer.parseInt(inputs[1]);
                    unmarkTask(idx);
                } catch (NumberFormatException e) {
                    System.out.println("\t" + "-".repeat(60));
                    System.out.println("\t Index is not an integer. Please try again.");
                    System.out.println("\t" + "-".repeat(60));
                } catch (DukeException e) {
                    System.out.println("\t" + "-".repeat(60));
                    System.out.println("\t " + e.getMessage() + " Please try again.");
                    System.out.println("\t" + "-".repeat(60));
                }
                break;
            default:
                displayInvalidCmd();
                break;
            }
            input = sc.nextLine();
            inputs = input.split(" ", 2);
        }
    }

    private void displaySupportedCmds() {
        // Supported commands
        System.out.println("\t Supported commands:");
        System.out.println("\t Type \"todo <task>\" to add a todo task");
        System.out.println("\t Type \"deadline <task> /by <time>\" to add a deadline task");
        System.out.println("\t Type \"event <task> /at <time>\" to add a event task");
        System.out.println("\t Type \"list\" to list all tasks");
        System.out.println("\t Type \"mark <task number>\" to mark a task");
        System.out.println("\t Type \"unmark <task number>\" to unmark a task");
        System.out.println("\t Type \"bye\" to exit");
        System.out.println("\t" + "-".repeat(60));
    }

    public void addTask(String option, String taskDescription) throws DukeException{
        if(option.equals("todo")) {
            taskDescription = taskDescription.trim();
            tasks.add(new Todo(taskDescription));
            //tasks[taskCount++] = new Todo(taskDescription.trim());
            return;
        }

        if(option.equals("deadline")) {
            try {
                String[] descriptions = taskDescription.split("/by", 2);
                descriptions[0] = descriptions[0].trim();
                descriptions[1] = descriptions[1].trim();
                tasks.add(new Deadline(descriptions[0], descriptions[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Deadline is not specified.");
            }
        } else if(option.equals("event")) {
            try {
                String[] descriptions = taskDescription.split("/at", 2);
                descriptions[0] = descriptions[0].trim();
                descriptions[1] = descriptions[1].trim();
                tasks.add(new Event(descriptions[0], descriptions[1]));
            } catch (IndexOutOfBoundsException e){
                throw new DukeException("Event time is not specified.");
            }
        }
    }

    public void listTasks() {
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\t Here are the tasks in your list:");
        if(tasks.size() == 0) {
            System.out.println("\t No task recorded.");
            System.out.println("\t" + "-".repeat(60));
            return;
        }

        for(int i = 0;i < tasks.size(); i++) {
            System.out.println("\t\t " + (i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("\t" + "-".repeat(60));
    }

    public void markTask(int idx) throws DukeException {
        if(idx > tasks.size()){
            throw new DukeException("Task index out of bound.");
        }
        tasks.get(idx-1).markAsDone();
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + idx + "." + tasks.get(idx-1).toString());
        System.out.println("\t" + "-".repeat(60));
    }

    public void unmarkTask(int idx) throws DukeException {
        if(idx > tasks.size()){
            throw new DukeException("Task index out of bound.");
        }
        tasks.get(idx-1).unmark();
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t\t " + idx + "." + tasks.get(idx-1).toString());
        System.out.println("\t" + "-".repeat(60));
    }

    public void displayInvalidCmd() {
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\t ☹ OOPS!!! I don't know what that means. Please try again.");
        System.out.println("\t" + "-".repeat(60));
    }
}
