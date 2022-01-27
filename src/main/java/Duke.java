import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Greet.sayHi();
        taskLoop(taskManager);
        //echo();
        Greet.sayBye();
    }

    public static void taskLoop(TaskManager taskManager){
        Greet.printDecoration();
        Scanner reader = new Scanner(System.in);
        boolean isDone = false;
        while(!isDone){
            String taskName = reader.nextLine();
            String[] taskNameWords = taskName.trim().split(" ");
            switch (taskNameWords[0].toLowerCase()){
            case "list":
                taskManager.printTasks();
                break;
            case "mark":
                taskManager.markTask(Integer.parseInt(taskNameWords[1]));
                break;
            case "unmark":
                taskManager.unmarkTask(Integer.parseInt(taskNameWords[1]));
                break;
            case "bye":
                isDone = true;
                break;
            default:
                taskManager.addToTasks(taskName);
            }
        }
    }

    public static void echo(){
        Greet.printDecoration();
        Scanner reader = new Scanner(System.in);
        boolean isDone = false;
        while(!isDone){
            String toRepeat = reader.nextLine();
            if(toRepeat.toLowerCase().equals("bye")){
                isDone = true;
                break;
            }
            else{
                System.out.println(toRepeat);
            }
        }
    }
}
