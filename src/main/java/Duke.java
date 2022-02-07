import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Controller bot = new Controller();
        bot.greet();
        while(true){  //opens a new session for receiving instructions
            bot.listen();
        }
    }
}
