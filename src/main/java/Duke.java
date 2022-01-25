import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        String botLogo = "  ___                             __  \n"
                       + "|  _  |  |   |  \\        / |     /  \\ \n"
                       + "| | | |  |   |   \\      /  |    / _  \\ \n"
                       + "| | | |  |   |    \\    /   |   / /_\\  \\ \n"
                       + "| |_| |  |   |     \\  /    |  /   _    \\ \n"
                       + "| ___ |  |__ |      \\/     | /___/ \\____\\ \n";
        System.out.println("Hello from\n" + botLogo);
        System.out.println("-----------------------------");
        System.out.println("Greetings! I'm Olivia, your lovely personal assistant.");
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
                Task task = new Task(userInput); //create new task
                taskList.add(task); //append task to arraylist
                System.out.println("-----------------------------");
                System.out.println("added: " + userInput);
                System.out.println("-----------------------------");

            }
        } while(!userInput.equals("bye"));
        System.out.println("-----------------------------");
        System.out.println("Bye. Hope to see you again soon! With Love, Olivia");
        System.out.println("-----------------------------");
    }
}
