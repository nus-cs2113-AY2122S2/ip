package taskitems.task;

import helper.Parser;
import taskitems.exceptions.DateException;
import taskitems.exceptions.TimeException;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Todo{

    protected LocalDate endDate;
    protected LocalTime endTime;
    private Parser parser = new Parser();

    public Deadline(String name,String endDate) {
        super(name);
        String[] date = endDate.trim().split(" ");
        try {
            this.endDate = parser.parseDate(date[0]);
            this.endTime = parser.parseTime(date[1]);
        } catch (DateException | TimeException t) {
            System.out.println(t);
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString(){
        if (isMarked) {
            return "[D][X] " + name + " by:(" + endDate.getDayOfMonth() + " " + endDate.getMonth()
                    + " " + endDate.getYear() + " " + endTime + ")";
        } else {
            return "[D][ ] " + name + " by:(" + endDate.getDayOfMonth() + " " + endDate.getMonth()
                    + " " + endDate.getYear() + " " + endTime + ")";
        }
    }

    @Override
    public String saveString() {
        if (isMarked) {
            return ("D," + name + ",1," + endDate.toString() + " " + endTime.toString());
        } else {
            return ("D," + name + ",0," + endDate.toString() + " " + endTime.toString());
        }
    }

}
