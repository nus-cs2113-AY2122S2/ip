package chatbox;

public class Chatbox {
    private String content = "";
    //border of the chatbox
    private final String HORIZON_LINE = "-------------------------------------";

    public void chatboxPrinter(){
        System.out.println(content);
        System.out.println(HORIZON_LINE);
    }

    public void linePrinter(){
        System.out.println(HORIZON_LINE);
    }

    public void setContent(String input){
        this.content = input;
    }


}
