package brave;

import java.util.Scanner;
import java.lang.String;

public class Brave {
    public static void main(String[] args) {
        String input;
        String[] splitInputs;
        String command;
        String[] arguments;
        String description;
        Scanner in = new Scanner(System.in);
        TaskManager tasks = new TaskManager();

        tasks.showWelcomeMessage();

        while (true) {
            input = in.nextLine();
            splitInputs = input.split(" ", 2);
            command = splitInputs[0]; //e.g. mark 2 -> take the first word as the command -> "mark"

            if (command.equals("bye")) {
                tasks.showFarewellMessage();
                break;
            }

            switch (command) {
            case "list":
                tasks.printTaskList();
                break;
            case "mark":
                try {
                    tasks.markTask(Integer.parseInt(splitInputs[1]) - 1); // 0 indexing
                } catch (NumberFormatException e) {
                    System.out.println("Please put in integer value");
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please put in valid number of task");
                }
                break;
            case "unmark":
                try {
                    tasks.unmarkTask(Integer.parseInt(splitInputs[1]) - 1); // 0 indexing
                } catch (NumberFormatException e) {
                    System.out.println("Please put in integer value");
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please put in valid number of task");
                }
                break;
            case "todo":
                try {
                    description = splitInputs[1];
                    tasks.addTask(new Todo(description));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                // To-do validate arguments~
                arguments = splitInputs[1].split(" /by ", 2);
                description = arguments[0];
                String by = arguments[1];
                tasks.addTask(new Deadline(description, by));
                break;
            case "event":
                // To-do validate arguments~
                arguments = splitInputs[1].split(" /at ", 2);
                description = arguments[0];
                String eventTime = arguments[1];
                tasks.addTask(new Event(description, eventTime));
                break;
            case "delete":
                try {
                    tasks.deleteTask(Integer.parseInt(splitInputs[1]) - 1); // 0 indexing
                } catch (NumberFormatException e) {
                    System.out.println("Please put in integer value");
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please put in valid number of task");
                }
                break;
            default:
                try {
                    throw new IllegalArgumentException();
                } catch (IllegalArgumentException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("Available command are -> list/mark/unmark/todo/deadline/event");
                }
                break;
            }
        }
    }


}
