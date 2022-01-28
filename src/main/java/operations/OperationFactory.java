package operations;

public class OperationFactory {

    private String order;

    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";



    public OperationFactory(String orderLocal) {

        order = orderLocal;

    }

    public OperationFactory() {

    }

    public void setOrder(String orderLocal) {
        order = orderLocal;
    }

    /**
     * Makes certain operation according to the orderName
     * @return Certain operation
     */
    public Operation makeOperation() {
        String orderName = order.split(" ", 2)[0];

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
        default:
            return new AddOperation(orderName, order);
        }
    }

}
