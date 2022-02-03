class Task {

    public String task;
    public Boolean isDone = false;

    Task(String task) {
        this.task = task;
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getTask() {
        return this.task;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String mark = " ";
        if (this.isDone()) {
            mark = "X";
        }
        return String.format("[%s] %s", mark, task);
    }
}