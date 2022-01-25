import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Alexis.greet();
        String input = in.nextLine();
        while (!input.equals("bye")) {
            Alexis.echo(input);
            input = in.nextLine();
        }
        Alexis.exit();


    }
}
