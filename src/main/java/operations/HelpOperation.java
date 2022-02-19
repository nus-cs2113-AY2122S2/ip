package operations;

import exceptions.DukeException;

public class HelpOperation extends Operation{
    /**
     * Initializes operation with operationName
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by User
     */
    public HelpOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
        result = "Usage: \n\n";
        helpMessage = "     help                                               --list guide for all operations\n     ";
    }

    @Override
    public void executeOperation() throws DukeException {
        return;
    }

    public void appendHelpMessage(String newHelpMessage) {
        result = result + newHelpMessage;
    }
}
