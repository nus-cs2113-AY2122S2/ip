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
            if(taskName.toLowerCase().equals("bye")){
                isDone = true;
                break;
            }
            else if(taskName.toLowerCase().equals("list")){
                taskManager.printTasks();
            }
            else{
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
        }
    }
}
