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
        ArrayList<String> array = new ArrayList<String>();
        int i = 0;
        Scanner sc = new Scanner(System.in);
        String stringInput;
        do {
            stringInput = sc.nextLine();
            if (stringInput.equals("bye")) {
                break;
            } else if (stringInput.equals("list")) {
                System.out.println("=============================");
                for (int j = 0; j < array.size(); j++) {
                    System.out.println(j + 1 + ". " + array.get(j));
                }
                System.out.println("=============================");
            } else {
                array.add(stringInput);
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
