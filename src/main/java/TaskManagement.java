import java.util.Scanner;

public class TaskManagement {
    public static void main(String[] args) {
        Task[] taskList = new Task[3];
        taskList[0] = new Task("read book");
        taskList[1] = new Task("return book");
        taskList[2] = new Task("buy bread");

        taskList[0].markAsDone();
        

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i =0 ; i<3; i++){
                    System.out.println((i+1) + ". " + taskList[i].toString());
                }
            }
            else if(input.contains("unmark")){
                System.out.println("Ok, I've marked this task as not done yet:");
                int idx = Integer.parseInt(input.replace("unmark ", ""));
                taskList[idx-1].markAsUndone();
                System.out.println(taskList[idx-1].toString());

            }
            else if(input.contains("mark")){
                System.out.println("Nice! I have marked this task as done:");
                int idx = Integer.parseInt(input.replace("mark ", ""));
                taskList[idx-1].markAsDone();
                System.out.println(taskList[idx-1].toString());

            }
            else{
                System.out.println("  " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("  Bye. Hope to see you again soon!");
    }
    
}