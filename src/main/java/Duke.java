import controller.Controller;

public class Duke {
    /**
     * Run program
     * @param args input from user
     * @throws Exception if there's any unacceptable condition
     */
    public static void main(String[] args) throws Exception {
        Controller bot = new Controller();
        bot.greet();
        bot.createFile();
        bot.loadTask();
        while (true) {
            //opens a new session for receiving instruction
            bot.listen();
        }
    }
}
