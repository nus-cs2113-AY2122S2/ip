package duke;

public class DukeEmptyDescriptionException extends RuntimeException{
    DukeEmptyDescriptionException() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        System.out.println("    ____________________________________________________________");
    }
}