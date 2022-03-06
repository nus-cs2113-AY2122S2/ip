public class Task {

    protected String description;

    protected boolean isDone;


    public String getDescription(){

        return description;

    }


    public Task(String description) {

        this.description = description;

        this.isDone = false;

    }

    public void markIt() {

        this.isDone = true;

    }


    public String getStatusIcon() {

        return (isDone ? "X" : " "); //return  X symbols
    }

        public String getLetter() {
            return null;
        }


    }



