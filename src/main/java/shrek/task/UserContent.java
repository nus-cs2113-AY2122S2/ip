package shrek.task;

public class UserContent {
    private String content;
    private boolean isMark;
    private int listIndex;
    private String taskName;

    public UserContent(){
        this("", -1);
    }

    public UserContent(String content, int listIndex){
        setContent(content);
        setUnmark();
        setListIndex(listIndex);
        setTaskName(" ");
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

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        String mark;
        if(this.getMark()){
            mark = "X";
        } else{
            mark = " ";
        }
        return (getListIndex() + ". " + "[" + this.taskName + "]" + "[" + mark +"] " + getContent());
    }
}