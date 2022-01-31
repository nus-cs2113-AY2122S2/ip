import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static int numTasks = 0;
    public static void main(String[] args) {
        System.out.println("Hello! I'm KaiKai.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();


        int taskNum;

        while (!str.equals("bye")) {
            if (str.equals("list")) {
                printTasks();
            } else if (str.equals("mark")) {
                printTasks();
                System.out.println("Which task would you like to mark as completed?");
                taskNum = sc.nextInt();
                sc.nextLine();
                if (taskNum > numTasks || taskNum <= 0) {
                    System.out.println("Uh oh! It seems like that task doesn't exist!");
                } else {
                    tasks[taskNum - 1].setDone(true);
                    System.out.println("Nice! I've marked this task as done: ");
                    tasks[taskNum - 1].printTask();
                }
            } else if (str.equals("unmark")) {
                printTasks();
                System.out.println("Which task would you like to mark as incomplete?");
                taskNum = sc.nextInt();
                sc.nextLine();
                if (taskNum > numTasks || taskNum <= 0) {
                    System.out.println("Uh oh! It seems like that task doesn't exist!");
                } else {
                    tasks[taskNum - 1].setDone(false);
                    System.out.println("Okie, I've marked this task as not done yet:");
                    tasks[taskNum - 1].printTask();
                }
            } else if (str.equals("add")) {
                System.out.println("Okie, what type of task is this?");
                System.out.println("1.Todo");
                System.out.println("2.Deadline");
                System.out.println("3.Event");
                str = sc.nextLine();
                switch (str) {
                case "1":
                    addTodo();
                    break;
                case "2":
                    addDeadline();
                    break;
                case "3":
                    addEvent();
                    break;
                default:
                    System.out.println("Uh oh, please enter a valid number!");
                }
                System.out.println("______________________________________");
            }
            else{
                System.out.println("Sorry, I don't recognise that command. Please try again!");
                System.out.println("______________________________________");
            }
            str = sc.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");
    }

    public static void printTasks(){
        for (int i=0; i<numTasks; i++){
            System.out.print((i+1) + ". ");
            tasks[i].printTask();
        }
    }

    public static void addTodo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Okie, what should I call the task?");
        String str = sc.nextLine();
        tasks[numTasks] = new Todo(str);
        numTasks++;
        System.out.println("Added!");
    }
    public static void addDeadline(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Okie, what should I call the task?");
        String str = sc.nextLine();
        System.out.println("Okie, when is this due by?");
        String by = sc.nextLine();
        tasks[numTasks] = new Deadline(str,by);
        numTasks++;
        System.out.println("Added!");
    }
    public static void addEvent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Okie, what should I call the task?");
        String str = sc.nextLine();
        System.out.println("Okie, when does the event start?");
        String start = sc.nextLine();
        System.out.println("Okie, when does the event end?");
        String by = sc.nextLine();
        tasks[numTasks] = new Event(str,by, start);
        numTasks++;
        System.out.println("Added!");
    }
}
