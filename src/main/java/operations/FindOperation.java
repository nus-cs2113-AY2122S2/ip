package operations;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;

public class FindOperation extends Operation {

    /**
     * Initializes operation with operationName
     * @param operationNameLocal Name of the operations.Operation
     * @param order Order given by User
     */
    public FindOperation(String operationNameLocal, String order) throws DukeException {
        super(operationNameLocal, order);
        helpMessage = "     find <keyword>                                     --find task(s) containing the keyword\n";
    }

    @Override
    public String operate() throws DukeException {
        String targetWord = order.split(" ", 2)[1];
        int count = 0;
        int sizeArray = TaskList.getSize();
        String resultLocal = "Here are the matching tasks in your list:\n";
        try {
            for (int i = 0; i < sizeArray; i++) {
                if (i > 0) {
                    resultLocal += "\n";
                }
                Task taskLocal = TaskList.getElement(i);
                if (taskLocal.getTaskDescription().contains(targetWord)) {
                    count += 1;
                    resultLocal += (String.valueOf(count) + ". " + taskLocal.getReport());
                }

            }
            return resultLocal;
        } catch (DukeException e) {
            throw e;
        }
    }
}
