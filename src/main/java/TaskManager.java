import java.util.*;

public class TaskManager {
    protected static Task[] tasks = new Task[100];
    protected static int inputAmount = 0;

    public static void addTask(String reply){
        String choice = reply.split(" ")[0];
        String newTask = reply.replace(choice+" ", "");
        switch(choice.toUpperCase()) {
            case "TODO":
                ToDo todo = new ToDo(newTask);
                tasks[inputAmount++] = todo;
                break;
            case "DEADLINE":
                int startIndexforBy = newTask.indexOf("/by");
                String by = newTask.substring(startIndexforBy + 4);
                String newDeadline = newTask.substring(0, startIndexforBy - 1);
                Deadline deadline = new Deadline(newDeadline, by);
                tasks[inputAmount++] = deadline;
                break;
            case "EVENT":
                int startIndexforAt = newTask.indexOf("/at");
                String at = newTask.substring(startIndexforAt + 4);
                String newEvent = newTask.substring(0, startIndexforAt - 1);
                Event event = new Event(newEvent, at);
                tasks[inputAmount++] = event;
                break;
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks[inputAmount-1]);
        System.out.println("Now you have " + inputAmount + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }

    public void printTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i=1;i<=inputAmount;i++){
            System.out.println(i+". "+tasks[i-1]);
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
        System.out.println(tasks[index-1]);
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
                return "finishedLoop";
            }
            else if(words[0].equalsIgnoreCase("unmark")){
                if(manageTask(index, false)==-1){
                    System.out.println("Invalid input!");
                }
                return "finishedLoop";
            }
        }
        return sentence;
    }
}
