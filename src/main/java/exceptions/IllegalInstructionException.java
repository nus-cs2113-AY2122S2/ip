package exceptions;

public class IllegalInstructionException extends DukeExceptions {
    protected static final String INSTRUCTION_ERROR_MSG =
            "…（⊙_⊙；）… Sorry I can't understand. Please check the user guide for acceptable instructions";

    @Override
    public String toString() {
        return INSTRUCTION_ERROR_MSG;
    }
}
