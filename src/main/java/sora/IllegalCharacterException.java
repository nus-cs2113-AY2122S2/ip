package sora;

public class IllegalCharacterException extends Exception {
    public static final String ILLEGAL_CHARACTER_MSG = "Input string contains an illegal character.";

    private String throwingMethod;
    private String throwingClass;

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
