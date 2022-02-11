import java.lang.String;
import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String command;
        String taskDescrip;
        String[] taskWord;
        TaskManager tManager = new TaskManager();
        String horiLine = "____________________________________________________________\n";

        tManager.welcome(); //greating

        command = in.nextLine();
        while (!command.equals("bye")) {

            String[] word_split;
            word_split = command.split(" ", 2);
            String taskName = word_split[0];

            //different functions based on command lines
            switch (taskName) {
                case "todo":
                    tManager.addTask(new Todo(word_split[1]));
                    break;
                case "deadline":
                    taskWord = word_split[1].split("/by ", 2);
                    taskDescrip = taskWord[0];
                    String ddl = taskWord[1];
                    tManager.addTask(new Deadline(taskDescrip, ddl));
                    break;
                case "event":
                    taskWord = word_split[1].split("/at ", 2);
                    taskDescrip = taskWord[0];
                    String eventTime = taskWord[1];
                    tManager.addTask(new Event(taskDescrip, eventTime));
                    break;
                case "list":
                    tManager.printTaskList();
                    break;
                case "mark":
                    System.out.println(horiLine);
                    tManager.markTask(Integer.parseInt(word_split[1]) - 1);
                    System.out.println(horiLine);
                    break;
                case "unmark":
                    System.out.println(horiLine);
                    tManager.unmarkTask(Integer.parseInt(word_split[1]) - 1);
                    System.out.println(horiLine);
                    break;
                default:
                    System.out.println("Error. Please retry");
                    break;
            }
            command = in.nextLine();
        }
        tManager.bye(); //bye
    }
}
