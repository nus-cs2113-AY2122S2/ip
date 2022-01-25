import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("-----------------------------");
        System.out.println("Hello! I'm Olivia, your lovely personal assistant.");
        System.out.println("What can Olivia do for you my love?");
        System.out.println("-----------------------------");
        String userInput;
        ArrayList<Task> taskList=new ArrayList<Task>();

        do {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            else if (userInput.equals("list")) {
                System.out.println("-----------------------------");
                System.out.println("Olivia presents you a list of tasks for you to do:");
                for (Task task : taskList) {
                    System.out.print(task.getTaskId()+".");
                    System.out.print("["+task.getTaskStatus()+"] ");
                    System.out.println(task.getDescription());
                }
                System.out.println("-----------------------------");
            }
            else if (userInput.contains("mark")) {
                System.out.println("-----------------------------");
                String[] arraySplit = userInput.split(" "); //determine raw value of task number to mark/unmark
                int indexAlter = Integer.valueOf(arraySplit[1]) -1; //index of description to mark/unmark
                if (userInput.contains("un")) {
                    taskList.get(indexAlter).markAsNotDone();
                    System.out.println("Hey!!! Please get the task done.");
                    System.out.println("    [" + taskList.get(indexAlter).getTaskStatus() + "] " + taskList.get(indexAlter).getDescription());
                }
                else {
                    taskList.get(indexAlter).markAsDone();
                    System.out.println("Olivia thanks you for completing the task! :)");
                    System.out.println("    [" + taskList.get(indexAlter).getTaskStatus() + "] " + taskList.get(indexAlter).getDescription());
                }
                System.out.println("-----------------------------");
            }
            else {
                Task t = new Task(userInput); //create new task
                taskList.add(t); //append task to arraylist
                System.out.println("-----------------------------");
                System.out.print("added: ");
                System.out.println(userInput);
                System.out.println("-----------------------------");

            }
        } while(!userInput.equals("bye"));
        System.out.println("-----------------------------");
        System.out.println("Bye. Hope to see you again soon! With Love, Olivia");
        System.out.println("-----------------------------");
    }
}
