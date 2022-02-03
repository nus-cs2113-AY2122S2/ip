import java.util.Scanner;

public class TaskManager {
    private Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public TaskManager() {

    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int idx;
        // Supported commands
        System.out.println("\t Supported commands:");
        System.out.println("\t Type \"todo/deadline/event <task>\" to add a task");
        System.out.println("\t Type \"list\" to list all tasks");
        System.out.println("\t Type \"mark <task number>\" to mark a task");
        System.out.println("\t Type \"unmark <task number>\" to unmark a task");
        System.out.println("\t Type \"bye\" to exit");
        System.out.println("\t" + "-".repeat(60));

        String option = sc.next();
        while(!option.equals("bye")) {
            switch (option) {
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                String taskDescription = sc.nextLine();
                addTask(option, taskDescription);
                System.out.println("\t" + "-".repeat(60));
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t\t" + tasks[taskCount - 1].toString());
                System.out.println("\t Now you have " + taskCount + " tasks in the list.");
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
                displayInvalidCmd();
                sc.nextLine();
                break;
            }
            option = sc.next();
        }
    }

    public void addTask(String option, String taskDescription) {
        if(option.equals("todo")) {
            tasks[taskCount++] = new Todo(taskDescription.trim());
            return;
        }

        int sepIndex = taskDescription.indexOf("/");
        String description = taskDescription.substring(0, sepIndex);
        description = description.trim();
        String time = taskDescription.substring(sepIndex + 3);
        time = time.trim();
        if(option.equals("deadline")) {
            tasks[taskCount++] = new Deadline(description, time);
        } else if(option.equals("event")) {
            tasks[taskCount++] = new Event(description, time);
        }
    }

    public void listTasks(){
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\t Here are the tasks in your list:");
        if(taskCount == 0) {
            System.out.println("\t No task recorded.");
            System.out.println("\t" + "-".repeat(60));
            return;
        }

        for(int i = 0;i < taskCount; i++) {
            System.out.println("\t\t " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println("\t" + "-".repeat(60));
    }

    public void markTask(int idx) {
        idx --;
        tasks[idx].markAsDone();
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + (idx + 1) + "." + tasks[idx].toString());
        System.out.println("\t" + "-".repeat(60));
    }

    public void unmarkTask(int idx) {
        idx --;
        tasks[idx].unmark();
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t\t " + (idx + 1) + "." + tasks[idx].toString());
        System.out.println("\t" + "-".repeat(60));
    }

    public void displayInvalidCmd() {
        System.out.println("\t" + "-".repeat(60));
        System.out.println("\t I cannot read this instruction. Please try again.");
        System.out.println("\t" + "-".repeat(60));
    }
}
