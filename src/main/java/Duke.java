import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput = "";

        Task[] taskList = new Task[100];
        for (int i = 0; i < taskList.length;i++) {
            taskList[i] = new Task(); //loops through and individually initialise each
        }                             //Task in the array, else they remain as null
        int count = 0;

        String line = "------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");

        while (!userInput.equals("bye")) {
            System.out.println(line);
            userInput = in.nextLine();
            String[] words = userInput.split(" ");
            switch (words[0]) {
            case "list":
                listTaskList(taskList,count);
                break;
            case "mark":
            case "unmark":
                editList(taskList, words[0], words[1], count);
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                addToTaskList(taskList, userInput, count);
                count++;
                break;
            }
        }
    }

    private static void addToTaskList(Task[] taskList, String userInput, int count) {
        System.out.println("added: " + userInput);
        taskList[count].setDescription(userInput);
    }

    private static void listTaskList(Task[] taskList, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0;i < count;i++) {
            System.out.println(i + 1 +".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
    }

    private static void editList(Task[] taskList, String command, String taskNumber, int count) {
        if (!validateTaskNumber(taskNumber)) {
            System.out.println("Oops! Seems like a number was not given. Try again!");
            return;
        }
        int number = Integer.parseInt(taskNumber) - 1;
        if (number >= count) {
            System.out.println("Oops! The number given is larger than the list currently. Try again!");
        } else if (command.equals("mark")) {
            taskList[number].setIsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + taskList[number].getStatusIcon() + "] " + taskList[number].getDescription());
        } else {
            taskList[number].unsetIsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + taskList[number].getStatusIcon() + "] " + taskList[number].getDescription());
        }
    }

    private static boolean validateTaskNumber(String taskNumber) {
        try {
            int number = Integer.parseInt(taskNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
