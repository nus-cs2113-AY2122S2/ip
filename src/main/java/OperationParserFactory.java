import java.util.HashMap;

import exceptions.DukeException;
import exceptions.UnknownOrderDukeException;
import operations.*;

public class OperationParserFactory {

    private String order;

    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String SAVE_COMMAND = "save";
    private static final String FIND_COMMAND = "find";
    private static final String HELP_COMMAND = "help";
    private static final String NULL_PARAMETER = "";
    private static final String NULL_ORDER = "";
    private static final String[] OPERATION_NAME_LIST = {BYE_COMMAND, LIST_COMMAND, MARK_COMMAND, UNMARK_COMMAND, TODO_COMMAND, DEADLINE_COMMAND, EVENT_COMMAND, DELETE_COMMAND, SAVE_COMMAND, FIND_COMMAND, HELP_COMMAND};
    private HashMap<String, Operation> operationCache;
    private String[] helpMessages;

    public OperationParserFactory(String orderLocal) {
        order = orderLocal;
    }

    public OperationParserFactory() {
        helpMessages = new String[OPERATION_NAME_LIST.length];
        operationCache = new HashMap<>();
    }

    public void setOrder(String orderLocal) {
        order = orderLocal;
    }

    /**
     * Checks whether there are catched operation firstly
     *      if so it will not make duplicate operation and will return the existed one
     *      else it will call makeOperation.
     * @return The required operation
     * @throws DukeException Exception of getting operation
     */
    public Operation getOperation() throws DukeException {
        try {
            String orderName = order.split(" ", 2)[0].trim();
            if (operationCache.containsKey(orderName) == true) {
                operationCache.get(orderName).setOrder(order);
                operationCache.get(orderName).executeOperation();
                return operationCache.get(orderName);
            }
            else {
                operationCache.put(orderName, makeOperation(orderName, order));
                return operationCache.get(orderName);
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    private Operation getHelpList() throws DukeException {
        try {
            HelpOperation helpOperation = new HelpOperation(HELP_COMMAND, HELP_COMMAND);
            if (operationCache.containsKey(HELP_COMMAND) == false) {
                operationCache.put(HELP_COMMAND, helpOperation);
            }
            for (String operationName : OPERATION_NAME_LIST) {
                if (operationCache.containsKey(operationName) == true) {
                    helpOperation.appendHelpMessage(operationCache.get(operationName).getHelpMessage());
                } else {
                    operationCache.put(operationName, makeOperation(operationName, NULL_ORDER));
                    helpOperation.appendHelpMessage(operationCache.get(operationName).getHelpMessage());
                }
            }
            operationCache.put(HELP_COMMAND, helpOperation);
            return helpOperation;
        } catch (DukeException e) {
            throw e;
        }

    }

    /**
     * Makes certain operation according to the orderName
     *
     * @return Certain operation
     */
    private Operation makeOperation(String orderName, String order) throws DukeException {
        try {
            switch (orderName) {
            case BYE_COMMAND:
                return new ByeOperation(orderName, order);
            case LIST_COMMAND:
                return new ListOperation(orderName, order);
            case MARK_COMMAND:
                return new MarkOperation(orderName, order);
            case UNMARK_COMMAND:
                return new UnmarkOperation(orderName, order);
            case TODO_COMMAND:
                return new ToDoAddOperation(orderName, order);
            case DEADLINE_COMMAND:
                return new DeadlinesAddOperation(orderName, order);
            case EVENT_COMMAND:
                return new EventAddOperation(orderName, order);
            case SAVE_COMMAND:
                return new SaveOperation(orderName, order);
            case DELETE_COMMAND:
                return new RemoveOperation(orderName, order);
            case FIND_COMMAND:
                return new FindOperation(orderName, order);
            case HELP_COMMAND:
                // A method that make a helpOperation containing the help information.
                return getHelpList();
            default:
                throw new UnknownOrderDukeException();
            }
        } catch (DukeException e) {
            throw e;
        }
    }


}
