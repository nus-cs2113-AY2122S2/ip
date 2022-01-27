import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetStr = "\t____________________________________________________________\n" +
                "\t Hello! I'm Maahes\n" +
                "\t What can I do for you?\n";
        String byeStr = "\t____________________________________________________________\n" +
                "\t Bye. Hope to see you again soon!\n" +
                "\t____________________________________________________________";

        System.out.println(greetStr);
        echo();
        System.out.println(byeStr);
    }

    public static void echo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\t Type \"bye\" to exit.");
        String input = sc.nextLine();
        while(!input.equals("bye")){
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + input);
            System.out.println("\t____________________________________________________________");
            input = sc.nextLine();
        }
    }
}
