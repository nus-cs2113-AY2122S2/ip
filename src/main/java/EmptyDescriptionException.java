public class EmptyDescriptionException extends RuntimeException{
    EmptyDescriptionException() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        System.out.println("    ____________________________________________________________");
    }
}