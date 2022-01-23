import java.util.*;

public class TaskManager {
    protected static Task[] tasks = new Task[100];
    protected static int inputAmount = 0;

    public void addTask(Task task){
        tasks[inputAmount++] = task;
    }

    public void printTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i=1;i<=inputAmount;i++){
            System.out.println(i+". "+tasks[i-1].printTask());
        }
        System.out.println("____________________________________________________________");
    }

    public int manageTask(int index, boolean markDone){
        if(index> inputAmount || index<=0)
            return -1;
        if(markDone){//to mark it as done
            tasks[index-1].setDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done: ");
        }
        else{
            tasks[index-1].setUndone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks[index-1].printTask());
        System.out.println("____________________________________________________________");
        return 1;
    }

    public String taskChoice(){
        Scanner input = new Scanner(System.in);
        String sentence = input.nextLine();
        String[] words = sentence.split(" ");
        if(words.length==2 && Integer.parseInt(words[1])<=inputAmount){
            int index = Integer.parseInt(words[1]);
            if(words[0].equalsIgnoreCase("mark")){
                if(manageTask(index, true)==-1){
                    System.out.println("Invalid input!");
                }
                return "ok";
            }
            else if(words[0].equalsIgnoreCase("unmark")){
                if(manageTask(index, false)==-1){
                    System.out.println("Invalid input!");
                }
                return "ok";
            }
        }
        return sentence;
    }
}
