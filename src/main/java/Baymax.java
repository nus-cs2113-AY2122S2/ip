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

            System.out.println(horiLine);
            //different functions based on command lines
            switch (taskName) {
                case "todo":
                    try {
                        tManager.addTask(new Todo(word_split[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "deadline":
                    try {
                        taskWord = word_split[1].split("/by ", 2);
                        taskDescrip = taskWord[0];
                        String ddl = taskWord[1];
                        tManager.addTask(new Deadline(taskDescrip, ddl));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "event":
                    try {
                        taskWord = word_split[1].split("/at ", 2);
                        taskDescrip = taskWord[0];
                        String eventTime = taskWord[1];
                        tManager.addTask(new Event(taskDescrip, eventTime));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(" ☹ OOPS!!! The description of a event cannot be empty.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "list":
                    tManager.printTaskList();
                    break;
                case "mark":
                    try {
                        tManager.markTask(Integer.parseInt(word_split[1]) - 1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please input an integer for task index.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "unmark":
                    try {
                        tManager.unmarkTask(Integer.parseInt(word_split[1]) - 1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please input an integer for task index.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                default:
                    System.out.println("Error. Please retry");
                    break;
            }
            System.out.println(horiLine);
            command = in.nextLine();

        }
        tManager.bye(); //bye
    }
}
