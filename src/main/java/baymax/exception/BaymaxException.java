package baymax.exception;

public class BaymaxException extends RuntimeException{
    public BaymaxException(String errorMessage){
        super(errorMessage);
    }
}
