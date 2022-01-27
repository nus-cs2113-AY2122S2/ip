import java.util.Scanner;

public class TaskManager {
    private Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public TaskManager(){

    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        // Supported commands
        System.out.println("\tType \"add <task>\" to add a task");
        System.out.println("\tType \"list\" to list all tasks");
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
            default:
                System.out.println("\t" + "-".repeat(60));
                System.out.println("I cannot read this instruction. Please try again.");
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
        for(int i = 0;i < taskCount; i++){
            System.out.println("\t " + (i + 1) + ". " + tasks[i].getDescription());
        }
        if(taskCount == 0){
            System.out.println("No task recorded.");
        }
        System.out.println("\t" + "-".repeat(60));
    }

}
