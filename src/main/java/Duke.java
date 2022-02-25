import controller.*;
import exceptions.*;

public class Duke {
    public static void main(String[] args) throws DukeExceptions {
        Controller bot = new Controller();
        bot.greet();
        while(true){  //opens a new session for receiving instructions
            bot.listen();
        }
    }
}
