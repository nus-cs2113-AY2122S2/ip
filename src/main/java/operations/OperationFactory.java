package operations;

import java.util.HashMap;
import exceptions.DukeException;
import exceptions.UnknownOrderDukeException;

public class OperationFactory {

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
    private static final String[] ORDER_LIST = {BYE_COMMAND,LIST_COMMAND,MARK_COMMAND,UNMARK_COMMAND,TODO_COMMAND, DEADLINE_COMMAND, EVENT_COMMAND, DELETE_COMMAND, SAVE_COMMAND, FIND_COMMAND, HELP_COMMAND};
    private HashMap<String,Operation> operationCache;
    private String[] helpMessages;

    public OperationFactory(String orderLocal) {
        order = orderLocal;
    }

    public OperationFactory() {
        helpMessages = new String[ORDER_LIST.length];
        operationCache = new HashMap<>();
    }

    public void setOrder(String orderLocal) {
        order = orderLocal;
    }

    public Operation getOperation() throws DukeException {
        String orderName = order.split(" ", 2)[0];
        if (operationCache.containsKey(orderName)==true) {
            operationCache.get(orderName).setOrder(order);
            operationCache.get(orderName).executeOperation();
            return operationCache.get(orderName);
        } else
        {
            operationCache.put(orderName, makeOperation(orderName, order));
            return operationCache.get(orderName);
        }
    }

    /**
     * Makes certain operation according to the orderName
     *
     * @return Certain operation
     */
    private Operation makeOperation(String orderName,String order) throws DukeException {
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
            return new SaveOperation(orderName,order);
        case DELETE_COMMAND:
            return new RemoveOperation(orderName, order);
        case FIND_COMMAND:
            return new FindOperation(orderName,order);
        default:
            throw new UnknownOrderDukeException();
        }
    }


}
