import java.lang.String;
import java.util.Scanner;

public class Baymax {


    public static void main(String[] args) {
        String greeting = "  Hello, I'm Baymax.\n"+
                          "  Your personal task managing companion. \n" +
                          "  What can I do for you? \n";
        String ending = "Bye. Hope to see you again soon! \n";
        String horiLine = "____________________________________________________________\n";

        System.out.println(horiLine + greeting + horiLine);
        //greetings

        Task[] task = new Task[100];
        int taskCount = 0;


        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while(!command.equals("bye")){

            if(command.equals("list")){
                System.out.println(horiLine);
                for(int i=0; i<taskCount; i++){
                    System.out.println((i+1) + "." + task[i].getStatusIcon() + " " + task[i].getDescription());
                }
                System.out.println(horiLine);
            }
            else if(command.split(" ")[0].equals("mark")){
                System.out.println(horiLine);
                String[] word_split;
                word_split = command.split(" ");
                int taskIndex = Integer.parseInt(word_split[1]) - 1;
                task[taskIndex].markTaskDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task[taskIndex].getStatusIcon() + " " + task[taskIndex].getDescription());
                System.out.println(horiLine);
            }
            else if (command.split(" ")[0].equals("unmark")) {
                System.out.println(horiLine);
                String[] words = command.split(" ");
                int taskIndex = Integer.parseInt(words[1]) - 1;
                task[taskIndex].unmarkTaskDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task[taskIndex].getStatusIcon() + " " + task[taskIndex].getDescription());
                System.out.println(horiLine);
            }
            else{
                System.out.println(horiLine);
                System.out.println("added: " + command);
                System.out.println(horiLine);
                task[taskCount] = new Task(command);
                taskCount++;
            }
            command = in.nextLine();
        }

        System.out.println(horiLine + ending + horiLine);


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
