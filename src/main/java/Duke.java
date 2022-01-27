import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Greet.sayHi();
        echo();
        Greet.sayBye();
    }

    public static void echo(){
        Greet.printDecoration();
        Scanner reader = new Scanner(System.in);
        boolean isDone = false;
        while(!isDone){
            String toRepeat = reader.nextLine();
            if(toRepeat.toLowerCase().equals("bye")){
                isDone = true;
                break;
            }
            Greet.printDecoration();
            System.out.println(toRepeat);
            Greet.printDecoration();
        }
    }
}
