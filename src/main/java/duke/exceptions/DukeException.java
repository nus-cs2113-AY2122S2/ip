package duke.exceptions;



public class DukeException extends Exception {

    /**
     * @param errorMessage
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}