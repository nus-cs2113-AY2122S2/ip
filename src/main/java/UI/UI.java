package UI;

import exceptions.DukeExceptions;

public class UI {
    protected static final String HELLO_WORDS = "Hello! I'm Duke :P\nWhat can I do for you?";
    protected static final String GOODBYE_WORDS = "Bye. Hope to see you again soon! ;)";
    Chatbox chatbox = new Chatbox();

    public void greet() {
        chatbox.setContent(HELLO_WORDS);
        chatbox.chatboxPrinter();
    }

    public void goodbye() {
        chatbox.setContent(GOODBYE_WORDS);
        chatbox.chatboxPrinter();
    }

    public void printMsg(String message) {
        chatbox.setContent(message);
        chatbox.chatboxPrinter();
    }
}
