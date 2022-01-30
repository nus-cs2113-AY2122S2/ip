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
                Greet.printDecoration();
                System.out.println("Invalid Command!");
                Greet.printDecoration();
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
