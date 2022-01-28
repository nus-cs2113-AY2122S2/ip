public class Chatbox {
    private String content = "";
    private String horizontalLine = "-------------------------------------";

    public void chatboxPrinter(){
        System.out.println(content);
        System.out.println(horizontalLine);
    }

    public void setContent(String input){
        this.content = input;
    }


}
