package baymax.data;

import baymax.data.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String saveInfo(){
        String sep =" / ";
        return "T" + super.saveInfo() + sep;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
