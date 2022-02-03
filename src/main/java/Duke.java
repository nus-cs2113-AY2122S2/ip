import java.util.*;

public class Duke {
    public static void main(String[] args) {
        dukeIntro();
        ArrayList<Task> taskArray = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String stringInput;
        do {
            stringInput = sc.nextLine();
            if (stringInput.equals("bye")) {
                break;
            } else if (stringInput.equals("list")) {
                System.out.println("=============================");
                System.out.println("Here's what you got to do! Seize the day!");
                for (int j = 0; j < taskArray.size(); j++) {
                    System.out.println(j + 1 + ". " + taskArray.get(j).getStatusIcon() + taskArray.get(j).getDescription());
                }
                System.out.println("=============================");
            } else if (stringInput.contains("unmark")) {
                String[] temp = new String[100];
                temp = stringInput.split(" ");
                int num = Integer.parseInt(temp[1]);
                taskArray.get(num-1).markAsUndone();
                System.out.println("=============================");
                System.out.println("What are you waiting for? Just do it!");
                System.out.println(taskArray.get(num-1).getStatusIcon() + taskArray.get(num-1).getDescription());

            } else if (stringInput.contains("mark")){
                String[] temp = new String[100];
                temp = stringInput.split(" ");
                int num = Integer.parseInt(temp[1]);
                taskArray.get(num-1).markAsDone();
                System.out.println("=============================");
                System.out.println("Go get it king, well done!");
                System.out.println(taskArray.get(num-1).getStatusIcon() + taskArray.get(num-1).getDescription());

            } else {
                Task t = new Task(stringInput);
                taskArray.add(t);
                System.out.println("=============================");
                System.out.println("added: " + stringInput);
                System.out.println("=============================");
            }
        }while (!stringInput.equals("bye"));
        dukeExit();

    }

    public static void dukeIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("=============================");
        System.out.println("Ayo my name's Duke!");
        System.out.println("What's up?");
        System.out.println("=============================");
    }

    public static void dukeExit(){
        System.out.println("=============================");
        System.out.println("Cheers! See you!");
        System.out.println("=============================");
    }
}
