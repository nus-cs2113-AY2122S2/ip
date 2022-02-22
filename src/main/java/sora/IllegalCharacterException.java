package sora;

/**
 * Thrown to indicate that the user's command input contains at least one character that is regarded
 * as illegal due to design constraints.
 * <p>
 * For example, the pipe character ("|") is not allowed because this character is used as a delimiter
 * for data stored in Sora's storage file.
 */
public class IllegalCharacterException extends Exception {
    public static final String ILLEGAL_CHARACTER_MSG = "Input string contains an illegal character.";

    private String throwingMethod;
    private String throwingClass;

    /**
     * Constructs an IllegalCharacterException with a specified error message.
     *
     * @param errorMsg The message written by the throwing method.
     * @param throwingMethod (to be removed)
     * @param throwingClass (to be removed)
     */
    public IllegalCharacterException(String errorMsg, String throwingMethod, String throwingClass) {
        super(errorMsg);
        this.throwingMethod = throwingMethod;
        this.throwingClass = throwingClass;
    }

    public String getThrowingMethod() {
        return this.throwingMethod;
    }

    public String getThrowingClass() {
        return this.throwingClass;
    }
}
