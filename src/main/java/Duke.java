import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KaiKai.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String[] tasks = new String[100];
        int numTasks = 0;

        while (!str.equals("bye")) {
            if (str.equals("list")){
                for (int i=0; i<numTasks; i++){
                    System.out.println((i+1) + ". " + tasks[i]);
                }
            }
            else {
                tasks[numTasks] = str;
                numTasks++;
                System.out.println("added " + str);
            }
            System.out.println("______________________________________");
            str = sc.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");
    }
}
