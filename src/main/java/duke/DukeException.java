package duke;
public class DukeException extends Exception{

    public void missingDescription(String type){
        System.out.println("OOPS! The description of " + type + " cannot be empty!");
    }

    public void wrongIndex() {
        System.out.println("OOPS! Index given is out of array size. Please try again!");
    }
}
