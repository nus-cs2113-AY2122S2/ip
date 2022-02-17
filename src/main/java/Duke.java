import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws DukeException{
        Controller bot = new Controller();
        bot.greet();
        while(true){  //opens a new session for receiving instructions
            bot.listen();
        }
    }
}
