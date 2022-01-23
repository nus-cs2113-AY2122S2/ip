public class OperationFactory {

    private String order;

    public OperationFactory(String _order){

        order = _order;

    }

    public OperationFactory() {

    }

    public void orderSetter(String _order){

        order = _order;

    }

    public Operation makeOperation(){

        String orderName = order.split(" ",2)[0];
        switch (orderName) {
            case "bye":
                return new ByeOperation(orderName, order);
            case "list":
                return new ListOperation(orderName, order);
            case "blah":
                return new BlahOperation(orderName, order);
            default:
                return new AddOperation(orderName, order);
        }

    }

}
