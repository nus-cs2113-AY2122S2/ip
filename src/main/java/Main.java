import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Alexis.greet();
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                Alexis.addToList(input);
            }else{
                Alexis.readList();
            }
            input = in.nextLine();
        }
        Alexis.exit();

    }
}
