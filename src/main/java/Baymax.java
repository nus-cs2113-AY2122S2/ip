import java.lang.String;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Baymax {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        String command;
        String taskDescrip;
        String[] taskWord;
        TaskManager tManager = new TaskManager();
        String horiLine = "____________________________________________________________\n";

        //load record
        try {
            tManager.initialiseNewFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                    try {
                        taskWord = word_split[1].split("/by ", 2);
                        taskDescrip = taskWord[0];
                        String ddl = taskWord[1];
                        tManager.addTask(new Deadline(taskDescrip, ddl));
                    } catch (ArrayIndexOutOfBoundsException e) {
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
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(" ☹ OOPS!!! The description of an event cannot be empty.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    break;
                case "list":
                    tManager.printTaskList();
                    break;
                case "mark":
                    System.out.println(horiLine);
                    try {
                        tManager.markTask(Integer.parseInt(word_split[1]) - 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input an integer for task index.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    System.out.println(horiLine);
                    break;
                case "unmark":
                    System.out.println(horiLine);
                    try {
                        tManager.unmarkTask(Integer.parseInt(word_split[1]) - 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input an integer for task index.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    System.out.println(horiLine);
                    break;
                case "delete":
                    System.out.println(horiLine);
                    try {
                        tManager.deleteTask(Integer.parseInt(word_split[1]) - 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input an integer for task index.");
                    } catch (BaymaxException b){
                        System.out.println( b.getMessage() +" ☹ OOPS!!! Let's do it again.");
                    }
                    System.out.println(horiLine);
                    break;
                default:
                    System.out.println(horiLine);
                    System.out.println("Error. Please retry");
                    System.out.println(horiLine);
                    break;
            }
            command = in.nextLine();

        }
        tManager.saveTask();
        tManager.bye(); //bye
    }

}
