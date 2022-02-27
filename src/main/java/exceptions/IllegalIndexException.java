package exceptions;

public class IllegalIndexException extends DukeExceptions{
    protected static final String ILLEGAL_INDEX_ERROR_MSG = "I can't find the task from the list (°ー°〃) Please check your index again";

    @Override
    public String toString(){
        return ILLEGAL_INDEX_ERROR_MSG;
    }

}
