import java.util.Scanner;

public class TaskManager {
    private Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public TaskManager(){

    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        int idx;
        // Supported commands
        System.out.println("\tType \"add <task>\" to add a task");
        System.out.println("\tType \"list\" to list all tasks");
        System.out.println("\tType \"mark <task number>\" to mark a task");
        System.out.println("\tType \"unmark <task number>\" to unmark a task");
        System.out.println("\tType \"bye\" to exit");
        System.out.println("\t" + "-".repeat(60));

        String input = sc.next();
        while(!input.equals("bye")){
            switch (input){
            case "add":
                input = sc.nextLine();
                addTask(input.trim());
                System.out.println("\t" + "-".repeat(60));
                System.out.println("\t added: " + tasks[taskCount - 1].getDescription());
                System.out.println("\t" + "-".repeat(60));
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                idx = sc.nextInt();
                markTask(idx);
                break;
            case "unmark":
                idx = sc.nextInt();
                unmarkTask(idx);
                break;
            default:
                System.out.println("\t" + "-".repeat(60));
                System.out.println("I cannot read this instruction. Please try again.");
                sc.nextLine();
                System.out.println("\t" + "-".repeat(60));
            }
            input = sc.next();
        }
    }

    public void addTask(String description){

        tasks[taskCount++] = new Task(description);
    }

    public void markTaskAsDone(int idx){

        tasks[idx].markAsDone();
    }

    public void listTasks(){
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0;i < taskCount; i++){
            System.out.println("\t " + (i + 1) +
                    ".[" + tasks[i].getStatusIcon() + "] " +
                    tasks[i].getDescription());
        }
        if(taskCount == 0){
            System.out.println("No task recorded.");
        }
        System.out.println("\t" + "-".repeat(60));
    }

    public void markTask(int idx){
        idx --;
        tasks[idx].markAsDone();
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t " + (idx + 1) +
                ".[" + tasks[idx].getStatusIcon() + "] " +
                tasks[idx].getDescription());
        System.out.println("\t" + "-".repeat(60));
    }

    public void unmarkTask(int idx){
        idx --;
        tasks[idx].unmark();
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t " + (idx + 1) +
                ".[" + tasks[idx].getStatusIcon() + "] " +
                tasks[idx].getDescription());
        System.out.println("\t" + "-".repeat(60));
    }
}
