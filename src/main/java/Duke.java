import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // We can assume <= 100 tasks
        Task[] savedTasks = new Task[100];
        int numSavedTasks = 0;

        System.out.println("Hi, I'm Robit!");
        System.out.println("What would you like me to do?");
        System.out.println("------------------------------------------------------------");

        while (true) {
            System.out.print("> ");
            String userInput = in.nextLine();
            System.out.println("------------------------------------------------------------");
            String[] userInputTokenized = userInput.split(" ", 2);
            switch (userInputTokenized[0]) {
            case "bye":
                if (userInputTokenized.length == 1) {
                    System.out.println("Goodbye!");
                    System.out.println("------------------------------------------------------------");
                    return;
                } else {
                    System.out.println("Oops, \"bye\" doesn't take any arguments!");
                }
                break;
            case "list":
                if (userInputTokenized.length == 1) {
                    System.out.println("Here's your to-do list:");
                    for (int i = 0; i < numSavedTasks; i++) {
                        if (savedTasks[i].getIsDone()) {
                            System.out.println((i + 1) + ") [✓] " + savedTasks[i].getTaskDescription());
                        } else {
                            System.out.println((i + 1) + ") [ ] " + savedTasks[i].getTaskDescription());
                        }
                    }
                } else {
                    System.out.println("Oops, \"list\" doesn't take any arguments!");
                }
                break;
            case "add":
                if (userInputTokenized.length == 2) {
                    savedTasks[numSavedTasks] = new Task(userInputTokenized[1]);
                    numSavedTasks++;
                    System.out.println("Task added: " + userInputTokenized[1]);
                } else {
                    System.out.println("Oops, \"add\" expected 1 argument. Try \"add (your task)\" instead.");
                }
                break;
            case "mark":
                try {
                    int index = Integer.parseInt(userInputTokenized[1]);
                    if (index > 0 && index <= numSavedTasks) {
                        if (savedTasks[index - 1].getIsDone()) {
                            System.out.println("That task is already marked!");
                        } else {
                            savedTasks[index - 1].setIsDone(true);
                            System.out.println("I've marked this task as done. Yay!");
                            System.out.println(index + ") [✓] " + savedTasks[index - 1].getTaskDescription());
                        }
                    } else {
                        System.out.println("Oops, there's no task with that index!");
                    }
                } catch (Exception e) {
                    System.out.println("That's not a number!");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(userInputTokenized[1]);
                    if (index > 0 && index <= numSavedTasks) {
                        if (!savedTasks[index - 1].getIsDone()) {
                            System.out.println("That task is already unmarked!");
                        } else {
                            savedTasks[index - 1].setIsDone(false);
                            System.out.println("I've marked this task as unfinished.");
                            System.out.println(index + ") [ ] " + savedTasks[index - 1].getTaskDescription());
                        }
                    } else {
                        System.out.println("Oops, there's no task with that index!");
                    }
                } catch (Exception e) {
                    System.out.println("That's not a number!");
                }
                break;
            default:
                System.out.println("I didn't quite understand that...");
            }

            System.out.println("------------------------------------------------------------");
        }
    }
}
