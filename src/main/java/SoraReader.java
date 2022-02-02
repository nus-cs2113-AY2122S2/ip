import java.util.Scanner;

public class SoraReader {
    Scanner reader = new Scanner(System.in);

    protected String getUserInput() {
        return reader.nextLine();
    }
}
