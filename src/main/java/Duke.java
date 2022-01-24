import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KaiKai.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Task[] tasks = new Task[100];
        int numTasks = 0;
        int taskNum;

        while (!str.equals("bye")) {
            if (str.equals("list")){
                printTasks(tasks, numTasks);
            }
            else if (str.equals("mark")){
                printTasks(tasks, numTasks);
                System.out.println("Which task would you like to mark as completed?");
                taskNum = sc.nextInt();
                sc.nextLine();
                if (taskNum > numTasks || taskNum <= 0){
                    System.out.println("Uh oh! It seems like that task doesn't exist!");
                }
                else {
                    tasks[taskNum - 1].setDone(true);
                    System.out.println("Nice! I've marked this task as done: ");
                    tasks[taskNum - 1].printTask();
                }
            }
            else if (str.equals("unmark")){
                printTasks(tasks, numTasks);
                System.out.println("Which task would you like to mark as incomplete?");
                taskNum = sc.nextInt();
                sc.nextLine();
                if (taskNum > numTasks || taskNum <= 0){
                    System.out.println("Uh oh! It seems like that task doesn't exist!");
                }
                else {
                    tasks[taskNum - 1].setDone(false);
                    System.out.println("Okie, I've marked this task as not done yet:");
                    tasks[taskNum - 1].printTask();
                }
            }
            else {
                tasks[numTasks] = new Task(str);
                numTasks++;
                System.out.println("added " + str);
            }
            System.out.println("______________________________________");
            str = sc.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");
    }

    public static void printTasks(Task[] tasks, int numTasks){
        for (int i=0; i<numTasks; i++){
            System.out.print((i+1) + ". ");
            tasks[i].printTask();
        }
    }
}
