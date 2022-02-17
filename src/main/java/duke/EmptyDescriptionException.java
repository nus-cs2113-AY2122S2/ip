package duke;

public class EmptyDescriptionException extends Throwable {
    EmptyDescriptionException() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        System.out.println("    ____________________________________________________________");
    }
}
