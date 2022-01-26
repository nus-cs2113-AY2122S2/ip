public class ChatBot {
    private final String BOT_NAME = "Big Bob";
    public ChatBot() {
        System.out.println("    Greetings Human! I'm "+BOT_NAME+".");
        System.out.println("    How may i be of service to you?");
    }
    public void echoCommand(String command) {
        System.out.println("    "+command);
    }
    public void echoFarewellGreeting() {
        System.out.println("    Good bye.See you soon :)) !");
    }
}
