package operations;

<<<<<<< HEAD
=======
import exceptions.DukeException;

>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
public class ByeOperation extends Operation {

    /**
     * Initializes operation with operationName
     *
     * @param operationName Name of the Operation
     * @param  order Order given by User
     */
<<<<<<< HEAD
    public ByeOperation(String operationName, String order) {
=======
    public ByeOperation(String operationName, String order) throws DukeException {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f

        super(operationName, order);
    }

    @Override
    public String operate() {
        return "Bye. Hope to see you again soon!";
    }
}
