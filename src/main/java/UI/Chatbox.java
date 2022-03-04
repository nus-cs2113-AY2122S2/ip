package UI;

/**
 * Create a chatbox for each message
 */
public class Chatbox {
    //border of the chatbox
    protected static final String HORIZON_LINE = "-------------------------------------";
    private String content = "";

    /**
     * Initialize a chatbox
     */
    public void chatboxPrinter() {
        System.out.println(content);
        System.out.println(HORIZON_LINE);
    }

    public void setContent(String input) {
        this.content = input;
    }


}
