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
        Scanner sc = new Scanner(System.in);
        String stringInput;
        do{
            stringInput = sc.next();
            if (stringInput.equals("bye")){
                break;
            }
            System.out.println("=============================");
            System.out.println(stringInput);
            System.out.println("=============================");
        } while (!stringInput.equals("bye"));
        System.out.println("Cheers! See you!");



    }
}
