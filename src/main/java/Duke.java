import java.util.*;

public class Duke {
    public static void main(String[] args) {
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
        ArrayList<Task> array = new ArrayList<Task>();
        int i = 0;
        Scanner sc = new Scanner(System.in);
        String stringInput;
        do {
            stringInput = sc.nextLine();
            if (stringInput.equals("bye")) {
                break;
            } else if (stringInput.equals("list")) {
                System.out.println("=============================");
                System.out.println("Here's what you got to do! Seize the day!");
                for (int j = 0; j < array.size(); j++) {
                    System.out.println(j + 1 + ". " + array.get(j).getStatusIcon() + array.get(j).getDescription());
                }
                System.out.println("=============================");
            } else if (stringInput.contains("unmark")) {
                String[] temp = new String[100];
                temp = stringInput.split(" ");
                int num = Integer.parseInt(temp[1]);
                array.get(num-1).markAsUndone();
                System.out.println("=============================");
                System.out.println("What are you waiting for? Just do it!");
                System.out.println(array.get(num-1).getStatusIcon() + array.get(num-1).getDescription());

            } else if (stringInput.contains("mark")){
                String[] temp = new String[100];
                temp = stringInput.split(" ");
                int num = Integer.parseInt(temp[1]);
                array.get(num-1).markAsDone();
                System.out.println("=============================");
                System.out.println("Go get it king, well done!");
                System.out.println(array.get(num-1).getStatusIcon() + array.get(num-1).getDescription());

            } else {
                Task t = new Task(stringInput);
                array.add(t);
                System.out.println("=============================");
                System.out.println("added: " + stringInput);
                System.out.println("=============================");
            }
        }while (!stringInput.equals("bye"));
        System.out.println("=============================");
        System.out.println("Cheers! See you!");
        System.out.println("=============================");



    }
}
