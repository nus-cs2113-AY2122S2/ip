public class Deadline extends Task {
    private String dueTime;

    public Deadline(String taskInfo, String dueTime){
        super(taskInfo);
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        String completion = " ";
        if (super.isDone()){
            completion = "X";
        }
        return "[D]["+completion+"] "+super.getTaskInfo()+"(by: "+dueTime+")";
    }

}
