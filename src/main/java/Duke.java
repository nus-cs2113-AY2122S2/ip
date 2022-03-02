import commandsParser.Controller;

public class Duke {
    public static void main(String[] args) throws Exception {
        Controller bot = new Controller();
        bot.greet();
        bot.createFile();
        bot.loadTask();
        while(true){//opens a new session for receiving instruction
            bot.listen();
        }
    }
}
