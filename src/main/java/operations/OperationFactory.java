package operations;

public class OperationFactory {

    private String order;

    public OperationFactory(String orderLocal) {

        order = orderLocal;

    }

    public OperationFactory() {

    }

    public void setOrder(String orderLocal) {
        order = orderLocal;
    }

    public Operation makeOperation() {
        String orderName = order.split(" ", 2)[0];
        switch (orderName) {
        case "bye":
            return new ByeOperation(orderName, order);
        case "list":
            return new ListOperation(orderName, order);
        case "blah":
            return new BlahOperation(orderName, order);
        case "mark":
            return new MarkOperation(orderName, order);
        case "unmark":
            return new UnmarkOperation(orderName, order);
        default:
            return new AddOperation(orderName, order);
        }
    }

}
