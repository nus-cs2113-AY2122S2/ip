import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Controller bot = new Controller();
        bot.greet();
        while(true){
            bot.listen();
        }
    }
}
