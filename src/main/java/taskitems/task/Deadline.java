package taskitems.task;

public class Deadline extends Todo{

    protected String endDate;

    public Deadline(String name,String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        if (isMarked) {
            return "[D][X] " + name + " by:(" + endDate + ")";
        } else {
            return "[D][ ] " + name + " by:(" + endDate + ")";
        }
    }

    @Override
    public String saveString() {
        if (isMarked) {
            return ("D," + name + ",1," + endDate);
        } else {
            return ("D," + name + ",0," + endDate);
        }
    }
}
