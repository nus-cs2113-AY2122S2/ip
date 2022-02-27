package exceptions;

public class EmptyListException extends DukeExceptions{
    protected static final String EMPTY_LIST_ERROR_MSG = "The list is empty currently (￣3￣)a";

    @Override
    public String toString(){
        return EMPTY_LIST_ERROR_MSG;
    }

}
