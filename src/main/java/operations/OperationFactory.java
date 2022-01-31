package operations;

<<<<<<< HEAD
=======
import exceptions.DukeException;
import exceptions.UnknownOrderDukeException;

>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
public class OperationFactory {

    private String order;

    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

<<<<<<< HEAD


=======
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
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
<<<<<<< HEAD
     * @return Certain operation
     */
    public Operation makeOperation() {
=======
     *
     * @return Certain operation
     */
    public Operation makeOperation() throws DukeException {
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
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
<<<<<<< HEAD
            return new AddOperation(orderName, order);
=======
            //return new AddOperation(orderName, order);
            throw new UnknownOrderDukeException();
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
        }
    }

}
