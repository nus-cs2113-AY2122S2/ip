import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void displayLogo() {
        String logo = "                       ___\n"
                +"                      / ()\\\n"
                +"                    _|_____|_\n"
                +"                   | | === | |\n"
                +" _____  _      _   |_|  0  |_|\n"
                +"| ___| | \\    / |   ||  0  ||\n"
                +"| |__  |  \\  /  |   ||__*__||\n"
                +"|  __| |   \\/   |  |~ \\___/ ~| \n"
                +"| |___ | |\\__/| |  /=\\ /=\\ /=\\ \n"
                +"|_____||_|    |_|__[_]_[_]_[_]______________________________\n";
        String greetings = "____________________________________________________________\n"
                + "Hello! I'm EM\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo + greetings);
    }

    public static void displayFarewell() {
        String farewell = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(farewell);
    }

    public static void displayTaskList(int listCount, Task[] taskList) {
        String lineSeparator = "____________________________________________________________\n";
        System.out.println(lineSeparator);
        System.out.println("Here are the tasks in your list:");
        if (listCount == 0) {
            System.out.println("List is Empty!");
        }

        for(int i = 0; i < listCount; i++) {
            System.out.println((i+1) + ". " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
        System.out.println(lineSeparator);
    }

    public static void main(String[] args) {
        String lineSeparator = "____________________________________________________________\n";
        displayLogo();

        String userInput;
        Task[] taskList = new Task[100];
        int listCount = 0;

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while(!userInput.equalsIgnoreCase("Bye")) {
            if (userInput.equalsIgnoreCase("List")) {
                displayTaskList(listCount, taskList);
            } else if (userInput.toLowerCase().startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                if (taskNumber > listCount || taskNumber <= 0) {
                    System.out.println(lineSeparator + "Task does not exist!\n" +lineSeparator);
                }else {
                    taskList[taskNumber-1].markAsDone(taskNumber, taskList);
                }
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                if (taskNumber > listCount || taskNumber <= 0) {
                    System.out.println(lineSeparator + "Task does not exist!\n" + lineSeparator);
                }else {
                    taskList[taskNumber-1].markAsUndone(taskNumber, taskList);
                }
            } else {
                Task newTask = new Task(userInput.toLowerCase());
                taskList[listCount] = newTask;
                listCount ++;
                System.out.println(lineSeparator + "added: " + userInput.toLowerCase() + "\n" + lineSeparator);
            }
            userInput = in.nextLine();
        }
        displayFarewell();
    }
}