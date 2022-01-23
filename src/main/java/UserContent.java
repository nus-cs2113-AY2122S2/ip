public class UserContent {
    private String content;
    private boolean isMark;
    private int listIndex;

    public UserContent(){
        this("", -1);
    }
    public UserContent(String content, int listIndex){
        setContent(content);
        setUnmark();
        setListIndex(listIndex);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public void setMark() {
        isMark = true;
    }
    public void setUnmark() {
        isMark = false;
    }

    public int getListIndex() {
        return listIndex;
    }

    public String getContent() {
        return content;
    }

    public boolean getMark() {
        return isMark;
    }
}