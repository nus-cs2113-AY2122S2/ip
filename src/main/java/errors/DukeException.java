package errors;

public class DukeException extends Exception{
    /**
     * To generate a Duke Exception and print out message
     *
     * @param message input message to print out
     *
     */
    public DukeException(String message){
        super(message);
    }
}
