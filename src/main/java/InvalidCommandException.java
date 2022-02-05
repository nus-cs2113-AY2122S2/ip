public class InvalidCommandException extends Exception{
    public InvalidCommandException (String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

}
