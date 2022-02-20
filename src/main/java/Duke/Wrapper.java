package Duke;

public class Wrapper {
    public static String wrapMessage(String message) {
        String divider = "____________________________________________________________\n";
        message = String.format("%s %s%s", divider, message, divider);
        return message;
    }
}
