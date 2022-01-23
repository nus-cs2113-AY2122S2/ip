import java.util.Scanner;

public class Sora {
    private void printGreetings() {
        String logo = "     _______.  ______   .______          ___      \n"
                + "    /       | /  __  \\  |   _  \\        /   \\     \n"
                + "   |   (----`|  |  |  | |  |_)  |      /  ^  \\    \n"
                + "    \\   \\    |  |  |  | |      /      /  /_\\  \\   \n"
                + ".----)   |   |  `--'  | |  |\\  \\----./  _____  \\  \n"
                + "|_______/     \\______/  | _| `._____/__/     \\__\\";

        // Print banner
        System.out.println(logo);
        Helper.printLine();

        // Print greetings
        if (Helper.getHourOfDay() < 12) {
            System.out.print("Good morning! ");
        } else if (Helper.getHourOfDay() < 18) {
            System.out.print("Good afternoon! ");
        } else {
            System.out.print("Good evening. ");
        }

        System.out.println("I'm Sora ヽ(・∀・)ﾉ");
        System.out.println("What can I do for you?");
        Helper.printLine();
    }

    public void printGoodbye() {
        if (Helper.getHourOfDay() < 18) {
            System.out.println("Goodbye! Have a great day ahead (⌒▽⌒)☆");
        } else if (Helper.getHourOfDay() < 22) {
            System.out.println("Goodbye! Have a good evening <(￣︶￣)>");
        } else {
            System.out.println("Good night, have a good rest... (－ω－) zzZ");
        }

        System.out.println("See you again soon~");
        Helper.printLine();
    }

    public static void main(String[] args) {
        // Start Sora and components
        Sora sora = new Sora();
        TaskList taskList = new TaskList();
        sora.printGreetings();

        // Get user command
        Scanner in = new Scanner(System.in);
        String userInput;

        System.out.print("> ");
        userInput = in.nextLine();
        Helper.printLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                // Display the task list
                System.out.println(Helper.getRandomAcknowledgement()
                        + ", here's the list of tasks that you have given to me:");
                System.out.println();
                taskList.displayList();
                System.out.println();

            } else if (userInput.toLowerCase().startsWith("mark")) {
                // Obtain task number
                int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                boolean markSuccess = taskList.updateDoneStatus(taskNum, true);

                if (markSuccess) {
                    System.out.println(Helper.getRandomAcknowledgement()
                            + ", I've marked this task as done:");
                    System.out.println();
                    taskList.displayTask(taskNum);
                    System.out.println();
                } else {
                    System.out.println("Oops, I couldn't mark that task as done.");
                    System.out.println("Sorry about that... (-ω-、)");
                }
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                // Obtain task number
                int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                boolean unmarkSuccess = taskList.updateDoneStatus(taskNum, false);

                if (unmarkSuccess) {
                    System.out.println(Helper.getRandomAcknowledgement()
                            + ", I've marked this task as not done:");
                    System.out.println();
                    taskList.displayTask(taskNum);
                    System.out.println();
                } else {
                    System.out.println("Oops, I couldn't mark that task as not done.");
                    System.out.println("Sorry about that... (-ω-、)");
                }
            } else {
                // Add text to list
                boolean addSuccess = taskList.addTask(userInput);

                if (addSuccess) {
                    System.out.println(Helper.getRandomAcknowledgement()
                            + ", I have added your text to my list:");
                    System.out.println();
                    System.out.println("\t" + userInput);
                    System.out.println();
                } else {
                    System.out.println("Oops! Somehow I wasn't able to add your text to my list...");
                    System.out.println("Sorry about that! (-ω-、)");
                }
            }

            // Get next user prompt
            Helper.printLine();
            System.out.println("What's next?");
            Helper.printLine();
            System.out.print("> ");
            userInput = in.nextLine();
            Helper.printLine();
        }

        // User has entered "bye"
        sora.printGoodbye();
    }
}
