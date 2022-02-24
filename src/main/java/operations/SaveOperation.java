package operations;

import exceptions.DukeException;
import tasks.TaskList;

public class SaveOperation extends Operation {

    private static final String SAVE_SUCCESS_WORDS = "Received! Already saved the records for you! ^-^";

    /**
     * Initializes operation with operationName
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by User
     */
    public SaveOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
        helpMessage = "     save                                               --save the current records\n";
    }

    /**
     * Save all tasks in list
     * @return Execution results
     * @throws DukeException Save Exception
     */
    @Override
    public String operate() throws DukeException {
        try {
            TaskList.save();
            return SAVE_SUCCESS_WORDS;
        } catch (DukeException e) {
            throw e;
        }
    }

}
