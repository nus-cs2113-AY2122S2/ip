package UI;

public class UI {
    protected static final String HELLO_WORDS = "Hello! I'm Duke :P\nWhat can I do for you?";
    protected static final String GOODBYE_WORDS = "Bye. Hope to see you again soon! ;)";
    Chatbox chatbox;

    /**
     * Initialize a UI
     */
    public UI(){
        chatbox = new Chatbox();
    }

    /**
     * Print greeting message
     */
    public void greet() {
        chatbox.setContent(HELLO_WORDS);
        chatbox.chatboxPrinter();
    }

    /**
     * Print goodbye message
     */
    public void goodbye() {
        chatbox.setContent(GOODBYE_WORDS);
        chatbox.chatboxPrinter();
    }

    /**
     * Prints message from other classes
     * @param message the message that is returned from other function or classes
     */
    public void printMsg(String message) {
        chatbox.setContent(message);
        chatbox.chatboxPrinter();
    }
}
