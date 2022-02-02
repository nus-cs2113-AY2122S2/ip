import java.util.Scanner;

public class Duke {

    static Greet greet;
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        greet.sayHi();
        taskLoop(taskManager);
        //echo();
        greet.sayBye();
    }

    public static void taskLoop(TaskManager taskManager){
        greet.printDecoration();
        boolean isDone = false;
        while(!isDone){
            String command = reader.next();
            String taskName = reader.nextLine();
            switch (command){
            case "todo":
                taskManager.addToTasks(taskName);
                break;
            case "deadline":
                taskManager.addToTasks("D",taskName.trim().split(" /by ")[0],taskName.trim().split(" /by ")[1]);
                break;
            case "event":
                taskManager.addToTasks("E",taskName.trim().split(" /at ")[0],taskName.trim().split(" /at ")[1]);
                break;
            case "list":
                taskManager.printTasks();
                break;
            case "mark":
                taskManager.markTask(Integer.parseInt(taskName.trim()));
                break;
            case "unmark":
                taskManager.unmarkTask(Integer.parseInt(taskName.trim()));
                break;
            case "bye":
                isDone = true;
                break;
            default:
                greet.printDecoration();
                System.out.println("Invalid Command!");
                greet.printDecoration();
            }
        }
    }

    public static void echo(){
        greet.printDecoration();
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
