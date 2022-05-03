/**
 * @param errorMessage any string you'd like to print out
 *
 * */

package duke.duke_exception;
public class DukeException extends Exception{
    public String error;
    public DukeException(String error){
        this.error = error;
    }
}
